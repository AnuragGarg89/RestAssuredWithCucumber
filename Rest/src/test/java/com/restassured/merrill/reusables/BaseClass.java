package com.restassured.merrill.reusables;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
	public ExtentReports report;
	public ExtentTest logger;
	
	
	public static void LoadConfigPropFile() throws IOException {
		//System.out.println("inside before");	
		String userpath = System.getProperty("user.dir");
		//System.out.println(userpath);
		f = new File(userpath + "\\src\\test\\java\\com\\restassured\\merrill\\config\\config.properties");
		fis = new FileInputStream(f);
		config = new Properties();
		config.load(fis);
		
	}
	

	public static void LoadDataPropFile() throws IOException {
		//System.out.println("inside before");	
		String userpath = System.getProperty("user.dir");
		//System.out.println(userpath);
		f = new File(userpath + "\\src\\test\\java\\com\\restassured\\merrill\\config\\Data.properties");
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
