$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./Features/download.feature");
formatter.feature({
  "line": 1,
  "name": "Download Api\u0027s",
  "description": "",
  "id": "download-api\u0027s",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Test Manual processing Download Api",
  "description": "",
  "id": "download-api\u0027s;test-manual-processing-download-api",
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
  "name": "user hits the Download API",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "verify the status code is 200",
  "keyword": "Then "
});
formatter.match({
  "location": "Common.user_has_a_unique_task_id()"
});
formatter.result({
  "duration": 4056474279,
  "status": "passed"
});
formatter.match({
  "location": "Download.user_hits_the_Download_API()"
});
formatter.result({
  "duration": 3616234920,
  "status": "passed"
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
  "duration": 33154886,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Verify user gets \"2004\" javelin code when invalid task is provided",
  "description": "",
  "id": "download-api\u0027s;verify-user-gets-\"2004\"-javelin-code-when-invalid-task-is-provided",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "user has an invalid unique task id",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "user hits the Download API",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "verify the status code is 200",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "javelin code is 2004",
  "keyword": "And "
});
formatter.match({
  "location": "Common.user_has_an_invalid_task_id()"
});
formatter.result({
  "duration": 1103234094,
  "status": "passed"
});
formatter.match({
  "location": "Download.user_hits_the_Download_API()"
});
formatter.result({
  "duration": 3200655262,
  "status": "passed"
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
  "duration": 951385,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2004",
      "offset": 16
    }
  ],
  "location": "Common.javelinCode(int)"
});
formatter.result({
  "duration": 21325137,
  "status": "passed"
});
});