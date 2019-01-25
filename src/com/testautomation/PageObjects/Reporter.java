package com.testautomation.PageObjects;


	
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
				desc = desc+test.
						addScreenCapture("./../reports/images/"+snapNumber+".jpg");
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
		
		

		

		public abstract long takeSnap();
		

		public ExtentReports startResult(){
			extent = new ExtentReports("./reports/report.html", false);
			extent = new ExtentReports("");
			extent.loadConfig(new File("./src/main/resources/extent-config.xml"));
			return extent;
		}

		public ExtentTest startTestCase(String testCaseName){
			test = extent.startTest(testCaseName);
			return test;
		}

		public void endResult(){		
			extent.flush();
		}

		public void endTestcase(){
			extent.endTest(test);
		}
		
		public void startTestcase(){
			extent.endTest(test);
		}

		
		
	}



