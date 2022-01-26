package com.vn.chotot.api.rest_assured;

import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.SSLConfig.sslConfig;

@Deprecated
public class RestAssure {

    private static RestAssure restAssure;
    final Logger log = Log4jFactory.instance().createClassLogger(getClass());
    private static String request_url = "";

    private RestAssure() {
    }

    public static RestAssure instance() {
        if (restAssure == null) restAssure = new RestAssure();

        return restAssure;
    }

    public static void setRequestURL(String req_url){
        request_url = req_url;
    }

    public static String getRequestURL(){
        return request_url;
    }

    @Deprecated
    public Response postJson(String body, String url) {
        log.info("\n----- POST url: " + url + "\n----- Body: " + body);
        setRequestURL(url);

        return given().contentType("application/json").body(body).when().post(url);
    }

    public Response post(String authorization, String body, String url) {
        log.info("\n----- POST url: " + url + "\n----- Body: " + body
                + "\n----- TOKEN: " + authorization
        );
        setRequestURL(url);

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .post(url);
    }

    public Response post(String authorization, JsonObject body, String url) {
        log.info("\n----- POST url: " + url
                + "\n----- Body: " + body.toString()
                + "\n----- Token: " + authorization
        );
        setRequestURL(url);
        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body.toString())
                .post(url);
    }

    public Response head(String url) {
        log.info("\n----- HEAD url: " + url
        );
        return given()
                .contentType("application/json")
                .head(url);
    }

    public Response post(String url) {
        log.info("\n----- POST url: " + url);
        setRequestURL(url);

        return given().contentType("application/json").post(url);
    }

    public Response post(String body, String url) {
        log.info("\n----- POST url: " + url + "\n----- Body: " + body);
        setRequestURL(url);

        return given().contentType("application/json").body(body).post(url);
    }

    public Response post(JsonObject body, String url) {
        log.info("\n----- POST url: " + url + "\n----- Body: " + body);
        setRequestURL(url);
        return given()
                .contentType("application/json")
                .body(body.toString())
                .post(url);
    }

    public Response postWithoutBody(String authorization, String url) {
        log.info("\n----- POST url: " + url);
        setRequestURL(url);

        return given().contentType("application/json").header("Authorization", authorization).post(url);
    }

    public Response postImageFile(String authorization, String url, String filePath) {
        log.info("\n----- POST url: " + url + "\n----- File path: " + filePath);
        setRequestURL(url);

        return given()
                .multiPart("image", new File(filePath))
                .header("Authorization", authorization)
                .when()
                .post(url);
    }

    public Response postImageShop(String authorization, String url, String filePath) {
        log.info("\n----- POST url: " + url
                + "\n----- File path: " + filePath
                + "\n----- Token: " + authorization
        );

        String fileExtension = filePath.split("\\.")[1].toLowerCase();

        return given()
                .multiPart("media", new File(filePath), "image/" + fileExtension)
                .header("Authorization", authorization)
                .when()
                .post(url);
    }

    public Response put(String authorization, JsonObject body, String url) {
        setRequestURL(url);
        log.info("\n----- PUT url: " + url
                + "\n----- Body: " + body
                + "\n----- Token: " + authorization

        );

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body.toString())
                .put(url);
    }

    public Response put(String authorization, String url) {
        log.info("\n----- PUT url: " + url
                + "\n----- Token: " + authorization
        );

        setRequestURL(url);

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .put(url);
    }

    public Response put(String authorization, String body, String url) {
        log.info("\n----- PUT url: " + url
                + "\n----- Body: " + body
                + "\n----- Token: " + authorization
        );

        setRequestURL(url);

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .put(url);
    }

    public Response put(Object body, String url) {
        String bodyString = String.valueOf(body);
        setRequestURL(url);
        log.info("\n----- PUT url: " + url
                + "\n----- Body: " + bodyString
        );

        return given().contentType("application/json").body(bodyString).put(url);
    }

    public Response put(String url) {
        log.info("\n----- PUT url: " + url);
        setRequestURL(url);

        return given().put(url);
    }

    public Response delete(String authorization, String body, String url) {
        log.info("\n----- DELETE url: " + url
                + "\n----- Body: " + body
                + "\n----- Token: " + authorization
        );

        setRequestURL(url);

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .delete(url);
    }

    public Response delete(String body, String url) {
        log.info("\n----- DELETE url: " + url);
        setRequestURL(url);

        return given().contentType("application/json").body(body).delete(url);
    }

    public Response deleteWithoutBody(String authorization, String url) {
        log.info("\n----- DELETE url: " + url);
        setRequestURL(url);

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .delete(url);
    }

    public Response delete(String url) {
        log.info("\n----- DELETE url: " + url);
        setRequestURL(url);

        return given().contentType("application/json").delete(url);
    }

    public Response get(String authorization, String url) {
        log.info("\n----- GET url: " + url + "\n----- Token: " + authorization);
        setRequestURL(url);

        return given().contentType("application/json").header("Authorization", authorization).get(url);
    }

    public Response get(String url) {
        log.info("\n----- GET url: " + url);
        setRequestURL(url);

        return given().contentType("application/json").get(url);
    }

    public void restAssuredConfig(Boolean allowAllCert) {
        if (allowAllCert) {
            // Allow all https certificates
            RestAssured.config =
                    new io.restassured.config.RestAssuredConfig()
                            .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"))
                            .sslConfig(sslConfig().allowAllHostnames())
                            .sslConfig(sslConfig().relaxedHTTPSValidation());
        }
    }
}
