package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import org.testng.Assert;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adPTYExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.EditAds.editNewAd;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.*;
import static projects.configuaration.CategoryConfig.*;
import static projects.functions.chotot._common.api.CM_API_CreateNewUser.createNewAccount;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderDT4B;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class CM_API_Ads_InsertPTY {
    public static CM_API_Ads_InsertPTY cmAPIAdInsert;
    private final CM_CP_API_AcceptAd cm_cp_api_acceptAdPTY;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAdPTY;
    private final ExcelProvider excelDataProvider;
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_InsertPTY() {
        cm_cp_api_acceptAdPTY = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAdPTY = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    public static CM_API_Ads_InsertPTY instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertPTY();

        return cmAPIAdInsert;
    }

    public List<String> getCurrentListDataKeys() {
        return dataKeys;
    }

    public List<String> getCurrentListDataValues() {
        return dataValues;
    }

    // House
    public String insertNewAdHouse(boolean isUsingNewUser) {
        return insertNewAdHouse(isUsingNewUser, "no action");
    }

    public String insertNewAdHouse(boolean isUsingNewUser, String cpAction) {
        return insertNewAdHouse(cpAction);
    }

    public String insertNewAdHouse() {
        return insertNewAdHouse("no action");
    }

    public String insertNewAdHouse(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "acceptpay":
                paymentOrderWithDongTot();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "rejectpay":
                paymentOrderWithDongTot();
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    @Deprecated
    public String insertNewAdHousePro(boolean isUsingNewUser) {
        return insertNewAdHousePro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdHousePro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdHousePro(cpAction);
    }

    public String insertNewAdHousePro() {
        return insertNewAdHousePro("no action");
    }

    public String insertNewAdHousePro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(47);
        dataValues = excelDataProvider.getRowData(48);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdHouseProObligateShop(boolean isUsingNewUser) {
        return insertNewAdHouseProObligateShop();
    }

    public String insertNewAdHouseProObligateShop() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(74);
        dataValues = excelDataProvider.getRowData(75);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseProObligateChotot(boolean isUsingNewUser) {
        return insertNewAdHouseProObligateChotot();
    }

    public String insertNewAdHouseProObligateChotot() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(79);
        dataValues = excelDataProvider.getRowData(80);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseBuy(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(52);
        dataValues = excelDataProvider.getRowData(53);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseBuyProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(84);
        dataValues = excelDataProvider.getRowData(85);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseBuyProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(89);
        dataValues = excelDataProvider.getRowData(90);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseOtherCity(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(31);
        dataValues = excelDataProvider.getRowData(32);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseOtherCityPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(67);
        dataValues = excelDataProvider.getRowData(68);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseOtherCityProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(114);
        dataValues = excelDataProvider.getRowData(115);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseOtherCityProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(119);
        dataValues = excelDataProvider.getRowData(120);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseHire(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(36);
        dataValues = excelDataProvider.getRowData(37);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseHirePro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(57);
        dataValues = excelDataProvider.getRowData(58);

        // Using new or existing user
        if (!isUsingNewUser) {
            // Set access token
            setAccessToken();
        } else {
            createNewAccount(true);
        }

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseHireProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(94);
        dataValues = excelDataProvider.getRowData(95);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseHireProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(99);
        dataValues = excelDataProvider.getRowData(100);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseLease(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(41);
        dataValues = excelDataProvider.getRowData(42);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseLeasePro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(62);
        dataValues = excelDataProvider.getRowData(63);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseLeaseProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(104);
        dataValues = excelDataProvider.getRowData(105);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseLeaseProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(109);
        dataValues = excelDataProvider.getRowData(110);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouse_ProObligate_Private(boolean isUsingNewUser) {
        return insertNewAdHouse_ProObligate_Private(isUsingNewUser, "no action");
    }

    public String insertNewAdHouse_ProObligate_Private() {
        return insertNewAdHouse_ProObligate_Private("No Action");
    }

    public String insertNewAdHouse_ProObligate_Private(boolean isUsingNewUser, String cpAction) {
        return insertNewAdHouse_ProObligate_Private(cpAction);
    }

    public String insertNewAdHouse_ProObligate_Private(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(125);
        dataValues = excelDataProvider.getRowData(126);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Insert new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;
        }

        return tempAdID;
    }

    public String insertNewAdHouseBuy_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(130);
        dataValues = excelDataProvider.getRowData(131);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseHire_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(135);
        dataValues = excelDataProvider.getRowData(136);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseLease_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(140);
        dataValues = excelDataProvider.getRowData(141);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdHouseOtherRegion_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(145);
        dataValues = excelDataProvider.getRowData(146);

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
    public String insertNewAdApartment(boolean isUsingNewUser) {
        return insertNewAdApartment(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdApartment(boolean isUsingNewUser, String cpAction) {
        return insertNewAdApartment(cpAction);
    }

    public String insertNewAdApartment() {
        return insertNewAdApartment("No action");
    }

    public String insertNewAdApartment(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdApartmentToShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdApartmentPro(boolean isUsingNewUser) {
        return insertNewAdApartmentPro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdApartmentPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdApartmentPro(cpAction);
    }

    public String insertNewAdApartmentPro() {
        return insertNewAdApartmentPro("No action");
    }

    public String insertNewAdApartmentPro(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(47);
        dataValues = excelDataProvider.getRowData(48);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdApartmentProObligateShop(boolean isUsingNewUser) {
        return insertNewAdApartmentProObligateShop();
    }

    public String insertNewAdApartmentProObligateShop() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(74);
        dataValues = excelDataProvider.getRowData(75);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdApartmentProObligateChotot(boolean isUsingNewUser) {
        return insertNewAdApartmentProObligateChotot();
    }

    public String insertNewAdApartmentProObligateChotot() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(79);
        dataValues = excelDataProvider.getRowData(80);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentBuy(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(52);
        dataValues = excelDataProvider.getRowData(53);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentBuyProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(84);
        dataValues = excelDataProvider.getRowData(85);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentBuyProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(89);
        dataValues = excelDataProvider.getRowData(90);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentOtherCity(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(31);
        dataValues = excelDataProvider.getRowData(32);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentOtherCityPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(67);
        dataValues = excelDataProvider.getRowData(68);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentOtherCityProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(114);
        dataValues = excelDataProvider.getRowData(115);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentOtherCityProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(119);
        dataValues = excelDataProvider.getRowData(120);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentHire(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(36);
        dataValues = excelDataProvider.getRowData(37);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentHirePro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(57);
        dataValues = excelDataProvider.getRowData(58);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdApartmentHireProObligateShop(boolean isUsingNewUser) {
        return insertNewAdApartmentHireProObligateShop();
    }

    public String insertNewAdApartmentHireProObligateShop() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(94);
        dataValues = excelDataProvider.getRowData(95);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentHireProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(99);
        dataValues = excelDataProvider.getRowData(100);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdApartmentLease(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(41);
        dataValues = excelDataProvider.getRowData(42);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentLeasePro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(62);
        dataValues = excelDataProvider.getRowData(63);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartment_ProObligate_Private(boolean isUsingNewUser) {
        return insertNewAdApartment_ProObligate_Private(isUsingNewUser, "no action");
    }

    public String insertNewAdApartmentLeaseProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(104);
        dataValues = excelDataProvider.getRowData(105);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentLeaseProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(109);
        dataValues = excelDataProvider.getRowData(110);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartment_ProObligate_Private() {
        return insertNewAdApartment_ProObligate_Private("No Action");
    }

    public String insertNewAdApartment_ProObligate_Private(boolean isUsingNewUser, String cpAction) {
        return insertNewAdApartment_ProObligate_Private(cpAction);
    }

    public String insertNewAdApartment_ProObligate_Private(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(125);
        dataValues = excelDataProvider.getRowData(126);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdApartmentBuy_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(130);
        dataValues = excelDataProvider.getRowData(131);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentHire_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(135);
        dataValues = excelDataProvider.getRowData(136);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentLease_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(140);
        dataValues = excelDataProvider.getRowData(141);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdApartmentOtherRegion_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(145);
        dataValues = excelDataProvider.getRowData(146);

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
    public String insertNewAdLand(boolean isUsingNewUser) {
        return insertNewAdLand(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdLand(boolean isUsingNewUser, String cpAction) {
        return insertNewAdLand(cpAction);
    }

    public String insertNewAdLand() {
        return insertNewAdLand("No action");
    }

    public String insertNewAdLand(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    @Deprecated
    public String insertNewAdLandPro(boolean isUsingNewUser) {
        return insertNewAdLandPro(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdLandPro(boolean isUsingNewUser, String cpAction) {
        return insertNewAdLandPro(cpAction);
    }

    public String insertNewAdLandPro() {
        return insertNewAdLandPro("No action");
    }

    public String insertNewAdLandPro(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(47);
        dataValues = excelDataProvider.getRowData(48);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    @Deprecated
    public String insertNewAdLandProObligateShop(boolean isUsingNewUser) {
        return insertNewAdLandProObligateShop();
    }

    public String insertNewAdLandProObligateShop() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(74);
        dataValues = excelDataProvider.getRowData(75);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdLandProObligateChotot(boolean isUsingNewUser) {
        return insertNewAdLandProObligateChotot();
    }

    public String insertNewAdLandProObligateChotot() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(79);
        dataValues = excelDataProvider.getRowData(80);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandBuy(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandBuyPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(52);
        dataValues = excelDataProvider.getRowData(53);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandBuyProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(84);
        dataValues = excelDataProvider.getRowData(85);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandBuyProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(89);
        dataValues = excelDataProvider.getRowData(90);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandOtherCity(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(31);
        dataValues = excelDataProvider.getRowData(32);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandOtherCityPro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(67);
        dataValues = excelDataProvider.getRowData(68);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandOtherCityProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(114);
        dataValues = excelDataProvider.getRowData(115);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandOtherCityProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(119);
        dataValues = excelDataProvider.getRowData(120);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandHire(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(36);
        dataValues = excelDataProvider.getRowData(37);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandHirePro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(57);
        dataValues = excelDataProvider.getRowData(58);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandHireProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(94);
        dataValues = excelDataProvider.getRowData(95);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandLease(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(41);
        dataValues = excelDataProvider.getRowData(42);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandHireProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(99);
        dataValues = excelDataProvider.getRowData(100);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandLeasePro(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(62);
        dataValues = excelDataProvider.getRowData(63);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandLeaseProObligateShop(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(104);
        dataValues = excelDataProvider.getRowData(105);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandLeaseProObligateChotot(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(109);
        dataValues = excelDataProvider.getRowData(110);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLand_ProObligate_Private(boolean isUsingNewUser) {
        return insertNewAdLand_ProObligate_Private(isUsingNewUser, "no action");
    }

    public String insertNewAdLand_ProObligate_Private(boolean isUsingNewUser, String cpAction) {
        return insertNewAdLand_ProObligate_Private(cpAction);
    }

    public String insertNewAdLand_ProObligate_Private() {
        return insertNewAdLand_ProObligate_Private("No Action");
    }

    public String insertNewAdLand_ProObligate_Private(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(125);
        dataValues = excelDataProvider.getRowData(126);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Insert new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }
        return tempAdID;
    }

    public String insertNewAdLandBuy_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(130);
        dataValues = excelDataProvider.getRowData(131);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandHire_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(135);
        dataValues = excelDataProvider.getRowData(136);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandLease_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(140);
        dataValues = excelDataProvider.getRowData(141);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdLandOtherRegion_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(145);
        dataValues = excelDataProvider.getRowData(146);

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
    public String insertNewAdOffice(boolean isUsingNewUser) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdOffice(boolean isUsingNewUser, String cpAction) {
        return insertNewAdOffice(cpAction);
    }

    public String insertNewAdOffice() {
        return insertNewAdOffice("No action");
    }

    public String insertNewAdOffice(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdOfficeProObligateChotot() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(30);
        dataValues = excelDataProvider.getRowData(31);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdOfficeProObligateShop() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(35);
        dataValues = excelDataProvider.getRowData(36);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdOfficePro() {
        return insertNewAdOfficePro("No action");
    }

    public String insertNewAdOfficePro(String cpAction) {
        return insertNewAdOfficePro(cpAction, true);
    }

    private String insertNewAdOfficePro(String cpAction, boolean isUsingNewImage) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(18);
        dataValues = excelDataProvider.getRowData(19);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdOffice_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(43);
        dataValues = excelDataProvider.getRowData(44);

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
    public String insertNewAdRoomForLease(boolean isUsingNewUser) {
        return insertNewAdRoomForLease(isUsingNewUser, "no action");
    }

    @Deprecated
    public String insertNewAdRoomForLease(boolean isUsingNewUser, String cpAction) {
        return insertNewAdRoomForLease(cpAction);
    }

    public String insertNewAdRoomForLease() {
        return insertNewAdRoomForLease("No action");
    }

    public String insertNewAdRoomForLease(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdRoomForLeasePro() {
        return insertNewAdRoomForLeasePro("No action");
    }

    public String insertNewAdRoomForLeasePro_NoUploadNewImage(String cpAction) {
        return insertNewAdRoomForLeasePro(cpAction, false);
    }

    public String insertNewAdRoomForLeasePro(String cpAction) {
        return insertNewAdRoomForLeasePro(cpAction, true);
    }

    private String insertNewAdRoomForLeasePro(String cpAction, boolean isUsingNewImage) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(18);
        dataValues = excelDataProvider.getRowData(19);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }
        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    @Deprecated
    public String insertNewAdRoomForLeaseProObligateChotot(boolean isUsingNewUser) {
        return insertNewAdRoomForLeaseProObligateChotot();
    }

    public String insertNewAdRoomForLeaseProObligateChotot() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(30);
        dataValues = excelDataProvider.getRowData(31);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdRoomForLeaseProObligateShop(boolean isUsingNewUser) {
        return insertNewAdRoomForLeaseProObligateShop();
    }

    public String insertNewAdRoomForLeaseProObligateShop() {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(35);
        dataValues = excelDataProvider.getRowData(36);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }

    public String insertNewAdRoom_ProObligate_Private(boolean isUsingNewUser) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(43);
        dataValues = excelDataProvider.getRowData(44);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad

        // Return new ad_id
        insertNewAd(data);
        return tempAdID;
    }


    //Support for Chat v2
    @Deprecated
    public String insertAdNewHouse_ChatV2(String cpAction, String userToken) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data, userToken);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "pay":
                paymentOrderWithDongTot();
                break;
            default:
                break;
        }

        return tempAdID;
    }


    //VUHOANG Supports for faster insert
    public String insertNewAdHouse_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdHousePro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(47);
        dataValues = excelDataProvider.getRowData(48);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }


    public String insertNewAdApartment_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdApartmentPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(47);
        dataValues = excelDataProvider.getRowData(48);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;
            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdApartmentShop(String cpAction) {
        return insertNewAdApartmentShop(cpAction, true, true);
    }

    public String insertNewAdApartmentShop_NoUploadNewImage(String cpAction) {
        return insertNewAdApartmentShop(cpAction, true, false);
    }

    public String insertNewAdLand_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdLandPro_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(47);
        dataValues = excelDataProvider.getRowData(48);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdLandShop(String cpAction) {
        return insertNewAdLandShop(cpAction, true);
    }

    public String insertNewAdLandShop_NoUploadNewImage(String cpAction) {
        return insertNewAdLandShop(cpAction, false);
    }

    private String insertNewAdLandShop(String cpAction, boolean isUsingNewImage) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(79);
        dataValues = excelDataProvider.getRowData(80);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }
        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Land(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Land(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        return tempAdID;
    }


    public String insertNewAdOffice_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            default:
                break;
        }

        return tempAdID;
    }

    public String insertNewAdOfficePro_NoUploadNewImage(String cpAction) {
        return insertNewAdOfficePro(cpAction, false);
    }

    public String insertNewAdOfficeShop(String cpAction) {
        return insertNewAdOfficeShop(cpAction, true);
    }

    public String insertNewAdOfficeShop_NoUploadNewImage(String cpAction) {
        return insertNewAdOfficeShop(cpAction, false);
    }

    private String insertNewAdOfficeShop(String cpAction, boolean isUsingNewImage) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");

        // Set access token
        setAccessToken();

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(30);
        dataValues = excelDataProvider.getRowData(31);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        insertNewAd(data);

        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Office(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Office(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        // Return new ad_id
        return tempAdID;
    }


    public String insertNewAdRoomForLease_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;

            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Room(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_Room(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_Room(dataKeys, dataValues);
                break;

            default:
                break;
        }

        return tempAdID;
    }


    ///EDIT AD for support
    @Deprecated
    public String editNewAdHouseShopToChotot(String adID, String cpAction) {
        return editNewAdHouse(adID, cpAction, 47, 48);
    }

    private String editNewAdHouse(String adID, String cpAction, int excelRowKeyIndex, int excelRowValueIndex) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");

        // Get list data from excel
        dataKeys = excelDataProvider.getRowData(excelRowKeyIndex);
        dataValues = excelDataProvider.getRowData(excelRowValueIndex);

        // Set access token
        setAccessToken();

        // Get JSON String data
        String jsonData = extractAndUpdateSubjectJSONMapping_EditAd(adID, dataKeys, dataValues);


        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY_House(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY_House(dataKeys, dataValues);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }

        // Return new ad_id
        return adEditedID;
    }


    /*
    ------------------------------------------------------------------------------------------------------------------------------------
    ------------------------------ VUHOANG NEW GEN : SUPPORT FOR DATA TEST, PLANNING TO REFACTOR OLD CORE ------------------------------
    ------------------------------------------------------------------------------------------------------------------------------------
    */

    //Because there're differenet ways to insert Ad and The functionality Core changes much --> MUCH TIME TO MAINTAIN
    private String insertNewAdPTYCORE(String bodyJSONData, String cpAction, String subcateID) {
        return flashAdPTYCORE(bodyJSONData, cpAction, subcateID, false);
    }

    private String editNewAdPTYCORE(String bodyJSONData, String cpAction, String subcateID) {
        return flashAdPTYCORE(bodyJSONData, cpAction, subcateID, true);
    }

    private String flashAdPTYCORE(String bodyJSONData, String cpAction, String subcateID, boolean isEditAd) {
        Assert.assertFalse(bodyJSONData.isEmpty(), "insertNewAdPTYCORE - bodyJSONData is null");
        Assert.assertFalse(subcateID.isEmpty(), "insertNewAdPTYCORE: cateID is null");

        if (isEditAd)
            editNewAd(bodyJSONData);
        else
            insertNewAd(bodyJSONData);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAdPTY.acceptAd_PTY(dataKeys, dataValues, subcateID);
                break;
            case "acceptpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Accept
                cm_cp_api_acceptAdPTY.acceptAd_PTY(dataKeys, dataValues, subcateID);
                break;

            case "acceptpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_acceptAdPTY.acceptAd_PTY(dataKeys, dataValues, subcateID);
                break;

            case "reject":
                cm_cp_api_rejectAdPTY.rejectAd_PTY(dataKeys, dataValues, subcateID);
                break;

            case "rejectpay":
                // Pay the order
                paymentOrderWithDongTot();
                // Reject
                cm_cp_api_rejectAdPTY.rejectAd_PTY(dataKeys, dataValues, subcateID);
                break;

            case "rejectpaydt4b":
                paymentOrderDT4B();
                cm_cp_api_rejectAdPTY.rejectAd_PTY(dataKeys, dataValues, subcateID);
                break;

            case "pay":
                paymentOrderWithDongTot();
                break;

            default:
                break;
        }
        return tempAdID;
    }

    //=========== HOUSE ============
    /**
     * @param cpAction        Action of cp
     * @param postToChotot    true: Ad_To_Chotot, false: Ad_To_Shop_Dashboard
     * @param isUsingNewImage Skip uploading new image to avoid Upload_Image_Quota_Limitation. Support only for data_test. Not for Insert Ad Test
     * @param paidRegionID    Regions are paid for House Ad, default is HCM
     * @return tempAdID
     */
    public String insertNewAdHouseShop(String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        Assert.assertFalse(data.isEmpty(), "insertNewAdHouseShop - bodyJson is null");
        return insertNewAdPTYCORE(data, cpAction, CATEID_PTY_HOUSE);
    }

    public String insertNewAdHouseShop(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return insertNewAdHouseShop(cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }

    public String insertNewAdHouseShopRent(String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(104);
        dataValues = excelDataProvider.getRowData(105);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        return insertNewAdPTYCORE(data, cpAction, CATEID_PTY_HOUSE);
    }

    public String insertNewAdHouseShopRent(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return insertNewAdHouseShopRent(cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }

    //=========== OFFICE ============
    public String insertNewAdOfficeShop(String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(30);
        dataValues = excelDataProvider.getRowData(31);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        return insertNewAdPTYCORE(data, cpAction, CATEID_PTY_OFFICE);
    }

    public String insertNewAdOfficeShop(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return insertNewAdOfficeShop(cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }

    //=========== LAND ==============
    public String insertNewAdLandShop(String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(84);
        dataValues = excelDataProvider.getRowData(85);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        return insertNewAdPTYCORE(data, cpAction, CATEID_PTY_LAND);
    }

    public String insertNewAdLandShop(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return insertNewAdLandShop(cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }

    //=========== APARTMENT =============
    public String insertNewAdApartmentShop(String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(79);
        dataValues = excelDataProvider.getRowData(80);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        return insertNewAdPTYCORE(data, cpAction, CATEID_PTY_APARTMENT);
    }

    public String insertNewAdApartmentShop(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return insertNewAdApartmentShop(cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }


    //=========== ROOM =============
    public String insertNewAdRoomShopLease(String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "RoomForLease");
        dataKeys = excelDataProvider.getRowData(30);
        dataValues = excelDataProvider.getRowData(31);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);


        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = "";
        if (isUsingNewImage) {
            data = extractAndUpdateSubjectJSONMapping(dataKeys, dataValues);
        } else {
            data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);
        }

        // Post a new ad
        return insertNewAdPTYCORE(data, cpAction, CATEID_PTY_ROOMFORRENT);
    }

    public String insertNewAdRoomShopLease(String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return insertNewAdRoomShopLease(cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }

    //========================================= EDIT AD ======================================================
    public String editNewAdHouseShop(String adID, String cpAction, boolean postToChotot, boolean isUsingNewImage, String paidRegionID) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Set access token
        setAccessToken();

        //Update dataKey "shop_to_chotot" for Ad_toChotot or Ad_to_ShopDashboard   @author VUHOANG
        int fieldIndex = dataKeys.indexOf("shop_to_chotot");
        String shopToChotot = "no";
        if (postToChotot)
            shopToChotot = "yes";
        dataValues.set(fieldIndex, shopToChotot);

        //Update RegionID
        dataValues.set(dataKeys.indexOf("region_v2"), paidRegionID);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_EditAd(adID, dataKeys, dataValues);

        // Post a new ad
        Assert.assertFalse(data.isEmpty(), "insertNewAdHouseShop - bodyJson is null");
        return editNewAdPTYCORE(data, cpAction, CATEID_PTY_HOUSE);
    }

    public String editNewAdHouseShop(String adID, String cpAction, boolean postToChotot, boolean isUsingNewImage) {
        return editNewAdHouseShop(adID, cpAction, postToChotot, isUsingNewImage, REGION_HCM_ID);
    }
}
