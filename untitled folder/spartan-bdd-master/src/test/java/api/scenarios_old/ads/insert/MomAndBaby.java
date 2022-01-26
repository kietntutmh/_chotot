package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adMomAndBabyExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class MomAndBaby extends BaseAPITest {

    @Test(
            description =
                    "Insert Ad - MomAndBaby, Verify insert Mom and Baby ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdMomAndBaby() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adMomAndBabyExcelFile, "MomAndBaby");

        // Get list data from excel
        List<String> momDataKeys = excelDataProvider.getRowData(1);
        List<String> momDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(momDataKeys, momDataValues);

        // Post ad mom data
        insertNewAd(data);
    }
}
