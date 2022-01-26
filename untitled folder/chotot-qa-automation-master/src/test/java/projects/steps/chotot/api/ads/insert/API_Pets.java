package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adPetExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Pets {

    @Then("I can post a new Chicken Ad using api successfully")
    @When("I post a new Chicken Ad using api successfully")
    public void verifyInsertAdChicken() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Chicken");

        // Get list data from excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Dog Ad using api successfully")
    @When("I post a new Dog Ad using api successfully")
    public void verifyInsertAdDog() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Bird Ad using api successfully")
    @When("I post a new Bird Ad using api successfully")
    public void verifyInsertAdBird() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Cat Ad using api successfully")
    @When("I post a new Cat Ad using api successfully")
    public void verifyInsertAdCat() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Other Pet Ad using api successfully")
    @When("I post a new Other Pet Ad using api successfully")
    public void verifyInsertAdOtherPet() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "other");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Pet_Accessories Ad using api successfully")
    @When("I post a new Pet_Accessories Ad using api successfully")
    public void verifyInsertAdPetAccessories() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "accessory");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Chicken Pro Ad using api successfully")
    @When("I post a new Chicken Pro Ad using api successfully")
    public void verifyInsertProAdChicken() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Chicken");

        // Get list data from excel
        List<String> petDataKeys = excelDataProvider.getRowData(5);
        List<String> petDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Dog Pro Ad using api successfully")
    @When("I post a new Dog Pro Ad using api successfully")
    public void verifyInsertProAdDog() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(10);
        List<String> petDataValues = excelDataProvider.getRowData(11);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Bird Pro Ad using api successfully")
    @When("I post a new Bird Pro Ad using api successfully")
    public void verifyInsertProAdBird() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(5);
        List<String> petDataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Cat Pro Ad using api successfully")
    @When("I post a new Cat Pro Ad using api successfully")
    public void verifyInsertProAdCat() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(5);
        List<String> petDataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Other Pet Pro Ad using api successfully")
    @When("I post a new Other Pet Pro Ad using api successfully")
    public void verifyInsertProAdOtherPet() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "other");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(5);
        List<String> petDataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Then("I can post a new Pet_Accessories Pro Ad using api successfully")
    @When("I post a new Pet_Accessories Pro Ad using api successfully")
    public void verifyInsertProAdPetAccessories() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "accessory");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(5);
        List<String> petDataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }
}
