package desktop.scenarios_old.ads.pos;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertElectricAppliances;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_ElectricAppliances extends BaseTest {
    CM_API_Ads_InsertElectricAppliances cm_api_ads_insertElectricAppliances;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void initObjects() {
        cm_api_ads_insertElectricAppliances = new CM_API_Ads_InsertElectricAppliances();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads ElectricAppliances, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertElectricAppliances.insertNewAdCooler();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertElectricAppliances.insertNewAdRefrigerator();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertElectricAppliances.insertNewAdWashing_Machine();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat", "dev"},
            description = "POS - Ads ElectricAppliances, Verify POS of Cooler Ad, Vu Hoang, ME")
    public void verifyPOSOfCoolerAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_ElectricAppliances.xlsx", "Cooler");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertElectricAppliances.insertNewAdCooler();

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

    @Test(description = "POS - Ads ElectricAppliances, Verify POS of Refrigerator Ad, Vu Hoang, ME")
    public void verifyPOSOfRefrigeratorAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_ElectricAppliances.xlsx", "Refrigerator");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertElectricAppliances.insertNewAdRefrigerator();

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
            description = "POS - Ads ElectricAppliances, Verify POS of Washing_Machine Ad, Vu Hoang, ME")
    public void verifyPOSOfWashing_MachineAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/pos/Bump_Ads_ElectricAppliances.xlsx", "Washing_Machine");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertElectricAppliances.insertNewAdWashing_Machine();

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
