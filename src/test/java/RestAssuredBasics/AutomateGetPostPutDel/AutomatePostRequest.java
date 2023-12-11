package RestAssuredBasics.AutomateGetPostPutDel;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AutomatePostRequest {

    RequestSpecBuilder reqSpecB = new RequestSpecBuilder();
    ResponseSpecBuilder respSpecB = new ResponseSpecBuilder();

    @BeforeClass
    public void beforeClass(){

        reqSpecB.setBaseUri("https://cf1c7376-30d4-4a18-afdc-a8dafb2ee04a.mock.pstmn.io");
        reqSpecB.addHeader("reqHeader", "reqValue");
        //reqSpecB.addHeader("x-mock-match-request-body", "reqHeader");
        reqSpecB.log(LogDetail.ALL);
        RestAssured.requestSpecification = reqSpecB.build();

        respSpecB.expectStatusCode(200);
        respSpecB.expectHeader("respHeader","respValue");
        reqSpecB.log(LogDetail.ALL);
        RestAssured.responseSpecification = respSpecB.build();
    }

    @Test
    public void postBddStyle(){
        String payload = "{\n" +
                "    \"name\":\"sohail\",\n" +
                "    \"designation\":\"SDET\"\n" +
                "}";


        given().
                body(payload).
        when().
                post("/post").
        then().
                assertThat().
                body("msg", equalTo("successful"));

    }

    @Test
    public void postNonBDD(){
        String payload = "{\n" +
                "    \"name\":\"sohail\",\n" +
                "    \"designation\":\"SDET\"\n" +
                "}";

        Response response = with().
                body(payload).
                post("/post");
                assertThat(response.path("msg"), equalTo("successful"));

    }
}
