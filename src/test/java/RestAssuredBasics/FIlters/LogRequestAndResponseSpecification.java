package RestAssuredBasics.FIlters;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogRequestAndResponseSpecification {
    @Test
    public void logReqRespSpec(){

        given().
                baseUri("https://postman-echo.com").
            //These 2 are like log().all(), prints everything
                //filter(new RequestLoggingFilter()).
                //filter(new ResponseLoggingFilter()).
            //To use specific filters,
                filter(new RequestLoggingFilter((LogDetail.URI))).
                filter(new ResponseLoggingFilter(LogDetail.BODY)).

        when().
                get("/get").
        then().
                assertThat().
                statusCode(200);

    }

}
