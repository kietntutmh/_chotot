package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Household_Furniture_Plant;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertHousehold;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adHousehold_Furniture_PlantExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Household_Furniture_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_Household_Furniture_Plant cm_ads_household_furniture_plant;
    CM_API_Ads_InsertHousehold cm_api_ads_Insert_household_;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_household_furniture_plant = new CM_Ads_Household_Furniture_Plant();
        cm_api_ads_Insert_household_ = new CM_API_Ads_InsertHousehold();
        pos = new POS();
    }

    @Then("All of InsertAd Household APIs should be working")
    public void verifyAllAPIs() {
        initObjects();

        cm_api_ads_Insert_household_.insertNewAdFan();
        String adID = tempAdID;
        cm_api_ads_Insert_household_.insertNewAdBathware();
        cm_api_ads_Insert_household_.insertNewAdHousehold_Bed();
        cm_api_ads_Insert_household_.insertNewAdHousehold_Drawer_Shelf();
        cm_api_ads_Insert_household_.insertNewAdHousehold_Table_Chair();
        cm_api_ads_Insert_household_.insertNewAdKitchen_Appliance();
        cm_api_ads_Insert_household_.insertNewAdHousehold_Dinnerware();
        cm_api_ads_Insert_household_.insertNewAdLighting();
        cm_api_ads_Insert_household_.insertNewAdOrnamental_Plant_Decoration();
        cm_api_ads_Insert_household_.insertNewAdOther_Household_Items();
        checkAdOnDashboard(tempAdID);
    }

    @Then("I post a new Kitchen Appliance Ad as a private ad")
    public void verifyInsertKitchen_AppliancePrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Appliance");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdKitchen_Appliance(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Kitchen Utensil Dinnerware Ad as a private ad")
    public void verifyInsertKitchen_Utensil_DinnerwarePrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Kitchen_Utensil_Dinnerware");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdKitchen_Utensil_Dinnerware(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Bed Bedding Ad as a private ad")
    public void verifyInsertBed_BeddingPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bed_Bedding");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdBed_Bedding(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Bathware Ad as a private ad")
    public void verifyInsertBathwarePrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bathware");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdBathware(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Fan Ad as a private ad")
    public void verifyInsertFanPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Fan");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdFan(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Lighting Ad as a private ad")
    public void verifyInsertLightingPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Lighting");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdLighting(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Household Table Chair Ad as a private ad")
    public void verifyInsertHousehold_Table_ChairPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Table_Chair");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdTable_Chair(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Household Drawer Shelf Ad as a private ad")
    public void verifyInsertHousehold_Drawer_ShelfPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Drawer_Shelf");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdDrawer_Shelf(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Ornamental Plant Decoration Ad as a private ad")
    public void verifyInsertOrnamental_Plant_DecorationPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Ornamental_Plant_Decoration");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdOrnamental_Plant_Decoration(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Household Items Ad as a private ad")
    public void verifyInsertOther_Household_ItemsPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Other_Household_Items");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdOther_Household_Item(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Kitchen Appliance Ad as a Pro ad")
    public void verifyInsertProKitchen_AppliancePrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Appliance");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdKitchen_Appliance(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Kitchen Utensil Dinnerware Ad as a Pro ad")
    public void verifyInsertProKitchen_Utensil_DinnerwarePrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Kitchen_Utensil_Dinnerware");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdKitchen_Utensil_Dinnerware(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Bed Bedding Ad as a Pro ad")
    public void verifyInsertProBed_BeddingPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bed_Bedding");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdBed_Bedding(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Bathware Ad as a Pro ad")
    public void verifyInsertProBathwarePrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bathware");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdBathware(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Fan Ad as a Pro ad")
    public void verifyInsertFanPro() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Fan");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdFan(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Lighting Ad as a Pro ad")
    public void verifyInsertProLightingPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Lighting");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdLighting(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Household Table Chair Ad as a Pro ad")
    public void verifyInsertProHousehold_Table_ChairPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Table_Chair");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdTable_Chair(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Household Drawer Shelf Ad as a Pro ad")
    public void verifyInsertProHousehold_Drawer_ShelfPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Drawer_Shelf");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdDrawer_Shelf(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Ornamental Plant Decoration Ad as a Pro ad")
    public void verifyInsertProOrnamental_Plant_DecorationPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Ornamental_Plant_Decoration");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdOrnamental_Plant_Decoration(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @Then("I post a new Household Items Ad as a Pro ad")
    public void verifyInsertProOther_Household_ItemsPrivate() {
        initObjects();

        // Setup test data
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Other_Household_Items");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_household_furniture_plant.insertAdOther_Household_Item(insertData);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
