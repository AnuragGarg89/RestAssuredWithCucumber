/**
 * 
 */
package com.restAssured.Merrill.resources;

import java.util.HashMap;
import java.util.Map;

/**
 * @author anurag.garg1
 *
 */
public class PayLoad {
	
	public static Map<String,String> data(){
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("password","Password1!");
		map.put("username","dummyone@merrillcorp.com");
		
		return map;
		
	}
		

	/*public static String javelinJwtInternalUserPayload() {
		String body = "{"
						+ "\"password\": \"Password1!\","
						+ "\"username\": \"dummyone@merrillcorp.com\""
					+ "}";

		return body;

	}*/

}
