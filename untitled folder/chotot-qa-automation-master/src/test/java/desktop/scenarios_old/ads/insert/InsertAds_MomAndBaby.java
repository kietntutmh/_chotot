package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_MomAndBaby;

import java.util.List;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;

public class InsertAds_MomAndBaby extends BaseTest {
    ManageAds manageAds;
    CM_Ads_MomAndBaby cm_ads_momAndBaby;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads_momAndBaby = new CM_Ads_MomAndBaby();
        cm_ads = new CM_Ads();
        pos = new POS();
        manageAds = new ManageAds();
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - MomAndBaby, Verify insert Requisite Ad on Private, Tri Nguyen, MABU")
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
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
