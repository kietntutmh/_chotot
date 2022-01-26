package desktop.scenarios_old.ads.insert;

import com.vn.chotot.exception.FailureHandling;
import desktop.base.BaseTest;
import desktop.pages.Dashboard.ManageAds;
import desktop.pages.Dashboard.POS;
import org.testng.annotations.Test;
import projects.functions.chotot._common.ads.CM_Ads;
import projects.functions.chotot._common.ads.insert.CM_Ads_Pets;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPets;

import java.util.List;

import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.DashboardAds.checkAdOnDashboard;
import static com.vn.chotot.configuration.Constant.IS_CHECKING_POS;
import static desktop.configuration.DataConfig.adPetExcelFile;

public class InsertAds_Pets extends BaseTest {
    ManageAds manageAds;
    CM_Ads cm_ads;
    CM_Ads_Pets cm_ads_pets;
    POS pos;
    CM_API_Ads_InsertPets cm_api_ads_insertPets;
    private int order = 0;

    public void initObjects() {
        cm_ads_pets = new CM_Ads_Pets();
        cm_ads = new CM_Ads();
        pos = new POS();
        manageAds = new ManageAds();
        cm_api_ads_insertPets = new CM_API_Ads_InsertPets();
        if (order == 0) // Not yet initialize
            order = getRandomOrder(1, 6);
    }

    @Test(
            priority = -1,
            groups = {"apiCheckUAT", "uat"},
            description = "FLASHAD  >> Insert Ad - Pets, Verify all APIs work, Vu Hoang, SRE")
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

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Pets , Verify insert Rooster Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsRoosterPrivate() {
        checkActiveOrder(order, 1); // To random execute TC
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Pets, Verify insert Dog Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsDogPrivate() {
        checkActiveOrder(order, 2); // To random execute TC
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Pets, Verify insert Bird Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsBirdPrivate() {
        checkActiveOrder(order, 3); // To random execute TC
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Pets, Verify insert Cat Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsCatPrivate() {
        checkActiveOrder(order, 4); // To random execute TC
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Pets, Verify insert Other_Pets Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsOther_PetsPrivate() {
        checkActiveOrder(order, 5); // To random execute TC
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }

    @Test(
            groups = {"uat"},
            description =
                    "FLASHAD  >> Insert Ad - Pets, Verify insert Food_Service Ad on Private, Tuan Chieu, MABU")
    public void verifyInsertAdsFood_ServicePrivate() {
        checkActiveOrder(order, 6); // To random execute TC
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
        if (IS_CHECKING_POS) {
            pos.verifyPosItems(FailureHandling.STOP_ON_FAILURE);
        }

        // Verify new add inserted successfully
        manageAds.verifyNewAdDisplayedOnPrivateDashboard(oldAdsInfo);
    }
}
