Feature: Filter Field Api's 

Scenario: Test Manual processing Filter Field Api 
		Given user has filter field
		When user hits the Filter Field api 
		Then print total number of records
		And verify the status code is 200