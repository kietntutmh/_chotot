package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.configuration.LoginConfig;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Vehicle;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adVehicleExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldListAdsInfo;

public class InsertAds_VEH_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_Vehicle cm_ads_vehicle;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertVehicle cm_api_ads_insertVehicle;

    public void initObjects() {
        cm_ads_vehicle = new CM_Ads_Vehicle();
        cm_ads = new CM_Ads();
        pos = new POS();
        cm_api_ads_insertVehicle = new CM_API_Ads_InsertVehicle();
    }

    private void setupAccount() {
        LoginConfig.setTempAccountAndGetToken(10);
    }

    @Then("All of InsertAd VEH APIs should be working")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }

        initObjects();

        //Check Insert Ad and dashboard
        cm_api_ads_insertVehicle.insertNewAdCar();
        String firstAdID = tempAdID;
        cm_api_ads_insertVehicle.insertNewAdCarForPro();
        cm_api_ads_insertVehicle.insertNewAdMotorbike();
        cm_api_ads_insertVehicle.insertNewAdMotorbikeForPro();
        cm_api_ads_insertVehicle.insertNewAdTrucks();
        cm_api_ads_insertVehicle.insertNewAdTrucksForPro();
        cm_api_ads_insertVehicle.insertNewAdBicycles();
        cm_api_ads_insertVehicle.insertNewAdBicyclesForPro();
        cm_api_ads_insertVehicle.insertNewAdElectric_Vehicle();
        cm_api_ads_insertVehicle.insertNewAdElectric_VehicleForPro();
        cm_api_ads_insertVehicle.insertNewAdOther_Vehicles();
        cm_api_ads_insertVehicle.insertNewAdOther_VehiclesForPro();
        cm_api_ads_insertVehicle.insertNewAdVehicles_Parts();
        cm_api_ads_insertVehicle.insertNewAdVehicles_PartsForPro();
        checkAdOnDashboard(firstAdID);

        setupAccount();
        cm_api_ads_insertVehicle.insertNewAdCarChotot();
        String secondAdID = tempAdID;
        cm_api_ads_insertVehicle.insertNewAdCarShop();
        cm_api_ads_insertVehicle.insertNewAdMotorbikeChotot();
        cm_api_ads_insertVehicle.insertNewAdMotorbikeShop();
        cm_api_ads_insertVehicle.insertNewAdTrucksChotot();
        cm_api_ads_insertVehicle.insertNewAdTrucksShop();
        checkAdOnDashboard(secondAdID);
    }

    @When("I post a new Car Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Car Ad as a Pro ad")
    public void verifyInsertAdsCarPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert data
        cm_ads_vehicle.insertCar(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Car Ad as a Chotot ad")
    public void verifyInsertAdsCarShopToChoTot() {
        // Setup account
        //setupAccount();

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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Car Ad as a Shop ad")
    public void verifyInsertAdsCarToShop() {
        //setupAccount();

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
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Motorbike Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Motorbike Ad as a Pro ad")
    public void verifyInsertAdsMotorbikePro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertMotorbike(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Motorbike Ad as a Chotot ad")
    public void verifyInsertAdsMotorbikeShopToChoTot() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate objects
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertMotorbike(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Motorbike Ad as a Shop ad")
    public void verifyInsertAdsMotorbikeToShop() {
        //setupAccount();

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
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Trucks Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Trucks Ad as a Pro ad")
    public void verifyInsertAdsTrucksPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertTrucks(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Trucks Ad as a Chotot ad")
    public void verifyInsertAdsTrucksShopToChoTot() {
        //setupAccount();

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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Trucks Ad as a Shop ad")
    public void verifyInsertAdsTrucksToShop() {
        //setupAccount();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");
        List<String> insertData = excelDataProvider.getRowData(4);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        List<String> adInfoDashboard = cm_ads.goToInsertAdsShop();

        // Insert Ad
        cm_ads_vehicle.insertTrucks(insertData);

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Electric Vehicles Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Electric Vehicles Ad as a Pro ad")
    public void verifyInsertAdsElectric_VehiclePro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertElectric_Vehicle(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Electric Vehicles Ad as a Chotot ad")
    public void verifyInsertAdsElectric_VehicleShopToChoTot() {
        //setupAccount();

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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Electric Vehicles Ad as a Shop ad")
    public void verifyInsertAdsElectric_VehicleToShop() {
        //setupAccount();

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
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Bicycles Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Bicycles Ad as a Pro ad")
    public void verifyInsertAdsBicyclesPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertBicycles(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Bicycles Ad as a Chotot ad")
    public void verifyInsertAdsBicyclesShopToChoTot() {
        //setupAccount();

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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Bicycles Ad as a Shop ad")
    public void verifyInsertAdsBicyclesToShop() {
        //setupAccount();

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
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Other Vehicles Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Other Vehicles Ad as a Pro ad")
    public void verifyInsertAdsOther_VehiclesPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertOther_Vehicles(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Other Vehicles Ad as a Chotot ad")
    public void verifyInsertAdsOther_VehiclesShopToChoTot() {
        //setupAccount();

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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Other Vehicles Ad as a Shop ad")
    public void verifyInsertAdsOther_VehiclesToShop() {
        //setupAccount();

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
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Vehicle Parts Ad as a private ad")
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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Vehicle Parts Ad as a Pro ad")
    public void verifyInsertAdsVehicle_PartsPro() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicle_Parts");
        List<String> insertData = excelDataProvider.getRowData(5);

        // Initiate object instances
        initObjects();

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_vehicle.insertVehicle_Parts(insertData);

        // Check POS page displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Vehicle Parts Ad as a Chotot ad")
    public void verifyInsertAdsVehicle_PartsShopToChoTot() {
        //setupAccount();

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
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldListAdsInfo(adInfoDashboard);
    }

    @When("I post a new Vehicle Parts Ad as a Shop ad")
    public void verifyInsertAdsVehicle_PartsToShop() {
        //setupAccount();

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
        setOldListAdsInfo(adInfoDashboard);
    }
}
