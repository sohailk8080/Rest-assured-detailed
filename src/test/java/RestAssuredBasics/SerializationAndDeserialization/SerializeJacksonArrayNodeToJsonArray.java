package RestAssuredBasics.SerializationAndDeserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

public class SerializeJacksonArrayNodeToJsonArray {
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

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode nestedObj1 = objectMapper.createObjectNode();
        nestedObj1.put("id", "5001");
        nestedObj1.put("type", "None");

        ObjectNode nestedObj2 = objectMapper.createObjectNode();
        nestedObj2.put("id", "5002");
        nestedObj2.put("type", "Glazed");

        ArrayNode mainList = objectMapper.createArrayNode();
        mainList.add(nestedObj1);
        mainList.add(nestedObj2);

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