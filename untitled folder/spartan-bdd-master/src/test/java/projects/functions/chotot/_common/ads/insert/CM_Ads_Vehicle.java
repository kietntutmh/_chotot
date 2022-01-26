package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_Vehicle extends BasePage {

    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;

    public void insertCar(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + i);
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 13) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 15) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 16) {
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

    public void insertMotorbike(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + i);
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 13) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 14 || i == 16 || i == 19) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 17) {
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

    public void insertTrucks(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + i);
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 12) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 13 || i == 16 || i == 20) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 14) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        //Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertElectric_Vehicle(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 12) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 13) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 14) {
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

    public void insertBicycles(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 13) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 14) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 15) {
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

    public void insertOther_Vehicles(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 13) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 14 || i == 17 || i == 21) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 15) {
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

    public void insertVehicle_Parts(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 10) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 12) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 13) {
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
