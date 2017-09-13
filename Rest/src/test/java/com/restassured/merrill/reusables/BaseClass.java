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
import com.restassured.merrill.reusables.Convert;

public class BaseClass {

	public static RequestSpecification request;
	public static Response response;
	public static ValidatableResponse validateResponse;
	
	public static ExtentTest logger;

	public static Properties config;
	public static Properties data;
	public static File f;
	public static ExtentReports report;
	public static WebDriver driver;
	public static String reportPath;
	public static String projectPath;
	public static String filename;
	public static FileInputStream fis;
	public static FileOutputStream fileOutPutStream;
	public static PrintStream printStream;
	public static String logPath;

	static {

		try {

			projectPath = System.getProperty("user.dir");
			logPath = projectPath + "\\log\\application.log";

			f = new File(logPath);
			fileOutPutStream = new FileOutputStream(f);
			printStream = new PrintStream(fileOutPutStream);

			RestAssured.config = RestAssured.config().logConfig(new LogConfig().defaultStream(printStream));
			
			filename = new SimpleDateFormat("ddMMyyyy-hhmmss").format(new Date());

			reportPath = projectPath + "\\Output\\ExtentReports\\MerrillTesting" + filename + ".html";

			// System.setProperty("webdriver.gecko.driver",projectPath+"\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\chromedriver.exe");
			report = new ExtentReports(reportPath, true);
			
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

	public static String getToken() {

		String res = given().header("Content-Type", "application/json")
				.body("{" + "\"password\": \"Password1!\"," + "\"username\": \"dummyone@merrillcorp.com\"" + "}").when()
				.post("http://token-service.apps.us2.dev.foundry.mrll.com/api/tokens/internaluser/javelinJwt").then()
				.extract().response().asString();

		JsonPath jp = Convert.rawToJson(res);
		return jp.getString("jwt");

	}

	public static Map<String, String> setHeaders() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Accept", "application/json");
		map.put("Authorization", "Bearer " + getToken());

		return map;
	}
	
	
	public static void printRequestLogs(String testName){
		
		logger = report.startTest("TESTING "+testName+" API");
		logger.log(LogStatus.INFO, "SETTING HEADERS AND PATH PARAMETERS");
		
		printStream.println();		
		printStream.println("####################### Printing Request Logs for "+testName+" #######################");		
		printStream.println();
		
		logger.log(LogStatus.INFO, "CHECK LOG FOLDER "+logPath+" FOR REQUEST LOGS");		
		
		
	}
	
	public static void printResponseLogs(String testName){
		
		printStream.println();		
		printStream.println("####################### Printing Response Logs for "+testName+" #######################");		
		printStream.println();
		
		logger.log(LogStatus.INFO, "CHECK LOG FOLDER "+logPath+" FOR RESPONSE LOGS");		
		logger.log(LogStatus.PASS, testName+" IS UP AND RUNNING");		
		logger.log(LogStatus.INFO, "END TEST");
		
	}
}
