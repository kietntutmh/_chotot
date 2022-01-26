package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_Pets extends BasePage {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;

    public void insertRooster(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertDog(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertBird(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertCat(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertOther_Pets(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 10) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 11) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertFood_Service(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 10) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 9 || i == 11) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 12) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }
}
