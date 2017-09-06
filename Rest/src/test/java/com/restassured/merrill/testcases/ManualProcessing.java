package com.restassured.merrill.testcases;

import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;

public class ManualProcessing extends BaseClass{
	
	public String END_POINT_DOWNLOAD = "/api/manualdoc/{uniqueTaskId}/download";
	
	
	@Given("^user has a unique task id$")
	public void user_has_a_unique_task_id() throws Throwable {
		
		LoadConfigPropFile();
		LoadDataPropFile();
		baseURI = config.getProperty("HOST");		
		request = given()
				.headers(BaseClass.setHeaders())
				.pathParam("uniqueTaskId", data.getProperty("UniqueTaskID"));
	}

	@When("^user hits the Download api$")
	public void user_hits_the_Download_api() throws Throwable {
	
		response = request.when().get(END_POINT_DOWNLOAD);
	}

	@Then("^verify the status code is (\\d+)$")
	public void verify_the_status_code_is(int statuscode) throws Throwable {
		
		response.then().assertThat().statusCode(statuscode);
	}
	
}
