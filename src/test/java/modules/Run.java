package modules;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Run{	
	@FindBy(xpath = "//button[@ng-click= 'runCtrl.reanalysis();']")	//Reanalyze button
	public WebElement reanalyze_button;
	
	@FindBy(xpath = "//button[@ng-click= 'runCtrl.launchDVT();']")	//DVT button
	public WebElement dvt_button;
	
	@FindBy(xpath = "//button[@ng-click= 'runCtrl.launchWebDVT();']")	//Web DVT button 
	public WebElement web_dvt_button;
	
	@FindBy(xpath = "//button[@id ='dropdownMenu1']")	//Export Results
	public WebElement export_results_button;
	
	@FindBy(xpath = "//div[@ng-click= 'runCtrl.checkExtrapolation()']/input")	//Extrapolation checkbox
	public WebElement extrapolation_checkbox;
	
	@FindBy(xpath = "//div[@options='filtered_columns']//button")	//Search Filters button
	public WebElement button_filters_selected;
	 
	@FindBy(id = "selectAll")	//Check All option of Search Filters 
	public WebElement button_filters_selected_check_all_button;
	
	@FindBy(id = "deselectAll")	//Reset To Default option of Search Filters 
	public WebElement button_filters_selected_reset_to_default_button;
	
	@FindBy(xpath = "//a[@href= contains(text(), '/runs')]")	//Run tab
	public WebElement run_tab;
	
	@FindBy(xpath = "//button[@ng-click= 'column_reorder_modal()']") //Edit Columns button 
	public WebElement edit_columns_button;  
	
	@FindBy(xpath = "//li[contains(text(), ' Num Thinning Cycles')]")	//Num Thinning Cycles column under available columns section
	public WebElement from;
	
	@FindBy(xpath= "//ul[@id='available-li']/li")	//List of column present in the Available column section of Edit columns pop up
	public List<WebElement> available_columns;
	
	@FindBy(xpath= "//ul[@id='listed-li']/li")	//List of column present in the Selected column section of Edit columns pop up
	public List<WebElement> selected_columns;
	
	@FindBy(xpath="//*[@id = 'listed-li']") 	//Selected column section of Edit columns pop up
	public WebElement to;
	
	@FindBy(xpath = "//button[@ng-click= 'reordering_done()']") 	//Save button in Edit column section
	public WebElement edit_columns_save_button;
	
	@FindBy(xpath = "//button[@ng-click= 'cancel()']") 	//Cancel button in Edit column section
	public WebElement edit_columns_cancel_button;
	
	@FindBy(xpath = "//div[@ng-click= 'reordering_done()']")  //Num Thinning Cycles table header
	public WebElement  Num_Thinning_Cycles_th;
	
	@FindBy(xpath = "//div[@ng-repeat='col in columns | filter: {filter_visible: true}']") 	// Filter tab displaying all the filters selected
	public List<WebElement> filter_tab;
	
	@FindBy(xpath = "//i[@class='pull-left fa fa-chevron-right']")	//Filter tab drop down
	public WebElement filters_tab_dropdown;
	
	@FindBy(xpath = "//button[@class= 'applyBtn btn btn-sm btn-success']") 	//Apply button of 'Analysis Start Date/Time' calendar
	public WebElement apply_calender_button;
	
	@FindBy(xpath = "//button[@ng-click= 'display_selected_filters(true);']") 	//Apply Filters button in Filter drop down tab
	public WebElement apply_filters_button;
	
	@FindBy(xpath = "//button[@ng-click= 'filter_clear();']") 	//Clear Filters button in Filter drop down tab
	public WebElement clear_filters_button;
	
	//@FindBy(xpath = "//a[@onclick='"+"$('.accordion-toggle').click();"+"']") //Close Filters button in Filter drop down tab
	@FindBy(xpath = "//*[contains(text(),'Close Filter')]")
	public WebElement close_filters_button;
	
	@FindBy(xpath = "//tr[@ng-repeat= 'run in $data']") 	//Records filtered out
	public List<WebElement> filtered_records;
	
	@FindBy(xpath = "//table[@ng-table='usersTable']//tr[1]/td[@ng-repeat='column in columns | filter : {datatable_visible: true} | orderBy : column.datatable_position']") 	//Records filtered out
	public List<WebElement> filtered_record_cells;
	
	@FindBy(xpath = "//table[@id='tbl-runs-analysis']//tr[1]/td[@ng-click='runCtrl.get_analysis_run(br.file_name)']") 	//Records filtered out
	public List<WebElement> filtered_record_cells_detail_view;
	
	@FindBy(xpath = "//table[@ng-table='usersTable']/thead/tr/th") //Table headers of the table
	public List<WebElement> table_headers;
	
	@FindBy(xpath = "//table[@id='tbl-runs-analysis']//thead//th")
	public List<WebElement> table_headers_detail_view;
		
	@FindBy(xpath = "//li[@ng-repeat= 'option in options | filter: searchFilter']") 	//Filters selected drop down
	public List<WebElement> filters_dropdown;
	
	@FindBy(xpath = "//li [@id = 'multi_example_chzn_c_3']/span")	//Labels of filters set 
	public List<WebElement> filters_set_value;
	
	@FindBy(xpath = "//button[contains(text(),'Switch View')]")	//Switch View button
	public WebElement switch_view_button;
	
	@FindBy(xpath = "//li[@ng-repeat='page in pages track by $index']/a")	//Pagination element corresponding to page number
	public List<WebElement> page_number;
	
	@FindBy(xpath = "//li[@class='pagination-page active']/a")	//Pagination element corresponding to page number selected
	public WebElement page_number_selected;
		
	@FindBy(xpath = "//div[@class='table-counts-section']") 	//Count of records text displayed above the table
	public WebElement table_count_text;
		
	@FindBy(xpath = "//button[@ng-click='reset_default()']")
	public WebElement restore_to_default_columns_button;
	
	@FindBy(xpath = "//div[@class= 'col-xs-8']/p[1]")
	public WebElement run_title_on_detail_view;
	
	@FindBy(xpath = "//table[@ng-table='usersTable']//tr[1]/td[@ng-repeat='column in columns | filter : {datatable_visible: true} | orderBy : column.datatable_position']") 	//Records filtered out
	public List<WebElement> filtered_record_first_row_cells;
	
	@FindBy(xpath = "//table[@id='tbl-runs-analysis']//tr[1]/td[@ng-click='runCtrl.get_analysis_run(br.file_name)']") 	//Records filtered out
	public List<WebElement> filtered_record_first_row_cells_detail_view;
	
	//Run Overview Web Elements
	
	public void openRunTab(){		
		run_tab.click();
	}
	
	public void enableAllFilters(){		
		button_filters_selected.click();
		button_filters_selected_check_all_button.click();
		button_filters_selected.click();
	}
	
	public void resetDefaultFilters(){	
		button_filters_selected.click();
		button_filters_selected_reset_to_default_button.click();
		button_filters_selected.click();
	}
	
	public void enableAFilter(String filter_name, WebDriver driver) throws InterruptedException{
		if (!checkAFilterSelectionStatus(filter_name, driver)){
			Thread.sleep(5000);
			button_filters_selected.click();
			getFilterToBeEnabled(filter_name,driver).click();
			button_filters_selected.click();
			driver.navigate().refresh();
			Thread.sleep(5000);
		}
	}
	
	//To clear the filters set
	public void clearFiltersSet(WebDriver driver) throws InterruptedException{
		WebDriverWait wdw = new WebDriverWait(driver, 20);
		wdw.until(ExpectedConditions.visibilityOf(filters_tab_dropdown));
		filters_tab_dropdown.click();
		wdw.until(ExpectedConditions.visibilityOf(clear_filters_button));
		clear_filters_button.click();
		Thread.sleep(5000);
		close_filters_button.click();
		Thread.sleep(20000);
		}
	
	public void enableAColumn(String column_name, WebDriver driver) throws InterruptedException{
		//System.out.println(checkAColumnSelectionStatus(column_name, driver));
		if (!checkAColumnSelectionStatus(column_name, driver)){
			WebDriverWait wdw = new WebDriverWait(driver, 20);
			wdw.until(ExpectedConditions.elementToBeClickable(edit_columns_button));
			Thread.sleep(5000);
			edit_columns_button.click();
			Actions a = new Actions(driver);
			a.dragAndDrop(getAvailableColumnToBeSelected(column_name, driver), to).build().perform();
			//wdw.until(ExpectedConditions.elementToBeClickable(edit_columns_save_button));

			edit_columns_save_button.click();
			}
		}
	
	public void resetDefaultColumns(WebDriver driver) throws InterruptedException{
		WebDriverWait wdw = new WebDriverWait(driver, 20);
		wdw.until(ExpectedConditions.elementToBeClickable(edit_columns_button));
		Thread.sleep(5000);
		edit_columns_button.click();
		restore_to_default_columns_button.click();
		wdw.until(ExpectedConditions.elementToBeClickable(edit_columns_save_button));
		edit_columns_save_button.click();
		driver.navigate().refresh();
		Thread.sleep(15000);
	}
	
	public void setAFilter(String filter_value, String filter_type, String filter_name, WebDriver driver) throws InterruptedException{	
		WebDriverWait wdw = new WebDriverWait(driver, 20);
		wdw.until(ExpectedConditions.visibilityOf(filters_tab_dropdown));	
		filters_tab_dropdown.click();
		WebElement filter_to_be_set= getFilterToBeSet(filter_name, filter_type, driver);
		wdw.until(ExpectedConditions.visibilityOf(filter_to_be_set));
		switch(filter_type){
		case "text":
			System.out.println("Filter To be Set :" +getFilterToBeSet(filter_name, filter_type, driver).getText());
			filter_to_be_set.sendKeys(filter_value);
		break;
		case "drop down":
			//System.out.println("Filter To be Set :" +getFilterToBeSet(filter_name, filter_type).getText());
			filter_to_be_set.click();
			driver.findElement(By.xpath("//li[@ng-repeat='option in options | filter:getFilter(input.searchFilter)']//span[text()= '"+filter_value+"']//preceding::input[1]")).click();
			filter_to_be_set.click();
		break;
		case "numeric":
			System.out.println("Filter To be Set :" +filter_name);
			filter_to_be_set.sendKeys(filter_value);
			break;
		case "date":
			//System.out.println("Filter To be Set :" +getFilterToBeSet(filter_name, filter_type).getText());
			filter_to_be_set.sendKeys(filter_value);
			apply_calender_button.click();
			break;
		default: System.out.println("Invalid Filter Type!");
		}
		wdw.until(ExpectedConditions.elementToBeClickable(apply_filters_button));
		apply_filters_button.click();
		wdw.until(ExpectedConditions.elementToBeClickable(close_filters_button));
		close_filters_button.click();
		driver.navigate().refresh();
	}	
	
	public boolean checkAFilterSelectionStatus(String filter_name, WebDriver driver) throws InterruptedException{	
		boolean is_selected;
		//System.out.println("checkAFilterSelectionStatus : "+filter_name);
		WebDriverWait wdw = new WebDriverWait(driver, 20);
    	wdw.until(ExpectedConditions.elementToBeClickable(button_filters_selected));
		Thread.sleep(3000);
		button_filters_selected.click();
		is_selected = getFilterToBeEnabled(filter_name, driver).isSelected();
		System.out.println("checkAFilterSelectionStatus:"+is_selected);
		button_filters_selected.click();
		return is_selected;
	}
	
	public boolean checkAColumnSelectionStatus(String column_name, WebDriver driver) throws InterruptedException{
        boolean is_selected=false;
		WebDriverWait wdw = new WebDriverWait(driver, 30);
    	wdw.until(ExpectedConditions.elementToBeClickable(edit_columns_button));
    	//driver.navigate().refresh();
    	Thread.sleep(10000);
    	edit_columns_button.click();
		System.out.println("checkAColumnSelectionStatus_Column name:"+column_name);
		for (int i=0; i< selected_columns.size(); i++){
			if (selected_columns.get(i).getText().trim().equals(column_name.trim())){
				System.out.println(selected_columns.get(i).getText());
				is_selected=true;
			}
			}
		System.out.println(is_selected);
		edit_columns_cancel_button.click();
		//driver.navigate().refresh();
		return is_selected;
	}
	
	public WebElement getFilterLabelValue(String filter_name, WebDriver driver){
		//System.out.println("getFilterLabel: "+filter_name.trim()+".");
		return driver.findElement(By.xpath("//div[@id='multi_example_chzn']//li [@id = 'multi_example_chzn_c_3']//strong[contains(text(), '"+filter_name+": ')]//parent::span"));
	}
	
	public WebElement getFilterToBeSet(String filter_name, String filter_type, WebDriver driver){
		//System.out.println("getFilterToBeSet"+filter_name);
		if(filter_type.equals("drop down")){
			return driver.findElement(By.xpath("//label[contains(text(),'"+filter_name+"')]//following-sibling::div"));
		}
		else{
		return driver.findElement(By.xpath("//label[text()='"+filter_name+"']//following-sibling::div//input[1]"));
		}	
	}
	
	public WebElement getFilterToBeEnabled(String filter_name, WebDriver driver){
		System.out.println("getFilterToBeEnabled: "+filter_name.trim());
		return driver.findElement(By.xpath("//li[@ng-repeat='option in options | filter:getFilter(input.searchFilter)']//span[text()='"+filter_name.trim()+"']//preceding::input[1]"));
	}
	
	public WebElement getAvailableColumnToBeSelected(String available_column_name, WebDriver driver){	
		//System.out.println("getAvailableColumnToBeSelected: "+available_column_name);
		return driver.findElement(By.xpath("//ul[@id='available-li']/li[contains(text(), ' "+available_column_name.trim()+"')]"));
	}
	
	public String getCellValue(String column, List<WebElement> filtered_record_cells, List<WebElement> table_headers ) throws IOException{	
		for(int i=1;i<table_headers.size();i++){		
			//System.out.println("getCellValue_Table Headers:"+table_headers.get(i).getText().trim());
			if(table_headers.get(i).getText().contains(column)){
				System.out.println("getCellValue:"+filtered_record_cells.get(i-1).getText());
				return filtered_record_cells.get(i-1).getText();
				}
			}
		return null;
	}
	
	public String getCellValueDetailView(String column, List<WebElement> filtered_record_cells, List<WebElement> table_headers ) throws IOException{	
		for(int i=0;i<table_headers.size();i++){		
			//System.out.println("getCellValue_Table Headers:"+table_headers.get(i).getText().trim());
			if(table_headers.get(i).getText().equals(column)){
				System.out.println("getCellValue:"+filtered_record_cells.get(i).getText());
				return filtered_record_cells.get(i).getText();
				}
			}
		return null;
		}
	
	public WebElement getAColumnTableHeaderElement(String column, List<WebElement> table_headers ) throws IOException{
		for(int i=0;i<table_headers.size();i++){
			//System.out.println("getCellValue_Table Headers:"+table_headers.get(i).getText().trim());
			if(table_headers.get(i).getText().contains(column)){
				System.out.println("getCellValue:"+filtered_record_cells.get(i-1).getText());
				return table_headers.get(i);
				}
			}
		return null;
		}
	public int getTotalNumberOfRuns() {
		return Integer.parseInt(table_count_text.getText().split(" ")[0].trim());
		}
	
	public String getLastPageNumber(WebDriver driver) throws InterruptedException{
		//getBoundaryPaginationElement("totalPages", driver).click();
		System.out.println(page_number.get(page_number.size()-1).getText().trim());
		return page_number.get(page_number.size()-1).getText().trim();
	}
	/*
	public String viewSelected(WebDriver driver) throws InterruptedException{
		
		WebDriverWait wdw = new WebDriverWait(driver, 20);
		wdw.until(ExpectedConditions.visibilityOf(switch_view_button_dropdown));
    	switch_view_button_dropdown.click();
    	
    	String view_selected=checked_view_option.getText().trim();
    	
    	wdw.until(ExpectedConditions.visibilityOf(switch_view_button_dropdown));
    	switch_view_button_dropdown.click();

    	
		//System.out.println("checked_view_option:"+view_selected);
		return view_selected;
	}
	
	public void switchToDetailView(WebDriver driver) throws InterruptedException{
		
		if(viewSelected(driver).equals("List View")){
			
			switch_view_button.click();
			ExpectedCondition<Boolean> pageLoadCondition = new
			        ExpectedCondition<Boolean>() {
			            @Override
						public Boolean apply(WebDriver river) {
			                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			            }
			        };
			WebDriverWait wdw = new WebDriverWait(driver, 20);
			wdw.until(pageLoadCondition);
			}
		}
	
	public void switchToListView(WebDriver driver) throws InterruptedException{
		
		if(viewSelected(driver).equals("Detail View")){
			
			switch_view_button.click();
			ExpectedCondition<Boolean> pageLoadCondition = new
			        ExpectedCondition<Boolean>() {
			            @Override
						public Boolean apply(WebDriver driver) {
			                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			            }
			        };
			WebDriverWait wdw = new WebDriverWait(driver, 20);
			wdw.until(pageLoadCondition);
		}
		
		
	}
	*/
	public WebElement getCountOfRecordsPerPagePaginationElement(String count_of_records_per_page_options, WebDriver driver){
		return driver.findElement(By.xpath("//label[@btn-radio='"+count_of_records_per_page_options+"']"));
	}
	
	public WebElement getBoundaryPaginationElement(String boundary_links_options, WebDriver driver){	
		return driver.findElement(By.xpath("//a[@ng-click='selectPage("+boundary_links_options+", $event)']"));
	}
	
	public WebElement getDirectionPaginationElement(String direction_links_options, WebDriver driver){
		
		return driver.findElement(By.xpath("//a[@ng-click='selectPage("+direction_links_options+", $event)']"));	
	}
	
	public String getFilterName(int row_index, GeneralActions general_actions) throws IOException{	
		String filter_name=null;
		ExcelData excel_data= new ExcelData();
		filter_name= excel_data.Run_Data_Sheet.getRow(row_index).getCell(0).getStringCellValue();
		//System.out.println("Get Filter Name: "+filter_name);
		return filter_name.trim();
	}
	
	public String getFilterValue(int row_index, String filter_type, GeneralActions general_actions) throws IOException{
		String filter_value=null;
		ExcelData excel_data= new ExcelData();
		switch(filter_type){
		case "text":
			filter_value= excel_data.Run_Data_Sheet.getRow(row_index).getCell(1).getStringCellValue();
		break;
		case "drop down":
			filter_value= excel_data.Run_Data_Sheet.getRow(row_index).getCell(1).getStringCellValue();
		break;
		case "numeric":
			filter_value= Double.toString(excel_data.Run_Data_Sheet.getRow(row_index).getCell(1).getNumericCellValue());
		break;
		case "date":
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			filter_value= df.format(excel_data.Run_Data_Sheet.getRow(row_index).getCell(1).getDateCellValue());
		break;
		default:
			System.out.println("Invalid input type!");
		}
		//System.out.println("getFilterValue:"+filter_value);
		return filter_value.trim();
	}
	
	public String getFilterType(int row_index, GeneralActions general_actions) throws IOException{
		String filter_type=null;
		ExcelData excel_data= new ExcelData();
		filter_type=excel_data.Run_Data_Sheet.getRow(row_index).getCell(2).getStringCellValue();
		//System.out.println("Get Filter Type: "+filter_type);
		return filter_type.trim();
	}
	
	public int getLastRowIndex(GeneralActions general_actions) throws IOException{			
		//System.out.println("getLastRowNum: "+ExcelWSheet.getLastRowNum());
		ExcelData excel_data= new ExcelData();
		return excel_data.Run_Data_Sheet.getLastRowNum();
	}
	
	public String getColumnNameToMatch(int row_index, GeneralActions general_actions)throws IOException{
		String column_name= null;
		System.out.println("Row Index:"+row_index);
		ExcelData excel_data= new ExcelData();		
		column_name= excel_data.Columns_To_Match_Sheet.getRow(row_index).getCell(0).getStringCellValue();
		System.out.println("Column Name:"+column_name);
		return column_name;
	}
	
	public String getCalculatedExtrapolatedValue(String before_extrapolation_value, GeneralActions general_actions, List<WebElement> filtered_record_cells, String extrpolation_divisor_column, List<WebElement> table_headers) throws NumberFormatException, IOException{
		Double divisor=-1.0, multiplier=-1.0, result=-1.0;
		try{
		divisor= Double.parseDouble(getCellValue(extrpolation_divisor_column, filtered_record_cells, table_headers ));
		System.out.println("Divisor:"+divisor);
		multiplier= Double.parseDouble(before_extrapolation_value);
		System.out.println("Multiplier:"+multiplier);
		result= (100.0/divisor)*multiplier;
		}	
		catch(NullPointerException e){
			e.getMessage();
			e.printStackTrace();
			System.out.println("Blank Values");
			return null;
		}
		return Integer.toString(result.intValue());
		}
	
	public WebElement getRunOverviewWebElementInColumn1(WebDriver driver, String element_name){
		WebElement run_overview_element_in_column_1= null;
		run_overview_element_in_column_1= driver.findElement(By.xpath("//div[@class='col-xs-2']/p[text()='"+element_name+":']"));
		return run_overview_element_in_column_1;
	}
	
	public WebElement getRunOverviewWebElementInColumn2(WebDriver driver, String element_name){
		WebElement run_overview_element_in_column_2= null;
		run_overview_element_in_column_2=driver.findElement(By.xpath("//div[@class='col-xs-offset-2 col-xs-2']/p[text()='"+element_name+":']"));
		return run_overview_element_in_column_2;		
	}
	
	public String getRunOverviewColumnValue(WebDriver driver, String element_name, int column_position){
		WebElement run_overview_column_value= null;
		switch(column_position){
		case 1:
			run_overview_column_value= driver.findElement(By.xpath("//div[@class='col-xs-2']/p[text()='"+element_name+":']//following::div[1]/p"));
			break;
		case 2:
			run_overview_column_value= driver.findElement(By.xpath("//div[@class='col-xs-offset-2 col-xs-2']/p[text()='"+element_name+":']//following::div[1]"));
			break;
		}
		return run_overview_column_value.getText().trim();		
	}

	public WebElement getRunOverviewWebElementInColumn2Value(WebDriver driver, String element_name){
		WebElement run_overview_element_in_column_2= null;
		run_overview_element_in_column_2=driver.findElement(By.xpath("//div[@class='col-xs-offset-2 col-xs-2']/p[text()='"+element_name+":']"));
		return run_overview_element_in_column_2;		
	}
	
	public int getCellIndex(List<WebElement> table_headers, String column_name){
		int cell_index=-1;
		for(int i=0; i<table_headers.size();i++){
			if(table_headers.get(i).getText().contains(column_name)){
				cell_index=i;
			}
		}
		cell_index=cell_index+1;
		return cell_index;
	}
	public void enableMultipleColumns(List<WebElement> columnList, WebDriver driver) throws InterruptedException {
		for (int i = 0; i < columnList.size(); i++) {
			Thread.sleep(5000);
			Actions a = new Actions(driver);
			a.dragAndDrop(getAvailableColumnToBeSelected(columnList.get(i).getText(), driver), to).build().perform();
		}
	}
	
public boolean tableColumnDetailAndListViewValueComparison(String column_name, WebDriver driver) throws InterruptedException, IOException{
		//run_tab.click();
		//test.log(LogStatus.INFO, "User switched to List view successfully");		
		if (!checkAColumnSelectionStatus(column_name, driver)){
			enableAColumn(column_name, Login.driver);
		}
		switch_view_button.click();
		System.out.println(column_name);
		String column_value_from_list_view= getCellValue(column_name, filtered_record_first_row_cells, table_headers);
		System.out.println("Expected:"+column_value_from_list_view);
		//test.log(LogStatus.INFO, "Column value on List View: "+column_value_from_list_view);
		//test.log(LogStatus.INFO, "User switched to Detail view successfully");
		if (!checkAColumnSelectionStatus(column_name, driver)){
			enableAColumn(column_name, driver);
		}
		String column_value_from_detail_view= getCellValueDetailView(column_name, filtered_record_first_row_cells_detail_view, table_headers_detail_view);
		System.out.println("Actual:"+column_value_from_detail_view);
		//test.log(LogStatus.INFO, "Column value on Detail View: "+column_value_from_detail_view);	
		return column_value_from_list_view.contentEquals(column_value_from_detail_view);
	}

public boolean overviewSectionColumnAndTableColumnValueComparison(String column_name, WebDriver driver, int row_index) throws InterruptedException, IOException{
	String column_value_on_list_view=null;
	String run_overview_column_value_on_detail_view=null;
	ExcelData excel_data= new ExcelData(); 
	if(!column_name.equals("NA")){
		switch_view_button.click();
	//test.log(LogStatus.INFO, "User switched to List view successfully");
	if(extrapolation_checkbox.isSelected()){
		extrapolation_checkbox.click();
	}	
	enableAColumn(column_name, Login.driver);
	System.out.println("Column name:"+column_name);
	column_value_on_list_view= getCellValue(column_name, filtered_record_first_row_cells, table_headers);
	System.out.println("Expected:"+column_value_on_list_view);
	//test.log(LogStatus.INFO, "Value for Column: " + column_name + " On List view is: "+ column_value_on_list_view);
	Actions action = new Actions(driver);
	action.doubleClick(filtered_record_first_row_cells.get(0)).perform();
	//test.log(LogStatus.INFO, "User switched to Detail view successfully");
	if(extrapolation_checkbox.isSelected()){
		extrapolation_checkbox.click();
	}
	run_overview_column_value_on_detail_view= getRunOverviewColumnValue(Login.driver, excel_data.Run_Overview_Sheet.getRow(row_index).getCell(0).getStringCellValue().trim(), (int) excel_data.Run_Overview_Sheet.getRow(row_index).getCell(1).getNumericCellValue());
	System.out.println("Actual:"+run_overview_column_value_on_detail_view);
	}
	return column_value_on_list_view.contentEquals(run_overview_column_value_on_detail_view);
	//test.log(LogStatus.INFO,"Column value in Run Overview on Detail view is: " + run_overview_column_value_on_detail_view);
}

public WebElement getRunOverviewWebElement(WebDriver driver, String element_name, int column_position){
	WebElement run_overview_element= null;
	switch(column_position){
	case 1:run_overview_element= driver.findElement(By.xpath("//div[@class='col-xs-2']/p[text()='"+element_name+":']"));
	break;
	case 2: run_overview_element= driver.findElement(By.xpath("//div[@class='col-xs-2']/p[text()='"+element_name+":']"));
	break;
	}
	return run_overview_element;	
}

public String getRunNameWithParticularRunStatus(String run_status) throws InterruptedException, IOException{
	run_tab.click();
	clearFiltersSet(Login.driver);//To clear the filter
	setAFilter(run_status, "drop down", "Run Status", Login.driver);
	String run_name= getCellValue("Run Name", filtered_record_first_row_cells, table_headers);
	return run_name;
}

public String getRunStatusLinkURL( String run_name, WebDriver driver) throws InterruptedException{
	run_tab.click();
	clearFiltersSet(Login.driver);//To clear the filter
	setAFilter(run_name, "text", "Run Name", Login.driver);
	Actions action= new Actions(Login.driver);
	action.doubleClick(filtered_record_first_row_cells.get(0)).perform();	//Switch to Detail View
	String run_status_link_url= driver.findElement(By.xpath("//table[@id='tbl-runs-analysis']//tr[1]/td["+getCellIndex(table_headers_detail_view, "Run Status")+"]/a")).getAttribute("href");
	return run_status_link_url;
}

public int getPFolderNumberColumnValueDetailViewTable(int record_index, String column_name){
	int column_position = getCellIndex(table_headers_detail_view,column_name);
	System.out.println(column_position);
	System.out.println(record_index);
	int p_folder_numbers = Integer.parseInt(Login.driver.findElement(By.xpath("//table[@id='tbl-runs-analysis']//tbody/tr["+record_index+"]/td["+column_position+"]/span")).getText());
		return p_folder_numbers;
	}
}