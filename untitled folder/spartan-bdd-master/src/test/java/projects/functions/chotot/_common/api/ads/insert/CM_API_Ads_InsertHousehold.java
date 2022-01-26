package projects.functions.chotot._common.api.ads.insert;

import api.base.BaseAPITest;
import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;

import java.util.List;

import static api.configuration.DataConfig.adHousehold_Furniture_PlantExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;

public class CM_API_Ads_InsertHousehold extends BaseAPITest {
    CM_CP_API_AcceptAd cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();

    public static CM_API_Ads_InsertHousehold cmAPIAdInsert;

    public static CM_API_Ads_InsertHousehold instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertHousehold();

        return cmAPIAdInsert;
    }



    public String insertNewAdKitchen_Appliance() {
        return insertNewAdKitchen_Appliance("");
    }

    public String insertNewAdKitchen_Appliance(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Appliance");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Kitchen_Appliance(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdKitchen_Appliance_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Kitchen_Appliance");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Kitchen_Appliance(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }




    public String insertNewAdHousehold_Dinnerware() {
        return insertNewAdHousehold_Dinnerware("");
    }

    public String insertNewAdHousehold_Dinnerware(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Kitchen_Utensil_Dinnerware");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Kitchen_Utensil_Dinnerware(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdHousehold_Dinnerware_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Kitchen_Utensil_Dinnerware");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Kitchen_Utensil_Dinnerware(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }




    public String insertNewAdHousehold_Bed() {
        return insertNewAdHousehold_Bed("");
    }

    public String insertNewAdHousehold_Bed(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bed_Bedding");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Bed_Bedding(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdHousehold_Bed_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bed_Bedding");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Bed_Bedding(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }



    public String insertNewAdBathware() {
        return insertNewAdBathware("");
    }

    public String insertNewAdBathware(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bathware");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Bathware(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdBathware_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Bathware");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Bathware(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }




    public String insertNewAdFan() {
       return insertNewAdFan("");
    }

    public String insertNewAdFan(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Fan");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fan(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdFan_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Fan");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fan(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }



    public String insertNewAdLighting() {
        return insertNewAdLighting("");
    }

    public String insertNewAdLighting(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Lighting");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Lighting(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdLighting_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adHousehold_Furniture_PlantExcelFile, "Lighting");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Lighting(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }



    public String insertNewAdHousehold_Table_Chair() {
        return insertNewAdHousehold_Table_Chair("");
    }

    public String insertNewAdHousehold_Table_Chair(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Table_Chair");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Household_Table_Chair(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdHousehold_Table_Chair_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Table_Chair");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Household_Table_Chair(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }



    public String insertNewAdHousehold_Drawer_Shelf() {
        return insertNewAdHousehold_Drawer_Shelf("");
    }

    public String insertNewAdHousehold_Drawer_Shelf(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Drawer_Shelf");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Household_Drawer_Shelf(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdHousehold_Drawer_Shelf_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Household_Drawer_Shelf");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Household_Drawer_Shelf(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }



    public String insertNewAdOrnamental_Plant_Decoration() {
        return insertNewAdOrnamental_Plant_Decoration("");
    }

    public String insertNewAdOrnamental_Plant_Decoration(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Ornamental_Plant_Decoration");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Ornamental_Plant_Decoration(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdOrnamental_Plant_Decoration_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Ornamental_Plant_Decoration");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Ornamental_Plant_Decoration(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }



    public String insertNewAdOther_Household_Items() {
        return insertNewAdOther_Household_Items("");
    }

    public String insertNewAdOther_Household_Items(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Other_Household_Items");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAdOther_Household_Items(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdOther_Household_Items_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(
                adHousehold_Furniture_PlantExcelFile, "Other_Household_Items");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAdOther_Household_Items(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }
}
