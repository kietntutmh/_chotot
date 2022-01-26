package api.scenarios_old.ads.insert;

import api.base.BaseAPITest;
import org.testng.annotations.Test;

import java.util.List;

import static api.configuration.DataConfig.adPetExcelFile;
import static api.feature.ads.InsertAds.insertNewAd;
import static api.utils.GetJSONString.extractAndUpdateSubjectJSONMapping;

public class Pets extends BaseAPITest {

    @Test(description = "Insert Ad - Pet, Verify insert Pet ad successfully, Quoc Tran, MABU")
    public void verifyInsertAdChicken() {
        // Setup test data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Chicken");

        // Get list data from excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get JSON String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Pet, Verify insert Dog ad successfully, Tri Nguyen, MABU")
    public void verifyInsertAdDog() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Dog");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Pet, Verify insert Bird ad successfully, Tri Nguyen, Mabu")
    public void verifyInsertAdBird() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Bird");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Pet, Verify insert Cat ad successfully, Tri Nguyen, Mabu")
    public void verifyInsertAdCat() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "Cat");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Pet, Verify insert Pet ad successfully, Tri Nguyen, Mabu")
    public void verifyInsertAdOtherPet() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "other");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }

    @Test(description = "Insert Ad - Pet, Verify insert Accessory ad successfully, Tri Nguyen, Mabu")
    public void verifyInsertAdAccessoryForPet() {
        // Setup data
        excelDataProvider.getExcelFileSheet(adPetExcelFile, "accessory");

        // Get list data form excel
        List<String> petDataKeys = excelDataProvider.getRowData(1);
        List<String> petDataValues = excelDataProvider.getRowData(2);

        // Get Json String data
        String data = extractAndUpdateSubjectJSONMapping(petDataKeys, petDataValues);

        // Post ad pet data
        insertNewAd(data);
    }
}
