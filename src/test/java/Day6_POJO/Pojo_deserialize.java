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
        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionId());
    }
    @Test
    public void gson_example(){

        Gson gson = new Gson();

        //JSON to Java collections or Pojo --> De-serialization

        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
        Map map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData,Spartan.class);
        System.out.println(spartan15);

        //-------SERIALIZATION---------
        //JAVA collection or POJO to JSON
        Spartan spartanEu = new Spartan(200,"Mike","Male",123123123);

        String jsonSpartanEu = gson.toJson(spartanEu);
        System.out.println(jsonSpartanEu);

    }
}
