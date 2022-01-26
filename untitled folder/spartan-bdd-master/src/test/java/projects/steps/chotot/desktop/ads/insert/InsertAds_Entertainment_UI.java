package projects.steps.chotot.desktop.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import com.vn.chotot.exception.FailureHandling;
import desktop.pages.Dashboard.POS;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Entertainment;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertEntertainment;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.TEST_RUN_TYPE;
import static desktop.configuration.BaseConfig.checkPOSDisplayed;
import static projects.steps.chotot.desktop.ChototVerifySteps.setOldAdsInfo;

public class InsertAds_Entertainment_UI {
    ExcelProvider excelDataProvider = new ExcelProvider();
    CM_Ads_Entertainment cm_entertainment;
    CM_Ads cm_ads;
    POS pos;
    CM_API_Ads_InsertEntertainment cm_api_ads_insertEntertainment;

    public void initObjects() {
        cm_entertainment = new CM_Ads_Entertainment();
        cm_ads = new CM_Ads();
        pos = new POS();
        cm_api_ads_insertEntertainment = new CM_API_Ads_InsertEntertainment();
    }

    @Then("All of InsertAd Entertainment APIs should be working")
    public void verifyAllAPIs() {
        if (TEST_RUN_TYPE.equalsIgnoreCase("nightly")) {
            return;
        }
        initObjects();
        cm_api_ads_insertEntertainment.insertNewAdInstrument();
        String adID = tempAdID;
        cm_api_ads_insertEntertainment.insertNewAdBook();
        cm_api_ads_insertEntertainment.insertNewAdCollectibles();
        cm_api_ads_insertEntertainment.insertNewAdGaming();
        cm_api_ads_insertEntertainment.insertNewAdHobby();
        checkAdOnDashboard(adID);
    }

    @When("I post a new Instrument Ad as a private ad")
    public void verifyInsertInstrumentPrivate() {
        // Initialize objects
        initObjects();

        // Setup test data for insert ad
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Instrument");
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertInstrument(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Book Ad as a private ad")
    public void verifyInsertBookPrivate() {
        initObjects();

        // Setup data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Book");
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertBook(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Sport Ad as a private ad")
    public void verifyInsertSportPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Sport");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertSport(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Collectibles Ad as a private ad")
    public void verifyInsertCollectiblesPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Collectibles");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertCollectibles(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Gaming Ad as a private ad")
    public void verifyInsertGamingPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Gaming");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertGaming(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Habit Ad as a private ad")
    public void verifyInsertHabbitPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Habbit");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(2);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertHabbit(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Instrument Ad as a Pro ad")
    public void verifyInsertProAdInstrumentPrivate() {
        // Initialize objects
        initObjects();

        // Setup test data for insert ad
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Instrument");
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertInstrument(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Book Ad as a Pro ad")
    public void verifyInsertProAdBookPrivate() {
        initObjects();

        // Setup data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Book");
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Go to insert ads page
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertBook(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Sport Ad as a Pro ad")
    public void verifyInsertProAdSportPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Sport");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertSport(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Collectibles Ad as a Pro ad")
    public void verifyInsertProAdCollectiblesPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Collectibles");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertCollectibles(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Gaming Ad as a Pro ad")
    public void verifyInsertProAdGamingPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Gaming");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertGaming(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }

    @When("I post a new Habit Ad as a Pro ad")
    public void verifyInsertProAdHabbitPrivate() {
        // Setup test data
        excelDataProvider.getExcelFileSheet("data/excel/ui/ads/Ads_Entertainment.xlsx", "Habbit");

        // Get insert ads data
        List<String> insertSteps = excelDataProvider.getRowData(1);
        List<String> insertData = excelDataProvider.getRowData(3);

        // Initiate object instances
        initObjects();
        String oldAdsInfo = cm_ads.goToInsertAds();

        // Insert Ad
        cm_entertainment.insertHabbit(insertData, insertSteps);

        // Verify POS displayed
        if (checkPOSDisplayed()) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        setOldAdsInfo(oldAdsInfo);
    }
}
