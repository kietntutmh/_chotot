package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.List;

import static api.configuration.DataConfig.adPetExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;

public class CM_API_Ads_InsertPets {
    private final CM_CP_API_AcceptAd cm_cp_api_acceptAd;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAd;
    private final ExcelProvider excelDataProvider;

    public CM_API_Ads_InsertPets() {
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
    }

    public static CM_API_Ads_InsertPets cmAPIAdInsert;
    public static CM_API_Ads_InsertPets instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertPets();

        return cmAPIAdInsert;
    }

    public String insertNewChickenAd(boolean isUsingNewUser) {
        return insertNewChickenAd();
    }

    public String insertNewChickenAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Chicken");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
    public String insertNewAd_Chicken_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Chicken");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_Chicken(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAd.rejectAd_Job(dataKeys, dataValues);
                break;
        }

        return tempAdID;
    }

    public String insertNewDogAd(boolean isUsingNewUser) {
        return insertNewDogAd();
    }

    public String insertNewDogAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
    public String insertNewAd_Dog_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        insertNewAd(data);
        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_Dog(dataKeys, dataValues);
                break;


        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewBirdAd(boolean isUsingNewUser) {
        return insertNewBirdAd();
    }

    public String insertNewBirdAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
    public String insertNewAd_Bird_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);
        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_Bird(dataKeys, dataValues);
                break;
        }
        // Return new ad_id
        return tempAdID;
    }

    public String insertNewCatAd(boolean isUsingNewUser) {
        return insertNewCatAd();
    }

    public String insertNewCatAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
    public String insertNewAd_Cat_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_Cat(dataKeys, dataValues);
                break;
        }
        // Return new ad_id
        return tempAdID;
    }

    public String insertNewOtherAd(boolean isUsingNewUser) {
        return insertNewOtherAd();
    }

    public String insertNewOtherAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "other");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
    public String insertNewAd_OtherPet_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "other");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_OtherPets(dataKeys, dataValues);
                break;
        }
        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAccessoriesAd(boolean isUsingNewUser) {
        return insertNewAccessoriesAd();
    }

    public String insertNewAccessoriesAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "accessory");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
    public String insertNewAdAccessories_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "accessory");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_Accessories(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }
    public String insertNewAdFood_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "accessory");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Pet_Accessories(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }
}
