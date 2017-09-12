package com.restassured.merrill.runner;

import org.junit.runner.RunWith;

import com.restassured.merrill.reusables.BaseClass;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(	features = "./", 
					glue = {"com.restassured.merrill.testcases"},
					format = {"pretty", "html:./Output"})
				

public class TestRunner extends BaseClass {
	
	
	
	}
		

