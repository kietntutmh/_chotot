package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.List;

import static api.configuration.DataConfig.adFashionExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class CM_API_Ads_InsertFashion {

    private final CM_CP_API_AcceptAd cm_cp_api_acceptAd;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAd;

    public CM_API_Ads_InsertFashion() {
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAd = new CM_CP_API_RejectAd();
        ExcelProvider excelDataProvider = new ExcelProvider();
    }

    public static CM_API_Ads_InsertFashion cmAPIAdInsert;
    public static CM_API_Ads_InsertFashion instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertFashion();

        return cmAPIAdInsert;
    }

    @Deprecated
    public String insertNewAdClothes(boolean isUsingNewUser) {
        return insertNewAdClothes(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdClothes(boolean isUsingNewUser, String cpAction) {
        return insertNewAdClothes(cpAction);
    }

    public String insertNewAdClothes() {
        return insertNewAdClothes("no action");
    }

    public String insertNewAdClothes(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdClothes_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

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
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdClothesPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdClothesBuy(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(9);
        List<String> dataValues = excelDataProvider.getRowData(10);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdClothesPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Clothes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }




    public String insertNewAdWatch() {
        return insertNewAdWatch("No Action");
    }

    @Deprecated
    public String insertNewAdWatch(boolean isUsingNewUser) {
        return insertNewAdWatch("no action");
    }

    @Deprecated
    public String insertNewAdWatch(boolean isUsingNewUser, String cpAction) {
        return insertNewAdWatch(cpAction);
    }

    public String insertNewAdWatch(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Watch");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewWatchAd_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Watch");

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
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdWatchPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Watch");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdWatchPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Watch");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }




    public String insertNewAdShoes() {
        return insertNewAdShoes("No Action");
    }

    @Deprecated
    public String insertNewAdShoes(boolean isUsingNewUser) {
        return insertNewAdShoes("no action");
    }

    @Deprecated
    public String insertNewAdShoes(boolean isUsingNewUser, String cpAction) {
        return insertNewAdShoes(cpAction);
    }

    public String insertNewAdShoes(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Shoes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdShoes_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Shoes");

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
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdShoesPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Shoes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdShoesPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Shoes");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }




    public String insertNewAdHandbag() {
        return insertNewAdHandbag("No Action");
    }

    @Deprecated
    public String insertNewAdHandbag(boolean isUsingNewUser) {
        return insertNewAdHandbag("no action");
    }

    @Deprecated
    public String insertNewAdHandbag(boolean isUsingNewUser, String cpAction) {
        return insertNewAdHandbag(cpAction);
    }

    public String insertNewAdHandbag(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Handbag");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdHandbag_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Handbag");

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
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdHandbagPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Handbag");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdHandbagPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Handbag");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }





    public String insertNewAdPerfume() {
        return insertNewAdPerfume("No action");
    }

    @Deprecated
    public String insertNewAdPerfume(boolean isUsingNewUser) {
        return insertNewAdPerfume("no action");
    }

    @Deprecated
    public String insertNewAdPerfume(boolean isUsingNewUser, String cpAction) {
        return insertNewAdPerfume(cpAction);
    }

    public String insertNewAdPerfume(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Perfume");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPerfume_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Perfume");

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
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPerfumePro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Perfume");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPerfumePro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Perfume");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }



    public String insertNewAdAccessories() {
        return insertNewAdAccessories("No Action");
    }

    @Deprecated
    public String insertNewAdAccessories(boolean isUsingNewUser) {
        return insertNewAdAccessories("No Action");
    }

    @Deprecated
    public String insertNewAdAccessories(boolean isUsingNewUser, String cpAction) {
        return insertNewAdAccessories(cpAction);
    }

    public String insertNewAdAccessories(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdAccessories_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

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
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdAccessoriesPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdAccessoriesPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }



    //Chat version 2
    public String insertNewAdAccessories_ChatV2(String token) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adFashionExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data, token);

        cm_cp_api_acceptAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
        // Return new ad_id
        return tempAdID;
    }
}
