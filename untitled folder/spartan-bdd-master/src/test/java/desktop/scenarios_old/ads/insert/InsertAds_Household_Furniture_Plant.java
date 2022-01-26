package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Household_Furniture_Plant;

import java.util.List;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static desktop.configuration.DataConfig.adHousehold_Furniture_PlantExcelFile;

public class InsertAds_Household_Furniture_Plant extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Household_Furniture_Plant cm_ads_household_furniture_plant;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_household_furniture_plant = new CM_Ads_Household_Furniture_Plant();
        pos = new POS();
        manageAds = new ManageAds();
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Kitchen_Appliance Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Kitchen_Utensil_Dinnerware Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Bed_Bedding Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Bathware Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Fan Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Lighting Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Household_Table_Chair Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Household_Drawer_Shelf Ad on Private(Not have bump), Tuan Chieu, MABU")
    public void verifyInsertHousehold_Drawer_ShelfPrivate() {
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Ornamental_Plant_Decoration Ad on Private(Not have bump), Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Household_Furniture_Plant, Verify insert Other_Household_Items Ad on Private(Not have bump), Tuan Chieu, MABU")
    public void verifyInsertOther_Household_ItemsPrivate() {
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
