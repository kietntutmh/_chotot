package projects.functions.chotot._common.ads.insert;

import desktop.base.BaseTest;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_Entertainment extends BaseTest {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;
    String data, step;

    public void insertInstrument(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 10 || i == 13) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 11) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 12) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertBook(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 12) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertSport(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 12) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertCollectibles(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 10 || i == 13) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 11) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 12) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertGaming(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6 || i == 12) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertHabbit(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 0 || i == 1 || i == 2 || i == 4 || i == 5 || i == 6  || i == 12) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 7) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 8 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 3 || i == 9) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i));
                }
            }
        }
        // Dang tin
        adsGroup.clickPostNow();
    }
}
