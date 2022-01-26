package projects.functions.chotot._common.ads.insert;

import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_MomAndBaby {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;
    String data, step;

    public void insertConsumer(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 6) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                } else if (i == 10 || i == 7) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 2 || i == 8) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }

        adsGroup.clickPostNow();
    }
}
