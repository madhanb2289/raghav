$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("MyProfileSearch.feature");
formatter.feature({
  "line": 2,
  "name": "Facebook search validations",
  "description": "",
  "id": "facebook-search-validations",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@CucumberTesting"
    }
  ]
});
formatter.scenario({
  "line": 3,
  "name": "Facebook search positive scenario",
  "description": "",
  "id": "facebook-search-validations;facebook-search-positive-scenario",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "open chrome browser and enter url",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Enter search criteria",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "click on search button",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});