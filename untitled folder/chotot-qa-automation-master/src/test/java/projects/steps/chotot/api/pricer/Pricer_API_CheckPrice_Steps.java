package projects.steps.chotot.api.pricer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projects.functions.chotot.pricer.Pricer_API_CheckPrice_Functions;

import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class Pricer_API_CheckPrice_Steps extends Pricer_API_CheckPrice_Functions {

    @Then("I login my account to check price")
    public void login() {
        setUserToken(getAccessTokenOfNewUser());
        setUserAccountID(global_accountID);

        System.out.println("PHONE: " + tempUserPhone);
        System.out.println("Account ID: " + getUserAccountID());
    }

    @Then("I login my account with Shop PTY to check price")
    public void loginSHOPPTY_tocheckprice() {
        setUserToken(getAccessTokenNewUserShopPTY());
        setUserAccountID(global_accountID);
    }

    @Then("I login my account with Shop ELT to check price")
    public void loginSHOPELT_tocheckprice() {
        setUserToken(getAccessTokenNewUserShopELT());
        setUserAccountID(global_accountID);
    }

    @Then("I login my account with Shop VEH to check price")
    public void loginSHOPVEH_tocheckprice() {
        setUserToken(getAccessTokenNewUserShopVEH());
        setUserAccountID(global_accountID);
    }

    //--------------- CHECK BUMP ---------------
    @Then("Bump Price of all subcates should be correct")
    public void bump_Price_of_all_subcates_should_be_correct() {
        verifyBumpPrice_VND();
    }

    @Then("Bump 3 days Price of all subcates should be correct")
    public void bump_3_days_Price_of_all_subcates_should_be_correct() {
        verifyBump3DaysPrice_VND();
    }

    @Then("Bump 7 days Price of all subcates should be correct")
    public void bump_7_days_Price_of_all_subcates_should_be_correct() {
        verifyBump7DaysPrice_VND();
    }

    @Then("Bump Timer Price of all subcates should be correct")
    public void bump_Timer_Price_of_all_subcates_should_be_correct() {
        verifyBumpTimerPrice_VND();
    }

    @Then("Bump Price Dong Tot of all subcates should be correct")
    public void bump_Price_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyBumpPrice_DongTot();
    }

    @Then("Bump 3 days Price Dong Tot of all subcates should be correct")
    public void bump_3days_Price_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyBump3DaysPrice_DongTot();
    }

    @Then("Bump 7 days Price Dong Tot of all subcates should be correct")
    public void bump_7days_Price_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyBump7DaysPrice_DongTot();
    }

    @Then("Bump Timer Price Dong Tot of all subcates should be correct")
    public void bump_Timer_Price_Promotion_of_all_subcates_should_be_correct() {
        verifyBumpTimerPrice_DongTot();
    }




    @Then("Bump Price UNIT DongTot of all subcates should be correct")
    public void bump_Pricse_Dong_Promsotion_of_all_subcates_should_be_correct() {
        verifyBumpPriceUnit_DongTot();
    }

    @Then("Bump 3 days Price UNIT DongTot of all subcates should be correct")
    public void bump_3days_Pssrice_Dong_Promotiont_of_all_subcates_should_be_correct() {
        verifyBump3DaysPriceUnit_DongTot();
    }

    @Then("Bump 7 days Price UNIT DongTot of all subcates should be correct")
    public void bump_7days_Prisce_Dong_Promsotion_of_all_subcates_should_be_correct() {
        verifyBump7DaysPriceUnit_DongTot();
    }

    @Then("Bump Timer Price UNIT DongTot of all subcates should be correct")
    public void bump_Timer_Pricse_Dong_sTot_of_all_subcates_should_be_correct() {
        verifyBumpTimerPriceUnit_DongTot();
    }


    @Then("Bump Price UNIT VND of all subcates should be correct")
    public void bump_Pricse_Dong_Promotion_of_all_subcates_should_be_correct() {
        verifyBumpPriceUnit_VND();
    }

    @Then("Bump 3 days Price UNIT VND of all subcates should be correct")
    public void bump_3days_Psrice_Dong_Promotiont_of_all_subcates_should_be_correct() {
        verifyBump3DaysPriceUnit_VND();
    }

    @Then("Bump 7 days Price UNIT VND of all subcates should be correct")
    public void bump_7days_Prisce_Dong_Promotion_of_all_subcates_should_be_correct() {
        verifyBump7DaysPriceUnit_VND();
    }

    @Then("Bump Timer Price UNIT VND of all subcates should be correct")
    public void bump_Timer_Pricse_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyBumpTimerPriceUnit_VND();
    }



    @Then("Bump Price UNIT Promotion of all subcates should be correct")
    public void bump_Price_Dong_Promotion_of_all_subcates_should_be_correct() {
        verifyBumpPriceUnit_Promotion();
    }

    @Then("Bump 3 days Price UNIT Promotion of all subcates should be correct")
    public void bump_3days_Price_Dong_Promotiont_of_all_subcates_should_be_correct() {
        verifyBump3DaysPriceUnit_Promotion();
    }

    @Then("Bump 7 days Price UNIT Promotion of all subcates should be correct")
    public void bump_7days_Price_Dong_Promotion_of_all_subcates_should_be_correct() {
        verifyBump7DaysPriceUnit_Promotion();
    }

    @Then("Bump Timer Price UNIT Promotion of all subcates should be correct")
    public void bump_Timer_Price_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyBumpTimerPriceUnit_Promotion();
    }



    //--------------- CHECK PRICER ---------------
    @Then("Ad Feature Price of all subcates should be correct")
    public void ad_Feature_Price_of_all_subcates_should_be_correct() {
        verifyAdFeature_VND();
    }

    @Then("Ad Feature Price Dong Tot of all subcates should be correct")
    public void ad_Feature_Price_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyAdFeature_DongTot();
    }

    @Then("Ad Feature Price Promotion of all subcates should be correct")
    public void ad_Feature_Price_Promotion_of_all_subcates_should_be_correct() {
        verifyAdFeature_Promotion();
    }

    @Then("Ad Feature Internal Price of all subcates should be correct")
    public void ad_InternalFeature_Price_of_all_subcates_should_be_correct() {
        verifyAdFeatureInternal_VND();
    }

    @Then("Ad Feature Internal Price Dong Tot of all subcates should be correct")
    public void ad_InternalFeature_Price_Dong_Tot_of_all_subcates_should_be_correct() {
        verifyAdFeatureInternal_DongTot();
    }

    @Then("Ad Feature Internal Price Promotion of all subcates should be correct")
    public void ad_InternalFeature_Price_Promotion_of_all_subcates_should_be_correct() {
        verifyAdFeatureInternal_Promotion();
    }






    @Then("Listing Fee Services PTY Sell Ad Cate {string} which are applied for all regions should be correct")
    public void listing_Fee_VND_of_Sell_Ad(String cateIds) {
        verifyListingFeePrice_SellAd_PTY_AllRegions(cateIds);
    }



    //------------- LISTING FEE /services -------------
    //Listing Fee PTY Private
    @Then("Listing Fee Services PTY Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_Fee_VND_of_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_SellAd_PTY_AllRegions(cateIds);
    }


    @Then("Listing Fee PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_Fee_For_Rent_region_cate_should_be_correct() {
        verifyListingFeePrice_RentAd_PTY_13000();
    }

    //Listing Fee VEH Private
    @Then("Listing Fee Services VEH Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_Fee_VND_VEHof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_SellAd_VEH_AllRegions(cateIds);

    }


    //Listing Fee ELT Private
    @Then("Listing Fee Services ELT Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_Fee_VND_ELTof_Sell_Ad_region(String cateIds, String regions) {
//        verifyListingFeePrice_SellAd_ELT_AllRegions(cateIds);
        verifyListingFeePrice_SellAd_VEH_AllRegions(cateIds);
    }


    //Listing Fee PTY Shop
    @Then("Listing Fee Services PTY Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_Fee_VND_of_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_SellAdShop_PTY_AllRegions(cateIds);
    }

    @Then("Listing Fee PTY For Rent Ad Shop Dashboard To Chotot which are applied for only region 13000 should be correct")
    public void listing_Fee_For_Rent_region_cate_should_be_correctPro() {
        verifyListingFeePrice_RentAdShop_PTY_13000();
    }

    //Listing Fee VEH Shop
    @Then("Listing Fee Services VEH Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_Fee_VND_VEHof_ProSell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_SellAdShop_VEH_AllRegions(cateIds);
    }

    //Listing Fee ELT Shop
    @Then("Listing Fee Services ELT Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_Fee_VND_ELTof_Sell_Ad_regionPro(String cateIds, String regions) {
//        verifyListingFeePrice_SellAdShop_ELT_AllRegions(cateIds);     //Inside code is outdate
        verifyListingFeePrice_SellAdShop_VEH_AllRegions(cateIds);
    }


    //Listing Fee PTY Shop of a specified User
    @Then("Listing Fee Services my PTY Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fee_VND_of_Sell_Ad_regionPro_account(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "shop_to_chotot", false);
    }

    @Then("Listing Fee Services my PTY Sell Ad Private Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fee_VND_of_Sell_Ad_regionPro_saccount(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "insert_ads", false);
    }

    @Then("Listing Fee Services my VEH Sell Ad Private Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    @Then("Listing Fee Services my ELT Sell Ad Private Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fee_VND_of_Sells_Ad_regionPro_saccount(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "insert_ads", true);
    }

    @Then("Listing Fee Services my PTY Sell Ad Private Region Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fee_VND_of_Sell_Ad_regisonPro_saccount(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "insert_ads_with_regions", false);
    }

    @Then("Listing Fee Services my VEH Sell Ad Private Region Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    @Then("Listing Fee Services my ELT Sell Ad Private Region Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fees_VND_of_Sell_Ad_regisonPro_saccount(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "insert_ads_with_regions", true);
    }

    @Then("Listing Fee Services my PTY Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void my_listing_Fee_VND_of_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_SellAd_PTY_AllRegions(cateIds, "insert_ads_with_regions");
    }

    @Then("Listing Fee Services my PTY Sell Ad Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
    public void my_listing_Fee_VND_of_Sell_Ad_region13000Pro() {
        verifyListingFeePrice_SellAdShopSpecified_PTY_13000(getUserAccountID());
    }

    @Then("Listing Fee my PTY For Rent Ad Shop Dashboard To Chotot which are applied for only region 13000 should be correct")
    public void my_listing_Fee_For_Rent_region_cate_should_be_correct() {
        verifyListingFeePrice_RentAd_PTY_13000("insert_ads_with_regions");
    }
    @Then("Listing Fee my PTY For Rent Ad Shop Dashboard To Chotot which are applied for only region 13000 should be correct for a specified account")
    public void my_listing_Fee_For_Rent_region_cate_should_sbe_correct() {
        verifyListingFeePrice_AdSpecified_AllRegions("", getUserAccountID(), false, "shop_to_chotot", false);
    }

    @Then("Listing Fee my PTY For Rent Ad Private Dashboard To Chotot which are applied for only region 13000 should be correct for a specified account")
    public void my_listing_Fee_For_Rent_region_casstesss_should_sbe_correct() {
        verifyListingFeePrice_AdSpecified_AllRegions("", getUserAccountID(), false, "insert_ads", false);
    }

    @Then("Listing Fee my PTY For Rent Ad Private Region Dashboard To Chotot which are applied for only region 13000 should be correct for a specified account")
    public void my_listing_Fee_For_Rent_region_casstesss_ssshould_sbe_correct() {
        verifyListingFeePrice_AdSpecified_AllRegions("", getUserAccountID(), false, "insert_ads_with_regions", false);
    }

    //Listing Fee VEH Shop
    @Then("Listing Fee Services my VEH Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fee_VND_VEHof_ProSell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "shop_to_chotot", true);
    }

    @Then("Listing Fee Services my VEH Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void my_listing_Fee_VND_VEHof_ProSell_Ad_regisson(String cateIds, String regions) {
        verifyListingFeePrice_SellAd_VEH_AllRegions(cateIds, "insert_ads_with_regions");
    }

    @Then("Listing Fee Services my VEH Sell Ad Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
    public void my_listing_Fee_VEH_VND_of_Sell_Ad_region13000Pro() {
        verifyListingFeePrice_SellAdShopSpecified_VEH_13000(getUserAccountID());
    }

    //Listing Fee ELT Shop
    @Then("Listing Fee Services my ELT Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct for a specified account")
    public void my_listing_Fsee_VND_ELTof_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_AdSpecified_AllRegions(cateIds, getUserAccountID(), true, "shop_to_chotot", true);
    }

    @Then("Listing Fee Services my ELT Sell Ad Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void my_listing_Fee_VND_ELTof_Sell_Ad_regionPro(String cateIds, String regions) {
//        verifyListingFeePrice_SellAd_ELT_AllRegions(cateIds, "insert_ads_with_regions");      //OUTdate
        verifyListingFeePrice_SellAd_VEH_AllRegions(cateIds, "insert_ads_with_regions");
    }

    @Then("Listing Fee Services my ELT Sell Ad Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
    public void my_listing_Fee_ELT_VND_of_Sell_Ad_region13000Pro() {
        verifyListingFeePrice_SellAdShopSpecified_ELT_13000(getUserAccountID());
    }



    //------------- LISTING FEE /pricer -------------
    //PTY all regions
    @Then("Listing Fee Pricer Internal PTY Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_of_Sell_Ad_region_a(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAd_PTY_AllRegions(cateIds, "", getUserAccountID());
    }
    @Then("Listing Fee Pricer Private PTY Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_of_Sell_Ad_region_b(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAd_PTY_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }

    //PTY rent
    @Then("Listing Fee Pricer Private PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_FeePricer_For_Rent_region_cates_should_be_correct() {
        verifyListingFeePrice_Pricer_RentAd_PTY_13000(getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer Internal PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_FeePricer_For_Rent_regions_cate_should_be_correct() {
        verifyListingFeePrice_Pricer_RentAd_PTY_13000("", getUserAccountID());
    }

    //Listing Fee VEH all regions
    @Then("Listing Fee Pricer Internal VEH Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_VsEHof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAd_VEH_AllRegions(cateIds, "", getUserAccountID());
    }
    @Then("Listing Fee Pricer Private VEH Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_VEHof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAd_VEH_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }

    //ELT all regsion
    @Then("Listing Fee Pricer Internal ELT Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAd_ELT_AllRegions(cateIds, "", getUserAccountID());
    }
    @Then("Listing Fee Pricer Private ELT Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_ELT_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAd_ELT_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }


    //Listing Fee PTY Shop
    @Then("Listing Fee Pricer PTY Sell Ad Private Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_of_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAdShop_PTY_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer PTY Sell Ad Internal Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_of_Sell_Ad_regionPros(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAdShop_PTY_AllRegions(cateIds, "", getUserAccountID());
    }

    //Listing Fee PTY Shop Rent
    @Then("Listing Fee PTY For Rent Ad Private Shop Dashboard To Chotot which are applied for only region 13000 should be correct")
    public void listing_FeePricer_For_Rent_region_cate_shoulds_be_correctPro() {
        verifyListingFeePrice_Pricer_RentAdShop_PTY_13000(getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee PTY For Rent Ad Internal Shop Dashboard To Chotot which are applied for only region 13000 should be correct")
    public void listing_FeePricer_For_Rent_region_cate_shsould_be_correctPro() {
        verifyListingFeePrice_Pricer_RentAdShop_PTY_13000("", getUserAccountID());
    }

    //Listing Fee VEH Shop
    @Then("Listing Fee Pricer VEH Sell Ad Private Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_VEHof_ProSell_Asd_region(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAdShop_VEH_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer VEH Sell Ad Internal Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FesePricer_VND_VEHof_ProSell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAdShop_VEH_AllRegions(cateIds, "", getUserAccountID());
    }

    //Listing Fee ELT Shop
    @Then("Listing Fee Pricer ELT Sell Ad Private Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricer_VND_ELTof_Sell_Ad_regionsPro(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAdShop_ELT_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer ELT Sell Ad Internal Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePriscer_VND_EsLTof_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_Pricer_SellAdShop_ELT_AllRegions(cateIds, "", getUserAccountID());
    }


    //------------- LISTING FEE PRICER Goldpot  -----------------
//PTY all regions
    @Then("Listing Fee Pricer Goldpot Internal PTY Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_of_Sell_Ad_region_a(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAd_PTY_AllRegions(cateIds, "", getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot Private PTY Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_of_Sell_Ad_region_b(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAd_PTY_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }

    //PTY rent
    @Then("Listing Fee Pricer Goldpot Private PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_FeePricerGoldpot_For_Rent_region_cates_should_be_correct() {
        verifyListingFeePrice_PricerGoldpot_RentAd_PTY_13000(getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot Internal PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_FeePricerGoldpot_For_Rent_regions_cate_should_be_correct() {
        verifyListingFeePrice_PricerGoldpot_RentAd_PTY_13000("", getUserAccountID());
    }

    //Listing Fee VEH all regions
    @Then("Listing Fee Pricer Goldpot Internal VEH Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_VsEHof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAd_VEH_AllRegions(cateIds, "", getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot Private VEH Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_VEHof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAd_VEH_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }

    //ELT all regsion
    @Then("Listing Fee Pricer Goldpot Internal ELT Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAd_ELT_AllRegions(cateIds, "", getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot Private ELT Sell Ad Private Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_ELT_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAd_ELT_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }


    //Listing Fee PTY Shop
    @Then("Listing Fee Pricer Goldpot PTY Sell Ad Private Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_of_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAdShop_PTY_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot PTY Sell Ad Internal Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_of_Sell_Ad_regionPros(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAdShop_PTY_AllRegions(cateIds, "", getUserAccountID());
    }

    //Listing Fee PTY Shop Rent
    @Then("Listing Fee Goldpot PTY For Rent Ad Private Shop Dashboard To Chotot which are applied for only region 13000 should be correct")
    public void listing_FeePricerGoldpot_For_Rent_region_cate_shoulds_be_correctPro() {
        verifyListingFeePrice_PricerGoldpot_RentAdShop_PTY_13000(getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Goldpot PTY For Rent Ad Internal Shop Dashboard To Chotot which are applied for only region 13000 should be correct")
    public void listing_FeePricerGoldpot_For_Rent_region_cate_shsould_be_correctPro() {
        verifyListingFeePrice_PricerGoldpot_RentAdShop_PTY_13000("", getUserAccountID());
    }

    //Listing Fee VEH Shop
    @Then("Listing Fee Pricer Goldpot VEH Sell Ad Private Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_VEHof_ProSell_Asd_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAdShop_VEH_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot VEH Sell Ad Internal Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FesePricerGoldpot_VND_VEHof_ProSell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAdShop_VEH_AllRegions(cateIds, "", getUserAccountID());
    }

    //Listing Fee ELT Shop
    @Then("Listing Fee Pricer Goldpot ELT Sell Ad Private Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VND_ELTof_Sell_Ad_regionsPro(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAdShop_ELT_AllRegions(cateIds, getUserToken(), getUserAccountID());
    }
    @Then("Listing Fee Pricer Goldpot ELT Sell Ad Internal Shop Dashboard To Chotot Cate {string} which are applied for {string} should be correct")
    public void listing_FeePriscer_VND_ELTof_Sell_Ad_regionPro(String cateIds, String regions) {
        verifyListingFeePrice_PricerGoldpot_SellAdShop_ELT_AllRegions(cateIds, "", getUserAccountID());
    }




    //---------- Listing Fee Get All Listing ------------
    //ELT all regsion
    @Then("Listing Fee Pricer GetAllListing Internal PTY Sell Ad Internal Cate {string} which are applied for {string} should be correct")
    public void listing_sFeePricerGoldpot_VNssD_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAllListing_SellAd_AllRegions_PTY(cateIds);
    }

    @Then("Listing Fee Pricer GetAllListing Internal PTY Sell Ad Shop Internal Cate {string} which are applied for {string} should be correct")
    public void listing_sFeePricersGoldpot_VNssD_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAllListing_SellAdShop_AllRegions_PTY(cateIds);
    }

    @Then("Listing Fee Pricer GetAllListing Internal PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_FeePricers_For_Rent_region_cates_should_be_correct() {
        verifyListingFeePrice_PricerGetAllListing_RentAd_Region13000_PTY();
    }
    @Then("Listing Fee Pricer GetAllListing Internal PTY For Rent Ad Shop which are applied for only region 13000 should be correct")
    public void listing_FeePricer_Fosr_Rent_regions_cate_should_be_correct() {
        verifyListingFeePrice_PricerGetAllListing_RentAdShop_Region13000_PTY();
    }



    @Then("Listing Fee Pricer GetAllListing Internal VEH Sell Ad Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNssD_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAllListing_SellAd_AllRegions(cateIds);
    }

    @Then("Listing Fee Pricer GetAllListing Internal ELT Sell Ad Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNsD_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAllListing_SellAd_AllRegions(cateIds);
    }


    @Then("Listing Fee Pricer GetAllListing Internal VEH Sell Ad Shop Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNssD_ELTof_SeShopll_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAllListing_SellAdShop_AllRegions(cateIds);
    }

    @Then("Listing Fee Pricer GetAllListing Internal ELT Sell Ad Shop Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNsD_ELTof_Sell_ShopAd_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAllListing_SellAdShop_AllRegions(cateIds);
    }


    //---------- Listing Fee Get All ------------
    //ELT all regsion
    @Then("Listing Fee Pricer GetAll Internal PTY Sell Ad Internal Cate {string} which are applied for {string} should be correct")
    public void listing_sFeePricerGo_VNssD_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAll_SellAd_AllRegions_PTY(cateIds);
    }

    @Then("Listing Fee Pricer GetAll Internal PTY Sell Ad Shop Internal Cate {string} which are applied for {string} should be correct")
    public void listing_sFeePricersGoldpost_VNssD_ELTof_Sell_Ad_region(String cateIds, String regions) {
//        verifyListingFeePrice_PricerGetAll_SellAdShop_AllRegions_PTY(cateIds);
    }

    @Then("Listing Fee Pricer GetAll Internal PTY For Rent Ad which are applied for only region 13000 should be correct")
    public void listing_FeePricers_For_Renst_region_cates_should_be_correct() {
        verifyListingFeePrice_PricerGetAll_RentAd_Region13000_PTY();
    }
    @Then("Listing Fee Pricer GetAll Internal PTY For Rent Ad Shop which are applied for only region 13000 should be correct")
    public void listing_FeePricer_Fosr_sRent_regions_cate_should_be_correct() {
        verifyListingFeePrice_PricerGetAll_RentAdShop_Region13000_PTY();
    }



    @Then("Listing Fee Pricer GetAll Internal VEH Sell Ad Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpots_VNssD_ELTof_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAll_SellAd_AllRegions(cateIds);
    }

    @Then("Listing Fee Pricer GetAll Internal ELT Sell Ad Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNsD_ELTosf_Sell_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAll_SellAd_AllRegions(cateIds);
    }


    @Then("Listing Fee Pricer GetAll Internal VEH Sell Ad Shop Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNssD_ELsTof_SeShopll_Ad_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAll_SellAdShop_AllRegions(cateIds);
    }

    @Then("Listing Fee Pricer GetAll Internal ELT Sell Ad Shop Internal Cate {string} which are applied for {string} should be correct")
    public void listing_FeePricerGoldpot_VNsD_ELTof_Sell_sShopAd_region(String cateIds, String regions) {
        verifyListingFeePrice_PricerGetAll_SellAdShop_AllRegions(cateIds);
    }



    @Then("Sticky Ads Price of all subcates should be correct")
    public void Sticky_ads_Price_VND_in_1month_of_all_subcates_should_be_correct() {
        verifyStickyAdsPrice_VND();
    }

    @Then("Sticky Ads Dong Tot Price of all subcates should be correct")
    public void Sticky_ads_Price_VND_in_3months_of_all_subcates_should_be_correct() {
        verifyStickyAdsPrice_DongTot();
    }

    @Then("Sticky Ads Promotion Price of all subcates should be correct")
    public void Sticky_ads_Price_VND_in_6months_of_all_subcates_should_be_correct() {
        verifyStickyAdsPrice_Promotion();
    }


    @Then("Sticky Ads Internal Price of all subcates should be correct")
    public void Sticky_asds_Price_VND_in_1month_of_all_subcates_should_be_correct() {
        verifyStickyAdsInternalPrice_VND();
    }

    @Then("Sticky Ads Internal Dong Tot Price of all subcates should be correct")
    public void Sticky_adss_Price_VND_in_3months_of_all_subcates_should_be_correct() {
        verifyStickyAdsInternalPrice_DongTot();
    }

    @Then("Sticky Ads Internal Promotion Price of all subcates should be correct")
    public void Sticky_ads_sPrice_VND_in_6months_of_all_subcates_should_be_correct() {
        verifyStickyAdsInternalPrice_Promotion();
    }

    @Given("I download data from Google Sheet {string}")
    public void i_download_data_from_Google_Sheet(String sheetName) {
        downloadCSVToLocal(sheetName);
        setFailedMsg("");
        getStaticDataToTest();
    }

    //------------------- Shop get-all public -------------------
    @Then("Shop Create Price VND of all categories should be correct")
    public void shop_Create_Price_VND_in_1month_of_all_subcates_should_be_correct() {
        verifyShopCreatePrice_VND();
    }

    @Then("Shop Create Price DongTot of all categories should be correct")
    public void shop_Create_Price_VND_in_3months_of_all_subcates_should_be_correct() {
        verifyShopCreatePrice_DongTot();
    }

    @Then("Shop Create Price Promotion of all categories should be correct")
    public void shop_Create_Price_VND_in_6months_of_all_subcates_should_be_correct() {
        verifyShopCreatePrice_Promotion();
    }




    @Then("Shop Extend Price VND of all categories should be correct")
    public void shop_Extend_Price_VND_in_1month_of_all_subcates_should_be_correct() {
        verifyShopExtendPrice_VND();
    }

    @Then("Shop Extend Price DongTot of all categories should be correct")
    public void shop_Extend_Price_VND_in_3months_of_all_subcates_should_be_correct() {
        verifyShopExtendPrice_DongTot();
    }

    @Then("Shop Extend Price Promotion of all categories should be correct")
    public void shop_Extend_Price_VND_in_6months_of_all_subcates_should_be_correct() {
        verifyShopExtendPrice_Promotion();
    }


    //------------------- Shop get-all private -------------------
    @Then("Shop Private Create Price VND of all categories should be correct")
    public void shop_Create_sPrsice_VND_in_1month_of_all_subcates_should_be_correct() {
        verifyShopCreatePrice_Private_VND(getUserToken());
    }

    @Then("Shop Private Create Price DongTot of all categories should be correct")
    public void shop_Cresate_Price_VsND_in_3months_of_all_subcates_should_be_correct() {
        verifyShopCreatePrice_Private_DT(getUserToken());
    }

    @Then("Shop Private Create Price Promotion of all categories should be correct")
    public void shop_Creaste_Price_VND_in_6mosnths_of_all_subcates_should_be_correct() {
        verifyShopCreatePrice_Private_Promotion(getUserToken());
    }

    @Then("Shop Private Extend Price VND of all categories should be correct")
    public void shop_Extend_Price_VND_in_1month_of_alls_subcates_should_be_correct() {
        verifyShopExtendPrice_Private_VND(getUserToken());
    }

    @Then("Shop Private Extend Price DongTot of all categories should be correct")
    public void shop_Extend_Price_VND_in_3months_sof_all_subcates_should_be_correct() {
        verifyShopExtendPrice_Private_DT(getUserToken());
    }

    @Then("Shop Private Extend Price Promotion of all categories should be correct")
    public void shop_Extend_Price_VND_in_6months_sof_all_subcates_should_be_correct() {
        verifyShopExtendPrice_Private_Promotion(getUserToken());
    }


    //------------------- Shop get-package -------------------
    @Then("Shop Package Create Price of all categories should be correct")
    public void shop_Create_Prsice_VND_in_1month_of_all_subcates_should_be_correct() {
        verifyShopPackageCreatePrice();
    }

    @Then("Shop Package Extend Price of all categories should be correct")
    public void shop_Extend_Price_VND_in_1month_of_asll_subcates_should_be_correct() {
        verifyShopPackageExtendPrice();
    }


    //------------------- New Pricer JS -------------------
    @Given("Set Pricer URI Gateway Private V1")
    public void set_Pricer_URI_Gateway_Private_V1() {
        setGatewayPricerURI_PrivateV1();
    }

    @Given("Set Pricer URI Gateway Private V2")
    public void set_Pricer_URI_Gateway_Private_V2() {
        setGatewayPricerURI_PrivateV2();
    }

    @Given("Set Pricer URI Gateway Internal V1")
    public void set_Pricer_URI_Gateway_Internal_V1() {
        setGatewayPricerURI_InternalV1();
    }

    @Given("Set Pricer URI Gateway Public V1")
    public void set_Pricer_URI_Gateway_Internal_V1s() {
        setGatewayPricerURI_PublicV1();
    }

    @Then("Check API Pricer New JS with URL {string}")
    public void check_API_Pricer_New_JS_with_URL(String url) {
        comparePricerAPIJS(url);
    }

    @Then("Check API Pricer New JS with Account ID and URL {string}")
    public void check_API_Pricer_News_JS_with_URL(String url) {
        comparePricerAPIJS(url,true);
    }

    @Then("Check API Pricer New JS with accountID and URL {string}")
    public void check_API_Pricers_New_JS_with_URL(String url) {
        comparePricerAPIJS(url, true);
    }

    @Then("Set Pricer Service New JS to {string}")
    public void set_service_pricer_to(String service){
        setServiceNamePricerNew(service);
    }

    @Then("Compare Response Body Structure between 2 public APIs {string} and {string}")
    public void compare_Response_Body_Data_between_public_APIs_and(String urlA, String urlB) {
        compareResBody_Public(urlA, urlB);
    }

    @Then("Compare Response Body Keys between 2 public APIs {string} and {string}")
    public void compare_Response_Body_Structure_between_public_APIs_and(String urlA, String urlB) {
        compareResBodyKey_Public(urlA, urlB);
    }

    //----------- Unavaiable -----------
//    //PTY all cates region 13000
//    @Then("Listing Fee Pricer Internal PTY Sell Ad Private All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VND_of_Sell_Ad_resgion13000() {
//        verifyListingFeePrice_Pricer_SellAd_PTY_13000("", getUserAccountID());
//    }
//    @Then("Listing Fee Pricer Private PTY Sell Ad Private All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VND_of_Sell_Ad_regison13000() {
//        verifyListingFeePrice_Pricer_SellAd_PTY_13000(getUserToken(), getUserAccountID());
//    }
//    //VEH all cates region 13000
//    @Then("Listing Fee Pricer Internal VEH Sell Ad Private All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VEH_VND_of_Sell_Ad_regsion13000() {
//        verifyListingFeePrice_Pricer_SellAd_VEH_13000("", getUserAccountID());
//    }
//    @Then("Listing Fee Pricer Private VEH Sell Ad Private All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VEH_VND_sof_Sell_Ad_region13000() {
//        verifyListingFeePrice_Pricer_SellAd_VEH_13000(getUserToken(), getUserAccountID());
//    }
//    //ELT region 13000
//    @Then("Listing Fee Pricer Internal ELT Sell Ad Private All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_ELTVND_of_Sell_Ad_regsion13000() {
//        verifyListingFeePrice_Pricer_SellAd_ELT_13000("", getUserAccountID());
//    }
//    @Then("Listing Fee Pricer Private ELT Sell Ad Private All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricerELT_VND_sof_Sell_Ad_region13000() {
//        verifyListingFeePrice_Pricer_SellAd_ELT_13000(getUserToken(), getUserAccountID());
//    }
//    @Then("Listing Fee Pricer PTY Sell Ad Private Shop Package Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VND_of_Sell_Ad_region13000sPro() {
//        verifyListingFeePrice_Pricer_SellAdShop_PTY_13000(getUserToken(), getUserAccountID());
//    }
//    @Then("Listing Fee Pricer PTY Sell Ad Internal Shop Package Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VND_of_Sell_Ad_region13000Pro() {
//        verifyListingFeePrice_Pricer_SellAdShop_PTY_13000("", getUserAccountID());
//    }
//    @Then("Listing Fee Pricer VEH Sell Ad Private Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VEH_VND_of_Sell_Ad_region13a000Pro() {
//        verifyListingFeePrice_Pricer_SellAdShop_VEH_13000(getUserToken(), getUserAccountID());
//    }
//    @Then("Listing Fee Pricer VEH Sell Ad Internal Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_VEH_VaND_of_Sell_Ad_region13000Pro() {
//        verifyListingFeePrice_Pricer_SellAdShop_VEH_13000("", getUserAccountID());
//    }
//    @Then("Listing Fee Pricer ELT Sell Ad Private Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_ELT_VND_sof_Sell_Ad_region13000Pro() {
//        verifyListingFeePrice_Pricer_SellAdShop_ELT_13000(getUserToken(), getUserAccountID());
//    }
//    @Then("Listing Fee Pricer ELT Sell Ad Internal Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_FeePricer_ELT_VND_of_sSell_Ad_region13000Pro() {
//        verifyListingFeePrice_Pricer_SellAdShop_ELT_13000("", getUserAccountID());
//    }
//    @Then("Listing Fee Services ELT Sell Ad Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_Fee_ELT_VND_of_Sell_Ad_region13000Pro() {
//        verifyListingFeePrice_SellAdShop_ELT_13000();
//    }
//    @Then("Listing Fee Services PTY Sell Ad Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_Fee_VND_of_Sell_Ad_region13000Pro() {
//        verifyListingFeePrice_SellAdShop_PTY_13000();
//    }
//    @Then("Listing Fee Services VEH Sell Ad Shop Dashboard To Chotot All Cates which are applied for only region 13000 should be correct")
//    public void listing_Fee_VEH_VND_of_Sell_Ad_region13000Pro() {
//        verifyListingFeePrice_SellAdShop_VEH_13000();
//    }
}
