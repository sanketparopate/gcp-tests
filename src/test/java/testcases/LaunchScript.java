package testcases;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.testng.TestNG;
import modules.ExcelData;
import modules.GeneralFunctions;

public class LaunchScript {	
	
	public static String folder_path=null;
		
	public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException{
								
		GeneralFunctions general_functions= new GeneralFunctions();
		folder_path= general_functions.getFolderPath(7,8);
		
		//System.out.println(folder_path);
		general_functions.createTestNGXML();
						
		TestNG runner=new TestNG();
		
		List<String> suitefiles=new ArrayList<String>();
		
		ExcelData excel_data= new ExcelData();
		
		
		suitefiles.add(folder_path+"/"+excel_data.Configuration_Data_Sheet.getRow(9).getCell(1).getStringCellValue());
		
		runner.setTestSuites(suitefiles);
		runner.run();
	}
}