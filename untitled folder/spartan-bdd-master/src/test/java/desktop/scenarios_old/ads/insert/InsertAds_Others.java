package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Others;

import java.util.List;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;

public class InsertAds_Others extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Others cm_others;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_others = new CM_Ads_Others();
        cm_ads = new CM_Ads();
        pos = new POS();
        manageAds = new ManageAds();
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Others, Verify insert Other Ad on Private, Vu Hoang, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
