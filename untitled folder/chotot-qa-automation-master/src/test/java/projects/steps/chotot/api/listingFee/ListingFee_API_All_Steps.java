package projects.steps.chotot.api.listingFee;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projects.functions.chotot.flashad.FlashAd_ELT_Functions;
import projects.functions.chotot.flashad.FlashAd_PTY_Functions;
import projects.functions.chotot.flashad.FlashAd_VEH_Functions;
import projects.functions.chotot.listingFee.ListingFee_API_All_Functions;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_2000k;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_500k;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class ListingFee_API_All_Steps extends ListingFee_API_All_Functions {

    private FlashAd_PTY_Functions pty;
    private FlashAd_VEH_Functions veh;
    private FlashAd_ELT_Functions elt;

    private void setup() {
        pty = new FlashAd_PTY_Functions();
        veh = new FlashAd_VEH_Functions();
        elt = new FlashAd_ELT_Functions();
    }

    @Given("I register {string} New Accounts")
    public void register_alotof_account(String numberOfNewAcc) {
        for(int i = 0; i < Integer.parseInt(numberOfNewAcc); i++){
            getAccessTokenOfNewUser();
            System.out.println("Finish " + i);
        }
    }

    @Given("I login my Account LF")
    @Given("I login my Account with 500k Đồng Tốt")
    public void login_private_and_Topup500() {
        setUserToken(getAccessTokenOfNewUser());
        setUserPhone(newUserPhone);
        setUserAccountID(global_accountID);
        global_accessToken = getUserToken();
        topupDongTotWithMomo_500k(newUserPhone);
    }

    @Given("I login my Account with PTY Shop LF")
    public void login_shop_pty() {
        setUserToken(getAccessTokenNewUserShopPTY());
        setUserPhone(getShopUserPhone());
        setUserAccountID(getShopUserAccountID());
        global_accessToken = getUserToken();
    }

    @Given("I login my Account with ELT Shop LF")
    public void login_shop_elt() {
        setUserToken(getAccessTokenNewUserShopELT());
        setUserPhone(getShopUserPhone());
        setUserAccountID(getShopUserAccountID());
        global_accessToken = getUserToken();
    }

    @Given("I login my Account with VEH Shop LF")
    public void login_shop_veh() {
        setUserToken(getAccessTokenNewUserShopVEH());
        setUserPhone(getShopUserPhone());
        setUserAccountID(getShopUserAccountID());
        global_accessToken = getUserToken();
    }

    @Given("I create PTY Shop and Chotot approves my Shop")
    public void login_shop_pty_register() {
        //For create a new Shop
        setShopUserPhone(newUserPhone);
        setShopUserAccountID(global_accountID);
        //For listing functions
        setUserPhone(newUserPhone);
        setUserAccountID(global_accountID);

        //Create new Shop
        setUserToken(getAccessTokenNewUserShopPTY(global_accessToken));
    }

    @Given("I create ELT Shop and Chotot approves my Shop")
    public void login_shop_elt_register() {
        //For create a new Shop
        setShopUserPhone(newUserPhone);
        setShopUserAccountID(global_accountID);
        //For listing functions
        setUserPhone(newUserPhone);
        setUserAccountID(global_accountID);

        //Create new Shop
        setUserToken(getAccessTokenNewUserShopELT(global_accessToken));
    }

    @Given("I create VEH Shop and Chotot approves my Shop")
    public void login_shop_veh_register() {
        //For create a new Shop
        setShopUserPhone(newUserPhone);
        setShopUserAccountID(global_accountID);
        //For listing functions
        setUserPhone(newUserPhone);
        setUserAccountID(global_accountID);

        //Create new Shop
        setUserToken(getAccessTokenNewUserShopVEH(global_accessToken));
    }

    @Then("My Ad should be on Tab ĐANG BÁN")
    public void i_should_see_my_new_ad_displayed_on_Filter_active() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "ĐANG BÁN");
    }

    @Then("My Ad should be on Tab CẦN THANH TOÁN")
    public void i_should_see_my_new_ad_displayed_on_Filter_pay() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "CẦN THANH TOÁN");
    }

    @Then("My Ad should be on Tab KHÁC - ĐỢI DUYỆT")
    public void i_should_see_my_new_ad_displayed_on_Filter_other() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "ĐỢI DUYỆT");
    }

    @Then("My Ad should be on Tab KHÁC - ĐỢI DUYỆT - SAU KHI BỊ TỪ CHỐI")
    public void i_should_see_my_new_ad_displayed_on_Filtser_other() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "ĐỢI DUYỆT - SAU KHI BỊ TỪ CHỐI");
    }

    @Then("My Ad should be on Tab BỊ TỪ CHỐI")
    public void i_should_see_my_new_ad_displayed_on_Filter() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "BỊ TỪ CHỐI");
    }

    @Then("My Ad should be on Tab BỊ TỪ CHỐI - SAU KHI THANH TOÁN")
    public void i_should_see_my_new_ad_displayed_ossn_Filter() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "BỊ TỪ CHỐI - SAU KHI THANH TOÁN");
    }

    @Then("My Ad should be on Tab BỊ TỪ CHỐI - CẦN THANH TOÁN")
    public void i_should_see_my_news_ad_displayed_on_Filter() {
        checkAdOnPrivateDashboard(getUserToken(), tempAdID, "BỊ TỪ CHỐI - CẦN THANH TOÁN");
    }

    @Given("I create {string} Shop with my current Account LF but don't pay")
    public void login_shop_pty_register_no_pay(String shopType) {
        //For create a new Shop
        setShopUserPhone(newUserPhone);
        setShopUserAccountID(global_accountID);
        //For listing functions
        setUserPhone(newUserPhone);
        setUserAccountID(global_accountID);

        topupDongTotWithMomo_2000k(newUserPhone);
        //Create new Shop
        switch(shopType.toUpperCase()){
            case "PTY":
                createShop_CheckStatus(global_accessToken, newUserPhone, CATEID_PTY, false, false);
                break;
            case "VEH":
                createShop_CheckStatus(global_accessToken, newUserPhone, CATEID_VEH, false, false);
                break;
            case "ELT":
                createShop_CheckStatus(global_accessToken, newUserPhone, CATEID_ELT, false, false);
                break;
        }
    }

    @Given("I create {string} Shop with my current Account LF but Chotot is reviewing")
    public void login_shop_pty_register_no_pay_cp(String shopType) {
        //For create a new Shop
        setShopUserPhone(newUserPhone);
        setShopUserAccountID(global_accountID);
        //For listing functions
        setUserPhone(newUserPhone);
        setUserAccountID(global_accountID);

        topupDongTotWithMomo_2000k(newUserPhone);
        //Create new Shop
        switch(shopType.toUpperCase()){
            case "PTY":
                createShop_CheckStatus(global_accessToken, newUserPhone, CATEID_PTY, true, false);
                break;
            case "VEH":
                createShop_CheckStatus(global_accessToken, newUserPhone, CATEID_VEH, true, false);
                break;
            case "ELT":
                createShop_CheckStatus(global_accessToken, newUserPhone, CATEID_ELT, true, false);
                break;
        }
    }



    //SHOP DASHBOARD

    @Then("My Ad should be on Tab TIN CHỢ TỐT of Shop Dashboard")
    public void i_shsould_see_my_new_ad_displayed_on_Filter_active() {
        checkAdOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN CHỢ TỐT");
    }

    @Then("My Ad should be on Tab TIN CHUYÊN TRANG of Shop Dashboard")
    public void i_shoulds_see_my_new_ad_displayedg_on_Filter_pay() {
        checkAdOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN CHUYÊN TRANG");
    }

    @Then("My Ad should be on Tab TIN KHÁC of Shop Dashboard")
    public void i_should_sees_my_new_ad_displayed_on_Filter_other() {
        checkAdOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN KHÁC");
    }

    @Then("My Ad should be on Tab TIN KHÁC - BỊ TỪ CHỐI of Shop Dashboard")
    public void i_should_sees_my_new_ad_displayed_on_Filter() {
        checkAdOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN KHÁC - BỊ TỪ CHỐI");
    }

    @Then("My Ad should be on Tab TIN KHÁC - TIN ĐÃ ẨN of Shop Dashboard")
    public void i_shousld_sees_my_new_ad_displayed_on_Filter() {
        checkAdOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN KHÁC - TIN ĐÃ ẨN");
    }

    @Then("My Ad should be on Tab TIN KHÁC - TIN ĐỢI DUYỆT of Shop Dashboard")
    public void i_should_sesses_my_new_ad_displayed_on_Filter() {
        checkAdOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN KHÁC - TIN ĐỢI DUYỆT");
    }

    @Then("My Ad shouldn't be on Shop Dashboard")
    public void i_shousld_sesses_my_new_ad_displayed_on_Filter() {
        checkAdNotOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "all tabs");
    }

    @Then("My Ad shouldn't be on Tab TIN CHỢ TỐT of Shop Dashboard")
    public void i_shsosuld_see_my_new_ad_displayed_on_Filter_active() {
        checkAdNotOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN CHỢ TỐT");
    }

    @Then("My Ad shouldn't be on Tab TIN CHUYÊN TRANG of Shop Dashboard")
    public void i_shsosuld_see_my_new_Sad_displayed_on_Filter_active() {
        checkAdNotOnShopDashboard(getUserToken(), getShopAlias(), tempAdID, "TIN CHUYÊN TRANG");
    }

    @Then("My Profiler should be Pro with Category {string} and Ad Type {string}")
    public void profilesssssr_check(String cateID, String adType) {
        checkUserProfilerLF(getUserAccountID(), cateID, adType, "pro");
    }

    @Then("My Profiler should be Private with Category {string} and Ad Type {string}")
    public void profilesr_check(String cateID, String adType) {
        checkUserProfilerLF(getUserAccountID(), cateID, adType, null);
    }

    @Then("My Profiler should be SHOP with Category {string} and Ad Type {string}")
    public void profilerss_chesck(String cateID, String adType) {
        checkUserProfilerLF(getUserAccountID(), cateID, adType, "shop");
    }

    @Then("My Ad Payment Status should be PAID")
    public void ad_state_paid() {
        setAdPaymentStatus_Paid();
    }

    @Then("My Ad Payment Status should be UNPAID")
    public void ad_state_unpaid() {
        setAdPaymentStatus_Unpaid();
    }

    @Then("My Ad Payment Status should be FREE")
    public void ad_state_free() {
        setAdPaymentStatus_Free();
    }


    //Just use for LF
    @Then("I should edit my House Private Ad on Chotot with Free LF")
    @Then("I should edit my House Private Ad with Free LF")
    public void edit_free_private_house(){
        setup();
        pty.setSubject(tempAdSubject + " EDIT");
        pty.editHouseSellPrivate(getUserToken(), tempAdID, "no action", false);
    }

    @Then("I should edit my House Ad which is refused with Charge LF")
    public void edist_free_private_house(){
        setStatusExpectAfterRefused();
        setup();
        pty.setSubject(tempAdSubject + " EDIT");
        pty.editHouseSellPrivate(getUserToken(), tempAdID, "no action", false);
    }

    @Then("I should edit my House Shop Ad To Chotot which is refused with Free LF")
    public void edist_free_private_hsouse(){
        setStatusExpectAfterRefused();
        setup();
        pty.setSubject(tempAdSubject + " EDIT");
        pty.editHouseSellPrivate(getUserToken(), tempAdID, "no action", false);
    }

    @Then("I should edit my House Pro Ad with Charge LF")
    public void edit_free_pro_house(){
        setup();
        pty.setSubject(tempAdSubject + " EDIT");
        pty.editHouseSellPro(getUserToken(), tempAdID, "no action", false);
    }

    @Then("My Cart LF should have {string} order")
    public void edit_frsee_private_house(String numberOfOrder){
        checkCartOrder(getUserToken(), Integer.parseInt(numberOfOrder));
    }

    @Then("I publish Ad to Chotot as Paid LF")
    public void publishToChotot(){
        moveAdToChototPaid(getUserToken(), tempAdID);
    }

    @Then("I publish Ad to Chotot as Free LF")
    public void publishToChotots(){
        moveAdToChototFree(getUserToken(), getShopAlias(), tempAdID);
    }

    @Then("The Remaining free ad of my PTY Shop should be {string}")
    public void the_Remaining_free_ad_of_my_PTY_Shop_should_be(String freeAd) {
        checkRemainingFreeAdPTY(global_accountID, global_shopAlias, Integer.parseInt(freeAd));
    }

    @Then("The Remaining free ad of my ELT Shop should be {string}")
    public void the_Remaining_free_ad_of_my_ELT_Shop_should_be(String freeAd) {
        checkRemainingFreeAdELT(global_accountID, global_shopAlias, Integer.parseInt(freeAd));
    }

    @Then("The Remaining free ad of my VEH Shop should be {string}")
    public void the_Remaining_free_ad_of_my_VEH_Shop_should_be(String freeAd) {
        checkRemainingFreeAdVEH(global_accountID, global_shopAlias, Integer.parseInt(freeAd));
    }

//    @But("My PTY Shop is extended with duration {string} months")
//    public void exteneded_shoppty(String duration) {
//        extendPaidShop(global_accessToken, global_accountID, CATEID_PTY, getShopAlias(), Integer.parseInt(duration), false);
//    }
//
//    @But("My ELT Shop is extended with duration {string} months")
//    public void exteneded_shopelt(String duration) {
//        extendPaidShop(global_accessToken, global_accountID, CATEID_ELT, getShopAlias(), Integer.parseInt(duration), false);
//    }
//
//    @But("My VEH Shop is extended with duration {string} months")
//    public void exteneded_shopveh(String duration) {
//        extendPaidShop(global_accessToken, global_accountID, CATEID_VEH, getShopAlias(), Integer.parseInt(duration), false);
//    }

}
