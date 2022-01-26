package desktop.scenarios_old.ads.pos;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertHousehold;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_Household_Furniture_Plant extends BaseTest {
    CM_API_Ads_InsertHousehold cm_api_ads_Insert_household_;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void initObjects() {
        cm_api_ads_Insert_household_ = new CM_API_Ads_InsertHousehold();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads Entertainment, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        // Initialize objects
        initObjects();
        cm_api_ads_Insert_household_.insertNewAdBathware();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat", "dev"},
            description = "POS - Ads Household_Furniture_Plant, Verify POS of Bathware Ad, Vu Hoang, ME")
    public void verifyPOSOfBathwareAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Bathware");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdBathware();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Bed_Bedding Ad, Vu Hoang, ME")
    public void verifyPOSOfBed_BeddingAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Bed_Bedding");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdHousehold_Bed();

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

    @Test(description = "POS - Ads Household_Furniture_Plant, Verify POS of Fan Ad, Vu Hoang, ME")
    public void verifyPOSOfFanAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Fan");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdFan();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Drawer_Shelf Ad, Vu Hoang, ME")
    public void verifyPOSOfDrawer_ShelfAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Drawer_Shelf");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdHousehold_Drawer_Shelf();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Table_Chair Ad, Vu Hoang, ME")
    public void verifyPOSOfTable_ChairAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Table_Chair");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdHousehold_Table_Chair();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Kitchen_Appliance Ad, Vu Hoang, ME")
    public void verifyPOSOfKitchen_ApplianceAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Kitchen_Appliance");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdKitchen_Appliance();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Kitchen_Utensil_Dinnerware Ad, Vu Hoang, ME")
    public void verifyPOSOfKitchen_Utensil_DinnerwareAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Kitchen_Utensil_Dinnerware");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdHousehold_Dinnerware();

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
            description = "POS - Ads Household_Furniture_Plant, Verify POS of Lighting Ad, Vu Hoang, ME")
    public void verifyPOSOfLightingAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Lighting");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdLighting();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Ornamental_Plant_Decoration Ad, Vu Hoang, ME")
    public void verifyPOSOfOrnamental_Plant_DecorationAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Ornamental_Plant_Decoration");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdOrnamental_Plant_Decoration();

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
            description =
                    "POS - Ads Household_Furniture_Plant, Verify POS of Other_Household_Items Ad, Vu Hoang, ME")
    public void verifyPOSOfOther_Household_ItemsAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_Household_Furniture_Plant.xlsx", "Other_Household_Items");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_Insert_household_.insertNewAdOther_Household_Items();

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
