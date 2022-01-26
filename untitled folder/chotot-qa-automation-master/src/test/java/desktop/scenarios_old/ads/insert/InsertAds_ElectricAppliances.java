package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_ElectricAppliances;

import java.util.List;

import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static desktop.configuration.DataConfig.adElectricAppliancesExcelFile;

public class InsertAds_ElectricAppliances extends BaseTest {
    ManageAds manageAds;
    CM_Ads_ElectricAppliances cm_ads_electricAppliances;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_electricAppliances = new CM_Ads_ElectricAppliances();
        pos = new POS();
        manageAds = new ManageAds();
    }

    public void verifyInsertAdCoolerPersonal() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Cooler");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(0);
        List<String> insertData = excelDataProvider.getRowData(1);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_electricAppliances.insertCooler(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdRefrigeratorPersonal() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Refrigerator");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_electricAppliances.insertRefrigerator(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
    public void verifyInsertAdWashing_MachinePersonal() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Washing machine");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(0);
        List<String> insertData = excelDataProvider.getRowData(1);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_electricAppliances.insertWashing_Machine(insertData, insertSteps);

        // Verify POS displayed
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
