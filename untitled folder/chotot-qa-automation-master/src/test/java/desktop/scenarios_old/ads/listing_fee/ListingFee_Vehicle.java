package desktop.scenarios_old.ads.listing_fee;

import api.feature.ads.DashboardAds;
import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.PrivateDashboard;
import desktop.pages.Payment.OrderHistory;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditVEH;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;
import projects.functions.chotot._common.cp.CM_CP_AcceptAd;
import projects.functions.chotot._common.cp.CM_CP_Login;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.Date;

import static api.configuration.DataConfig.*;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static desktop.configuration.PaymentConfig.listingFee_VehicleExcelFile;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class ListingFee_Vehicle extends BaseTest {
    CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle;
    CM_API_Ads_EditVEH cm_api_ads_editVEH;
    PrivateDashboard privateDashboard;
    CM_Login cm_login;
    CM_CP_AcceptAd cm_cp_acceptAd;
    CM_Payment_DongTot cm_payment_dongTot;
    OrderHistory orderHistory;
    CM_CP_Login cm_cp_login;
    DashboardAds api_dashboard;

    private void initObjects() {
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        cm_api_ads_editVEH = new CM_API_Ads_EditVEH();
        privateDashboard = new PrivateDashboard();
        cm_login = new CM_Login();
        cm_cp_acceptAd = new CM_CP_AcceptAd();
        cm_payment_dongTot = new CM_Payment_DongTot();
        orderHistory = new OrderHistory();
        cm_cp_login = new CM_CP_Login();
        api_dashboard = new DashboardAds();
        setTempAccountCPAPIIndex(6);
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "Listing Fee - VEH - API Check, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        // init objects
        initObjects();

        // Check Payment & Dashboard
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");
        cm_api_ads_insertVehicle.insertNewAdCar("AcceptPay");
        checkAdOnDashboard(tempAdID);

        // Check edit ad accept
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Reject");
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "Accept");
        checkAdOnDashboard(tempAdID);

        // Check edit ad reject
        cm_api_ads_insertVehicle.insertNewAdTrucks("Reject");
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "Reject");
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - Vehicle, Verify the first Car Ad is free, Quoc Tran, ME")
    public void verifyInsertFirstAdCar() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - Vehicle, Verify the first Motorbike Ad is free, Quoc Tran, ME")
    public void verifyInsertFirstAdMotorbike() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(description = "Listing Fee - Vehicle, Verify the first Truck Ad is free, Quoc Tran, ME")
    public void verifyInsertFirstAdTruck() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");

        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐỢI DUYỆT");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - Vehicle, Verify the second Car Ad is charged fee, Quoc Tran, ME")
    public void verifyInsertSecondAdCar() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify the second Motorbike Ad is charged fee, Quoc Tran, ME")
    public void verifyInsertSecondAdMotorbike() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify the second Truck Ad is charged fee, Quoc Tran, ME")
    public void verifyInsertSecondAdTruck() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert second Car ad is charged fee when the first ad is published , Quoc Tran, ME")
    public void verifyInsertSecondAdCarIsChargedAfterFirstAdPublished() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        String ad_id = cm_api_ads_insertVehicle.insertNewAdCar("Accept");

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Verify status of second ad is un-review
        //cm_cp_acceptAd.loginAndVerifyUnreviewStatus(ad_id);
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "Listing Fee - Vehicle, Verify insert second Motorbike ad is charged fee when the first ad is published , Quoc Tran, ME")
    public void verifyInsertSecondAdMotorbikeIsChargedAfterFirstAdPublished() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Accept");

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Verify status of second ad is un-review
        // cm_cp_acceptAd.loginAndVerifyUnreviewStatus(ad_id);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert second Truck ad is charged fee when the first ad is published , Quoc Tran, ME")
    public void verifyInsertSecondAdTruckIsChargedAfterFirstAdPublished() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        String ad_id = cm_api_ads_insertVehicle.insertNewAdTrucks("Accept");

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Verify status of second ad is un-review
        // cm_cp_acceptAd.loginAndVerifyUnreviewStatus(ad_id);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert second Car ad is free when the first ad is rejected , Quoc Tran, ME")
    public void verifyInsertSecondAdCarFreeAfterFirstAdIsRejected() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdCar("reject");

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify second ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle, Verify insert second Motorbike ad is free when the first ad is rejected , Quoc Tran, ME")
    public void verifyInsertSecondAdMotorbikeFreeAfterFirstAdIsRejected() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike("reject");

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify second ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert second Truck ad is free when the first ad is rejected , Quoc Tran, ME")
    public void verifyInsertSecondAdTruckFreeAfterFirstAdIsRejected() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdTrucks("reject");

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify second ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "KHÁC");
    }

    @Test(
            groups = {"uat"},
            description = "Listing Fee - Vehicle, Verify refund for rejected ad Car, Quoc Tran, ME")
    public void verifyRefundForRejectedAdCar() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdCar("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description = "Listing Fee - Vehicle, Verify refund for rejected ad Motorbike, Quoc Tran, ME")
    public void verifyRefundForRejectedAdMotorbike() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(description = "Listing Fee - Vehicle, Verify refund for rejected ad Truck, Quoc Tran, ME")
    public void verifyRefundForRejectedAdTruck() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert the second ad
        cm_api_ads_insertVehicle.insertNewAdTrucks("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle, Verify insert ad with type Need buy is free, Quoc Tran, ME")
    public void verifyInsertAdNeedBuyTypeIsFree() {
        // Initialize objects
        initObjects();

        // Insert the first ad car
        cm_api_ads_insertVehicle.insertNewAdCar_NeedBuyType();

        // Insert the second ad car
        cm_api_ads_insertVehicle.insertNewAdCar_NeedBuyType();
        String carSubject = tempAdSubject;
        String carID = tempAdID;

        // Insert the first ad motorbike
        cm_api_ads_insertVehicle.insertNewAdMotorbike_NeedBuyType();

        // Insert the second ad motorbike
        cm_api_ads_insertVehicle.insertNewAdMotorbike_NeedBuyType();
        String motorbikeSubject = tempAdSubject;
        String motorbikeID = tempAdID;

        // Insert the first ad truck
        cm_api_ads_insertVehicle.insertNewAdTrucks_NeedBuyType();

        // Insert the second ad truck
        cm_api_ads_insertVehicle.insertNewAdTrucks_NeedBuyType();
        String truckSubject = tempAdSubject;
        String truckID = tempAdID;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ads are displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(carSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(motorbikeSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(truckSubject, "KHÁC");

        // Verify status of new ads is un-review
        // cm_cp_acceptAd.loginAndVerifyUnreviewStatus(carID);
        // cm_cp_acceptAd.verifyUnreviewStatus(motorbikeID);
        // cm_cp_acceptAd.verifyUnreviewStatus(truckID);
    }

    @Test(description = "Listing Fee - Vehicle, Verify unpaid ad Car, Quoc Tran, ME")
    public void verifyUnpaidAdCar() {
        // Initialize objects
        initObjects();

        // Get listing fee
        excelDataProvider.getExcelFileSheet(listingFee_VehicleExcelFile, "Car");
        String fee = excelDataProvider.getRowData(1).get(0);

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdCar();

        // Insert the second ad
        String ad_id = cm_api_ads_insertVehicle.insertNewAdCar();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Click checkout
        String totalAmount = privateDashboard.clickCheckout();

        // Verify listing fee
        verifyMatch(totalAmount, fee, false, FailureHandling.CONTINUE_ON_FAILURE);

        // Verify status of second ad is unpaid
        // cm_cp_acceptAd.loginAndVerifyUnpaidStatus(ad_id);
    }

    @Test(description = "Listing Fee - Vehicle, Verify unpaid ad Motorbike, Quoc Tran, ME")
    public void verifyUnpaidAdMotorbike() {
        // Initialize objects
        initObjects();

        // Get listing fee
        excelDataProvider.getExcelFileSheet(listingFee_VehicleExcelFile, "Motorbike");
        String fee = excelDataProvider.getRowData(1).get(0);

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Insert the second ad
        String ad_id = cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Click checkout
        String totalAmount = privateDashboard.clickCheckout();

        // Verify listing fee
        verifyMatch(totalAmount, fee, false, FailureHandling.CONTINUE_ON_FAILURE);

        // Verify status of second ad is unpaid
        // cm_cp_acceptAd.loginAndVerifyUnpaidStatus(ad_id);
    }

    @Test(description = "Listing Fee - Vehicle, Verify unpaid ad Truck, Quoc Tran, ME")
    public void verifyUnpaidAdTruck() {
        // Initialize objects
        initObjects();

        // Get listing fee
        excelDataProvider.getExcelFileSheet(listingFee_VehicleExcelFile, "Trucks");
        String fee = excelDataProvider.getRowData(1).get(0);

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Insert the second ad
        String ad_id = cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");

        // Click checkout
        String totalAmount = privateDashboard.clickCheckout();

        // Verify listing fee
        verifyMatch(totalAmount, fee, false, FailureHandling.CONTINUE_ON_FAILURE);

        // Verify status of second ad is unpaid
        // cm_cp_acceptAd.loginAndVerifyUnpaidStatus(ad_id);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert ad for other VEH categories is free, Quoc Tran, ME")
    public void verifyInsertAdOtherVEHCategoryIsFree() {
        // Initialize objects
        initObjects();

        // Insert the first ad bicycle
        cm_api_ads_insertVehicle.insertNewAdBicycles();

        // Insert the second ad bicycle
        cm_api_ads_insertVehicle.insertNewAdBicycles();
        String bicycleAdSubject = tempAdSubject;
        String bicycleAdID = tempAdID;

        // Insert the first ad Electric_Vehicle
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle();

        // Insert the second ad Electric_Vehicle
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle();
        String electric_VehicleAdSubject = tempAdSubject;
        String electric_VehicleAdID = tempAdID;

        // Insert the first ad Other_Vehicles
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles();

        // Insert the second ad Other_Vehicles
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles();
        String other_VehiclesAdSubject = tempAdSubject;
        String other_VehiclesAdID = tempAdID;

        // Insert the first ad Vehicles_Parts
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts();

        // Insert the second ad Vehicles_Parts
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts();
        String vehicles_PartsAdSubject = tempAdSubject;
        String vehicles_PartsAdID = tempAdID;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ads are displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(bicycleAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(other_VehiclesAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(electric_VehicleAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(vehicles_PartsAdSubject, "KHÁC");

        // Verify status of new ads is un-review
        // cm_cp_acceptAd.loginAndVerifyUnreviewStatus(bicycleAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(other_VehiclesAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(electric_VehicleAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(vehicles_PartsAdID);
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle, Verify insert Car Ad is always charged fee of Pro User, Quoc Tran, ME")
    public void verifyInsertAdCaOfFProUser() {
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdCarForPro();
        String firstSubject = tempAdSubject;

        // Insert second ad
        cm_api_ads_insertVehicle.insertNewAdCarForPro();
        String secondSubject = tempAdSubject;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(firstSubject, "CẦN THANH TOÁN");
        privateDashboard.verifyAdSubjectDisplayed(secondSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert Motorbike Ad is always charged fee of Pro User, Quoc Tran, ME")
    public void verifyInsertFirstAdMotorbikeOfProUser() {
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(true);
        String firstSubject = tempAdSubject;

        // Insert second ad
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(false);
        String secondSubject = tempAdSubject;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(firstSubject, "CẦN THANH TOÁN");
        privateDashboard.verifyAdSubjectDisplayed(secondSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert Truck Ad is always charged fee of Pro User, Quoc Tran, ME")
    public void verifyInsertFirstAdTruckOfProUser() {
        // Initialize objects
        cm_login = new CM_Login();
        privateDashboard = new PrivateDashboard();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        cm_payment_dongTot = new CM_Payment_DongTot();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro();
        String firstSubject = tempAdSubject;

        // Insert second ad
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro();
        String secondSubject = tempAdSubject;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(firstSubject, "CẦN THANH TOÁN");
        privateDashboard.verifyAdSubjectDisplayed(secondSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert ad for other VEH categories is free of Pro User, Quoc Tran, ME")
    public void verifyInsertAdOtherVEHCategoryIsFreeOfProUser() {
        // Initialize objects
        initObjects();

        // Insert ad bicycle
        cm_api_ads_insertVehicle.insertNewAdBicyclesForPro();
        String bicycleAdSubject = tempAdSubject;
        String bicycleAdID = tempAdID;

        // Insert ad Electric_Vehicle
        cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro();
        String electric_VehicleAdSubject = tempAdSubject;
        String electric_VehicleAdID = tempAdID;

        // Insert ad Other_Vehicles
        cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro();
        String other_VehiclesAdSubject = tempAdSubject;
        String other_VehiclesAdID = tempAdID;

        // Insert ad Vehicles_Parts
        cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro();
        String vehicles_PartsAdSubject = tempAdSubject;
        String vehicles_PartsAdID = tempAdID;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ads are displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(bicycleAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(other_VehiclesAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(electric_VehicleAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(vehicles_PartsAdSubject, "KHÁC");

        // Verify status of new ads is un-review
        // cm_cp_acceptAd.loginAndVerifyUnreviewStatus(bicycleAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(other_VehiclesAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(electric_VehicleAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(vehicles_PartsAdID);
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle, Verify refund for rejected ad Car for pro User , Quoc Tran, ME")
    public void verifyRefundForRejectedAdCarOfProUser() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdCarForPro("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify refund for rejected ad Motorbike of Pro User, Quoc Tran, ME")
    public void verifyRefundForRejectedAdMotorbikeOfProUser() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(true, "rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Go to history
        orderHistory.goToOrderHistory();

        // Verify Refund in history
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify refund for rejected ad Truck of Pro User, Quoc Tran, ME")
    public void verifyRefundForRejectedAdTruckOfProUser() {
        // Initialize objects
        initObjects();

        // Insert the first ad
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
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
                    "Listing Fee - Vehicle, Verify insert Car ad of user is converted to Pro automatically, Quoc Tran, ME")
    public void verifyInsertAdCarOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert 3 ad cars
        cm_api_ads_insertVehicle.insertNewAdCar("accept");
        cm_api_ads_insertVehicle.insertNewAdCar("acceptpay");
        cm_api_ads_insertVehicle.insertNewAdCar("acceptpay");

        // Insert the 4th ad with pro type
        cm_api_ads_insertVehicle.insertNewAdCarForPro();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify 4th ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert Motorbike ad with user that is converted to Pro automatically, Quoc Tran, ME")
    public void verifyInsertAdMotorbikeOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert 3 ad cars
        cm_api_ads_insertVehicle.insertNewAdMotorbike("accept");
        cm_api_ads_insertVehicle.insertNewAdMotorbike("acceptpay");
        cm_api_ads_insertVehicle.insertNewAdMotorbike("acceptpay");

        // Insert the 4th ad with pro type
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(false);

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify 4th ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert Truck ad with user that is converted to Pro automatically, Quoc Tran, ME")
    public void verifyInsertAdTruckOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert 3 ad cars
        cm_api_ads_insertVehicle.insertNewAdTrucks("accept");
        cm_api_ads_insertVehicle.insertNewAdTrucks("acceptpay");
        cm_api_ads_insertVehicle.insertNewAdTrucks("acceptpay");

        // Insert the 4th ad with pro type
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro();

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify 4th ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "CẦN THANH TOÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle, Verify refund for rejected ad Car of User is converted to Pro automatically, Quoc Tran, ME")
    public void verifyRefundForRejectedAdCarOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Insert ad cars
        cm_api_ads_insertVehicle.insertNewAdCar("accept");

        // Topup Dong tot
        topupDongTotWithMomo(tempUserPhone);

        // Insert more ads
        cm_api_ads_insertVehicle.insertNewAdCar("acceptpay");
        cm_api_ads_insertVehicle.insertNewAdCar("acceptpay");

        // Insert the 4th ad with pro type
        cm_api_ads_insertVehicle.insertNewAdCarForPro("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify refund for rejected ad Motorbike of User is converted to Pro automatically, Quoc Tran, ME")
    public void verifyRefundForRejectedAdMotorbikeOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Insert ad cars
        cm_api_ads_insertVehicle.insertNewAdMotorbike("accept");

        // Topup Dong tot
        topupDongTotWithMomo(tempUserPhone);

        // Insert more ads
        cm_api_ads_insertVehicle.insertNewAdMotorbike("acceptpay");
        cm_api_ads_insertVehicle.insertNewAdMotorbike("acceptpay");

        // Insert the 4th ad with pro type
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify refund for rejected ad Trucks of User is converted to Pro automatically, Quoc Tran, ME")
    public void verifyRefundForRejectedAdTruckOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Insert ad cars
        cm_api_ads_insertVehicle.insertNewAdTrucks("accept");

        // Topup Dong tot
        topupDongTotWithMomo(tempUserPhone);

        // Insert more ads
        cm_api_ads_insertVehicle.insertNewAdTrucks("acceptpay");
        cm_api_ads_insertVehicle.insertNewAdTrucks("acceptpay");

        // Insert the 4th ad with pro type
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("rejectpay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Payment tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify Refund in history
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle, Verify insert ad for other VEH categories is free of User is converted to Pro automatically, Quoc Tran, ME")
    public void verifyInsertAdOtherVEHCategoryIsFreeOfUserIsConvertedToProAutomatically() {
        // Initialize objects
        initObjects();

        // Insert ad bicycle
        cm_api_ads_insertVehicle.insertNewAdBicyclesForPro();
        cm_api_ads_insertVehicle.insertNewAdBicyclesForPro();
        String bicycleAdSubject = tempAdSubject;
        String bicycleAdID = tempAdID;

        // Insert ad Electric_Vehicle
        cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro();
        cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro();
        String electric_VehicleAdSubject = tempAdSubject;
        String electric_VehicleAdID = tempAdID;

        // Insert ad Other_Vehicles
        cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro();
        cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro();
        String other_VehiclesAdSubject = tempAdSubject;
        String other_VehiclesAdID = tempAdID;

        // Insert ad Vehicles_Parts
        cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro();
        cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro();
        String vehicles_PartsAdSubject = tempAdSubject;
        String vehicles_PartsAdID = tempAdID;

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ads are displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(bicycleAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(other_VehiclesAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(electric_VehicleAdSubject, "KHÁC");
        privateDashboard.verifyAdSubjectDisplayed(vehicles_PartsAdSubject, "KHÁC");

        // Verify status of new ads is un-review
        // cm_cp_acceptAd.loginAndVerifyUnreviewStatus(bicycleAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(other_VehiclesAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(electric_VehicleAdID);
        // cm_cp_acceptAd.verifyUnreviewStatus(vehicles_PartsAdID);
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle - Private User, Verify Car Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertCar_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Motorbike Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertMotorbike_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Trucks Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertTrucks_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdTrucks("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Electric_Vehicle Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertElectric_Vehicle_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Bicycles Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertBicycles_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdBicycles("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Vehicles_Parts Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertVehicles_Parts_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Other_Vehicles Ad displays on Listing after accepted, Vu Hoang, ME")
    public void verifyInsertOther_Vehicles_CPAccepted() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles("Accept");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"dev", "uat"},
            description =
                    "Listing Fee - Vehicle - Private User, Verify Car Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertCar_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdCar("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Motorbike Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertMotorbike_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Trucks Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertTrucks_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdTrucks("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Electric_Vehicle Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertElectric_Vehicle_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Bicycles Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertBicycles_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdBicycles("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Vehicles_Parts Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertVehicles_Parts_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Other_Vehicles Ad displays on Listing after Rejected, Vu Hoang, ME")
    public void verifyInsertOther_Vehicles_CPRejected() {
        // Initialize objects
        initObjects();

        // Insert new ad
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Other tab
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle - Private User, Verify Car Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertCar_Edit_CPAcceptedRejected() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdCar("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdCar(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_editVEH.editNewAdCar(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Motorbike Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertMotorbike_Edit_CPAcceptedRejected() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdMotorbike("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "AccepPay");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify Truck Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertTruck_Edit_CPAcceptedRejected() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdTrucks("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdTrucks("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle - Private User, Verify edit Car Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertCar_Pubished_Edit_CPAcceptedRejected() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdCar("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdCar(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify edit Motorbike Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertMotorbike_Pubished_Edit_CPAcceptedRejected() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdMotorbike("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Private User, Verify edit Trucks Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertTrucks_Pubished_Edit_CPAcceptedRejected() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdTrucks("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdTrucks("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "RejectPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    // PRO USER
    @Test(
            groups = {"uat", "dev"},
            description =
                    "Listing Fee - Vehicle - Pro, Verify Car Pro Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertCar_Edit_CPAcceptedRejected_Pro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdCarForPro();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdCarForPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_editVEH.editNewAdCarPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Pro, Verify Motorbike Pro Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertMotorbike_Edit_CPAcceptedRejected_Pro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_editVEH.editNewAdMotorbikePro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Pro, Verify Truck Pro Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertTruck_Edit_CPAcceptedRejected_Pro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro();
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_editVEH.editNewAdTrucksPro(tempAdID, "acceptpay");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle - Pro, Verify edit Pro Car Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertCar_Pro_Pubished_Edit_Free() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdCarForPro("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdCarPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Pro, Verify edit Pro Motorbike Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertMotorbike_Pro_Pubished_Edit_Free() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(true, "AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdMotorbikePro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Pro, Verify edit Pro Trucks Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertTrucks_Pro_Pubished_Edit_Free() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdTrucksPro(tempAdID, "AcceptPay");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    // PRO OBLIGATE
    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Car Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertCar_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdCar("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdCar("AcceptPay");

        cm_api_ads_insertVehicle.insertNewAdCarForPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdCarPro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdCarForPro("RejectPay");
        cm_api_ads_editVEH.editNewAdCar(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Motorbike Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertMotorbike_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdMotorbike("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdMotorbike("AcceptPay");

        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdMotorbikePro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(false, "RejectPay");
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Trucks Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertTrucks_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdTrucks("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdTrucks("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdTrucks("AcceptPay");

        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("RejectPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdTrucksPro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // Check money is refunded
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Hoàn trả Đồng Tốt";
        orderHistory.verifyOrderHistoryItemDisplayed(refundItem);

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("RejectPay");
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Electric Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertElectric_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle("Accept");
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle("Accept");

        cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdElectric_VehiclePro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro("Reject");
        cm_api_ads_editVEH.editNewAdElectric_Vehicle(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Bicycles Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertBicycles_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdBicycles("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdBicycles("Accept");
        cm_api_ads_insertVehicle.insertNewAdBicycles("Accept");
        cm_api_ads_insertVehicle.insertNewAdBicyclesForPro("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdBicyclesPro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdBicyclesForPro("Reject");
        cm_api_ads_editVEH.editNewAdBicycles(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Vehicle_Parts Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertVehicle_Parts_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts("Accept");
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts("Accept");
        cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdVehicles_PartsPro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro("Reject");
        cm_api_ads_editVEH.editNewAdVehicles_Parts(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify Other_Vehicles Ad displays on list after edit accepted or rejected, Vu Hoang, ME")
    public void verifyInsertOther_Vehicles_Edit_CPAcceptedRejected_ObligatePro() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles("Accept");
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles("Accept");
        cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro("Reject");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdOther_VehiclesPro(tempAdID, "Reject");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "BỊ TỪ CHỐI");

        // 2. Edit and accepted
        cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro("Reject");
        cm_api_ads_editVEH.editNewAdOther_Vehicles(tempAdID, "Accept");

        // Verify new ad is displayed on Selling tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");
    }

    @Test(
            groups = {"uat"},
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify edit Car Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertCar_Pubished_Edit_Free() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdCar("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdCar("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdCar("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdCarForPro("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdCar(tempAdID, "Accept");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify edit Motorbike Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertMotorbike_Pubished_Edit_Free() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdMotorbike("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdMotorbike("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdMotorbike("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdMotorbike(tempAdID, "Accept");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }

    @Test(
            description =
                    "Listing Fee - Vehicle - Obligate Pro, Verify edit Trucks Ad isn't charge, Vu Hoang, ME")
    public void verifyInsertTrucks_Pubished_Edit_Free() {
        // Initialize objects
        initObjects();

        // Insert 2 ads
        cm_api_ads_insertVehicle.insertNewAdTrucks("Accept");
        topupDongTotWithMomo(tempUserPhone);
        cm_api_ads_insertVehicle.insertNewAdTrucks("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdTrucks("AcceptPay");
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro("AcceptPay");

        // Go to private dashboard
        cm_login.loginAndGoToManageAd(true, false);

        // 1. Edit but rejected
        cm_api_ads_editVEH.editNewAdTrucks(tempAdID, "Accept");

        // Verify new ad is displayed on Reject tab
        privateDashboard.goToPrivateDashboard();
        privateDashboard.verifyAdSubjectDisplayed(tempAdSubject, "ĐANG BÁN");

        // Verify DongTot is only charged from Insert ad, not Edit ad
        orderHistory.goToOrderHistory();
        String refundItem = getDateInStringFormat(new Date(), "dd/MM/yyyy") + "Phí đăng tin \"EDITED";
        orderHistory.verifyDongTotAmount(refundItem);
    }
}
