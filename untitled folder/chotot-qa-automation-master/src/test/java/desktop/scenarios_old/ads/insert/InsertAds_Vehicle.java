package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.configuration.LoginConfig;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Vehicle;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.DataConfig.adVehicleExcelFile;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class InsertAds_Vehicle extends BaseTest {
    ManageAds manageAds;
    CM_Ads_Vehicle cm_ads_vehicle;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle;

    public void initObjects() {
        cm_ads_vehicle = new CM_Ads_Vehicle();
        cm_ads = new CM_Ads();
        manageAds = new ManageAds();
        pos = new POS();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
    }

    private void setupAccount() {
        LoginConfig.setTempAccountAndGetToken(10);
    }

    @Test(priority = -1, groups = {"apiCheckUAT", "uat"}, description = "FLASHAD  >> Insert Ad - Vehicle, Verify all APIs work, Vu Hoang, SRE")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();

        //Check Insert Ad and dashboard
        cm_api_ads_insertVehicle.insertNewAdCar();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdCarForPro();
        checkAdOnDashboard(tempAdID);

        setupAccount();
        cm_api_ads_insertVehicle.insertNewAdCarChotot();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertVehicle.insertNewAdCarShop();
        checkAdOnDashboard(tempAdID);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Car Ad on Private, Quoc Tran, MABU")
    public void verifyInsertAdsCarPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert data
        cm_ads_vehicle.insertCar(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Car Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsCarShopToChoTot() {
        // Setup account
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertCar(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(groups = {"uat", "dev"}, dependsOnGroups = "apiCheckUAT"
            , description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Car Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsCarToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertCar(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad on Private, Vu Hoang, MABU")
    public void verifyInsertAdsMotorbikePrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertMotorbike(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsMotorbikeShopToChoTot() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertMotorbike(insertData);

        //Pay the order
        paymentOrderWithDongTot();

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(groups = {"uat", "dev"}, dependsOnGroups = "apiCheckUAT"
            , description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsMotorbikeToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertMotorbike(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad on Private, Vu Hoang, MABU")
    public void verifyInsertAdsTrucksPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertTrucks(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsTrucksShopToChoTot() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertTrucks(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Topup Dongtot
        topupDongTotWithMomo(tempUserPhone);

        //Pay the order

        paymentOrderWithDongTot();

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsTrucksToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        cm_ads_vehicle = new CM_Ads_Vehicle();
        cm_ads = new CM_Ads();
        manageAds = new ManageAds();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertTrucks(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Electric_Vehicle Ad on Private, Vu Hoang, MABU")
    public void verifyInsertAdsElectric_VehiclePrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertElectric_Vehicle(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsElectric_VehicleShopToChoTot() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertElectric_Vehicle(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsElectric_VehicleToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initialize objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertElectric_Vehicle(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Bicycles Ad on Private, Vu Hoang, MABU")
    public void verifyInsertAdsBicyclesPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertBicycles(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsBicyclesShopToChoTot() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initialize objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertBicycles(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsBicyclesToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initialize objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertBicycles(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Other_Vehicles Ad on Private, Vu Hoang, MABU")
    public void verifyInsertAdsOther_VehiclesPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertOther_Vehicles(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsOther_VehiclesShopToChoTot() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initialize objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertOther_Vehicles(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsOther_VehiclesToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initialize objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertOther_Vehicles(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Vehicle_Parts Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsVehicle_PartsPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicle_Parts");
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertVehicle_Parts(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Trucks Ad by Shop to Cho Tot, Tuan Chieu, MABU")
    public void verifyInsertAdsVehicle_PartsShopToChoTot() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicle_Parts");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initialize objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertVehicle_Parts(insertData);

        // Check POS page displayed
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }

    @Test(description = "FLASHAD  >> Insert Ad - Vehicle, Verify insert Motorbike Ad to Shop, Tuan Chieu, MABU")
    public void verifyInsertAdsVehicle_PartsToShop() {
        setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicle_Parts");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Go to insert ads page
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertVehicle_Parts(insertData);

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnShopDashboard(adInfoDashboard);
    }
}
