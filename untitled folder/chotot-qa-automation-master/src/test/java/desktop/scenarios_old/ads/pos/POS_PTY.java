package desktop.scenarios_old.ads.pos;

import api.feature.ads.DashboardAds;
import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.setTempAccountAndGetToken;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_PTY extends BaseTest {
    CM_API_Ads_InsertPTY cmApiAdsInsertPTY;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;
    DashboardAds api_dashboard;

    public void setupAccountWithShop() {
        setTempAccountAndGetToken(3);
    }

    public void initObjects() {
        cmApiAdsInsertPTY = new CM_API_Ads_InsertPTY();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
        api_dashboard = new DashboardAds();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads PTY, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        // init objects
        initObjects();

        // Check Insert Ad and dashboard
        tempAdID = cmApiAdsInsertPTY.insertNewAdHouse();
        DashboardAds.checkAdOnDashboard(tempAdID);

        tempAdID = cmApiAdsInsertPTY.insertNewAdLand();
        DashboardAds.checkAdOnDashboard(tempAdID);

        tempAdID = cmApiAdsInsertPTY.insertNewAdApartment();
        DashboardAds.checkAdOnDashboard(tempAdID);

        tempAdID = cmApiAdsInsertPTY.insertNewAdOffice();
        DashboardAds.checkAdOnDashboard(tempAdID);

        tempAdID = cmApiAdsInsertPTY.insertNewAdRoomForLease();
        DashboardAds.checkAdOnDashboard(tempAdID);

        // Check Payment
        cmApiAdsInsertPTY.insertNewAdHouse("Accept");
        cmApiAdsInsertPTY.insertNewAdHouse();
        cmApiAdsInsertPTY.insertNewAdHouse();
        topupDongTotWithMomo(tempUserPhone);
    }

    @Test(
            groups = {"uat"},
            description = "POS - Ads PTY, Verify POS of House Ad private, Vu Hoang, ME")
    public void verifyPOSOfHouseAdPrivate() {
        // Initialize objects
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "House");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdHouse(true);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

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

    @Test(description = "POS - Ads PTY, Verify POS of House Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfHouseAdProUser() {
        // Initialize objects
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "House");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdHousePro();

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

    @Test(description = "POS - Ads PTY, Verify POS of House Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfHouseAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account
        setupAccountWithShop();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "House");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdHouseProObligateChotot(true);

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
            groups = {"uat"},
            description = "POS - Ads PTY, Verify POS of Apartment Ad private, Vu Hoang, ME")
    public void verifyPOSOfApartmentAdPrivate() {
        // Initialize objects
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Apartment");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdApartment(true);

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

    @Test(description = "POS - Ads PTY, Verify POS of Apartment Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfApartmentAdProUser() {
        // Initialize objects
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Apartment");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdApartmentPro();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Pay order
        paymentOrderWithDongTot(FailureHandling.WARNING);

        // Open POS page
        pos.openPOSPage(adID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }

    @Test(description = "POS - Ads PTY, Verify POS of Apartment Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfApartmentAdToChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Apartment");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdApartmentProObligateChotot(true);

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
            groups = {"uat"},
            description = "POS - Ads PTY, Verify POS of Land Ad private, Vu Hoang, ME")
    public void verifyPOSOfLandAdPrivate() {
        // Initialize objects
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Land");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdLand(true);

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

    @Test(description = "POS - Ads PTY, Verify POS of Land Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfLandAdProUser() {
        // Initialize objects
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Land");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdLandPro(true);

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

    @Test(description = "POS - Ads PTY, Verify POS of Land Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfLandAdToChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Land");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdLandProObligateChotot(true);

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
            groups = {"uat"},
            description = "POS - Ads PTY, Verify POS of Office Ad private, Vu Hoang, ME")
    public void verifyPOSOfOfficeAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Office");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdOffice(true);

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

    @Test(description = "POS - Ads PTY, Verify POS of Office Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfOfficeAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Office");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdOfficePro();

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

    @Test(description = "POS - Ads PTY, Verify POS of Office Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfOfficeAdToChotot() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // setup account
        setupAccountWithShop();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Office");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdOfficeProObligateChotot();

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
            groups = {"uat"},
            description = "POS - Ads PTY, Verify POS of Room Ad private, Vu Hoang, ME")
    public void verifyPOSOfRoomAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Room");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdRoomForLease(true);

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

    @Test(description = "POS - Ads PTY, Verify POS of Room Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfRoomAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Room");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdRoomForLeasePro();

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

    @Test(description = "POS - Ads PTY, Verify POS of Room Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfRoomAdToChotot() {
        // Initialize objects
        initObjects();

        // setup account
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_PTY.xlsx", "Room");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cmApiAdsInsertPTY.insertNewAdRoomForLeaseProObligateChotot(true);

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
}
