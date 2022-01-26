package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot._common.api.ads.edit.CM_API_Ads_EditPTY;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertPTY;

import java.util.List;

import static api.configuration.DataConfig.adPTYExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.listingFee.ListingFee_API_Functions.checkOrderHistory_POSOrder_Refund;
import static projects.functions.chotot.listingFee.ListingFee_API_Functions.getOwnerToken;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getAmountAfterPayDT;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;
import static projects.utilities.Hooks.excelDataProvider;

public class API_PTY {

    @Then("I can post a new Apartment Ad using api successfully")
    @When("I post a new Apartment Ad using api successfully")
    public void verifyInsertAdApartment() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Apartment Pro Ad using api successfully")
    @When("I post a new Apartment Pro Ad using api successfully")
    public void verifyInsertProAdApartment() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(47);
        List<String> apartmentDataValues = excelDataProvider.getRowData(48);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Apartment Ad Shop using api successfully")
    @When("I post a new Apartment Ad Shop using api successfully")
    public void verifyInsertAdApartmentToShop() {
        topupDongTotWithMomo(tempUserPhone);

        // Setup test datas
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(6);
        List<String> apartmentDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Apartment Ad Chotot using api successfully")
    @When("I post a new Apartment Ad Chotot using api successfully")
    public void verifyInsertAdApartmentShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(11);
        List<String> apartmentDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new House Ad using api successfully")
    @When("I post a new House Ad using api successfully")
    public void verifyInsertAdHouse() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        List<String> houseDataKeys = excelDataProvider.getRowData(1);
        List<String> houseDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(houseDataKeys, houseDataValues);

        // Post ad house data
        insertNewAd(data);
    }

    @Then("I can post a new House Pro Ad using api successfully")
    @When("I post a new House Pro Ad using api successfully")
    public void verifyInsertProAdHouse() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        List<String> houseDataKeys = excelDataProvider.getRowData(125);
        List<String> houseDataValues = excelDataProvider.getRowData(126);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(houseDataKeys, houseDataValues);

        // Post ad house data
        insertNewAd(data);
    }

    @Then("I can post a new House Ad Shop using api successfully")
    @When("I post a new House Ad Shop using api successfully")
    public void verifyInsertAdHouseToShop() {
        CM_API_Ads_InsertPTY cm_api_ads_insertPTY = new CM_API_Ads_InsertPTY();
        cm_api_ads_insertPTY.insertNewAdHouseShop("accept", true, true);
    }

    @Then("I can post a new House Ad Chotot using api successfully")
    @When("I post a new House Ad Chotot using api successfully")
    public void verifyInsertAdHouseShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        List<String> houseDataKeys = excelDataProvider.getRowData(11);
        List<String> houseDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(houseDataKeys, houseDataValues);

        // Post ad house data
        insertNewAd(data);
    }

    @Then("I can post a new Land Ad using api successfully")
    @When("I post a new Land Ad using api successfully")
    public void verifyInsertAdLand() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(1);
        List<String> landDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    @Then("I can post a new Land Pro Ad using api successfully")
    @When("I post a new Land Pro Ad using api successfully")
    public void verifyInsertProAdLand() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(125);
        List<String> landDataValues = excelDataProvider.getRowData(126);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    @Then("I can post a new Land Ad Shop using api successfully")
    @When("I post a new Land Ad Shop using api successfully")
    public void verifyInsertAdLandToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(6);
        List<String> landDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    @Then("I can post a new Land Ad Chotot using api successfully")
    @When("I post a new Land Ad Chotot using api successfully")
    public void verifyInsertAdLandShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(11);
        List<String> landDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    @Then("I can post a new Office Ad using api successfully")
    @When("I post a new Office Ad using api successfully")
    public void verifyInsertAdOffice() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(1);
        List<String> officeDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    @Then("I can post a new Office Pro Ad using api successfully")
    @When("I post a new Office Pro Ad using api successfully")
    public void verifyInsertProAdOffice() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(18);
        List<String> officeDataValues = excelDataProvider.getRowData(19);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    @Then("I can post a new Office Ad Shop using api successfully")
    @When("I post a new Office Ad Shop using api successfully")
    public void verifyInsertAdOfficeToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(6);
        List<String> officeDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    @Then("I can post a new Office Ad Chotot using api successfully")
    @When("I post a new Office Ad Chotot using api successfully")
    public void verifyInsertAdOfficeShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(11);
        List<String> officeDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    @Then("I can post a new RoomForLease Ad using api successfully")
    @When("I post a new RoomForLease Ad using api successfully")
    public void verifyInsertAdRoomForLease() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomeDataKeys = excelDataProvider.getRowData(1);
        List<String> roomDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomeDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }

    @Then("I can post a new RoomForLease Pro Ad using api successfully")
    @When("I post a new RoomForLease Pro Ad using api successfully")
    public void verifyInsertProAdRoomForLease() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomeDataKeys = excelDataProvider.getRowData(18);
        List<String> roomDataValues = excelDataProvider.getRowData(19);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomeDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }

    @Then("I can post a new RoomForLease Ad Shop using api successfully")
    @When("I post a new RoomForLease Ad Shop using api successfully")
    public void verifyInsertAdRoomForLeaseToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomeDataKeys = excelDataProvider.getRowData(6);
        List<String> roomDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomeDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }

    @Then("I can post a new RoomForLease Ad Chotot using api successfully")
    @When("I post a new RoomForLease Ad Chotot using api successfully")
    public void verifyInsertAdRoomForLeaseShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomDataKeys = excelDataProvider.getRowData(11);
        List<String> roomDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }

    @Given("I post {string} new Apartment Ad using api successfully")
    public void iPostNewApartmentAdUsingApiSuccessfully(String no) {
        int NumOfAd = Integer.parseInt(no);
        for(int i=0;i<NumOfAd;i++){
            delayStep(minWaitTime); // delay to wait for server more stability
            verifyInsertAdApartment();
        }

    }

    @Given("I post {string} new Land Ad using api successfully")
    public void iPostNewLandAdUsingApiSuccessfully(String no) {
        int NumOfAd = Integer.parseInt(no);
        for(int i=0;i<NumOfAd;i++){
            delayStep(minWaitTime); // delay to wait for server more stability
            verifyInsertAdLand();
        }
    }
    @Given("I post {string} new House Ad using api successfully")
    public void iPostNewHouseAdUsingApiSuccessfully(String no) {
        int NumOfAd = Integer.parseInt(no);
        for(int i=0;i<NumOfAd;i++){
            delayStep(minWaitTime); // delay to wait for server more stability
            verifyInsertAdHouse();
        }
    }

    //--------------- INSERT AD with CP--------------------
    CM_API_Ads_InsertPTY cm_api_ads_insertPTY  = new CM_API_Ads_InsertPTY();
    CM_API_Ads_EditPTY  cm_api_ads_editPTY = new CM_API_Ads_EditPTY();

    @Given("I post a new Land Ad as a Private ad API and be rejected")
    public void iPostANewLandAdAsAPrivateAdAPIAndBeRejected() {
        cm_api_ads_insertPTY.insertNewAdLand("reject");

    }

    @Given("I post a new Apartment Ad as a private ad API for buying")
    public void iPostANewApartmentAdAsAPrivateAdAPIForBuying() {
        cm_api_ads_insertPTY.insertNewAdApartmentBuy(false);
    }

    @And("I post a new House Ad as a private ad API for buying")
    public void iPostANewHouseAdAsAPrivateAdAPIForBuying() {
        cm_api_ads_insertPTY.insertNewAdHouseBuy(false);
    }

    @And("I post a new Land Ad as a Private ad API for buying")
    public void iPostANewLandAdAsAPrivateAdAPIForBuying() {
        cm_api_ads_insertPTY.insertNewAdLandBuy(false);
    }


    @Given("I post a new House Ad as a Pro ad API for buying")
    public void iPostANewHouseAdAsAProAdAPIForBuying() {
        cm_api_ads_insertPTY.insertNewAdHouseBuyPro(false);
    }

    @Given("I post {string} new House Ads as Private ads API and be accepted")
    public void iPostNewHouseAdsAsPrivateAdsAPIAndBeAccepted(String arg0) {
        cm_api_ads_insertPTY.insertNewAdHouse("accept");

    }

    @When("I edit my new Land Ad as a Private ad API and be rejected")
    public void iEditMyNewLandAdAsAPrivateAdAPIAndBeRejected() {
        cm_api_ads_editPTY.editNewAdLand(tempAdID,"reject");
    }

    @When("I post a new Apartment Ad and pay for Listing Fee But ad is rejected and DT is refunded")
    public void iPostANewAparstmentAdAndPayForListingFeeButAdIsRejectedAndDTIsRefunded() {
        cm_api_ads_insertPTY.insertNewAdApartment("rejectpay");
    }

    @When("I post a new Apartment PRO Ad and pay for Listing Fee But ad is rejected and DT is refunded")
    public void iPostANewsAparstmentAdAndPayForListingFeeButAdIsRejectedAndDTIsRefunded() {
        cm_api_ads_insertPTY.insertNewAdApartmentPro("rejectpay");
    }

    @And("I should see the POS refund in my Order History")
    public void iShouldSeeThePOSRefundInMyOrderHistory() {
        checkOrderHistory_POSOrder_Refund(getOwnerToken(), getAmountAfterPayDT());
    }

    @And("I post a new Apartment Ad and Cho Tot accept my Ad")
    public void iPostANewApartmentAdAndChoTotAcceptMyAd() {
        cm_api_ads_insertPTY.insertNewAdApartment("accept");
    }

    @When("I post a new House Ad and Cho Tot accept my Ad")
    public void iPostANewHouseAdAndChoTotAcceptMyAd() {
        cm_api_ads_insertPTY.insertNewAdHouse_NoUploadNewImage("accept");
    }

    @When("I post a new Land Ad and Cho Tot accept my Ad")
    public void iPostANewLandAdAndChoTotAcceptMyAd() {
        cm_api_ads_insertPTY.insertNewAdLand_NoUploadNewImage("accept");
    }

    @When("I post a new Office Equipment Ad and Cho Tot accept my Ad")
    public void iPostANewOfficeEquipmentAdAndChoTotAcceptMyAd() {
        cm_api_ads_insertPTY.insertNewAdOffice_NoUploadNewImage("accept");
    }

    @When("I post a new RoomForLease Ad and Cho Tot accept my Ad")
    public void iPostANewRoomForLeaseAdAndChoTotAcceptMyAd() {
        cm_api_ads_insertPTY.insertNewAdRoomForLease_NoUploadNewImage("accept");
    }



}
