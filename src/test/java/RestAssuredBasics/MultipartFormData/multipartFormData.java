package RestAssuredBasics.MultipartFormData;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class multipartFormData {
    @Test
    public void multiPartFormData(){
        given().
                baseUri("https://postman-echo.com").
                //[In Postman: body type= x-www-form-urlencoded]
                multiPart("foo1", "bar1").
                multiPart("foo2", "bar2").
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);

    }

}
