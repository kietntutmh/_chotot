package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_Job extends BasePage {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;

    public void insertRecruitment(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 3 || i == 4 || i == 5) {
                    switch (i) {
                        case 3: {
                            adsGroup.selectOptionByText(insertData.get(i - 1), 0);
                            break;
                        }
                        case 4: {
                            adsGroup.selectOptionByText(insertData.get(i - 1), 1);
                            break;
                        }
                        default: {
                            adsGroup.selectOptionByText(insertData.get(i - 1), 2);
                            adsGroup.clickContinue();
                        }
                    }
                } else if (i == 6) {
                    adsGroup.enterSelectSuggestionInput(insertData.get(i - 1), 0);
                } else if (i == 7) {
                    adsGroup.enterHouseNumber(insertData.get(i - 1));
                } else if (i == 11 || i == 12 || i == 14 || i == 18 || i == 19 || i == 21) {
                    switch (i) {
                        case 11:
                        case 14:
                        case 18: {
                            adsGroup.selectDropdownListByText(insertData.get(i - 1), 0);
                            break;
                        }
                        case 21: {
                            adsGroup.selectDropdownListByText(insertData.get(i - 1), 2);
                            break;
                        }
                        default: {
                            adsGroup.selectDropdownListByText(insertData.get(i - 1), 1);
                            break;
                        }
                    }
                } else if (i == 24) {
                    adsGroup.enterRequiredData(insertData.get(i - 1));
                } else if (i == 8) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 9) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 10 || i == 13 || i == 22 || i == 23) {
                    switch (i) {
                        case 22: {
                            adsGroup.enterRequireDataHaveIndex(insertData.get(i - 1), 1);
                            break;
                        }
                        case 23: {
                            adsGroup.enterRequireDataHaveIndex(insertData.get(i - 1), 2);
                            adsGroup.clickContinue();
                            break;
                        }
                        default: {
                            adsGroup.enterRequiredData(insertData.get(i - 1));
                            break;
                        }
                    }
                } else if (i == 15 || i == 16 || i == 17 || i == 20) {
                    switch (i) {
                        case 15:
                        case 20: {
                            adsGroup.enterOptionalData(insertData.get(i - 1), 0);
                            break;
                        }
                        case 16: {
                            adsGroup.enterOptionalData(insertData.get(i - 1), 1);
                            break;
                        }
                        case 17: {
                            adsGroup.enterOptionalData(insertData.get(i - 1), 2);
                            adsGroup.clickContinue();
                            break;
                        }
                    }
                } else if (i == 25) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i - 1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }

    public void insertFind_Job(List<String> insertData) {
        // Initiate object
        adsGroup = new AdsGroup();

        // Select or enter data
        for (int i = 1; i <= insertData.size(); i++) {
            log.info("Step " + (i));
            if (!insertData.get(i - 1).isEmpty()) {
                if (i == 7 || i == 17) {
                    adsGroup.selectCheckboxValue(insertData.get(i - 1));
                } else if (i == 6) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i - 1));
                } else if (i == 12 || i == 19 || i == 20) {
                    if (i == 12) {
                        adsGroup.enterRequireDataHaveIndex(insertData.get(i - 1), 2);
                        adsGroup.clickContinue();
                    } else {
                        adsGroup.enterRequiredData(insertData.get(i - 1));
                    }
                } else if (i == 10 || i == 11) {
                    if (i == 10) {
                        adsGroup.enterOptionalData(insertData.get(i - 1), 0);
                    } else {
                        adsGroup.enterOptionalData(insertData.get(i - 1), 1);
                    }
                } else if (i == 21) {
                    adsGroup.enterDescription(insertData.get(i - 1));
                } else if (i == 8 || i == 9 || i == 13 || i == 18) {
                    if (i == 9) {
                        adsGroup.selectDropdownListByText(insertData.get(i - 1), 2);
                    } else if (i == 8 || i == 18) {
                        adsGroup.selectDropdownListByText(insertData.get(i - 1), 1);
                    } else {
                        adsGroup.selectDropdownListByText(insertData.get(i - 1), 0);
                    }
                } else if (i == 3 || i == 4 || i == 5 || i == 14 || i == 15 | i == 16) {
                    if (i == 3 || i == 14) {
                        adsGroup.selectOptionByText(insertData.get(i - 1), 0);
                    } else if (i == 4 || i == 15) {
                        adsGroup.selectOptionByText(insertData.get(i - 1), 1);
                    } else {
                        adsGroup.selectOptionByText(insertData.get(i - 1), 2);
                        if (i != 16) {
                            adsGroup.clickContinue();
                        }
                    }
                } else {
                    adsGroup.selectGroupByAttribute(insertData.get(i - 1), "innerText");
                }
            }
        }

        // Click View and Post
        adsGroup.clickViewAndPost();

        // Click PostNow
        adsGroup.clickPostNow();
    }
}
