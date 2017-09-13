package com.restassured.merrill.runner;

import org.openqa.selenium.chrome.ChromeDriver;

import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.java.After;

public class GlobalHooks extends BaseClass {

	@After()
	public void tearDown() throws InterruptedException {

		driver = new ChromeDriver();
		Thread.sleep(8000L);
		driver.get(reportPath);
		Thread.sleep(20L);
		driver.close();

		printStream.flush();
		printStream.close();

		report.endTest(logger);
		report.flush();
	}

}
