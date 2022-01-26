package desktop.scenarios_old.ads.pos;

import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertMomAndBaby;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_MomAndBaby extends BaseTest {
    CM_API_Ads_InsertMomAndBaby cm_api_ads_insertMomAndBaby;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;

    public void initObjects() {
        cm_api_ads_insertMomAndBaby = new CM_API_Ads_InsertMomAndBaby();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads PTY, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        tempAdID = cm_api_ads_insertMomAndBaby.insertNewAdMomAndBaby();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat", "dev"},
            description = "POS - Ads Mom And Baby, Verify POS of Mom And Baby Ad, Vu Hoang, ME")
    public void verifyPOSOfMomAndBabyAd() {
        // Initialize objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_MomAndBaby.xlsx", "MomAndBaby");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        tempAdID = cm_api_ads_insertMomAndBaby.insertNewAdMomAndBaby();

        // Login and go to Manage Ad page
        cmLogin.loginAndGoToManageAd(true, false);

        // Open POS page
        pos.openPOSPage(tempAdID);

        // Check default values
        pos.verifyListDefaultPrice(listDefaultPrice);

        // Select all premium services
        String totalAmount = pos.selectAllPremiumItemByName();

        // Verify checkout
        cmPaymentDongTot.paymentWithDongTot(totalAmount);
    }
}
