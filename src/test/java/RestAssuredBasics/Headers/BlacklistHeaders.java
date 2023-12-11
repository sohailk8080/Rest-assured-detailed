package RestAssuredBasics.Headers;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class BlacklistHeaders {

        @Test
        public void blackListHeaders(){
        //Blacklisting prevents log().all() from printing header value in the response

          //2nd way of blacklisting headers [Using Set<String> and 'blacklistHeaders']
            Set<String> header = new HashSet<>();
            header.add("X-Api-Key");
            header.add("Accept");

            given().
                    baseUri("https://api.postman.com").
                    header("X-Api-Key","PMAK-6349451a807995020fa24ca6-a2be6d33e29f13cbf21733d81a2a5f4ac8").
                //1st way of blacklisting
                    //config(config.logConfig(LogConfig.logConfig().blacklistHeader("X-Api-Key"))).
                //2nd way contd.
                    config(config.logConfig(LogConfig.logConfig().blacklistHeaders(header))).
                    log().all().
            when().
                    get("/workspaces").
            then().
                    assertThat().
                    statusCode(200).
                    body("workspaces[0].name", equalTo("My Workspace"));

        }
}

