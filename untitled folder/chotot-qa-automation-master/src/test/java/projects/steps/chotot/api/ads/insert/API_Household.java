package projects.steps.chotot.api.ads.insert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static api.configuration.DataConfig.adHousehold_Furniture_PlantExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static projects.utilities.Hooks.excelDataProvider;

public class API_Household {

    @Then("I can post a new Kitchen_Appliance Ad using api successfully")
    @When("I post a new Kitchen_Appliance Ad using api successfully")
    public void verifyInsertAdKitchen_Appliance() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Appliance");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Kitchen_Utensil_Dinnerware Ad using api successfully")
    @When("I post a new Kitchen_Utensil_Dinnerware Ad using api successfully")
    public void verifyInsertAdKitchen_Utensil_Dinnerware() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Utensil_Dinnerware");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Bedding Ad using api successfully")
    @When("I post a new Bedding Ad using api successfully")
    public void verifyInsertAdBedding() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bed_Bedding");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Bathware Ad using api successfully")
    @When("I post a new Bathware Ad using api successfully")
    public void verifyInsertAdBathware() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bathware");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Fan Ad using api successfully")
    @When("I post a new Fan Ad using api successfully")
    public void verifyInsertAdFan() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Fan");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Lighting Ad using api successfully")
    @When("I post a new Lighting Ad using api successfully")
    public void verifyInsertAdLighting() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Lighting");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Household_Table_Chair Ad using api successfully")
    @When("I post a new Household_Table_Chair Ad using api successfully")
    public void verifyInsertAdHousehold_Table_Chair() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Household_Table_Chair");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Household_Drawer_Shelf Ad using api successfully")
    @When("I post a new Household_Drawer_Shelf Ad using api successfully")
    public void verifyInsertAdHousehold_Drawer_Shelf() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Household_Drawer_Shelf");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Ornamental_Plant_Decoration Ad using api successfully")
    @When("I post a new Ornamental_Plant_Decoration Ad using api successfully")
    public void verifyInsertAdOrnamental_Plant_Decoration() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Ornamental_Plant_Decoration");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Other_Household_Items Ad using api successfully")
    @When("I post a new Other_Household_Items Ad using api successfully")
    public void verifyInsertAdOther_Household_Items() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Other_Household_Items");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Kitchen_Appliance Pro Ad using api successfully")
    @When("I post a new Kitchen_Appliance Pro Ad using api successfully")
    public void verifyInsertProAdKitchen_Appliance() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Appliance");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Kitchen_Utensil_Dinnerware Pro Ad using api successfully")
    @When("I post a new Kitchen_Utensil_Dinnerware Pro Ad using api successfully")
    public void verifyInsertProAdKitchen_Utensil_Dinnerware() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Utensil_Dinnerware");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Bedding Pro Ad using api successfully")
    @When("I post a new Bedding Pro Ad using api successfully")
    public void verifyInsertProAdBedding() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bed_Bedding");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Bathware Pro Ad using api successfully")
    @When("I post a new Bathware Pro Ad using api successfully")
    public void verifyInsertProAdBathware() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bathware");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Fan Pro Ad using api successfully")
    @When("I post a new Fan Pro Ad using api successfully")
    public void verifyInsertProAdFan() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Fan");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Lighting Pro Ad using api successfully")
    @When("I post a new Lighting Pro Ad using api successfully")
    public void verifyInsertProAdLighting() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Lighting");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Household_Table_Chair Pro Ad using api successfully")
    @When("I post a new Household_Table_Chair Pro Ad using api successfully")
    public void verifyInsertProAdHousehold_Table_Chair() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Household_Table_Chair");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Household_Drawer_Shelf Pro Ad using api successfully")
    @When("I post a new Household_Drawer_Shelf Pro Ad using api successfully")
    public void verifyInsertProAdHousehold_Drawer_Shelf() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Household_Drawer_Shelf");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Ornamental_Plant_Decoration Pro Ad using api successfully")
    @When("I post a new Ornamental_Plant_Decoration Pro Ad using api successfully")
    public void verifyInsertProAdOrnamental_Plant_Decoration() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Ornamental_Plant_Decoration");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    @Then("I can post a new Other_Household_Items Pro Ad using api successfully")
    @When("I post a new Other_Household_Items Pro Ad using api successfully")
    public void verifyInsertProAdOther_Household_Items() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Other_Household_Items");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(5);
        List<String> apartmentDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }
}
