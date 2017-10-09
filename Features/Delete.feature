Feature: Delete Api's

Scenario: Test Manual processing Delete Api 
	Given user has Path of Delete Json File in which payload is defined
	When user hits the Delete API
	Then Verify the record doesnt exist is database anymore 
	And verify the status code is 200