package projects.steps.chotot.api.shops;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.functions.chotot._common.api.ads.insert.CM_API_Ads_InsertOther;
import projects.functions.chotot.shops.C2CShop_Food_API_Functions;

import static api.feature.profile.UpdateProfile.updateProfileEmail;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.topupDongTotWithMomo_200k;
import static projects.functions.chotot.privateDashboard.PrivateDashboard_API_Functions.checkAdNotOnPrivateDashboard;
import static projects.functions.chotot.privateDashboard.PrivateDashboard_API_Functions.checkAdOnPrivateDashboard;

public class C2CShop_Food_API_Steps extends C2CShop_Food_API_Functions {

    @Given("I register my new account to create a Shop")
    public void i_register_my_new_account_to_create_a_Shop() {
        setOwnerToken();
//        getC2CShop_InQueues_NewCount();
        topupDongTotWithMomo_200k(getOwnerPhone());
    }

    @Given("I use my new account to register a New {string} Shop successfully")
    public void i_use_my_account_register_a_new_Temp_Food_Shop(String cate) {
        setOwnerToken();
        shopTempName = createTempShop(cate);
        updateTempShop(cate);
        payForTempShopC2C(cate, true);
        activateNewShopWithImages();
        approveNewShopByAlias();
    }

    @Given("I register a New {string} Shop successfully")
    public void i_register_a_new_Temp_Food_Shop(String cate) {
        shopTempName = createTempShop(cate);
        updateTempShop(cate);
        payForTempShopC2C(cate, true);
        activateNewShopWithImages();
        approveNewShopByAlias();
    }

    @Given("I update my email to be {string} to receive Extended Shop Confirm Email")
    public void i_update_my_email_to_be(String email) {
        updateProfileEmail(getOwnerToken(), email);
    }

    @Given("I should receive an Email sent to {string} to confirm that C2C Shop has beed extended successfully")
    public void i_update_my_email_to_be_should(String email) {
       checkExtendedShopConfirmEmail(email);
    }


    @Given("I create a new Temp {string} Shop")
    public void i_create_a_new_Temp_Food_Shop(String cate) {
        shopTempName = createTempShop(cate);
    }

    @Given("I create and update a new Temp {string} Shop")
    public void i_register_and_update_a_new_Temp_Food_Shop(String cate) {
        shopTempName = createTempShop(cate);
        updateTempShop(cate);
    }




    @Given("I create and update my Temp {string} Shop but missing {string}")
    public void i_register_and_update_a_new_Temp_Food_Shop(String cate, String missedParam) {
        shopTempName = createTempShop(cate);
        updateTempShopMissingParam(cate, missedParam);
    }

    @Given("I cannot update my Temp {string} Shop when Chotot is reviewing")
    public void i_can_not_update_when_reviewing(String cate) {
        updateTempShop(cate, "400");
    }

    @Given("I cannot update my New {string} Shop when Chotot is reviewing")
    public void i_can_not_update_new_when_reviewing(String cate) {
        updateNewShop_Excep_URL_Name("400");
    }

    @Given("I update all information for my New {string} Shop except url, name")
    public void i_full_fill_the_informations_for_my_New_Food_Shop(String cate) {
        updateNewShop_Excep_URL_Name();
    }

    @Given("I update all information for my Temp {string} Shop")
    public void i_full_fill_the_informations_for_my_Temp_Food_Shop(String cate) {
        updateTempShop(cate);
    }

    @Given("I should not be able to activate my New Shop")
    public void i_should_not_activate_my_New_Shop() {
        activateNewShop("400");
    }

    @Given("I activate my New Shop")
    public void i_activate_my_New_Shop() {
//        activateNewShop();
        activateNewShopWithImages();
    }

    @Given("I create another new {string} Shop")
    public void i_register_another_new_Food_Shop(String cate) {
        shopTempName = createTempShop(cate);
    }

    @Then("I should see {string} Temp Shop created")
    public void i_should_see_Temp_Shop_created(String numberOfTempShop) {
        Assert.assertEquals(getNumberOfTempShop(), Integer.parseInt(numberOfTempShop));
        checkTempShopExist(shopTempName);
    }

    @Then("My Temp Food Shop should not be active")
    public void my_Shop_should_not_be_active() {
        checkTempShopInactive(shopTempName);
    }

    @Given("All my Temp {string} Shop settings should be correct")
    public void all_my_Temp_Shop_settings_should_be_correct(String cate) {
        verifyTempShopSettings(cate);
    }

    @Given("All my Temp {string} Shop settings should be correct after updated")
    public void all_my_Temp_Shop_settings_should_be_correct_after_updated(String cate) {
        verifyTempShopSettings_AfterUpdate(cate);
    }

    @Given("I update the category of my Temp {string} Shop")
    public void i_update_the_category_of_my_Temp_Shop(String cate, DataTable dataTable) {
        fakeCateID = dataTable.cell(0, 1);
        updateTempShop_CategoryID(fakeCateID);
    }

    @Then("My Temp {string} Shop's Category ID should not be updated")
    public void my_Temp_Shop_s_Category_ID_should_not_be_updated(String string) {
        verifyTempShopSettings_CategoryID(fakeCateID);
    }

    @When("Chotot rejects my New Shop URL")
    public void chotot_rejectes_my_Temp_Shop_submit() {
        rejectNewShopByAlias_NewUrl();
    }

    @When("Chotot rejects my New Shop Name")
    public void chotot_rejectes_my_Temp_Shop_name() {
        rejectNewShopByAlias_NewName();

    }

    @When("Chotot rejects my New Shop")
    public void chotot_reject_my_Temp_Shop() {
        rejectNewShopByAlias();
    }

    @When("Chotot approves my New Shop without updating information")
    public void chotot_approves_my_Temp_Shop_without_update() {
        approveNewShopByAlias();
    }

    @When("Chotot approves my New Shop URL")
    public void chotot_approves_my_Temp_Shop_submit() {
        approveNewShopByAlias_NewURL();
    }

    @When("Chotot approves my New Shop Name")
    public void chotot_approves_my_New_Shop_name() {
        approveNewShopByAlias_NewName();
    }

    @When("Chotot approves my New Shop")
    public void chotot_approves_my_New_Shop() {
        approveNewShopByAlias();
    }

    @When("Chotot approves the new Name of my Shop")
    public void chotot_approves_my_the_new_name_of_Shop() {
        approveNewShopByAlias_NewName();
    }

    @When("Chotot allows me editing URL after my Shop is accepted")
    public void chotot_allows_me_editing_url_adfter_my_shop_is_accepted() {
        allowEditURL();
    }

    @When("Chotot allows me editing Shop Name after my Shop is accepted")
    public void chotot_allows_me_editing_name_adfter_my_shop_is_accepted() {
        allowEditName();
    }

    @When("Chotot denies me editing URL after my Shop is accepted")
    public void chotot_denis_me_editing_url_adfter_my_shop_is_accepted() {
        denyEditURL();
    }

    @When("Chotot denies me editing Shop Name after my Shop is accepted")
    public void chotot_denis_me_editing_name_adfter_my_shop_is_accepted() {
        denyEditName();
    }

    @When("Chotot approves my New Shop with updating all information")
    public void chotot_approves_my_Temp_Shop_with_update() {
        approveNewShopByAlias_UpdateInfo(true);
    }

    @When("Chotot approves my New Shop with updating all information except url")
    public void chotot_approves_my_Temp_Shop_with_update_excepturl() {
        approveNewShopByAlias_UpdateInfo(false);
    }

    @When("Chotot approves my New Shop with updating param {string}")
    public void chotot_approves_my_Temp_Shop_with_update_excepturl(String updated_param) {
        approveNewShopByAlias_UpdateParam(updated_param);
    }

    @Then("The URL of my {string} Shop should not be updated to the new one")
    public void the_url_of_my_shop_should_not_be_updated_correctly(String cate) {
        checkShopActiveAfterReject(cate);
    }

    @Then("The Shop Name of my {string} Shop should not be updated to the new one")
    public void the_name_of_my_shop_should_not_be_updated_correctly(String cate) {
        checkShopActiveAfterReject(cate);
    }

    @Then("The URL of my {string} Shop should be updated correctly")
    public void the_url_of_my_shop_should_be_updated_correctly(String cate) {
        checkShopActiveAfterApprove(cate);
    }

    @Then("The Shop Name of my {string} Shop should be updated correctly")
    public void the_name_of_my_shop_should_be_updated_correctly(String cate) {
        checkShopActiveAfterApprove(cate);
    }

    @Then("My {string} Shop should be active with correct information")
    public void my_Shop_should_be_active(String cate) {
        checkShopActiveAfterApprove(cate);
    }

    @Then("I can visit my {string} Shop Dashboard")
    public void i_can_visit_my_Shop_Dashboard(String cate) {
        checkShopURLCreated();
        navigateToShopURL(false);
    }

    @Then("I should be able to visit my {string} Shop Dashboard")
    public void i_should_be_able_to_visit_my_Shop_Dashboard(String cate) {
        navigateToShopURL(false);
    }

    @Then("I should not be able to visit my {string} Shop Dashboard")
    public void i_should_not_be_able_to_visit_my_Shop_Dashboard(String cate) {
        navigateToShopURL(false, "400");
    }

    @Then("I edit my Shop Name to a new one")
    public void i_edit_my_shop_name_to_new_one() {
        updateNewShop_Name("200");
    }

    @Then("I edit my Shop Name to a bad one")
    public void i_edit_my_shop_name_to_bad_one() {
        updateNewShop_Name("200");
    }

    @Then("I edit my URL to a new one")
    public void i_edit_my_url_to_new_one() {
        updateNewShop_URL();
    }

    @Then("I edit my URL to a bad one")
    public void i_edit_my_url_to_bad_one() {
        updateNewShop_URL();
    }

    @Then("I cannot edit my URL again")
    public void i_cannot_edit_my_URL_again() {
        updateNewShop_URL("400");
    }

    @Then("I cannot edit my Shop Name again")
    public void i_cannot_edit_my_shop_name_again() {
        updateNewShop_Name("400");
    }

    @Then("I can update all information for my Temp {string} Shop")
    public void i_can_update_after_paid(String cate) {
        updateNewShop_AfterPaid_All();
    }

    @Then("Buyers can visit my {string} Shop")
    public void buyers_can_visit_my_Shop(String string) {
        navigateToShopURL(true);
    }

    @Then("Buyers should not be able to visit my {string} Shop")
    public void buyers_should_not_visit_my_Shop(String string) {
        navigateToShopURL(true, "400");
    }

    @Then("Buyers should be able to visit my {string} Shop")
    public void buyers_should_visit_my_Shop(String string) {
        navigateToShopURL(true);
    }

    @When("I pay for my Temp {string} Shop")
    public void i_pay_for_my_Temp_Shop(String cate) {
        payForTempShopC2C(cate, true);
    }

    @Then("I cannot pay for my {string} Shop order when I don't update required information")
    public void the_shop_order_can_not_be_paid(String cate) {
        payForTempShopC2C(cate, true, "400");
    }

    @Then("My New Shop should appear in Chotot Shop Queue")
    public void my_New_Shop_appears_in_Chotot_Queue() {
//        moveNewShopToQueue();
        verifyNewShopInQueue();
    }

    @Then("My New Shop shouldn't appear in Chotot Shop Queue")
    public void my_New_Shop_shouldnot_appears_in_Chotot_Queue() {
        verifyNewShopNotInQueue();
    }

    @Then("Chotot CS should see AllowEditURL is {string}")
    public void chotot_CS_should_see_AllowEditURL_is_off(String trueFalse) {
        checkAllowEditURL(Boolean.parseBoolean(trueFalse));
    }

    @Then("Chotot CS should see AllowEditName is {string}")
    public void chotot_CS_should_see_AllowEditName_is_off(String trueFalse) {
        checkAllowEditName(Boolean.parseBoolean(trueFalse));
    }

    @When("I close my Shop")
    public void I_close_my_shop() {
        userCloseShop("200");
    }

    @When("I should not be able to close my Shop again")
    public void I_close_my_shop_again() {
        userCloseShop("400");
    }

    @Deprecated
    @When("Chotot expires my Shop")
    public void chotot_expires_to_block_my_shop() {
        expireShop();
    }

    @When("I extend my free {string} Shop with {string} months")
    public void i_extend_my_shop(String cate, String months) {
//        createFreeTokenToExtend(months);
        extendShop(months);
    }

    @When("Chotot blocks my Shop")
    public void chotot_allowed_to_block_my_shop() {
        csBlockShop();
    }

    @When("Chotot unblocks my Shop")
    public void chotot_unblock_my_shop() {
        csUnBlockShop();
    }

    @When("Chotot should be able to block my Shop")
    public void chotot_should_be_able_to_block_my_shop() {
        csBlockShop();
    }

    @When("Chotot is reviewing my request")
    public void chotot_is_reviewing_my_request() {
    }

    @Then("I should be able to create another new {string} Shop")
    public void i_should_be_able_to_create_another_new_shop(String cate) {
        createTempShop(cate, "200");
        updateTempShop(cate);
        payForTempShopC2C(cate, true, "200");
        activateNewShopWithImages("200");
    }

    @Then("I should not be able to create another new {string} Shop")
    public void i_should_not_be_able_to_create_another_new_shop(String cate) {
        createTempShop(cate);
        updateTempShop(cate);
        payForTempShopC2C(cate, true, "400");
        activateNewShopWithImages("400");
    }

    @Then("My Shop Status is {string}")
    public void my_shop_status_is(String status) {
        checkShopStatus(status);
    }

    @Then("My Shop is blocked")
    public void my_shop_id_deleted() {
        checkShopIsBlocked();
    }

    @Then("My Shop is unblocked")
    public void my_shop_id_unblocked() {
        checkShopIsUnblocked();
    }

    @When("I cannot insert a new Food Ad on Listing")
    public void i_cannot_insert_a_new_food_ad() {
        CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
        cm_api_ads_insertOther.insertNewFoodAdC2CShop(getOwnerToken(), "accept", "400");
    }

    @When("I insert a new Food Ad onto my {string} Shop")
    public void i_insert_a_new_food_ad(String cate) {
        CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
        adID = cm_api_ads_insertOther.insertNewFoodAdC2CShop(getOwnerToken(), "accept");
    }

    @When("I insert {string} new Food Ad on Listing")
    public void i_insert_number_of_new_food_ad(String numberOfAd) {
        CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++){
            cm_api_ads_insertOther.insertNewFoodAdC2CShop(getOwnerToken(), "accept");
        }
    }

    @When("I can insert a new Food Ad onto my {string} Shop")
    public void i_can_insert_a_new_food_ad(String cate) {
        CM_API_Ads_InsertOther cm_api_ads_insertOther = new CM_API_Ads_InsertOther();
        adID = cm_api_ads_insertOther.insertNewFoodAdC2CShop(getOwnerToken(), "accept");
    }

    @When("My new Food Ad should display on my Shop")
    public void my_new_food_ad_should_display_on_my_shop() {
        checkNewAdOnC2CShopDashboard(adID);
    }

    @When("My new Food Ad should not display on my Shop")
    public void my_new_food_ad_should_not_display_on_my_shop() {
        checkNewAdNotOnC2CShopDashboard(adID);
    }

    @When("My new Food Ad should display on Private Dashboard")
    public void my_new_food_ad_should_display_on_private_dashboard() {
        checkAdOnPrivateDashboard(adID);
    }

    @When("My new Food Ad should not display on Private Dashboard")
    public void my_new_food_ad_should_not_display_on_private_dashboard() {
        checkAdNotOnPrivateDashboard(adID);
    }


    @Given("I register, pay and activate my new {string} Shop")
    public void i_register_pay_a_new_Shop(String cate, DataTable table) {
        shopTempName = createTempShop(cate);
        setTmp_name(table.row(1).get(0));
        setTmp_address(table.row(1).get(1));
        setTmp_desc(table.row(1).get(2));
        String url = table.row(1).get(3);
        updateTempShop(getOwnerToken(), cate, url, "200");
        payForTempShopC2C(cate, true);
        activateNewShopWithImages();
    }


    @When("Chotot approves my New Shop with updating information")
    public void chotot_approves_my_New_Shop_with_updating_information(DataTable table) {
        String name = table.row(1).get(0);
        String address = table.row(1).get(1);
        String desc = table.row(1).get(2);
        String url = table.row(1).get(3);
        approveNewShopByAliasUpdateInfo(name, address, desc, url);
        setTmp_name(name);
        setTmp_address(address);
        setTmp_desc(desc);
    }

}
