package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Industrial;
import projects.functions.chotot._common.ads.insert.CM_Ads_OfficeEquipments;

import java.util.List;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;

public class InsertAds_Industrial extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Industrial cm_industrial;
    CM_Ads_OfficeEquipments cm_OfficeEquipments;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_industrial = new CM_Ads_Industrial();
        cm_OfficeEquipments = new CM_Ads_OfficeEquipments();
        pos = new POS();
        manageAds = new ManageAds();
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Industrial, Verify insert Requisite Ad on Private, Vu Hoang, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - Industrial, Verify insert SpecializedItem Ad on Private, Vu Hoang, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - OfficeEquipments, Verify insert ad Office Equipment on Private, TRI NGUYEN, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - OfficeEquipment, Verify insert ad Specialized Item on Private, TRI NGUYEN, MABU")
    public void verifyInsertAdSpecializedItemPrivate() {
        initObjects();
        // Setup test data
        excelDataProvider.getExcelFileSheet(
                "data/excel/ui/ads/Ads_OfficeEquipments.xlsx", "SpecializedItem");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_OfficeEquipments.insertOfficeEquipments(insertData, insertSteps);

        // ====== BUMP ======
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
