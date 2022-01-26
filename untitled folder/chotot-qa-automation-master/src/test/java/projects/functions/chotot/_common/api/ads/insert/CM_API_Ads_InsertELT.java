package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adELTExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

//REFACTOR:
//Phone: Private
public class CM_API_Ads_InsertELT {
    public static CM_API_Ads_InsertELT cmAPIAdInsert;
    private final CM_CP_API_AcceptAd cm_api_acceptAdELT;
    private final CM_CP_API_RejectAd cm_api_rejectAdELT;
    private final ExcelProvider excelDataProvider;
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_InsertELT() {
        cm_api_acceptAdELT = new CM_CP_API_AcceptAd();
        cm_api_rejectAdELT = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    public static CM_API_Ads_InsertELT instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertELT();

        return cmAPIAdInsert;
    }

    @Deprecated
    public String insertNewAdLaptop(boolean isUsingNewUser) {
        return insertNewAdLaptop(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdLaptop(boolean isUsingNewUser, String cpAction) {
        return insertNewAdLaptop(cpAction);
    }

    public String insertNewAdLaptop() {
        return insertNewAdLaptop("no action");
    }

    public String insertNewAdLaptop(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdLaptopPro(boolean isUsingNewUser) {
        return insertNewAdLaptopPro(isUsingNewUser, "no action");
    }

    public String insertNewAdLaptopPro() {
        return insertNewAdLaptopPro("No Action");
    }

    @Deprecated
    public String insertNewAdLaptopPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdLaptopPro(cpAction);
    }

    public String insertNewAdLaptopPro(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(17);
        List<String> dataValues = excelDataProvider.getRowData(18);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdLaptopBuy(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLaptopBuyPro(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(21);
        List<String> dataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String inserAdLaptopChotot(boolean isUsingNewUser) {
        return inserAdLaptopChotot();
    }

    public String inserAdLaptopChotot() {
        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdPhone() {
        return insertNewAdPhone("no action");
    }

    //SUB-CORE
    public String insertNewAdPhone(String cpAction) {
        return insertNewAdPhoneCORE(cpAction, 1, 2);
    }

    private String insertNewAdPhoneCORE(String cpAction, int keyRowIndex, int valueRowIndex) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(keyRowIndex);
        List<String> dataValues = excelDataProvider.getRowData(valueRowIndex);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPhoneAd_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPhoneShop(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(5);
        dataValues = excelDataProvider.getRowData(6);

        //Set: to Chotot or to Shop Dashboard
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);

        // Get JSON String data
        String data = null;
        if (isUsingNewImage)
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        else
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }


    public String insertNewAdTabletAd_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdLaptop_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Laptop");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPC_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCamera_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdTVSound_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdWearable_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Wearable(dataKeys, dataValues);
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
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPCComponent_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdPhoneBuy(boolean isUsingNewUser) {
        return insertNewAdPhoneBuy();
    }

    public String insertNewAdPhoneBuy() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(22);
        List<String> dataValues = excelDataProvider.getRowData(23);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdPhonePro(boolean isUsingNewUser) {
        return insertNewAdPhonePro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdPhonePro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdPhonePro(cpAction);
    }

    public String insertNewAdPhonePro() {
        return insertNewAdPhonePro("no action");
    }

    public String insertNewAdPhonePro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad phone data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPhoneBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdPhoneChotot(boolean isUsingNewUser) {
        return insertAdPhoneChotot();
    }

    public String insertAdPhoneChotot() {
        // Set access token
        setAccessToken();

        // Set up data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Phone");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(5);
        List<String> dataValues = excelDataProvider.getRowData(6);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTablet(boolean isUsingNewUser) {
        return insertNewAdTablet(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdTablet(boolean isUsingNewUser, String cpAction) {
        return insertNewAdTablet(cpAction);
    }

    public String insertNewAdTablet() {
        return insertNewAdTablet("no action");
    }

    public String insertNewAdTablet(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTabletPro() {
        return insertNewAdTabletPro("No action");
    }

    public String insertNewAdTabletPro(boolean isUsingNewUser) {
        return insertNewAdTabletPro("no action");
    }

    @Deprecated
    public String insertNewAdTabletPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdTabletPro(cpAction);
    }

    public String insertNewAdTabletPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdTabletBuyPro() {
        return insertNewAdTabletBuyPro(false);
    }

    @Deprecated
    public String insertNewAdTabletBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad phone data

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdTabletChotot(boolean isUsingNewUser) {
        return insertAdTabletChotot();
    }

    public String insertAdTabletChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Tablet");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdPC(boolean isUsingNewUser) {
        return insertNewAdPC(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdPC(boolean isUsingNewUser, String cpAction) {
        return insertNewAdPC(cpAction);
    }

    public String insertNewAdPC() {
        return insertNewAdPC("no action");
    }

    public String insertNewAdPC(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPCPro() {
        return insertNewAdPCPro("No action");
    }

    @Deprecated
    public String insertNewAdPCPro(boolean isUsingNewUser) {
        return insertNewAdPCPro("no action");
    }

    @Deprecated
    public String insertNewAdPCPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdPCPro(cpAction);
    }

    public String insertNewAdPCPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPCBuyPro() {
        return insertNewAdPCBuyPro(false);
    }

    @Deprecated
    public String insertNewAdPCBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdPCChotot(boolean isUsingNewUser) {
        return insertAdPCChotot();
    }

    public String insertAdPCChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC");

        // Get list data from excelh
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdCamera(boolean isUsingNewUser) {
        return insertNewAdCamera(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdCamera(boolean isUsingNewUser, String cpAction) {
        return insertNewAdCamera(cpAction);
    }

    public String insertNewAdCamera() {
        return insertNewAdCamera("no action");
    }

    public String insertNewAdCamera(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCameraPro() {
        return insertNewAdCameraPro("No action");
    }

    @Deprecated
    public String insertNewAdCameraPro(boolean isUsingNewUser) {
        return insertNewAdCameraPro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdCameraPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdCameraPro(cpAction);
    }

    public String insertNewAdCameraPro(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCameraBuyPro() {
        return insertNewAdCameraBuyPro(false);
    }

    public String insertNewAdCameraBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad data

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdCameraChotot(boolean isUsingNewUser) {
        return insertAdCameraChotot();
    }

    public String insertAdCameraChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Camera");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdSound(boolean isUsingNewUser) {
        return insertNewAdSound(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdSound(boolean isUsingNewUser, String cpAction) {
        return insertNewAdSound(cpAction);
    }

    public String insertNewAdSound() {
        return insertNewAdSound("no action");
    }

    public String insertNewAdSound(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }


    public String insertNewAdSoundPro() {
        return insertNewAdSoundPro("No action");
    }

    @Deprecated
    public String insertNewAdSoundPro(boolean isUsingNewUser) {
        return insertNewAdSoundPro("no action");
    }

    @Deprecated
    public String insertNewAdSoundPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdSoundPro(cpAction);
    }

    public String insertNewAdSoundPro(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdSoundBuyPro() {
        return insertNewAdSoundBuyPro(false);
    }

    @Deprecated
    public String insertNewAdSoundBuyPro(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(23);
        List<String> dataValues = excelDataProvider.getRowData(24);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        return insertNewAd(data);
    }

    public String insertNewAdSoundBuy() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(18);
        List<String> dataValues = excelDataProvider.getRowData(19);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdSoundChotot(boolean isUsingNewUser) {
        return insertAdSoundChotot();
    }

    public String insertAdSoundChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Sound");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdWearable(boolean isUsingNewUser) {
        return insertNewAdWearable(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdWearable(boolean isUsingNewUser, String cpAction) {
        return insertNewAdWearable(cpAction);
    }

    public String insertNewAdWearable() {
        return insertNewAdWearable("no action");
    }

    public String insertNewAdWearable(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdWearablePro() {
        return insertNewAdWearablePro("No Action");
    }

    @Deprecated
    public String insertNewAdWearablePro(boolean isUsingNewUser) {
        return insertNewAdWearablePro("no action");
    }

    @Deprecated
    public String insertNewAdWearablePro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdWearablePro(cpAction);
    }

    public String insertNewAdWearablePro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdWearableBuyPro() {
        return insertNewAdWearableBuyPro(false);
    }

    @Deprecated
    public String insertNewAdWearableBuyPro(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdWearableChotot(boolean isUsingNewUser) {
        return insertAdWearableChotot();
    }

    public String insertAdWearableChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Wearable");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdAccessories(boolean isUsingNewUser) {
        return insertNewAdAccessories(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdAccessories(boolean isUsingNewUser, String cpAction) {
        return insertNewAdAccessories(cpAction);
    }

    public String insertNewAdAccessories() {
        return insertNewAdAccessories("no action");
    }

    public String insertNewAdAccessories(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdAccessoriesPro() {
        return insertNewAdAccessoriesPro("No action");
    }

    @Deprecated
    public String insertNewAdAccessoriesPro(boolean isUsingNewUser) {
        return insertNewAdAccessoriesPro("no action");
    }

    @Deprecated
    public String insertNewAdAccessoriesPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdAccessoriesPro(cpAction);
    }

    public String insertNewAdAccessoriesPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdAccessoriesBuyPro() {
        return insertNewAdAccessoriesBuyPro(false);
    }

    @Deprecated
    public String insertNewAdAccessoriesBuyPro(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdAccessoriesChotot(boolean isUsingNewUser) {
        return insertAdAccessoriesChotot();
    }

    public String insertAdAccessoriesChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "Accessories");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdPC_Component(boolean isUsingNewUser) {
        return insertNewAdPC_Component(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdPC_Component(boolean isUsingNewUser, String cpAction) {
        return insertNewAdPC_Component(cpAction);
    }

    public String insertNewAdPC_Component() {
        return insertNewAdPC_Component(false, "no action");
    }

    public String insertNewAdPC_Component(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPC_ComponentPro() {
        return insertNewAdPC_ComponentPro("No action");
    }

    @Deprecated
    public String insertNewAdPC_ComponentPro(boolean isUsingNewUser) {
        return insertNewAdPC_ComponentPro("no action");
    }

    @Deprecated
    public String insertNewAdPC_ComponentPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdPC_ComponentPro(cpAction);
    }

    public String insertNewAdPC_ComponentPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptAdELT.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Accept
                cm_api_acceptAdELT.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectAdELT.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order

                paymentOrderWithDongTot();
                // Reject
                cm_api_rejectAdELT.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdPC_ComponentBuyPro() {
        return insertNewAdPC_ComponentBuyPro(false);
    }

    @Deprecated
    public String insertNewAdPC_ComponentBuyPro(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(12);
        List<String> dataValues = excelDataProvider.getRowData(13);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertAdPC_ComponentChotot(boolean isUsingNewUser) {
        return insertAdPC_ComponentChotot();
    }

    public String insertAdPC_ComponentChotot() {
        // Set access token
        setAccessToken();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adELTExcelFile, "PC_Component");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(8);
        List<String> dataValues = excelDataProvider.getRowData(9);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }
}
