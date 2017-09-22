package com.restassured.merrill.reusables;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {

	public static RequestSpecification requestSpec;
	public static Response response;
	public static ValidatableResponse validateResponse;
	public WebDriver driver;

	public static Properties config;
	public static Properties data;
	
	public static ExtentTest logger;
	public static ExtentReports report;
	
	public static String extenetReportPath;
	public static String extenReportFileName;
	public static String projectPath;
	public static String applicationLogPath;	
	
	
	public static File f;
	public static FileInputStream fis;
	public static FileOutputStream fileOutPutStream;
	public static PrintStream printStream;
	
	static {

		try {

			projectPath = System.getProperty("user.dir");
			applicationLogPath = projectPath + "\\log\\application.log";

			f = new File(applicationLogPath);
			fileOutPutStream = new FileOutputStream(f);
			printStream = new PrintStream(fileOutPutStream);
			RestAssured.config = RestAssured.config().logConfig(new LogConfig().defaultStream(printStream));
			
			extenReportFileName = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
			//extenetReportPath = projectPath + "\\Output\\ExtentReports\\MerrillTesting" + extenReportFileName + ".html";
			extenetReportPath = projectPath + "\\Output\\ExtentReports\\MerrillTesting.html";

			// System.setProperty("webdriver.gecko.driver",projectPath+"\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\chromedriver.exe");
			report = new ExtentReports(extenetReportPath, false);
			
			LoadDataPropFile();			
			LoadConfigPropFile();

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public static void LoadConfigPropFile() throws IOException {

		try {

			f = new File(projectPath + "\\src\\test\\java\\com\\restassured\\merrill\\config\\config.properties");
			fis = new FileInputStream(f);
			config = new Properties();
			config.load(fis);
			baseURI = config.getProperty("HOST");

		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}
	}

	public static void LoadDataPropFile() throws IOException {

		try {
			f = new File(projectPath + "\\src\\test\\java\\com\\restassured\\merrill\\config\\Data.properties");
			fis = new FileInputStream(f);
			data = new Properties();
			data.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			fis.close();
		}

	}
	

	public String getToken() {

		String res = given().header("Content-Type", "application/json")
				.body("{" + "\"password\": \"Password1!\"," + "\"username\": \"dummyone@merrillcorp.com\"" + "}").when()
				.post("http://token-service.apps.us2.dev.foundry.mrll.com/api/tokens/internaluser/javelinJwt").then()
				.extract().response().asString();

		JsonPath jp = Convert.rawToJson(res);
		return jp.getString("jwt");

	}

	public Map<String, String> setHeaders() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Accept", "application/json");
		map.put("Authorization", "Bearer " +getToken());

		return map;
	}	
	
	public void printRequestLogs(String testName){
		
		logger = report.startTest("TESTING "+testName);
		
		logger.log(LogStatus.INFO, "START TEST");
				
		printStream.println();		
		printStream.println("####################### Printing Request Logs for "+testName+" #######################");		
		printStream.println();
		
		logger.log(LogStatus.INFO, "CHECK LOG FOLDER "+applicationLogPath+" FOR REQUEST LOGS");		
		
		
	}
	
	public void printResponseLogs(String testName){
		
		printStream.println();		
		printStream.println("####################### Printing Response Logs for "+testName+" #######################");		
		printStream.println();
		
		logger.log(LogStatus.INFO, "CHECK LOG FOLDER "+applicationLogPath+" FOR RESPONSE LOGS");		
		logger.log(LogStatus.PASS, testName+" IS UP AND RUNNING");		
		logger.log(LogStatus.INFO, "END TEST");
		/*
		driver = new ChromeDriver();
		try {
			Thread.sleep(8000L);
			driver.get(extenetReportPath);
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}		
		driver.close();
		
		printStream.flush();
		printStream.close();
		*/
		
		report.endTest(logger);
		report.flush();
		
	}
}
