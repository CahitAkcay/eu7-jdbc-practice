package Day6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://100.26.60.119:8000/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //JSON to POJO --> de serialize to java custom class
        //JSON to Our Spartan class object
        Spartan spartan15 = response.body().as(Spartan.class);

        System.out.println(spartan15);
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //assertion
        assertEquals(spartan15.getName(),"Meta");
        assertEquals(spartan15.getId(),15);
    }
    @Test
    public void regionToPojo(){

        Response response = when().get("http://100.26.60.119:1000/ords/hr/regions");
         assertEquals(response.statusCode(),200);

        //JSON to POJO(regions class)




    }
}
