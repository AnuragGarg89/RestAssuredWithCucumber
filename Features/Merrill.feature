Feature: Merrill Api's 

Scenario: Test Manual processing Download Api 
	Given user has a unique task id 
	When user hits the Download api 
	Then verify the status code is 200 
	
Scenario: Test Manual processing getUserInfo Api 
	Given user has JWT Token 
	When user hits the getUserInfo api 
	Then check response code is 200 