package resources;

//enum is a special class is java which has a collection of constants and methods
public enum APIResources {

	AddPlaceApi("maps/api/place/add/json"),
	GetPlaceApi("maps/api/place/get/json"),
	DeletePlaceApi("maps/api/place/delete/json");

	public String resource;
	APIResources(String resource) {
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
