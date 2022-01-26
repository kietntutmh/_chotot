package desktop.scenarios_old.ads.pos;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_Fashion extends BaseTest {
    CM_API_Ads_InsertFashion cm_api_ads_insertFashion;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void initObjects() {
        cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads Fashion, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertFashion.insertNewAdClothes();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertFashion.insertNewAdShoes();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertFashion.insertNewAdWatch();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertFashion.insertNewAdHandbag();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertFashion.insertNewAdPerfume();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertFashion.insertNewAdAccessories();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat", "dev"},
            description = "POS - Ads Fashion, Verify POS of Clothes Ad, Vu Hoang, ME")
    public void verifyPOSOfClothesAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Fashion.xlsx", "Clothes");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertFashion.insertNewAdClothes();

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

    @Test(description = "POS - Ads Fashion, Verify POS of Shoes Ad, Vu Hoang, ME")
    public void verifyPOSOfShoesAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Fashion.xlsx", "Shoes");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertFashion.insertNewAdShoes();

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

    @Test(description = "POS - Ads Fashion, Verify POS of Watch Ad, Vu Hoang, ME")
    public void verifyPOSOfWatchAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Fashion.xlsx", "Watch");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertFashion.insertNewAdWatch();

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

    @Test(description = "POS - Ads Fashion, Verify POS of Handbag Ad, Vu Hoang, ME")
    public void verifyPOSOfHandbagAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Fashion.xlsx", "Handbag");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertFashion.insertNewAdHandbag();

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

    @Test(description = "POS - Ads Fashion, Verify POS of Perfume Ad, Vu Hoang, ME")
    public void verifyPOSOfPerfumeAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Fashion.xlsx", "Perfume");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertFashion.insertNewAdPerfume();

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

    @Test(description = "POS - Ads Fashion, Verify POS of Accessories Ad, Vu Hoang, ME")
    public void verifyPOSOfAccessoriesAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Fashion.xlsx", "Accessories");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertFashion.insertNewAdAccessories();

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
