package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adJobExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Jobs extends BaseAPITest {

    @Test(description = "Insert Ad - JOB, Verify insert Job ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdJob() {
        //Setup test data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Get list data from excel
        List<String> jobDataKeys = excelDataProvider.getRowData(1);
        List<String> jobDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(jobDataKeys, jobDataValues);

        // Post ad job data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - JOB , Verify Insert People Looking For Jobs ad successfully, Tri Nguyen, Mabu")
    public void verifyInsertAdJPeopleLookingForJobs() {
        //setup data
        excelDataProvider.getExcelFileSheet(adJobExcelFile, "Job");

        // Get list data from excel
        List<String> jobDataKeys = excelDataProvider.getRowData(6);
        List<String> jobDataValues = excelDataProvider.getRowData(7);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(jobDataKeys, jobDataValues);

        // Post ad job data
        insertNewAd(data);
    }
}
