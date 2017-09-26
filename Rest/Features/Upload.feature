Feature: Upload Api's

Scenario: Test Manual processing Upload Api 
		Given user has a file top upload
		When user hits the upload API 
		Then verify the status code is 200