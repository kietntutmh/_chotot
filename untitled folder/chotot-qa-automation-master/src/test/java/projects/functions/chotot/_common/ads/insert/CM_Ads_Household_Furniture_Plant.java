package projects.functions.chotot._common.ads.insert;

import com.vn.chotot.base.BasePage;
import desktop.pages.InsertAds.AdsGroup;

import java.util.List;

public class CM_Ads_Household_Furniture_Plant extends BasePage {
    AdsGroup adsGroup;
    String data;

    public void insertAdKitchen_Appliance(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdKitchen_Utensil_Dinnerware(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdBed_Bedding(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdBathware(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdFan(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdLighting(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdTable_Chair(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdDrawer_Shelf(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdOrnamental_Plant_Decoration(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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

    public void insertAdOther_Household_Item(List<String> insertData) {
        adsGroup = new AdsGroup();
        for (int i = 0; i < insertData.size(); i++) {
            data = insertData.get(i);
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
}
