package testcases;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import com.relevantcodes.extentreports.LogStatus;

import modules.ExcelData;
import modules.GeneralActions;
import modules.GeneralFunctions;
import modules.Login;
import modules.Run;

public class RunsTabListView {

	public Run run;
	public GeneralActions general_actions;
	public WebDriver driver;
	public GeneralFunctions general_functions;
	public ExcelData excel_data;
	public ExtentReports report;
	public ExtentTest test;
	public FileInputStream result_file;
	public FileOutputStream file_writing;
	public List<String> table_headers;
	public XSSFWorkbook wb;
	public XSSFSheet s;
	public int j = 1;
	public static String folder_path=null;
	List<WebElement> columns;
	List<WebElement> availableColumns;
	
	@BeforeClass
	public void setUp() throws IOException, InterruptedException{	
		
		excel_data = new ExcelData();
		general_functions = new GeneralFunctions();

		report=LogInApplication.report;
		test =LogInApplication.test;
		
		file_writing=LogInApplication.file_writing;
		result_file= LogInApplication.result_file;
		
		wb= LogInApplication.wb;
		s= LogInApplication.s;
		
		table_headers=LogInApplication.table_headers;
		
		driver = Login.driver;
		

		run = PageFactory.initElements(driver, Run.class);
		general_actions = PageFactory.initElements(driver, GeneralActions.class);
		
		run.run_tab.click();
		s = wb.getSheet("ListViewTC");
				
	}

		
	@Test
	public void toVerifySortingOnAllColumns() throws InterruptedException, IOException {

		Thread.sleep(3000);
		
		run.edit_columns_button.click();
		columns = driver.findElements(By.xpath("//ul[@id='available-li']/li"));

		run.enableMultipleColumns(columns, driver);
		run.edit_columns_save_button.click();

		Thread.sleep(10000);

		for (int j = 1; j < run.table_headers.size(); j++) {

			System.out.println("Column: " + j);
			run.table_headers.get(j).click();
			
			System.out.println("Ascending sorting per: " + run.table_headers.get(j).getText());

			Thread.sleep(5000);

			List<WebElement> rowsAfterAscending = driver.findElements(By.xpath("//div[@class='col-md-12']/table/tbody/tr"));
			System.out.println("Number of rows after ascending sorting: "+rowsAfterAscending.size());
			
			Assert.assertTrue(rowsAfterAscending.size() != 0);
			run.table_headers.get(j).click();

			System.out.println("Descending sorting per: " + run.table_headers.get(j).getText());

			Thread.sleep(5000);

			List<WebElement> rowsAfterDescending = driver.findElements(By.xpath("//div[@class='col-md-12']/table/tbody/tr"));
			System.out.println("Number of rows after descending sorting: "+rowsAfterDescending.size());

			Assert.assertTrue(rowsAfterDescending.size() != 0);
		}
		
		run.edit_columns_button.click();
		run.restore_to_default_columns_button.click();
		run.edit_columns_save_button.click();
		Assert.assertTrue(true);
	}		
		

	

	
	// To verify the menu bar title on List View

	@Test
	public void toVerifyMenuBarTitleOnListView() throws InterruptedException {

		test = report.startTest("toVerifyMenuBarTitleOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		Assert.assertEquals(general_actions.menu_bar_title.getText(), "Cumulus");

		
	}

	// To verify Help link on the menu bar on List View
	
	@Test
	public void toVerifyHelpLinkOnMenuBarOnListView() throws InterruptedException {

		test = report.startTest("toVerifyHelpLinkOnMenuBarOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");
		
		Assert.assertEquals(general_actions.menu_bar_help_link.getText(), "Help");
		test.log(LogStatus.INFO, "Help option is available on the Menu Bar in List View");

		Assert.assertEquals(general_actions.menu_bar_help_link.getAttribute("href"),"https://drive.google.com/open?id=0B0RzGmgFaNKlS1pWMjFzSndjX1k");
	}

	// To verify logo on the menu bar on List View
	
	@Test
	public void toVerifyLogoOnListView() throws InterruptedException {

		test = report.startTest("toVerifyLogoOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		Assert.assertTrue(general_actions.menu_bar_logo.isDisplayed());
		Assert.assertEquals(general_actions.menu_bar_logo.getAttribute("title"), "Roche Genia");
	}

	// To verify profile name on the menu bar on List View
	
	@Test
	public void toVerifyProfileNameOnListView() throws InterruptedException, IOException {

		test = report.startTest("toVerifyProfileNameOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");


		Assert.assertEquals(general_actions.menu_bar_profile_name.getText(), excel_data.getProfileName());

	}

	// To verify the presence of basic elements
	
	@Test
	public void toVerifyPresenceOfBasicElementsOnListViewPage() throws InterruptedException {

		test = report.startTest("toVerifyPresenceOfBasicElementsOnListViewPage");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		Assert.assertTrue(run.export_results_button.isDisplayed());
		test.log(LogStatus.INFO, "Export Results button is present in List View");

		Assert.assertTrue(run.extrapolation_checkbox.isDisplayed());
		test.log(LogStatus.INFO, "Extrapolation button is present in List View");

		Assert.assertTrue(run.switch_view_button.isDisplayed());
		test.log(LogStatus.INFO, "Switch view button is present");

		// Assert.assertTrue(run.reanalyze_button.isDisplayed());
		// Assert.assertTrue(run.dvt_button.isDisplayed());
		// Assert.assertTrue(run.web_dvt_button.isDisplayed());
	}

	// To verify the count of filters selected when user checks all the filters
	
	@Test
	public void toVerifyCheckAllFiltersOnListView() throws IOException, InterruptedException {

		test = report.startTest("toVerifyCheckAllFiltersOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		run.enableAllFilters(); // To enable all the filters

		test.log(LogStatus.INFO, "All filters are selected successfully");
		test.log(LogStatus.INFO, "Total number of filters selected: " + run.filter_tab.size());

		test.log(LogStatus.INFO, "Actual number of filters selected: " + run.filter_tab.size() + "and Expected is: "+ (int) excel_data.Run_Data_Sheet_2.getRow(1).getCell(1).getNumericCellValue());

		Assert.assertEquals(run.filter_tab.size(),(int) excel_data.Run_Data_Sheet_2.getRow(1).getCell(1).getNumericCellValue());

		run.resetDefaultFilters(); // To reset to default filters
		//driver.navigate().refresh();

	}

	// To verify the count of filters selected when user reset filters to default

	@Test
	public void toVerifyResetToDefaultFiltersOnListView() throws IOException, InterruptedException {

		test = report.startTest("toVerifyResetToDefaultFiltersOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		run.resetDefaultFilters();// To reset to default filters
		Thread.sleep(4000);
		test.log(LogStatus.INFO, "Reset to default button is clicked and only default filters are selected");

		//driver.navigate().refresh();

		test.log(LogStatus.INFO, "Total number of filters selected by default: " + run.filter_tab.size()+ " and Expected is: " + (int) excel_data.Run_Data_Sheet_2.getRow(0).getCell(1).getNumericCellValue());

		Assert.assertEquals(run.filter_tab.size(),(int) excel_data.Run_Data_Sheet_2.getRow(0).getCell(1).getNumericCellValue());

	}

	// To verify the count of filters selected when user clear all filters
	
	@Test
	public void toVerifyClearFiltersOnListView() throws InterruptedException {

		test = report.startTest("toVerifyClearFiltersOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		run.clearFiltersSet(driver); // To clear the filter
		test.log(LogStatus.INFO, "All the selected filters are cleared successfully");

		Assert.assertEquals(run.table_count_text.getText(), run.getTotalNumberOfRuns() + " Total Runs, "+ run.getTotalNumberOfRuns() + " Runs Matching Filter, 0 Selected");

	}

	// To verify the count of filters enabled by user
	
	@Test
	public void toVerifyFiltersEnableOnListView() throws InterruptedException, IOException {

		test = report.startTest("toVerifyFiltersEnableOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		int number_of_filters_after_enabling = -1;
		int number_of_default_filters = -1;

		int number_of_filters_to_be_enabled = excel_data.Run_Data_Sheet.getLastRowNum() - 1;
		run.clearFiltersSet(driver);
		run.resetDefaultFilters();
		test.log(LogStatus.INFO, "Reset to default button clicked and only default filters are selected");

		run.filters_tab_dropdown.click();
		number_of_default_filters = run.filter_tab.size();
		test.log(LogStatus.INFO, "Number of filters selected by default: " + number_of_default_filters);

		run.close_filters_button.click();

		int row_index = 0;

		for (row_index = 2; row_index <= excel_data.Run_Data_Sheet.getLastRowNum(); row_index++) {

			run.enableAFilter(excel_data.Run_Data_Sheet.getRow(row_index).getCell(0).getStringCellValue(), driver); // To enable a filter
																													
		}

		test.log(LogStatus.INFO, "Filters are selected successfully");

		run.filters_tab_dropdown.click();
		number_of_filters_after_enabling = run.filter_tab.size();

		Thread.sleep(5000);

		run.close_filters_button.click();
		test.log(LogStatus.INFO, "Number of filters after enabling :  " + number_of_filters_after_enabling);

		Assert.assertEquals(number_of_filters_after_enabling,number_of_filters_to_be_enabled + number_of_default_filters);
		run.resetDefaultFilters();   //To reset the filters to default
		Thread.sleep(3000);
		
	}

	// To verify the count of columns user selected
	
	@Test
	public void toVerifyColumnsSelectionOnListView() throws InterruptedException, IOException {

		int number_of_columns_after_selection;
		// int number_of_columns_after_selection = -1;
		int number_of_default_columns = -1;
		int number_of_columns_to_be_selected = excel_data.Run_Data_Sheet_3.getLastRowNum() - 1;

		test = report.startTest("toVerifyColumnsSelectionOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		run.resetDefaultColumns(driver);
		test.log(LogStatus.INFO, "Reset to default button clicked and only default columns are selected");

		run.edit_columns_button.click();
		number_of_default_columns = run.selected_columns.size();
		test.log(LogStatus.INFO, "Number of columns selected by default: " + number_of_default_columns);

		run.edit_columns_cancel_button.click();
		int row_index = 0;

		for (row_index = 2; row_index <= excel_data.Run_Data_Sheet_3.getLastRowNum(); row_index++) {

			run.enableAColumn(excel_data.Run_Data_Sheet_3.getRow(row_index).getCell(0).getStringCellValue(), driver); // To
																														// enable
																														// 																													// column
		}
		test.log(LogStatus.INFO, "Some columns are selected from the available list of columns");

		number_of_columns_after_selection = run.table_headers.size() - 1; // -1 for the first column which is a checkbox
																			
		test.log(LogStatus.INFO, "Total number of columns after selection: " + number_of_columns_after_selection);

		Assert.assertEquals(number_of_columns_after_selection,number_of_columns_to_be_selected + number_of_default_columns);
	}

	// To verify the count of columns selected when user reset columns to default

	@Test
	public void toVerifyResetToDefaultColumnsOnListView() throws InterruptedException, IOException {

		test = report.startTest("toVerifyResetToDefaultColumnsOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		test.log(LogStatus.INFO, "User switched to List view successfully");

		run.resetDefaultColumns(driver); // To reset to default columns
		test.log(LogStatus.INFO, "Reset to default button is clicked and only default columns are present");
		test.log(LogStatus.INFO, "Number of columns present by default: " + (run.table_headers.size()-1));

		driver.navigate().refresh();

		Assert.assertEquals(run.table_headers.size()-1,(int) excel_data.Run_Data_Sheet_2.getRow(2).getCell(1).getNumericCellValue());
	}

	// To verify basic flow of filters
	
	@Test
	public void toVerifyBasicFilterFlow() throws InterruptedException, IOException {

		test = report.startTest("toVerifyBasicFilterFlow");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		run.resetDefaultFilters();
		test.log(LogStatus.INFO, "Reset to default filters button clicked, only default filters are availble");

		for (int row_index = 2; row_index <= run.getLastRowIndex(general_actions); row_index++) {

			run.enableAFilter(run.getFilterName(row_index, general_actions), driver); // To enable the filter if disabled
																					
			run.enableAColumn(run.getFilterName(row_index, general_actions), driver); // To enable the column if disabled
																						
			Thread.sleep(10000);
			test.log(LogStatus.INFO, "Required Filter and Column enabled successfully");
			run.clearFiltersSet(driver); // To clear the filter

			run.setAFilter(	run.getFilterValue(row_index, run.getFilterType(row_index, general_actions), general_actions),run.getFilterType(row_index, general_actions), run.getFilterName(row_index, general_actions),	driver); // To set the filters
			test.log(LogStatus.INFO, "Records are filterd by setting some value for the required filter ");

			// To verify the value displayed in the label of the filter set
			
			if (run.getFilterType(row_index, general_actions).equals("numeric")) {
				Assert.assertEquals((run.getFilterName(row_index, general_actions).concat(": > " + run.getFilterValue(row_index,run.getFilterType(row_index, general_actions), general_actions))),run.getFilterLabelValue(run.getFilterName(row_index, general_actions), driver).getText());
				test.log(LogStatus.INFO, "Value displayed in the label of filter set verified ");
			} else {
				Assert.assertEquals((run.getFilterName(row_index, general_actions).concat(": " + run.getFilterValue(row_index,run.getFilterType(row_index, general_actions), general_actions))),run.getFilterLabelValue(run.getFilterName(row_index, general_actions), driver).getText());
				test.log(LogStatus.INFO, "Value displayed in the label of filter set verified ");
			}

			// To verify the count of filtered records
			
			if (run.getFilterName(row_index, general_actions).equals("Run Name")) { // For only 'Run Name' filter count=1
																				
				Assert.assertEquals(1, run.filtered_records.size());
				test.log(LogStatus.INFO, "Count of filtered records verified ");
			} 
			else {
				Assert.assertFalse(run.filtered_records.size() < 0);
				test.log(LogStatus.INFO, "Count of filtered records verified ");
			}

			// To compare the column value of one filtered record with the filter value set
			
			if (run.getFilterType(row_index, general_actions).equals("numeric")) {
				
				Assert.assertFalse(Double.parseDouble(run.getCellValue(run.getFilterName(row_index, general_actions),run.filtered_record_cells, run.table_headers)) <= Double.parseDouble(run.getFilterValue(row_index,	run.getFilterType(row_index, general_actions), general_actions)));
			} 
			else {
				Assert.assertEquals(run.getCellValue(run.getFilterName(row_index, general_actions), run.filtered_record_cells,run.table_headers),run.getFilterValue(row_index, run.getFilterType(row_index, general_actions), general_actions)); // to check that actual column value is equal to the expected value
																												
			}
		}
		
	}
	
	// To verify the presence of all the pagination elements on List View
	
	@Test
	public void toVerifyPresenceOfPaginationElementsOnListView() throws InterruptedException {

		test = report.startTest("toVerifyPresenceOfPaginationElementsOnListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		List<String> count_of_records_per_page_options = Arrays.asList("10", "30", "50", "100", "200");
		List<String> boundary_links_options = Arrays.asList("First", "Last");
		List<String> direction_links_options = Arrays.asList("Previous", "Next");

		run.run_tab.click();
		test.log(LogStatus.INFO, "User switched to List view successfully");

		run.clearFiltersSet(driver);

		for (int count_of_records_per_page_list_index = 0; count_of_records_per_page_list_index < count_of_records_per_page_options.size(); count_of_records_per_page_list_index++) {

			Assert.assertTrue(run.getCountOfRecordsPerPagePaginationElement(count_of_records_per_page_options.get(count_of_records_per_page_list_index), driver).isDisplayed());
		}

		for (int boundary_links_index = 0; boundary_links_index < boundary_links_options.size(); boundary_links_index++) {

			switch (boundary_links_options.get(boundary_links_index)) {

			case "First":
				Assert.assertTrue(run.getBoundaryPaginationElement("1", driver).isDisplayed());
				break;

			case "Last":
				Assert.assertTrue(run.getBoundaryPaginationElement("totalPages", driver).isDisplayed());
				break;
			}
		}

		for (int direction_links_index = 0; direction_links_index < direction_links_options.size(); direction_links_index++) {

			switch (direction_links_options.get(direction_links_index)) {

			case "Previous":
				Assert.assertTrue(run.getDirectionPaginationElement("page - 1", driver).isDisplayed());
				break;

			case "Next":
				Assert.assertTrue(run.getDirectionPaginationElement("page + 1", driver).isDisplayed());
				break;
			}
		}

		Thread.sleep(10000);
		System.out.println(run.table_count_text.getText().trim());

		Assert.assertEquals(run.table_count_text.getText().trim(), run.getTotalNumberOfRuns() + " Total Runs, "+ run.getTotalNumberOfRuns() + " Runs Matching Filter, 0 Selected");
	}

	// To verify the count of number of pages for for different records per page options
	
	@Test
	public void toVerifyTheCountOfTotalPagesToBeDisplayedForDifferentRecordsPerPage() throws InterruptedException {

		test = report.startTest("toVerifyTheCountOfTotalPagesToBeDisplayedForDifferentRecordsPerPage");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		double[] records_per_page = { 10.0, 30.0, 50.0, 100.0, 200.0 };

		System.out.println("No. of pages expected:" + (int) Math.ceil(run.getTotalNumberOfRuns() / 10.0));
		System.out.println("Last Page number:" + run.page_number.get(run.page_number.size() - 1).getText().trim());
		//System.out.println(run.viewSelected(driver));

		for (int records_per_page_array_index = 0; records_per_page_array_index < records_per_page.length; records_per_page_array_index++) {

			run.getCountOfRecordsPerPagePaginationElement(Integer.toString((int) records_per_page[records_per_page_array_index]), driver).click();

			Thread.sleep(20000);

			run.getBoundaryPaginationElement("totalPages", driver).click();
			System.out.println(run.page_number.get(run.page_number.size() - 1).getText().trim());

			System.out.println("Total number of Runs:" + run.getTotalNumberOfRuns());
			test.log(LogStatus.INFO,"For pagination option :" + (int) records_per_page[records_per_page_array_index]+ " number of pages are : "	+ Integer.parseInt(run.page_number.get(run.page_number.size() - 1).getText().trim()));

			Assert.assertEquals(Integer.parseInt(run.page_number.get(run.page_number.size() - 1).getText().trim()),	(int) Math.ceil(run.getTotalNumberOfRuns() / records_per_page[records_per_page_array_index]));
		}
	}

	// To verify the functionality of direction links on Pagination
	
	@Test
	public void toVerifyTheFunctionalityOfDirectionLinksPaginationElement() throws InterruptedException {

		test = report.startTest("toVerifyTheFunctionalityOfDirectionLinksPaginationElement");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		List<String> direction_links = Arrays.asList("Previous", "Next");
		String old_page_number = null;
		String new_page_number = null;

		run.run_tab.click();
		test.log(LogStatus.INFO, "User switched to List view successfully");

		for (int direction_links_index = 0; direction_links_index < direction_links.size(); direction_links_index++) {

			switch (direction_links.get(direction_links_index)) {

			case "Previous":
				old_page_number = run.page_number_selected.getText();
				run.getDirectionPaginationElement("page - 1", driver).click();
				new_page_number = run.page_number_selected.getText();

				if (!old_page_number.equals("1")) {
					Assert.assertEquals(Integer.parseInt(new_page_number), Integer.parseInt(old_page_number) - 1);
				} else {
					Assert.assertEquals(new_page_number, old_page_number);
				}
				test.log(LogStatus.INFO, "Previous Link verified successfully");
				break;

			case "Next":
				old_page_number = run.page_number_selected.getText();
				run.getDirectionPaginationElement("page + 1", driver).click();
				new_page_number = run.page_number_selected.getText();

				if (old_page_number.equals(run.getLastPageNumber(driver))) {
					Assert.assertEquals(new_page_number, old_page_number);
				} 
				else {
					Assert.assertEquals(Integer.parseInt(new_page_number), Integer.parseInt(old_page_number) + 1);
				}
				test.log(LogStatus.INFO, "Next Link verified successfully");
				break;

			default:
				test.log(LogStatus.INFO, "Invalid Direction Link");
			}
		}
	}

	// To verify the functionality of boundary links on Pagination
	
	@Test
	public void toVerifyTheFunctionalityOfBoundaryLinksPaginationElement() throws InterruptedException {

		test = report.startTest("toVerifyTheFunctionalityOfBoundaryLinksPaginationElement");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		List<String> boundary_links = Arrays.asList("First", "Last");
		String new_page_number = null;

		run.run_tab.click();
		test.log(LogStatus.INFO, "User switched to List view successfully");

		for (int boundary_links_index = 0; boundary_links_index < boundary_links.size(); boundary_links_index++) {

			switch (boundary_links.get(boundary_links_index)) {

			case "First":
				run.getBoundaryPaginationElement("1", driver).click();
				
				new_page_number = run.page_number_selected.getText();

				Assert.assertEquals(Integer.parseInt(new_page_number), 1);
				test.log(LogStatus.INFO, "First Page Link in pagination verified successfully");
				break;

			case "Last":
				run.getBoundaryPaginationElement("totalPages", driver).click();
				
				new_page_number = run.page_number_selected.getText();

				Assert.assertEquals(new_page_number, run.getLastPageNumber(driver));
				test.log(LogStatus.INFO, "Last Page Link in pagination verified successfully");
				break;

			default:
				test.log(LogStatus.INFO, "Invalid Link");
			}
		}
	}
	
	// To verify basic extrapolation flow

	@Test
	public void toVerifyBasicExtrapolationFlow() throws NumberFormatException, IOException, InterruptedException {

		test = report.startTest("toVerifyBasicExtrapolationFlow");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		run.run_tab.click();
		test.log(LogStatus.INFO, "User switched to List view successfully");

		List<String> extrpolation_columns = Arrays.asList("High quality reads", "Single Pores After Tagflow",
				"Single Pores", "Functional sequencing pores");
		List<String> extrpolation_divisor_column_values = Arrays.asList("10", "25", "50", "75", "100");

		String extrpolation_divisor_column = "Pct Cells Sampled";

		if (!run.checkAColumnSelectionStatus(extrpolation_divisor_column, driver)) {

			run.enableAColumn(extrpolation_divisor_column, driver);
		}

		driver.navigate().refresh();
		Thread.sleep(10000);

		if (!run.checkAFilterSelectionStatus(extrpolation_divisor_column, driver)) {
			run.enableAFilter(extrpolation_divisor_column, driver);
		}

		for (int extrpolation_divisor_column_values_index = 0; extrpolation_divisor_column_values_index < extrpolation_divisor_column_values.size(); extrpolation_divisor_column_values_index++) {

			Thread.sleep(10000);

			run.clearFiltersSet(driver);

			run.setAFilter(Integer.toString((Integer.parseInt(extrpolation_divisor_column_values.get(extrpolation_divisor_column_values_index)) - 1)),"numeric", extrpolation_divisor_column, driver);

			for (int extrpolation_columns_index = 0; extrpolation_columns_index < extrpolation_columns.size(); extrpolation_columns_index++) {

				if (!run.checkAFilterSelectionStatus(extrpolation_columns.get(extrpolation_columns_index), driver)) {
					run.enableAFilter(extrpolation_columns.get(extrpolation_columns_index), driver);
				}

				driver.navigate().refresh();
				Thread.sleep(10000);

				run.setAFilter("0", "numeric", extrpolation_columns.get(extrpolation_columns_index), driver);

				if (!run.checkAColumnSelectionStatus(extrpolation_columns.get(extrpolation_columns_index), driver)) {

					run.enableAColumn(extrpolation_columns.get(extrpolation_columns_index), driver);
				}

				driver.navigate().refresh();
				Thread.sleep(10000);

				run.getAColumnTableHeaderElement(extrpolation_divisor_column, run.table_headers).click(); // To sort the extrapolation_divisor_column
																										
				Thread.sleep(5000);

				if (run.extrapolation_checkbox.isSelected()) {

					run.extrapolation_checkbox.click();
				} 

				String before_extrapolation_value = run.getCellValue(
						extrpolation_columns.get(extrpolation_columns_index), run.filtered_record_cells,
						run.table_headers);

				System.out.println("before_extrapolation_value:" + before_extrapolation_value);
				System.out.println("Is selected extrapolation:" + run.extrapolation_checkbox.isSelected());

				if (!run.extrapolation_checkbox.isSelected()) {

					run.extrapolation_checkbox.click();
				}

				System.out.println("after_extrapolation_value:"	+ run.getCellValue("Adj." + extrpolation_columns.get(extrpolation_columns_index),run.filtered_record_cells, run.table_headers));

				Assert.assertEquals(run.getCellValue("Adj." + extrpolation_columns.get(extrpolation_columns_index),	run.filtered_record_cells, run.table_headers),run.getCalculatedExtrapolatedValue(before_extrapolation_value, general_actions,run.filtered_record_cells, extrpolation_divisor_column, run.table_headers));
			}
		}
	}

	// To verify the extrapolation table headers

	@Test
	public void toVerifyExtrapolationTableHeaders() throws InterruptedException {

		test = report.startTest("toVerifyExtrapolationTableHeaders");
		test.log(LogStatus.INFO, "User logged in to the application successfully");

		run.run_tab.click();
		test.log(LogStatus.INFO, "User switched to List view successfully");

		List<String> extrpolation_columns = Arrays.asList("High quality reads", "Single Pores After Tagflow","Single Pores", "Functional sequencing pores");

		String before_extrapolation_table_header = null;
		int index_of_table_header_of_column = -1;

		for (int extrpolation_columns_index = 0; extrpolation_columns_index < extrpolation_columns.size(); extrpolation_columns_index++) {

			if (!run.checkAColumnSelectionStatus(extrpolation_columns.get(extrpolation_columns_index), driver)) {

				run.enableAColumn(extrpolation_columns.get(extrpolation_columns_index), driver);
			}

			driver.navigate().refresh();
			Thread.sleep(10000);

			if (run.extrapolation_checkbox.isSelected()) {

				run.extrapolation_checkbox.click();
			}

			for (int index = 1; index < run.table_headers.size(); index++) {

				if (run.table_headers.get(index).getText().equals(extrpolation_columns.get(extrpolation_columns_index))) {

					before_extrapolation_table_header = run.table_headers.get(index).getText().trim();
					index_of_table_header_of_column = index;
				}
			}

			Thread.sleep(30000);

			if (!run.extrapolation_checkbox.isSelected()) {

				run.extrapolation_checkbox.click();
			}
			System.out.println("Expected:" + run.table_headers.get(index_of_table_header_of_column).getText().trim());
			System.out.println("Actual:" + "Adj." + before_extrapolation_table_header);

			test.log(LogStatus.INFO,"Actual header: " + run.table_headers.get(index_of_table_header_of_column).getText().trim()+ " Expected header: " + "Adj." + before_extrapolation_table_header);

			Assert.assertEquals(run.table_headers.get(index_of_table_header_of_column).getText().trim(),"Adj." + before_extrapolation_table_header);

		}

	}

//	// To verify the functionality of double clicking on a run in list view
//
//	@Test
//	public void toVerifyDoubleClickDetailView() throws InterruptedException {
//
//		test = report.startTest("toVerifyDoubleClickDetailView");
//		test.log(LogStatus.INFO, "User logged in to the application successfully");
//
//		//run.run_tab.click();
//		test.log(LogStatus.INFO, "User switched to List view successfully");
//
//		Actions action = new Actions(driver);
//		action.doubleClick(run.filtered_record_cells.get(0)).perform();
//		Assert.assertEquals(run.viewSelected(driver), "Detail View");
//		run.run_tab.click();
//	}

	// To verify all the links on RUns tab for List View

	@Test
	public void toVerifyAllTheLinksOnRunsTabForListView() throws Exception {

		test = report.startTest("toVerifyAllTheLinksOnRunsTabForListView");
		test.log(LogStatus.INFO, "User logged in to the application successfully");


		List<WebElement> linkElementsOnListView = new ArrayList<WebElement>();

		linkElementsOnListView = driver.findElements(By.tagName("a"));

		List<WebElement> linksOnListViewPage = new ArrayList<WebElement>();

		for (WebElement element : linkElementsOnListView)

		{
			String href = element.getAttribute("href");
			if (href != null && !href.isEmpty() && !href.contains("javascript"))

			{

				linksOnListViewPage.add(element);

			}

		}
		test.log(LogStatus.INFO, "Total Number of links on List View page : " + linksOnListViewPage.size());
		general_functions.isLinkBroken(linksOnListViewPage, wb, "ListViewLinks", driver);
		test.log(LogStatus.INFO, "Links on List view page verified succeessfully");

		Assert.assertTrue(true);

	}

	@AfterMethod
	public void tocaptureScreenshot(ITestResult testResult) throws InterruptedException, IOException {
		
		CellStyle style = wb.createCellStyle();
		file_writing = new FileOutputStream(new File("./Support_Files/GCP_Cumulus_Alpha.xlsx"));
		

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String[] date_time = dateFormat.format(date).split(" ");

		s.createRow(j).createCell(0).setCellValue(this.getClass().getSimpleName());
		s.getRow(j).createCell(1).setCellValue(testResult.getName());
		s.getRow(j).createCell(2).setCellValue("Selenium");
		
		
		if (testResult.getStatus() == ITestResult.FAILURE) {

			test.log(LogStatus.FAIL, "Test Case  " + testResult.getName() + "  failed :  " + testResult.getThrowable());

			style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);

			Font font = wb.createFont();
			font.setColor(IndexedColors.BLACK.getIndex());
			
			font.setBold(true);
			style.setFont(font);

			s.getRow(j).createCell(3).setCellValue("Failed");
			s.getRow(j).getCell(3).setCellStyle(style);
			
			System.out.println("Screenshot taken");
			general_functions.captureScreenshot(testResult.getName(),driver);

		} 
		else if (testResult.getStatus() == ITestResult.SUCCESS) {

			test.log(LogStatus.PASS, "Test Case " + testResult.getName() + " Passed");

			style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLACK.getIndex());
			
			font.setBold(true);
			style.setFont(font);

			s.getRow(j).createCell(3).setCellValue("Passed");
			s.getRow(j).getCell(3).setCellStyle(style);

		} 
		else {

			test.log(LogStatus.SKIP, "Test Case " + testResult.getName() + " Skipped");

			style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLACK.getIndex());
			
			style.setFont(font);

			s.getRow(j).createCell(3).setCellValue("Skipped");
			s.getRow(j).getCell(3).setCellStyle(style);

		}

		s.getRow(j).createCell(4).setCellValue(general_actions.menu_bar_profile_name.getText());
		s.getRow(j).createCell(5).setCellValue(date_time[0]);
		s.getRow(j).createCell(6).setCellValue(date_time[1]);

		wb.write(file_writing);
		report.endTest(test);

		j++;
	
	}

	@AfterClass
	public void logout() throws IOException {
		
		file_writing.flush();
		file_writing.close();
		

	}
}
