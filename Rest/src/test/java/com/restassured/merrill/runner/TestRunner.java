package com.restassured.merrill.runner;

import org.junit.runner.RunWith;

import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(	features = "./", 
					glue = {"com.restassured.merrill.testcases"})
				

public class TestRunner extends BaseClass {
	
	/*@After
	public void tearDown(){
		
		driver.get(reportPath);		
		report.endTest(logger);
		report.flush();
		}
*/
		
	}
		

