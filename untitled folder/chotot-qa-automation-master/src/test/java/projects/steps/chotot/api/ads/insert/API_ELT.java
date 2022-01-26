package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditELT;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertELT;

import java.util.List;

import static api.configuration.DataConfig.adELTExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_200k;
import static projects.utilities.Hooks.excelDataProvider;

public class API_ELT {

    @Then("I can post a new Phone Ad using api successfully")
    @When("I post a new Phone Ad using api successfully")
    public void verifyInsertAdPhone() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> phoneDataKeys = excelDataProvider.getRowData(1);
        List<String> phoneDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(phoneDataKeys, phoneDataValues);

        // Post phone data
        insertNewAd(data);
    }

    @Then("I can post a new Phone Pro Ad using api successfully")
    @When("I post a new Phone Pro Ad using api successfully")
    public void verifyInsertProAdPhone() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> phoneDataKeys = excelDataProvider.getRowData(12);
        List<String> phoneDataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(phoneDataKeys, phoneDataValues);

        // Post phone data
        insertNewAd(data);
    }

    @Then("I can post a new Phone Ad Shop using api successfully")
    @When("I post a new Phone Ad Shop using api successfully")
    public void verifyInsertAdPhoneShop() {
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> phoneDataKeys = excelDataProvider.getRowData(5);
        List<String> phoneDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(phoneDataKeys, phoneDataValues);

        // Post phone data
        insertNewAd(data);
    }

    @Then("I can post a new Phone Ad Chotot using api successfully")
    @When("I post a new Phone Ad Chotot using api successfully")
    public void verifyInsertAdPhoneChotot() {
        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post data
        insertNewAd(data);
    }

    @Then("I can post a new Laptop Ad using api successfully")
    @When("I post a new Laptop Ad using api successfully")
    public void verifyInsertAdLaptop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> laptopDataKeys = excelDataProvider.getRowData(1);
        List<String> laptopDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(laptopDataKeys, laptopDataValues);

        // Post ad laptop data
        insertNewAd(data);
    }

    @Then("I can post a new Laptop Pro Ad using api successfully")
    @When("I post a new Laptop Pro Ad using api successfully")
    public void verifyInsertProAdLaptop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> laptopDataKeys = excelDataProvider.getRowData(12);
        List<String> laptopDataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(laptopDataKeys, laptopDataValues);

        // Post ad laptop data
        insertNewAd(data);
    }

    @Then("I can post a new Laptop Ad Shop using api successfully")
    @When("I post a new Laptop Ad Shop using api successfully")
    public void verifyInsertAdLaptopShop() {
        // Topup Dong tot
        topupDongTotWithMomo_200k(tempUserPhone);

        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post data
        insertNewAd(data);
    }

    @Then("I can post a new Laptop Ad Chotot using api successfully")
    @When("I post a new Laptop Ad Chotot using api successfully")
    public void verifyInserAdLaptopChotot() {
        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post data
        insertNewAd(data);
    }

    @Then("I can post a new Tablet Ad using api successfully")
    @When("I post a new Tablet Ad using api successfully")
    public void verifyInsertAdTablet() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Then("I can post a new Tablet Pro Ad using api successfully")
    @When("I post a new Tablet Pro Ad using api successfully")
    public void verifyInsertProAdTablet() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Then("I can post a new Tablet Ad Shop using api successfully")
    @When("I post a new Tablet Ad Shop using api successfully")
    public void verifyInsertAdTabletShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Then("I can post a new Tablet Ad Chotot using api successfully")
    @When("I post a new Tablet Ad Chotot using api successfully")
    public void verifyInsertAdTabletChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Then("I can post a new PC Ad using api successfully")
    @When("I post a new PC Ad using api successfully")
    public void verifyInsertAdPC() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Then("I can post a new PC Pro Ad using api successfully")
    @When("I post a new PC Pro Ad using api successfully")
    public void verifyInsertProAdPC() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Then("I can post a new PC Ad Shop using api successfully")
    @When("I post a new PC Ad Shop using api successfully")
    public void verifyInsertAdPCShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excelh
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Then("I can post a new PC Ad Chotot using api successfully")
    @When("I post a new PC Ad Chotot using api successfully")
    public void verifyInsertAdPCChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excelh
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Then("I can post a new Camera Ad Shop using api successfully")
    @When("I post a new Camera Ad Shop using api successfully")
    public void verifyInsertAdCameraShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Then("I can post a new Camera Pro Ad using api successfully")
    @When("I post a new Camera Pro Ad using api successfully")
    public void verifyInsertProAdCamera() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Then("I can post a new Camera Ad using api successfully")
    @When("I post a new Camera Ad using api successfully")
    public void verifyInsertAdCamera() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Then("I can post a new Camera Ad Chotot using api successfully")
    @When("I post a new Camera Ad Chotot using api successfully")
    public void verifyInsertAdCameraChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Then("I can post a new Sound Pro Ad using api successfully")
    @When("I post a new Sound Pro Ad using api successfully")
    public void verifyInsertProAdSound() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @And("I post a new TV Sound Ad as a Private ad API")
    @Then("I can post a new Sound Ad using api successfully")
    @When("I post a new Sound Ad using api successfully")
    public void verifyInsertAdSound() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @Then("I can post a new Sound Ad Shop using api successfully")
    @When("I post a new Sound Ad Shop using api successfully")
    public void verifyInsertAdSoundShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @Then("I can post a new Sound Ad Chotot using api successfully")
    @When("I post a new Sound Ad Chotot using api successfully")
    public void verifyInsertAdSoundChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @Then("I can post a new Wearable Pro Ad using api successfully")
    @When("I post a new Wearable Pro Ad using api successfully")
    public void verifyInsertProAdWearable() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Then("I can post a new Wearable Ad using api successfully")
    @When("I post a new Wearable Ad using api successfully")
    public void verifyInsertAdWearable() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Then("I can post a new Wearable Ad Chotot using api successfully")
    @When("I post a new Wearable Ad Chotot using api successfully")
    public void verifyInsertAdWearableChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Then("I can post a new Wearable Ad Shop using api successfully")
    @When("I post a new Wearable Ad Shop using api successfully")
    public void verifyInsertAdWearableShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Then("I can post a new Accessories ELT Ad using api successfully")
    @When("I post a new Accessories ELT Ad using api successfully")
    public void verifyInsertAdAccessoriesELT() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Then("I can post a new Accessories ELT Pro Ad using api successfully")
    @When("I post a new Accessories ELT Pro Ad using api successfully")
    public void verifyInsertProAdAccessoriesELT() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Then("I can post a new Accessories ELT Ad Shop using api successfully")
    @When("I post a new Accessories ELT Ad Shop using api successfully")
    public void verifyInsertAdAccessoriesShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Then("I can post a new Accessories ELT Ad Chotot using api successfully")
    @When("I post a new Accessories ELT Ad Chotot using api successfully")
    public void verifyInsertAdAccessoriesChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Then("I can post a new PC_Component Ad using api successfully")
    @When("I post a new PC_Component Ad using api successfully")
    public void verifyInsertAdPC_Component() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }

    @Then("I can post a new PC_Component Pro Ad using api successfully")
    @When("I post a new PC_Component Pro Ad using api successfully")
    public void verifyInsertProAdPC_Component() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }

    @Then("I can post a new PC_Component Ad Shop using api successfully")
    @When("I post a new PC_Component Ad Shop using api successfully")
    public void verifyInsertAdPC_ComponentShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }

    @Then("I can post a new PC_Component Ad Chotot using api successfully")
    @When("I post a new PC_Component Ad Chotot using api successfully")
    public void verifyInsertAdPC_ComponentChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }

    //--------------- INSERT AD with CP--------------------
    CM_API_Ads_InsertELT cm_api_ads_insertELT = new CM_API_Ads_InsertELT();
    CM_API_Ads_EditELT cm_api_ads_editELT = new CM_API_Ads_EditELT();

    @Given("I post a new Phone Ad as a Private ad API")
    @Given("I post a new Second Phone Ad as a Private ad API")
    public void i_post_a_new_Phone_Ad_Private_API(){
        cm_api_ads_insertELT.insertNewAdPhone();
    }

    @Given("I post a new Laptop Ad as a Private ad API")
    @Given("I post a new Second Laptop Ad as a Private ad API")
    public void i_post_a_new_Laptop_Ad_Private_API(){
        cm_api_ads_insertELT.insertNewAdLaptop();
    }

    @Given("I post a new Phone Ad as a Private ad API and be accepted")
    @And("I post a new Second Phone Ad as a Private ad API and be accepted")
    public void i_post_new_phone_ad_Chotot_accept(){
        cm_api_ads_insertELT.insertNewAdPhone("accept");
    }

    @Given("I post a new Laptop Ad as a Private ad API and be accepted")
    @And("I post a new Second Laptop Ad as a Private ad API and be accepted")
    public void i_post_new_laptop_ad_Chotot_accept(){
        cm_api_ads_insertELT.insertNewAdLaptop("accept");
    }

    @Given("I post a new TV Sound Ad as a Private ad API and be accepted")
    @When("I post a new Second TV Sound Ad as a Private ad API and be accepted")
    public void i_post_new_tvsound_ad_Chotot_accept(){
        cm_api_ads_insertELT.insertNewAdSound("accept");
    }

    @Given("I post a new TV Sound Ad as a Private ad API and be rejected")
    @When("I post a new Second TV Sound Ad as a Private ad API and be rejected")
    public void i_post_new_tvsound_ad_Chotot_reject(){
        cm_api_ads_insertELT.insertNewAdSound("reject");
    }

    @When("I post a new TV Sound Ad as a Private ad API for buying")
    public void verifyInsertAdSoundBuy() {
        cm_api_ads_insertELT.insertNewAdSoundBuy();
    }

    @When("I post a new TV Sound Ad as a Pro ad API")
    public void i_post_new_tvsound_ad_pro(){
        cm_api_ads_insertELT.insertNewAdSoundPro();
    }

    @When("I post a new TV Sound Ad as a Pro ad API for buying")
    public void i_post_new_tvsound_ad_pro_buying(){
        cm_api_ads_insertELT.insertNewAdSoundBuyPro();
    }

    @When("I post a new TV Sound Ad as a Pro ad API and be accepted")
    public void i_post_new_tvsound_ad_pro_accepted(){
        cm_api_ads_insertELT.insertNewAdSoundPro("accept");
    }

    @When("I post {string} new TV Sound Ads as Private ads API")
    public void i_post_new_TV_Sound_Ad_as_a_Pro_ad_API(String numberOfAd) {
        for(int i = 0; i < Integer.parseInt(numberOfAd); i++){
            cm_api_ads_insertELT.insertNewAdSound();
        }
    }

    @When("I post {string} new TV Sound Ads as Private ads API and be accepted")
    public void i_post_new_TV_Sound_Ad_as_a_Pro_ad_API_be_accepted(String numberOfAd) {
        for(int i = 0; i < Integer.parseInt(numberOfAd); i++){
            cm_api_ads_insertELT.insertNewAdSound("accept");
        }
    }

    @Given("I edit my new TV Sound Ad as a Private ad API and be rejected")
    public void i_edit_my_new_ad_and_rejected(){
        cm_api_ads_editELT.editNewAdSound(tempAdID, "reject");
    }

    @Given("I edit my new TV Sound Ad as a Private ad API and be accepted")
    public void i_edit_my_new_ad_and_accepted(){
        cm_api_ads_editELT.editNewAdSound(tempAdID, "accept");
    }
}
