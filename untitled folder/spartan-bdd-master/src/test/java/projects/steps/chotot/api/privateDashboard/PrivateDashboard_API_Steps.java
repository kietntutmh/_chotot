package projects.steps.chotot.api.privateDashboard;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertFashion;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertOther;
import projects.functions.chotot.privateDashboard.PrivateDashboard_API_Functions;

public class PrivateDashboard_API_Steps extends PrivateDashboard_API_Functions {
    private CM_API_Ads_InsertFashion cm_api_ads_insertFashion = new CM_API_Ads_InsertFashion();
    private CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();

    @Given("I pose a new Food Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Food_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertOther.insertNewFoodAd(action));
    }

    @Given("I pose a new Pro Food Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Food_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertOther.insertNewFoodAdPro(action));
    }

    @Given("I pose a new Clothes Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Clothes_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdClothes(action));
    }

    @Given("I pose a new Pro Clothes Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Clothes_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdClothes(action));
    }

    @Given("I pose a new Watch Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Watch_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdWatch(action));
    }

    @Given("I pose a new Pro Watch Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Watch_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdWatch(action));
    }

    @Given("I pose a new Shoes Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Shoes_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdShoes(action));
    }

    @Given("I pose a new Pro Shoes Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Shoes_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdShoes(action));
    }

    @Given("I pose a new Handbag Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Handbag_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdHandbag(action));
    }

    @Given("I pose a new Pro Handbag Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Handbag_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdHandbag(action));
    }

    @Given("I pose a new Perfume Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Perfume_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdPerfume(action));
    }

    @Given("I pose a new Pro Perfume Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Perfume_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdPerfume(action));
    }

    @Given("I pose a new Accessories Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Accessories_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdAccessories(action));
    }

    @Given("I pose a new Pro Accessories Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_pro_Accessories_Ad_and_Chotot_accepts_my_new_Ad(String action) {
        setAdID(cm_api_ads_insertFashion.insertNewAdAccessories(action));
    }


    @Given("I pose {string} new Food Ad and Chotot {string} my New Ad")
    public void i_pose_a_new_Food_Ad_and_Chotot_accepts_my_new_Ad(String numberOfAd, String action) {
        CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
        for (int i = 0; i < Integer.parseInt(numberOfAd) - 1; i++) {
            cm_api_ads_insertOther.insertNewFoodAd(action);
        }
        setAdID(cm_api_ads_insertOther.insertNewFoodAd(action));        //get last adid
    }

    @Then("My new Ad should be displayed on Private Dashboard")
    public void my_new_Ad_should_be_displayed_on_Private_Dashboard() {
        checkAdOnPrivateDashboard(getAdID());
    }

    @Then("My new 31st Ad should not be displayed on Private Dashboard")
    public void my_new_31st_Ad_should_be_displayed_on_Private_Dashboard() {
        checkAdNotOnPrivateDashboard(getAdID());
    }

    @Then("My new Ad should not be displayed on Private Dashboard")
    public void my_new_Ad_should_not_be_displayed_on_Private_Dashboard() {
        checkAdNotOnPrivateDashboard(getAdID());
    }
}

