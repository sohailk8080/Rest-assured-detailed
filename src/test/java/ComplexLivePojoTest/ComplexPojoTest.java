package ComplexLivePojoTest;

import ComplexLivePojo.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class ComplexPojoTest {
    ResponseSpecification respSpec;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder reqSpecB = new RequestSpecBuilder();
        reqSpecB.setBaseUri("https://api.getpostman.com");
        reqSpecB.addHeader("X-Api-Key","PMAK-638c7bd1059954005236efde-790498a5968d1527bb47af5e31870d4f66");
        reqSpecB.setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
        reqSpecB.log(LogDetail.ALL);
        RestAssured.requestSpecification = reqSpecB.build();

        ResponseSpecBuilder respSpecB = new ResponseSpecBuilder();
        respSpecB.expectStatusCode(200);
        respSpecB.expectContentType("application/json;charset=utf-8");
        reqSpecB.log(LogDetail.ALL);
        respSpec = respSpecB.build();
    }

    @Test
    public void complexPojoTest(){

        Header header = new Header("Content-Type","application/json");
        List<Header> headerList = new ArrayList<>();
        headerList.add(header);

        Body body = new Body("raw", "{\"data\":\"123\"}");

        Request request = new Request("https://postman-echo.com/post", "POST", headerList,
                body, "This is just a sample POST Request");
        Item item = new Item("Sample Post Request", request);

        List<Item> itemsList = new ArrayList<>();
        itemsList.add(item);

        Folder folder = new Folder("This is a Sample folder", itemsList);
        List<Folder> folderList = new ArrayList<>();
        folderList.add(folder);

        Info info = new Info("Sample collection 2", "This is just a sample collection",
                "https://schema.getpostman.com/json/collection/v2.1.0/collection.json");

        CollectionRoot collectionroot = new CollectionRoot(info, folderList);
        Collection collection = new Collection(collectionroot);

        //String collectionUID = given().
        Collection deserialize = given().
                body(collection).
        when().
                post("/collections").
        then().spec(respSpec).
                //extract().response().path("collection.uid");
                extract().response().
                as(Collection.class);
        //System.out.println("UID is::"+collectionUID);
    }

}
