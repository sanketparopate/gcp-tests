package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import modules.GeneralFunctions;
import modules.Login;

public class LogInApplication {
	
	public static ExtentReports report;
	public static ExtentTest test;
	public static FileInputStream result_file;
	public static GeneralFunctions general_functions;
	public static FileOutputStream file_writing;
	public static List<String> table_headers;
	public static XSSFWorkbook wb;
	public static XSSFSheet s;
	Login login = new Login();
	public static String folder_path=null;

	
	
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void launchAndLogin() throws IOException, InterruptedException{
		
		try{
			
			GeneralFunctions general_functions= new GeneralFunctions();
			folder_path= general_functions.getFolderPath(10,11);
			
			/*Properties obj = new Properties();
			InputStream fin = new FileInputStream(new File("./Support_Files\\CounterInfo"));
			
			obj.load(fin);
			int run_count = Integer.parseInt(obj.getProperty("Counter"));

			run_count++;*/
			
			report = new ExtentReports(folder_path+ "/Report.html", true);
			report.addSystemInfo("Host Name", "Windows").addSystemInfo("Environment", "QA")
					.addSystemInfo("User Name", "Digant Tyagi").config().documentTitle("Automation Report")
					.reportName("Test Case Execution Report");

			/*obj.setProperty("Counter", Integer.toString(run_count));
			OutputStream out = new FileOutputStream(new File("./Support_Files\\CounterInfo"));
			
			obj.store(out, "Updating counter");
			out.flush();
			
			out.close();*/
			
			result_file = new FileInputStream(new File("./Support_Files/GCP_Cumulus_Alpha.xlsx"));
			wb = new XSSFWorkbook(result_file);
		} 
		
		catch (Exception e) {
			
			e.getMessage();
			
		}
		
			login.launchBrowser();
			login.signIn(Login.driver);
			
			Login.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Thread.sleep(3000);
			
			
			//run.run_tab.click();
		}
		
		

	
	@AfterSuite
	public void logout() throws IOException{
		
		report.flush();
		//report.close();
		login.logoutApplication(Login.driver);
		Login.driver.close();
	}
	

}
