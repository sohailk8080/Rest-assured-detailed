package RestAssuredBasics.AutomateGetPostPutDel;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AutomatePutRequest {
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
    public void putRequest(){
        String payload = "{\n" +
                "    \"name\": \"sohail Khan\",\n" +
                "    \"designation\": \"Sr.SDET\"\n" +
                "}";
        //Passing path parameter using variable
        String pathPar = "8080692002";


        given().
                body(payload).
            //for passing path parameter through variable
                pathParam("pathParameter", pathPar).
        when().
                //put("/put/8080692002").
                put("/put/{pathParameter}").   //[Commenting 47 for this]
        then().
                assertThat().
                body("msg", equalTo("successful"),
                    "id", equalTo(pathPar)
                );

    }
}
