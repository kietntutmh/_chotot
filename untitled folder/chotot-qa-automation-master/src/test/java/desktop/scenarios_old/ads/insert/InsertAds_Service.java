package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Service;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertServices;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static desktop.configuration.DataConfig.adServiceExcelFile;

public class InsertAds_Service extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Service cm_service;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertServices cm_api_ads_insertServices;
    private int order = 0;

    public void initObjects() {
        cm_service = new CM_Ads_Service();
        manageAds = new ManageAds();
        cm_ads = new CM_Ads();
        pos = new POS();
        manageAds = new ManageAds();
        cm_api_ads_insertServices = new CM_API_Ads_InsertServices();
        if (order == 0) // Not yet initialize
            order = getRandomOrder(1, 2);
    }

    public void verifyAllAPIs() {
        initObjects();
        cm_api_ads_insertServices.insertNewServiceAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertServices.insertNewTravelAd();
        checkAdOnDashboard(tempAdID);
    }

    public void verifyInsertServicePrivate() {
        checkActiveOrder(order, 1); // To random execute TC
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "service");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_service.insertService(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertTravelPrivate() {
        checkActiveOrder(order, 2); // To random execute TC
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "travel");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_service.insertTravel(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
