package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.components.Popup;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.vn.chotot.configuration.WaitTime.maxWaitTime;

public class CM_Ads_ELT extends BasePage {

    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;

    public void insertCellphone(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 12 || i == 15) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 17) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 20) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else if (i == 10) {
                    adsGroup.selectPanelByName(insertData.get(i - 1));
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

    // difference Steps between Pro and Pri
    public void insertCellphonePRO(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 12 || i == 16) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 18) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 20) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else if (i == 10) {
                    adsGroup.selectPanelByName(insertData.get(i - 1));
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

    public void insertTablet(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 11 || i == 12 || i == 14) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 16) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 20) {
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

    public void insertTabletPRO(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 11 || i == 12 || i == 16) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 18) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 20) {
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

    public void insertLaptop(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 12 || i == 17) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 14) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 22) {
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

    public void insertLaptopPRO(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 18 || i == 14) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 9 || i == 20) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 22) {
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

    public void insertDesktop(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 4 || i == 10 || i == 15) {
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
                } else if (i == 20) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    if (i == 3) {
                        new Popup().clickSuccess(maxWaitTime);
                    }
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertDesktopPRO(List<String> insertData) {
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
                } else if (i == 9 || i == 18) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 20) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    if (i == 3) {
                        new Popup().clickSuccess(maxWaitTime);
                    }
                    adsGroup.selectGroupByAttribute(insertData.get(i-1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertCamera(List<String> insertData) {
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

    public void insertTV_Sound(List<String> insertData) {
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
                } else if (i == 9 || i == 14) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
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

    public void insertSmart_Watch(List<String> insertData) {
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
                } else if (i == 9 || i == 15) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
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

    public void insertAccessories_Monitor(List<String> insertData) {
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
                } else if (i == 9 || i == 14) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
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

    public void insertAccessories_RAM(List<String> insertData) {
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
                } else if (i == 9 || i == 14) {
                    if (i == 9 && insertData.get(i - 1).equals("Tôi muốn cho tặng miễn phí")) {
                        adsGroup.selectCheckboxValue(insertData.get(i - 1));
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
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
}
