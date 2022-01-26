package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.List;

import static api.configuration.DataConfig.adEntertainmentExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class CM_API_Ads_InsertEntertainment {

    private final CM_CP_API_AcceptAd cm_cp_api_acceptAd;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAd;
    private ExcelProvider excelDataProvider;

    public CM_API_Ads_InsertEntertainment() {
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
    }

    public static CM_API_Ads_InsertEntertainment cmAPIAdInsert;
    public static CM_API_Ads_InsertEntertainment instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertEntertainment();

        return cmAPIAdInsert;
    }

    // Instrument
    public String insertNewAdInstrument() {
        return insertNewAdInstrument("No Action");
    }

    public String insertNewAdInstrument(boolean isUsingNewUser) {
        return insertNewAdInstrument("No Action");
    }

    public String insertNewAdInstrument(boolean isUsingNewUser, String cpAction) {
        return insertNewAdInstrument(cpAction);
    }

    public String insertNewAdInstrument(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Instrument");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdInstrument_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Instrument");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdInstrumentBuy(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Instrument");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }



    public String insertNewAdSport() {
        return insertNewAdSport(false);
    }

    public String insertNewAdSport(boolean isUsingNewUser) {

        return insertNewAdSport(isUsingNewUser, "no action");
    }

    public String insertNewAdSport(String cpAction) {

        return insertNewAdSport(false, cpAction);
    }

    public String insertNewAdSport(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Sport");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdSport_NoUploadNewImage(String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Sport");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdSportBuy(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Sport");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }




    public String insertNewAdBook() {
        return insertNewAdBook(false);
    }

    public String insertNewAdBook(boolean isUsingNewUser) {

        return insertNewAdBook(isUsingNewUser, "no action");
    }

    public String insertNewAdBook(String cpAction) {

        return insertNewAdBook(false, cpAction);
    }


    public String insertNewAdBook(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Book");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdBook_NoUploadNewImage(String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Book");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdBookBuy(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Book");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }





    public String insertNewAdCollectibles() {
        return insertNewAdCollectibles(false);
    }

    public String insertNewAdCollectibles(boolean isUsingNewUser) {
        return insertNewAdCollectibles(isUsingNewUser, "no action");
    }

    public String insertNewAdCollectibles(String cpAction) {
        return insertNewAdCollectibles(false, cpAction);
    }

    public String insertNewAdCollectibles(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCollectiblesBuy(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCollectibles_NoUploadNewImage(String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCollectiblesBuy(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Collectibles");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }




    // Gaming
    public String insertNewAdGaming() {
        return insertNewAdGaming(false);
    }

    public String insertNewAdGaming(boolean isUsingNewUser) {

        return insertNewAdGaming(isUsingNewUser, "no action");
    }

    public String insertNewAdGaming(String cpAction) {

        return insertNewAdGaming(false, cpAction);
    }

    public String insertNewAdGaming(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Gaming");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdGaming_NoUploadNewImage(String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Gaming");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdGamingBuy(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Gaming");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }





    public String insertNewAdHobby() {
        return insertNewAdHobby(false);
    }

    public String insertNewAdHobby(boolean isUsingNewUser) {
        return insertNewAdHobby(isUsingNewUser, "no action");
    }

    public String insertNewAdHobby(String cpAction) {
        return insertNewAdHobby(false, cpAction);
    }


    public String insertNewAdHobby(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Hobby");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdHobby_NoUploadNewImage(String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Hobby");

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
                cm_cp_api_acceptAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdHobbyBuy(boolean isUsingNewUser, String cpAction) {
        // Setup test data
        //
        excelDataProvider.getExcelFileSheet(adEntertainmentExcelFile, "Hobby");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }
}
