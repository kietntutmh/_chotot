package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adFashionExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Fashion extends BaseAPITest {

    @Test(description = "Insert Ad - Fashion, Verify insert Clothes ad successfully, Quoc Tran, MABU")
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

    @Test(description = "Insert Ad - Fashion, Verify insert Watch ad successfully, Quoc Tran, MABU")
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

    @Test(description = "Insert Ad - Fashion, Verify insert Shoes ad successfully, Tuan Chieu, MABU")
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

    @Test(
            description = "Insert Ad - Fashion, Verify insert Handbag ad successfully, Tuan Chieu, MABU")
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

    @Test(
            description = "Insert Ad - Fashion, Verify insert Perfume ad successfully, Tuan Chieu, MABU")
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

    @Test(
            description =
                    "Insert Ad - Fashion, Verify insert Accessories ad successfully, Tuan Chieu, MABU")
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
}
