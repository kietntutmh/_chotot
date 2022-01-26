package desktop.scenarios_old.ads.pos;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.setTempAccountAndGetToken;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_ELT extends BaseTest {
    CM_API_Ads_InsertELT cm_api_ads_insertELT;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void initObjects() {
        cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    public void setupAccountWithShop() {
        setTempAccountAndGetToken(9);
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads ELT, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        // init objects
        initObjects();

        cm_api_ads_insertELT.insertNewAdPhone();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdLaptop();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdTablet();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdPC();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdPC_Component();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdAccessories();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdSound();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdWearable();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertELT.insertNewAdCamera();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"dev", "uat"},
            description = "POS - Ads ELT, Verify POS of Phone ad private, Vu Hoang, ME")
    public void verifyPOSOfCellphoneAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Cellphone");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdPhone(); // default is false

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
            description = "POS - Ads ELT, Verify POS of Phone Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfCellphoneAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Cellphone");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdPhonePro();

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
            description = "POS - Ads ELT, Verify POS of Phone Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfCellphoneAdShopToChoTot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Cellphone");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdPhoneChotot();

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
            description = "POS - Ads ELT, Verify POS of Laptop Ad private, Vu Hoang, ME")
    public void verifyPOSOfLaptopAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Laptop");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdLaptop();

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

    @Test(description = "POS - Ads ELT, Verify POS of Laptop Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfLaptopAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Laptop");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdLaptopPro();

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

    @Test(description = "POS - Ads ELT, Verify POS of Laptop Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfLaptopAdShopToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Laptop");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.inserAdLaptopChotot();

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
            description = "POS - Ads ELT, Verify POS of Tablet Ad private, Vu Hoang, ME")
    public void verifyPOSOfTabletAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Tablet");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdTablet();

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

    @Test(description = "POS - Ads ELT, Verify POS of Tablet Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfTabletAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Tablet");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdTabletPro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of Tablet Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfTabletAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Tablet");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdTabletChotot();

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
            description = "POS - Ads ELT, Verify POS of Desktop Ad private, Vu Hoang, ME")
    public void verifyPOSOfDesktopAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Desktop");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdPC();

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

    @Test(description = "POS - Ads ELT, Verify POS of Desktop Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfDesktopAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Desktop");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdPCPro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of Desktop Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfDesktopAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Desktop");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdPCChotot();

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
            description = "POS - Ads ELT, Verify POS of Camera Ad private, Vu Hoang, ME")
    public void verifyPOSOfCameraAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Camera");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdCamera();

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

    @Test(description = "POS - Ads ELT, Verify POS of Camera Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfCameraAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Camera");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdCameraPro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of Camera Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfCameraAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Camera");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdCameraChotot();

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
            description = "POS - Ads ELT, Verify POS of TV_Sound Ad private, Vu Hoang, ME")
    public void verifyPOSOfTV_SoundAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "TV_Sound");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdSound();

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

    @Test(description = "POS - Ads ELT, Verify POS of TV_Sound Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfTV_SoundAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "TV_Sound");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdSoundPro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of TV_Sound Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfTV_SoundAdtoChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "TV_Sound");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdSoundChotot();

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
            description = "POS - Ads ELT, Verify POS of Smart_Watch Ad private, Vu Hoang, ME")
    public void verifyPOSOfSmart_WatchAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Smart_Watch");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdWearable();

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

    @Test(description = "POS - Ads ELT, Verify POS of Smart_Watch Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfSmart_WatchAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Smart_Watch");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdWearablePro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of Smart_Watch Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfSmart_WatchAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Smart_Watch");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdWearableChotot();

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
            description = "POS - Ads ELT, Verify POS of Accessories_Monitor Ad private, Vu Hoang, ME")
    public void verifyPOSOfAccessories_MonitorAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Accessories_Monitor");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdAccessories();

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

    @Test(description = "POS - Ads ELT, Verify POS of Accessories_Monitor Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfAccessories_MonitorAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Accessories_Monitor");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdAccessoriesPro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of Accessories_Monitor Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfAccessories_MonitorAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Accessories_Monitor");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdAccessoriesChotot();

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
            description = "POS - Ads ELT, Verify POS of Accessories_RAM Ad private, Vu Hoang, ME")
    public void verifyPOSOfAccessories_RAMAdPrivate() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Accessories_RAM");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdPC_Component();

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

    @Test(description = "POS - Ads ELT, Verify POS of Accessories_RAM Ad pro user, Vu Hoang, ME")
    public void verifyPOSOfAccessories_RAMAdProUser() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Accessories_RAM");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertNewAdPC_ComponentPro(true);

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

    @Test(description = "POS - Ads ELT, Verify POS of Accessories_RAM Ad to Chotot, Vu Hoang, ME")
    public void verifyPOSOfAccessories_RAMAdToChotot() {
        // Initialize objects
        initObjects();

        // Setup account login
        setupAccountWithShop();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_ELT.xlsx", "Accessories_RAM");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertELT.insertAdPC_ComponentChotot();

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
