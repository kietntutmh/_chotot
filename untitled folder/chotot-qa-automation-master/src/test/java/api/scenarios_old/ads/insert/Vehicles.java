package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adVehicleExcelFile;
import static api.configuration.DataConfig.setTempAccountAPIAndGetToken;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Vehicles extends BaseAPITest {

    public void verifyInsertAdCar() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(1);
        List<String> carDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    public void verifyInsertAdCarToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(6);
        List<String> carDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Car ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertAdCarToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(11);
        List<String> carDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data
        insertNewAd(data);
    }

    @Test(
            description = "Insert Ad - Vehicle, Verify insert Motorbike ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdMotorbike() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(1);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Motorbike ad to Chotot successfully, Quoc Tran, MABU")
    public void verifyInsertAdMotorbikeToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(6);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Motorbike ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertAdMotorbikeToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(11);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad motorbike data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Vehicle, Verify insert Truck ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdTruck() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Truck ad to Chotot successfully, Quoc Tran, MABU")
    public void verifyInsertAdTruckToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Truck ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertAdTruckToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad truck data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Electric_Vehicle ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdElectricVehicle() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Electric_Vehicle ad to Chotot successfully, Quoc Tran, MABU")
    public void verifyInsertAdElectricVehicleToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Electric_Vehicle ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertAdElectricVehicleToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Electric_Vehicle data
        insertNewAd(data);
    }

    @Test(
            description = "Insert Ad - Vehicle, Verify insert Bicycles ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdBicycles() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Bicycles ad to Chotot successfully, Quoc Tran, MABU")
    public void verifyInsertAdBicyclesToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Bicycles ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertAdBicyclesToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Bicycles data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Vehicles_Parts ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdVehiclesPart() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Vehicles_Parts ad to Chotot successfully, Quoc Tran, MABU")
    public void verifyInsertAdVehiclesPartToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Vehicles_Parts ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertAdVehiclesPartToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Vehicles_Parts data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Other_Vehicles ad successfully, Quoc Tran, MABU")
    public void verifyInsertOtherVehicle() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Other_Vehicles ad to Chotot successfully, Quoc Tran, MABU")
    public void verifyInsertOtherVehicleToChotot() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - Vehicle, Verify insert Other_Vehicles ad to Shop successfully, Quoc Tran, MABU")
    public void verifyInsertOtherVehicleToShop() {
        // Set to account has shops
        setTempAccountAPIAndGetToken(10);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Other_Vehicles data
        insertNewAd(data);
    }
}
