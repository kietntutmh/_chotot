package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adServiceExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Services extends BaseAPITest {

    @Test(
            description = "Insert Ad - Services, Verify insert Service ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdService() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Service");

        // Get list data from excel
        List<String> serviceDataKeys = excelDataProvider.getRowData(1);
        List<String> serviceDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(serviceDataKeys, serviceDataValues);

        // Post ad service data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Travel, Verify insert Travel ad successfuly, Tri Nguyen, MABU")
    public void verifyInsertAdTravel() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Travel");
        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad travel data
        insertNewAd(data);
    }
}
