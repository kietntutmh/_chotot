package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adFashionExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Fashion {

    @Then("I can post a new Clothes Ad using api successfully")
    @When("I post a new Clothes Ad using api successfully")
    public void verifyInsertAdClothes() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

        // Get list data from excel
        List<String> fashionDataKeys = excelDataProvider.getRowData(1);
        List<String> fashionDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(fashionDataKeys, fashionDataValues);

        // Post ad fashion data
        insertNewAd(data);
    }

    @Then("I can post a new Watch Ad using api successfully")
    @When("I post a new Watch Ad using api successfully")
    public void verifyInsertAdWatch() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Watch");

        // Get list data from excel
        List<String> watchDataKeys = excelDataProvider.getRowData(1);
        List<String> watchDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(watchDataKeys, watchDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Shoes Ad using api successfully")
    @When("I post a new Shoes Ad using api successfully")
    public void verifyInsertAdShoes() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Shoes");

        // Get list data from excel
        List<String> shoesDataKeys = excelDataProvider.getRowData(1);
        List<String> shoesDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(shoesDataKeys, shoesDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

  @Then("I can post a new Handbag Ad using api successfully")
  @When("I post a new Handbag Ad using api successfully")
  public void verifyInsertAdHandbag() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Handbag");

        // Get list data from excel
        List<String> handbagDataKeys = excelDataProvider.getRowData(1);
        List<String> handbagDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(handbagDataKeys, handbagDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Perfume Ad using api successfully")
    @When("I post a new Perfume Ad using api successfully")
    public void verifyInsertAdPerfume() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Perfume");

        // Get list data from excel
        List<String> perfumeDataKeys = excelDataProvider.getRowData(1);
        List<String> perfumeDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(perfumeDataKeys, perfumeDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Accessories Clothes Ad using api successfully")
    @When("I post a new Accessories Clothes Ad using api successfully")
    public void verifyInsertAdAccessoriesClothes() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

        // Get list data from excel
        List<String> accessoriesDataKeys = excelDataProvider.getRowData(1);
        List<String> accessoriesDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(accessoriesDataKeys, accessoriesDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Clothes Pro Ad using api successfully")
    @When("I post a new Clothes Pro Ad using api successfully")
    public void verifyInsertProAdClothes() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

        // Get list data from excel
        List<String> fashionDataKeys = excelDataProvider.getRowData(5);
        List<String> fashionDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(fashionDataKeys, fashionDataValues);

        // Post ad fashion data
        insertNewAd(data);
    }

    @Then("I can post a new Watch Pro Ad using api successfully")
    @When("I post a new Watch Pro Ad using api successfully")
    public void verifyInsertProAdWatch() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Watch");

        // Get list data from excel
        List<String> watchDataKeys = excelDataProvider.getRowData(5);
        List<String> watchDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(watchDataKeys, watchDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Shoes Pro Ad using api successfully")
    @When("I post a new Shoes Pro Ad using api successfully")
    public void verifyInsertProAdShoes() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Shoes");

        // Get list data from excel
        List<String> shoesDataKeys = excelDataProvider.getRowData(5);
        List<String> shoesDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(shoesDataKeys, shoesDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Handbag Pro Ad using api successfully")
    @When("I post a new Handbag Pro Ad using api successfully")
    public void verifyInsertProAdHandbag() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Handbag");

        // Get list data from excel
        List<String> handbagDataKeys = excelDataProvider.getRowData(5);
        List<String> handbagDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(handbagDataKeys, handbagDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Perfume Pro Ad using api successfully")
    @When("I post a new Perfume Pro Ad using api successfully")
    public void verifyInsertProAdPerfume() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Perfume");

        // Get list data from excel
        List<String> perfumeDataKeys = excelDataProvider.getRowData(5);
        List<String> perfumeDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(perfumeDataKeys, perfumeDataValues);

        // Post ad watch data
        insertNewAd(data);
    }

    @Then("I can post a new Accessories Clothes Pro Ad using api successfully")
    @When("I post a new Accessories Clothes Pro Ad using api successfully")
    public void verifyInsertProAdAccessoriesClothes() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

        // Get list data from excel
        List<String> accessoriesDataKeys = excelDataProvider.getRowData(5);
        List<String> accessoriesDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(accessoriesDataKeys, accessoriesDataValues);

        // Post ad watch data
        insertNewAd(data);
    }
}
