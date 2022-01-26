package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.List;

import static api.configuration.DataConfig.adJobExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;

public class CM_API_Ads_InsertJob {

    private final CM_CP_API_AcceptAd cm_cp_api_acceptAd;
    private final CM_CP_API_RejectAd cm_cp_api_rejectAd;
    private final ExcelProvider excelDataProvider;

    public CM_API_Ads_InsertJob() {
        cm_cp_api_acceptAd = new CM_CP_API_AcceptAd();
        cm_cp_api_rejectAd = new CM_CP_API_RejectAd();
        excelDataProvider = new ExcelProvider();
    }

    public static CM_API_Ads_InsertJob cmAPIAdInsert;
    public static CM_API_Ads_InsertJob instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertJob();

        return cmAPIAdInsert;
    }

    public String insertNewAdJob() {
        return insertNewAdJob("no action");
    }

    public String insertNewAdJob(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

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
                cm_cp_api_acceptAd.acceptAd_Job(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAd.rejectAd_Job(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }
    public String insertNewAdJob_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(1);
        List<String> dataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Job(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAd.rejectAd_Job(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }
    public String insertNewAdLookingJob_NoUploadNewImage(String cpAction) {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Set access token
        setAccessToken();

        // Get list data from excel
        List<String> dataKeys = excelDataProvider.getRowData(6);
        List<String> dataValues = excelDataProvider.getRowData(7);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping_NoUploadNewImage(dataKeys, dataValues);

        // Post a new ad
        insertNewAd(data);

        // Do based on input action
        switch (cpAction.toLowerCase().trim()) {
            case "accept":
                cm_cp_api_acceptAd.acceptAd_Job(dataKeys, dataValues);
                break;

            case "reject":
                cm_cp_api_rejectAd.rejectAd_Job(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

}
