package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;

import java.util.List;

import static api.configuration.DataConfig.adRequisiteExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;

public class CM_API_Ads_InsertIndustrial {
    private final CM_CP_API_AcceptAd cm_cp_api_acceptAd;

    public CM_API_Ads_InsertIndustrial() {
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
    }

    public static CM_API_Ads_InsertIndustrial cmAPIAdInsert;
    public static CM_API_Ads_InsertIndustrial instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertIndustrial();

        return cmAPIAdInsert;
    }

    public String insertNewSpecializedItemAd(boolean isUsingNewUser) {
        return insertNewSpecializedItemAd();
    }

    public String insertNewAdSpecializedItem_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "SpecializedItem");

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
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_SpecializedItem(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdSpecializedItem(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "SpecializedItem");

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
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_SpecializedItem(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewSpecializedItemAd() {
        return insertNewAdSpecializedItem("");
    }



    @Deprecated
    public String insertNewRequisiteAd(boolean isUsingNewUser) {
        return insertNewRequisiteAd();
    }

    public String insertNewRequisiteAd() {
        return insertNewRequisiteAd("");
    }

    public String insertNewRequisiteAd(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

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
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_SpecializedItem(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewRequisiteAd_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

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
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_SpecializedItem(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }




    public String insertNewAdOfficeEquipment(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

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
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_OfficeEquipment(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdOfficeEquipment_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adRequisiteExcelFile, "OfficeEquipment");

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
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_OfficeEquipment(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }
}
