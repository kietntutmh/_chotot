package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;

import java.util.List;

import static api.configuration.DataConfig.adPTYExcelFile;
import static api.configuration.DataConfig.setTempAccountAPIAndGetToken;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class PTY extends BaseAPITest {

    public void verifyInsertAdApartment() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(1);
        List<String> apartmentDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    public void verifyInsertAdApartmentToShop() {
        // Set to account ha shops
        setTempAccountAPIAndGetToken(3);

        // Setup test datas
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(6);
        List<String> apartmentDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    public void verifyInsertAdApartmentShopToChotot() {
        // Set to account had shops
        setTempAccountAPIAndGetToken(3);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        List<String> apartmentDataKeys = excelDataProvider.getRowData(11);
        List<String> apartmentDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(apartmentDataKeys, apartmentDataValues);

        // Post ad apartment data
        insertNewAd(data);
    }

    public void verifyInsertAdHouse() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        List<String> houseDataKeys = excelDataProvider.getRowData(1);
        List<String> houseDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(houseDataKeys, houseDataValues);

        // Post ad house data
        insertNewAd(data);
    }

    public void verifyInsertAdHouseToShop() {
        setTempAccountAPIAndGetToken(3);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        List<String> houseDataKeys = excelDataProvider.getRowData(6);
        List<String> houseDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(houseDataKeys, houseDataValues);

        // Post ad house data
        insertNewAd(data);
    }

    public void verifyInsertAdHouseShopToChotot() {
        setTempAccountAPIAndGetToken(3);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        List<String> houseDataKeys = excelDataProvider.getRowData(11);
        List<String> houseDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(houseDataKeys, houseDataValues);

        // Post ad house data
        insertNewAd(data);
    }

    public void verifyInsertAdLand() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(1);
        List<String> landDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    public void verifyInsertAdLandToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(6);
        List<String> landDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    public void verifyInsertAdLandShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        List<String> landDataKeys = excelDataProvider.getRowData(11);
        List<String> landDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(landDataKeys, landDataValues);

        // Post ad land data
        insertNewAd(data);
    }

    public void verifyInsertAdOffice() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(1);
        List<String> officeDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    public void verifyInsertAdOfficeToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(6);
        List<String> officeDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    public void verifyInsertAdOfficeShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        List<String> officeDataKeys = excelDataProvider.getRowData(11);
        List<String> officeDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(officeDataKeys, officeDataValues);

        // Post ad office data
        insertNewAd(data);
    }

    public void verifyInsertAdRoomForLease() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomeDataKeys = excelDataProvider.getRowData(1);
        List<String> roomDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomeDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }

    public void verifyInsertAdRoomForLeaseToShop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomeDataKeys = excelDataProvider.getRowData(6);
        List<String> roomDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomeDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }

    public void verifyInsertAdRoomForLeaseShopToChotot() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        List<String> roomDataKeys = excelDataProvider.getRowData(11);
        List<String> roomDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(roomDataKeys, roomDataValues);

        // Post ad room data
        insertNewAd(data);
    }
}
