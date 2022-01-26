package desktop.scenarios_old.ads.listing_fee;

import desktop.pages.Dashboard.PrivateDashboard;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import static api.configuration.DataConfig.tempAdID;
import static api.configuration.DataConfig.tempAdSubject;
import static desktop.configuration.LoginConfig.setTempAccountAndGetToken;

public class ListingFee_PTY_Shop_NotRun {
    CM_API_Ads_InsertPTY cm_api_ads_insertPTY;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;
    CM_CP_AcceptAd cm_cp_acceptAd;
    CM_Payment_DongTot cm_payment_dongTot;

    private void setupObligateProAccounts() {
        setTempAccountAndGetToken(11);
    }

    // Obligate Pro User
    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad is always charge on Shop, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertChargeHouseAdWithProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();
        cm_cp_acceptAd = new CM_CP_AcceptAd();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        //        tempAdID = cm_api_ads_insertPTY.insertNewAdHouse(false);
        //        cm_cp_acceptAd.acceptNewAd_ListingFreePTY(tempAdID);
        //        tempAdID = cm_api_ads_insertPTY.insertNewAdHouse(false);
        //        cm_cp_acceptAd.acceptNewAd_ListingFreePTY(tempAdID);
        //        tempAdID = cm_api_ads_insertPTY.insertNewAdHouse(false);
        //        cm_cp_acceptAd.acceptNewAd_ListingFreePTY(tempAdID);

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseProObligateShop();

        // Login
        cm_login.loginAndGoToManageAd(false, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad is always charge on Shop, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertChargeApartmentAdWithProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentProObligateShop();

        // Login
        cm_login.loginAndGoToManageAd(false, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad is always charge on Shop, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertChargeLandAdWithProObligateShopObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandProObligateShop();

        // Login
        cm_login.loginAndGoToManageAd(false, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad is always charge on Chotot, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertChargeHouseAdWithProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseProObligateChotot(false);

        // Login
        cm_login.loginAndGoToManageAd(false, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad is always charge on Chotot, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertChargeApartmentAdWithProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentProObligateChotot();

        // Login
        cm_login.loginAndGoToManageAd(false, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad is always charge on Chotot, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertChargeLandAdWithProObligateShopObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandProObligateChotot();

        // Login
        cm_login.loginAndGoToManageAd(false, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy House Shop Ad is charge, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeBuyHouseAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseBuyProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy House Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeBuyHouseAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseBuyProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire House Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHireHouseAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseHireProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire House Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHireHouseAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseHireProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease House Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLeaseHouseAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseLeaseProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease House Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLeaseHouseAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseLeaseProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy Apartment Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeBuyApartmentAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentBuyProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy Apartment Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeBuyApartmentAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentBuyProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire Apartment Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHireApartmentAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentHireProObligateShop();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire Apartment Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHireApartmentAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentHireProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease Apartment Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLeaseApartmentAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentLeaseProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease Apartment Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLeaseApartmentAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentLeaseProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy Land Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeBuyLandAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandBuyProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy Land Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeBuyLandAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandBuyProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire Land Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHireLandAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandHireProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire Land Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHireLandAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandHireProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease Land Shop Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLeaseLandAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandLeaseProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease Land Chotot Ad is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLeaseLandAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandLeaseProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad Shop is free with other City, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHouseAdOtherCityProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseOtherCityProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad Chotot is free with other City, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeHouseAdOtherCityProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdHouseOtherCityProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad Shop is free with other City, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeApartmentAdOtherCityProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentOtherCityProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad Chotot is free with other City, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeApartmentAdOtherCityProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdApartmentOtherCityProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad Shop is free with other City, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLandAdOtherCityProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandOtherCityProObligateShop(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad Chotot is free with other City, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeLandAdOtherCityProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdLandOtherCityProObligateChotot(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Room Ad Shop is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeRoomAdProObligateShop() {
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdRoomForLeaseProObligateShop(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Room Ad Chotot is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeRoomAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdRoomForLeaseProObligateChotot();

        // Go to private
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Office Ad Shop is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeOfficeAdProObligateShop() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdOfficeProObligateShop();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Office Ad Chotot is free, Vu Hoang, ME",
            enabled = false)
    public void verifyInsertFreeOfficeAdProObligateChotot() {
        setupObligateProAccounts();
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();

        // Insert new ad
        tempAdID = cm_api_ads_insertPTY.insertNewAdOfficeProObligateChotot();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(false, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }
}
