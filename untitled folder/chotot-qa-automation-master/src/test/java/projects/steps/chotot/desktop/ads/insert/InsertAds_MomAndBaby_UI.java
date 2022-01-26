package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_MomAndBaby;

import java.util.List;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_MomAndBaby_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_MomAndBaby cm_ads_momAndBaby;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads_momAndBaby = new CM_Ads_MomAndBaby();
        cm_ads = new CM_Ads();
        pos = new POS();
    }

    @Then("I post a new Mom and Baby Ad as a private ad")
    public void verifyInsertAdsMomAndBabyPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_MomAndBaby.xlsx", "Baby");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_momAndBaby.insertConsumer(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Mom and Baby Ad as a Pro ad")
    public void verifyInsertProAdsMomAndBabyPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_MomAndBaby.xlsx", "Baby");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_momAndBaby.insertConsumer(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
