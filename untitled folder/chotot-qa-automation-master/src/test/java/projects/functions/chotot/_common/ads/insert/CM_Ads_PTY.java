package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.pages.InsertAds.AdsGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CM_Ads_PTY extends BasePage {
    final Logger log = LogManager.getLogger(getClass());
    AdsGroup adsGroup;

    public void insertHouse(List<String> insertData, List<String> stepDesc) {
        log.info("Login Successfully");

        adsGroup = new AdsGroup();

        String data;
        String step;
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 10) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 27 || i == 28 || i == 29) {
                    int index = 0;
                    if(i == 28) index = 1;
                    if(i == 29) index = 2;
                    adsGroup.enterNonRequiredDataList(insertData.get(i), index);
                } else if (i == 21) {
                    adsGroup.clickContinue();
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 22) {
                    adsGroup.enterDescription(insertData.get(i));
                } else if (i == 19 || i == 24) {
                    adsGroup.selectDropdownListByText(insertData.get(i), 0);        //Always index = 0
                } else if (i == 17 || i == 18) {
                    adsGroup.addSubNumber(insertData.get(i));
                } else if (i == 25 || i == 26 || i == 30) {
                    adsGroup.selectCheckboxByText(insertData.get(i));
                } else if (i == 9 || i == 16 || i == 23) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                    if(i == 9) adsGroup.clickContinue();
                } else if (i == 3) {
                    adsGroup.enterSelectSuggestionInput(insertData.get(i), 0);
                    adsGroup.clickContinue();
                } else if (i == 0 || i == 1 || i == 2) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 11 || i == 12 || i == 13 || i == 14 || i == 15){
                    int index = 0;
                    if(i == 12) index = 1;
                    if(i == 13) index = 2;
                    if(i == 14) index = 3;
                    if(i == 15) index = 4;
                    adsGroup.enterRequiredDataList(insertData.get(i), index);
                    if(i == 15)
                        adsGroup.clickContinue();
                }
            }
        }
        // Dang tin
        adsGroup.clickContinue();
        adsGroup.clickPostNow();
    }

    public void insertApartment(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();

        String data;
        String step;
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                //12 -> 11
                if (i == 10) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                } else if (i == 12 || i == 13 || i == 19) {
                    int index = 0;
                    if(i == 13) index = 1;
                    adsGroup.clickContinue();
                    adsGroup.enterRequiredDataList(insertData.get(i), index);
                    if(i == 13 || i == 19)
                        adsGroup.clickContinue();
                } else if (i == 20) {
                    adsGroup.enterDescription(insertData.get(i));
                } else if (i == 17 || i == 18 || i == 22) {
                    int index = 0;
                    if (i == 18) index = 1;
                    adsGroup.selectDropdownListByText(insertData.get(i), index);
                    if (i == 18) adsGroup.clickContinue();
                } else if (i == 15 || i == 16) {
                    adsGroup.addSubNumber(insertData.get(i));
                } else if (i == 9 || i == 14 || i == 21) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 3) {
                    adsGroup.enterSelectSuggestionInput(insertData.get(i), 0);
                    adsGroup.clickContinue();
                } else if (i == 23 || i == 27) {
                    adsGroup.selectCheckboxByText(insertData.get(i));
                } else if (i == 24 || i == 25 || i == 26) {
                    int index = 0;
                    if(i == 25) index = 1;
                    if(i == 26) index = 2;
                    adsGroup.enterNonRequiredDataList(insertData.get(i), index);
                } else if (i == 0 || i == 1 || i == 2 || i == 11) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }

        // Dang tin
        adsGroup.clickContinue();
        adsGroup.clickPostNow();
    }

    public void insertLand(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();

        String data;
        String step;
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 10) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                } else if (i == 11 || i == 12 || i == 13 || i == 14) {
                    adsGroup.clickContinue();
                    int index = 0;
                    if (i == 12) index = 1;
                    if (i == 13) index = 2;
                    if (i == 14) index = 3;
                    adsGroup.enterRequiredDataList(insertData.get(i), index);
                    if (i == 14)
                        adsGroup.clickContinue();
                } else if (i == 18) {
                    adsGroup.clickContinue();
                    adsGroup.enterRequiredData(insertData.get(i));
                } else if (i == 19){
                    adsGroup.enterDescription(insertData.get(i));
                } else if (i == 16) {
                    adsGroup.selectDropdownListByText(insertData.get(i), 0);
                } else if (i == 9 || i == 15 || i == 17) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                    if(i == 17)
                        adsGroup.clickContinue();
                } else if (i == 3) {
                    adsGroup.enterSelectSuggestionInput(insertData.get(i), 0);
                    adsGroup.clickContinue();
                } else if (i == 20 || i == 21) {
                    adsGroup.selectCheckboxByText(insertData.get(i));
                    if(i == 21) adsGroup.clickContinue();
                } else if (i == 0 || i == 1 || i == 2) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }

        // Dang tin
        adsGroup.clickPostNow();
    }

    public void insertOffice(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();

        String data;
        String step;
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 10) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                } else if (i == 14) {
                    adsGroup.selectDropdownListByText(insertData.get(i), 0);
                } else if (i == 15) {
                    adsGroup.selectDropdownListByText(insertData.get(i), 1);
                    adsGroup.clickContinue();
                }
                else if (i == 11 || i == 12 || i == 16) {
                    if (i == 12)
                        adsGroup.enterRequiredDataList(insertData.get(i), 1);
                    else
                        adsGroup.enterRequiredDataList(insertData.get(i), 0);
                    if (i == 16)
                        adsGroup.clickContinue();
                } else if (i == 17) {
                    adsGroup.enterDescription(insertData.get(i));
                } else if (i == 9 || i == 13 || i == 18) {
                    if (i == 13) adsGroup.clickContinue();
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 3) {
                    adsGroup.enterSelectSuggestionInput(insertData.get(i), 0);
                    adsGroup.clickContinue();
                } else if (i == 19 || i == 20 || i == 21) {
                    int index = 0;
                    if (i == 19) index = 1;
                    if (i == 20) index = 1;
                    adsGroup.enterRequiredDataList(insertData.get(i), index);
                } else if (i == 22) {
                    adsGroup.selectCheckboxByText(insertData.get(i));
                } else if (i == 0 || i == 1 || i == 2){
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                }
            }
        }

        // Dang tin
        adsGroup.clickContinue();
        adsGroup.clickPostNow();
    }

    public void insertRoom(List<String> insertData, List<String> stepDesc) {
        adsGroup = new AdsGroup();

        String data;
        String step;
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
            step = stepDesc.get(i);
            log.info("STEP: " + step + " -> DATA: " + data);
            if (!data.isEmpty()) {
                if (i == 9) {
                    adsGroup.uploadMultiImagesToAds(insertData.get(i));
                } else if (i == 10 || i == 11) {
                    int index = 0;
                    if (i == 11) index = 1;
                    adsGroup.clickContinue();
                    adsGroup.enterRequiredDataList(insertData.get(i), index);
                } else if (i == 12) {
                    adsGroup.enterNonRequiredDataList(insertData.get(i), 2);
                    adsGroup.clickContinue();
                } else if (i == 13) {
                    adsGroup.clickContinue();
                    adsGroup.enterRequiredData(insertData.get(i));
                    adsGroup.clickContinue();
                } else if (i == 14) {
                    adsGroup.enterDescription(insertData.get(i));
                } else if (i == 8) {
                    adsGroup.selectCheckboxValue(insertData.get(i));
                } else if (i == 7) {
                    adsGroup.enterSelectSuggestionInput(insertData.get(i), 0);
                    adsGroup.clickContinue();
                } else if (i == 4 || i == 5 || i == 6) {
                    int index = 0;
                    if (i == 5) index = 1;
                    if (i == 6) index = 2;
                    adsGroup.selectOptionByText(insertData.get(i), index);
                } else if (i == 0 || i == 1 || i == 2) {
                    adsGroup.selectGroupByAttribute(insertData.get(i), "innerText");
                } else if (i == 15){
                    adsGroup.selectDropdownListByText(insertData.get(i), 0);
                    adsGroup.clickContinue();
                }
            }
        }

        // Dang tin
        adsGroup.clickPostNow();
    }
}
