package api.scenarios_old.deeplink;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adElectricAppliancesExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class DeepLink_PTY extends BaseAPITest {
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
}
