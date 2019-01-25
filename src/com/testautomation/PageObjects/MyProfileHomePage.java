package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyProfileHomePage {

	WebDriver driver;

	public MyProfileHomePage(WebDriver driver)

	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.LINK_TEXT, using = "abc")
	public WebElement SearchTextBox;

	public String getTitle() throws InterruptedException {

		Thread.sleep(1000);
		return driver.getTitle();
	}
}