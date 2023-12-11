package RestAssuredBasics.HandlingRequestParameter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathParameter {

    @Test
    public void pathParameter(){
        given().
                baseUri("https://postman-echo.com").
                pathParam("id", 1).
        when().
                get("/get/{id}").
        then().
                log().all();

    }

}