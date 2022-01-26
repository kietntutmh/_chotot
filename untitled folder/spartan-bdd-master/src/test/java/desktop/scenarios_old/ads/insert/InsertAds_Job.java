package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Job;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertJob;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.DataConfig.adJobExcelFile;

public class InsertAds_Job extends BaseTest {
    CM_Ads cm_ads;
    CM_Ads_Job cm_ads_job;
    ManageAds manageAds;
    POS pos;
    CM_API_Ads_InsertJob cm_api_ads_insertJob;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_job = new CM_Ads_Job();
        pos = new POS();
        cm_api_ads_insertJob = new CM_API_Ads_InsertJob();
        manageAds = new ManageAds();
    }

    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertJob.insertNewAdJob();
        checkAdOnDashboard(tempAdID);
    }

    public void verifyInsertAdsRecruitmentPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Recruitment");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_job.insertRecruitment(insertData);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsFind_JobPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Find_Job");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_job.insertFind_Job(insertData);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
