package RestAssuredBasics.SerializationAndDeserialization.PojoTestClasses;

import com.RestAssured.Pojo.SimplePojo;
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

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoDesTest {
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
    public void simplePojoTest() throws JsonProcessingException {
        SimplePojo sps = new SimplePojo("val1", "val2");
        //SimplePojo spsD = new SimplePojo();
        SimplePojo deserialize = given().
                body(sps).
        when().
                post("/postSimpleJSON").
        then().spec(respSpec).
            //for asserting serialized response
                //assertThat().
                //body("Key1", equalTo(spsD.getKey1()));
                extract().response().
                as(SimplePojo.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String deserStrObjMapr = objectMapper.writeValueAsString(deserialize);
        String StrObjMapper = objectMapper.writeValueAsString(sps);
        assertThat(objectMapper.readTree(deserStrObjMapr), equalTo(objectMapper.readTree(StrObjMapper)));

    }
}

