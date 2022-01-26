package projects.steps.chotot.api.supportQAManual;

import com.vn.chotot.api.rest_assured.RestAssure;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.en.Given;
import org.testng.Assert;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot.supportQAManual.SupportQAManual_Functions;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrder;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_3000k;
import static projects.functions.chotot.pos.POS_Functions.posStickyAd;

public class SupportQAManual_Steps extends SupportQAManual_Functions {
    @Given("Insert {string} Ads of cate {string} subcate {string} for new user")
    public void insert_Ads_of_cate_subcate_for_new_user(String numAd, String cateID, String subcateID) {
        insertAdSell_NewUser_Private(Integer.parseInt(numAd), cateID, subcateID, "accept");
    }

    @Given("Insert {string} Ads of cate {string} subcate {string} for user {string}")
    public void insert_Ads_of_cate_subcate_for_user(String numAd, String cateID, String subcateID, String phone) {
        insertAdSell_SpecifiedUser_Private(phone, Integer.parseInt(numAd), cateID, subcateID, "accept");
    }

    @Given("I login my account to post a New Ad and buy Sticky Ad for it")
    public void i_login_my_account_to_post_a_New_Ad_and_buy_Sticky_Ad_for_it() {
        insertAdWithStickyAd();
    }

    @Given("Insert a new ad to test autoreview")
    public void insert_a_new_ad_to_test_autoreview() {
        insertAdByParam();
    }

    @Given("Topup momo for specified account")
    public void insertss_a_new_ad_to_test_autoreview() {
        topupForSpecifiedAccount();
    }

    @Given("Create Shop and post free ads")
    public void shopelt_30ads(){
        insertAdByParam_Shop_InsertAds();
    }


    @Given("Get all subcategories")
    public void get_all_subcategories() {
        getAllCateSubcate();
    }

    @Given("Vuhoang Debug")
    public void vuhoang_Debugaaa() {
        vuhoangdebuga();
    }

    @Given("I post a new House Ad and use Stick Ad")
    public void failed() {
        CM_API_Ads_InsertPTY cm_ad = new CM_API_Ads_InsertPTY();
        cm_ad.insertNewAdHouse();
        topupDongTotWithMomo(newUserPhone);
        posStickyAd(global_accessToken, tempAdID);
        paymentOrder();
    }
    @Given("I post a new House Ad, topup and user Sticky Ad")
    public void failedTopup() {
        int numberOfTopup = 5;
        CM_API_Ads_InsertPTY cm_ad = new CM_API_Ads_InsertPTY();
        cm_ad.insertNewAdHouse();
        for(int i = 0; i < numberOfTopup; i++) {
            topupDongTotWithMomo_3000k(newUserPhone);
        }
        posStickyAd(global_accessToken, tempAdID);
        paymentOrder();
    }
    @Given("Failed")
    public void testtest() {
        Assert.assertTrue(true);
    }

    //---------------- SUpport for Son by Quoc----------------
    CM_API_Ads_InsertFashion cm_api_ads_insertFashion;

    @Given("Create a new user and Insert {int} Ads")
    public void create_a_new_user_and_Insert_Ads(Integer numberOfAd) {
        setAccessToken();
        cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
        for(int i = 0; i < numberOfAd; i++){
            cm_api_ads_insertFashion.insertNewAdAccessories();
        }
        System.out.println("Phone: " + tempUserPhone);
    }

    @Given("Create a new user and Insert {int} Laptop Ads and approved")
    public void createANewUserAndInsertAdsAndApproved(int numberOfAd) {
        setAccessToken();
        cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
        CM_API_Ads_InsertELT cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
        for(int i = 0; i < numberOfAd; i++){
            cm_api_ads_insertELT.insertNewAdLaptop("accept");
        }
        System.out.println("Phone: " + tempUserPhone);
    }

    @Given("I register {int} Accounts")
    public void iRegisterAccounts(int no) {
        for(int i=0; i<no;i++){
            getAccessTokenOfNewUser();
        }
    }

    @Given("Create user and Topup for the account")
    public void create_user_and_Topup_for_the_account() {
        registerAndTopup();
    }

    @Given("Add Diem Tot to a specified user")
    public void add_diem_tot_to_user() {
        addDiemTotToUser();
    }

}
