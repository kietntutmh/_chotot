package projects.functions.chotot._common.api.ads.edit;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adEditEntertainmentExcelFile;
import static api.feature.ads.EditAds.editNewAd;
import static api.utils.GetJSONString.extractJSONMapping_EditAd_Entertainment;

public class CM_API_Ads_EditEntertainment {
    private final CM_CP_API_AcceptAd cm_api_acceptEditAd;
    private final CM_CP_API_RejectAd cm_api_rejectEditAd;
    private final ExcelProvider excelDataProvider;
    private String jsonData = "";
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_EditEntertainment() {
        cm_api_acceptEditAd = new CM_CP_API_AcceptAd();
        cm_api_rejectEditAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    // Instrument
    public String editNewAdInstrument(String adID) {
        return editNewAdInstrument(adID, "no action");
    }

    public String editNewAdInstrument(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Instrument");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdInstrumentBuy(String adID) {
        return editNewAdInstrumentBuy(adID, "no action");
    }

    public String editNewAdInstrumentBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Instrument");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Instrument(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Sport
    public String editNewAdSport(String adID) {
        return editNewAdSport(adID, "no action");
    }

    public String editNewAdSport(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Sport");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdSportBuy(String adID) {
        return editNewAdSportBuy(adID, "no action");
    }

    public String editNewAdSportBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Sport");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Sport(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Book
    public String editNewAdBook(String adID) {
        return editNewAdBook(adID, "no action");
    }

    public String editNewAdBook(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Book");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdBookBuy(String adID) {
        return editNewAdBookBuy(adID, "no action");
    }

    public String editNewAdBookBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Book");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Book(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Book(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Collectibles
    public String editNewAdCollectibles(String adID) {
        return editNewAdCollectibles(adID, "no action");
    }

    public String editNewAdCollectibles(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Collectibles");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdCollectiblesBuy(String adID) {
        return editNewAdCollectiblesBuy(adID, "no action");
    }

    public String editNewAdCollectiblesBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Collectibles");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Collectibles(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Gaming
    public String editNewAdGaming(String adID) {
        return editNewAdGaming(adID, "no action");
    }

    public String editNewAdGaming(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Gaming");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdGamingBuy(String adID) {
        return editNewAdGamingBuy(adID, "no action");
    }

    public String editNewAdGamingBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Gaming");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Gaming(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Hobby
    public String editNewAdHobby(String adID) {
        return editNewAdHobby(adID, "no action");
    }

    public String editNewAdHobby(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Hobby");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdHobbyBuy(String adID) {
        return editNewAdHobbyBuy(adID, "no action");
    }

    public String editNewAdHobbyBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditEntertainmentExcelFile, "Hobby");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Entertainment(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Entertainment_Hobby(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }
}
