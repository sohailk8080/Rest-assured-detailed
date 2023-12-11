package RestAssuredBasics.SerializationAndDeserialization.PojoTestClasses;

import com.RestAssured.Pojo.WorkspacePojo;
import com.RestAssured.Pojo.WorkspaceRootPojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WorkspaceTest {
    ResponseSpecification respSpec;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://api.postman.com");
        reqSpecB.addHeader("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8");
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
    public void workspaceTest() throws JsonProcessingException {

        WorkspacePojo workspace = new WorkspacePojo("My Workspace:RA","personal", "Created by Rest assured");
     //Jackson Annotation for empty Map or List (at class level or field level[POJO class])
        HashMap<String, String> hMap = new HashMap<>();
        workspace.sethMap(hMap);

        WorkspaceRootPojo rootPojo = new WorkspaceRootPojo(workspace);
        WorkspaceRootPojo deserializeRootP = given().
                body(rootPojo).
        when().
                post("/workspaces").
        then().spec(respSpec).
                extract().response().
                as(WorkspaceRootPojo.class);
        assertThat(deserializeRootP.getWorkspace().getName(), equalTo(rootPojo.getWorkspace().getName()));

     //Asserting full body
        //ObjectMapper objectMapper = new ObjectMapper();
        //String strDeserialize = objectMapper.writeValueAsString(deserializeRootP);
        //String strWorkspace = objectMapper.writeValueAsString(rootPojo);
        //assertThat(objectMapper.readTree(strWorkspace), equalTo(objectMapper.readTree(strDeserialize)));
    }
}
//2 more concepts: JsonIgnore(doesn't work when a field is available in response body)
//                 JsonIgnoreProperties(takes care of the above limitation).....refer notes