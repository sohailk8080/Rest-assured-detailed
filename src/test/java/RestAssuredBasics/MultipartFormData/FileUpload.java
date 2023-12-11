package RestAssuredBasics.MultipartFormData;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class FileUpload {
    @Test
    public void fileUpload(){
        String attribute = "{\"name\":\"temp.txt\",\"parent\":{\"id\":\"123456\"}}";
        given().
                baseUri("https://postman-echo.com").
                multiPart("file", new File("src/main/resources/temp.txt")).
                multiPart("attributes", attribute, "application/json").
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);

    }

}
