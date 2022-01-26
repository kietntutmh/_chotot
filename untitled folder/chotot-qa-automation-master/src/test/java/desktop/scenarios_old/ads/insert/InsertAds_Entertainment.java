package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Entertainment;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;

public class InsertAds_Entertainment extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Entertainment cm_entertainment;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment;
    private int order = 0;

    public void initObjects() {
        cm_entertainment = new CM_Ads_Entertainment();
        cm_ads = new CM_Ads();
        manageAds = new ManageAds();
        pos = new POS();
        cm_api_ads_insertEntertainment = new CM_API_Ads_InsertEntertainment();
        if (order == 0) // Not yet initialize
            order = getRandomOrder(1, 6);
    }

    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();

        cm_api_ads_insertEntertainment.insertNewAdInstrument();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertEntertainment.insertNewAdBook();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertEntertainment.insertNewAdCollectibles();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertEntertainment.insertNewAdGaming();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertEntertainment.insertNewAdHobby();
        checkAdOnDashboard(tempAdID);
    }

    public void verifyInsertInstrumentPrivate() {
        checkActiveOrder(order, 1); // To random execute TC
        // Initialize objects
        initObjects();

        // Setup test data for insert ad
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Instrument");
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertInstrument(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertBookPrivate() {
        checkActiveOrder(order, 2); // To random execute TC
        initObjects();

        // Setup data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Book");
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertBook(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertSportPrivate() {
        checkActiveOrder(order, 3); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Sport");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertSport(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertCollectiblesPrivate() {
        checkActiveOrder(order, 4); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Collectibles");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertCollectibles(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertGamingPrivate() {
        checkActiveOrder(order, 5); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Gaming");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertGaming(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertHabbitPrivate() {
        checkActiveOrder(order, 6); // To random execute TC
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Habbit");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertHabbit(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
