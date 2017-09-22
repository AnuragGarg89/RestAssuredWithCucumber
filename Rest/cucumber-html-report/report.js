$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./Features/Reject.feature");
formatter.feature({
  "line": 1,
  "name": "Reject Api\u0027s",
  "description": "",
  "id": "reject-api\u0027s",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test Manual processing RejectTask Api",
  "description": "",
  "id": "reject-api\u0027s;test-manual-processing-rejecttask-api",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "user has Path of Reject Json File in which payload is defined",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "user hits the Manual RejectTask API",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "verify the rejectreason from database",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "verify the status code is 200",
  "keyword": "And "
});
formatter.match({
  "location": "Reject.user_has_Path_of_Reject_Json_File_in_which_payload_is_defined()"
});
formatter.result({
  "duration": 2932268036,
  "status": "passed"
});
formatter.match({
  "location": "Reject.user_hits_the_Manual_RejectTask_API()"
});
formatter.result({
  "duration": 3250432644,
  "status": "passed"
});
formatter.match({
  "location": "Reject.verify_the_rejectreason_from_database()"
});
formatter.result({
  "duration": 112945834671,
  "error_message": "java.lang.IllegalStateException: state should be: open\r\n\tat com.mongodb.assertions.Assertions.isTrue(Assertions.java:70)\r\n\tat com.mongodb.connection.BaseCluster.selectServer(BaseCluster.java:82)\r\n\tat com.mongodb.binding.ClusterBinding$ClusterBindingConnectionSource.\u003cinit\u003e(ClusterBinding.java:75)\r\n\tat com.mongodb.binding.ClusterBinding$ClusterBindingConnectionSource.\u003cinit\u003e(ClusterBinding.java:71)\r\n\tat com.mongodb.binding.ClusterBinding.getReadConnectionSource(ClusterBinding.java:63)\r\n\tat com.mongodb.operation.OperationHelper.withConnection(OperationHelper.java:404)\r\n\tat com.mongodb.operation.FindOperation.execute(FindOperation.java:709)\r\n\tat com.mongodb.operation.FindOperation.execute(FindOperation.java:81)\r\n\tat com.mongodb.Mongo.execute(Mongo.java:810)\r\n\tat com.mongodb.Mongo$2.execute(Mongo.java:797)\r\n\tat com.mongodb.OperationIterable.iterator(OperationIterable.java:47)\r\n\tat com.mongodb.FindIterableImpl.iterator(FindIterableImpl.java:200)\r\n\tat com.restassured.merrill.reusables.DBConnection.getMyData(DBConnection.java:81)\r\n\tat com.restassured.merrill.stepdefinitions.Reject.verify_the_rejectreason_from_database(Reject.java:49)\r\n\tat ✽.Then verify the rejectreason from database(./Features/Reject.feature:6)\r\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 26
    }
  ],
  "location": "Common.verify_the_status_code_is(int)"
});
formatter.result({
  "status": "skipped"
});
});