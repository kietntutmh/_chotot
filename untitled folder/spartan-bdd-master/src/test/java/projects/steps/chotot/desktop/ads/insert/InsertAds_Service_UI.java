package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Service;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertServices;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adServiceExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Service_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_Service cm_service;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertServices cm_api_ads_insertServices;

    public void initObjects() {
        cm_service = new CM_Ads_Service();
        cm_ads = new CM_Ads();
        pos = new POS();
        cm_api_ads_insertServices = new CM_API_Ads_InsertServices();
    }

    @When("All of InsertAd Service APIs should be working")
    public void verifyAllAPIs() {
        initObjects();
        cm_api_ads_insertServices.insertNewServiceAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertServices.insertNewTravelAd();
        checkAdOnDashboard(tempAdID);
    }

    @When("I post a new Service Ad as a private ad")
    public void verifyInsertServicePrivate() {
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Travel Ad as a private ad")
    public void verifyInsertTravelPrivate() {
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Service Ad as a Pro ad")
    public void verifyInsertProServicePrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "service");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_service.insertService(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Travel Ad as a Pro ad")
    public void verifyInsertProTravelPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "travel");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_service.insertTravel(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
