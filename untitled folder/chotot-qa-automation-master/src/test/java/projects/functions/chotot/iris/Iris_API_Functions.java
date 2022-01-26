package projects.functions.chotot.iris;

import api.configuration.GatewayConfig;
import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import projects.functions.APISupportFunctions;

import java.io.File;

import static api.configuration.GatewayConfig.gatewayIrisFas;
import static api.configuration.GatewayConfig.global_accessToken;
import static com.vn.chotot.api.rest_assured.VerifyRespone.getPropertyValue;
import static io.restassured.RestAssured.given;

public class Iris_API_Functions extends APISupportFunctions{

    static final Logger log = Log4jFactory.instance().createClassLogger(Iris_API_Functions.class);
    static Response response = null;
    static final String cfToken = "pVrlD0Wt2Adr7zGN7TpTeuV5Ukat";
    private static String gatewayLoginMD = "https://stg-uac-gateway.mudah.my/nuauthapi/v1/signin";
    public static final String emailTestMD = "hobby01-co@gmail.com";
    public static final String passwordTestMD = "ten20304050";
    private static String irisTokenMudah = "";

    public static String uploadImageByType(String type, String filePath) {
        log.info("\n----- IRIS url: " + gatewayIrisFas + "\n----- File path: " + filePath + "\n----- Type: " + type);
        response = given()
                .multiPart("image", new File(filePath))
                .headers("cf-token", cfToken, "type", type)
                .when()
                .post(gatewayIrisFas);
        return getResponseCode(response);
    }

    public static String uploadInternalImageByType(String type, String filePath) {
        log.info("\n----- IRIS url: " + gatewayIrisFas + "\n----- File path: " + filePath + "\n----- Type: " + type);
        response = given()
                .multiPart("image", new File(filePath))
                .headers("Authorization", global_accessToken, "type", type)
                .when()
                .post(gatewayIrisFas);
        return getResponseCode(response);
    }

    public static String uploadImageByDefault(String filePath) {
        return uploadImageByType("", filePath);
    }

    public static String getIrisTokenOfUserMD(String email, String password) {
        // Set cert flag
        RestAssure.instance().restAssuredConfig(GatewayConfig.allowAllCertFlag);

        // Create body data
        String body =
                "{\n\t\"data\": { \"attributes\": {"
                        + "\"login_type\": \"email\",\n\t\"kong_consumer_name\": \"desktop\", \n\t\"source\" : \"desktop\", \n\t\"account_type\": \"proniaga\","
                        + "\n\t\"email\": \"" + email + "\","
                        + "\n\t\"password\": \"" + password + "\""
                        + "\n\t\t}\n\t}\n}";
        log.info("\n----- Get iris_token of user: " + email);
        // Post login data
        Response response = RestAssure.instance().post(body, gatewayLoginMD);

        // Set access_token
        String iris_token = getPropertyValue(response, "data.attributes.iris_token");
        irisTokenMudah = "Bearer " + iris_token;

        return irisTokenMudah;
    }

    public static String uploadInternalMudahImageByType(String type, String filePath) {
        log.info("\n----- IRIS url: " + gatewayIrisFas + "\n----- File path: " + filePath + "\n----- Type: " + type);
        if (irisTokenMudah.isEmpty())
            getIrisTokenOfUserMD(emailTestMD, passwordTestMD);

        response = given()
                .multiPart("image", new File(filePath))
                .headers("Authorization", irisTokenMudah, "type", type, "Tenant-Namespace", "mudah")
                .when()
                .post(gatewayIrisFas);
        String statusCode = getResponseCode(response);
        System.out.println("Status code: " + statusCode);
        return statusCode;
    }
}
