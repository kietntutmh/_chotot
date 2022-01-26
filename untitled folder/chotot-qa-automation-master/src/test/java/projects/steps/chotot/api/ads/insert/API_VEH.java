package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditVEH;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertVehicle;

import java.util.List;

import static api.configuration.DataConfig.adVehicleExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_200k;
import static projects.utilities.Hooks.excelDataProvider;

public class API_VEH {

    @Then("I can post a new Car Ad using api successfully")
    @When("I post a new Car Ad using api successfully")
    public void verifyInsertAdCar() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(1);
        List<String> carDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    @Then("I can post a new Car Pro Ad using api successfully")
    @When("I post a new Car Pro Ad using api successfully")
    public void verifyInsertProAdCar() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(21);
        List<String> carDataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    @Then("I can post a new Car Ad Chotot using api successfully")
    @When("I post a new Car Ad Chotot using api successfully")
    public void verifyInsertAdCarToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(6);
        List<String> carDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    @Then("I can post a new Car Ad Shop using api successfully")
    @When("I post a new Car Ad Shop using api successfully")
    public void verifyInsertAdCarToShop() {
        topupDongTotWithMomo(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(11);
        List<String> carDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    @Then("I can post a new Motorbike Ad using api successfully")
    @When("I post a new Motorbike Ad using api successfully")
    public void verifyInsertAdMotorbike() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(1);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Then("I can post a new Motorbike Pro Ad using api successfully")
    @When("I post a new Motorbike Pro Ad using api successfully")
    public void verifyInsertProAdMotorbike() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(21);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Then("I can post a new Motorbike Ad Chotot using api successfully")
    @When("I post a new Motorbike Ad Chotot using api successfully")
    public void verifyInsertAdMotorbikeToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(6);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Then("I can post a new Motorbike Ad Shop using api successfully")
    @When("I post a new Motorbike Ad Shop using api successfully")
    public void verifyInsertAdMotorbikeToShop() {
        // Topup Dong tot
        topupDongTotWithMomo_200k(tempUserPhone);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(11);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Then("I can post a new Truck Ad using api successfully")
    @When("I post a new Truck Ad using api successfully")
    public void verifyInsertAdTruck() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Then("I can post a new Truck Pro Ad using api successfully")
    @When("I post a new Truck Pro Ad using api successfully")
    public void verifyInsertProAdTruck() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(21);
        List<String> dataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Then("I can post a new Truck Ad Chotot using api successfully")
    @When("I post a new Truck Ad Chotot using api successfully")
    public void verifyInsertAdTruckToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Then("I can post a new Truck Ad Shop using api successfully")
    @When("I post a new Truck Ad Shop using api successfully")
    public void verifyInsertAdTruckToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Then("I can post a new ElectricVehicle Ad using api successfully")
    @When("I post a new ElectricVehicle Ad using api successfully")
    public void verifyInsertAdElectricVehicle() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Then("I can post a new ElectricVehicle Pro Ad using api successfully")
    @When("I post a new ElectricVehicle Pro Ad using api successfully")
    public void verifyInsertProAdElectricVehicle() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Then("I can post a new ElectricVehicle Ad Chotot using api successfully")
    @When("I post a new ElectricVehicle Ad Chotot using api successfully")
    public void verifyInsertAdElectricVehicleToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Then("I can post a new ElectricVehicle Ad Shop using api successfully")
    @When("I post a new ElectricVehicle Ad Shop using api successfully")
    public void verifyInsertAdElectricVehicleToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Then("I can post a new Bicycles Ad using api successfully")
    @When("I post a new Bicycles Ad using api successfully")
    public void verifyInsertAdBicycles() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Then("I can post a new Bicycles Pro Ad using api successfully")
    @When("I post a new Bicycles Pro Ad using api successfully")
    public void verifyInsertProAdBicycles() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Then("I can post a new Bicycles Ad Chotot using api successfully")
    @When("I post a new Bicycles Ad Chotot using api successfully")
    public void verifyInsertAdBicyclesToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Then("I can post a new Bicycles Ad Shop using api successfully")
    @When("I post a new Bicycles Ad Shop using api successfully")
    public void verifyInsertAdBicyclesToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Then("I can post a new VehiclesPart Ad using api successfully")
    @When("I post a new VehiclesPart Ad using api successfully")
    public void verifyInsertAdVehiclesPart() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Then("I can post a new VehiclesPart Pro Ad using api successfully")
    @When("I post a new VehiclesPart Pro Ad using api successfully")
    public void verifyInsertProAdVehiclesPart() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Then("I can post a new VehiclesPart Ad Chotot using api successfully")
    @When("I post a new VehiclesPart Ad Chotot using api successfully")
    public void verifyInsertAdVehiclesPartToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Then("I can post a new VehiclesPart Ad Shop using api successfully")
    @When("I post a new VehiclesPart Ad Shop using api successfully")
    public void verifyInsertAdVehiclesPartToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Then("I can post a new Other_Vehicle Ad using api successfully")
    @When("I post a new Other_Vehicle Ad using api successfully")
    public void verifyInsertOtherVehicle() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }

    @Then("I can post a new Other_Vehicle Pro Ad using api successfully")
    @When("I post a new Other_Vehicle Pro Ad using api successfully")
    public void verifyInsertProAdOtherVehicle() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }

    @Then("I can post a new Other_Vehicle Ad Chotot using api successfully")
    @When("I post a new Other_Vehicle Ad Chotot using api successfully")
    public void verifyInsertOtherVehicleToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }

    @Then("I can post a new Other_Vehicle Ad Shop using api successfully")
    @When("I post a new Other_Vehicle Ad Shop using api successfully")
    public void verifyInsertOtherVehicleToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }

    //--------------- INSERT AD with CP--------------------
    CM_API_Ads_InsertVehicle cm_api_ads_insertVEH  = new CM_API_Ads_InsertVehicle();
    CM_API_Ads_EditVEH cm_api_ads_EditVEH  = new CM_API_Ads_EditVEH();

    @Given("I post a new Motorbike Ad as a Private ad API and be accepted")
    public void iPostANewMotorbikeAdAsAPrivateAdAPIAndBeAccepted() {
        cm_api_ads_insertVEH.insertNewAdMotorbike("accept");

    }

    @Given("I post a new Motorbike Ad as a Private ad API and be rejected")
    public void iPostANewMotorbikeAdAsAPrivateAdAPIAndBeRejected() {
        cm_api_ads_insertVEH.insertNewAdMotorbike("reject");

    }

    @Given("I post a new Motorbike Ad as a private ad API for buying")
    public void iPostANewMotorbikeAdAsAPrivateAdAPIForBuying() {
        cm_api_ads_insertVEH.insertNewAdMotorbike_NeedBuyType();
    }

    @When("I post a new Truck Ad as a private ad API for buying")
    public void iPostANewTruckAdAsAPrivateAdAPIForBuying() {
        cm_api_ads_insertVEH.insertNewAdTrucks_NeedBuyType();
    }

    @And("I post a new Car Ad as a private ad API for buying")
    public void iPostANewCarAdAsAPrivateAdAPIForBuying() {
        cm_api_ads_insertVEH.insertNewAdCar_NeedBuyType();
    }

    @Given("I post a new Car Ad as a Pro ad API for buying")
    public void iPostANewCarAdAsAProAdAPIForBuying() {
        cm_api_ads_insertVEH.insertNewAdCar_NeedBuyType();
    }

    @Given("I post {string} new Motorbike Ad as a Private ad API and be accepted")
    public void iPostNewMotorbikeAdAsAPrivateAdAPIAndBeAccepted(String no) {
        int NumOfAd = Integer.parseInt(no);
        for(int i=0;i<NumOfAd;i++){
            delayStep(minWaitTime); // delay to wait for server more stability
            verifyInsertAdMotorbike();
        }
    }

    @When("I edit a new Motorbike Ad as a Private ad API and be rejected")
    public void iEditANewMotorbikeAdAsAPrivateAdAPIAndBeRejected() {
        cm_api_ads_EditVEH.editNewAdMotorbike(tempAdID,"reject");
    }


    @Given("I post {string} new Motorbike Ad using api successfully")
    public void iPostNewMotorbikeAdUsingApiSuccessfully(String no) {
        int NumOfAd = Integer.parseInt(no);
        for(int i=0;i<NumOfAd;i++){
            delayStep(minWaitTime); // delay to wait for server more stability
            cm_api_ads_insertVEH.insertNewAdMotorbike();

        }
    }

    @When("I post a new Motorbike Ad and pay for Listing Fee But ad is rejected and DT is refunded")
    public void iPostANewMotorbikeAdAndPayForListingFeeButAdIsRejectedAndDTIsRefunded() {
        cm_api_ads_insertVEH.insertNewAdMotorbike("rejectpay");
    }

    @Given("I post a new Motorbike Ad as a Pro ad and pay for Listing Fee But ad is rejected and DT is refunded")
    public void iPostANewMotorbikeAdAsAProAdAndPayForListingFeeButAdIsRejectedAndDTIsRefunded() {
        cm_api_ads_insertVEH.insertNewAdMotorbikeForPro("rejectpay");
    }
}
