package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Fashion;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;

public class InsertAds_Fashion extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Fashion cm_fashion;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertFashion cm_api_ads_insertFashion;
    private int order = 0;

    public void initObjects() {
        cm_fashion = new CM_Ads_Fashion();
        cm_ads = new CM_Ads();
        pos = new POS();
        cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();

        if (order == 0) // Not yet initialize
            order = getRandomOrder(1, 7);
    }


    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "FLASHAD  >> Insert Ad - Fashion, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();

        cm_api_ads_insertFashion.insertNewAdClothes();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertFashion.insertNewAdWatch();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertFashion.insertNewAdHandbag();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertFashion.insertNewAdShoes();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertFashion.insertNewAdAccessories();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertFashion.insertNewAdPerfume();
        checkAdOnDashboard(tempAdID);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Fashion, Verify insert Clothes Ad on Private, Vu Hoang, MABU")
    public void verifyInsertClothesPrivate() {
        checkActiveOrder(order, 1); // To random execute TC

        // Initiate object instances
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Fashion.xlsx", "Clothes");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_fashion.insertClothes(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Fashion, Verify insert Watch Ad on Private, Vu Hoang, MABU")
    public void verifyInsertWatchPrivate() {
        checkActiveOrder(order, 2); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Fashion.xlsx", "Watch");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_fashion.insertWatch(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Fashion, Verify insert Shoe Ad on Private, Vu Hoang, MABU")
    public void verifyInsertShoePrivate() {
        checkActiveOrder(order, 3); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Fashion.xlsx", "Shoe");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_fashion.insertShoe(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Fashion, Verify insert Handbag Ad on Private, Vu Hoang, MABU")
    public void verifyInsertHandbagPrivate() {
        checkActiveOrder(order, 4); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Fashion.xlsx", "Handbag");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_fashion.insertHandbag(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Fashion, Verify insert Perfume Ad on Private, Vu Hoang, MABU")
    public void verifyInsertPerfumePrivate() {
        checkActiveOrder(order, 5); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Fashion.xlsx", "Perfume");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_fashion.insertPerfume(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Fashion, Verify insert Accessories Ad on Private, Vu Hoang, MABU")
    public void verifyInsertAccessoriesPrivate() {
        checkActiveOrder(order, 6); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Fashion.xlsx", "Accessories");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_fashion.insertAccessories(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
