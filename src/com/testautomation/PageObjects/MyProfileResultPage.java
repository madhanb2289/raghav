package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyProfileResultPage {

	WebDriver driver;

	public MyProfileResultPage(WebDriver driver)

	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.LINK_TEXT, using = "How to write Automated test in Selenium Webdriver")
	public WebElement SearchTextBox;

	public void NavigateToLinkName() throws InterruptedException {
		Thread.sleep(1000);
		SearchTextBox.click();

	}
}
