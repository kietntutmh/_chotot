package projects.steps.chotot.api.video_ad;

import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot.video_ad.Video_Ad_API_Functions;

import java.util.List;

import static api.configuration.DataConfig.adPTYExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMappingWithVideo;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static projects.utilities.Hooks.excelDataProvider;

public class Video_Ad_API_Steps extends Video_Ad_API_Functions {
    static final Logger log = Log4jFactory.instance().createClassLogger(Video_Ad_API_Steps.class);
    static final String defaultVideoName = "ad";

    @Given("New account for video_ad")
    public void i_create_new_account_for_video_ad(){
        getAccessTokenOfNewUser();
    }

    @Then("I can upload a video ad with type {string} for {string}")
    public void i_can_upload_video_ad_with_type(String type, String platform) {
        String video_file_name = defaultVideoName + type;
        log.info("Start upload video: " + video_file_name);
        int category_id = getRandomEnabledCategory(platform);
        List<String> values = generateUploadVideoLink(category_id);

        String statusCode = uploadVideo(values.get(1), video_file_name);
        verifyMatch(statusCode, "200", false, FailureHandling.STOP_ON_FAILURE);
        log.info("===== Video ID: " + values.get(0));
        checkUploadVideoStatus(values.get(0), "ready",maxWaitTime * 90);
    }

    @Then("I can upload a new ad with type {string} for {string}")
    public void i_can_upload_new_ad_with_video_type(String type, String platform) {
        // upload video
        String video_file_name = defaultVideoName + type;
        log.info("Start upload video: " + video_file_name);
        int category_id = getRandomEnabledCategory(platform);
        List<String> values = generateUploadVideoLink(category_id);

        String statusCode = uploadVideo(values.get(1), video_file_name);
        verifyMatch(statusCode, "200", false, FailureHandling.STOP_ON_FAILURE);
        String video_id = values.get(0);
        checkUploadVideoStatus(values.get(0), "ready",maxWaitTime * 90);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMappingWithVideo(apartmentDataKeys, apartmentDataValues, video_id);

        // Post ad apartment data
        insertNewAd(data);

    }

    @Then("I can upload a video ad with size {string} for {string}")
    public void i_can_upload_video_ad_with_size(String size, String platform) {

    }

    @Then("I can upload a video ad with duration {string} for {string}")
    public void i_can_upload_video_ad_with_duration(String duration, String platform) {

    }

    @Then("I can upload a video ad up to {string} times per month for {string}")
    public void i_can_upload_video_ad_with_limit(String limit, String platform) {

    }
}
