package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_ElectricAppliances extends BasePage {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;
    String data, step;

    public void insertCooler(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 11) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 12) {
                    adsGroup.enterDescription(insertData.get(i));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertRefrigerator(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 11) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 12) {
                    adsGroup.enterDescription(insertData.get(i));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertWashing_Machine(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 11) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 12) {
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
