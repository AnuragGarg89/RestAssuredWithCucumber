package com.restassured.merrill.helper;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.LogStatus;
import com.restassured.merrill.reusables.BaseClass;
import com.restassured.merrill.reusables.Convert;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Keywords extends BaseClass {

	public static RequestSpecification returnRequestSpecNoParam() throws IOException {

		requestSpec = given().headers(BaseClass.setHeaders());
		return requestSpec;

	}

	public static RequestSpecification returnRequestSpecPathParam(String pathParamName, Object value)
			throws IOException {

		// requestSpec = given().headers(BaseClass.setHeaders()).pathParam(pathParamName, data.getProperty(pathParamName));
		requestSpec = given().headers(BaseClass.setHeaders()).pathParam(pathParamName, value);
		return requestSpec;

	}

	public static RequestSpecification returnRequestSpecQueryParam(String param[], Object value[]) throws IOException {

		Map<String, Object> queryParam = new HashMap<String, Object>();
		int numOfParamteres = param.length;
		int numOfValues = value.length;
		if (numOfParamteres == numOfValues) {
			for (int i = 0; i < param.length; i++) {
				queryParam.put(param[i], param[i]);

			}
		} else {
			logger.log(LogStatus.ERROR, "Either Parameter or Value Missing");
		}

		requestSpec = given().headers(BaseClass.setHeaders()).queryParams(queryParam);
		return requestSpec;

	}

	public static RequestSpecification returnRequestSpecBody(String filePath){
		
		File f = new File(filePath);
		requestSpec = given().headers(BaseClass.setHeaders()).body(f);		
		return requestSpec;
	}
	
	public static Response returnResponeForGet(String EndPoint) throws IOException {

		response = requestSpec.when().get(config.getProperty(EndPoint));
		return response;

	}

	public static Response returnResponeForPost(String EndPoint) throws IOException {

		response = requestSpec.when().post(config.getProperty(EndPoint));
		return response;

	}

	public static Response returnResponeForPut(String EndPoint) throws IOException {

		response = requestSpec.when().put(config.getProperty(EndPoint));
		return response;

	}

	public static ValidatableResponse validateResponse(int statusCode) {

		validateResponse = response.then().assertThat().statusCode(statusCode);
		return validateResponse;

	}

	public static String validateResponse(String getValueOfField) {

		String responseType = response.getHeader("content-type");

		if (responseType == "application/json") {

			String ValueOfTagInResponse = response.then().extract().asString();
			JsonPath jp = Convert.rawToJson(ValueOfTagInResponse);
			return jp.get(getValueOfField);

		} else if (responseType == "application/xml") {

			String ValueOfTagInResponse = response.then().extract().asString();
			XmlPath xp = Convert.rawToXml(ValueOfTagInResponse);
			return xp.get(getValueOfField);

		} else {
			return "text/plain";
		}

	}

}
