package RestAssuredBasics.FIlters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Before;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;

public class ReuseFilters {

    RequestSpecification reqSpec;
    ResponseSpecification respSpec;

    @BeforeClass()
    public void beforeClass() throws FileNotFoundException {

        PrintStream ps = new PrintStream(new File("Reusing.log"));

        RequestSpecBuilder reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://postman-echo.com");
    //adding filters using reqSpecB
        reqSpecB.addFilter(new RequestLoggingFilter(ps));
        reqSpecB.addFilter(new ResponseLoggingFilter(ps));
        reqSpec = reqSpecB.build();
        //requestSpecification = reqSpecB.build();

    //Don't need to add filter in Response context
        ResponseSpecBuilder respSpecB = new ResponseSpecBuilder();
        respSpecB.expectStatusCode(200);
        respSpec = respSpecB.build();
    }

    @Test
    public void reuseFilters(){
        given(reqSpec).
                queryParam("key1", "bar1").
        when().
                get("/get").
        then().spec(respSpec).
                log().status();
    }
}
