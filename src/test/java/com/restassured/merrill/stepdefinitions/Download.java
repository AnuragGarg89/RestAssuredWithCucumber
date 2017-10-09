package com.restassured.merrill.stepdefinitions;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.When;

public class Download extends BaseClass {
	
	Keywords keywords = new Keywords();

	
	@When("^user hits the Download API$")
	public void user_hits_the_Download_API() throws Throwable {
		
		printRequestLogs("Download API");
		requestSpec.log().all();
		response = keywords.returnResponeForGet("END_POINT_DOWNLOAD");
		printResponseLogs("Download API");
		response.then().log().all();
		   
	}
}