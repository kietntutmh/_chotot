package projects.functions.chotot._common.api.ads.insert;

import com.vn.chotot.data.ExcelProvider;
import projects.functions.chotot._common.api.cp.CM_CP_API_AcceptAd;
import projects.functions.chotot._common.api.cp.CM_CP_API_RejectAd;

import java.util.List;

import static api.configuration.DataConfig.adMomAndBabyExcelFile;
import static api.configuration.DataConfig.tempAdID;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.CheckAccessToken.setAccessToken;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping_NoUploadNewImage;

public class CM_API_Ads_InsertMomAndBaby {

    public static CM_API_Ads_InsertMomAndBaby cmAPIAdInsert;
    public static CM_API_Ads_InsertMomAndBaby instance() {
        if (cmAPIAdInsert == null) cmAPIAdInsert = new CM_API_Ads_InsertMomAndBaby();

        return cmAPIAdInsert;
    }

    public String insertNewAdMomAndBaby() {
        return insertNewAdMomAndBaby("no action");
    }

    public String insertNewAdMomAndBaby(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adMomAndBabyExcelFile, "MomAndBaby");

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
                new CM_CP_API_AcceptAd().acceptAd_MomAndBaby(dataKeys, dataValues);
                break;

            case "reject":
                new CM_CP_API_RejectAd().rejectAd_MomAndBaby(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }

    public String insertNewAdMomAndBaby_NoUploadNewImage(String cpAction) {
        // Setup test data
        ExcelProvider excelDataProvider = new ExcelProvider();
        excelDataProvider.getExcelFileSheet(adMomAndBabyExcelFile, "MomAndBaby");

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
                new CM_CP_API_AcceptAd().acceptAd_MomAndBaby(dataKeys, dataValues);
                break;

            case "reject":
                new CM_CP_API_RejectAd().rejectAd_MomAndBaby(dataKeys, dataValues);
                break;
        }

        // Return new ad_id
        return tempAdID;
    }
}
