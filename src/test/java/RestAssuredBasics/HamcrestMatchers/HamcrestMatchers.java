package RestAssuredBasics.HamcrestMatchers;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class HamcrestMatchers {
    @Test
    public void hamcrestMatchers(){
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200)
             //   .
        //contains : Strict, passes only when order is maintained
               //body("workspaces.name", contains("My Workspace","Nov2022","MyWorkspace2")
        //containsInAnyOrder: Passes in any order
               // body("workspaces[0].name", containsInAnyOrder("My Workspace", "MyWorkspace2", "Nov2022")
        //empty() : Passes when collection is empty
                //body("workspaces[0].name", empty()
        //is(not(empty()) : Passes when collection is not empty
                //body("workspaces[0].name", is(not(empty()))
        //hasSize()
                //body("workspaces.name", hasSize(3)
        //everyItem(startsWith()) : Pass when all the entries start with "My"
                //body("workspaces.name", everyItem(startsWith("My"))
        //hasKey()
                //body("workspaces[0]", hasKey("name")
        //hasValue()
                //body("workspaces[0]", hasValue("personal")
        //hasEntry()
                //body("workspaces[0]", hasEntry("id", "c2a18590-fc43-44db-8f84-224766bc32d0")
        //EMPTY_MAP
                //body("workspaces[0]", equalTo(Collections.EMPTY_MAP)
            //not(equalTo):
                //body("workspaces[0]", not(equalTo(Collections.EMPTY_MAP))
        //allOf()
                //body("workspaces[0].name", allOf(startsWith("My")), containsString("Workspace")
        //anyOf()
                //body("workspaces[0].name", anyOf(startsWith("id")), containsString("Workspace")
        //       )
        ;

    }


}
