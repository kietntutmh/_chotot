package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Pets;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPets;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static desktop.configuration.DataConfig.adPetExcelFile;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Pets_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads cm_ads;
    CM_Ads_Pets cm_ads_pets;
    POS pos;
    CM_API_Ads_InsertPets cm_api_ads_insertPets;

    public void initObjects() {
        cm_ads_pets = new CM_Ads_Pets();
        cm_ads = new CM_Ads();
        pos = new POS();
        cm_api_ads_insertPets = new CM_API_Ads_InsertPets();
    }

    @When("All of InsertAd Pet APIs should be working")
    public void verifyAllAPIs() {
        initObjects();
        cm_api_ads_insertPets.insertNewChickenAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPets.insertNewBirdAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPets.insertNewDogAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPets.insertNewCatAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPets.insertNewAccessoriesAd();
        checkAdOnDashboard(tempAdID);
        cm_api_ads_insertPets.insertNewOtherAd();
        checkAdOnDashboard(tempAdID);
    }

    @When("I post a new Rooster Ad as a private ad")
    public void verifyInsertAdsRoosterPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Rooster");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertRooster(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Dog Ad as a private ad")
    public void verifyInsertAdsDogPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertDog(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Bird Ad as a private ad")
    public void verifyInsertAdsBirdPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertBird(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Cat Ad as a private ad")
    public void verifyInsertAdsCatPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertCat(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Other_Pets Ad as a private ad")
    public void verifyInsertAdsOther_PetsPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Other_Pets");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertOther_Pets(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Food_Service Ad as a private ad")
    public void verifyInsertAdsFood_ServicePrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Food_Service");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertFood_Service(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Rooster Ad as a Pro ad")
    public void verifyInsertProAdsRoosterPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Rooster");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertRooster(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Dog Ad as a Pro ad")
    public void verifyInsertProAdsDogPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertDog(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Bird Ad as a Pro ad")
    public void verifyInsertProAdsBirdPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertBird(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Cat Ad as a Pro ad")
    public void verifyInsertProAdsCatPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertCat(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Other_Pets Ad as a Pro ad")
    public void verifyInsertProAdsOther_PetsPrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Other_Pets");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertOther_Pets(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Food_Service Ad as a Pro ad")
    public void verifyInsertProAdsFood_ServicePrivate() {
        // Setup test data
        initObjects();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Food_Service");

        // Get insert ads data
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_ads_pets.insertFood_Service(insertData);

        // ====== BUMP ======
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
