package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Others;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertOther;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Others_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    ManageAds manageAds;
    CM_Ads_Others cm_others;
    CM_API_Ads_InsertOther cm_api_ads_insertOther;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_others = new CM_Ads_Others();
        cm_ads = new CM_Ads();
        pos = new POS();
        manageAds = new ManageAds();
        cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
    }

    @Then("All of InsertAd Others APIs should be working")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }
        initObjects();
        cm_api_ads_insertOther.insertNewOtherAd();
        checkAdOnDashboard(tempAdID);
    }


    @When("I post a new Others Ad as a private ad")
    public void verifyInsertOtherPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Others.xlsx", "Others");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_others.insertOthers(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Others Ad as a Pro ad")
    public void verifyInsertProOtherPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Others.xlsx", "Others");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_others.insertOthers(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
