package test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAndPostExamples {

	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api/";
		
		given().
			get("https://reqres.in/api/users?page=2").
		then().
			statusCode(200).
			body("data[2].first_name", equalTo("Tobias")).
			body("data.first_name", hasItems("George","Byron"));
	}
	
	@Test
	public void testPost() {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
//		map.put("name","Gaurav");
//		map.put("job", "Automation tester");
		
//		System.out.println(map);
		JSONObject request = new JSONObject();
		request.put("name","Gaurav");
		request.put("job", "Automation tester");
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api/";
		
		given().
			header("content-type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).log().all();
		
	}
}
