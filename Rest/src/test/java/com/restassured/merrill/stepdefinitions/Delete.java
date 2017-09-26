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

public class Delete extends BaseClass {

	String path = projectPath + "\\TestData\\ManualProcessing\\Delete.json";

	Keywords keywords = new Keywords();
	DBConnection conn = new DBConnection();
	ReadJosnFile jsonFile = new ReadJosnFile(path);

	@Given("^user has Path of Delete Json File in which payload is defined$")
	public void user_has_Path_of_Delete_Json_File_in_which_payload_is_defined() throws Throwable {

		requestSpec = keywords.returnRequestSpecWithBody(path);
	}

	@When("^user hits the Delete API$")
	public void user_hits_the_Delete_API() throws Throwable {

		printRequestLogs("Delete API");
		requestSpec.log().all();
		response = keywords.returnResponeForDelete("END_POINT_DELETE");

	}

	@Then("^Verify the record doesnt exist is database anymore$")
	public void verify_the_record_doesnt_exist_is_database_anymore() throws Throwable {

		conn.openMongoConnection();
		boolean Not_exist_in_db = conn.verifyTaskIdExistInDatabase(Integer.parseInt(jsonFile.getKeyValue("uniqueTaskId")));

		if (Not_exist_in_db) {
			logger.log(LogStatus.INFO, "DELETE SUCCESSFUL!!");
		} else {
			logger.log(LogStatus.INFO, "DELETE UN-SUCCESSFUL!!");
		}
		Assert.assertTrue(Not_exist_in_db);

		printResponseLogs("Delete API");
		response.then().log().all();
		conn.closeMongoConnection();
	}

}
