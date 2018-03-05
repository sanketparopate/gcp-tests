package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import modules.Login;
import modules.ExcelData;
import modules.GeneralActions;
import modules.GeneralFunctions;
import modules.Run;

public class RunsTabDetailView{
	
	
	public Run run;
	public ExcelData excel_data;
	public GeneralActions general_actions;
	public  ExtentReports report;
	public  ExtentTest test;
	public  FileInputStream result_file;
	public  FileOutputStream file_writing;
	public  List<String> table_headers;
	public  XSSFWorkbook wb;
	public  XSSFSheet s;
	public int j = 1;
	public GeneralFunctions general_functions;

	@BeforeClass
	public void defineObjects() throws InterruptedException, IOException
	{
		
		general_functions = new GeneralFunctions();
		excel_data	= new ExcelData();
		
		report=LogInApplication.report;
		test =LogInApplication.test;
		
		file_writing=LogInApplication.file_writing;
		result_file= LogInApplication.result_file;
		
		wb= LogInApplication.wb;
		s= LogInApplication.s;
		
		table_headers=LogInApplication.table_headers;
		
		run = PageFactory.initElements(Login.driver, Run.class);
		general_actions = PageFactory.initElements(Login.driver, GeneralActions.class);
		s = wb.getSheet("DetailViewTC");
		
		Thread.sleep(30000);

			
	}
	//To be defined separately as Dashboard Test Cases
	//To verify the menu bar title on Detail View
	
		@Test
		public void toVerifyMenuBarTitleOnDetailView() throws InterruptedException
		{
				
			test = report.startTest("toVerifyMenuBarTitleOnDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			
			test.log(LogStatus.INFO, "User switched to Detail view successfully");
			
			Assert.assertEquals(general_actions.menu_bar_title.getText(), "Cumulus");
		}
		
		//To verify Help link on the menu bar on Detail View
		
		@Test
		public void toVerifyHelpLinkOnMenuBarOnDetailView() throws InterruptedException
		{
			
			test = report.startTest("toVerifyHelpLinkOnMenuBarOnDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			
			test.log(LogStatus.INFO, "User switched to Detail view successfully");
			
			Assert.assertEquals(general_actions.menu_bar_help_link.getText().toLowerCase(), "help");
			test.log(LogStatus.INFO, "Help option is available on the Menu Bar in Detail View");
			
			Assert.assertEquals(general_actions.menu_bar_help_link.getAttribute("href"), "https://drive.google.com/open?id=0B0RzGmgFaNKlS1pWMjFzSndjX1k");
		}
		
		//To verify logo on the menu bar on Detail View
		
		@Test
		public void toVerifyLogoOnDetailView() throws InterruptedException
		{
			
			test = report.startTest("toVerifyLogoOnDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			
			test.log(LogStatus.INFO, "User switched to Detail view successfully");

			
			Assert.assertTrue(general_actions.menu_bar_logo.isDisplayed());
			Assert.assertEquals(general_actions.menu_bar_logo.getAttribute("title"), "Roche Genia");
		}
		
		//To verify profile name on the menu bar on Detail View
		
		@Test
		public void toVerifyProfileNameOnDetailView() throws InterruptedException, IOException
		{
			
			test = report.startTest("toVerifyProfileNameOnDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			
			test.log(LogStatus.INFO, "User switched to Detail view successfully");

			Assert.assertEquals(general_actions.menu_bar_profile_name.getText(), excel_data.getProfileName());
		}
		
		//To compare Run titles on List and Detail View
		
		@Test
		public void toCompareRunTitleOnListAndDetailView() throws InterruptedException, IOException
		{
			
			test = report.startTest("toCompareRunTitleOnListAndDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			
			run.run_tab.click();
			test.log(LogStatus.INFO, "User clicks on Runs Tab.");
			
			String run_title_on_list_view= run.getCellValue("Run Name", run.filtered_record_cells, run.table_headers);
			test.log(LogStatus.INFO, "Run title on List View is: " + run_title_on_list_view);
			
			Actions action= new Actions(Login.driver);
			action.doubleClick(run.filtered_record_cells.get(1)).perform();
			
			String run_title_on_detail_view= run.run_title_on_detail_view.getText();
			test.log(LogStatus.INFO, "Run title on Detail View is: " + run_title_on_detail_view);
			
			Assert.assertTrue(run_title_on_detail_view.equals(run_title_on_list_view));
		
		}
		
		//To verify presence of pagination elements on Detail View
		
		@Test
		public void toVerifyPresenceOfPaginationElementsOnDetailView() throws InterruptedException
		{
			test = report.startTest("toVerifyPresenceOfPaginationElementsOnDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
				
			run.run_tab.click();
			test.log(LogStatus.INFO, "User clicks on Runs Tab.");
				
			run.switch_view_button.click();
			test.log(LogStatus.INFO, "User switched to Detail view successfully");

			List<String> count_of_records_per_page_options=  Arrays.asList("10","30","50","100","200"); 
					
					
			WebDriverWait wdw = new WebDriverWait(Login.driver, 20);
			wdw.until(ExpectedConditions.elementToBeClickable(run.filters_tab_dropdown));
			run.filters_tab_dropdown.click();
			Thread.sleep(5000);
			wdw.until(ExpectedConditions.elementToBeClickable(run.clear_filters_button));
			run.clear_filters_button.click();
			wdw.until(ExpectedConditions.elementToBeClickable(run.close_filters_button));
			run.close_filters_button.click();
			Thread.sleep(5000);
				
			for (int count_of_records_per_page_list_index=0; count_of_records_per_page_list_index< count_of_records_per_page_options.size();count_of_records_per_page_list_index++){
				
				try{
					Assert.assertTrue(run.getCountOfRecordsPerPagePaginationElement(count_of_records_per_page_options.get(count_of_records_per_page_list_index), Login.driver).isDisplayed());
					test.log(LogStatus.INFO, "Count of records per page element visibility verified successfully");
				}
				catch(AssertionError ae){
					test.log(LogStatus.INFO, "Assertion"+(count_of_records_per_page_list_index+1)+" failed!");
					ae.printStackTrace();
				}
			}
			
			try{
				Assert.assertTrue(run.getBoundaryPaginationElement("1", Login.driver).isDisplayed());
				test.log(LogStatus.INFO, "Switch to first element visibility verified successfully");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "FIRST Boundary Pagination Element Assertion Failed!");
				ae.printStackTrace();
			}
			
			try{
				Assert.assertTrue(run.getBoundaryPaginationElement("totalPages", Login.driver).isDisplayed());
				test.log(LogStatus.INFO, "Switch to last element visibility verified successfully");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "LAST Boundary Pagination Element Assertion Failed!");
				ae.printStackTrace();
			}
			
			try{
				Assert.assertTrue(run.getDirectionPaginationElement("page - 1", Login.driver).isDisplayed());
				test.log(LogStatus.INFO, "Switch to previous element visibility verified successfully");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "PREVIOUS Direction Pagination Element Assertion Failed!");
				ae.printStackTrace();
			}
			
			try{
				Assert.assertTrue(run.getDirectionPaginationElement("page + 1", Login.driver).isDisplayed());
				test.log(LogStatus.INFO, "Switch to next element visibility verified successfully");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "NEXT Direction Pagination Element Assertion Failed!");
				ae.printStackTrace();
			}
		}
		
		//To verify presence of basic elements on Detail View 
				
		@Test
		public void toVerifyPresenceOfBasicElementsOnDetailPage() throws InterruptedException
		{
			test = report.startTest("toVerifyPresenceOfBasicElementsOnDetailPage");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			run.run_tab.click();
			test.log(LogStatus.INFO, "User clicks on Runs Tab.");
			
			run.switch_view_button.click();
			test.log(LogStatus.INFO, "User switched to Detail view successfully");
				
			try{
				Assert.assertTrue(run.extrapolation_checkbox.isDisplayed());
				test.log(LogStatus.INFO, "Extrapolation checbox is present on Detail view");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "Assertion Failed!");
				ae.printStackTrace();
			}
					
			try{
				Assert.assertTrue(run.switch_view_button.isDisplayed());
				test.log(LogStatus.INFO, "Switch View button is present on Detail view");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "Assertion Failed!");
				ae.printStackTrace();
			}
					
			try{
				Assert.assertTrue(run.reanalyze_button.isDisplayed());
				test.log(LogStatus.INFO, "Reanalyze button is present on Detail view");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "Assertion Failed!");
				ae.printStackTrace();
			}
					
			try{
				Assert.assertTrue(run.dvt_button.isDisplayed());
				test.log(LogStatus.INFO, "DVT button is present on Detail view");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "Assertion Failed!");
				ae.printStackTrace();
			}
			
			try{
				Assert.assertTrue(run.web_dvt_button.isDisplayed());
				test.log(LogStatus.INFO, "Web DVT button is present on Detail view");
			}
			catch(AssertionError ae){
				test.log(LogStatus.INFO, "Assertion Failed!");
				ae.printStackTrace();
			}
		}
		
		//To compare column values between List and Detail View

				
				@Test
				public void toCompareTableColumnValuesOfListViewWithDetailView() throws InterruptedException, IOException{
					
								
					test = report.startTest("toCompareColumnValuesOfListViewWithDetailView");
					test.log(LogStatus.INFO, "User logged in to the application successfully");
					
					for(int row_index=2; row_index <= excel_data.Columns_To_Match_Sheet.getLastRowNum(); row_index++){
						
						
						boolean comparison_result = run.tableColumnDetailAndListViewValueComparison(run.getColumnNameToMatch(row_index, general_actions), Login.driver);
						try{
							Assert.assertTrue(comparison_result);
						}
						catch(AssertionError ae){
							test.log(LogStatus.INFO, "Assertion"+(row_index-1)+" Failed!");
							ae.printStackTrace();
						}
						
					
					}
				}
		
				//To compare Run overview values of detail view with List View
				@Test
				public void toCompareRunOverviewColumnValuesOfDetailViewWithListView() throws InterruptedException, IOException{
					
					test = report.startTest("toCompareRunOverviewColumnValuesOfDetailViewWithListView");
					test.log(LogStatus.INFO, "User logged in to the application successfully");
					
					for(int row_index=2; row_index <= excel_data.Run_Overview_Sheet.getLastRowNum(); row_index++){
						
						boolean comparison_result = run.overviewSectionColumnAndTableColumnValueComparison(excel_data.Run_Overview_Sheet.getRow(row_index).getCell(2).getStringCellValue().trim(), Login.driver, row_index);
						try{
							Assert.assertTrue(comparison_result);
						}
						catch(AssertionError ae){
							test.log(LogStatus.INFO, "Assertion"+(row_index-1)+" Failed!");
							ae.printStackTrace();
						}
					}
				}
		
				//To verify the presence of WebElements under Run Overview for Detail View

				@Test
				public void toVerifyThePresenceOfWebElementsUnderRunOverviewSectionForDetailView() throws InterruptedException, IOException{
					
					test = report.startTest("toVerifyThePresenceOfWebElementsUnderRunOverviewSectionForDetailView");
					test.log(LogStatus.INFO, "User logged in to the application successfully");
					run.run_tab.click();
					test.log(LogStatus.INFO, "User clicks on Runs Tab.");
					
					run.switch_view_button.click();
					test.log(LogStatus.INFO, "User switched to Detail view successfully");

					if(run.extrapolation_checkbox.isSelected()){

						run.extrapolation_checkbox.click();
					}
					
					for(int row_index=2; row_index< excel_data.Run_Overview_Sheet.getLastRowNum(); row_index++){
						
						try{
							Assert.assertTrue(run.getRunOverviewWebElement(Login.driver, excel_data.Run_Overview_Sheet.getRow(row_index).getCell(0).getStringCellValue(), (int)excel_data.Run_Overview_Sheet.getRow(row_index).getCell(1).getNumericCellValue()).isDisplayed());
						}
						catch(AssertionError ae){
							test.log(LogStatus.INFO, "Assertion"+(row_index-1)+" Failed!");
							ae.printStackTrace();
						}
					}
				}
		
				//To the URL of Run Status links o Detail View

				@Test
				public void toVerifyTheURLOfRunStatusLink() throws InterruptedException, IOException {
					
					test = report.startTest("toVerifyTheURLOfRunStatusLink");
					test.log(LogStatus.INFO, "User logged in to the application successfully");
					
					String filter_name="Run Status";
					
					run.run_tab.click();
					test.log(LogStatus.INFO, "User clicks on Runs Tab.");
					
					run.enableAFilter(filter_name, Login.driver);
					run.enableAColumn(filter_name, Login.driver);
					
					run.switch_view_button.click();
					test.log(LogStatus.INFO, "User switched to Detail view successfully");
					
					run.enableAColumn(filter_name, Login.driver);
					
					for(int row_index=2; row_index< excel_data.Run_Data_Sheet_4.getLastRowNum(); row_index++){
						
						
						String run_name = run.getRunNameWithParticularRunStatus(excel_data.Run_Data_Sheet_4.getRow(row_index).getCell(0).getStringCellValue().trim());
						String run_status_link_url= run.getRunStatusLinkURL(run_name, Login.driver);
						test.log(LogStatus.INFO, "URL :"+run_status_link_url);
						
						
						try{
							Assert.assertEquals(run_status_link_url, "http://merge1.rsc.roche.com//"+run_name+"//processing_logs/");
						}
						catch(AssertionError ae){
							test.log(LogStatus.INFO, "Assertion"+(row_index-1)+" Failed!");
							ae.printStackTrace();
						}
						
						}
					}
					
			
		//To that the records are sorted as per P_Folder Number Column

		@Test
		public void toVerifyRecordsAreSortedAsPerPFolderNumberColumn() throws InterruptedException, IOException{
			
			test = report.startTest("toVerifyRecordsAreSortedAsPerPFolderNumberColumn");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			
			run.run_tab.click();
			test.log(LogStatus.INFO, "User clicks on Runs Tab.");
			
			String filter_name="Number of Analyses";
						
			run.enableAColumn(filter_name, Login.driver);
			test.log(LogStatus.INFO,filter_name+" column enabled");
			
			Thread.sleep(10000);
			
			run.clearFiltersSet(Login.driver);
			Login.driver.navigate().refresh();
			
			run.enableAFilter(filter_name, Login.driver);
			test.log(LogStatus.INFO,filter_name+" filter enabled");
			Thread.sleep(20000);
			
			run.setAFilter("6", "numeric", filter_name, Login.driver);
			test.log(LogStatus.INFO,filter_name+" is set successfully with value 6");
			
			int number_of_analyses= Integer.parseInt(run.getCellValue(filter_name, run.filtered_record_cells, run.table_headers));
			test.log(LogStatus.INFO, "Number of Analyses:"+number_of_analyses);
			
			Actions action= new Actions(Login.driver);
			action.doubleClick(run.filtered_record_cells.get(0)).perform();
			
			Thread.sleep(3000);
			String column_name= "P_Folder Number";
			
			run.enableAColumn(column_name, Login.driver);
			
			int[] p_folder_numbers= new int[number_of_analyses];
			int record_index=-1; 
			
			for(int i=0; i< number_of_analyses; i++ ){
				record_index=i+1;
				
				p_folder_numbers[i]= run.getPFolderNumberColumnValueDetailViewTable(record_index, column_name);
				test.log(LogStatus.INFO, ("p_folder_numbers[i]:"+p_folder_numbers[i]));
				
			}
			
			for(int i=0;i <number_of_analyses;i++){
				
				if(!(i== (number_of_analyses-1))){
				Assert.assertTrue(p_folder_numbers[i] >= p_folder_numbers[i+1]);
				}
			}
		}
		
		//To verify all the links present on Detail View page

		@Test
		public void toVerifyAllTheLinksOnRunsTabForDetailView() throws MalformedURLException, Exception {
			
			test = report.startTest("toVerifyAllTheLinksOnRunsTabForDetailView");
			test.log(LogStatus.INFO, "User logged in to the application successfully");
			Thread.sleep(5000);
			
			run.run_tab.click();
			test.log(LogStatus.INFO, "User clicks on Runs Tab.");

			run.switch_view_button.click();
			test.log(LogStatus.INFO, "User switched to Detail view successfully");

			List<WebElement> linkElementsOnDetailView = new ArrayList<WebElement>();

			linkElementsOnDetailView = Login.driver.findElements(By.tagName("a"));

			List<WebElement> linksOnDetailViewPage = new ArrayList<WebElement>();
			

			for (WebElement element : linkElementsOnDetailView)

			{
				
				String href = element.getAttribute("href");
				if(href != null)
{
				if (!href.isEmpty() && !href.contains("javascript")&& href != "#")

					{

						linksOnDetailViewPage.add(element);
					}
				}

			}
			 test.log(LogStatus.INFO, "Total Number of links on Detail View page : "+linksOnDetailViewPage.size());
			 general_functions.isLinkBroken(linksOnDetailViewPage, wb, "DetailViewLinks",Login.driver);

			//Assert.assertTrue(true);

		}
		
		
		
		@AfterMethod
		public void tocaptureScreenshot(ITestResult testResult) throws InterruptedException, IOException{
			
			CellStyle style = wb.createCellStyle();
			file_writing = new FileOutputStream(new File("./Support_Files/GCP_Cumulus_Alpha.xlsx"));
			
			/*table_headers = new ArrayList<String>();
			int number_of_columns = s.getRow(0).getLastCellNum();

			for (int i = 0; i < number_of_columns; i++) {
				table_headers.add(s.getRow(0).getCell(i).getStringCellValue());
			}
*/
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			String[] date_time = dateFormat.format(date).split(" ");

			/*
			 * ExpectedCondition<Boolean> pageLoadCondition = new
			 * ExpectedCondition<Boolean>() { public Boolean apply(WebDriver driver)
			 * { return ((JavascriptExecutor) driver).executeScript(
			 * "return document.readyState").equals("complete"); } }; WebDriverWait
			 * wdw = new WebDriverWait(driver, 20); wdw.until(pageLoadCondition);
			 */


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
				test.log(LogStatus.INFO, "Screenshot taken");
				// general_actions.captureScreenshot(testResult.getName());

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
			// general_actions.getProfileNameDropdown(general_actions.getProfileName(),
			// driver).click();
			// general_actions.logout_option.click();
		
			
		}
}