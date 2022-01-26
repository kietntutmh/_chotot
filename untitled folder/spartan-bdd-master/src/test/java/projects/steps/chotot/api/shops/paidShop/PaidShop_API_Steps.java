package projects.steps.chotot.api.shops.paidShop;

import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot.shops.PaidShop_API_Functions;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static projects.configuaration.CategoryConfig.getCategoryID;
import static projects.functions.chotot.flashad.FlashAd_Core_Functions.checkUserAdTotal;

public class PaidShop_API_Steps extends PaidShop_API_Functions {
    private String editAdID = "";
    private boolean isAdToShop = false;
    private boolean isAdToChotot = false;
    private CM_API_Ads_InsertPTY insertAdApi = new CM_API_Ads_InsertPTY();

    private void setAdToChotot(){
        isAdToChotot = true;
        isAdToShop = false;
    }

    private void setAdToShop(){
        isAdToChotot = false;
        isAdToShop = true;
    }

    @Given("I login my account with PTY Shop")
    public void i_register_my_new_account_to_create_a_Shoppty() {
        getAccessTokenNewUserShopPTY();
    }

    @Given("I login my account with VEH Shop")
    public void i_register_my_new_accounst_to_create_a_Shoppty() {
        getAccessTokenNewUserShopVEH();
    }

    @Given("I login my account with ELT Shop")
    public void i_register_my_new_accounst_to_create_a_Shopelt() {
        getAccessTokenNewUserShopELT();
    }

    @Given("I post a Shop House Ad_To_Chotot which is refused")
    public void i_post_a_Shop_House_Ad_To_Chotot_which_is_refused() {
        setAdToChotot();
        global_accessToken = getShopUserToken();
        editAdID = insertAdApi.insertNewAdHouseShop("rejectpay", true, false);
        getShopAdInfo(editAdID);
    }

    @When("I edit my Shop Ad")
    public void i_edit_my_Ad_To_Chotot() {
        insertAdApi.editNewAdHouseShop(editAdID, "pay", true, false);
    }

    @Then("My Shop Ad Status should be {string}")
    public void my_Ad_Status_should_be(String editAdStatus) {
        verifyShopAdStatus(editAdID, editAdStatus);
    }

    @Then("My Shop Ad Payment should be {string}")
    public void my_Ad_Payment_should_be(String paymentStatus) {
        verifyShopAdPaymentStatus(editAdID, paymentStatus);
    }

    @Given("My Shop Ad should be in CP Queue new_pty_ads")
    public void my_Shop_Ad_should_be_in_CP_Queue() {
        verifyShopAdInQueue(editAdID, "new_pty_ads");
    }

    @Given("My Shop Ad should be in CP Suggested Queue new_pty_ads")
    public void my_Shop_Ad_should_be_in_CP_Qusseue() {
        verifyShopAdInQueueSuggested(editAdID, "new_pty_ads");
    }


    @Then("I have total {string} ads of category {string}")
    public void i_have_ad_total(String total, String categoryID){
        String cateID = getCategoryID(categoryID.trim());
        if(cateID == null){
            cateID = categoryID;
        }
        checkUserAdTotal(global_accountID, cateID, Integer.parseInt(total));
    }

    @When("I hide my Shop Ad from Chợ Tốt Listing")
    public void ihide_ssssmy_ad() {
        hideAdOnShopDashboard(global_accessToken, getShopAlias(), tempAdID);
    }

    @When("I unhide my Shop Ad on Chợ Tốt Listing")
    public void ihide_sssssmy_ad() {
        unhideAd(global_accessToken, getShopAlias(), tempAdID);
    }

    @When("I remove my Shop Ad out of Chợ Tốt Listing")
    @When("I delete my Shop Ad out of Chợ Tốt Listing")
    public void publishTsoChssotot() {
        removeAdOutOfListing(global_accessToken, getShopAlias(), tempAdID);
    }

    @When("I wait for {string} seconds")
    public void ahsjs(String sec){
        waitConstant(Integer.parseInt(sec));
    }

    @When("My PTY Shop is expired")
    @When("My ELT Shop is expired")
    @When("My VEH Shop is expired")
    public void chotot_expires_to_block_my_shop() {
        expirePaidShop(global_accessToken);
    }

    @But("My PTY Shop is extended")
    @Deprecated     //REMOVE BY SHOP PROMOTION
    public void exteneded_shoppty() {
        extendShopPTY(global_accessToken, getShopAlias(), 1, false);
    }

    @But("My ELT Shop is extended")
    @Deprecated     //REMOVE BY SHOP PROMOTION
    public void exteneded_shopelt() {
        extendShopELT(global_accessToken, getShopAlias(), 1, false);
    }

    @But("My VEH Shop is extended")
    @Deprecated     //REMOVE BY SHOP PROMOTION
    public void exteneded_shopveh() {
        extendShopVEH(global_accessToken, getShopAlias(), 1, false);
    }
}
