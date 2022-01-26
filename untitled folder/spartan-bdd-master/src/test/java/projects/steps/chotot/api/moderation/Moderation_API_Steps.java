package projects.steps.chotot.api.moderation;

import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import projects.functions.chotot.moderation.Moderation_API_Functions;

import static api.configuration.DataConfig.*;
import static api.feature.ads.HideAds.hideExistingAd;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static com.vn.chotot.configuration.WaitTime.maxWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;
import static projects.functions.chotot.shops.PaidShop_API_Functions.getAccessTokenNewUserShopELT;

public class Moderation_API_Steps extends Moderation_API_Functions {

    static final Logger log = Log4jFactory.instance().createClassLogger(Moderation_API_Steps.class);

    @Given("New user is created to test RR31a")
    public void new_user_is_created_for_moderation_rr31a() {
        setTempAccountCPAPIIndex(4);
        getAccessTokenOfNewUser();
        setIsTestingModeration(true);
        setIsUploadingImage(true);
        setIsGeneratingRandomImage(true);
    }

    @Given("User had an shop PTY for testing moderation RR31a")
    public void user_had_shop_pty_rr31a() {
        log.info("Create new shop PTY for testing moderation RR31a");
        getAccessTokenNewUserShopELT();
    }

    @Given("User had an shop ELT for testing moderation RR31a")
    public void user_had_shop_elt_rr31a() {
        log.info("Create new shop ELT for testing moderation RR31a");
        getAccessTokenNewUserShopELT();
    }

    @Given("User had an accepted ad {string} for testing moderation RR31a")
    public void existing_an_accepted_ad_for_moderation_rr31a(String cate_id) {
      postPrivateAdByCateID(cate_id, "accept");
    }

    @Given("User had an accepted ad pro {string} for testing moderation RR31a")
    public void existing_an_accepted_ad_pro_for_moderation_rr31a(String cate_id) {
        postProAdByCateID(cate_id, "accept");
    }

    @And("User had a pending ad {string} for testing moderation RR31a")
    public void existing_a_pending_ad_for_moderation_rr31a(String cate_id) {
        postPrivateAdByCateID(cate_id, "none");
    }

    @And("User had a pending ad pro {string} for testing moderation RR31a")
    public void existing_a_pending_ad_pro_for_moderation_rr31a(String cate_id) {
        postProAdByCateID(cate_id, "none");
    }

    @And("User had a hidden ad {string} for testing moderation RR31a")
    public void existing_a_hidden_ad_for_moderation_rr31a(String cate_id) {
        postPrivateAdByCateID(cate_id, "accept");
        delayStep(maxWaitTime);
        hideExistingAd(tempAdID,"");

    }

    @And("User had a hidden ad pro {string} for testing moderation RR31a")
    public void existing_a_hidden_ad_pro_for_moderation_rr31a(String cate_id) {
        postProAdByCateID(cate_id, "accept");
        delayStep(maxWaitTime);
        hideExistingAd(tempAdID,"");

    }

    @When("User post a new paid ad {string} with the same image as existing ad")
    public void user_post_new_paid_ad_moderation_rr31a(String cate_id) {
        topupDongTotWithMomo();
        postPrivateAdByCateID(cate_id, "pay");
    }

    @When("User post a new buy ad {string} with the same image as existing ad")
    public void user_post_new_buy_ad_moderation_rr31a(String cate_id) {
        postPrivateBuyAdByCateID(cate_id, "none");
    }

    @When("User post a new ad {string} with the same image as existing ad")
    public void user_post_new_ad_moderation_rr31a(String cate_id) {
        postPrivateAdByCateID(cate_id, "none");
    }

    @When("User post a new ad pro {string} with the same image as existing ad")
    public void user_post_new_ad_pro_moderation_rr31a(String cate_id) {
        postProAdByCateID(cate_id, "none");
    }

    @Then("New paid ad is NOT rejected by RR31a")
    public void new_paid_ad_is_not_rejected_by_31a(){
        checkAdStatusOnDashboard(tempAdID, "KHÁC");
    }

    @Then("New buy ad is NOT rejected by RR31a")
    public void new_buy_ad_is_not_rejected_by_31a(){
        checkAdStatusOnDashboard(tempAdID, "KHÁC");
    }

    @Then("New ad is NOT rejected by RR31a")
    public void new_ad_is_not_rejected_by_31a(){
        checkAdStatusOnDashboard(tempAdID, "KHÁC");
    }

    @Then("New ad is rejected by RR31a")
    public void new_ad_is_rejected_by_31a(){
        checkAdStatusOnDashboard(tempAdID, "BỊ TỪ CHỐI");
    }
}
