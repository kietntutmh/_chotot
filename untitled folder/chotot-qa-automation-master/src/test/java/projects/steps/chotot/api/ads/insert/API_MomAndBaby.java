package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adMomAndBabyExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_MomAndBaby {

    @Then("I can post a new Mom And Baby Ad using api successfully")
    @When("I post a new Mom And Baby Ad using api successfully")
    public void verifyInsertAdMomAndBaby() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adMomAndBabyExcelFile, "MomAndBaby");

        // Get list data from excel
        List<String> momDataKeys = excelDataProvider.getRowData(1);
        List<String> momDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(momDataKeys, momDataValues);

        // Post ad mom data
        insertNewAd(data);
    }

    @Then("I can post a new Mom And Baby Pro Ad using api successfully")
    @When("I post a new Mom And Baby Pro Ad using api successfully")
    public void verifyInsertProAdMomAndBaby() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adMomAndBabyExcelFile, "MomAndBaby");

        // Get list data from excel
        List<String> momDataKeys = excelDataProvider.getRowData(5);
        List<String> momDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(momDataKeys, momDataValues);

        // Post ad mom data
        insertNewAd(data);
    }
}
