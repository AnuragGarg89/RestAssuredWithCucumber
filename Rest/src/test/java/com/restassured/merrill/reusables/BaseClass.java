package com.restassured.merrill.reusables;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.restassured.merrill.reusables.Convert;

public class BaseClass {
	
	public RequestSpecification request;
	public Response response;
	public static Properties config;
	public static Properties data;
	public static FileInputStream fis;
	public static File f;
	public static ExtentReports report;
	public ExtentTest logger;
	public static WebDriver driver;
	public static String reportPath;
	public static String projectPath;
	public static String filename;
	
	static {
		
		filename = new SimpleDateFormat("dd-MM-yyyyhh:mm:ss").format(new Date());
		System.out.println(filename);
		projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		//reportPath = projectPath+"\\Output\\ExtentReports\\MerrillTesting"+"\\"+filename+".html";
		reportPath = projectPath+"\\Output\\ExtentReports\\MerrillTesting.html";
		System.out.println(reportPath);
		//System.setProperty("webdriver.gecko.driver", projectPath+"\\geckodriver.exe");	
		System.setProperty("webdriver.chrome.driver", projectPath+"\\chromedriver.exe");	
		report = new ExtentReports(reportPath, false);;
	}
	
	
	public static void LoadConfigPropFile() throws IOException {
		//System.out.println("inside before");		
		//System.out.println(projectPath);
		f = new File(projectPath + "\\src\\test\\java\\com\\restassured\\merrill\\config\\config.properties");
		fis = new FileInputStream(f);
		config = new Properties();
		config.load(fis);
		
	}
	

	public static void LoadDataPropFile() throws IOException {
		//System.out.println("inside before");	
		//System.out.println(projectPath);
		f = new File(projectPath + "\\src\\test\\java\\com\\restassured\\merrill\\config\\Data.properties");
		fis = new FileInputStream(f);
		data = new Properties();
		data.load(fis);
		
	}
	
	public static String getToken() {

		String res = given().header("Content-Type", "application/json")
				.body("{" 
						+ "\"password\": \"Password1!\"," 
						+ "\"username\": \"dummyone@merrillcorp.com\"" 
						+ "}")
				.when()
				.post("http://token-service.apps.us2.dev.foundry.mrll.com/api/tokens/internaluser/javelinJwt")
				.then()
				.extract()
				.response().asString();

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
	
}
