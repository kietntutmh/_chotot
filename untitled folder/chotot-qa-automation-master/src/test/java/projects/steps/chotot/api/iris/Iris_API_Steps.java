package projects.steps.chotot.api.iris;

import com.vn.chotot.exception.FailureHandling;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static api.utils.GetAccessToken.getAccessTokenOfExistingUser;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Utils.verifyNotMatch;
import static projects.functions.chotot.iris.Iris_API_Functions.*;

public class Iris_API_Steps {
    static String statusCodeStr = "";
    static String defaultImagePath =
      System.getProperty("user.dir") + "/images/iris/defaultImage.jpg";
    static String imageTypePath =
            System.getProperty("user.dir") + "/images/iris/uploadImageType";
    static String bigSizeImagePath =
            System.getProperty("user.dir") + "/images/iris/bigSizeImage.jpg";
    static String smallSizeImagePath =
            System.getProperty("user.dir") + "/images/iris/smallSizeImage.jpg";
    static String bigDimensionImagePath =
            System.getProperty("user.dir") + "/images/iris/bigDimensionImage.jpg";
    static String smallDimensionImagePath =
            System.getProperty("user.dir") + "/images/iris/smallDimensionImage.png";

    @Given("Reset test data for iris")
    public void reset_test_data_iris() {
        statusCodeStr = "";
    }

    @Given("I logged in Chotot using existing account")
    public void i_login_using_existing_account() {
        getAccessTokenOfExistingUser();
    }

    @Given("I logged in to Mudah site")
    public void i_login_Mudah_using_existing_account() {
        getIrisTokenOfUserMD(emailTestMD, passwordTestMD);
    }

    @Given("I register a new account")
    public void i_have_an_new_account() {
        getAccessTokenOfNewUser();
    }

    @Then("I can upload image with type {string}")
    public void i_can_upload_image_with_specific_type(String type) {
        String filePath = defaultImagePath;
        statusCodeStr = uploadImageByType(type, filePath);
        verifyMatch(statusCodeStr, "200", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can upload image format {string}")
    public void i_can_upload_image_format(String format) {
        String filePath = imageTypePath + format;
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "200", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I cannot upload image format {string}")
    public void i_cannot_upload_image_format(String format) {
        String filePath = imageTypePath + format;
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "400", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can upload image less than {string}")
    public void i_can_upload_less_than_max_size(String maxSize) {
        String filePath = smallSizeImagePath;
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "200", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I cannot upload image greater than {string}")
    public void i_cannot_upload_greater_than_max_size(String maxSize) {
        String filePath = bigSizeImagePath;
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "413", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can upload image with dimension <= {string}")
    public void i_can_upload_image_not_too_big(String maxDimention) {
        String filePath = bigDimensionImagePath;
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "200", false, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I cannot upload image too small with dimension < {string}")
    public void i_cannot_upload_image_too_small(String minDimention) {
        String filePath = smallDimensionImagePath;
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "400", true, FailureHandling.STOP_ON_FAILURE);
    }

  @Then("I cannot upload more than {string} images per hour for {string} type")
  public void i_cannot_upload_too_many_images_by_type(String maxNumber, String type) {
        int number = Integer.parseInt(maxNumber);
        String filePath = defaultImagePath;
        for (int i = 1; i <= number; i++) {
            System.out.println("Running time: " + i);
            statusCodeStr = uploadInternalImageByType(type, filePath);
            verifyNotMatch(statusCodeStr, "429", true, FailureHandling.STOP_ON_FAILURE);
        }
        statusCodeStr = uploadInternalImageByType(type, filePath);
        verifyMatch(statusCodeStr, "429", true, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I cannot upload more than {string} images per hour for {string} type in MD")
    public void i_cannot_upload_too_many_images_by_type_in_MD(String maxNumber, String type) {
        int number = Integer.parseInt(maxNumber);
        String filePath = defaultImagePath;
        for (int i = 1; i <= number; i++) {
            System.out.println("Running time: " + i);
            statusCodeStr = uploadInternalMudahImageByType(type, filePath);
            verifyNotMatch(statusCodeStr, "429", true, FailureHandling.STOP_ON_FAILURE);
        }
        statusCodeStr = uploadInternalMudahImageByType(type, filePath);
        verifyMatch(statusCodeStr, "429", true, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I cannot upload more than {string} images per hour")
    public void i_cannot_upload_too_many_images(String maxNumber) {
        int number = Integer.parseInt(maxNumber);
        String filePath = defaultImagePath;
        for (int i = 1; i <= number; i++) {
            System.out.println("Running time: " + i);
            statusCodeStr = uploadImageByDefault(filePath);
            verifyNotMatch(statusCodeStr, "429", true, FailureHandling.STOP_ON_FAILURE);
        }
        statusCodeStr = uploadImageByDefault(filePath);
        verifyMatch(statusCodeStr, "429", true, FailureHandling.STOP_ON_FAILURE);
    }
}
