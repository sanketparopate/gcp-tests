package exportHeaderCheck;

import java.io.File;



import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;
import modules.Login;
import modules.Run;
import testcases.LogInApplication;

public class ExcelRead {

	FileInputStream excelFile;
	XSSFWorkbook excelWBook;
	XSSFSheet excelWSheet;
	int rowCount;
	String[] li;
	int len;
	
	Login login = new Login();
	Run run = new Run();

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		
		LogInApplication log_in_application = new LogInApplication();
		log_in_application.launchAndLogin();
		Login.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		run.setAFilter("Aborted", "drop down", "Run Status", Login.driver);
		run.export_results_button.click();

	}

	@Test(priority = 1)
	public void fileSetup() throws IOException, InterruptedException {
		excelFile = new FileInputStream("Support_Files/Automation_Data.xlsx");
		excelWBook = new XSSFWorkbook(excelFile);
		excelWSheet = excelWBook.getSheet("Staging_Exports");
		rowCount = excelWSheet.getLastRowNum() + 1;
		File folder = new File("Support_Files/");
		File[] listofFiles = folder.listFiles();

		for (int i = 0; i < listofFiles.length; i++) {
			String fileName = listofFiles[i].getName();
			if (fileName.startsWith("Exported")) {

				@SuppressWarnings("resource")
				CSVReader reader = new CSVReader(new FileReader("Support_Files/" + fileName));
				li = reader.readNext();
				len = li.length;
			}
		}

	}

	@Test(priority = 2)
	public void compare() {

		if (rowCount == len) {
			for (int i = 0; i < rowCount; i++) {
				System.out.println(excelWSheet.getRow(i).getCell(0).getStringCellValue() + "  MATCHING  " + li[i]);
				Assert.assertEquals(li[i], excelWSheet.getRow(i).getCell(0).getStringCellValue());

			}
		}

		else {
			System.out.println("Count not matching  " + len + "  " + rowCount);
		}
	}
}
