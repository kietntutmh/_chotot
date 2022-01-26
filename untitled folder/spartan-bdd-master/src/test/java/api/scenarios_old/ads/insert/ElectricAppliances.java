package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adElectricAppliancesExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class ElectricAppliances extends BaseAPITest {

    @Test(
            description =
                    "Insert Ad - Electric Appliances, Verify insert Cooler ad successfully, Quoc Tran, MABU")
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

    @Test(
            description =
                    "Insert Ad - Electric Appliances, Verify insert Refrigerator ad successfully, Tuan Chieu, MABU")
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

    @Test(
            description =
                    "Insert Ad - Electric Appliances, Verify insert Washing_Machine ad successfully, Tuan Chieu, MABU")
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
}
