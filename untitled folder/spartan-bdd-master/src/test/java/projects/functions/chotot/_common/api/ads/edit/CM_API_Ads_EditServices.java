package projects.functions.chotot._common.api.ads.edit;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.adEditServicesExcelFile;
import static api.feature.ads.EditAds.editNewAd;
import static api.utils.GetJSONString.extractJSONMapping_EditAd_Services;

public class CM_API_Ads_EditServices {

    private final CM_CP_API_AcceptAd cm_api_acceptEditAd;
    private final CM_CP_API_RejectAd cm_api_rejectEditAd;
    private final ExcelProvider excelDataProvider;
    private String jsonData = "";
    private List<String> dataKeys, dataValues;

    public CM_API_Ads_EditServices() {
        cm_api_acceptEditAd = new CM_CP_API_AcceptAd();
        cm_api_rejectEditAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
        dataKeys = new ArrayList<>();
        dataValues = new ArrayList<>();
    }

    // Service
    public String editNewAdService(String adID) {
        return editNewAdService(adID, "no action");
    }

    public String editNewAdService(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditServicesExcelFile, "Service");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Services(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Services_Service(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Services_Service(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdServiceBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditServicesExcelFile, "Service");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Services(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Services_Service(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Services_Service(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    // Travel
    public String editNewAdTravel(String adID) {
        return editNewAdTravel(adID, "no action");
    }

    public String editNewAdTravel(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditServicesExcelFile, "Travel");
        dataKeys = excelDataProvider.getRowData(1);
        dataValues = excelDataProvider.getRowData(2);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Services(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Services_Travel(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Services_Travel(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }

    public String editNewAdTravelBuy(String adID, String cpAction) {
        excelDataProvider.getExcelFileSheet(adEditServicesExcelFile, "Travel");
        dataKeys = excelDataProvider.getRowData(6);
        dataValues = excelDataProvider.getRowData(7);

        // Get JSON of Edit Ad
        jsonData = extractJSONMapping_EditAd_Services(adID, dataKeys, dataValues);

        // Edit ad
        String adEditedID = editNewAd(jsonData);

        // Accept or Reject Edit request
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_api_acceptEditAd.acceptAd_Services_Travel(dataKeys, dataValues);
                break;
            case "reject":
                cm_api_rejectEditAd.rejectAd_Services_Travel(dataKeys, dataValues);
                break;
            default:
        }

        return adEditedID;
    }
}
