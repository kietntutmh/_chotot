package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_ElectricAppliances;

import java.util.List;

import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adElectricAppliancesExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_ElectricAppliances_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_ElectricAppliances cm_ads_electricAppliances;
    CM_Ads cm_ads;
    POS pos;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_electricAppliances = new CM_Ads_ElectricAppliances();
        pos = new POS();
    }

    @When("I post a new Cooler Ad as a private ad")
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
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Refrigerator Ad as a private ad")
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
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Washing_Machine Ad as a private ad")
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
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Cooler Ad as a Pro ad")
    public void verifyInsertProAdCoolerPersonal() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Cooler");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(0);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_electricAppliances.insertCooler(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Refrigerator Ad as a Pro ad")
    public void verifyInsertProAdRefrigeratorPersonal() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Refrigerator");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_electricAppliances.insertRefrigerator(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Washing_Machine Ad as a Pro ad")
    public void verifyInsertProAdWashing_MachinePersonal() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Washing machine");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(0);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_electricAppliances.insertWashing_Machine(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
