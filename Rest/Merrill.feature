Feature: Merrill Api's
  
 --Scenario: Test Manual processing Download Api's
	--Given user has a unique task id	 
	--When user hits the Download api
	--Then verify the status code is 200
	
 Scenario: Test Manual processing getTask Api's
	Given user has filter field with corresponding filter values
	When user hits the getTask api
	Then verify the status code in response is 200
	