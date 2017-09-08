package com.restassured.merrill.testcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ManualProcessingDownload extends BaseClass {

	public String END_POINT_DOWNLOAD = "/api/manualdoc/{uniqueTaskId}/download";

	@Given("^user has a unique task id$")
	public void user_has_a_unique_task_id() throws Throwable {

		logger = report.startTest("TESTING DOWNLOAD API");

		logger.log(LogStatus.INFO, "SETTING HEADERS AND PATH PARAMETERS");

		LoadConfigPropFile();
		LoadDataPropFile();

		baseURI = config.getProperty("HOST");
		request = given().headers(BaseClass.setHeaders()).pathParam("uniqueTaskId", data.getProperty("UniqueTaskID"));

		logger.log(LogStatus.INFO, "REQUEST LOGS: " + request.log().all());
	}

	@When("^user hits the Download api$")
	public void user_hits_the_Download_api() throws Throwable {

		response = request.when().get(END_POINT_DOWNLOAD);

		logger.log(LogStatus.INFO, "GET METHOD CALLED");

	}

	@Then("^verify the status code is (\\d+)$")
	public void verify_the_status_code_is(int statuscode) throws Throwable {

		response.then().assertThat().statusCode(statuscode);

		logger.log(LogStatus.INFO, "RESPONSE LOGS: " + response.then().log().all());

		logger.log(LogStatus.PASS, "DOWNLOAD API IS UP AND RUNNING");
		logger.log(LogStatus.INFO, "END TEST");
	}

}