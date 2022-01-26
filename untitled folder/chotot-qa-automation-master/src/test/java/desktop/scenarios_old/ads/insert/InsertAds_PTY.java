package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_PTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class InsertAds_PTY extends BaseTest {
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

    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();
        cm_api_ads_insertPTY.insertNewAdHouse();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdHouseProObligateChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdHouseProObligateShop();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertPTY.insertNewAdApartment();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdApartmentProObligateChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdApartmentProObligateShop();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertPTY.insertNewAdLand();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdLandProObligateChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdLandProObligateShop();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertPTY.insertNewAdOffice();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdOfficeProObligateChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdOfficeProObligateShop();
        checkAdOnDashboard(tempAdID);

        cm_api_ads_insertPTY.insertNewAdRoomForLease();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdRoomForLeaseProObligateChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPTY.insertNewAdRoomForLeaseProObligateShop();
        checkAdOnDashboard(tempAdID);
    }

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertHouseToShop() {
        setupAccount();

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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertHouseShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Pay the order
        paymentOrderWithDongTot();

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertApartmentToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertApartmentShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Pay the order
        paymentOrderWithDongTot();

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertLandToShop() {
        // Setup test data
        setupAccount();

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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertLandShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        // Pay the order
        paymentOrderWithDongTot();

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertOfficeToShop() {
        setupAccount();

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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertOfficeShopToChotot() {
        setupAccount();

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    public void verifyInsertRoomToShop() {
        setupAccount();
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
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    public void verifyInsertRoomShopToChotot() {
        setupAccount();
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }
}
