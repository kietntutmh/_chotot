package desktop.scenarios_old.ads.pos;

import api.feature.ads.DashboardAds;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.CM_Login;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertJob;
import projects.functions.chotot._common.payment.CM_Payment_DongTot;

import java.util.List;

import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class POS_Job extends BaseTest {
    CM_API_Ads_InsertJob cm_api_ads_insertJob;
    CM_Login cmLogin;
    POS pos;
    CM_Payment_DongTot cmPaymentDongTot;
    DashboardAds api_dashboard;

    public void initObjects() {
        cm_api_ads_insertJob = new CM_API_Ads_InsertJob();
        cmLogin = new CM_Login();
        pos = new POS();
        cmPaymentDongTot = new CM_Payment_DongTot();
        api_dashboard = new DashboardAds();
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "POS - Ads Job, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
//        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
//            return;
//        }
//
//        // Init objects
//        initObjects();
//
//        tempAdID = cm_api_ads_insertJob.insertNewAdJob();
//        DashboardAds.checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat", "dev"},
            description = "POS - Ads Job, Verify POS of Job Recruitment Ad, Vu Hoang, ME")
    public void verifyPOSOfJobRecruitmentAd() {
        // Init objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Job.xlsx", "Recruitment");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertJob.insertNewAdJob();

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
            groups = {"uat", "dev"},
            description = "POS - Ads Job, Verify POS of Job Find Ad, Vu Hoang, ME")
    public void verifyPOSOfJobFindAd() {
        // Init objects
        initObjects();

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/pos/Bump_Ads_Job.xlsx", "Find_Job");
        List<String> listDefaultPrice = excelDataProvider.getRowData(1);

        // Insert new ad
        String adID = cm_api_ads_insertJob.insertNewAdJob();

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
