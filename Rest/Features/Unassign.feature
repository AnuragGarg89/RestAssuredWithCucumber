Feature: Unassign Api's

Scenario: Test Manual processing UnassignTask Api 
		Given user has a unique task id  
		When user hits the UnassignTask API 
		Then verify the status code is 200 

Scenario: Verify user gets "2004" javelin code when invalid task is provided
		Given user has an invalid unique task id
		When user hits the UnassignTask API 
		Then verify the status code is 200
		And javelin code is 2004
