package in.ibm.com.RestAssur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity2 
{

  String root_uri = "https://petstore.swagger.io/v2/user";
	
  @Test(priority = 1, enabled = true)
  public void postMethTest() throws IOException 
  {
	   FileInputStream inputJson = new FileInputStream("C:\\Users\\09089O744\\Desktop\\IBM_FST_ew\\RestAssured\\RestAssur\\file1.json");
	   
	   String reqbody = new String(inputJson.readAllBytes());
	   
	   Response response = given().contentType(ContentType.JSON) // Set headers
	            		          .body(reqbody) // Pass request body from file
	            		          .when()
	            		          .post(root_uri); // Send POST request
	 
	   inputJson.close();
	   
	   response.then().body("code", equalTo(200));
       response.then().body("message", equalTo("MalluVB"));
  }
  
  @Test(priority = 2, enabled = true)
  public void GetMethTest() 
  {
	  File outputJSON = new File("C:\\Users\\09089O744\\Desktop\\IBM_FST_ew\\RestAssured\\RestAssur\\Response.json");
	  
      Response response = given().contentType(ContentType.JSON) // Set headers
    		  					 .pathParam("username", "MalluVB") // Pass request body from file
    		  					 .when()
    		  					 .get(root_uri + "/{username}"); // Send POST request
      
     
      String resBody = response.getBody().asPrettyString();

      try 
      {
          outputJSON.createNewFile();
         
          FileWriter writer = new FileWriter(outputJSON.getPath());
          writer.write(resBody);
          writer.close();
      }
      catch (IOException excp) 
      {
          excp.printStackTrace();
      }
      
    
      response.then().body("id", equalTo(7189));
      response.then().body("username", equalTo("MalluVB"));
      response.then().body("firstName", equalTo("Justin"));
      response.then().body("lastName", equalTo("Case"));
      response.then().body("email", equalTo("justincase@mail.com"));
      response.then().body("password", equalTo("password123"));
      response.then().body("phone", equalTo("9812763450"));
  }
  
  @Test(priority = 3, enabled = true)
  public void DelMethTest() 
  {
	  Response response = given().contentType(ContentType.JSON) 
	           					 .pathParam("username", "MalluVB") 
	           					 .when()
	           					 .delete(root_uri + "/{username}"); 
	 
	        
	        response.then().body("code", equalTo(200));
	        response.then().body("message", equalTo("MalluVB"));
    
  }

  

  
}
