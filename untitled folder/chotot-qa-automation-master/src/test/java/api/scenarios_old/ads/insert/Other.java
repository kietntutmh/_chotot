package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adOtherExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Other extends BaseAPITest {

    @Test(description = "Insert Ad - Other, Verify insert Other ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdOther() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Other");

        // Get list data from excel
        List<String> otherDataKeys = excelDataProvider.getRowData(1);
        List<String> otherDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(otherDataKeys, otherDataValues);

        // Post ad other data
        insertNewAd(data);
    }
}
