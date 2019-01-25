package com.testautomation.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyProfileSearchPage {

	WebDriver driver;

	public MyProfileSearchPage(WebDriver driver)

	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "search")
	public WebElement SearchTextBox;

	@FindBy(how = How.ID, using = "search-icon-legacy")
	public WebElement SearchButton;

	public void NavigateToResultPage() {

		SearchTextBox.sendKeys("selenium automation");
		SearchButton.click();

	}

}
