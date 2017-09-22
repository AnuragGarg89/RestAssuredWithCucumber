package com.restassured.merrill.reusables;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJosnFile {

	String filePath;
	File file;
	FileReader fileReader;
	JSONObject jObj;
	JSONParser jParser;
	JSONArray jArray;
	Map<Object, Object> map;
	Object value;

	public ReadJosnFile(String path) {

		try {

			filePath = path;
			file = new File(filePath);
			fileReader = new FileReader(file);
			jParser = new JSONParser();
			jArray = (JSONArray) jParser.parse(fileReader);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public Object getKeyValue(Object keyName) {
		try {

			for (int i = 0; i < jArray.size(); i++) {
				jObj = (JSONObject) jParser.parse(jArray.get(i).toString());
				value = jObj.get(keyName).toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

}
