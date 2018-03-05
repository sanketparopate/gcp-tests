package modules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import testcases.LaunchScript;

public class GeneralFunctions {
	
	LaunchScript launch_script= new LaunchScript();
	List<WebElement> columns;
	List<WebElement> availableColumns;
	Run run= new Run();
	
	public String getFolderPath(int main_folder_row,int build_folder_row) throws IOException{
		
		File build_file_folder;
		ExcelData excel_data= new ExcelData();
		
		DateFormat date_format= new SimpleDateFormat("dd-MM-yyyy"); 
		Date date = new Date();
		
		
		String main_folder_name = excel_data.Configuration_Data_Sheet.getRow(main_folder_row).getCell(1).getStringCellValue();
		String daily_folder_name = date_format.format(date);
		
		//System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
		
		File daily_file_folder = new File(System.getProperty("user.dir")+"/"+main_folder_name+"/"+daily_folder_name);
		daily_file_folder.mkdirs();
		
		date_format= new SimpleDateFormat("hh_mm_ss");
		String build_folder_name = excel_data.Configuration_Data_Sheet.getRow(build_folder_row).getCell(1).getStringCellValue()+"-"+ date_format.format(date);
		
		
		build_file_folder= new File(System.getProperty("user.dir")+"/"+main_folder_name+"/"+daily_folder_name+"/"+build_folder_name);
		build_file_folder.mkdirs();
		
		return build_file_folder.getPath();
	}
	
	
	public void createTestNGXML() throws ParserConfigurationException, IOException, TransformerException{
		

		ExcelData excel_data= new ExcelData();
		
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		        
		Document document = documentBuilder.newDocument();
		DOMImplementation domImpl = document.getImplementation();
		DocumentType doctype = domImpl.createDocumentType("docType","", "http://testng.org/testng-1.0.dtd");
		
        Element suite_element = document.createElement("suite");
        suite_element.setAttribute("name", "Cumulus Suite");
        document.appendChild(suite_element);
        
        Element test_element = document.createElement("test");
        test_element.setAttribute("name", "Cumulus Test");
        suite_element.appendChild(test_element);
        
        Element classes_element = document.createElement("classes");
        test_element.appendChild(classes_element);
        
        Element class_element = document.createElement("class");
        class_element.setAttribute("name", excel_data.Classes_Sheet.getRow(2).getCell(0).getStringCellValue().trim());
        classes_element.appendChild(class_element);
        
        for (int row_index1=3; row_index1 <= excel_data.Classes_Sheet.getLastRowNum(); row_index1++){
        	
        	if(excel_data.Classes_Sheet.getRow(row_index1).getCell(1).getStringCellValue().equals("Y")){
        		
        		class_element = document.createElement("class");
                class_element.setAttribute("name", excel_data.Classes_Sheet.getRow(row_index1).getCell(0).getStringCellValue().trim());
                classes_element.appendChild(class_element);
                
                Element methods_element = document.createElement("methods");
                class_element.appendChild(methods_element);
                
                
                excel_data.Methods_Sheet= excel_data.ExcelWBook2.getSheet(excel_data.Classes_Sheet.getRow(row_index1).getCell(0).getStringCellValue().trim());
                
                for(int row_index2=2; row_index2 <= excel_data.Methods_Sheet.getLastRowNum(); row_index2++){
                	
                	if(excel_data.Methods_Sheet.getRow(row_index2).getCell(1).getStringCellValue().equals("Y")){
                		
                		Element include_element = document.createElement("include");
                		include_element.setAttribute("name", excel_data.Methods_Sheet.getRow(row_index2).getCell(0).getStringCellValue().trim());
                        methods_element.appendChild(include_element);
                	}
                	
                }
        	}
        }
        
        TransformerFactory transformer_Factory = TransformerFactory.newInstance();
        Transformer aTransformer = transformer_Factory.newTransformer();
        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
        aTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(LaunchScript.folder_path+"/"+excel_data.Configuration_Data_Sheet.getRow(9).getCell(1).getStringCellValue()));
        aTransformer.transform(source, result);
	}
	
	public void captureScreenshot(String screenshot_name, WebDriver driver) throws IOException{
		
		try{
		PageSnapshot page_snapshot=Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS);
		File testcase_file_folder = new File(LaunchScript.folder_path+"/Testcase(s) Screenshot(s)");
		testcase_file_folder.mkdir();
		
		File testcase_screenshot_file = new File(testcase_file_folder.getPath()+"/"+screenshot_name+".jpg");
		ImageIO.write(page_snapshot.getImage(), "jpg", testcase_screenshot_file);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
	}
	
	public void isLinkBroken(List<WebElement> linksOnthePage, XSSFWorkbook wb, String sheetName, WebDriver driver) throws Exception {

		XSSFSheet s1 = wb.getSheet(sheetName);

		FileOutputStream file_writing = new FileOutputStream(new File("./Support_Files/GCP_Cumulus_Alpha.xlsx"));

		System.out.println("Total number of links found on the page " + linksOnthePage.size());

		for (int i = 0; i < linksOnthePage.size(); i++) {
			try {

				URL url = new URL(linksOnthePage.get(i).getAttribute("href"));

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				connection.setConnectTimeout(3000);
				connection.connect();

				s1.createRow(i + 1).createCell(0).setCellValue(linksOnthePage.get(i).getAttribute("href"));
				s1.getRow(i + 1).createCell(1).setCellValue(connection.getResponseCode() + " " + connection.getResponseMessage());
				
				if(connection.getResponseCode()!=200)
				{
					
					String window=driver.getWindowHandle();
					driver.get(linksOnthePage.get(i).getAttribute("href"));
					
					if(!driver.getTitle().contains("Directory"))
					{
						
						driver.switchTo().window(window);
						Assert.assertTrue(false);
						
					}
				}
				//System.out.println("URL: " + linksOnthePage.get(i).getAttribute("href") + " returned "+ connection.getResponseCode() + " " + connection.getResponseMessage());
				Assert.assertTrue(true);		

			} catch (Exception exp)

			{
				s1.createRow(i+1).createCell(0).setCellValue(linksOnthePage.get(i).getAttribute("href"));
				s1.getRow(i+1).createCell(1).setCellValue(" Exception occured : " + exp.getMessage().toString());
				//System.out.println("At " + linksOnthePage.get(i).getAttribute("href") + " Exception occurred : "+ exp.getMessage());
					Assert.assertTrue(false);	
			}
		}

		wb.write(file_writing);

		file_writing.flush();

		file_writing.close();

	}
	
}
