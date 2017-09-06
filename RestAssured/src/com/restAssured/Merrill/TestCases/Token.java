package com.restAssured.Merrill.TestCases;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.restAssured.Merrill.resources.BaseClass;
import com.restAssured.Merrill.resources.Keywords;
import com.restAssured.Merrill.resources.PayLoad;
import com.restAssured.Merrill.resources.Resources;
import com.restAssured.Merrill.utils.*;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

/**
 * @author anurag.garg1
 *
 */
public class Token extends BaseClass {

	public static String getToken() {

		baseURI = prop.getProperty("HOST");

		String res = given().header("Content-Type", prop.getProperty("ContentType")).body(PayLoad.data()).log().all()
				.when().post(Resources.javelinJwtInternalUser()).then().log().all().assertThat()
				.statusCode(HttpStatus.SC_OK).extract().response().asString();

		JsonPath jp = Convert.rawToJson(res);
		String jwt = jp.get("jwt");
		return jwt;

	}

	@Test(alwaysRun = false)
	public void CheckStatus() {

		String Body = "" + "[{" + "\"uniqueTaskId\": 8890" + "}]";

		String URL = "http://doc-conversion.apps.us2.dev.foundry.mrll.com/api/manualdoc/requeue";

		int status = Keywords.returnStatusForPut(Body, URL);

		Assert.assertEquals(status, 200);
	}
	
	
	@Test(alwaysRun = false)
	public void getResponse() {

		String Body = "" + "[{" + "\"uniqueTaskId\": 8890" + "}]";

		String URL = "http://doc-conversion.apps.us2.dev.foundry.mrll.com/api/manualdoc/requeue";

		String res = Keywords.responseForPut(Body, URL);
		
		System.out.println(res);

	
	}
	
	
	@Test(alwaysRun = true)
	public void verifyResponse() {

		String Body = "" + "[{" + "\"uniqueTaskId\": 8890" + "}]";

		String URL = "http://doc-conversion.apps.us2.dev.foundry.mrll.com/api/manualdoc/requeue";

		String res = Keywords.verifyResponseForPut(Body, URL, "message");
		
		System.out.println(res);

	
	}

}
