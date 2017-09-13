$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Features/Merrill.feature");
formatter.feature({
  "line": 1,
  "name": "Merrill Api\u0027s",
  "description": "",
  "id": "merrill-api\u0027s",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test Manual processing Download Api",
  "description": "",
  "id": "merrill-api\u0027s;test-manual-processing-download-api",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "user has a unique task id",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "user hits the Download api",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "verify the status code is 200",
  "keyword": "Then "
});
formatter.match({
  "location": "ManualProcessingDownload.user_has_a_unique_task_id()"
});
formatter.result({
  "duration": 2644016821,
  "status": "passed"
});
formatter.match({
  "location": "ManualProcessingDownload.user_hits_the_Download_api()"
});
formatter.result({
  "duration": 2477317178,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 26
    }
  ],
  "location": "ManualProcessingDownload.verify_the_status_code_is(int)"
});
formatter.result({
  "duration": 39418389,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Test Manual processing getUserInfo Api",
  "description": "",
  "id": "merrill-api\u0027s;test-manual-processing-getuserinfo-api",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "user has JWT Token",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "user hits the getUserInfo api",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "check response code is 200",
  "keyword": "Then "
});
formatter.match({
  "location": "ManualProcessingGetUserInfo.user_has_a_JWT_Token()"
});
formatter.result({
  "duration": 771568353,
  "status": "passed"
});
formatter.match({
  "location": "ManualProcessingGetUserInfo.user_hits_the_getUserInfo_api()"
});
formatter.result({
  "duration": 2349717747,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 23
    }
  ],
  "location": "ManualProcessingGetUserInfo.check_response_code_is(int)"
});
formatter.result({
  "duration": 1314326,
  "status": "passed"
});
});