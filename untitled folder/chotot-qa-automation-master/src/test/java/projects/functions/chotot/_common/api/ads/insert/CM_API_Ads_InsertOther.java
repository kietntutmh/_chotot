package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;

import java.util.List;

import static api.configuration.DataConfig.adOtherExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class CM_API_Ads_InsertOther {
    public static CM_API_Ads_InsertOther cmAPIAdInsert;
    public static CM_API_Ads_InsertOther instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertOther();

        return cmAPIAdInsert;
    }
    public String insertNewOtherAd(boolean isUsingNewUser) {
        return insertNewOtherAd();
    }

    public String insertNewOtherAd() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Other");

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

    public String insertNewFoodAd(String cpAction) {
        return insertNewFoodAd(cpAction, "200");
    }

    public String insertNewFoodAd(String cpAction, String expectedCode) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Food");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_InsertAd_Food(dataKeys, dataValues);
        String adID = insertNewAd(data, global_accessToken, expectedCode);

        if (Integer.parseInt(expectedCode) < 300) {
            switch (cpAction.toLowerCase().trim()) {
                case "accept":
                    CM_CP_API_AcceptAd api_acceptAd = new CM_CP_API_AcceptAd();
                    api_acceptAd.acceptAd_Food(dataKeys, dataValues);
                    break;
                case "acceptpay":
                    break;
                case "reject":
                    break;
                case "rejectpay":
                    paymentOrderWithDongTot();
                    break;
                default:
                    break;
            }
        }
        return tempAdID;
    }

    public String insertNewAdFood_NoUploadNewImage( String cpAction){
        return insertNewAdFood_NoUploadNewImage(cpAction,"200");
    }
    String insertNewAdFood_NoUploadNewImage( String cpAction, String expectedCode) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Food");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        String adID = insertNewAd(data, global_accessToken, expectedCode);

        if (Integer.parseInt(expectedCode) < 300) {
            switch (cpAction.toLowerCase().trim()) {
                case "accept":
                    CM_CP_API_AcceptAd api_acceptAd = new CM_CP_API_AcceptAd();
                    api_acceptAd.acceptAd_Food(dataKeys, dataValues);
                    break;
                case "acceptpay":
                    break;
                case "reject":
                    break;
                case "rejectpay":
                    paymentOrderWithDongTot();
                    break;
                default:
                    break;
            }
        }
        return tempAdID;
    }

    public String insertNewFoodAdPro(String cpAction) {
        return insertNewFoodAdPro(cpAction, "200");
    }

    public String insertNewFoodAdPro(String cpAction, String expectedCode) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Food");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_InsertAd_Food(dataKeys, dataValues);
        String adID = insertNewAd(data, global_accessToken, expectedCode);

        if (Integer.parseInt(expectedCode) < 300) {
            switch (cpAction.toLowerCase().trim()) {
                case "accept":
                    CM_CP_API_AcceptAd api_acceptAd = new CM_CP_API_AcceptAd();
                    api_acceptAd.acceptAd_Food(dataKeys, dataValues);
                    break;
                case "acceptpay":
                    break;
                case "reject":
                    break;
                case "rejectpay":
                    paymentOrderWithDongTot();
                    break;
                default:
                    break;
            }
        }
        return tempAdID;
    }

    public String insertNewFoodAdC2CShop(String token, String cpAction) {
        return insertNewFoodAdC2CShop(token, cpAction, "200");
    }

    public String insertNewFoodAdC2CShop(String token, String cpAction, String expectedCode) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adOtherExcelFile, "Food");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_InsertAd_Food(dataKeys, dataValues);
        String adID = insertNewAd(data, token, expectedCode);

        if (Integer.parseInt(expectedCode) < 300) {
            switch (cpAction.toLowerCase().trim()) {
                case "accept":
                    CM_CP_API_AcceptAd api_acceptAd = new CM_CP_API_AcceptAd();
                    api_acceptAd.acceptAd_Food(dataKeys, dataValues);
                    break;
                case "acceptpay":
                    break;
                case "reject":
                    break;
                case "rejectpay":
                    paymentOrderWithDongTot();
                    break;
                default:
                    break;
            }
        }
        return tempAdID;
    }
}
