package com.restassured.merrill.stepdefinitions;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;
import com.restassured.merrill.reusables.Convert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;

public class FilterField extends BaseClass {
	
	Keywords keywords = new Keywords();
	
	@Given("^user has filter field$")
	public void user_has_filter_field() throws Throwable {
		
		requestSpec = keywords.returnRequestSpecWithPathParam("filterField", data.getProperty("FilterField"));
		
	}

	@When("^user hits the Filter Field api$")
	public void user_hits_the_Filter_Field_api() throws Throwable {
		
		printRequestLogs("Filter Field API");
		requestSpec.log().all();
		response = keywords.returnResponeForGet("END_POINT_FILTERFIELD");
		
			
	}
	
	@Then("^print total number of records$")
	public void print_total_number_of_records() throws Throwable {
		
		String extractResponse = response.then().extract().asString();
		
		JsonPath jp = Convert.rawToJson(extractResponse);
		int totalNumberOfRecords = jp.get("totalRecords");
		logger.log(LogStatus.INFO, "TOTAL NUMBER OF RECORDS : "+totalNumberOfRecords);		
		
		printResponseLogs("Filter Field API");
		response.then().log().all();
	    
	}

}
