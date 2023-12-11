package RestAssuredBasics.RequestSpecification;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RequestSpecificationBuilder {
    RequestSpecBuilder reqSpecB;
    //RequestSpecification reqSpecification;

    @BeforeClass
    public void beforeClass(){
        reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://api.postman.com");
        reqSpecB.addHeader("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8");
        reqSpecB.log(LogDetail.ALL);
        //reqSpecification = reqSpecB.build();

     //Default request specification [commented 12, 20 for this]
        RestAssured.requestSpecification = reqSpecB.build();

    }

    @Test
    public void requestSpecificationBuilder(){
        //RestAssured.given(reqSpecification).get("/workspaces").then().log().all();

     //For Default Request specification we don't need the given part
        get("/workspaces").then().log().all();

    }

  //Query Request Specification
    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println("Base URI::"+queryableRequestSpecification.getBaseUri());
        System.out.println("Headers:-"+"\n"+queryableRequestSpecification.getHeaders());

    }
}
