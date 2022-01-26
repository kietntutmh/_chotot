package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.configuration.LoginConfig;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_ELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adELTExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldListAdsInfo;

public class InsertAds_ELT_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads cm_ads;
    CM_Ads_ELT cm_ads_elt;
    POS pos;
    CM_API_Ads_InsertELT cm_api_ads_insertVehicle;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_elt = new CM_Ads_ELT();
        pos = new POS();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertELT();
    }

    private void setupAccount() {
        LoginConfig.setTempAccountAndGetToken(9);
    }

    @Then("All of InsertAd ETL APIs should be working")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertVehicle.insertNewAdLaptop();
        String firstAdID = tempAdID;
        cm_api_ads_insertVehicle.insertNewAdPhone();
        cm_api_ads_insertVehicle.insertNewAdTablet();
        cm_api_ads_insertVehicle.insertNewAdPC();
        cm_api_ads_insertVehicle.insertNewAdCamera();
        cm_api_ads_insertVehicle.insertNewAdSound();
        cm_api_ads_insertVehicle.insertNewAdWearable();
        cm_api_ads_insertVehicle.insertNewAdAccessories();
        cm_api_ads_insertVehicle.insertNewAdPC_Component();
        //checkAdOnDashboard(firstAdID);

        setupAccount();
        cm_api_ads_insertVehicle.inserAdLaptopChotot();
        String secondAdID = tempAdID;
        cm_api_ads_insertVehicle.insertAdTabletChotot();
        cm_api_ads_insertVehicle.insertAdPhoneChotot();
        cm_api_ads_insertVehicle.insertAdCameraChotot();
        cm_api_ads_insertVehicle.insertAdSoundChotot();
        cm_api_ads_insertVehicle.insertAdAccessoriesChotot();
        cm_api_ads_insertVehicle.insertAdPC_ComponentChotot();
        cm_api_ads_insertVehicle.insertAdWearableChotot();
        cm_api_ads_insertVehicle.insertAdPCChotot();
        //checkAdOnDashboard(secondAdID);
    }

    @When("I post a new Cellphone Ad as a private ad")
    public void verifyInsertAdsCellphonePrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Cellphone");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertCellphone(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Cellphone Ad as a Pro ad")
    public void verifyInsertAdsCellphonePro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Cellphone");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertCellphonePRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Cellphone Ad as a Shop ad")
    public void verifyInsertAdsCellphoneToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Cellphone_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertCellphonePRO(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Cellphone Ad as a Chotot ad")
    public void verifyInsertAdsCellphoneShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Cellphone_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertCellphonePRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Tablet Ad as a private ad")
    public void verifyInsertAdsTabletPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertTablet(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Tablet Ad as a Pro ad")
    public void verifyInsertAdsTabletPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertTabletPRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Tablet Ad as a Shop ad")
    public void verifyInsertAdsTabletToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertTabletPRO(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Tablet Ad as a Chotot ad")
    public void verifyInsertAdsTabletShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertTabletPRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Laptop Ad as a private ad")
    public void verifyInsertAdsLaptopPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertLaptop(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Laptop Ad as a Pro ad")
    public void verifyInsertAdsLaptopPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertLaptopPRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Laptop Ad as a Shop ad")
    public void verifyInsertAdsLaptopToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertLaptopPRO(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Laptop Ad as a Chotot ad")
    public void verifyInsertAdsLaptopShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertLaptopPRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Desktop Ad as a private ad")
    public void verifyInsertAdsDesktopPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Desktop");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertDesktop(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Desktop Ad as a Pro ad")
    public void verifyInsertAdsDesktopPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Desktop");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertDesktopPRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Desktop Ad as a Shop ad")
    public void verifyInsertAdsDesktopToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Desktop_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertDesktopPRO(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Desktop Ad as a Chotot ad")
    public void verifyInsertAdsDesktopShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Desktop_PRO");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertDesktopPRO(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Camera Ad as a private ad")
    public void verifyInsertAdsCameraPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertCamera(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Camera Ad as a Pro ad")
    public void verifyInsertAdsCameraPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertCamera(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Camera Ad as a Shop ad")
    public void verifyInsertAdsCameraToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertCamera(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Camera Ad as a Chotot ad")
    public void verifyInsertAdsCameraShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertCamera(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I pose a new TV Sound Ad as a Private ad")
    public void verifyInsertAdsTV_SoundPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "TV_Sound");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertTV_Sound(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

  @When("I post a new TV Sound Ad as a Pro ad")
  public void verifyInsertAdsTV_SoundPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "TV_Sound");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertTV_Sound(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new TV Sound Ad as a Shop ad")
    public void verifyInsertAdsTV_SoundToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "TV_Sound");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertTV_Sound(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new TV Sound Ad as a Chotot ad")
    public void verifyInsertAdsTV_SoundShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "TV_Sound");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertTV_Sound(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Smart Watch Ad as a private ad")
    public void verifyInsertAdsSmart_WatchPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Smart_Watch");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertSmart_Watch(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Smart Watch Ad as a Pro ad")
    public void verifyInsertAdsSmart_WatchPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Smart_Watch");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertSmart_Watch(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Smart Watch Ad as a Shop ad")
    public void verifyInsertAdsSmart_WatchToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Smart_Watch");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertSmart_Watch(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Smart Watch Ad as a Chotot ad")
    public void verifyInsertAdsSmart_WatchShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Smart_Watch");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertSmart_Watch(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Accessories Monitor Ad as a private ad")
    public void verifyInsertAdsAccessories_MonitorPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_Monitor");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertAccessories_Monitor(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Accessories Monitor Ad as a Pro ad")
    public void verifyInsertAdsAccessories_MonitorPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_Monitor");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertAccessories_Monitor(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Accessories Monitor Ad as a Shop ad")
    public void verifyInsertAdsAccessories_MonitorToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_Monitor");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertAccessories_Monitor(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Accessories Monitor Ad as a Chotot ad")
    public void verifyInsertAdsAccessories_MonitorShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_Monitor");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertAccessories_Monitor(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Accessories Ram Ad as a private ad")
    public void verifyInsertAdsAccessories_RAMPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_RAM");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertAccessories_RAM(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Accessories Ram Ad as a Pro ad")
    public void verifyInsertAdsAccessories_RAMPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_RAM");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_elt.insertAccessories_RAM(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Accessories Ram Ad as a Shop ad")
    public void verifyInsertAdsAccessories_RAMToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_RAM");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertAccessories_RAM(insertData);

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Accessories Ram Ad as a Chotot ad")
    public void verifyInsertAdsAccessories_RAMShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories_RAM");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_elt.insertAccessories_RAM(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }     

        // Check Dashboard after insert ad
        setOldListAdsInfo(adInfoDashboard);
    }
}
