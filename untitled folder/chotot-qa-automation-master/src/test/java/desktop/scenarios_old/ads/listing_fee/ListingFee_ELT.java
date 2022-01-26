package desktop.scenarios_old.ads.listing_fee;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.PrivateDashboard;
import desktop.pages.Payment.OrderHistory;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;

import java.util.Date;

import static api.configuration.DataConfig.*;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class ListingFee_ELT extends BaseTest {
    CM_API_Ads_InsertELT cm_api_ads_insertELT;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;
    OrderHistory orderHistory;
    CM_API_Ads_EditELT cm_api_ads_editELT;

    private void initObjects() {
        cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
        orderHistory = new OrderHistory();
        cm_api_ads_editELT = new CM_API_Ads_EditELT();
        setTempAccountCPAPIIndex(5);
    }

    // CHECK API BEFORE RUN TEST
    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "Listing Fee - ELT - API Check, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        // init objects
        initObjects();

        // Check Payment & Dashboard
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        topupDongTotWithMomo(tempUserPhone);

        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdLaptop("AcceptPay");
        checkAdOnDashboard(tempAdID);

        // Check edit ad accept
        cm_api_ads_insertELT.insertNewAdTablet("Accept");
        checkAdOnDashboard(tempAdID);
        cm_api_ads_editELT.editNewAdTablet(tempAdID, "Accept");
        checkAdOnDashboard(tempAdID);

        // Check edit ad reject
        cm_api_ads_insertELT.insertNewAdPhone("Reject");
        checkAdOnDashboard(tempAdID);
        cm_api_ads_editELT.editNewAdPhone(tempAdID, "Reject");
        checkAdOnDashboard(tempAdID);

        // Check order API
        cm_api_ads_insertELT.insertNewAdPC();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdPC_Component();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdAccessories();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdCamera();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdSound();
        checkAdOnDashboard(tempAdID);
    }

    // PRIVATE USER
    @Test(description = "Listing Fee - ELT - Private User, Verify the Ad is moved to ĐỢI DUYỆT after charged, Vu Hoang, ME")
    public void verifyInsert_MoveToOther_AfterCharged() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_insertELT.insertNewAdLaptop();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "CẦN THANH TOÁN" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Charge fee for Ad By Dong Tot
        topupDongTotWithMomo(tempUserPhone);
        paymentOrderWithDongTot();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Laptop
    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify the first Laptop Ad is free, Vu Hoang, ME")
    public void verifyInsert_Laptop_FirstFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify the second Laptop Ad is free, Vu Hoang, ME")
    public void verifyInsert_Laptop_SecondFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop();

        cm_api_ads_insertELT.insertNewAdLaptop(); // Set tempAdSubject;

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify the third Laptop Ad is free When First Ad is rejected, Vu Hoang, ME")
    public void verifyInsert_Laptop_ThirdFree_FirstReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Reject");
        cm_api_ads_insertELT.insertNewAdLaptop();
        cm_api_ads_insertELT.insertNewAdLaptop();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify the third Laptop Ad is free When Second Ad is rejected, Vu Hoang, ME")
    public void verifyInsert_Laptop_ThirdFree_SecondReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop();
        cm_api_ads_insertELT.insertNewAdLaptop("Reject");
        cm_api_ads_insertELT.insertNewAdLaptop();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify the third Laptop Ad is charged When having 2 Ads are published, Vu Hoang, ME")
    public void verifyInsert_Laptop_ThirdCharge() {
        initObjects();
        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop();
        cm_api_ads_insertELT.insertNewAdLaptop();
        cm_api_ads_insertELT.insertNewAdLaptop();

        // Check Ad displays on "Cần thanh toán" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - ELT - Private User, Verify Buy Laptop Ad is free, Vu Hoang, ME")
    public void verifyInsert_Laptop_BuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopBuy(true);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // New update
    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify First Phone Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Phone_FirstFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Second Phone Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Phone_SecondFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone();
        cm_api_ads_insertELT.insertNewAdPhone();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify the third Phone Ad is free When First Ad is rejected, Vu Hoang, ME")
    public void verifyInsert_Phone_ThirdFree_FirstReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone("Reject");
        cm_api_ads_insertELT.insertNewAdPhone();
        cm_api_ads_insertELT.insertNewAdPhone();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify the third Phone Ad is free When Second Ad is rejected, Vu Hoang, ME")
    public void verifyInsert_Phone_ThirdFree_SecondReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone();
        cm_api_ads_insertELT.insertNewAdPhone("Reject");
        cm_api_ads_insertELT.insertNewAdPhone();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify Third Phone Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Phone_ThirdCharge() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone();
        cm_api_ads_insertELT.insertNewAdPhone();
        cm_api_ads_insertELT.insertNewAdPhone();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Cần thanh toán" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(description = "Listing Fee - ELT - Private User, Verify Buy Phone Ad is free, Vu Hoang, ME")
    public void verifyInsert_Phone_BuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhoneBuy();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Others are free
    @Test(description = "Listing Fee - ELT - Private User, Verify Tablet Ad is free, Vu Hoang, ME")
    public void verifyInsert_Tablet_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdTablet();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdTablet();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Private User, Verify PC Ad is free, Vu Hoang, ME")
    public void verifyInsert_PC_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdPC();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Private User, Verify Camera Ad is free, Vu Hoang, ME")
    public void verifyInsert_Camera_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdCamera();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdCamera();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Private User, Verify Sound Ad is free, Vu Hoang, ME")
    public void verifyInsert_Sound_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdSound();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdSound();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Private User, Verify Wearable Ad is free, Vu Hoang, ME")
    public void verifyInsert_Wearable_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdWearable();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdWearable();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - ELT - Private User, Verify Accessories Ad is free, Vu Hoang, ME")
    public void verifyInsert_Accessories_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdAccessories();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdAccessories();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - ELT - Private User, Verify Accessories Ad is free, Vu Hoang, ME")
    public void verifyInsert_PC_Component_Free() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC_Component();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");

        // Check 2nd Ad is still Free
        cm_api_ads_insertELT.insertNewAdPC_Component();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Ad displays on ĐANG BÁN after accepted
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify Laptop Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Laptop_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Phone Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Phone_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Tablet Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Tablet_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdTablet("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify PC Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_PC_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Camera Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Camera_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdCamera("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Sound Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Sound_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdSound("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Wearable Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Wearable_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdWearable("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Accessories Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_Accessories_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdAccessories("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify PC Component Ad displays on ĐANG BÁN after accepted, Vu Hoang, ME")
    public void verifyInsert_PC_Component_OnSelling_AfterAccept() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC_Component("Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    // Ad displays on BỊ TỪ CHỐI after rejected
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify Laptop Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Laptop_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Phone Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Phone_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Tablet Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Tablet_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdTablet("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify PC Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_PC_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Camera Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Camera_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdCamera("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Sound Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Sound_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdSound("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Wearable Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Wearable_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdWearable("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Accessories Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_Accessories_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdAccessories("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify PC Component Ad displays on BỊ TỪ CHỐI after rejected, Vu Hoang, ME")
    public void verifyInsert_PC_Component_OnDeny_AfterReject() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC_Component("Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    // Edit ad
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify Charge Laptop Ad displays on BỊ TỪ CHỐI after edited and rejected, Vu Hoang, ME")
    public void verifyInsert_Charge_Laptop_Edited_And_Rejected() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // Nap tien Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert second laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // Insert 3rd charge Laptop ad and rejected
        cm_api_ads_insertELT.insertNewAdLaptop("RejectPay");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptop(tempAdID, "RejectPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
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
                    "Listing Fee - ELT - Private User, Verify Charge Laptop Ad displays on CẦN THANH TOÁN after edited and accepted, Vu Hoang, ME")
    public void verifyInsert_Charge_Laptop_Edited_And_Accepted() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // Insert second laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // Insert 3rd charge Laptop ad and rejected
        cm_api_ads_insertELT.insertNewAdLaptop("RejectPay");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptop(tempAdID, "AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Laptop Ad displays on BỊ TỪ CHỐI after edited and rejected, Vu Hoang, ME")
    public void verifyInsert_Laptop_Edited_And_Rejected() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Reject");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptop(tempAdID, "Reject");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify Laptop Ad displays on CẦN THANH TOÁN after edited and accepted, Vu Hoang, ME")
    public void verifyInsert_Laptop_Edited_And_Accepted() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Reject");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptop(tempAdID, "Accept");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Private User, Verify fee doesn't charge when edit a published charge ad, Vu Hoang, ME")
    public void verifyInsert_Edit_Published_Ad_Not_Charge() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // 3rd charge ad need to edit
        cm_api_ads_insertELT.insertNewAdLaptop("RejectPay");

        // Edit ad & accepted the published charge ad
        cm_api_ads_editELT.editNewAdLaptop(tempAdID, "AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Go to history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    // PRO USER
    @Test(
            groups = {"dev", "uat"},
            description = "Listing Fee - ELT - Pro User, Verify Laptop Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Laptop_ProCharge() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopPro(true);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "CẦN THANH TOÁN" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Phone Pro Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Phone_ProCharge() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhonePro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    // Sell Ad is Free except Laptop
    @Test(
            groups = {"dev", "uat"},
            description = "Listing Fee - ELT - Pro User, Verify Tablet Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_Tablet_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdTabletPro(true);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify PC Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_PC_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPCPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Camera Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_Camera_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdCameraPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Sound Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_Sound_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdSoundPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Wearable Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_Wearable_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdWearablePro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - ELT - Pro User, Verify Accessories Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_Accessories_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdAccessories();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Pro User, Verify PC_Component Pro Ad is Free, Vu Hoang, ME")
    public void verifyInsert_PC_Component_ProFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC_ComponentPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Buy Free
    @Test(
            groups = {"dev", "uat"},
            description = "Listing Fee - ELT - Pro User, Verify Buy Laptop Ad is free, Vu Hoang, ME")
    public void verifyInsert_Laptop_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopBuyPro(true);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Buy Phone Ad is free, Vu Hoang, ME")
    public void verifyInsert_Phone_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhoneBuyPro(true);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Buy Tablet Ad is free, Vu Hoang, ME")
    public void verifyInsert_Tablet_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdTabletBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Buy Camera Ad is free, Vu Hoang, ME")
    public void verifyInsert_Camera_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdCameraBuyPro(true);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Buy PC Ad is free, Vu Hoang, ME")
    public void verifyInsert_PC_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPCBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Buy Sound Ad is free, Vu Hoang, ME")
    public void verifyInsert_Sound_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdSoundBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - ELT - Pro User, Verify Buy Wearable Ad is free, Vu Hoang, ME")
    public void verifyInsert_Wearable_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdWearableBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - ELT - Pro User, Verify Buy Accessories Ad is free, Vu Hoang, ME")
    public void verifyInsert_Accessories_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdAccessoriesBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Pro User, Verify Buy PC Component Ad is free, Vu Hoang, ME")
    public void verifyInsert_PC_Component_ProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC_ComponentBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // Edit ad
    @Test(
            description =
                    "Listing Fee - ELT - Pro User, Verify Charge Laptop Ad displays on BỊ TỪ CHỐI after edited and rejected, Vu Hoang, ME")
    public void verifyInsert_Charge_Laptop_Edited_And_Rejected_Pro() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopPro(true);
        topupDongTotWithMomo(tempUserPhone);

        // Insert 3rd charge Laptop ad and rejected
        cm_api_ads_insertELT.insertNewAdLaptopPro("RejectPay");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptopPro(tempAdID, "RejectPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - ELT - Pro User, Verify Charge Laptop Ad displays on CẦN THANH TOÁN after edited and accepted, Vu Hoang, ME")
    public void verifyInsert_Charge_Laptop_Edited_And_Accepted_Pro() {
        initObjects();

        // Insert Laptop Ads
        cm_api_ads_insertELT.insertNewAdLaptopPro(true);
        topupDongTotWithMomo(tempUserPhone);

        cm_api_ads_insertELT.insertNewAdLaptopPro("RejectPay");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptopPro(tempAdID, "AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Pro User, Verify Laptop Ad displays on BỊ TỪ CHỐI after edited and rejected, Vu Hoang, ME")
    public void verifyInsert_Laptop_Edited_And_Rejected_Pro() {
        initObjects();

        // Insert to toppup
        cm_api_ads_insertELT.insertNewAdLaptopPro(true);
        topupDongTotWithMomo(tempUserPhone);

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopPro("RejectPay");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptopPro(tempAdID, "RejectPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Pro User, Verify Laptop Ad displays on CẦN THANH TOÁN after edited and accepted, Vu Hoang, ME")
    public void verifyInsert_Laptop_Edited_And_Accepted_Pro() {
        initObjects();

        // Insert to toppup
        topupDongTotWithMomo(tempUserPhone);

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopPro("RejectPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Verify ad is moved to BỊ TỪ CHỐI
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Edit ad & rejected
        cm_api_ads_editELT.editNewAdLaptopPro(tempAdID, "AcceptPay");

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - ELT - Pro User, Verify fee doesn't charge when edit a published charge ad, Vu Hoang, ME")
    public void verifyInsert_Edit_Published_Ad_Not_Charge_Pro() {
        initObjects();

        // Insert to toppup
        cm_api_ads_insertELT.insertNewAdLaptopPro(true);
        topupDongTotWithMomo(tempUserPhone);

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptopPro("RejectPay");

        // Edit ad & accepted the published charge ad
        cm_api_ads_editELT.editNewAdLaptopPro(tempAdID, "AcceptPay");

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "BỊ TỪ CHỐI" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Go to history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    // OBLIGATE PRO USER
    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Laptop Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Laptop_ProObligate_Charge() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        topupDongTotWithMomo(tempUserPhone);
        // Insert more ads
        cm_api_ads_insertELT.insertNewAdLaptop("AcceptPay");
        cm_api_ads_insertELT.insertNewAdLaptop("AcceptPay");
        cm_api_ads_insertELT.insertNewAdLaptopPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "CẦN THANH TOÁN" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Phnone Ad is charge, Vu Hoang, ME")
    public void verifyInsert_Phone_ProObligate_Charge() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        topupDongTotWithMomo(tempUserPhone);
        // Insert more ads
        cm_api_ads_insertELT.insertNewAdPhone("AcceptPay");
        cm_api_ads_insertELT.insertNewAdPhone("AcceptPay");
        cm_api_ads_insertELT.insertNewAdPhonePro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "CẦN THANH TOÁN" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Laptop Ad is free, Vu Hoang, ME")
    public void verifyInsert_Laptop_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");

        // Topup Dong tot
        topupDongTotWithMomo(tempUserPhone);

        // Insert more ads
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_insertELT.insertNewAdLaptop("AcceptPay");
        cm_api_ads_insertELT.insertNewAdLaptop("AcceptPay");
        cm_api_ads_insertELT.insertNewAdLaptopBuyPro(false);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Phone Ad is free, Vu Hoang, ME")
    public void verifyInsert_Phone_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        cm_api_ads_insertELT.insertNewAdPhoneBuyPro(false);

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Tablet Ad is free, Vu Hoang, ME")
    public void verifyInsert_Tablet_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdTablet("Accept");
        cm_api_ads_insertELT.insertNewAdTablet("Accept");
        cm_api_ads_insertELT.insertNewAdTablet("Accept");
        cm_api_ads_insertELT.insertNewAdTabletBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Camera Ad is free, Vu Hoang, ME")
    public void verifyInsert_Camera_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdCamera("Accept");
        cm_api_ads_insertELT.insertNewAdCamera("Accept");
        cm_api_ads_insertELT.insertNewAdCamera("Accept");
        cm_api_ads_insertELT.insertNewAdCameraBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description = "Listing Fee - ELT - Obligate Pro User, Verify Buy PC Ad is free, Vu Hoang, ME")
    public void verifyInsert_PC_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC("Accept");
        cm_api_ads_insertELT.insertNewAdPC("Accept");
        cm_api_ads_insertELT.insertNewAdPC("Accept");
        cm_api_ads_insertELT.insertNewAdPCBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Sound Ad is free, Vu Hoang, ME")
    public void verifyInsert_Sound_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdSound("Accept");
        cm_api_ads_insertELT.insertNewAdSound("Accept");
        cm_api_ads_insertELT.insertNewAdSound("Accept");
        cm_api_ads_insertELT.insertNewAdSoundBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Wearable Ad is free, Vu Hoang, ME")
    public void verifyInsert_Wearable_ObligateProBuyFree() {
        initObjects();
        // Insert Laptop AN
        cm_api_ads_insertELT.insertNewAdWearable("Accept");
        cm_api_ads_insertELT.insertNewAdWearable("Accept");
        cm_api_ads_insertELT.insertNewAdWearable("Accept");
        cm_api_ads_insertELT.insertNewAdWearableBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy Accessories Ad is free, Vu Hoang, ME")
    public void verifyInsert_Accessories_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdAccessories("Accept");
        cm_api_ads_insertELT.insertNewAdAccessories("Accept");
        cm_api_ads_insertELT.insertNewAdAccessories("Accept");
        cm_api_ads_insertELT.insertNewAdAccessoriesBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            description =
                    "Listing Fee - ELT - Obligate Pro User, Verify Buy PC Component Ad is free, Vu Hoang, ME")
    public void verifyInsert_PC_Component_ObligateProBuyFree() {
        initObjects();

        // Insert Laptop Ad
        cm_api_ads_insertELT.insertNewAdPC_Component("Accept");
        cm_api_ads_insertELT.insertNewAdPC_Component("Accept");
        cm_api_ads_insertELT.insertNewAdPC_Component("Accept");
        cm_api_ads_insertELT.insertNewAdPC_ComponentBuyPro();

        // Login
        cm_login.loginAndGoToManageAd(true, false);

        // Go to private board
        privateDashboard.goToPrivateDashboard();

        // Check Ad displays on "Khác" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        // Check Ad displays on "Đợi duyệt" tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    // REFUND
    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - ELT - Private User, Verify Dong Tot is refunded after Laptop Ad is rejected, Vu Hoang, ME")
    public void verifyInsertLaptopAdRejectedAndRefund() {
        // Initialize objects
        initObjects();

        // First free ad
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        cm_api_ads_insertELT.insertNewAdLaptop("Accept");
        // Nap tien Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Ad is rejected
        cm_api_ads_insertELT.insertNewAdLaptop("RejectPay");

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
                    "Listing Fee - ELT - Private User, Verify Dong Tot is refunded after Phone Ad is rejected, Vu Hoang, ME")
    public void verifyInsertPhoneAdRejectedAndRefund() {
        // Initialize objects
        initObjects();

        // First free ad
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        cm_api_ads_insertELT.insertNewAdPhone("Accept");
        topupDongTotWithMomo(tempUserPhone);

        // Ad is rejected
        cm_api_ads_insertELT.insertNewAdPhone("RejectPay");

        // Go to private dashboard to get init money
        cm_login.loginAndGoToManageAd(true, false);

        // Get Dong Tot amount to compare after insert free ad
        privateDashboard.goToPrivateDashboard();

        // Verify new ad is displayed on Reject tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }
}
