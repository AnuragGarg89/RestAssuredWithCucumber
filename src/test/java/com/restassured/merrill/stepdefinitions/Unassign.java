package com.restassured.merrill.stepdefinitions;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.When;

public class Unassign extends BaseClass{
	
	Keywords keywords = new Keywords();

	@When("^user hits the UnassignTask Api$")
	public void user_hits_the_UnassignTask_Api() throws Throwable {
		
		printRequestLogs("UnassignTask API");
		requestSpec.log().all();
		response = keywords.returnResponeForPut("END_POINT_UNASSIGN");
		printResponseLogs("UnassignTask API");
		response.then().log().all();
	    
	}

}
