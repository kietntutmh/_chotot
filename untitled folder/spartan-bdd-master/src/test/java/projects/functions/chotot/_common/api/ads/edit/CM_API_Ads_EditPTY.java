package projects.functions.chotot._common.api.ads.edit;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adEditPTYExcelFile;
import static api.feature.ads.EditAds.editNewAd;
import static api.utils.GetJSONString.extractJSONMapping_EditAd_PTY;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class CM_API_Ads_EditPTY {
    private final CM_CP_API_AcceptAd cm_api_acceptEditAd;
    private final CM_CP_API_RejectAd cm_api_rejectEditAd;
    private final ExcelProvider excelDataProvider;
    private String jsonData = "";
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_EditPTY() {
        cm_api_acceptEditAd = new CM_CP_API_AcceptAd();
        cm_api_rejectEditAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    public List<String> getDataKeys() {
        return dataKeys;
    }

    public List<String> getDataValues() {
        return dataValues;
    }

    // House
    public String editNewAdHouse(String adID) {
        return editNewAdHouse(adID, "no action");
    }

    public String editNewAdHouse(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHousePro(String adID) {
        return editNewAdHousePro(adID, "no action");
    }

    public String editNewAdHousePro(String adID, String cpAction) {

        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHouseBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHouseBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHouseChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHouseShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "House");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_House(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_House(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Apartment
    public String editNewAdApartment(String adID) {
        return editNewAdApartment(adID, "no action");
    }

    public String editNewAdApartment(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdApartmentPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdApartmentBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdApartmentBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdApartmentChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdApartmentShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Apartment");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Apartment(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Apartment(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Land
    public String editNewAdLand(String adID) {
        return editNewAdLand(adID, "no action");
    }

    public String editNewAdLand(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLandPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLandBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLandBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLandChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLandShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Land");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Land(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Land(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Office
    public String editNewAdOffice(String adID) {
        return editNewAdOffice(adID, "no action");
    }

    public String editNewAdOffice(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOfficePro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOfficeBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            default:
        }
        return adEditedID;
    }

    public String editNewAdOfficeBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOfficeChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdOfficeShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Office");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Office(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Office(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Room
    public String editNewAdRoom(String adID) {
        return editNewAdRoom(adID, "no action");
    }

    public String editNewAdRoom(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Room");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdRoomPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Room");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdRoomHire(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Room");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdRoomHirePro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Room");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdRoomChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Room");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdRoomShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditPTYExcelFile, "Room");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_PTY(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();
                cm_api_acceptEditAd.acceptAd_PTY_Room(dataKeys, dataValues);
                break;
            case "rejectpay":
                paymentOrderWithDongTot();
                cm_api_rejectEditAd.rejectAd_PTY_Room(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }
}
