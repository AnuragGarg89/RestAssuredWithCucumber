package com.restassured.merrill.testcases;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;

public class ManualProcessingGetUserInfo extends BaseClass {

	public String END_POINT_USERINFO = "/api/manualdoc/userInfo";
	

	@Given("^user has JWT Token$")
	public void user_has_a_JWT_Token() throws Throwable {

		logger = report.startTest("loggerING GET USER INFO API");
		logger.log(LogStatus.INFO, "SETTING HEADERS");

		LoadConfigPropFile();
		LoadDataPropFile();

		baseURI = config.getProperty("HOST");
		request = given().headers(BaseClass.setHeaders());
		printStream.println("####################### Printing Request Logs for ManualProcessingGetUserInfo #######################");
		request.log().all();
		printStream.println();
		
		logger.log(LogStatus.INFO, "CHECK LOG FOLDER FOR REQUEST LOGS");
	
	}

	@When("^user hits the getUserInfo api$")
	public void user_hits_the_getUserInfo_api() throws Throwable {

		response = request.when().get(END_POINT_USERINFO);

		logger.log(LogStatus.INFO, "GET METHOD CALLED");

	}

	@Then("^check response code is (\\d+)$")
	public void check_response_code_is(int statuscode) throws Throwable {

		response.then().assertThat().statusCode(statuscode);
		
		printStream.println("####################### Printing Response Logs for ManualProcessingGetUserInfo #######################");
		response.then().log().all();
		printStream.println();
		
		logger.log(LogStatus.INFO, "CHECK LOG FOLDER FOR RESPONSE LOGS");
		
		logger.log(LogStatus.PASS, "GET USER INFO API IS UP AND RUNNING");
		logger.log(LogStatus.INFO, "END logger");
		
		
	}
	
	
}
