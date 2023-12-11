package RestAssuredBasics.HandlingRequestParameter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SingleQueryParameter {
    //Postman-echo was introduced

    @Test
    public void singleQueryParameter(){
        given().
                baseUri("https://postman-echo.com").
                param("foo1", "bar1").
                //queryParam("foo1", "bar1").
        when().
                get("/get").
        then().
                log().all();

    }

}
