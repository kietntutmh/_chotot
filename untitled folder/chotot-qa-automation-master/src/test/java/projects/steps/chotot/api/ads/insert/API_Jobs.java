package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adJobExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Jobs {

    @Then("I can post a new Job Ad using api successfully")
    @When("I post a new Job Ad using api successfully")
    public void verifyInsertAdJob() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Get list data from excel
        List<String> jobDataKeys = excelDataProvider.getRowData(1);
        List<String> jobDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(jobDataKeys, jobDataValues);

        // Post ad job data
        insertNewAd(data);
    }

    @Then("I can post a new Looking Job Ad using api successfully")
    @When("I post a new Looking Job Ad using api successfully")
    public void verifyInsertAdJPeopleLookingForJobs() {
        //setup data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Get list data from excel
        List<String> jobDataKeys = excelDataProvider.getRowData(6);
        List<String> jobDataValues = excelDataProvider.getRowData(7);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(jobDataKeys, jobDataValues);

        // Post ad job data
        insertNewAd(data);
    }

    @Then("I can post a new Job Pro Ad using api successfully")
    @When("I post a new Job Pro Ad using api successfully")
    public void verifyInsertProAdJob() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Get list data from excel
        List<String> jobDataKeys = excelDataProvider.getRowData(1);
        List<String> jobDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(jobDataKeys, jobDataValues);

        // Post ad job data
        insertNewAd(data);
    }

    @Then("I can post a new Looking Job Pro Ad using api successfully")
    @When("I post a new Looking Job Pro Ad using api successfully")
    public void verifyInsertProAdJPeopleLookingForJobs() {
        //setup data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Get list data from excel
        List<String> jobDataKeys = excelDataProvider.getRowData(10);
        List<String> jobDataValues = excelDataProvider.getRowData(11);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(jobDataKeys, jobDataValues);

        // Post ad job data
        insertNewAd(data);
    }
}
