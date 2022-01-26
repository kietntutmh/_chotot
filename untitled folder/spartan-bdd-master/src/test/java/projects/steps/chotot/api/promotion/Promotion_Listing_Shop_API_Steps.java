package projects.steps.chotot.api.promotion;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;
import projects.functions.chotot.promotion.Promotion_Listing_Shop_API_Functions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.GatewayConfig.*;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getBalanceDT;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrder;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_500k;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class Promotion_Listing_Shop_API_Steps extends Promotion_Listing_Shop_API_Functions {
    private static CM_API_Ads_InsertPTY cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
    private static CM_API_Ads_InsertELT cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
    private static CM_API_Ads_InsertVehicle cm_api_ads_insertVEH = new CM_API_Ads_InsertVehicle();
    private static String adID = "";
    private static String adIDRejected = "";
    private static String adEdited = "";
    private static List<String> houseAdIdList = new ArrayList<String>();
    private static List<String> houseAdIdListOnDashboard = new ArrayList<String>();
    //    private static List<String> houseAdNameListOnDashboard = new ArrayList<String>();
    private static List<String> houseAdIdListOnListing = new ArrayList<String>();
    //    private static List<String> houseAdNameListOnListing = new ArrayList<String>();
    private static List<String> removedAdIds = new ArrayList<String>();
    //    private static List<String> removedAdNames = new ArrayList<String>();
    private static List<String> hidenAdIdList = new ArrayList<String>();

    @Given("I am back to Chợ Tốt Website to get Shop Promotion")
    public void init() {
        houseAdIdList = new ArrayList<>();
        houseAdIdListOnDashboard = new ArrayList<>();
        houseAdIdListOnListing = new ArrayList<>();
        removedAdIds = new ArrayList<>();
        hidenAdIdList = new ArrayList<>();
        adID = "";
        adIDRejected = "";
        adEdited = "";
    }

    @Given("I login my account to get Promotion for Shop PTY Ad")
    public void login_promotion_shop() {
        setUserToken(getAccessTokenNewUserShopPTY());
        setUserAccountID(global_accountID);
        System.out.println(String.format(gatewayUserProfiler_UpdateProfiler, getUserAccountID()));
    }


    @Given("I login my account to get Promotion Listing")
    public void login_prosmotion_shop() {
        setUserToken(getAccessTokenOfNewUser());
        setUserAccountID(global_accountID);
        setUserPhone(newUserPhone);
    }

    @When("I remove all of my Ad out of Chợ Tốt")
    public void publishTsoChotot() {
        if (houseAdIdListOnListing.size() > 0) {
            for (String adID : houseAdIdListOnListing) {
                removeAdOutOfListing(getUserToken(), getShopAlias(), adID);
                removedAdIds.add(adID);
            }
        }

        if (houseAdIdListOnDashboard.size() > 0) {
            for (String adID : houseAdIdListOnDashboard) {
                removeAdOutOfListing(getUserToken(), getShopAlias(), adID);
                removedAdIds.add(adID);
            }
        }
    }

    @When("I hide my Ad on Shop Dashboard")
    public void ihide_my_ad() {
        for (String adID : houseAdIdListOnDashboard) {
            hideAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            hidenAdIdList.add(adID);
        }
    }

    @When("I hide my Ad on Chợ Tốt")
    public void ihide_sssmy_ad() {
        for (String adID : houseAdIdListOnListing) {
            hideAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnDashboard.add(adID);
            hidenAdIdList.add(adID);
        }
    }

    @When("I unhide my Ad")
    public void unhide_sdfsdf_sdfsdfAd() {
        for (int i = 0; i < hidenAdIdList.size(); i++) {
            unhideAd(getUserToken(), getShopAlias(), hidenAdIdList.get(i));
        }
    }

    @When("I republish my removed ad To Chotot again as Free")
    public void i_post_a_new_Shop_ssssHouse_Ad_to_Chotot_but_Chotot_rejects_my_Ad() {
        //Use : houseAdIdListOnDashboard
        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < removedAdIds.size(); i++) {
            moveAdToChototFree(getUserToken(), getShopAlias(), removedAdIds.get(i));
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), removedAdIds.get(i));
        }
    }

    @When("I should republish my removed Ad To Chotot again Free")
    public void i_post_a_new_Shop_ssssHouse_ssAd_to_Chotot_but_Chotot_rejects_my_Ad() {
        //Use : houseAdIdListOnDashboard
        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (String adIdDashboard : removedAdIds) {
            moveAdToChototFree(getUserToken(), getShopAlias(), adIdDashboard);
            verifyAdOnShopDashboardAndListing(getUserToken(), getShopAlias(), adIdDashboard);
            checkCartEmpty(getUserToken());
        }
    }

    @When("I should republish my removed Ad To Chotot again Paid")
    public void i_post_a_new_Shop_ssssHouse_ssssAd_to_Chotot_but_Chotot_rejects_my_Ad() {
        //Use : houseAdIdListOnDashboard
        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (String adIdDashboard : removedAdIds) {
            moveAdToChototPaid(getUserToken(), adIdDashboard);
            checkCartOrder(getUserToken(), 1);
            paymentOrder();
            verifyAdOnShopDashboardAndListing(getUserToken(), getShopAlias(), adIdDashboard);
            checkCartEmpty(getUserToken());
        }
    }

    @Given("I post a FREE Shop House Ad_To_Chotot but Chotot rejects my Ad")
    public void i_post_a_new_Shop_House_Ad_to_Chotot_but_Chotot_rejects_my_Ad() {
        adID = cm_api_ads_insertPTY.insertNewAdHouseShop("reject", true, false);
        adIDRejected = adID;
    }

    @Given("I edit my rejected Ad but Chotot rejects my Ad again")
    @Given("I edit my rejected Ad but Chotot refused my request")
    public void i_post_a_nessws_Shop_House_Ad_to_Chotot_but_Chotot_rejects_my_Ad() {
        adEdited = cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "reject", true, false);
    }

    @Then("I should see my Ad displays on Shop Dashboard")
    public void scheck() {
        verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adEdited);
    }

    @Then("I should see my Ad displays on Shop Dashboard and Listing")
    public void sscheck() {
        verifyAdOnShopDashboardAndListing(getUserToken(), getShopAlias(), adEdited);
    }

    @Given("I edit my rejected Ad and Chotot is reviewing my request")
    public void i_post_a_nesssssws_Shop_House_Ad_to_Chotot_but_Chotot_rejects_my_Ad() {
        cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "", true, false);
    }

    @Given("I post a PAID Shop House Ad_To_Chotot but Chotot rejects my Ad")
    public void i_post_a_news_Shop_House_Ad_to_Chotot_but_Chotot_rejects_my_Ad() {
        adID = cm_api_ads_insertPTY.insertNewAdHouseShop("rejectpay", true, false);
        adIDRejected = adID;
    }

    @Given("I post a Shop House Ad_To_Shop but Chotot rejects my Ad")
    public void i_post_a_news_Shop_House_Asd_to_Chotot_but_Chotot_rejects_my_Ad() {
        adID = cm_api_ads_insertPTY.insertNewAdHouseShop("reject", false, false);
        adIDRejected = adID;
    }

    //    @When("Chotot promotes {string} Free Listing Ad for House Ad of my PTY Shop")
//    @When("Chotot promotes {string} Free Listing Ad for House Ad")
    @When("Chotot promotes {string} Free Listing for my Shop PTY")
    public void chotot_promotes_Free_Listing_Ad_for_me(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing_PTY_House(getUserGroupID(), getUserAccountID(), Integer.parseInt(numberOfFreeAd));
    }

    @When("Chotot promotes {string} Free Listing Ad for House Ad when the Promotion is NOT STARTED but ACTIVE")
    public void chotot_promotes_Free_Lisssting_Ad_for_me(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing_NotStarted(getUserGroupID(), getUserAccountID(), CATEID_PTY_HOUSE, Integer.parseInt(numberOfFreeAd), true);
    }

    @When("Chotot promotes {string} Free Listing Ad for House Ad when the Promotion is STARTED but INACTIVE")
    public void chotot_promotes_Free_Lisssting_Aad_for_me(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing(getUserGroupID(), getUserAccountID(), CATEID_PTY_HOUSE, Integer.parseInt(numberOfFreeAd), false);
    }

    @When("Chotot promotes {string} Free Listing Ad for House Ad when the Promotion is on TODAY")
    public void chotot_promotes_Free_Lisssting_Ad_fossr_me(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing_StartedOnToday(getUserGroupID(), getUserAccountID(), CATEID_PTY_HOUSE, Integer.parseInt(numberOfFreeAd), true);
    }

    @When("Chotot promotes {string} Free Listing Ad for House Ad but the Promotion is EXPIRED")
    public void chotot_pssssromotes_Free_Lisssting_Ad_fossr_me(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing_Expired(getUserGroupID(), getUserAccountID(), CATEID_PTY_HOUSE, Integer.parseInt(numberOfFreeAd), true);
    }

    @When("Chotot promotes {string} Free Listing Ad for Car Ad of my VEH Shop")
    public void chotot_promotes_Free_Listing_Ad_for_sme(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing_VEH_Car(getUserGroupID(), getUserAccountID(), Integer.parseInt(numberOfFreeAd));
    }

    @When("Chotot promotes {string} Free Listing Ad for Phone Ad of my ELT Shop")
    public void chotot_promotes_Free_Lissting_Ad_for_sme(String numberOfFreeAd) {
        createPromotionGroupByUser(getUserAccountID());
        setPromotionGroup_FreeListing_ELT_Phone(getUserGroupID(), getUserAccountID(), Integer.parseInt(numberOfFreeAd));
    }

    //VUHOANG DEBUG
    @When("I edit my Shop House Ad_To_Chotot as Free Ad")
    @When("I should edit my Shop House Ad_To_Chotot as Free Ad")
    public void i_edit_my_new_Shop_House_Ad_to_Chotot() {
        global_accessToken = getUserToken();
        cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "accept", true, false);
        checkCartEmpty(getUserToken());
    }

    @When("I edit my Shop House Ad_To_Chotot as Paid Ad")
    @When("I should edit my Shop House Ad_To_Chotot as Paid Ad")
    public void i_edit_my_new_Shop_Housse_Ad_to_Csshotot() {
        global_accessToken = getUserToken();
        cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "acceptpay", true, false);
        checkCartEmpty(getUserToken());
    }

    @When("I should edit my Shop House Ad_To_Shop as Free Ad")
    @When("I edit my Shop House Ad_To_Shop as Free Ad")
    public void i_edit_my_new_Shop_House_Assd_to_Chotot() {
        global_accessToken = getUserToken();
        String adID = cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "accept", false, false);
        houseAdIdListOnDashboard.add(adID);
        checkCartEmpty(getUserToken());
    }

    @Then("I should edit to publish my House Ad as Paid successfully")
    public void i_edit_my_new_Shop_Housse_Ad_to_Chotot() {
        global_accessToken = getUserToken();
        cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "accept", true, false);
        checkCartOrder(getUserToken(), 1);
    }


    @Then("I should edit my Ad to Ad_To_ShopDashboard as Free")
    public void i_edit_ssmy_new_Shop_Housse_Ad_to_Chotot() {
        global_accessToken = getUserToken();
        cm_api_ads_insertPTY.editNewAdHouseShop(adIDRejected, "accept", false, false);
        checkCartEmpty(getUserToken());
    }

    @Then("My Cart should have {string} order")
    public void my_Edited_Ad_should_be_paid(String numberOfOrder) {
        //Check adID in Cart
        checkCartOrder(getUserToken(), Integer.parseInt(numberOfOrder));      //Verify Cart sau reject paid ad
    }

    @Then("I should be able to post {string} Free Shop House Ad to Chotot")
    public void i_should_be_able_to_post_Free_Shop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
            houseAdIdList.add(adID);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertPTY.insertNewAdHouseShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //4. Clear Cart Order for next step
        paymentOrder();
    }

    @Then("I should be able to publish to Chotot {string} Paid Shop House Ad")
    @Then("I should be able to publish to Chotot {string} of my Ad as Paid")
    public void sspublish_to_chotosst(String numberOfFreeAd) {
        //Use : houseAdIdListOnDashboard
        Assert.assertTrue(houseAdIdListOnDashboard.size() > 0, "NOT ANY AD ON DASHBOARD");
        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        String adIdDashboard = "";
        int i = 0;
        for (i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adIdDashboard = houseAdIdListOnDashboard.get(i);
            //Dang tin len chotot
            moveAdToChototPaid(getUserToken(), adIdDashboard);
            checkCartOrder(getUserToken(), 1);
            paymentOrder();
            verifyAdOnShopDashboardAndListing(getUserToken(), getShopAlias(), adIdDashboard);
        }
    }

    @When("I publish Ad to Chotot as Free")
    public void sspublisssh_to_chotot() {
        //Use : houseAdIdListOnDashboard
        Assert.assertTrue(houseAdIdListOnDashboard.size() > 0, "NOT ANY AD ON DASHBOARD");

        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (String adIdDashboard : houseAdIdListOnDashboard) {
            moveAdToChototFree(getUserToken(), getShopAlias(), adIdDashboard);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adIdDashboard);
        }
    }

    @Then("I should publish Ad to Chotot as Paid")
    @And("I publish Ad to Chotot as Paid")
    @And("I should publish The rest of Ads to Chotot as Paid")
    public void psublish_to_chotot() {
        //Use : houseAdIdListOnDashboard
        Assert.assertTrue(houseAdIdListOnDashboard.size() > 0, "NOT ANY AD ON DASHBOARD");

        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        String adIdDashboard = houseAdIdListOnDashboard.get(houseAdIdListOnDashboard.size() - 1);
        //Dang tin len chotot
        moveAdToChototPaid(getUserToken(), adIdDashboard);
        checkCartOrder(getUserToken(), 1);   //Make sure ad is free
        paymentOrder();
        verifyAdOnShopDashboardAndListing(getUserToken(), getShopAlias(), adIdDashboard);
    }

    @Then("I should be able to post {string} Free Shop House Ad to Shop Dashboard")
    public void i_should_be_able_to_posts_Frsee_Shop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            checkCartEmpty(getUserToken());     //Make sure ad is free
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", false, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
            houseAdIdList.add(adID);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertPTY.insertNewAdHouseShop("no action", false, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //4. Clear Cart Order for next step
        paymentOrder();
    }


    @Then("I should be able to post {string} Free VEH Shop Ad")
    @Then("I should be able to post {string} Free VEH Shop Car Ad")
    public void i_should_be_able_to_post_Free_Shsop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            checkCartEmpty(getUserToken());     //Make sure ad is free
            cm_api_ads_insertVEH.insertNewAdCarShop("accept", true);
            checkCartEmpty(getUserToken());     //Make sure ad is free
//            redeemOneAdInCampaign(getUserAccountID(), adID, getCam);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertVEH.insertNewAdCarShop("no action", true);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //pay for next step
        paymentOrder();
    }


    @Then("I shouldn't be able to post Free VEH Shop Motorbike Ad")
    public void i_should_be_able_to_post_Free_Shssop_Ad() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT(global_accessToken);

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        checkCartEmpty(getUserToken());     //Make sure ad is free
        cm_api_ads_insertVEH.insertNewAdMotorbikeShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //3. Check balance is changed
        cm_api_ads_insertVEH.insertNewAdMotorbikeShop("acceptpay", true, false);
        int afterPostAdBalance = getBalanceDT(global_accessToken);
        System.out.println("afterPostAdBalance: " + afterPostAdBalance);
        System.out.println("currentDT: " + currentDT);
        Assert.assertNotEquals(afterPostAdBalance, currentDT, "DONG TOT DOESN'T CHANGE, PROMOTION DOESN'T WORK");
    }

    @Then("I shouldn't be able to post Free Shop Apartment Ad")
    public void i_shosuld_be_able_to_post_Free_Shssop_Ad() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        checkCartEmpty(getUserToken());     //Make sure ad is free
        cm_api_ads_insertPTY.insertNewAdApartmentShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //3. Check balance is changed
        cm_api_ads_insertPTY.insertNewAdApartmentShop("acceptpay", true, false);
        int afterPostAdBalance = getBalanceDT();
        Assert.assertNotEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I shouldn't be able to post Free Shop House Ad")
    @Then("I shouldn't be able to post Free Shop House Ad To Chotot")
    public void i_shosuld_be_able_to_post_Free_Shssssop_Ad() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        checkCartEmpty(getUserToken());     //Make sure ad is free
        cm_api_ads_insertPTY.insertNewAdHouseShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //3. Check balance is changed
        cm_api_ads_insertPTY.insertNewAdHouseShop("acceptpay", true, false);
        int afterPostAdBalance = getBalanceDT();
        Assert.assertNotEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }


    @Then("I should be able to post {string} Free Pro House Ad")
    public void i_shosuld_be_able_to_post_Frees_Shssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            cm_api_ads_insertPTY.insertNewAdHousePro_NoUploadNewImage("no action");
        }
        checkCartOrder(getUserToken(), 0);


        cm_api_ads_insertPTY.insertNewAdHousePro_NoUploadNewImage("no action");
        checkCartOrder(getUserToken(), 1);
    }

    @Then("I should be able to post {string} Free Private House Ad")
    public void i_shosuld_be_able_to_post_Frees_sShssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            cm_api_ads_insertPTY.insertNewAdHouse_NoUploadNewImage("no action");
        }
        checkCartOrder(getUserToken(), 0);


        cm_api_ads_insertPTY.insertNewAdHouse_NoUploadNewImage("no action");
        checkCartOrder(getUserToken(), 1);
    }

    @Then("I post {string} Free Private House Ad on Listing")
    public void i_shosuld_be_able_to_spost_Frees_sShssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            cm_api_ads_insertPTY.insertNewAdHouse_NoUploadNewImage("accept");
        }
        checkCartOrder(getUserToken(), 0);

        cm_api_ads_insertPTY.insertNewAdHouse_NoUploadNewImage("no action");
        checkCartOrder(getUserToken(), 1);

        //For next step
        topupDongTotWithMomo_500k(getUserPhone());
        paymentOrder();
    }


    @When("I post {string} Free Shop House Ad to Chotot")
    public void i_shosuld_be_able_to_posts_Frsee_Shssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnListing.add(adID);
        }
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @And("I post {string} Shop House Ad to Chotot")
    @And("I post {string} Shop House Ad to Chotot as Free")
    public void i_shosuld_be_able_to_posts_Fssrsee_Shssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnListing.add(adID);
        }
        checkCartEmpty(getUserToken());
    }

    @And("I post {string} Shop House Ad to Chotot as Paid")
    public void i_shosuld_be_able_to_posts_Fssrsee_Shssssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnListing.add(adID);
        }
    }

    @And("I post {string} Shop House Ad to Chotot as Paid and Pay for it")
    public void i_shosuld_be_ablse_to_posts_Fssrsee_Shssssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnListing.add(adID);
        }
        paymentOrder();
    }


    @And("I post {string} Shop House Ad to Chotot and Chotot is reviewing my ad")
    public void i_shosuld_be_able_to_postss_Fssrsee_Shssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("noaction", true, false);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnListing.add(adID);
        }
    }

    @When("I pay for my Ad to Chotot")
    public void stest() {
        paymentOrder(getUserToken());
    }

    @When("I post {string} Shop House Ad to Shop Dashboard")
    public void i_shosuld_be_able_to_posts_Frsesssse_Shssop_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", false, false);
            verifyAdOnShopDashboard(getUserToken(), getShopAlias(), adID);
            houseAdIdListOnDashboard.add(adID);
        }
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region TP.Hồ Chí Minh")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_with_region_hcm() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false, REGION_HCM_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region Đồng Nai")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_with_regioni() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false, REGION_DONGNAI_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region Bà Rịa Vũng Tàu")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_with_region_hr() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false, REGION_VUNGTAU_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region Tây Ninh")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_with_s() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false, REGION_TAYNINH_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region Bình Phước")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_withs() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false, REGION_BINHPHUOC_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region Bình Dương")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_with_regios() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false, REGION_BINHDUONG_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post Free Shop House Ad to Chotot with region Hà Nội")
    public void i_should_be_able_to_post_Free_Shop_House_Ad_to_Chotot_wissth_regios() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdHouseShop("", true, false, REGION_HANOI_ID);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }


    @Then("I should be able to post Free Shop Office Ad to Chotot")
    public void i_should_be_able_to_sspost_Free_Shop_House_Ad_to_Chotot_wissth_regios() {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        cm_api_ads_insertPTY.insertNewAdOfficeShop("accept", true, false);
        checkCartEmpty(getUserToken());     //Make sure ad is free

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");
    }

    @Then("I should be able to post {string} Free Rent Shop House Ad to Chotot")
    public void i_should_be_able_to_sspost_Free_Shop_Houses_Ad_to_Chotot_wissth_regios(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            cm_api_ads_insertPTY.insertNewAdHouseShopRent("accept", true, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
        }

        cm_api_ads_insertPTY.insertNewAdHouseShopRent("accept", true, false);
        checkCartOrder(getUserToken(), 1);

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        paymentOrder();
    }


    //No use
    @Given("I login my account to get Promotion for Shop ELT Ad")
    public void login_promotion_shopELT() {
        setUserToken(getAccessTokenNewUserShopELT());
        setUserAccountID(global_accountID);
    }

    @Given("I login my account to get Promotion for Shop VEH Ad")
    public void login_promotion_shopVEH() {
        setUserToken(getAccessTokenNewUserShopVEH());
        setUserAccountID(global_accountID);
    }

    //
//    @Given("I login my account to get Promotion for Shop VEH Car Ad")
//    public void login_promotion_shopVEHcar() {
//        setUserToken(getAccessTokenNewUserShopVEH());
//        setUserAccountID(global_accountID);
//        setUserPhone(newUserPhone);
//    }
//
//    @When("I post all of 8 Free Shop Car Ad onto Listing")
//    public void login_promsotion_shopVEH() {
//        global_accessToken = getUserToken();
//        int balanceBeforePostAd = getBalanceDT();
//        for (int i = 0; i < 8; i++) {
//            cm_api_ads_insertVEH.insertNewAdCarShop("accept", true);
//        }
//        checkCartEmpty(getUserToken());
//        int balanceAfterPostAd = getBalanceDT();
//        Assert.assertEquals(balanceBeforePostAd, balanceAfterPostAd, "BALANCE CHANGES WHEN POST FREE AD");
//        cm_api_ads_insertVEH.insertNewAdCarShop("acceptpay", true);
//    }
//
    @When("I post all of 8 Free Shop Motorbike Ad onto Listing")
    public void login_promsotion_shopVEHmo() {
        if (getUserToken() != null)
            global_accessToken = getUserToken();
        int balanceBeforePostAd = getBalanceDT();
        for (int i = 0; i < 7; i++) {
            cm_api_ads_insertVEH.insertNewAdMotorbikeShop("accept", true, false);
        }
        checkCartEmpty(global_accessToken);
        int balanceAfterPostAd = getBalanceDT();
        Assert.assertEquals(balanceBeforePostAd, balanceAfterPostAd, "BALANCE CHANGES WHEN POST FREE AD");
//        cm_api_ads_insertVEH.insertNewAdMotorbikeShop("acceptpay", true, false);
    }

    @When("I post all of 30 Free Shop Phone Ad on Listing")
    public void login_promsotxxion_shopVEH() {
        if (getUserToken() != null)
            global_accessToken = getUserToken();
        for (int i = 0; i < 29; i++) {
            cm_api_ads_insertELT.insertNewAdPhoneShop("accept", true, false);
        }
        checkCartEmpty(global_accessToken);
    }

    @Then("I shouldn't be able to post Free Phone Ad")
    public void i_shosuld_be_able_to_post_Fresse_Shssssop_Ad() {
        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        checkCartEmpty(getUserToken());     //Make sure ad is free
        String adID = cm_api_ads_insertELT.insertNewAdPhonePro("no action");
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge
    }

    @Then("I shouldn't be able to post Free Motorbike Ad")
    public void i_shosuld_be_able_to_post_ssFresse_Shssssop_Ad() {
        global_accessToken = getUserToken();

        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        checkCartEmpty(getUserToken());     //Make sure ad is free
        cm_api_ads_insertVEH.insertNewAdMotorbikeForPro("no action");
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge
    }

    //-------- STEP EDIT -----------
    @Then("My Free Slot should be still available")
    public void free_slot() {
        verifyFreeSlotAvailable(getUserAccountID(), 1000);
    }

    @Then("My Free Slot should be unavailable")
    public void free_slot_not() {
        verifyFreeSlotNotAvailable(getUserAccountID(), 1000);
    }


    @Then("I should be able to post {string} Free Shop Land Ad to Chotot")
    public void i_should_be_able_to_post_Free_Shops_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdLandShop("accept", true, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
            houseAdIdList.add(adID);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertPTY.insertNewAdLandShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //4. Clear Cart Order for next step
        paymentOrder();
    }

    @Then("I should be able to post {string} Free Shop Apartment Ad to Chotot")
    public void i_should_be_able_to_post_Frese_Shops_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdApartmentShop("accept", true, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
            houseAdIdList.add(adID);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertPTY.insertNewAdApartmentShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //4. Clear Cart Order for next step
        paymentOrder();
    }

    @Then("I should be able to post {string} Free Shop Office Ad to Chotot")
    public void i_should_be_able_to_post_Frese_Sshops_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdOfficeShop("accept", true, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
            houseAdIdList.add(adID);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertPTY.insertNewAdOfficeShop("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //4. Clear Cart Order for next step
        paymentOrder();
    }

    @Then("I should be able to post {string} Free Shop Room Lease Ad to Chotot")
    public void i_should_be_able_to_post_Frese_Ssshops_Ad(String numberOfFreeAd) {
        global_accessToken = getUserToken();

        //1. Check DT doesn't change
        int currentDT = getBalanceDT();

        String adID = "";
        //2. Check CART is always empty when user post numberOfFreeAd of free ad
        for (int i = 0; i < Integer.parseInt(numberOfFreeAd); i++) {
            adID = cm_api_ads_insertPTY.insertNewAdRoomShopLease("accept", true, false);
            checkCartEmpty(getUserToken());     //Make sure ad is free
            houseAdIdList.add(adID);
        }

        //3. Check after free ad of Promotion, Ad to listing is charge
        cm_api_ads_insertPTY.insertNewAdRoomShopLease("no action", true, false);
        checkCartOrder(getUserToken(), 1);     //Make sure ad is charge

        //Verify (1)
        int afterPostAdBalance = getBalanceDT();
        Assert.assertEquals(afterPostAdBalance, currentDT, "DONG TOT CHANGES, PROMOTION DOESN'T WORK");

        //4. Clear Cart Order for next step
        paymentOrder();
    }



    //SMOKE TEST
    private static String adIDSmoke = "";
    @Given("SMOKE Post and publish a Shop House Ad_To_Shop as paid")
    public void i_post_a_Sshop_Housse_Ad_To_Chotot_which_is_refused() {
        global_accessToken = getShopUserToken();
        adIDSmoke = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", false, false);
        moveAdToChototPaid(global_accessToken, adIDSmoke);
    }

    @Given("SMOKE Post a Shop House Ad_To_Chotot")
    public void i_post_a_Shop_Housse_Ad_To_Chotot_which_is_refused() {
        global_accessToken = getShopUserToken();
        adIDSmoke = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, false);
    }

    @Given("SMOKE Post and publish a Shop House Ad_To_Shop as free")
    public void i_post_a_Sshop_Housse_Ad_To_Chotot_which_iss_refused() {
        global_accessToken = getShopUserToken();
        adIDSmoke = cm_api_ads_insertPTY.insertNewAdHouseShop("accept", false, false);
        moveAdToChototFree(global_accessToken, getShopAlias(), adIDSmoke);
    }

    @Given("SMOKE Check Ad_To_Shop is published")
    public void i_post_as_Sshop_Housse_Ad_To_Chotot_which_is_refused() {
        getShopAdInfo(adIDSmoke);
    }


}
