package projects.steps.chotot.api.freePremium;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.customer.freePremium.FreeBump_API_Functions;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;


public class FreeBump_API_Steps extends FreeBump_API_Functions {

    @Given("ChoTot add a new campaign free bump with status ACTIVE")
    @Given("ChoTot should be able to add a new campaign free bump with status ACTIVE successfully")
    public void chotot_add_a_new_campaign_free_bump_with_status() {
        addCampaignFreeBumpActive();
        verifyCampaignFreeBump("true");
    }

    @Given("ChoTot add a new campaign free bump with status INACTIVE")
    public void chotot_add_a_new_campaign_free_bump_with_status_INACTIVE() {
        addCampaignFreeBumpInactive();
        verifyCampaignFreeBump("false");
    }

    @When("ChoTot update campaign status to ACTIVE")
    public void chotot_update_campaign_status_to_ACTIVE() {
        activateCampaignFreeBump();
    }

    @Then("ChoTot should be able to update the status successfully")
    public void chotot_should_update_the_status_successfully() {
        verifyCampaignFreeBump("true");
    }

    @When("ChoTot add {int} days to start time or {int} days to expired time of inactive campaign")
    public void chotot_add_days_to_start_and_expired_time(int startDays, int ExpireDays) {
        String campaignID = getCampaignFreeBumpID();
        updateCampaignFreeBumpTime(2, 2, true, campaignID);
    }

    @Then("ChoTot should be able to edit the time successfully")
    public void chotot_should_edit_time_successfully() {
        verifyCampaignFreeBumpUpdateSuccess(true);
    }

    @Given("Campaign should be added successfully")
    public void campaign_added_successfully() {
        verifyCampaignFreeBump("true");
    }

    @When("ChoTot add a redeem to the campaign")
    public void chotot_add_a_redeem_to_campaign() {
        addCampaignFreeBumpRedeem(global_accountID, tempAdID, tempAdSubject);
    }

    @Then("Redeem should be added to the campagin successfully")
    public void redeem_should_be_added_to_campagin_successfully() {
        checkRedeemFreeBump();
    }

    @Then("ChoTot can add a redeem into an active campaign for the user successfully")
    public void chotot_can_add_a_redeem_into_free_bump_for_the_user_successfully() {
        addCampaignFreeBumpRedeem(tempAccountID, tempAdID, tempAdSubject);
        verifyCampaignFreeBump("true");
    }

    @Then("I click the button redeem to get free bump for my ad")
    public void i_click_the_button_redeem_to_get_free_bump_for_my_ad() {
        getTokenUserRedeem(global_accessToken);
        String tokenRedeem = getTokenRedeem();
        String promotionID = getPromotionID();

        redeemFreeBump(tokenRedeem, promotionID);
    }

    @Then("I can redeem a free bump successfully")
    public void i_can_redeem_a_free_bump_successfully() {
        checkRedeemFreeBumpSuccess();
    }

    @Then("An announcement box is sent to the user")
    public void an_announcement_box_is_sent_to_the_user() {
        getTokenUserRedeem(global_accessToken);
        checkRedeemIsSendToUser();
    }

    @When("ChoTot can add a redeem into an inactive campaign for the user successfully")
    public void chotot_can_add_a_redeem_into_an_inactive_campaign_for_the_user_successfully() {
        addCampaignFreeBumpRedeem(tempAccountID, tempAdID, tempAdSubject);
        verifyCampaignFreeBump("false");
    }

    @Then("I should not be able to see the announcement box for free bump")
    public void i_should_not_be_able_to_see_the_announcement_box_for_free_bump() {
        getAnnouncementBoxInactiveCampaignFreeBump(global_accessToken);
        checkRedeemNotSendToUser();
    }

    @Then("ChoTot inactive the campaign")
    public void chotot_deactivate_the_campaign() {
        deactivateCampaignFreeBump();
    }

}
