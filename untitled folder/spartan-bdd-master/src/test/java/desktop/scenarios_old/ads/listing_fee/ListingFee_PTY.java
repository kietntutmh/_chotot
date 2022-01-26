package desktop.scenarios_old.ads.listing_fee;

import api.feature.ads.DashboardAds;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.PrivateDashboard;
import desktop.pages.Payment.OrderHistory;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditPTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;
import projects.functions.chotot._common.cp.CM_CP_RejectAd;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.Date;

import static api.configuration.DataConfig.*;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class ListingFee_PTY extends BaseTest {
    CM_API_Ads_InsertPTY cm_api_ads_insertPTY;
    CM_API_Ads_EditPTY cm_api_ads_editPTY;
    CM_CP_API_AcceptAd cm_cp_api_acceptAd;
    CM_CP_API_RejectAd cm_cp_api_rejectAd;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;
    CM_CP_RejectAd cm_cp_rejectAd;
    CM_CP_AcceptAd cm_cp_acceptAd;
    CM_Payment_DongTot cm_payment_dongTot;
    OrderHistory orderHistory;
    DashboardAds api_dashboard;

    public void initObjects() {
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAd = new CM_CP_API_RejectAd();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_api_ads_editPTY = new CM_API_Ads_EditPTY();
        cm_cp_rejectAd = new CM_CP_RejectAd();
        cm_cp_acceptAd = new CM_CP_AcceptAd();
        cm_payment_dongTot = new CM_Payment_DongTot();
        orderHistory = new OrderHistory();
        api_dashboard = new DashboardAds();
        setTempAccountCPAPIIndex(4);
    }

    @Test
    public void TCdemo() {
        initObjects();

        // Check API of insert, edit Ad
        cm_api_ads_insertPTY.insertNewAdLand();
    }

    // CHECK API BEFORE RUN TEST
    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "Listing Fee - PTY - API Check, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }
        // Init objects
        initObjects();

        // Check API of insert, edit Ad
        cm_api_ads_insertPTY.insertNewAdHouse();    //All cate using the same API: InsertAd. Just need to check one cate
        cm_api_ads_insertPTY.insertNewAdHouse();
        checkAdOnDashboard(tempAdID);
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertPTY.insertNewAdHouse("RejectPay");
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertPTY.insertNewAdHouse();
        cm_api_ads_insertPTY.insertNewAdHouse();
        cm_api_ads_editPTY.editNewAdHouse(tempAdID, "RejectPay");
        checkAdOnDashboard(tempAdID);
        cm_api_ads_editPTY.editNewAdHouse(tempAdID, "AcceptPay");
        checkAdOnDashboard(tempAdID);
    }

    // PRIVATE USER: Free First
    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify the first House Ad is free, Vu Hoang, ME")
    public void verifyInsertFirstFreeHouseAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify the first Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFirstFreeApartmentAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify the first Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFirstFreeLandAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();
        String initMoney = privateDashboard.getAmountMoney();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // PRIVATE USER: Charge Second
    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify second House Ad is charge When first ad is not reviewed, Vu Hoang, ME")
    public void verifyInsertSecondChargeHouseAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();
        String firstAdSubject = tempAdSubject;

        cm_api_ads_insertPTY.insertNewAdHouse();
        String secondAdSubject = tempAdSubject;

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(firstAdSubject, "KHÁC");

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(secondAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify second Apartment Ad is charge When first ad is not reviewed, Vu Hoang, ME")
    public void verifyInsertSecondChargeApartmentAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();
        String firstAdSubject = tempAdSubject;

        cm_api_ads_insertPTY.insertNewAdApartment();
        String secondAdSubject = tempAdSubject;
        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(firstAdSubject, "KHÁC");

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(secondAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify second Land Ad is charge When first ad is not reviewed, Vu Hoang, ME")
    public void verifyInsertSecondChargeLandAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand();
        String firstAdSubject = tempAdSubject;

        cm_api_ads_insertPTY.insertNewAdLand();
        String secondAdSubject = tempAdSubject;

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(firstAdSubject, "KHÁC");

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(secondAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify second House Ad is free When first ad is rejected, Vu Hoang, ME")
    public void verifyInsertSecondFreeHouseAd() {
        // Initialize objects
        initObjects();

        // Insert first Ad
        cm_api_ads_insertPTY.insertNewAdHouse("Reject");
        cm_api_ads_insertPTY.insertNewAdHouse();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify second Apartment Ad is free When first ad is rejected, Vu Hoang, ME")
    public void verifyInsertSecondFreeApartmentAd() {
        // Initialize objects
        initObjects();

        // Insert first Ad
        cm_api_ads_insertPTY.insertNewAdApartment("Reject");
        cm_api_ads_insertPTY.insertNewAdApartment();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify second Land Ad is free When first ad is rejected, Vu Hoang, ME")
    public void verifyInsertSecondFreeLandAd() {
        // Initialize objects
        initObjects();

        // Insert first Ad
        cm_api_ads_insertPTY.insertNewAdLand("Reject");
        cm_api_ads_insertPTY.insertNewAdLand();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify second House Ad is charge When first ad is published, Vu Hoang, ME")
    public void verifyInsertSecondChargeHouseAdFirstPublish() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        cm_api_ads_insertPTY.insertNewAdHouse();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify second Apartment Ad is charge When first ad is published, Vu Hoang, ME")
    public void verifyInsertSecondChargeApartmentAdFirstPublish() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        cm_api_ads_insertPTY.insertNewAdApartment();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify second Land Ad is charge When first ad is published, Vu Hoang, ME")
    public void verifyInsertSecondChargeLandAdFirstPublish() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        cm_api_ads_insertPTY.insertNewAdLand();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Private User, Verify Buy House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyHouseAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseBuy(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Buy Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyApartmentAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentBuy(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Private User, Verify Buy Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyLandAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandBuy(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify House Ad is free with other City, Vu Hoang, ME")
    public void verifyInsertFreeHouseAdOtherCity() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseOtherCity(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Apartment Ad is free with other City, Vu Hoang, ME")
    public void verifyInsertFreeApartmentAdOtherCity() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentOtherCity(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Land Ad is free with other City, Vu Hoang, ME")
    public void verifyInsertFreeLandAdOtherCity() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandOtherCity(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Private User, Verify Hire House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireHouseAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseHire(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Hire Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireApartmentAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentHire(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Private User, Verify Hire Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireLandAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandHire(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Private User, Verify Lease House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseHouseAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseLease(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Lease Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseApartmentAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentLease(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - PTY - Private User, Verify Lease Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseLandAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandLease(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Ad move from CẦN THANH TOÁN to ĐỢI DUYỆT after paid, Vu Hoang, ME")
    public void verifyHouseAdMovedToOtherAfterPay() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        topupDongTotWithMomo(tempUserPhone);

        // Ad will be paid and checked
        cm_api_ads_insertPTY.insertNewAdHouse();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on NeedToPay tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Pay order
        paymentOrderWithDongTot();

        // Come back to Private Board
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on WaitForAccept
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Private User, Verify Room Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeRoomAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdRoomForLease("Accept");
        cm_api_ads_insertPTY.insertNewAdRoomForLease("Accept");
        cm_api_ads_insertPTY.insertNewAdRoomForLease("Accept");
        cm_api_ads_insertPTY.insertNewAdRoomForLease();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Private User, Verify Office Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeOfficeAd() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdOffice("Accept");
        cm_api_ads_insertPTY.insertNewAdOffice("Accept");
        cm_api_ads_insertPTY.insertNewAdOffice("Accept");
        cm_api_ads_insertPTY.insertNewAdOffice();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "Listing Fee - PTY - Private User, Verify House Ad is displayed on Listing after being accepted, Vu Hoang, ME")
    public void verifyInsertHouseAdDisplayOnListingAfterAccept() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Apartment Ad is displayed on Listing after being accepted, Vu Hoang, ME")
    public void verifyInsertApartmentAdDisplayOnListingAfterAccept() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Land Ad is displayed on Listing after being accepted, Vu Hoang, ME")
    public void verifyInsertLandAdDisplayOnListingAfterAccept() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify House Ad is displayed on RejectList after being rejected, Vu Hoang, ME")
    public void verifyInsertHouseAdDisplayOnListingAfterReject() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Apartment Ad is displayed on RejectList after being rejected, Vu Hoang, ME")
    public void verifyInsertApartmentAdDisplayOnListingAfterReject() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Land Ad is displayed on RejectList after being rejected, Vu Hoang, ME")
    public void verifyInsertLandAdDisplayOnListingAfterReject() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify Dong Tot is refunded after House Ad is rejected, Vu Hoang, ME")
    public void verifyInsertHouseAdRejectedAndRefund() {
        // Initialize objects
        initObjects();

        // First free ad
        cm_api_ads_insertPTY.insertNewAdHouse();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Ad is rejected
        cm_api_ads_insertPTY.insertNewAdHouse("RejectPay");

        // Go to private dashboard to get init money
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Dong Tot is refunded after Apartment Ad is rejected, Vu Hoang, ME")
    public void verifyInsertApartmentAdRejectedAndRefund() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Ad is rejected
        cm_api_ads_insertPTY.insertNewAdApartment("RejectPay");

        // Go to private dashboard to get init money
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Dong Tot is refunded after Land Ad is rejected, Vu Hoang, ME")
    public void verifyInsertLandAdRejectedAndRefund() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Ad is rejected
        cm_api_ads_insertPTY.insertNewAdLand("RejectPay");

        // Go to private dashboard to get init money
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    // Edit Ad
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify Free House Ad displays on BỊ TỪ CHỐI or ĐANG BÁN after edit, Vu Hoang, ME")
    public void verify_InsertHouse_Free_EditRejected() {
        // Initialize objects
        initObjects();

        // 1. Insert new ad Then rejecting edit
        cm_api_ads_insertPTY.insertNewAdHouse("Reject");
        topupDongTotWithMomo(tempUserPhone);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdHouse(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Insert new Ad then accepting edit
        cm_api_ads_insertPTY.insertNewAdHouse("Reject");
        cm_api_ads_editPTY.editNewAdHouse(tempAdID, "Accept");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Free Apartment Ad displays on BỊ TỪ CHỐI or ĐANG BÁN after edit, Vu Hoang, ME")
    public void verify_InsertApartment_Free_EditRejected() {
        // Initialize objects
        initObjects();

        // 1. Insert new ad Then rejecting edit
        cm_api_ads_insertPTY.insertNewAdApartment("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdApartment(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Insert new ad Then accepting edit
        cm_api_ads_insertPTY.insertNewAdApartment("Reject");

        cm_api_ads_editPTY.editNewAdApartment(tempAdID, "Accept");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Free Land Ad displays on BỊ TỪ CHỐI or ĐANG BÁN after edit, Vu Hoang, ME")
    public void verify_InsertLand_Free_EditRejected() {
        // Initialize objects
        initObjects();

        // 1. Insert new ad Then rejecting edit
        cm_api_ads_insertPTY.insertNewAdLand("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);
        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdLand(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Insert new ad Then accepting edit
        cm_api_ads_insertPTY.insertNewAdLand("Reject");

        cm_api_ads_editPTY.editNewAdLand(tempAdID, "Accept");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Private User, Verify Charge House Ad displays on BỊ TỪ CHỐI or ĐANG BÁN after edit, Vu Hoang, ME")
    public void verify_InsertHouse_Charge_EditRejected() {
        // Initialize objects
        initObjects();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);
        privateDashboard.goToPrivateDashboard();

        // Insert 1st free new ad
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");

        // Insert 2nd charge ad
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("RejectPay"); //Paid Ad is rejected
        checkAdOnDashboard(tempAdID);

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdHouse(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Charge Land Ad displays on BỊ TỪ CHỐI or ĐANG BÁN after edit, Vu Hoang, ME")
    public void verify_InsertLand_Charge_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert 1st free new ad
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdLand(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdLand(tempAdID, "Accept");

        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Private User, Verify Charge Apartment Ad displays on BỊ TỪ CHỐI or ĐANG BÁN after edit, Vu Hoang, ME")
    public void verify_InsertApartment_Charge_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert 1st free new ad
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");

        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdApartment("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdApartment(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdApartment(tempAdID, "AcceptPay");

        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    // PRO USER
    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Pro User, Verify House Ad is always charge, Vu Hoang, ME")
    public void verifyInsertChargeHouseAdWithPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHousePro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Apartment Ad is always charge, Vu Hoang, ME")
    public void verifyInsertChargeApartmentAdWithPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Land Ad is always charge, Vu Hoang, ME")
    public void verifyInsertChargeLandAdWithPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Charge House Ad displays on ĐỢI DUYỆT, Vu Hoang, ME")
    public void verifyInsertChargeHouseAdWaitPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();
        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdHousePro("AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Charge Apartment Ad displays on ĐỢI DUYỆT, Vu Hoang, ME")
    public void verifyInsertChargeApartmentAdWaitPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();
        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdApartmentPro("AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Charge Land Ad displays on ĐỢI DUYỆT, Vu Hoang, ME")
    public void verifyInsertChargeLandAdWaitPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand();
        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdLandPro("AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Pro User, Verify Buy House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyHouseAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseBuyPro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Buy Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyApartmentAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentBuyPro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Buy Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyLandAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandBuyPro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Pro User, Verify Hire House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireHouseAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseHirePro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - PTY - Pro User, Verify Hire Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireApartmentAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentHirePro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Hire Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireLandAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandHirePro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - PTY - Pro User, Verify Lease House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseHouseAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseLeasePro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - PTY - Pro User, Verify Lease Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseApartmentAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentLeasePro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Lease Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseLandAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandLeasePro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Pro User, Verify House Ad is free with other City, Vu Hoang, ME")
    public void verifyInsertFreeHouseAdOtherCityPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseOtherCityPro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Apartment Ad is free with other City, Vu Hoang, ME")
    public void verifyInsertFreeApartmentAdOtherCityPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentOtherCityPro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Land Ad is free with other City, Vu Hoang, ME")
    public void verifyInsertFreeLandAdOtherCityPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandOtherCityPro(true);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Room Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeRoomAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdRoomForLeasePro();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Pro User, Verify Office Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeOfficeAdPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdOfficePro();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Pro User, Verify Charge House Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsertChargeHouseAdCPAcceptPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();
        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdHousePro("AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Charge Apartment Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsertChargeApartmentAdCPAcceptPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();

        topupDongTotWithMomo(tempUserPhone);

        cm_api_ads_insertPTY.insertNewAdApartmentPro("AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Charge Land Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsertChargeLandAdCPAcceptPro() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand();

        topupDongTotWithMomo(tempUserPhone);

        // Insert 2nd charge ad
        cm_api_ads_insertPTY.insertNewAdLandPro("AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Pro User, Verify House Ad displays on BỊ TỪ CHỐI after edit rejected, Vu Hoang, ME")
    public void verify_InsertHouse_Pro_EditRejected() {
        // Initialize objects
        initObjects();

        cm_api_ads_insertPTY.insertNewAdHouse();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHousePro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdHousePro(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Pro User, Verify House Ad displays on ĐANG BÁN after edit accepted, Vu Hoang, ME")
    public void verify_InsertHouse_Pro_EditAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();

        topupDongTotWithMomo(tempUserPhone);

        cm_api_ads_insertPTY.insertNewAdHousePro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdHousePro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Apartment Ad displays on CẦN THANH TOÁN after edit rejected, Vu Hoang, ME")
    public void verify_InsertApartment_Pro_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartmentPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdApartmentPro(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Apartment Ad displays on ĐANG BÁN after edit accepted, Vu Hoang, ME")
    public void verify_InsertApartment_Pro_EditAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartmentPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdApartmentPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Land Ad displays on BỊ TỪ CHỐI after edit rejected, Vu Hoang, ME")
    public void verify_InsertLand_Pro_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLandPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdLandPro(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - PTY - Pro User, Verify Land Ad displays on ĐANG BÁN after edit accepted, Vu Hoang, ME")
    public void verify_InsertLand_Pro_EditAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad for topup
        cm_api_ads_insertPTY.insertNewAdLand();
        cm_api_ads_insertPTY.insertNewAdLand();
        topupDongTotWithMomo(tempUserPhone);

        //Start Test Case
        cm_api_ads_insertPTY.insertNewAdLandPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdLandPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    // Obligate Pro User
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad is always charge, Vu Hoang, ME")
    public void verifyInsertChargeHouseAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse_ProObligate_Private(false);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad is always charge, Vu Hoang, ME")
    public void verifyInsertChargeApartmentAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment_ProObligate_Private(false);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad is always charge, Vu Hoang, ME")
    public void verifyInsertChargeLandAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand_ProObligate_Private(false);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private dashboard
        privateDashboard.goToPrivateDashboard();

        // Verify First ad is displayed on Charge tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    // Buy
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyHouseAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseBuy_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyApartmentAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentBuy_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Buy Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeBuyLandAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandBuy_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Hire
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireHouseAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseHire_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireApartmentAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentHire_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Hire Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeHireLandAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandHire_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Lease
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease House Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseHouseAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseLease_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease Apartment Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseApartmentAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentLease_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Lease Land Ad is free, Vu Hoang, ME")
    public void verifyInsertFreeLeaseLandAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user on Cate: LAND
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandLease_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Other Region
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad with Other Region is free, Vu Hoang, ME")
    public void verifyInsertHouseAdOtherRegion_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdHouse("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouseOtherRegion_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad with Other Region is free, Vu Hoang, ME")
    public void verifyInsertApartmentAdOtherRegion_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartmentOtherRegion_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad with Other Region is free, Vu Hoang, ME")
    public void verifyInsertLandAdOtherRegion_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLandOtherRegion_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Free Office + Room
    @Test(
            description = "Listing Fee - PTY - Obligate Pro User, Verify Office Ad is free, Vu Hoang, ME")
    public void verifyInsertOfficeAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdOffice("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdOffice("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdOffice("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdOffice_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - PTY - Obligate Pro User, Verify Room Ad is free, Vu Hoang, ME")
    public void verifyInsertRoomAd_ProObligate_Private() {
        // Initialize objects
        initObjects();

        // Insert & Accept 3 ads, make sure user is forced to Pro user
        cm_api_ads_insertPTY.insertNewAdRoomForLease("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdRoomForLease("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdRoomForLease("AcceptPay");

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdRoom_ProObligate_Private(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Edit Ad
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad displays on BỊ TỪ CHỐI after edit rejected, Vu Hoang, ME")
    public void verify_InsertHouse_ProObligate_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse_ProObligate_Private("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdHousePro(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt]";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify House Ad displays on ĐANG BÁN after edit accepted, Vu Hoang, ME")
    public void verify_InsertHouse_ProObligate_EditAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdHouse();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdHouse_ProObligate_Private("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdHousePro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - ObligCate Pro User, Verify Apartment Ad displays on BỊ TỪ CHỐI after edit rejected, Vu Hoang, ME")
    public void verify_InsertApartment_ProObligate_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment_ProObligate_Private("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdApartmentPro(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Apartment Ad displays on ĐANG BÁN after edit accepted, Vu Hoang, ME")
    public void verify_InsertApartment_ProObligate_EditAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdApartment("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdApartment("AcceptPay");

        cm_api_ads_insertPTY.insertNewAdApartment_ProObligate_Private("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but accepted

        cm_api_ads_editPTY.editNewAdApartmentPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad displays on BỊ TỪ CHỐI after edit rejected, Vu Hoang, ME")
    public void verify_InsertLand_ProObligate_EditRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand_ProObligate_Private("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but rejected
        cm_api_ads_editPTY.editNewAdLandPro(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - PTY - Obligate Pro User, Verify Land Ad displays on ĐANG BÁN after edit accepted, Vu Hoang, ME")
    public void verify_InsertLand_ProObligate_EditAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertPTY.insertNewAdLand("Accept");
        topupDongTotWithMomo(tempUserPhone);

        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand("AcceptPay");
        cm_api_ads_insertPTY.insertNewAdLand_ProObligate_Private(false, "RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        privateDashboard.goToPrivateDashboard();

        // Edit but accepted
        cm_api_ads_editPTY.editNewAdLandPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }
}
