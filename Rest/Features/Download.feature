Feature: Download Api's

Scenario: Test Manual processing Download Api 
		Given user has a unique task id
		When user hits the Download API 
		Then verify the status code is 200