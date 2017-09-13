package com.restassured.merrill.testcases;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ManualProcessingGetUserInfo extends BaseClass {

	public String END_POINT_USERINFO = "/api/manualdoc/userInfo";

	@Given("^user has JWT Token$")
	public void user_has_a_JWT_Token() throws Throwable {

		printRequestLogs("GetUserInfo API");
		request = Keywords.returnRequestSpec();
		request.log().all();

	}

	@When("^user hits the getUserInfo api$")
	public void user_hits_the_getUserInfo_api() throws Throwable {
		
		response = Keywords.returnResponeSpecForGet("END_POINT_USERINFO");

	}

	@Then("^check response code is (\\d+)$")
	public void check_response_code_is(int statuscode) throws Throwable {
		
		Keywords.validateResponse(statuscode);
		printResponseLogs("GetUserInfo API");
		response.then().log().all();

	}

}
