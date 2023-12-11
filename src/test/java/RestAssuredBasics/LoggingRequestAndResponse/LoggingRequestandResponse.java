package RestAssuredBasics.LoggingRequestAndResponse;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//Requires class names to be used inside the Test case(this is taken care by static imports)
    public class LoggingRequestandResponse {

        @Test
        public void loggingReqResp(){
            given().
                    baseUri("https://api.postman.com").
                    header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
                    //log().ifValidationFails().
                    //config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
            when().
                    get("/workspace").              //wrong endpoint to fail this test case
            then().
                //Log ifError
                    log().ifError().
                    assertThat().
                    statusCode(200).
                    body("workspaces[0].name", equalTo("My Workspace"));

        }


    }



