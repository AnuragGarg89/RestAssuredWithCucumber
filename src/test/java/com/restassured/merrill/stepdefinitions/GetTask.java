package com.restassured.merrill.stepdefinitions;

import org.junit.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;
import com.restassured.merrill.reusables.Convert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;

public class GetTask extends BaseClass{

	Keywords keywords = new Keywords();
	String param[] = new String[4];
	String value[] = new String[4];
	
	@Given("^user has filter field with corresponding filter values$")
	public void user_has_filter_field_with_corresponding_filter_values() throws Throwable {
		
		param[0] = "params[0].filterField";
		param[1] = "params[0].filterValue";
		param[2] = "params[1].filterField";
		param[3] = "params[1].filterValue";
		
		value[0] = "projectName";
		value[1] = "MDP_Test_Project";
		value[2] = "uploadedBy";
		value[3] = "Abhishek Singh";
		
	}

	@When("^user hits the GetTask api$")
	public void user_hits_the_GetTask_api() throws Throwable {
		
		printRequestLogs("GetTaskApi API");
		requestSpec = keywords.returnRequestSpecWithQueryParam(param, value);
		requestSpec.log().all();
		response = keywords.returnResponeForGet("END_POINT_GET_TASKS");		
	}

	@Then("^print the total number of records$")
	public void print_the_total_number_of_records() throws Throwable {

		String extractResponse = response.then().extract().asString();
		JsonPath jp = Convert.rawToJson(extractResponse);		
		int totalNumberOfRecords = jp.get("totalRecords");
		Assert.assertTrue(totalNumberOfRecords > 0);
		logger.log(LogStatus.INFO, "total Number of records: " + totalNumberOfRecords);
		printResponseLogs("GetTaskApi API");
		response.then().log().all();
		
		
		
				
	}

}
