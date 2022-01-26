package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adEntertainmentExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Entertainment extends BaseAPITest {

    @Test(
            description =
                    "Insert Ad - Entertainment, Verify insert Instrument ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdInstrument() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Instrument");

        // Get list data from excel
        List<String> instrumentDataKeys = excelDataProvider.getRowData(1);
        List<String> instrumentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(instrumentDataKeys, instrumentDataValues);

        // Post ad instrument data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Entertainment, Verify insert Sport ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdSport() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Sport");

        // Get list data from excel
        List<String> sportDataKeys = excelDataProvider.getRowData(1);
        List<String> sportDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(sportDataKeys, sportDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Entertainment, Verify insert Book ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdBook() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Book");

        // Get list data from excel
        List<String> bookDataKeys = excelDataProvider.getRowData(1);
        List<String> bookDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(bookDataKeys, bookDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Entertainment, Verify insert Collectibles ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdCollectibles() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

        // Get list data from excel
        List<String> collectiblesDataKeys = excelDataProvider.getRowData(1);
        List<String> collectiblesDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(collectiblesDataKeys, collectiblesDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Entertainment, Verify insert Gaming ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdGaming() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Gaming");

        // Get list data from excel
        List<String> gamingDataKeys = excelDataProvider.getRowData(1);
        List<String> gamingDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(gamingDataKeys, gamingDataValues);

        // Post ad sport data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Entertainment, Verify insert Hobby ad successfully, Tuan Chieu, MABU")
    public void verifyInsertAdHobby() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Hobby");

        // Get list data from excel
        List<String> hobbyDataKeys = excelDataProvider.getRowData(1);
        List<String> hobbyDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(hobbyDataKeys, hobbyDataValues);

        // Post ad sport data
        insertNewAd(data);
    }
}
