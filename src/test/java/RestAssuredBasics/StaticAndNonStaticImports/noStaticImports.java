package RestAssuredBasics.StaticAndNonStaticImports;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

//Requires class names to be used inside the Test case(this is taken care by static imports)
public class noStaticImports {

    @Test
    public void noStaticImportsMethod(){
        RestAssured.given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
        when().
                get("/workspaces").
        then().
                assertThat().
                statusCode(200).
                body("workspaces[0].name", Matchers.equalTo("My Workspace"));

    }


}
