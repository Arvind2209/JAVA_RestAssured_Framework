package Practise;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetMethod {

    @Test
    public void validateGetStatus(){

        Response response =given()
                .baseUri("https://reqres.in/")
                .when()
                .log().all()
                .get("api/users?page=2")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();


        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println(response.asString());
        System.out.println(jsonPath.getString("data[0].first_name"));
        Assert.assertEquals(jsonPath.getString("data[0].first_name"),"Michael");
    }
}
