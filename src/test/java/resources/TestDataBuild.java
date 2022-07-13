package resources;

import pojo.Location;
import pojo.PlacesAPI;

public class TestDataBuild {
	
	public PlacesAPI addPlacePayload(String name, String language, String address) {
		PlacesAPI p = new PlacesAPI();
//		List <Double> l = new ArrayList<Double>();
//		l.add(-38.383494);
//		l.add(33.427362);
		Location l = new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName(name);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		String ar [] = {"shoe park","shop"};
		p.setTypes(ar);
		return p;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\n"
				+ "    \"place_id\":\""+place_id+"\"\n"
				+ "}";
	}

}
