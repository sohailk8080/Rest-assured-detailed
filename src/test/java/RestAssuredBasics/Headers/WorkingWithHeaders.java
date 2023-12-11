package RestAssuredBasics.Headers;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class WorkingWithHeaders {

    @Test
    public void workingWithHeaders(){
        //2nd way: Using Header class
        Header header1 = new Header("header1","hvalue1");
        Header header2 = new Header("x-mock-match-request-body", "header1");

        //3rd way: Using Header's class
        Headers headers = new Headers(header1, header2);

        //4th way: Using Map
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("header1","hvalue1");

        //1st way For MultiValueHeader
        Header multiValHeader1 = new Header("multivalueHeader", "mulVal1");
        Header multiValHeader2 = new Header("multivalueHeader", "mulVal2");
        Headers multiValHeaders = new Headers(multiValHeader1, multiValHeader2);

        Headers headerExtract = given().
                baseUri("https://cf1c7376-30d4-4a18-afdc-a8dafb2ee04a.mock.pstmn.io").
            //1st way: multiple headers in the request
                //header("header1", "hvalue1").
                //header("x-mock-match-request-body", "header1").
            //2nd Contd.
                //header(header1).
                //header(header2).
            //3rd Contd.
                //headers(headers).
            //4th Contd.
                headers(headersMap).
            //1st way Contd. MultiValueHeader
                //headers(multiValHeaders).
            //2nd way MultiValueHeader
                header("multivalueHeader","mulVal1", "mulVal2").

        when().
                get("/GET").
        then().
                assertThat().
                statusCode(200).
            //assert Header response
                //header("respHeader1", "respValue1").
                //headers("Content-Type", "application/json; charset=utf-8","Connection", "keep-alive");
            //extract response header
                extract().headers();
            System.out.println("Header name is::"+headerExtract.get("multiValueHeader").getName()+" "
                +"Value is::"+headerExtract.getValue("multiValueHeader"));
            //extracting all values
            for (Header headerLoop: headerExtract ){
                System.out.println("Response headers name::"+headerLoop.getName()
                        +" and Value is::"+headerLoop.getValue());
            }

            //We can also extract multi value response header by storing it in a List
            List<String> mulValues = headerExtract.getValues("multiValueHeader");
            for (String value: mulValues){
                System.out.println("Also MultivalueHeader::"+value);
            }


    }
}
