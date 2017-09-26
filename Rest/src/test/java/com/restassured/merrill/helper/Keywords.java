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
import io.restassured.specification.RequestSpecification;

public class Keywords extends BaseClass {

	public RequestSpecification returnRequestSpecWithoutParam() throws IOException {

		requestSpec = given().headers(setHeaders());
		return requestSpec;

	}

	public RequestSpecification returnRequestSpecWithPathParam(String pathParamName, Object value) throws IOException {

		// requestSpec =
		// given().headers(BaseClass.setHeaders()).pathParam(pathParamName,
		// data.getProperty(pathParamName));
		requestSpec = given().headers(setHeaders()).pathParam(pathParamName, value);
		return requestSpec;

	}

	public RequestSpecification returnRequestSpecWithQueryParam(String param[], Object value[]) throws IOException {

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

		requestSpec = given().headers(setHeaders()).queryParams(queryParam);
		return requestSpec;

	}

	public RequestSpecification returnRequestSpecWithBody(String filePath) {
		try {
			File f = new File(filePath);
			requestSpec = given().headers(setHeaders()).body(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpec;
	}

	public RequestSpecification returnRequestSpecWithMultiPartFormData(String path) {
		try {
			
			File f = new File(path);
			requestSpec = given().headers(setHeaders()).header("Content-Type","multipart/form-data").multiPart(f);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestSpec;
	}

	public Response returnResponeForGet(String EndPoint) throws IOException {

		response = requestSpec.when().get(config.getProperty(EndPoint));
		return response;

	}

	public Response returnResponeForPost(String EndPoint) throws IOException {

		response = requestSpec.when().post(config.getProperty(EndPoint));
		return response;

	}

	public Response returnResponeForPut(String EndPoint) throws IOException {

		response = requestSpec.when().put(config.getProperty(EndPoint));
		return response;

	}

	public Response returnResponeForDelete(String EndPoint) throws IOException {

		response = requestSpec.when().delete(config.getProperty(EndPoint));
		return response;

	}

	public void validateResponse(int statusCode) {

		response.then().assertThat().statusCode(statusCode);

	}

	public String validateResponse(String getValueOfField) {

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
