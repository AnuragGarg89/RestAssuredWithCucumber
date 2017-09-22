Feature: GetTask Api's

Scenario: Test Manual processing GetTask Api 
		Given user has filter field with corresponding filter values
		When user hits the GetTask api 
		Then print the total number of records
		And verify the status code is 200