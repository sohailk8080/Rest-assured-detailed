package RestAssuredBasics.RequestSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecificationBuilder {
    RequestSpecBuilder reqSpecB;
    //RequestSpecification reqSpecification;

    //Using Default req res specification approach
    ResponseSpecBuilder respSpecB;
    @BeforeClass
    public void beforeClass(){
        reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://api.postman.com");
        reqSpecB.addHeader("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8");
        reqSpecB.log(LogDetail.ALL);
        //reqSpecification = reqSpecB.build();

        //Default request specification [commented 12, 20 for this]
        RestAssured.requestSpecification = reqSpecB.build();

        respSpecB = new ResponseSpecBuilder().
            expectStatusCode(200).
            expectHeader("Content-Type","application/json; charset=utf-8").
            log(LogDetail.ALL);
        RestAssured.responseSpecification = respSpecB.build();
    }

    @Test
    public void requestSpecificationBuilder(){
        //RestAssured.given(reqSpecification).get("/workspaces").then().log().all();

        //For Default Request specification we don't need the given and then
        get("/workspaces");

    }
    @Test
    public void validateResponseBody(){
            Response resp = get("/workspaces").
        then().
               body("workspaces[1].name", equalTo("Nov2022")).
               extract().response();
            //also
               assertThat(resp.path("workspaces[2].id").toString(), equalTo("b1458709-33f8-4b05-a41c-3c6619d8f034"));

    }

}
