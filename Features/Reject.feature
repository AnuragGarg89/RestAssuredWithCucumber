Feature: Reject Api's

Scenario: Test Manual processing RejectTask Api 
	Given user has Path of Reject Json File in which payload is defined
	When user hits the Manual RejectTask API
	Then verify the rejectreason from database
	And verify the status code is 200