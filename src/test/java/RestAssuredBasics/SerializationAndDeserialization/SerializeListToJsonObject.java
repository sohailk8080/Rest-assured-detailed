package RestAssuredBasics.SerializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SerializeListToJsonObject {
    ResponseSpecification respSpec;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://cf1c7376-30d4-4a18-afdc-a8dafb2ee04a.mock.pstmn.io");
        reqSpecB.setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        reqSpecB.log(LogDetail.ALL);
        RestAssured.requestSpecification = reqSpecB.build();

        ResponseSpecBuilder respSpecB = new ResponseSpecBuilder();
        respSpecB.expectStatusCode(200);
        respSpecB.expectContentType("application/json;charset=utf-8");
        reqSpecB.log(LogDetail.ALL);
        respSpec = respSpecB.build();
    }

    @Test
    public void serializeListToJson() throws JsonProcessingException {
        HashMap<String, String> nestedObj1 = new HashMap<>();
        nestedObj1.put("id", "5001");
        nestedObj1.put("type", "None");

        HashMap<String, String> nestedObj2 = new HashMap<>();
        nestedObj2.put("id", "5002");
        nestedObj2.put("type", "Glazed");

        List<HashMap<String, String>> mainList = new ArrayList<>();
        mainList.add(nestedObj1);
        mainList.add(nestedObj2);

        ObjectMapper objectMapper = new ObjectMapper();
        String strObjMapper = objectMapper.writeValueAsString(mainList);
     //writeValueAsString() throws JsonProcessingException

        given().
                body(strObjMapper).
                when().
                post("/postJson").
                then().spec(respSpec).
                log().all().
                assertThat().
                body("msg", equalTo("success"));

    }
}
