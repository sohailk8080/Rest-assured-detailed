package RestAssuredBasics.SendingRequestPayload_Ways;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SendRequestPayload_File {
    //File: is not suitable for multiple variations
    //src>>main>>resources>file.json

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
    public void fileAsBody(){
       File file = new File("src/main/resources/file.json");
        given().
                body(file).
                when().
                post("/post").
                then().
                assertThat().
                body("msg", equalTo("successful"));

    }

}
