package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adElectricAppliancesExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_ElectricAppliances {

    @Then("I can post a new Cooler Ad using api successfully")
    @When("I post a new Cooler Ad using api successfully")
    public void verifyInsertAdCooler() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Cooler");

        // Get list data from excel
        List<String> furnitureDataKeys = excelDataProvider.getRowData(1);
        List<String> furnitureDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(furnitureDataKeys, furnitureDataValues);

        // Post ad cooler data
        insertNewAd(data);
    }

    @Then("I can post a new Refrigerator Ad using api successfully")
    @When("I post a new Refrigerator Ad using api successfully")
    public void verifyInsertAdRefrigerator() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Refrigerator");

        // Get list data from excel
        List<String> refrigeratorDataKeys = excelDataProvider.getRowData(1);
        List<String> refrigeratorDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(refrigeratorDataKeys, refrigeratorDataValues);

        // Post ad furniture data
        insertNewAd(data);
    }

    @Then("I can post a new Washing_Machine Ad using api successfully")
    @When("I post a new Washing_Machine Ad using api successfully")
    public void verifyInsertAdWashing_Machine() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Washing_Machine");

        // Get list data from excel
        List<String> housewaresDataKeys = excelDataProvider.getRowData(1);
        List<String> housewaresDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(housewaresDataKeys, housewaresDataValues);

        // Post ad furniture data
        insertNewAd(data);
    }

    @Then("I can post a new Cooler Pro Ad using api successfully")
    @When("I post a new Cooler Pro Ad using api successfully")
    public void verifyInsertProAdCooler() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Cooler");

        // Get list data from excel
        List<String> furnitureDataKeys = excelDataProvider.getRowData(5);
        List<String> furnitureDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(furnitureDataKeys, furnitureDataValues);

        // Post ad cooler data
        insertNewAd(data);
    }

    @Then("I can post a new Refrigerator Pro Ad using api successfully")
    @When("I post a new Refrigerator Pro Ad using api successfully")
    public void verifyInsertProAdRefrigerator() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Refrigerator");

        // Get list data from excel
        List<String> refrigeratorDataKeys = excelDataProvider.getRowData(5);
        List<String> refrigeratorDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(refrigeratorDataKeys, refrigeratorDataValues);

        // Post ad furniture data
        insertNewAd(data);
    }

    @Then("I can post a new Washing_Machine Pro Ad using api successfully")
    @When("I post a new Washing_Machine Pro Ad using api successfully")
    public void verifyInsertProAdWashing_Machine() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Washing_Machine");

        // Get list data from excel
        List<String> housewaresDataKeys = excelDataProvider.getRowData(5);
        List<String> housewaresDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(housewaresDataKeys, housewaresDataValues);

        // Post ad furniture data
        insertNewAd(data);
    }
}
