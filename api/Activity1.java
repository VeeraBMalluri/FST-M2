package in.ibm.com.RestAssur;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity1 
{
	String ROOT_URI = "https://petstore.swagger.io/v2/pet";
	
 @Test(priority=1)
  public void postMeth() 
  {
	 
	 String reqBody = "{" + "\"id\": 76547,"
			 			  + "\"name\": \"Tongo\"," 
			 			  + "\"status\": \"alive\" "
			 			  +"}";
	 
	 Response response = given()
			 			.contentType(ContentType.JSON)
			 			.body(reqBody)
			 			.when().post(ROOT_URI);
	 
	 response.then().body("id", equalTo(76547));
	 response.then().body("name", equalTo("Tongo"));
     response.then().body("status", equalTo("alive"));
	  
  }
 
 @Test(priority=2)
 public void GetMeth() 
 {
	 Response response =  given()
			 					.contentType(ContentType.JSON) 
			 					.when()
			 					.pathParam("petId", "76547")
			 					.get(ROOT_URI + "/{petId}"); 
	 
	 
	        response.then().body("id", equalTo(76547));
	        response.then().body("name", equalTo("Tongo"));
	        response.then().body("status", equalTo("alive"));
 }
 
 @Test(priority=3)
 public void DelMeth() 
 {
	 Response response = given().contentType(ContentType.JSON) 
			 					.when()
			 					.pathParam("petId", "76547") 
			 					.delete(ROOT_URI + "/{petId}"); 
	 
	        
	        response.then().body("code", equalTo(200));
	        response.then().body("message", equalTo("76547"));
	 	 
 }
 
}
