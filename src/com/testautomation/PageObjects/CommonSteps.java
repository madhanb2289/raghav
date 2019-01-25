package com.testautomation.PageObjects;

import java.util.Properties;

import com.testautomation.Utility.PropertiesFileReader;
import com.testautomation.stepDef.MyProfileSearchstepDef;

import cucumber.api.java.en.Given;

public class CommonSteps extends TestBase{
	PropertiesFileReader obj = new PropertiesFileReader();
	
	MyProfileSearchstepDef fs = new MyProfileSearchstepDef();

		@Given("^open chrome browser and enter url$")
		public void open_chrome_browser_and_enter_url() throws Throwable {
			Properties properties=obj.getProperty();
			driver.get(properties.getProperty("browser.baseURL"));
		}

	}


