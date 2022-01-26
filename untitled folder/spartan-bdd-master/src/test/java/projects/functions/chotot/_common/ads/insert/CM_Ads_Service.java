package projects.functions.chotot._common.ads.insert;

import desktop.base.BaseTest;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_Service extends BaseTest {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;
    String data, step;

    public void insertService(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
               if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 9) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 10) {
                    adsGroup.enterDescription(insertData.get(i));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertTravel(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 9) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 10) {
                    adsGroup.enterDescription(insertData.get(i));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }
}
