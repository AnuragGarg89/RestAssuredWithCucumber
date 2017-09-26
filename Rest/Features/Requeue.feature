Feature: Requeue Api's

Scenario: Test Manual processing RequeueTask Api 
	Given user has Path of RequeueTask Json File in which payload is defined
	When user hits the Manual Requeue Task API
	Then verify the lastAction field from database 
	And verify the status code is 200
	
	
#Scenario: Test Auto processing RequeueTask Api 
#	Given user has Path of Json File in which payload is defined
#	When user hits the "Auto Requeue Task API" 
#	Then verify the status code is 200
	 

