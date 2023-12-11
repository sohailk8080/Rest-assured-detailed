package RestAssuredBasics.SerializationAndDeserialization.PojoTestClasses;

import com.RestAssured.Pojo.SimplePojo;
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
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoSerTest {
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
    public void simplePojoTest(){
        //SimplePojoSerialization sps = new SimplePojoSerialization("Val1", "Val1");
        SimplePojo spsD = new SimplePojo();
        given().
                body(spsD).
        when().
                post("/postSimpleJSON").
        then().spec(respSpec).
                assertThat().
                body("Key1", equalTo(spsD.getKey1()));

    }
}
