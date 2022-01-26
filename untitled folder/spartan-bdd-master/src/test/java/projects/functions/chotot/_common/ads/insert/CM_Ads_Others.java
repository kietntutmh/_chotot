package projects.functions.chotot._common.ads.insert;

import desktop.base.BaseTest;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_Others extends BaseTest {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;
    String data, step;

    public void insertOthers(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 4 || i == 5 || i == 3 || i == 10) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 6) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 7 || i == 8) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 2) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 9) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }
}
