package com.testautomation.PageObjects;

import cucumber.api.java.Before;

public class BaseClass {
	
	
	@Before
	public static void beforeScenario() {
		TestBase.driverInit();
	}

}
