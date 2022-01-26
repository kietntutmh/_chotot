package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adServiceExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Services {

    @Then("I can post a new Service Ad using api successfully")
    @When("I post a new Service Ad using api successfully")
    public void verifyInsertAdService() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Service");

        // Get list data from excel
        List<String> serviceDataKeys = excelDataProvider.getRowData(1);
        List<String> serviceDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(serviceDataKeys, serviceDataValues);

        // Post ad service data
        insertNewAd(data);
    }

    @Then("I can post a new Travel Ad using api successfully")
    @When("I post a new Travel Ad using api successfully")
    public void verifyInsertAdTravel() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Travel");
        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad travel data
        insertNewAd(data);
    }

    @Then("I can post a new Service Pro Ad using api successfully")
    @When("I post a new Service Pro Ad using api successfully")
    public void verifyInsertProAdService() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Service");

        // Get list data from excel
        List<String> serviceDataKeys = excelDataProvider.getRowData(5);
        List<String> serviceDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(serviceDataKeys, serviceDataValues);

        // Post ad service data
        insertNewAd(data);
    }

    @Then("I can post a new Travel Pro Ad using api successfully")
    @When("I post a new Travel Pro Ad using api successfully")
    public void verifyInsertProAdTravel() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Travel");
        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad travel data
        insertNewAd(data);
    }
}
