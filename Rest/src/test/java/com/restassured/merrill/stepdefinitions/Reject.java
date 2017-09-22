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
	
	Keywords keywords = new Keywords();
	DBConnection conn = new DBConnection();
	
	String path = projectPath+"\\TestData\\ManualProcessing\\Reject.json";
	
	ReadJosnFile jsonFile = new ReadJosnFile(path);	
	
	String key = "rejectReason";
	Object keyValue;	
	
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
	
			conn.testMongoConnection();			
			keyValue = jsonFile.getKeyValue(key);			
			Object obj = conn.getMyData(jsonFile.getKeyValue("uniqueTaskId"), key);
			
			Assert.assertEquals("Verifying Reject Reason", keyValue.toString().toLowerCase(), (String)obj);
			
			logger.log(LogStatus.INFO, "Reject Reason is: "+obj.toString());
			printResponseLogs("Manual RejectTask API");
			response.then().log().all();
		
		
	}


}
