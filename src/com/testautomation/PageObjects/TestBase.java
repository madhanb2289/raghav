package com.testautomation.PageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ISuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.testautomation.PageObjects.Reporter;
import com.testautomation.Utility.PropertiesFileReader;


public class TestBase {
	//public static RemoteWebDriver driver;
	protected String usedDeviceName;
	protected String testname;
	protected static Map<String, String> data = null;
	protected Throwable exception = null;
	protected static DesiredCapabilities dc;
	protected int const_waitlow = 20;
	protected int const_waitmed = 40;
	protected int const_waithigh = 60;
	public ExtentReports extent;
	public String testCaseName ;	
	public static ExtentTest logger ;
	public static WebDriver driver ;
	
	PropertiesFileReader obj = new PropertiesFileReader();
	//private static String homeWindow = null;

	public static WebDriver driverInit() {
		try {
			
			PropertiesFileReader obj = new PropertiesFileReader();
			Properties properties=obj.getProperty();
			
			System.setProperty("webdriver.chrome.driver", "C://Driver//chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(properties.getProperty("browser.baseURL"));
		
		}catch (Exception e) {
			
		}
		return driver;
	}
	
	//public void LaunchBrowser(Map<String, String> data) throws MalformedURLException, InterruptedException {
	/*	public void LaunchBrowser (Map<String, String> data) throws MalformedURLException, InterruptedException { 
		try {
			PropertiesFileReader obj = new PropertiesFileReader();
			Properties properties=obj.getProperty();
			
			System.setProperty("webdriver.chrome.driver", "C://Driver//chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(properties.getProperty("browser.baseURL"));
		
		}catch (Exception e) {
			
		}
	} */


	@BeforeClass
	public void setUp() {

		System.out.println(getClass().getName().substring(14));
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
		String strDate = formatter.format(date);
		extent = new ExtentReports("./reports/report" + strDate + ".html", false);
		

	}

	@BeforeMethod
	public void startReport(Method method){

		try {
			Properties properties=obj.getProperty();
			testname = method.getName();
			getTestData();
			if(data.get("Execute").equalsIgnoreCase("yes")){
				driverInit();	
			//	LaunchBrowser(data);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			//driver.navigate().to(getProperty("URL"));
			
			driver.get(properties.getProperty("browser.baseURL"));

			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

			extent.addSystemInfo("Environment",getProperty("browser.baseURL"));

			logger = extent.startTest(testname);
			
			// Assert.assertTrue(true);
			}
	}catch (Exception e) {
			System.out.println(e.getMessage());
	}
		

	}

	@AfterMethod
	public void tearDown(Method method) {
		try {
	if(data.get("Execute").equalsIgnoreCase("yes")){
	      Thread.sleep(1000);
		driver.close();	
		extent.endTest(logger);
		extent.flush();
			}} catch (WebDriverException e) {
			
		} catch (Exception e) {
				
			}
	}

	@AfterSuite
	public void tearDown2() {

	}

	public long takeSnap() {
		/*Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyyhh:mm:ss");*/
		//String strDate = formatter.format(date);

		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File("./reports/images/" + number + ".jpg"));
		} catch (WebDriverException e) {

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return number;
	}






private void getTestData() throws IOException {
	int z;
	data = new HashMap<String, String>();
	Row row;
	FileInputStream fileInputStream = new FileInputStream(getProperty("data.spreadsheet.name"));
	HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
	Sheet dataSheet = workbook.getSheet(getTestName());

	for (z=1;z<dataSheet.getLastRowNum();z++) {
		row = dataSheet.getRow(z);
		if (row.getCell(0).getStringCellValue().equalsIgnoreCase(testname)) {
			break;
		}
	}

	for (int i = 0; i < dataSheet.getRow(0).getLastCellNum(); i++) {
		row = dataSheet.getRow(0);
		String key = row.getCell(i).getStringCellValue();
		System.out.println(key);
		row = dataSheet.getRow(z);
		String value = row.getCell(i).getStringCellValue();
		System.out.println(value);
		data.put(key, value);
	}
	workbook.close();
}







 public static String getProperty(String property) {
	if (System.getProperty(property) != null) {
		return System.getProperty(property);
	}
	File setupPropFile = new File("resources/browser-config.properties");
	if (setupPropFile.exists()) {
		Properties prop = new Properties();
		FileReader reader;
		try {
			reader = new FileReader(setupPropFile);
			prop.load(reader);
			reader.close();
			return prop.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return null;
}  

public String getTestName() {
	String[] klassNameSplit = this.getClass().getName().split("\\.");
	return klassNameSplit[klassNameSplit.length - 1];
}

public void onFinish(ISuite suite) {
	System.out.println("Finishing");
}

public void onStart(ISuite suite) {
	System.out.println("Starting");
}


public static void navigateToUrl(String url) {
	try {
		driver.navigate().to(url);
		//Reporter.addStepLogPass("Application launched successfully to" + url);
		logger.log(LogStatus.PASS, " Button clicked Successfully");
	} catch (Exception e) {
		//Reporter.addStepLogInfo("Failed to load the url" + url + e.getMessage());
		//logger.log(LogStatus.FAIL, driver.findElement(By.xpath(Objname)).getText() + " Button is not clicked ");
	}
}

/*public static void closeBrowser() {
	try {
		driver.close();
		Reporter.addStepLogPass("Browser closed successfully");
	} catch (Exception e) {
		Reporter.addStepLogInfo("Browser is not closed");
	} */
}