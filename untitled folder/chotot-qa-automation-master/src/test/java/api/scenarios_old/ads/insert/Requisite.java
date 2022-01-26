package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adRequisiteExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Requisite extends BaseAPITest {

    @Test(
            description =
                    "Insert Ad - Requisite, Verify insert Requisite ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdRequisite() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

        // Get list data from excel
        List<String> requisiteDataKeys = excelDataProvider.getRowData(1);
        List<String> requisiteDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(requisiteDataKeys, requisiteDataValues);

        // Post ad requisite data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - OfficeEquipment, Verify insert OfficeEquipment ad successfully, Tri Nguyen, MABU")
    public void verifyInsertAdSpecializedItem() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "SpecializedItem");

        // Get list data from excel
        List<String> requisiteDataKeys = excelDataProvider.getRowData(1);
        List<String> requisiteDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(requisiteDataKeys, requisiteDataValues);

        // Post ad requisite data
        insertNewAd(data);
    }
}
