package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adHousehold_Furniture_PlantExcelFile;
import static api.configuration.DataConfig.setTempAccountAPIAndGetToken;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Household_Furniture_Plant extends BaseAPITest {

    private void setupAccount() {
        setTempAccountAPIAndGetToken(17);
    }

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Kitchen_Appliance ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdKitchen_Appliance() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Kitchen_Utensil_Dinnerware ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdKitchen_Utensil_Dinnerware() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Bed_Bedding ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdBed_Bedding() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Bathware ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdBathware() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Fan ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdFan() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Lighting ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdLighting() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Household_Table_Chair ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdHousehold_Table_Chair() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Household_Drawer_Shelf ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdHousehold_Drawer_Shelf() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Ornamental_Plant_Decoration ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdOrnamental_Plant_Decoration() {
        //Setup account test
        setupAccount();

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

    @Test(description = "Insert Ad - Household_Furniture_Plant, Verify insert Other_Household_Items ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdOther_Household_Items() {
        //Setup account test
        setupAccount();

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
}
