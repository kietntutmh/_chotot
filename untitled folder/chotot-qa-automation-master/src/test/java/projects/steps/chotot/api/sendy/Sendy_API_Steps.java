package projects.steps.chotot.api.sendy;

import com.vn.chotot.exception.FailureHandling;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static projects.functions.chotot.sendy.Sendy_API_Functions.*;

public class Sendy_API_Steps {

    @Then("I can send email for template {string}")
    public void i_can_send_email_for_template(String template) {
        sendEmailByTemplate(template, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can send email for a campaign_id {string}")
    public void i_can_send_email_for_a_campaign_id(String campaign_id) {
        sendEmailByCampaignID(campaign_id, FailureHandling.STOP_ON_FAILURE);
    }

    @Then("I can check a valid email {string}")
    public void i_can_check_a_valid_email(String string) {
        validateEmail(string, true);
    }

    @Then("I can check an invalid email {string}")
    public void i_can_check_an_invalid_email(String string) {
        validateEmail(string, false);
    }

    @Then("I can upload an image to sendy portal")
    public void i_can_upload_an_image_to_sendy_portal() {
        uploadSendyImage();
    }

    @Given("I uploaded an image to sendy portal")
    public void i_uploaded_an_image_to_sendy_portal() {
        uploadSendyImage();
    }

    @Then("I can delete that image on sendy portal")
    public void i_can_delete_existing_image_on_sendy_portal() {
        deleteSendyImage();
    }
}
