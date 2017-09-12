package com.restassured.merrill.runner;

import org.openqa.selenium.chrome.ChromeDriver;

import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.After;

public class TestHooks extends BaseClass {
	
	@After("@Api")
	public void tearDown() throws InterruptedException{
		
		driver = new ChromeDriver();
		Thread.sleep(5L);
		driver.get(reportPath);	
		Thread.sleep(20L);
		driver.close();
		
		printStream.flush();
		printStream.close();
		
			
		report.endTest(logger);
		report.flush();
		
		
		
	}
	
}
