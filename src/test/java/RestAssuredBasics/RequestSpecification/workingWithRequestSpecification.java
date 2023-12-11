package RestAssuredBasics.RequestSpecification;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;


public class workingWithRequestSpecification {
    RequestSpecification reqSpec;

    //Always write RequestSpec part in @BeforeClass
    @BeforeClass
    public void beforeClass(){
        //reqSpec = given().
        //Using with()
        reqSpec = with().
                baseUri("https://cf1c7376-30d4-4a18-afdc-a8dafb2ee04a.mock.pstmn.io").
                header("header1", "hvalue1");
    }

    @Test
    public void requestSpecification(){

        given(reqSpec).
                //OR
        //given().spec(reqSpec).
        when().
                get("/GET").
        then().
                assertThat().
                statusCode(200);
    }

    //We can use with().spec() instead of given()... there is syntactical difference.
    @Test
    public void usingWith(){

        with().
                //OR
        //with().spec(reqSpec).
                //OR
                //given().spec(reqSpec).
                        when().
                get("/GET").
        then().
                assertThat().
                statusCode(200);
    }

    //Not using given/with/when.....
    @Test
    public void usingBDDtoNonBDD(){

        Response resp = reqSpec.
                get("/GET").
        then().
                log().all().
                statusCode(200).
                extract().response();
        //asserting statusCode
        assertThat(resp.statusCode(), equalTo(200));

        //asserting responseBody
        assertThat(resp.path("msg"), equalTo("success"));

    }
}
