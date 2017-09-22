package com.restassured.merrill.stepdefinitions;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;

public class UserInfo extends BaseClass {
	
	Keywords keywords = new Keywords();

	@Given("^user hits the getUserInfo api$")
	public void user_hits_the_getUserInfo_api() throws Throwable {
		
		printRequestLogs("getUserInfo API");
		requestSpec = keywords.returnRequestSpecWithoutParam();
		requestSpec.log().all();
		response = keywords.returnResponeForGet("END_POINT_USERINFO");
		printResponseLogs("getUserInfo API");
		response.then().log().all();
	    
	}
}