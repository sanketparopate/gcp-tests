package modules;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	public FileInputStream ExcelFile1, ExcelFile2;
	public XSSFWorkbook ExcelWBook, ExcelWBook1, ExcelWBook2;
	public XSSFSheet Run_Data_Sheet;
	public XSSFSheet Configuration_Data_Sheet;
	public XSSFSheet Columns_To_Match_Sheet;
	public XSSFSheet Run_Overview_Sheet;
	public XSSFSheet Run_Data_Sheet_2;
	public XSSFSheet Run_Data_Sheet_3;
	public XSSFSheet Run_Data_Sheet_4;
	public XSSFSheet Classes_Sheet;
	public XSSFSheet Methods_Sheet;
	public XSSFSheet Run_Data_Sheet_5;
	public ExcelData() throws IOException{
		
		ExcelFile1= new FileInputStream("Support_Files/Automation_Data.xlsx");
		ExcelWBook1= new XSSFWorkbook(ExcelFile1);
		Run_Data_Sheet = ExcelWBook1.getSheet("Run_Filters");
		Configuration_Data_Sheet = ExcelWBook1.getSheet("Configuration_Data");
		Columns_To_Match_Sheet = ExcelWBook1.getSheet("Columns_To_Match");
		Run_Overview_Sheet = ExcelWBook1.getSheet("Run_Overview");
		Run_Data_Sheet_2 = ExcelWBook1.getSheet("Run_Data");
		Run_Data_Sheet_3 = ExcelWBook1.getSheet("Run_Columns");
		Run_Data_Sheet_4 = ExcelWBook1.getSheet("Run_Status_Links");
		Run_Data_Sheet_5 = ExcelWBook1.getSheet("Available_Columns");
		
		ExcelFile2= new FileInputStream("Support_Files/TestNG_XML.xlsx");
		ExcelWBook2= new XSSFWorkbook(ExcelFile2);
		Classes_Sheet = ExcelWBook2.getSheet("Class(es)");
		}
	
	public String getUsername() throws IOException{
		
		String username=null;
		username= Configuration_Data_Sheet.getRow(2).getCell(1).getStringCellValue();
		
		return username.trim();
	}
	
	public String getPassword() throws IOException{
		
		String password=null;
		password= Configuration_Data_Sheet.getRow(3).getCell(1).getStringCellValue();
		
		return password.trim();
	}
	
	public String getDbUsername() throws IOException{
		
		String dbUsername=null;
		
		dbUsername= Configuration_Data_Sheet.getRow(5).getCell(1).getStringCellValue();
		
		return dbUsername.trim();
	}
	
	public String getDbPassword() throws IOException{
		
		String dbPassword=null;
		
		dbPassword= Configuration_Data_Sheet.getRow(6).getCell(1).getStringCellValue();
		
		return dbPassword.trim();
	}
	
	//To get the profile name of the user from excel
		public String getProfileName() throws IOException{
			
			String profile_name=null;
			profile_name=Configuration_Data_Sheet.getRow(4).getCell(1).getStringCellValue();
						
			return profile_name;
		
		}
		
		//To get the chrome driver property of the user from excel
		public String getChromeDriverProperty() throws IOException{
			
			String property=null;
			property=Configuration_Data_Sheet.getRow(0).getCell(1).getStringCellValue();
						
			return property;
		
		}
		
		//To get the application URL of the user from excel
		public String getApplicationURL() throws IOException{
			
				String application_url=null;
				application_url=Configuration_Data_Sheet.getRow(1).getCell(1).getStringCellValue();
							

				return application_url;

			}

}
