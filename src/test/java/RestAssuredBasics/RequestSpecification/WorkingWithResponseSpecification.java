package RestAssuredBasics.RequestSpecification;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

//import static io.restassured.RestAssured.*;

public class WorkingWithResponseSpecification {

    RequestSpecification reqSpec;
  //Declaring ResponseSpecification
    //ResponseSpecification respSpec;

    //Always write RequestSpec part in @BeforeClass
    @BeforeClass
    public void beforeClass(){
        reqSpec = given().
                baseUri("https://cf1c7376-30d4-4a18-afdc-a8dafb2ee04a.mock.pstmn.io").
                header("header1", "hvalue1");

        //respSpec = expect().
      //[Commenting 15,24,45 for default responseSpecification
        RestAssured.responseSpecification = expect().
                statusCode(200).
                contentType(ContentType.JSON).
                log().all();
        //log.all doesn't work for custom respSpec....it works for default

    }

    @Test
    public void responseSpecification(){

        given(reqSpec).
        when().
                get("/GET");
    //then is not overloaded, spec is. passing the respSpec variable inside then().spec()
        //then().spec(respSpec);

    }

}
