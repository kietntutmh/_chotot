package projects.functions.chotot.sendy;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.restassured.response.Response;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.api.rest_assured.VerifyRespone.verifyStatusCode;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;

public class Sendy_API_Functions {
    static JsonObject bodyJson = null;
    static Response response = null;
//    private static String api_key_sendy = "yYqxMeHpJSFRfyVYKMMo"; //CT
    private static String api_key_sendy = "oIvzlUaSvyhdwbQxxowRCWHtXAAsVO"; //MD
    private static String defaultReceiverEmail = "quoctran@chotot.vn";
    private static String defaultEmailList = "1270";
    private static String defaultSendyImagePath = System.getProperty("user.dir") + "/images/sendy/sendyUpload.jpeg";
    private static String defaultImageName = "sendyUpload.jpeg";
    private static String defaultImagePath = "sendyUpload.jpeg";
    private static String basicTokenMD = "Basic bWNvLXNlbmR5OmNob3RvdCFtdWRhaD8=";

    private static String getAuthorizationForSendy() {
//        return getBasicAuthorization("chotot", "chotot");
        return basicTokenMD;
    }

    public static boolean sendEmailByTemplate(String template, FailureHandling failureHandling) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("api_key", api_key_sendy);
        bodyJson.addProperty("email", defaultReceiverEmail);
        bodyJson.addProperty("template_type", template);
        bodyJson.addProperty("app", 1);
        response = RestAssure.instance().post(getAuthorizationForSendy(), bodyJson, gatewaySendySendEmailTemplate);
        return verifyStatusCode(response, "200", failureHandling);
    }

    public static boolean sendEmailByCampaignID(String campaign_id, FailureHandling failureHandling) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("campaign_id", campaign_id);
        bodyJson.addProperty("email_list", defaultEmailList);
        bodyJson.addProperty("app", 1);
        response = RestAssure.instance().post(getAuthorizationForSendy(), bodyJson, gatewaySendySendEmailCampaign);
        return verifyStatusCode(response, "200", failureHandling);
    }

    public static void validateEmail(String email, boolean isValid) {
      String urlString = String.format(gatewaySendyEmailValidation, email);
      System.out.println(urlString);
      OkHttpClient client = new OkHttpClient().newBuilder()
              .build();
      Request request = new Request.Builder()
              .url(urlString)
              .method("GET", null)
              .addHeader("Authorization", getAuthorizationForSendy())
              .build();
        okhttp3.Response response;
      try {
          response = client.newCall(request).execute();
          String bodyString = response.body().string();
          System.out.println("Body string: " + bodyString);
          String status = com.jayway.jsonpath.JsonPath.parse(bodyString).read("$.is_valid").toString();
          System.out.println("status: " + status);
          if (isValid) {
              verifyMatch(status, "1", false,FailureHandling.STOP_ON_FAILURE);
          } else {
              verifyMatch(status, "2", false,FailureHandling.STOP_ON_FAILURE);
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    public static void uploadSendyImage() {
        String data = "";
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(defaultSendyImagePath));
            String encodedString = Base64
                    .getEncoder()
                    .encodeToString(fileContent);
            data = encodedString;
        } catch (IOException ex) { }
        bodyJson = new JsonObject();
        bodyJson.addProperty("name", defaultImageName);
        bodyJson.addProperty("path", defaultImagePath);
        bodyJson.addProperty("data", data);
        response = RestAssure.instance().post(getAuthorizationForSendy(), bodyJson, gatewaySendySendUploadImage);
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }

    public static void deleteSendyImage() {
        response = RestAssure.instance().delete(String.format(gatewaySendySendDeleteImage, defaultImageName));
        verifyStatusCode(response, "200", FailureHandling.STOP_ON_FAILURE);
    }
}
