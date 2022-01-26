package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;

import java.util.List;

import static api.configuration.DataConfig.adElectricAppliancesExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;

public class CM_API_Ads_InsertElectricAppliances {
    CM_CP_API_AcceptAd cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();

    public static CM_API_Ads_InsertElectricAppliances cmAPIAdInsert;

    public static CM_API_Ads_InsertElectricAppliances instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertElectricAppliances();

        return cmAPIAdInsert;
    }

    public String insertNewAdCooler() {
        return insertNewAdCooler("");
    }

    public String insertNewAdCooler(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Cooler");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Cooler(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCooler_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Cooler");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad

        insertNewAd(data);
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Cooler(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }



    public String insertNewAdRefrigerator() {
        return insertNewAdRefrigerator("");
    }

    public String insertNewAdRefrigerator(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Refrigerator");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Refrigerator(dataKeys, dataValues);
                break;
        }
        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdRefrigerator_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Refrigerator");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Refrigerator(dataKeys, dataValues);
                break;
        }
        // Return new ad_id
        return tempAdID;
    }


    public String insertNewAdWashing_Machine() {
        return insertNewAdWashing_Machine("");
    }

    public String insertNewAdWashing_Machine(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Washing_Machine");

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
                cm_cp_api_acceptAd.acceptAd_Washing_Machine(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }

    public String insertNewAdWashing_Machine_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adElectricAppliancesExcelFile, "Washing_Machine");

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
                cm_cp_api_acceptAd.acceptAd_Washing_Machine(dataKeys, dataValues);
                break;
        }
        return tempAdID;
    }
}
