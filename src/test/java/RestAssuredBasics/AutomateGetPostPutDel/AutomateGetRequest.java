package RestAssuredBasics.AutomateGetPostPutDel;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//Requires class names to be used inside the Test case(this is taken care by static imports)
public class AutomateGetRequest {

    @Test
    public void AutomateGet(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).

                //Asserting Response body
                body("workspaces[0].name", equalTo("My Workspace"),
                     "workspaces[0].name", is(equalTo("My Workspace")),
                     "workspaces.name", hasItem("MyWorkspace2"),
                     "workspaces.name", hasItems("MyWorkspace2", "My Workspace"),
                     "workspaces.size()", equalTo(3)
                );
    }
    @Test
    public void assetStatusCode(){
        System.out.println("Asserting Status code::");
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                log().all();
    }

    //Extracting entire response
    @Test
    public void extractResponse(){
        System.out.println("Extracting whole Response::");
        Response response = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                extract().response();
        System.out.println("Extracted response is::"+response.asString());
    }

    //Extracting Single response
    @Test
    public void extractSingleResponse() {
        System.out.println("Extracting single Response");
        //Response response = given().
        String respString = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                //        extract().response();
                //        extract().response().asString();
                        extract().response().path("workspaces[0].name");

        //using .path()
        //  System.out.println("1st Workspace name is::"+response.path("workspaces[0].name"));
        //Create instance of JsonPath
        //  JsonPath jsonPath = new JsonPath(response.asString());
        //  System.out.println("2nd Workspace name is::"+jsonPath.getString("workspaces[1].name"));

        //Capture the response in the String [Commented 66,75,79,81,82 for this]
        //System.out.println("3rd Workspace name is::"+JsonPath.from(respString).getString("workspaces[2].name"));
        //using path() after response() //Capture the response in the String [Commented 66,75,79,81,82,76,86 for this]
        System.out.println("1st Workspace name using path is::" + respString);
    }
        //Hamcrest Assertion on Extracted response
        @Test
        public void hamcrestAssertionOnExtractedResponse(){
            System.out.println("Hamcrest Assertion for extracted Response");
            //Response response = given().
            String respString = given().
                    baseUri("https://api.postman.com").
                    header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
            when().
                    get("/workspaces").
            then().
                    assertThat().
                    statusCode(200).
                    extract().response().path("workspaces[0].name");
                    assertThat(respString, equalTo("My Workspace"));
    }
}
