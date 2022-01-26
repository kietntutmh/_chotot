package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.List;

import static api.configuration.DataConfig.adServiceExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;
import static projects.functions.chotot.payment.PayOrder_API_Functions.*;
import static projects.functions.chotot.pos.POS_Functions.*;

public class CM_API_Ads_InsertServices {

    private final CM_CP_API_AcceptAd cm_cp_api_acceptAd;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAd;

    public CM_API_Ads_InsertServices() {
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAd = new CM_CP_API_RejectAd();
        ExcelProvider excelDataProvider = new ExcelProvider();
    }

    public static CM_API_Ads_InsertServices cmAPIAdInsert;

    public static CM_API_Ads_InsertServices instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertServices();

        return cmAPIAdInsert;
    }

    public String insertNewServiceAd() {
        return insertNewServiceAd("no action");
    }

    @Deprecated
    public String insertNewServiceAd(boolean isUsingNewUser) {

        return insertNewServiceAd("no action");
    }

    @Deprecated
    public String insertNewServiceAd(boolean isUsingNewUser, String cpAction) {
        return insertNewServiceAd(cpAction);
    }

    public String insertNewServiceAd(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Service");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        String adID = insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Services_Service(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Services_Service(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Services_Service(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Services_Service(dataKeys, dataValues);
                break;

            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewServiceAd_NoUploadNewImage() {
        return insertNewServiceAd_NoUploadNewImage("no action");
    }

    public String insertNewServiceAd_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Service");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        String adID = insertNewAd(data);

        switch (cpAction.toLowerCase().trim().replace(" ", "_")) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Services_Service(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Services_Service(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Services_Service(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Services_Service(dataKeys, dataValues);
                break;

            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }


    public String insertNewTravelAd() {
        return insertNewTravelAd("No Action");
    }

    public String insertNewTravelAd(boolean isUsingNewUser) {

        return insertNewTravelAd("No Action");
    }

    public String insertNewTravelAd(boolean isUsingNewUser, String cpAction) {
        return insertNewTravelAd(cpAction);
    }

    public String insertNewTravelAd(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Travel");

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
                cm_cp_api_acceptAd.acceptAd_Services_Travel(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Services_Travel(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Services_Travel(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Services_Travel(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewTravelAd_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Travel");

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
                cm_cp_api_acceptAd.acceptAd_Services_Travel(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Services_Travel(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Services_Travel(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Services_Travel(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    //--------------- SUPPORT FOR AH: INSERT AD BUT NOT YET ACCEPT, PAY POS BUT BE REJECTED ---------------
    @Deprecated
    public String insertNewServiceAd_BuyPOS_BeRejected(String posName, boolean isUsingDT4B) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adServiceExcelFile, "Service");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        String adID = insertNewAd(data);

        switch (posName.toLowerCase().replace(" ","_")) {

            //--------------- SUPPORT ACCOUNT HIERARCHY: PAY POS BUT BE REJECTED ---------------
            case "bump":
                posBump_NotDevivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "bump_timer":
                posBumpTimer_NotDevivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "bump_3days":
                posBump3Days_NotDevivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "bump_7days":
                posBump7Days_NotDevivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "stickyad":
                posStickyAd_NotDelivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "adfeature_ribbon":
                posAdFeatureRibbon_NotDelivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "adfeature_price":
                posAdFeaturePrice_NotDelivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "adfeature_title":
                posAdFeatureTitle_NotDelivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;

            case "bundle":
                posBundle_NotDelivery(global_accessToken, adID);
                if (isUsingDT4B) {
                    paymentOrderDT4B();
                } else {
                    paymentOrder();
                }
                break;
            default:
                break;
        }

        cm_cp_api_rejectAd.rejectAd_Services_Service(dataKeys, dataValues);

        // Return new ad_id
        return tempAdID;
    }


}
