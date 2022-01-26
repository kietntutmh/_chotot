package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Job;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertJob;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adJobExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Job_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads cm_ads;
    CM_Ads_Job cm_ads_job;
    POS pos;
    CM_API_Ads_InsertJob cm_api_ads_insertJob;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_job = new CM_Ads_Job();
        pos = new POS();
        cm_api_ads_insertJob = new CM_API_Ads_InsertJob();
    }

    @When("All of InsertAd Job APIs should be working")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertJob.insertNewAdJob();
        checkAdOnDashboard(tempAdID);
    }

    @When("I post a new Recruitment Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Find_Job Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Recruitment Ad as a Pro ad")
    public void verifyInsertProAdsRecruitmentPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Recruitment");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_job.insertRecruitment(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Find_Job Ad as a Pro ad")
    public void verifyInsertProAdsFind_JobPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Find_Job");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_job.insertFind_Job(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
