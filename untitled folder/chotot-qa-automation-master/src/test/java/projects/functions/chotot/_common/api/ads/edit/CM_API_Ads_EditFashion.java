package projects.functions.chotot._common.api.ads.edit;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adEditFashionExcelFile;
import static api.feature.ads.EditAds.editNewAd;
import static api.utils.GetJSONString.extractJSONMapping_EditAd_Fashion;

public class CM_API_Ads_EditFashion {

    private final CM_CP_API_AcceptAd cm_api_acceptEditAd;
    private final CM_CP_API_RejectAd cm_api_rejectEditAd;
    private final ExcelProvider excelDataProvider;
    private String jsonData = "";
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_EditFashion() {
        cm_api_acceptEditAd = new CM_CP_API_AcceptAd();
        cm_api_rejectEditAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    // Clothes
    public String editNewAdClothes(String adID) {
        return editNewAdClothes(adID, "no action");
    }

    public String editNewAdClothes(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Clothes");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdClothesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Clothes");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Clothes(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Watch
    public String editNewAdWatch(String adID) {
        return editNewAdWatch(adID, "no action");
    }

    public String editNewAdWatch(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Watch");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdWatchBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Watch");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Watch(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Watch(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Shoes
    public String editNewAdShoes(String adID) {
        return editNewAdShoes(adID, "no action");
    }

    public String editNewAdShoes(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Shoes");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdShoesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Shoes");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Shoes(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Handbag
    public String editNewAdHandbag(String adID) {
        return editNewAdHandbag(adID, "no action");
    }

    public String editNewAdHandbag(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Handbag");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHandbagBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Handbag");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Handbag(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Perfume
    public String editNewAdPerfume(String adID) {
        return editNewAdPerfume(adID, "no action");
    }

    public String editNewAdPerfume(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Perfume");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdPerfumeBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Perfume");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Perfume(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Perfume(dataKeys, dataValues);
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
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdAccessoriesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditFashionExcelFile, "Accessories");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Fashion(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Fashion_Accessories(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }
}
