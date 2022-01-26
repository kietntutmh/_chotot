package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Industrial;
import projects.functions.chotot._common.ads.insert.CM_Ads_OfficeEquipments;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertIndustrial;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Industrial_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_Industrial cm_industrial;
    CM_API_Ads_InsertIndustrial cm_api_ads_insertIndustrial;
    CM_Ads_OfficeEquipments cm_OfficeEquipments;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_industrial = new CM_Ads_Industrial();
        cm_OfficeEquipments = new CM_Ads_OfficeEquipments();
        cm_api_ads_insertIndustrial = new CM_API_Ads_InsertIndustrial();
        pos = new POS();
    }

    @Then("All of InsertAd Industrial APIs should be working")
    public void verifyAllAPIs() {
        initObjects();

        cm_api_ads_insertIndustrial.insertNewRequisiteAd();
        cm_api_ads_insertIndustrial.insertNewSpecializedItemAd();
        checkAdOnDashboard(tempAdID);
    }

    @When("I post a new Requisite Ad as a private ad")
    public void verifyInsertRequisitePrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Industrial.xlsx", "Requisite");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_industrial.insertRequisite(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Specialized Item Ad as a private ad")
    public void verifyInsertSpecializedItemPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Industrial.xlsx", "SpecializedItem");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_industrial.insertSpecializedItem(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Office Equipment Ad as a private ad")
    public void verifyInsertAdOfficeEquipmentPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/ads/Ads_OfficeEquipments.xlsx", "OfficeEquipment");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_OfficeEquipments.insertOfficeEquipments(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Requisite Ad as a Pro ad")
    public void verifyInsertProRequisitePrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Industrial.xlsx", "Requisite");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_industrial.insertRequisite(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Specialized Item Ad as a Pro ad")
    public void verifyInsertProSpecializedItemPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Industrial.xlsx", "SpecializedItem");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_industrial.insertSpecializedItem(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Office Equipment Ad as a Pro ad")
    public void verifyInsertProAdOfficeEquipmentPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/ads/Ads_OfficeEquipments.xlsx", "OfficeEquipment");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_OfficeEquipments.insertOfficeEquipments(insertData, insertSteps);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
