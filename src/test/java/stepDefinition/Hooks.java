package stepDefinition;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlaceApi")
	public void beforeScenario() throws Throwable{
		//execute this code only when place_id is null
		
		StepDefinition m = new StepDefinition();
		if (StepDefinition.place_id=="") {
		m.add_Place_payload_with_and("Alok", "Hindi", "100 MAIN ST");
		m.user_calls_with_http_request("AddPlaceApi", "POST");
		m.verify_place_Id_created_maps_to_using("Alok","GetPlaceApi");
		}
	}
}