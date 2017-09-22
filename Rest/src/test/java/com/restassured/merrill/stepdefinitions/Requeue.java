package com.restassured.merrill.stepdefinitions;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class Requeue extends BaseClass {	
	
	Keywords keywords = new Keywords();

	@Given("^user has Path of RequeueTask Json File in which payload is defined$")
	public void user_has_Path_of_RequeueTask_Json_File_in_which_payload_is_defined() throws Throwable {
		
		requestSpec = keywords.returnRequestSpecWithBody(projectPath+"\\TestData\\ManualProcessing\\Requeue.json");
		  
	}
	
	@When("^user hits the Manual Requeue Task API$")
	public void user_hits_the_Manual_Requeue_Task_API() throws Throwable {
		printRequestLogs("Manual Requeue Task API");
		requestSpec.log().all();
	    response = keywords.returnResponeForPut("END_POINT_MANUAL_REQUEUE");
		printResponseLogs("Manual Requeue Task API");
		response.then().log().all();
	    
	}

}
