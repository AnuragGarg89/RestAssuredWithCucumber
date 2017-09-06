/**
 * 
 */
package com.restAssured.Merrill.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;

/**
 * @author anurag.garg1
 *
 */
public class Convert {
	
	public static JsonPath rawToJson(String s){
		JsonPath json = new JsonPath(s);		
		return json;
	}
	
	public static XmlPath rawToXml(String s){
		XmlPath xml = new XmlPath(s);		
		return xml;
	}
	

}
