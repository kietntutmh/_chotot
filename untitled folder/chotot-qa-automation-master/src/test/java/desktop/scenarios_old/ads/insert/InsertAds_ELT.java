package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_ELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.DataConfig.adELTExcelFile;

public class InsertAds_ELT extends BaseTest {
    CM_Ads cm_ads;
    CM_Ads_ELT cm_ads_elt;
    ManageAds manageAds;
    POS pos;
    CM_API_Ads_InsertELT cm_api_ads_insertVehicle;

    public void initObjects() {
        cm_ads = new CM_Ads();
        cm_ads_elt = new CM_Ads_ELT();
        pos = new POS();
        manageAds = new ManageAds();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertELT();
    }

    private void setupAccount() {
        LoginConfig.setTempAccountAndGetToken(9);
    }

    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertVehicle.insertNewAdLaptop();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdPhone();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdTablet();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdPC();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdCamera();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdSound();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdWearable();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdAccessories();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdPC_Component();
        checkAdOnDashboard(tempAdID);

        setupAccount();
        cm_api_ads_insertVehicle.inserAdLaptopChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdTabletChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdPhoneChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdCameraChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdSoundChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdAccessoriesChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdPC_ComponentChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdWearableChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertAdPCChotot();
    }

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsCellphoneToShop() {
        setupAccount();

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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertAdsCellphoneShopToChotot() {
        setupAccount();

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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsTabletToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertAdsTabletShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsLaptopToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertAdsLaptopShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsDesktopToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertAdsDesktopShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsCameraToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertAdsCameraShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertAdsTV_SoundToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert TV_Sound Ad by Shop to Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdsTV_SoundShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Smart_Watch Ad Private, Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Smart_Watch Ad to Shop, Tri Nguyen, MABU")
    public void verifyInsertAdsSmart_WatchToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Smart_Watch Ad by Shop to Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdsSmart_WatchShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Accessories_Monitor Ad Private, Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Accessories_Monitor Ad to Shop, Tri Nguyen, MABU")
    public void verifyInsertAdsAccessories_MonitorToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Accessories_Monitor Ad by Shop to Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdsAccessories_MonitorShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Accessories_RAM Ad Private, Tuan Chieu, MABU")
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Accessories_RAM Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsAccessories_RAMToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(
            groups = {"uat", "dev"},
            description =
                    "FLASHAD  >> Insert Ad - ELT, Verify insert Accessories_RAM Ad by Shop to Chotot, Tuan Chieu, MABU")
    public void verifyInsertAdsAccessories_RAMShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
             pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }     

        // Check Dashboard after insert ad
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }
}
