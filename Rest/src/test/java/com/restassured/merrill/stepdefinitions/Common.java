package com.restassured.merrill.stepdefinitions;

import com.restassured.merrill.helper.Keywords;
import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Common extends BaseClass {

	Keywords keywords = new Keywords();

	@Given("^user has a unique task id$")
	public void user_has_a_unique_task_id() throws Throwable {

		requestSpec = keywords.returnRequestSpecWithPathParam("uniqueTaskId", data.getProperty("uniqueTaskId"));
	}

	@Then("^verify the status code is (\\d+)$")
	public void verify_the_status_code_is(int statuscode) throws Throwable {

		keywords.validateResponse(statuscode);

	}
}
