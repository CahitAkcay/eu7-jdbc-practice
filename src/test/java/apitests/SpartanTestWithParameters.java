package apitests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import  static org.testng.Assert.*;

public class SpartanTestWithParameters {
    @BeforeClass
    public void beforeclass(){
        baseURI = "http://100.26.60.119:8000";
    }
    /*
         Given accept type is Json
         And Id parameter value is 5
         When user sends GET request to /api/spartans/{id}
         Then response status code should be 200
         And response content-type: application/json;charset=UTF-8
         And "Blythe" should be in response payload
      */
    @Test
    public void getSpartanID_Positive_PathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Blythe"));
    }
     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json;charset=UTF-8
        And "Spartan Not Found" message should be in response payload
     */
    @Test
    public void getSpartanID_Negative_PathParam(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get(" /api/spartans/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));
    }
      /*
        Given accept type is Json
        And query parameter values are :
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json;charset=UTF-8
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @Test
    public void positiveTestWithQueryParam(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam(" gender", "Female")
                .and().queryParam(" nameContains", "e")
                .when().get("/api/spartans/search");

        //verifying all the requested tasked
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("janette"));

    }
    @Test
    public void positiveTestWithQueryParamWithMaps(){
        //create a map and add query parameters
        Map<String,Object> querymap = new HashMap<>();
        querymap.put("gender","Female");
        querymap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(querymap)
                .when().get("/api/spartans/search");

        //verifying all the requested tasked
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("janette"));

    }
}
