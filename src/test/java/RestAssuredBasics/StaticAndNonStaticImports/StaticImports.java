package RestAssuredBasics.StaticAndNonStaticImports;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//Requires class names to be used inside the Test case(this is taken care by static imports)
public class StaticImports {

    @Test
    public void noStaticImportsMethod(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                body("workspaces[0].name", equalTo("My Workspace"));

    }


}
