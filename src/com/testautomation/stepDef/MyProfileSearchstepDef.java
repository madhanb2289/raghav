package com.testautomation.stepDef;



import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.testautomation.PageObjects.TestBase;
import com.testautomation.Utility.PropertiesFileReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyProfileSearchstepDef extends TestBase {
	public static WebDriver driver ;
	PropertiesFileReader obj = new PropertiesFileReader();
	
	@Given("^open chrome browser and enter url$")
	public void open_chrome_browser_and_enter_url() throws Throwable {
		Properties properties=obj.getProperty();
		
		System.setProperty("webdriver.chrome.driver", "C://Driver//chromedriver.exe");
		driver = new ChromeDriver();  
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(properties.getProperty("browser.baseURL"));
		Thread.sleep(4000);
		//driver.manage().deleteAllCookies();
		//return driver;
	} 

	@When("^Enter search criteria$")
	public void enter_search_criteria() throws Throwable {
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id ='login_username' and @required='required']")).sendKeys("devadm");
		driver.findElement(By.xpath("//input[@id='login_password' and @required='required']")).sendKeys("Champ@12345");
		Thread.sleep(1000);
	}
	
	@Then("^click on search button$")
	public void click_on_search_button() throws Throwable {
		driver.findElement(By.xpath("//button[@id='login_signIn']")).click();
		Thread.sleep(1000);
		driver.quit();
}
		
}
