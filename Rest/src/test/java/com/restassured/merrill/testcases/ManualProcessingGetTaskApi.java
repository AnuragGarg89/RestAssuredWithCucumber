package com.restassured.merrill.testcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.reusables.BaseClass;
import com.restassured.merrill.reusables.Convert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;

public class ManualProcessingGetTaskApi extends BaseClass {
	
	public String END_POINT_GET_TASKS = "/api/manualdoc/tasks";
	
	public static Map<String,String> setQueryParam() throws IOException{
		
		LoadDataPropFile();		
		Map<String,String> queryParam = new HashMap<String,String>();		
		queryParam.put("params[0].filterField", data.getProperty("FilterField[0]"));
		queryParam.put("params[0].filterValue", data.getProperty("FilterValue[0]"));
		queryParam.put("params[1].filterField", data.getProperty("FilterField[1]"));
		queryParam.put("params[1].filterValue", data.getProperty("FilterValue[1]"));		
		return queryParam;			
		
	}	
	@Given("^user has filter field with corresponding filter values$")	
	public void user_has_filter_field() throws Throwable {
		
		report = new ExtentReports("D://Rest//Report//DocConversion.html");		 
		logger = report.startTest("Testig GetTask Api");
		
		LoadConfigPropFile();
		baseURI = config.getProperty("HOST");
		request = given()
				.queryParams(setQueryParam())
				.headers(BaseClass.setHeaders()).log().all();
		
		logger.log(LogStatus.INFO, "Headers and query parameters Declared");
	}

	@When("^user hits the getTask api$")
	public void user_hits_the_getTask_api() throws Throwable {
		
	    response = request.when().get(END_POINT_GET_TASKS);
	    
	    logger.log(LogStatus.INFO, "Get Method called");

	}
	
	@Then("^verify the status code in response is (\\d+)$")
	public void verify_the_status_code_in_response_is(int statuscode) throws Throwable {
		
		String extractResponse = response.then().extract().asString();
		JsonPath jp = Convert.rawToJson(extractResponse);
		int totalNumberOfRecords =	jp.get("totalRecords");	
		logger.log(LogStatus.INFO, "total Number of records: " + totalNumberOfRecords);
		Assert.assertTrue(totalNumberOfRecords>0);
		response.then().assertThat().statusCode(statuscode);
		logger.log(LogStatus.PASS, "GetTask Api up and running");
		report.endTest(logger);
		report.flush();
	}
}
