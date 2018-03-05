package modules;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class GeneralActions {
	
	@FindBy(xpath="//a[@href='/logout']")	//Logout option
	public WebElement logout_option;
	
	@FindBy(xpath = "//div[@class='navbar-header']/a")
	public WebElement menu_bar_title;	//Menu Bar Elements
	
	@FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right']/li/a[@class='pull-right capitalize help-link reset-expire-cookie']")
	public WebElement menu_bar_help_link;
	
	@FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right']//img[@src='/img/roche-logo.png']")
	public WebElement menu_bar_logo;
	
	@FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right']/li[2]/a")
	public WebElement menu_bar_profile_name;
	
	@FindBy(xpath = "//a[text()='Login']") //Login button
	public WebElement log_in_button;
	
	@FindBy(xpath = "//input[@id='username']")	//User ID text box
	public WebElement username_textbox;
	
    //@FindBy(id= "next")	//User ID text box
	//public WebElement next_button;
	
	@FindBy(xpath = "//input[@id='password']")	//Password text box
	public WebElement password_textbox;
	
	@FindBy(xpath = "//button[text()='Sign in']")	//Sign In button
	public WebElement sign_in_button;
	
	
	
	//To get the profile name drop down web element
	public WebElement getProfileNameDropdown(String profile_name, WebDriver driver) throws IOException{
		
		return driver.findElement(By.xpath("//a[contains(text(), '"+profile_name+" ')]"));
	}
		
	public void dragDrop(WebElement element_to_be_dragged, WebElement dropping_location, WebDriver driver){	
	
		Actions a = new Actions(driver);
		a.dragAndDrop(element_to_be_dragged, dropping_location).build().perform();
	
	}
}