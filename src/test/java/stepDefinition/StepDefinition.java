package stepDefinition;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;


public class StepDefinition extends Utils{
	RequestSpecification res ;
	ResponseSpecification resp;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id="";
	
	@Given("^Add Place payload with \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void add_Place_payload_with_and(String name, String language, String address) throws Throwable {
		res = given().spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));
	}

	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
		APIResources resourceApi = APIResources.valueOf(resource);
		System.out.println(resourceApi.getResource());
		
		resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		if (method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceApi.getResource());
		else if (method.equalsIgnoreCase("GET"))
		response = res.when().post(resourceApi.getResource());	
	}

	@Then("^the API call is success is with status code (\\d+)$")
	public void the_API_call_is_success_is_with_status_code(int arg1) throws Throwable {
	    assertEquals(200,response.statusCode());
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String keyValue, String expectedValue) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(getJsonPath(response,keyValue).toString(), expectedValue);
	}
	
	@Then("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws Throwable {
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}

	@Given("^Delete place payload$")
	public void delete_place_payload() throws Throwable {
		res = given().spec(requestSpecification())
				.body(data.deletePlacePayload(place_id));
	}

}
