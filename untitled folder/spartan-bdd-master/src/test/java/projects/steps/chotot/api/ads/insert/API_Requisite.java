package projects.steps.chotot.api.ads.insert;

import api.base.BaseAPITest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adRequisiteExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class API_Requisite extends BaseAPITest {

    @Then("I can post a new Requisite Ad using api successfully")
    @When("I post a new Requisite Ad using api successfully")
    public void verifyInsertAdRequisite() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

        // Get list data from excel
        List<String> requisiteDataKeys = excelDataProvider.getRowData(1);
        List<String> requisiteDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(requisiteDataKeys, requisiteDataValues);

        // Post ad requisite data
        insertNewAd(data);
    }

    @Then("I can post a new Specialized_Item Ad using api successfully")
    @When("I post a new Specialized_Item Ad using api successfully")
    public void verifyInsertAdSpecializedItem() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "SpecializedItem");

        // Get list data from excel
        List<String> requisiteDataKeys = excelDataProvider.getRowData(1);
        List<String> requisiteDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(requisiteDataKeys, requisiteDataValues);

        // Post ad requisite data
        insertNewAd(data);
    }

    @Then("I can post a new Requisite Pro Ad using api successfully")
    @When("I post a new Requisite Pro Ad using api successfully")
    public void verifyInsertProAdRequisite() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

        // Get list data from excel
        List<String> requisiteDataKeys = excelDataProvider.getRowData(5);
        List<String> requisiteDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(requisiteDataKeys, requisiteDataValues);

        // Post ad requisite data
        insertNewAd(data);
    }

    @Then("I can post a new Specialized_Item Pro Ad using api successfully")
    @When("I post a new Specialized_Item Pro Ad using api successfully")
    public void verifyInsertProAdSpecializedItem() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "SpecializedItem");

        // Get list data from excel
        List<String> requisiteDataKeys = excelDataProvider.getRowData(5);
        List<String> requisiteDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(requisiteDataKeys, requisiteDataValues);

        // Post ad requisite data
        insertNewAd(data);
    }
}
