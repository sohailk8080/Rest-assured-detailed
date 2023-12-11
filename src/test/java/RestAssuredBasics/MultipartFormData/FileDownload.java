package RestAssuredBasics.MultipartFormData;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class FileDownload {
    @Test(enabled = false)
    public void fileDownload() throws IOException {
        //https://github.com/0x27/apkpure_get/archive/refs/heads/master.zip

        InputStream is = given().
                baseUri("https://raw.githubusercontent.com").
                log().all().
                when().
                post("/appium-boneyard/sample-code/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
                then().
                log().all().
                extract().response().asInputStream();

        OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
        byte[] bytes = new byte[is.available()];
        is.read();
        os.write(bytes);
        os.close();

    }
//Another way to download file (Storing inside byte array, no need to read anything)
        @Test
        public void fileDownload1() throws IOException {

            byte[] bytes = given().
                    baseUri("https://raw.githubusercontent.com").
                    log().all().
                    when().
                    post("/appium-boneyard/sample-code/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
                    then().
                    log().all().
                    extract().response().asByteArray();

            OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
            os.write(bytes);
            os.close();

        }

}