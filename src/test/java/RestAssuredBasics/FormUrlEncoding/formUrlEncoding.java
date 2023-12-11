package RestAssuredBasics.FormUrlEncoding;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class formUrlEncoding {
    @Test
    public void formURlEncoding(){
        given().
                baseUri("https://postman-echo.com").
           //as rest assured doesn't recognize the response data type
                config(config.encoderConfig(EncoderConfig.encoderConfig().
                        appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                formParam("foo1", "bar1").
                formParam("foo2", "bar2").
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);

    }

}
