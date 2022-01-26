package desktop.scenarios_old.ads.pos;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.setTempAccountAndGetToken;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_VEH extends BaseTest {
    CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void setupAccountWithShop() {
        setTempAccountAndGetToken(10);
    }

    public void initObjects() {
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        // init objects
        initObjects();

        // Check Insert Ad and dashboard
        cm_api_ads_insertVehicle.insertNewAdCar();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdTrucks();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdMotorbike();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdBicycles();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Car Ad private, Vu Hoang, ME")
    public void verifyPOSOfCarAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Car");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdCar();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Car Chotot Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfCarAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Car");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdCarForPro();

        // pay order
        paymentOrderWithDongTot(FailureHandling.WARNING);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Car Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfCarAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Car");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdCarChotot();

        // pay order
        paymentOrderWithDongTot(FailureHandling.WARNING);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Motorbike Ad private, Vu Hoang, ME")
    public void verifyPOSOfMotorbikeAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Motorbike");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdMotorbike();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Motorbike Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfMotorbikeAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Motorbike");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro(true);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Motorbike Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfMotorbikeAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Motorbike");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdMotorbikeChotot();

        // pay order
        paymentOrderWithDongTot(FailureHandling.WARNING);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Trucks Ad private, Vu Hoang, ME")
    public void verifyPOSOfTrucksAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Trucks");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdTrucks();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Trucks Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfTrucksAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Trucks");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdTrucksForPro();

        // pay order
        paymentOrderWithDongTot(FailureHandling.WARNING);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Trucks Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfTrucksAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Trucks");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdTrucksChotot();

        // pay order
        paymentOrderWithDongTot(FailureHandling.WARNING);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Electric_Vehicle Ad private, Vu Hoang, ME")
    public void verifyPOSOfElectric_VehicleAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Electric_Vehicle");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Electric_Vehicle Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfElectric_VehicleAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Electric_Vehicle");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Electric_Vehicle Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfElectric_VehicleAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Electric_Vehicle");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdElectric_VehicleChotot(false);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Vehicle_Parts Ad private, Vu Hoang, ME")
    public void verifyPOSOfVehicle_PartsAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Vehicle_Parts");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdVehicles_Parts();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Vehicle_Parts Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfVehicle_PartsAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Vehicle_Parts");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Vehicle_Parts Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfVehicle_PartsAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Vehicle_Parts");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdVehicles_PartsChotot();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Other_Vehicles Ad private, Vu Hoang, ME")
    public void verifyPOSOfOther_VehiclesAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Other_Vehicles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdOther_Vehicles();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Other_Vehicles Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfOther_VehiclesAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Other_Vehicles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Other_Vehicles Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfOther_VehiclesAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Other_Vehicles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdOther_VehiclesChotot(false);

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads VEH, Verify POS of Bicycles Ad private, Vu Hoang, ME")
    public void verifyPOSOfBicyclesAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Bicycles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdBicycles();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Bicycles Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfBicyclesAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Bicycles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdBicyclesForPro();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads VEH, Verify POS of Bicycles Chotot Ad, Vu Hoang, ME")
    public void verifyPOSOfBicyclesAdChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Vehicle.xlsx", "Bicycles");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertVehicle.insertNewAdBicyclesChotot();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(false, false);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }
}
