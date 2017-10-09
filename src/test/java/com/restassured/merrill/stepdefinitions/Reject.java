package com.restassured.merrill.stepdefinitions;

import org.junit.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;
import com.restassured.merrill.reusables.DBConnection;
import com.restassured.merrill.reusables.ReadJosnFile;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Reject extends BaseClass{	
	
	String path = projectPath+"\\TestData\\ManualProcessing\\Reject.json";
	
	Keywords keywords = new Keywords();
	DBConnection conn = new DBConnection();
	ReadJosnFile jsonFile = new ReadJosnFile(path);	
	
	String rejectReason = "rejectReason";
	String keyValue;	
	
	@Given("^user has Path of Reject Json File in which payload is defined$")
	public void user_has_Path_of_Reject_Json_File_in_which_payload_is_defined() throws Throwable {
		
		requestSpec = keywords.returnRequestSpecWithBody(path);
	   
	}
	
	@When("^user hits the Manual RejectTask API$")
	public void user_hits_the_Manual_RejectTask_API() throws Throwable {
		
		printRequestLogs("Manual RejectTask Task API");
		requestSpec.log().all();
	    response = keywords.returnResponeForPut("END_POINT_REJECT");		
	
	}
	
	@Then("^verify the rejectreason from database$")
	public void verify_the_rejectreason_from_database() throws Throwable {
	
			conn.openMongoConnection();	
			
			String rejReason = conn.getRejectReason(Integer.parseInt(jsonFile.getKeyValue("uniqueTaskId")), rejectReason);			

			logger.log(LogStatus.INFO, "REJECTING TASK ID : "+ Integer.parseInt(jsonFile.getKeyValue("uniqueTaskId")));			
			logger.log(LogStatus.INFO, "EXPECTED REJECT REASON : "+ rejReason.toLowerCase());
			logger.log(LogStatus.INFO, "ACTUAL REJECT REASON : "+ jsonFile.getKeyValue(rejectReason).toLowerCase());
					
			Assert.assertEquals("Verifying Reject Reason",jsonFile.getKeyValue(rejectReason).toLowerCase(), rejReason.toLowerCase());
					
			printResponseLogs("Manual RejectTask API");
			
			response.then().log().all();
			
			conn.closeMongoConnection();	
		
		
	}


}
