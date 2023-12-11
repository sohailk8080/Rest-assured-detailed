package RestAssuredBasics.HandlingRequestParameter;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MultipleQueryParameter {
    @Test
    public void multipleQueryParameter(){
        given().
                baseUri("https://postman-echo.com").
                //param("foo1", "bar1").
                //queryParam("foo1", "bar1").
                //queryParam("foo2", "bar2").
                //OR
                queryParams("foo1", "bar1",
                "foo2", "bar2"
                            ).
             //You can also create HashMap object and pass the reference in queryParams
        when().
                get("/get").
        then().
                log().all();

    }

}