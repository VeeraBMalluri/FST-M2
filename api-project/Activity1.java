package project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Activity1
{
	 RequestSpecification requestSpec;
	 ResponseSpecification responseSpec;
	 
	 String key = "";
	 int KeyId;

	 @BeforeClass 
	 
	 public void setUp() 
	 {
	        
	        requestSpec = new RequestSpecBuilder()
	        				.setContentType(ContentType.JSON)
	        				.addHeader("Authorization", "token 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")
	        				.setBaseUri("https://api.github.com")
	        				.build();
 
	  }
	 
	 @Test(priority =0)
	 public void PostFun() 
  
	 {
		 String reqBody = "{\"title\": \"VeerAPIKey\",\"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCqv0gS4Pr2HwABijxMLz/08WCBfe+kDtmE51G6POxpJpTWSJmHeW/3hHe8dCF1EYDinTPUPYtTcxCUMBlL82d4a01eqYVvRtCJUOkkjMbO2Kgi3pJkviJXxI5+V/30rZH8o1Jt2b7axH6Pisk0YhqheDlw0sd424sa6ks5j5VM/iE3ZcC+Ef0xSfK55hX9uH4tE0BB0NoKvc0yfMcXhYW4vp+2zbMrSv75/dBBDgCGan3E+EidDLxlq71im1Jk6IvOkjzWvHN5Owp1i37/bCBAMPoiay/Bj14ipZlG5NwX+yO8pylnm41tqHaeDjjbIfV1cWdIalMEQO1DWqVh2H49mspmLz8+IKmpVkzRynAqEN2iNC43cIOgT9oxeYABgXJ0iscEWrn5EO6PiIef1mHgVV1nSW1vfN7ALLouPxKEsciWYtSo1Pr5LXtQf4R4DzUCT3/3XdrcdIdWFvwO5AlK8iZPOdGa40zYlmwCCCLOGUMoeespvKZJv1jU+NyLMrs= GMX+09089O744@DESKTOP-PHEBPI6\"}";
		 
		 Response response = given().spec(requestSpec) 
				   					.body(reqBody) 
				   					.when().post("/user/keys");
		 
		 String resBody = response.getBody().asString();
		 System.out.println(resBody);
		
		 response.then().statusCode(201);
		 KeyId = response.then().extract().path("id");
		 System.out.println(KeyId);
	 }
	 
	 @Test(priority=1)
	 public void GetFun()
	 {
		 Response response = given()
				 					.spec(requestSpec) 
									.when()
									.get("/user/keys");
		 
		 
		 String resBody = response.getBody().asPrettyString();
		 System.out.println("Response Body is : " +resBody);
		 Reporter.log("Response Body is : " +resBody);
		 response.then().statusCode(200);
		 response.then().body("[0].title", equalTo("VeerAPIKey"));
	 }
	 
	 @Test(priority=2)
	 public void DeleteFun()
	 {
		 System.out.println(KeyId);
		 Response response = given()
					.spec(requestSpec)
					.pathParam("id", KeyId)
					.when().delete("/user/keys/{id}");
		 
		 String resBody = response.getBody().asPrettyString();
		 System.out.println("Response Body is : " +resBody);
		 Reporter.log("Response Body is : " +resBody);
		 response.then().statusCode(204);
		 
	 }
	 
}
