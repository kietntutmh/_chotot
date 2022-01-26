package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adEntertainmentExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Entertainment {

    @Then("I can post a new Instrument Ad using api successfully")
    @When("I post a new Instrument Ad using api successfully")
    public void verifyInsertAdInstrument() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Instrument");

        // Get list data from excel
        List<String> instrumentDataKeys = excelDataProvider.getRowData(1);
        List<String> instrumentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(instrumentDataKeys, instrumentDataValues);

        // Post ad instrument data
        insertNewAd(data);
    }

    @Then("I can post a new Sport Ad using api successfully")
    @When("I post a new Sport Ad using api successfully")
    public void verifyInsertAdSport() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Sport");

        // Get list data from excel
        List<String> sportDataKeys = excelDataProvider.getRowData(1);
        List<String> sportDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(sportDataKeys, sportDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Book Ad using api successfully")
    @When("I post a new Book Ad using api successfully")
    public void verifyInsertAdBook() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Book");

        // Get list data from excel
        List<String> bookDataKeys = excelDataProvider.getRowData(1);
        List<String> bookDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(bookDataKeys, bookDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Collectibles Ad using api successfully")
    @When("I post a new Collectibles Ad using api successfully")
    public void verifyInsertAdCollectibles() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

        // Get list data from excel
        List<String> collectiblesDataKeys = excelDataProvider.getRowData(1);
        List<String> collectiblesDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(collectiblesDataKeys, collectiblesDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Gaming Ad using api successfully")
    @When("I post a new Gaming Ad using api successfully")
    public void verifyInsertAdGaming() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Gaming");

        // Get list data from excel
        List<String> gamingDataKeys = excelDataProvider.getRowData(1);
        List<String> gamingDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(gamingDataKeys, gamingDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Hobby Ad using api successfully")
    @When("I post a new Hobby Ad using api successfully")
    public void verifyInsertAdHobby() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Hobby");

        // Get list data from excel
        List<String> hobbyDataKeys = excelDataProvider.getRowData(1);
        List<String> hobbyDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(hobbyDataKeys, hobbyDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Instrument Pro Ad using api successfully")
    @When("I post a new Instrument Pro Ad using api successfully")
    public void verifyInsertProAdInstrument() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Instrument");

        // Get list data from excel
        List<String> instrumentDataKeys = excelDataProvider.getRowData(10);
        List<String> instrumentDataValues = excelDataProvider.getRowData(11);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(instrumentDataKeys, instrumentDataValues);

        // Post ad instrument data
        insertNewAd(data);
    }

    @Then("I can post a new Sport Pro Ad using api successfully")
    @When("I post a new Sport Pro Ad using api successfully")
    public void verifyInsertProAdSport() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Sport");

        // Get list data from excel
        List<String> sportDataKeys = excelDataProvider.getRowData(10);
        List<String> sportDataValues = excelDataProvider.getRowData(11);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(sportDataKeys, sportDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Book Pro Ad using api successfully")
    @When("I post a new Book Pro Ad using api successfully")
    public void verifyInsertProAdBook() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Book");

        // Get list data from excel
        List<String> bookDataKeys = excelDataProvider.getRowData(10);
        List<String> bookDataValues = excelDataProvider.getRowData(11);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(bookDataKeys, bookDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Collectibles Pro Ad using api successfully")
    @When("I post a new Collectibles Pro Ad using api successfully")
    public void verifyInsertProAdCollectibles() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

        // Get list data from excel
        List<String> collectiblesDataKeys = excelDataProvider.getRowData(10);
        List<String> collectiblesDataValues = excelDataProvider.getRowData(11);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(collectiblesDataKeys, collectiblesDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Gaming Pro Ad using api successfully")
    @When("I post a new Gaming Pro Ad using api successfully")
    public void verifyInsertProAdGaming() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Gaming");

        // Get list data from excel
        List<String> gamingDataKeys = excelDataProvider.getRowData(10);
        List<String> gamingDataValues = excelDataProvider.getRowData(11);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(gamingDataKeys, gamingDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Then("I can post a new Hobby Pro Ad using api successfully")
    @When("I post a new Hobby Pro Ad using api successfully")
    public void verifyInsertProAdHobby() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Hobby");

        // Get list data from excel
        List<String> hobbyDataKeys = excelDataProvider.getRowData(10);
        List<String> hobbyDataValues = excelDataProvider.getRowData(11);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(hobbyDataKeys, hobbyDataValues);

        // Post ad sport data
        insertNewAd(data);
    }
}
