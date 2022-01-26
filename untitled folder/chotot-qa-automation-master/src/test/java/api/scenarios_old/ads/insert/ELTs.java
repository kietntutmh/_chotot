package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import api.configuration.DataConfig;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adELTExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class ELTs extends BaseAPITest {
    @Test(description = "Insert Ad - ELT, Verify insert Phone ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdPhone() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> phoneDataKeys = excelDataProvider.getRowData(1);
        List<String> phoneDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(phoneDataKeys, phoneDataValues);

        // Post phone data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Phone ad successfully for Shop, Tri Nguyen , MABU")
    public void verifyInsertAdPhoneShop() {
        // Setup data
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> phoneDataKeys = excelDataProvider.getRowData(5);
        List<String> phoneDataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(phoneDataKeys, phoneDataValues);

        // Post phone data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Phone ad successfully for ChoTot, Tri Nguyen, MABU")
    public void verifyInsertAdPhoneChotot() {
        // Setup data
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert Laptop ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdLaptop() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> laptopDataKeys = excelDataProvider.getRowData(1);
        List<String> laptopDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(laptopDataKeys, laptopDataValues);

        // Post ad laptop data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Laptop ad successfully for Shop, Tri Nguyen, MABU")
    public void verifyInsertAdLaptopShop() {
        // Setup data
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Laptop ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInserAdLaptopChotot() {
        // Setup data
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert Tablet ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdTablet() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Tablet ad successfully for Shop, Tri Nguyen, MABU")
    public void verifyInsertAdTabletShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Tablet ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdTabletChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Tablet data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert PC ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdPC() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Test(
            description = "Insert Ad - ELT, Verify insert PC ad successfully for Shop , Tri Nguyen, MABU")
    public void verifyInsertAdPCShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excelh
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert PC ad successfully for Chotot , Tri Nguyen, MABU")
    public void verifyInsertAdPCChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excelh
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert Camera ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdCamera() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Camera ad successfully for Shop, Tri Nguyen, MABU")
    public void verifyInsertAdCameraShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Camera ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdCameraChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Camera data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert Sound ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdSound() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Sound ad successfully for Shop, Tri Nguyen, MABU")
    public void verifyInsertAdSoundShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Sound ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdSoundChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Sound data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert Wearable ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdWearable() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Wearable ad successfully for Shop, Tri Nguyen, MABU")
    public void verifyInsertAdWearableShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Wearable ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdWearableChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Wearable data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - ELT, Verify insert Accessories ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdAccessoriesELT() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Accessories ad successfully for Shop, Tri Nguyen, MABU")
    public void verifyInsertAdAccessoriesShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert Accessories ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdAccessoriesChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad Accessories data
        insertNewAd(data);
    }

    @Test(
            description = "Insert Ad - ELT, Verify insert PC Component ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdPC_Component() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert PC Component ad successfully for Shop , Tri Nguyen, MABU")
    public void verifyInsertAdPC_ComponentShop() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }

    @Test(
            description =
                    "Insert Ad - ELT, Verify insert PC Component ad successfully for Chotot, Tri Nguyen, MABU")
    public void verifyInsertAdPC_ComponentChotot() {
        // Setup account
        DataConfig.setTempAccountAPIAndGetToken(9);

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad PC_Component data
        insertNewAd(data);
    }
}
