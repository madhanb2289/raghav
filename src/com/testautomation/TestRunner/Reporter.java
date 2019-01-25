package com.testautomation.TestRunner;

import java.io.File;

import com.testautomation.PageObjects.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract  class Reporter {
		public ExtentTest test;
		public static ExtentReports extent;
		public String testCaseName ;
		
		

		public void reportStep(String desc, String status) {
			reportStep(desc, status, true);
		}
		
		
		public void reportStep(String desc, String status, boolean bSnap) {
			
			TestBase tb = new TestBase();

			if(bSnap && !status.equalsIgnoreCase("INFO")){
				long snapNumber = 100000l;
				
				try {
					snapNumber= tb.takeSnap();
				} catch (Exception e) {
					e.printStackTrace();
				}
				desc = desc+test.addScreenCapture("./../reports/images/"+snapNumber+".jpg");
			}
			
			// Write if it is successful or failure or information
			if(status.equalsIgnoreCase("PASS")){
				test.log(LogStatus.PASS, desc);
			}else if(status.equalsIgnoreCase("FAIL")){
				test.log(LogStatus.FAIL, desc);
				throw new RuntimeException("FAILED");
			}else if(status.equalsIgnoreCase("WARN")){
				test.log(LogStatus.WARNING, desc);
			}else if(status.equalsIgnoreCase("INFO")){
				test.log(LogStatus.INFO, desc);
			}
		
		}

}
