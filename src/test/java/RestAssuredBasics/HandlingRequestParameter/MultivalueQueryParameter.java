package RestAssuredBasics.HandlingRequestParameter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MultivalueQueryParameter {
    @Test
    public void multiValueQueryParameter(){
        given().
                baseUri("https://postman-echo.com").
                //param("foo1", "bar1", "bar2").
                queryParam("foo1", "bar1", "bar 2").      //space in bar 2 = %20..follow notes for more..
        when().
                get("/get").
        then().
                log().all();

    }

}
