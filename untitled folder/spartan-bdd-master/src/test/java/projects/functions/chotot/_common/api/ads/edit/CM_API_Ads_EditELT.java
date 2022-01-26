package projects.functions.chotot._common.api.ads.edit;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adEditELTExcelFile;
import static api.feature.ads.EditAds.editNewAd;
import static api.utils.GetJSONString.extractJSONMapping_EditAd_ELT;
import static projects.functions.chotot.payment.PayOrder_API_Functions.paymentOrderWithDongTot;

public class CM_API_Ads_EditELT {
    private final CM_CP_API_AcceptAd cm_api_acceptEditAd;
    private final CM_CP_API_RejectAd cm_api_rejectEditAd;
    private final ExcelProvider excelDataProvider;
    private String jsonData = "";
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_EditELT() {
        cm_api_acceptEditAd = new CM_CP_API_AcceptAd();
        cm_api_rejectEditAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    // Laptop
    public String editNewAdLaptop(String adID) {
        return editNewAdLaptop(adID, "no action");
    }

    public String editNewAdLaptop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Laptop");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLaptopPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Laptop");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "acceptpay":
                paymentOrderWithDongTot();

                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
            default:
        }

        return adEditedID;
    }

    public String editNewAdLaptopBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Laptop");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLaptopBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Laptop");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLaptopChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Laptop");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdLaptopShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Laptop");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Laptop(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Laptop(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Phone
    public String editNewAdPhone(String adID) {
        return editNewAdPhone(adID, "no action");
    }

    public String editNewAdPhone(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Phone");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPhonePro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Phone");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPhoneBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Phone");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);
        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPhoneBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Phone");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);
        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPhoneChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Phone");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);
        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPhoneShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Phone");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);
        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Phone(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Phone(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Tablet
    public String editNewAdTablet(String adID) {
        return editNewAdTablet(adID, "no action");
    }

    public String editNewAdTablet(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Tablet");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTabletPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Tablet");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTabletBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Tablet");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTabletBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Tablet");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTabletChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Tablet");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTabletShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Tablet");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Tablet(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Tablet(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Camera
    public String editNewAdCamera(String adID) {
        return editNewAdCamera(adID, "no action");
    }

    public String editNewAdCamera(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Camera");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCameraPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Camera");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCameraBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Camera");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCameraBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Camera");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCameraChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Camera");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCameraShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Camera");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Camera(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Camera(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // PC
    public String editNewAdPC(String adID) {
        return editNewAdPC(adID, "no action");
    }

    public String editNewAdPC(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPCPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPCBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPCBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPCChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPCShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Sound
    public String editNewAdSound(String adID) {
        return editNewAdSound(adID, "no action");
    }

    public String editNewAdSound(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Sound");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdSoundPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Sound");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdSoundBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Sound");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdSoundBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Sound");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdSoundChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Sound");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdSoundShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Sound");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Sound(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Sound(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Wearable
    public String editNewAdWearable(String adID) {
        return editNewAdWearable(adID, "no action");
    }

    public String editNewAdWearable(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Wearable");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdWearablePro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Wearable");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdWearableBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Wearable");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdWearableBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Wearable");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdWearableChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Wearable");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdWearableShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Wearable");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Wearable(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Wearable(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Accessories
    public String editNewAdAccessories(String adID) {
        return editNewAdAccessories(adID, "no action");
    }

    public String editNewAdAccessories(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdAccessoriesPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdAccessoriesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdAccessoriesBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdAccessoriesChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdAccessoriesShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // PC_Component
    public String editNewAdPC_Component(String adID) {
        return editNewAdPC_Component(adID, "no action");
    }

    public String editNewAdPC_Component(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC_Component");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPC_ComponentPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC_Component");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPC_ComponentBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC_Component");
        dataKeys = excelDataProvider.getRowData(11);
        dataValues = excelDataProvider.getRowData(12);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPC_ComponentBuyPro(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC_Component");
        dataKeys = excelDataProvider.getRowData(16);
        dataValues = excelDataProvider.getRowData(17);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPC_ComponentChotot(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC_Component");
        dataKeys = excelDataProvider.getRowData(21);
        dataValues = excelDataProvider.getRowData(22);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPC_ComponentShop(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditELTExcelFile, "PC_Component");
        dataKeys = excelDataProvider.getRowData(26);
        dataValues = excelDataProvider.getRowData(27);

        jsonData = extractJSONMapping_EditAd_ELT(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_ELT_PC_Component(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }
}
