package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adVehicleExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetAccessToken.getAccessTokenOfExistingUser;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo;

public class CM_API_Ads_InsertVehicle {

    private final CM_CP_API_AcceptAd cm_cp_api_acceptAdCP;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAdCP;
    private static ExcelProvider excelDataProvider;
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_InsertVehicle() {
        cm_cp_api_acceptAdCP = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAdCP = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    public static CM_API_Ads_InsertVehicle cmAPIAdInsert;

    public static CM_API_Ads_InsertVehicle instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertVehicle();

        return cmAPIAdInsert;
    }

    @Deprecated
    public String insertNewAdCar(boolean isUsingNewUser) {
        return insertNewAdCar("no action");
    }

    @Deprecated
    public String insertNewAdCar(boolean isUsingNewUser, String cpAction) {
        return insertNewAdCar(cpAction);
    }

    public String insertNewAdCar() {
        return insertNewAdCar("no action");
    }

    public String insertNewAdCar(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdCar_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdMotorbike_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdTrucks_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdElectricVehicle_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdBicycles_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdOther_Vehicles_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdVehicles_Parts_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdCar_NeedBuyType(boolean isUsingNewUser) {
        return insertNewAdCar_NeedBuyType();
    }

    public String insertNewAdCar_NeedBuyType() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdCarChotot(boolean isUsingNewUser) {
        return insertNewAdCarChotot();
    }

    //Chợ Tốt, for Pro obligate.
    public String insertNewAdCarChotot() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> carDataKeys = excelDataProvider.getRowData(6);
        List<String> carDataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(carDataKeys, carDataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    //VUHOANG NEW GEN
    public String insertNewAdCarShop() {
        return insertNewAdCarShop("no action", true);
    }

    //VUHOANG NEW GEN
    public String insertNewAdCarShop(String cpAction, boolean postToChotot) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        //Set: to Chotot or to Shop Dashboard
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        // Post ad car data

        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
                break;
        }

        return tempAdID;
    }

    @Deprecated
    public String insertNewAdCarForPro(boolean isUsingNewUser) {
        return insertNewAdCarForPro("no action");
    }

    @Deprecated
    public String insertNewAdCarForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdCarForPro(cpAction);
    }

    //Bán chuyên
    public String insertNewAdCarForPro() {
        return insertNewAdCarForPro("no action");
    }

    public String insertNewAdCarForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Car");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(21);
        List<String> dataValues = excelDataProvider.getRowData(22);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Car(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Car(dataKeys, dataValues);
                break;
            default:
                break;
        }

        return tempAdID;
    }

    @Deprecated
    public String insertNewAdMotorbike(boolean isUsingNewUser) {
        return insertNewAdMotorbike("no action");
    }

    @Deprecated
    public String insertNewAdMotorbike(boolean isUsingNewUser, String cpAction) {
        return insertNewAdMotorbike(cpAction);
    }

    public String insertNewAdMotorbike() {
        return insertNewAdMotorbike("no action");
    }

    public String insertNewAdMotorbike(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdMotorbike_NeedBuyType(boolean isUsingNewUser) {
        return insertNewAdMotorbike_NeedBuyType();
    }

    public String insertNewAdMotorbike_NeedBuyType() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdMotorbikeChotot(boolean isUsingNewUser) {
        return insertNewAdMotorbikeChotot();
    }

    public String insertNewAdMotorbikeChotot() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> motorbikeDataKeys = excelDataProvider.getRowData(6);
        List<String> motorbikeDataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(motorbikeDataKeys, motorbikeDataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdMotorbikeShop() {
        return insertNewAdMotorbikeShop("no action", true, true);
    }

    //VUHOANG NEW GEN
    public String insertNewAdMotorbikeShop(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        //Set: to Chotot or to Shop Dashboard
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //VUHOANG DEBUG: Bug of data: remove param "carbrand" --> need to recheck
        try {
            dataValues.remove(dataKeys.indexOf("carbrand"));
            dataKeys.remove("carbrand");
        } catch (Exception e) {

        }

        // Get JSON String data
        String data = null;
        if (isUsingNewImage)
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        else
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        // Post ad car data

        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
                break;
        }

        return tempAdID;
    }


    public String insertNewAdMotorbikeForPro() {
        return insertNewAdMotorbikeForPro("No Action");
    }

    public String insertNewAdMotorbikeForPro(boolean isUsingNewUser) {
        return insertNewAdMotorbikeForPro("No Action");
    }

    public String insertNewAdMotorbikeForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdMotorbikeForPro(cpAction);
    }

    public String insertNewAdMotorbikeForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Motorbike");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(21);
        List<String> dataValues = excelDataProvider.getRowData(22);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Motorbike(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTrucks(boolean isUsingNewUser) {
        return insertNewAdTrucks("no action");
    }

    @Deprecated
    public String insertNewAdTrucks(boolean isUsingNewUser, String cpAction) {
        return insertNewAdTrucks(cpAction);
    }

    public String insertNewAdTrucks() {
        return insertNewAdTrucks("no action");
    }

    public String insertNewAdTrucks(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTrucks_NeedBuyType(boolean isUsingNewUser) {
        return insertNewAdTrucks_NeedBuyType();
    }

    public String insertNewAdTrucks_NeedBuyType() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTrucksChotot(boolean isUsingNewUser) {
        return insertNewAdTrucksChotot();
    }

    public String insertNewAdTrucksChotot() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTrucksShop(boolean isUsingNewUser) {
        return insertNewAdTrucksShop();
    }

    public String insertNewAdTrucksShop() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdTrucksForPro(boolean isUsingNewUser) {
        return insertNewAdTrucksForPro("no action");
    }

    @Deprecated
    public String insertNewAdTrucksForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdTrucksForPro(cpAction);
    }

    public String insertNewAdTrucksForPro() {
        return insertNewAdTrucksForPro("No action");
    }

    public String insertNewAdTrucksForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Trucks");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(21);
        List<String> dataValues = excelDataProvider.getRowData(22);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                topupDongTotWithMomo(tempUserPhone);
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Truck(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdElectric_Vehicle(boolean isUsingNewUser) {
        return insertNewAdElectric_Vehicle(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdElectric_Vehicle(boolean isUsingNewUser, String cpAction) {
        return insertNewAdElectric_Vehicle(cpAction);
    }

    public String insertNewAdElectric_Vehicle() {
        return insertNewAdElectric_Vehicle("No action");
    }

    public String insertNewAdElectric_Vehicle(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdElectric_Vehicle_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdElectric_VehicleForPro() {
        return insertNewAdElectric_VehicleForPro("No action");
    }

    @Deprecated
    public String insertNewAdElectric_VehicleForPro(boolean isUsingNewUser) {
        return insertNewAdElectric_VehicleForPro("no action");
    }

    @Deprecated
    public String insertNewAdElectric_VehicleForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdElectric_VehicleForPro(cpAction);
    }

    public String insertNewAdElectric_VehicleForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Electric(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdElectric_VehicleChotot(boolean isUsingNewUser) {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdElectric_VehicleShop(boolean isUsingNewUser) {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Electric_Vehicle");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdBicycles() {
        return insertNewAdBicycles("No action");
    }

    @Deprecated
    public String insertNewAdBicycles(boolean isUsingNewUser) {
        return insertNewAdBicycles("no action");
    }

    @Deprecated
    public String insertNewAdBicycles(boolean isUsingNewUser, String cpAction) {
        return insertNewAdBicycles(cpAction);
    }

    public String insertNewAdBicycles(String cpAction) {
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdBicyclesForPro() {
        return insertNewAdBicyclesForPro("No Action");
    }

    @Deprecated
    public String insertNewAdBicyclesForPro(boolean isUsingNewUser) {
        return insertNewAdBicyclesForPro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdBicyclesForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdBicyclesForPro(cpAction);
    }

    public String insertNewAdBicyclesForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Bicycles(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdBicyclesChotot(boolean isUsingNewUser) {
        return insertNewAdBicyclesChotot();
    }

    public String insertNewAdBicyclesChotot() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdBicyclesShop(boolean isUsingNewUser) {
        return insertNewAdBicyclesShop();
    }

    public String insertNewAdBicyclesShop() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Bicycles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdVehicles_Parts(boolean isUsingNewUser) {
        return insertNewAdVehicles_Parts(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdVehicles_Parts(boolean isUsingNewUser, String cpAction) {
        return insertNewAdVehicles_Parts(cpAction);
    }

    public String insertNewAdVehicles_Parts() {
        return insertNewAdVehicles_Parts("No Action");
    }

    public String insertNewAdVehicles_Parts(String cpAction) {

        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdVehicles_PartsForPro(boolean isUsingNewUser) {
        return insertNewAdVehicles_PartsForPro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdVehicles_PartsForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdVehicles_PartsForPro("No Action");
    }

    public String insertNewAdVehicles_PartsForPro() {
        return insertNewAdVehicles_PartsForPro("No Action");
    }

    public String insertNewAdVehicles_PartsForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Vehicles_Parts(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdVehicles_PartsChotot(boolean isUsingNewUser) {
        return insertNewAdVehicles_PartsChotot();
    }

    public String insertNewAdVehicles_PartsChotot() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdVehicles_PartsShop(boolean isUsingNewUser) {
        return insertNewAdVehicles_PartsShop();
    }

    public String insertNewAdVehicles_PartsShop() {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Vehicles_Parts");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdOther_Vehicles(boolean isUsingNewUser) {
        return insertNewAdOther_Vehicles("No action");
    }

    @Deprecated
    public String insertNewAdOther_Vehicles(boolean isUsingNewUser, String cpAction) {
        return insertNewAdOther_Vehicles(cpAction);
    }

    public String insertNewAdOther_Vehicles() {
        return insertNewAdOther_Vehicles("No action");
    }

    public String insertNewAdOther_Vehicles(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdOther_VehiclesForPro(boolean isUsingNewUser) {
        return insertNewAdOther_VehiclesForPro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdOther_VehiclesForPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdOther_VehiclesForPro(cpAction);
    }

    public String insertNewAdOther_VehiclesForPro() {
        return insertNewAdOther_VehiclesForPro("No action");
    }

    public String insertNewAdOther_VehiclesForPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(16);
        List<String> dataValues = excelDataProvider.getRowData(17);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdCP.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdCP.acceptAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdCP.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdCP.rejectAd_VEH_Other_Vehicles(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdOther_VehiclesChotot(boolean isUsingNewUser) {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdOther_VehiclesShop(boolean isUsingNewUser) {
        // Set to account has shops
        ExcelProvider excelDataProvider = new ExcelProvider();
        getAccessTokenOfExistingUser();

        // Setup test data
        excelDataProvider.getExcelFileSheet(adVehicleExcelFile, "Other_Vehicles");

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(11);
        List<String> dataValues = excelDataProvider.getRowData(12);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post ad car data

        insertNewAd(data);
        return tempAdID;
    }
}
