package com.restassured.merrill.stepdefinitions;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;
import com.restassured.merrill.reusables.DBConnection;
import com.restassured.merrill.reusables.ReadJosnFile;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Requeue extends BaseClass {
	
	String path = projectPath+"\\TestData\\ManualProcessing\\Requeue.json";
	
	Keywords keywords = new Keywords();
	DBConnection conn = new DBConnection();
	ReadJosnFile jsonFile = new ReadJosnFile(path);
	
	String fieldName = "lastAction";
	//String expectedValue = "Requeued from Manual";

	@Given("^user has Path of RequeueTask Json File in which payload is defined$")
	public void user_has_Path_of_RequeueTask_Json_File_in_which_payload_is_defined() throws Throwable {
		
		requestSpec = keywords.returnRequestSpecWithBody(path);
		  
	}
	
	@When("^user hits the Manual Requeue Task API$")
	public void user_hits_the_Manual_Requeue_Task_API() throws Throwable {
		
		printRequestLogs("Manual Requeue Task API");
		requestSpec.log().all();
	    response = keywords.returnResponeForPut("END_POINT_MANUAL_REQUEUE");
		
	    
	}
	
	@Then("^verify the lastAction field from database$")
	public void verify_the_lastAction_field_from_database() throws Throwable {
		
		conn.openMongoConnection();
		
		String actualValue = conn.getLastAction(Integer.parseInt(jsonFile.getKeyValue("uniqueTaskId")), fieldName);
		
		logger.log(LogStatus.INFO, "TASK ID REQUEUED: "+ Integer.parseInt(jsonFile.getKeyValue("uniqueTaskId")));			
		//logger.log(LogStatus.INFO, "EXPECTED VALUE : "+ expectedValue);
		logger.log(LogStatus.INFO, "ACTUAL VALUE : "+ actualValue);
		
		printResponseLogs("Manual Requeue Task API");
		response.then().log().all();
		conn.closeMongoConnection();		
		
		//Assert.assertEquals("Verifying Database record", expectedValue, actualValue);
		
		
	
	    
	}


}
