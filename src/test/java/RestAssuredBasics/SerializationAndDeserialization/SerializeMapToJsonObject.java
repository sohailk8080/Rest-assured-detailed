package RestAssuredBasics.SerializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SerializeMapToJsonObject {
    RequestSpecBuilder reqSpecB;
    //RequestSpecification reqSpecification;

    //Using Default req res specification approach
    ResponseSpecBuilder respSpecB;
    ResponseSpecification respSpec;
    ObjectMapper objMapper;
    @BeforeClass
    public void beforeClass(){
        reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://api.postman.com");
        reqSpecB.addHeader("X-Api-Key",
                "PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8");
        reqSpecB.setContentType("application/json; charset=utf-8");
        reqSpecB.log(LogDetail.ALL);
        //reqSpecification = reqSpecB.build();

        //Default request specification [commented 12, 20 for this]
        RestAssured.requestSpecification = reqSpecB.build();

        respSpecB = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectHeader("Content-Type","application/json; charset=utf-8").
                log(LogDetail.ALL);
        respSpec = respSpecB.build();
    }

    @Test
    public void serializeMapToJson() throws JsonProcessingException {
        //[refer the json for this in the package]
        HashMap<String, Object> mainObj = new HashMap<>();

        HashMap<String, Object> nestedObj = new HashMap<>();
        nestedObj.put("name", "My Workspace 4");
        nestedObj.put("type", "personal");
        nestedObj.put("description", "REST-assured created this!");

        mainObj.put("workspace", nestedObj);


        objMapper = new ObjectMapper();
        String strObjMapper = objMapper.writeValueAsString(mainObj);

        given().
                body(strObjMapper).
                when().
                post("/workspaces").
                //get("/workspaces").
                        then().spec(respSpec).
                log().all().
                assertThat().
                body("workspace.name", equalTo("My Workspace 4"));

    }


}