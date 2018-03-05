package modules;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import modules.ExcelData;

public class Login {
	
	public static WebDriver driver;
				
	public WebDriver launchBrowser() throws IOException, InterruptedException{
		
		try{
			
			ExcelData excel_data= new ExcelData();
						
//			System.out.println("Property:"+excel_data.getChromeDriverProperty());
//			System.setProperty("webdriver.chrome.driver",excel_data.getChromeDriverProperty());
			
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--incognito");
			
			driver = new ChromeDriver(options);
			
			/*
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setBrowserName("chrome");
			driver = new RemoteWebDriver(new URL("http://10.21.51.15:4444/wd/hub"), capabilities);
			*/
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			//driver.manage().window().maximize();
			
			driver.get(excel_data.getApplicationURL());
			
			//run.run_tab.click();
		}
		
		catch(Exception e){
			
			e.printStackTrace();
		}
		return driver;
	}

	public void signIn(WebDriver driver) throws IOException, InterruptedException{
			
		ExcelData excel_data= new ExcelData();
		GeneralActions general_actions = PageFactory.initElements(Login.driver, GeneralActions.class);
		
		general_actions.log_in_button.click();
//		Thread.sleep(3000);
		general_actions.username_textbox.sendKeys(excel_data.getUsername());
		String decryptedPassword;
		byte[] decrypted = Base64.getDecoder().decode(excel_data.getPassword());
		decryptedPassword = new String(decrypted);
		//general_actions.next_button.click();
		general_actions.password_textbox.sendKeys(decryptedPassword);

		general_actions.sign_in_button.click();
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
	
	public void logoutApplication(WebDriver driver) throws IOException{
		
		GeneralActions general_actions = PageFactory.initElements(Login.driver, GeneralActions.class);
		ExcelData excel_data= new ExcelData();
		
		try{
		general_actions.getProfileNameDropdown(excel_data.getProfileName(), driver).click();
		general_actions.logout_option.click();
		driver.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	}