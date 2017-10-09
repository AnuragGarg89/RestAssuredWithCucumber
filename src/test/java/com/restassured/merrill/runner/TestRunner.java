package com.restassured.merrill.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(	features = "./Features/download.feature", 
					glue = {"com.restassured.merrill.stepdefinitions"},
					format = {"pretty","html:./cucumber-html-report"})
				

public class TestRunner  {
	
	    
	    }
	

		

