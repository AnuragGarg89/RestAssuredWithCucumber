package com.restassured.merrill.testcases;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.chrome.ChromeDriver;

public class ManualProcessingGetUserInfo extends BaseClass {

	public String END_POINT_USERINFO = "/api/manualdoc/userInfo";

	@Given("^user has JWT Token$")
	public void user_has_a_JWT_Token() throws Throwable {

		logger = report.startTest("TESTING GET USER INFO API");
		logger.log(LogStatus.INFO, "SETTING HEADERS");

		LoadConfigPropFile();
		LoadDataPropFile();

		baseURI = config.getProperty("HOST");
		request = given().headers(BaseClass.setHeaders());

		logger.log(LogStatus.INFO, "REQUEST LOGS: " + request.log().all());
	}

	@When("^user hits the getUserInfo api$")
	public void user_hits_the_getUserInfo_api() throws Throwable {

		response = request.when().get(END_POINT_USERINFO);

		logger.log(LogStatus.INFO, "GET METHOD CALLED");

	}

	@Then("^check response code is (\\d+)$")
	public void check_response_code_is(int statuscode) throws Throwable {

		response.then().assertThat().statusCode(statuscode);

		logger.log(LogStatus.INFO, "RESPONSE LOGS: " + response.then().log().all());
		logger.log(LogStatus.PASS, "GET USER INFO API IS UP AND RUNNING");
		logger.log(LogStatus.INFO, "END TEST");
		driver = new ChromeDriver();
		
		driver.get(reportPath);		
		report.endTest(logger);
		report.flush();
		
		//driver.close();
	}

	
}
