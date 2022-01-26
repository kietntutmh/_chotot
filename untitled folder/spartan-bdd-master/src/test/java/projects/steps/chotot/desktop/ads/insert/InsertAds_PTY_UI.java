package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.configuration.LoginConfig;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_PTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldListAdsInfo;

public class InsertAds_PTY_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    ManageAds manageAds;
    CM_Ads_PTY cm_pty;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertPTY cm_api_ads_insertPTY;

    public void initObjects() {
        cm_pty = new CM_Ads_PTY();
        cm_ads = new CM_Ads();
        manageAds = new ManageAds();
        pos = new POS();
        cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
    }

    private void setupAccount() {
        LoginConfig.setTempAccountAndGetToken(3);
    }

    @Then("All of InsertAd PTY APIs should be working")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertPTY.insertNewAdHouse();
        String firstAdID = tempAdID;
        cm_api_ads_insertPTY.insertNewAdHouseProObligateChotot();
        cm_api_ads_insertPTY.insertNewAdApartment();
        cm_api_ads_insertPTY.insertNewAdApartmentProObligateChotot();
        cm_api_ads_insertPTY.insertNewAdLand();
        cm_api_ads_insertPTY.insertNewAdLandProObligateChotot();
        cm_api_ads_insertPTY.insertNewAdOffice();
        cm_api_ads_insertPTY.insertNewAdOfficeProObligateChotot();
        cm_api_ads_insertPTY.insertNewAdRoomForLease();
        cm_api_ads_insertPTY.insertNewAdRoomForLeaseProObligateChotot();
        //checkAdOnDashboard(firstAdID);

        setupAccount();
        cm_api_ads_insertPTY.insertNewAdHouseProObligateShop();
        String secondAdID = tempAdID;
        cm_api_ads_insertPTY.insertNewAdApartmentProObligateShop();
        cm_api_ads_insertPTY.insertNewAdLandProObligateShop();
        cm_api_ads_insertPTY.insertNewAdOfficeProObligateShop();
        cm_api_ads_insertPTY.insertNewAdRoomForLeaseProObligateShop();
        //checkAdOnDashboard(secondAdID);
    }

    @When("I post a new House Ad as a private ad")
    public void verifyInsertHousePrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "House");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertHouse(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new House Ad as a Pro ad")
    public void verifyInsertHousePro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "House");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertHouse(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new House Ad as a Shop ad")
    public void verifyInsertHouseToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "House");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertHouse(insertData, insertSteps);

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new House Ad as a Chotot ad")
    public void verifyInsertHouseShopToChotot() {
        //setupAccount();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "House");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertHouse(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Apartment Ad as a private ad")
    public void verifyInsertApartmentPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Apartment");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertApartment(insertData, insertSteps);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Apartment Ad as a Pro ad")
    public void verifyInsertApartmentPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Apartment");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertApartment(insertData, insertSteps);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Apartment Ad as a Shop ad")
    public void verifyInsertApartmentToShop() {
        //setupAccount();
        // Setup test data

        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Apartment");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertApartment(insertData, insertSteps);

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Apartment Ad as a Chotot ad")
    public void verifyInsertApartmentShopToChotot() {
        //setupAccount();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Apartment");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertApartment(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Land Ad as a private ad")
    public void verifyInsertLandPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Land");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertLand(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Land Ad as a Pro ad")
    public void verifyInsertLandPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Land");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertLand(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Land Ad as a Shop ad")
    public void verifyInsertLandToShop() {
        // Setup test data
        //setupAccount();

        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Land");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertLand(insertData, insertSteps);

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Land Ad as a Chotot ad")
    public void verifyInsertLandShopToChotot() {
        //setupAccount();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Land");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertLand(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Office Ad as a private ad")
    public void verifyInsertOfficePrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Office");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertOffice(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Office Ad as a Pro ad")
    public void verifyInsertOfficePro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Office");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertOffice(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Office Ad as a Shop ad")
    public void verifyInsertOfficeToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Office");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertOffice(insertData, insertSteps);

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Office Ad as a Chotot ad")
    public void verifyInsertOfficeShopToChotot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Office");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertOffice(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Room Ad as a private ad")
    public void verifyInsertRoomPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Room");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertRoom(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Room Ad as a Pro ad")
    public void verifyInsertRoomPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Room");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_pty.insertRoom(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Room Ad as a Shop ad")
    public void verifyInsertRoomToShop() {
        //setupAccount();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Room");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertRoom(insertData, insertSteps);

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Room Ad as a Chotot ad")
    public void verifyInsertRoomShopToChotot() {
        //setupAccount();
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_PTY.xlsx", "Room");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_pty.insertRoom(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }
}
