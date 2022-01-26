package projects.functions.chotot.video_ad;

import com.vn.chotot.api.rest_assured.RestAssure;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.response.Response;
import okhttp3.*;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static io.restassured.RestAssured.given;
import static projects.functions.APISupportFunctions.getResponseData;

public class Video_Ad_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Video_Ad_API_Functions.class);
    static Response response = null;
    static String default_video_path = System.getProperty("user.dir") + "/videos";

    /**
     * @return video_upload_id, video_upload_url
     */
    public static List<String> generateUploadVideoLink(int category_id) {
        String bodyStr = "{\"category_id\":" + category_id +"}";
        response = RestAssure.instance().post(global_accessToken, bodyStr, gatewayVideoUploadPrivate);
        String video_upload_id = getResponseData(response, "$.result.uid");
        String video_upload_url = getResponseData(response, "$.result.uploadURL");
        return Arrays.asList(video_upload_id, video_upload_url);
    }

    public static String getVideoUploadStatus(String video_upload_id) {
        String gatewayCheckVideoStatus = String.format(gatewayVideoUploadStatusPrivate,video_upload_id);
        response = RestAssure.instance().get(global_accessToken, gatewayCheckVideoStatus);
        String status = getResponseData(response, "$.status.state");
        if (status == null) status = "null";
        log.info("Video upload status: " + status);
        return status;
    }

    public static void checkUploadVideoStatus(String video_id, String expectedStatus, int durationSeconds) {
        for (int i=0; i<=durationSeconds;i++) {
            if(getVideoUploadStatus(video_id).equals(expectedStatus)) {
                log.info("Total time to check video status: " + String.valueOf(maxWaitTime*i) + "seconds");
                return;
            }
            delayStep(maxWaitTime);
        }
        log.info("Total time to check video status is exceed" + durationSeconds + " !!!");
        verifyMatch(getVideoUploadStatus(video_id), expectedStatus, false, FailureHandling.STOP_ON_FAILURE);
    }

    public static String uploadVideo(String video_upload_url, String video_file_name) {
        String file_path = default_video_path + "/" + video_file_name;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file","ad.mp4",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(file_path)))
                .build();
        Request request = new Request.Builder()
                .url(video_upload_url)
                .method("POST", body)
                .addHeader("Authorization", global_accessToken)
                .addHeader("Content-Type", "multipart/form-data")
                .build();
        okhttp3.Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.debug("Error in uploading video: ", e.getMessage());
            return "error";
        }
        return String.valueOf(response.code());
    }

    public static Integer getRandomEnabledCategory(String platform) {
        response = given().header("ct-platform", platform).get(gatewayVideoConfigPublic);
        String[] strArr = getResponseData(response, "$['features']['video.category.enable']").split(",");
        //log.info("Enabled categories:  " + Arrays.toString(strArr));
        int intValue = 1010; // Default value
        try {
            Random rand = new Random();
            String randomValue =  strArr[(rand.nextInt(strArr.length-1))];
            intValue = Integer.parseInt(randomValue);
            log.info("Random category value: " + intValue);
        } catch (IllegalArgumentException e) {
            log.info("Error get random category -> using default category value: " + intValue);
        }
        return intValue;
    }
}
