package RestAssuredBasics.FIlters;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class LogToFile {
    @Test
    public void logToFile() throws FileNotFoundException {
     //Using Print stream [creates a new file]
        PrintStream ps = new PrintStream(new File("RA.log"));

        given().
                baseUri("https://postman-echo.com").
                filter(new RequestLoggingFilter(ps)).
                filter(new ResponseLoggingFilter(ps)).
        when().
                get("/get").
        then().
                assertThat().
                statusCode(200);
    }
//File gets saved in the target folder

}
