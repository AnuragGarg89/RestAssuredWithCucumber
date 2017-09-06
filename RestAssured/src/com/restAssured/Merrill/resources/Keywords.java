/**
 * 
 */
package com.restAssured.Merrill.resources;

/**
 * @author anurag.garg1
 *
 */
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import com.restAssured.Merrill.TestCases.Token;

import io.restassured.path.json.JsonPath;

public class Keywords {

	public static int returnStatusForPut(String Payload, String URL) {

		int statusCode = given().headers(Keywords.setHeaders()).body(Payload).when().put(URL).then().extract()
				.statusCode();

		return statusCode;

	}

	public static int returnStatusForPost(String ContentType, String Accept, String Authorization, String Payload,
			String URL) {

		int statusCode = given().headers(Keywords.setHeaders()).body(Payload).when().post(URL).then().extract()
				.statusCode();

		return statusCode;

	}

	public static int returnStatusForGet(String ContentType, String Accept, String Authorization, String Payload,
			String URL) {

		int statusCode = given().headers(Keywords.setHeaders()).when().get(URL).then().extract().statusCode();

		return statusCode;

	}

	public static int returnStatusForDelete(String ContentType, String Accept, String Authorization, String Payload,
			String URL) {

		int statusCode = given().headers(Keywords.setHeaders()).body(Payload).when().get(URL).then().extract()
				.statusCode();

		return statusCode;

	}

	public static Map<String, String> setHeaders() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("Accept", "application/json");
		map.put("Authorization", "Bearer " + Token.getToken());

		return map;

	}

	public static String responseForPut(String Payload, String URL) {

		String response = given().headers(Keywords.setHeaders()).body(Payload).when().put(URL).then().extract()
				.response().asString();

		return response;

	}

	public static String responseForPost(String Payload, String URL) {

		String response = given().headers(Keywords.setHeaders()).body(Payload).when().post(URL).then().extract()
				.response().asString();

		return response;

	}

	public static String responseForDelete(String Payload, String URL) {

		String response = given().headers(Keywords.setHeaders()).body(Payload).when().delete(URL).then().extract()
				.response().asString();

		return response;

	}

	public static String responseForGet(String Payload, String URL) {

		String response = given().headers(Keywords.setHeaders()).when().get(URL).then().extract().response().asString();

		return response;

	}

	public static String verifyResponseForPut(String Payload, String URL, String tagName) {

		String res = Keywords.responseForPut(Payload, URL);

		JsonPath jp = new JsonPath(res);

		String value = jp.get(tagName);

		return value;
	}

}
