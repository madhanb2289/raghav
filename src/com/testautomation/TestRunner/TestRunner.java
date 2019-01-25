package com.testautomation.TestRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
			features = {"./features"},
			glue = {"com.testautomation.stepDef"},
			tags ={"@CucumberTesting"},plugin ={"pretty",
			       "html:target/site/cucumber-pretty",
			  "json:target/cucumber.json"},
			monochrome = true)
public class TestRunner {
			private TestNGCucumberRunner testNGCucumberRunner;
			
			
	@BeforeClass(alwaysRun = true)
		public void setupClass() throws Exception {
				testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
				
		}
    @Test(dataProvider = "features")
        public void feature(CucumberFeatureWrapper cucumberFeature){
	           testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        }

    @DataProvider
        public Object[][] features(){
              return testNGCucumberRunner.provideFeatures();
	}
	@AfterClass(alwaysRun = true)
	   public void tearDownclass() throws Exception {
		      testNGCucumberRunner.finish();
	}
	}
	