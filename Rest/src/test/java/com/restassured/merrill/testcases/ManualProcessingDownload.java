package com.restassured.merrill.testcases;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ManualProcessingDownload extends BaseClass {

	@Given("^user has a unique task id$")
	public void user_has_a_unique_task_id() throws Throwable {

		printRequestLogs("Download API");
		request = Keywords.returnRequestSpec("uniqueTaskId");
		request.log().all();
	}

	@When("^user hits the Download api$")
	public void user_hits_the_Download_api() throws Throwable {
		response = Keywords.returnResponeSpecForGet("END_POINT_DOWNLOAD");
	}

	@Then("^verify the status code is (\\d+)$")
	public void verify_the_status_code_is(int statuscode) throws Throwable {
		
		Keywords.validateResponse(statuscode);
		printResponseLogs("Download API");
		response.then().log().all();

	}

}