package RestAssuredBasics.JsonSchemaValidation;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class jsonSchemaValidation {
    @Test
    public void jsonSchemaVal() {
        given().
                baseUri("https://postman-echo.com").
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("file2.json"));
        //the test case fails because, the response has id fields that are dynamic, so remove them and examples as well

        //to check if the jsonSchemaValidation is working fine, change the type of any value
        // from string to object or anything or vice versa in the json file, this gives the actual and expected
    }
}
