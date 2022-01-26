package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adOtherExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Other {

    @Then("I can post a new Other Ad using api successfully")
    @When("I post a new Other Ad using api successfully")
    public void verifyInsertAdOther() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Other");

        // Get list data from excel
        List<String> otherDataKeys = excelDataProvider.getRowData(1);
        List<String> otherDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(otherDataKeys, otherDataValues);

        // Post ad other data
        insertNewAd(data);
    }

    @Then("I can post a new Other Pro Ad using api successfully")
    @When("I post a new Other Pro Ad using api successfully")
    public void verifyInsertProAdOther() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Other");

        // Get list data from excel
        List<String> otherDataKeys = excelDataProvider.getRowData(5);
        List<String> otherDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(otherDataKeys, otherDataValues);

        // Post ad other data
        insertNewAd(data);
    }
}
