package com.restassured.merrill.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./", 
				glue = {"com.restassured.merrill.testcases"})
				

public class TestRunner {
	
		

}
