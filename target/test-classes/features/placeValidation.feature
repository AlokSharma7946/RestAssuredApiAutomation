Feature: Validating place api's

@AddPlaceApi @Regression
Scenario Outline: Verify if place being added successfully using addplace API
	Given Add Place payload with "<name>", "<language>" and "<address>"	
	When User calls "AddPlaceApi" with "Post" http request
	Then the API call is success is with status code 200
	And  "status" in response body is "OK"
	And  "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceApi"
	Examples:
	| name  | language  | address     |
	| alohya| Hindi	    | 100 Main st |
#	| alohya| English   | 101 Main st |


@DeletePlaceApi @Regression
Scenario: Verify if delete place functionality is working
	Given Delete place payload
	When User calls "DeletePlaceApi" with "Post" http request
	Then the API call is success is with status code 200
	And  "status" in response body is "OK"