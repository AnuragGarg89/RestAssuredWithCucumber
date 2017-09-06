package com.restAssured.Merrill.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author anurag.garg1
 *
 */
import org.testng.annotations.BeforeTest;

public class BaseClass {

	public static Properties prop;
	FileInputStream fis;
	File f;

	@BeforeTest
	public void LoadPropFile() throws IOException {

		String userpath = System.getProperty("user.dir");
		f = new File(userpath + "\\src\\com\\restAssured\\Merrill\\config\\config.properties");
		fis = new FileInputStream(f);
		prop = new Properties();
		prop.load(fis);
	}

}
