package RestAssuredBasics.SerializationAndDeserialization.PojoTestClasses;

import com.RestAssured.Pojo.WorkspacePojo;
import com.RestAssured.Pojo.WorkspaceRootPojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PassingValuesDataProvider {
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

    @Test(dataProvider = "workspace")
    public void DataProviderWorkspaceTest(String name, String type, String description) throws JsonProcessingException {
        WorkspacePojo workspace = new WorkspacePojo(name, type, description);
        WorkspaceRootPojo rootPojo = new WorkspaceRootPojo(workspace);
        WorkspaceRootPojo deserializeRootP = given().
                body(rootPojo).
        when().
                post("/workspaces").
        then().spec(respSpec).
                extract().response().
                as(WorkspaceRootPojo.class);
        assertThat(deserializeRootP.getWorkspace().getName(), equalTo(rootPojo.getWorkspace().getName()));
    }
    @DataProvider(name="workspace")
    public Object[][] getWorkspace(){
        return new Object[][]{
                {"myWS1", "personal", "TestNG created this"}
        };

    }
}
