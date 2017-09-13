package com.restassured.merrill.helper;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.restassured.merrill.reusables.BaseClass;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Keywords extends BaseClass {

	public static RequestSpecification returnRequestSpec() throws IOException {

		request = given().headers(BaseClass.setHeaders());

		return request;

	}

	public static RequestSpecification returnRequestSpec(String pathParamName) throws IOException {	

		request = given().headers(BaseClass.setHeaders()).pathParam(pathParamName, data.getProperty(pathParamName));

		return request;

	}
	
	public static Response returnResponeSpecForGet(String EndPoint ) throws IOException {

		response = request.when().get(config.getProperty(EndPoint));

		return response;

	}	
	
	public static Response returnResponeSpecForPost(String EndPoint ) throws IOException {

		response = request.when().post(config.getProperty(EndPoint));

		return response;

	}
	
	public static Response returnResponeSpecForPut(String EndPoint ) throws IOException {

		response = request.when().put(config.getProperty(EndPoint));

		return response;

	}
	
	public static ValidatableResponse validateResponse(int statusCode){
		
		validateResponse = 	response.then().assertThat().statusCode(statusCode);
		
		return validateResponse;
		
	}

}
