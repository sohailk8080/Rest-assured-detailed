package RestAssuredBasics.SendingRequestPayload_Ways;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class PassingComplexJSON {
    ResponseSpecification resSpec;
    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://088e6ac3-801f-4213-83a3-f550753c81f4.mock.pstmn.io");
        reqSpecB.addHeader("Content-Type","application/json; charset=utf-8");
        reqSpecB.log(LogDetail.ALL);
        requestSpecification = reqSpecB.build();

        ResponseSpecBuilder resSpecB = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectHeader("Content-Type","application/json; charset=utf-8").
                log(LogDetail.ALL);
        resSpec = resSpecB.build();
    }

    @Test
    public void passComplexJson(){
        List<Object> rgbaList1 = new ArrayList<>();
        rgbaList1.add(255);
        rgbaList1.add(255);
        rgbaList1.add(255);
        rgbaList1.add(1);

        Map<String, Object> codeMap1 = new HashMap<>();
        codeMap1.put("rgba", rgbaList1);
        codeMap1.put("hex", "#000");

        Map<String, Object> coloursHM1 = new HashMap<>();
        coloursHM1.put("color","black");
        coloursHM1.put("category","hue");
        coloursHM1.put("type","primary");
        coloursHM1.put("code",codeMap1);

        List<Object> rgbaList2 = new ArrayList<>();
        rgbaList2.add(0);
        rgbaList2.add(0);
        rgbaList2.add(0);
        rgbaList2.add(1);

        Map<String, Object> codeMap2 = new HashMap<>();
        codeMap2.put("rgba", rgbaList1);
        codeMap2.put("hex", "#FFF");

        Map<String, Object> coloursHM2 = new HashMap<>();
        coloursHM2.put("color","white");
        coloursHM2.put("category","value");
        coloursHM2.put("code",codeMap2);

        List<Map<String, Object>> coloursList = new ArrayList<>();
        coloursList.add(coloursHM1);
        coloursList.add(coloursHM2);

        Map<String, List<Map<String, Object>>> coloursHM = new HashMap<>();
        coloursHM.put("colors", coloursList);

        given().
                body(coloursHM).
                when().
                post("/post").
                then().spec(resSpec).
                assertThat().
                body("msg", equalTo("success"));
    }

}

