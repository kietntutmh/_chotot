package projects.steps.chotot.api.userProfiler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.userProfiler.UserProfiler_Functions;

import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static projects.configuaration.CategoryConfig.*;

public class UserProfiler_Steps extends UserProfiler_Functions {

    @Then("My User Profiler of Category {string} - Ad Type {string} should be PRIVATE")
    public void profiler_shoule_be_pri(String cate, String adType){
        checkUserProfilerIsPrivate(global_accountID, cate, adType);
    }

    @Then("My User Profiler of Category {string} - Ad Type {string} should be PRO")
    public void profiler_shoule_be_pro(String cate, String adType){
        checkUserProfilerIsPro(global_accountID, cate, adType);
    }

    @Then("My User Profiler of Category {string} - Ad Type {string} should be SHOP")
    public void profiler_shoule_be_shop(String cate, String adType){
        checkUserProfilerIsShop(global_accountID, cate, adType);
    }

    @Then("My NEXT User Profiler of Category {string} - Ad Type {string} should be PRIVATE")
    public void my_NEXT_User_Profiler_of_Category_Ad_Type_should_be_PROs(String cate, String adType) {
        checkUserProfilerNextIsPrivate(global_accountID, cate, adType);
    }

    @Then("My NEXT User Profiler of Category {string} - Ad Type {string} should be PRO")
    public void my_NEXT_User_Profiler_of_Category_Ad_Type_should_be_PROss(String cate, String adType) {
        checkUserProfilerNextIsPro(global_accountID, cate, adType);
    }

    @Then("User Profile of v1-internal-profiler-account_id should be {string} in Category {string}")
    public void api_v1_internal_profiler_account_id_should_have_value_with_query(String userProfile, String category) {
        switch (category.toUpperCase()){
            case "PTY":
                verifyUserProfilerAPI_InternalProfiler(global_accountID, CATEID_PTY, userProfile);
                break;
            case "VEH":
                verifyUserProfilerAPI_InternalProfiler(global_accountID, CATEID_VEH, userProfile);
                break;
            case "ELT":
                verifyUserProfilerAPI_InternalProfiler(global_accountID, CATEID_ELT, userProfile);
                break;
        }
    }

    @Then("User Profile of Get Multi Profiler with POST v1-internal-profiler should be {string} in Category {string}")
    public void user_Profile_of_Get_Multi_Profiler_with_POST_v1_internal_profiler(String userProfile, String category) {
        switch (category.toUpperCase()){
            case "PTY":
                verifyUserProfilerAPI_MultipleProfiler(global_accountID, CATEID_PTY, userProfile);
                break;
            case "VEH":
                verifyUserProfilerAPI_MultipleProfiler(global_accountID, CATEID_VEH, userProfile);
                break;
            case "ELT":
                verifyUserProfilerAPI_MultipleProfiler(global_accountID, CATEID_ELT, userProfile);
                break;
        }
    }


    //v1-internal-profiler-accountID

    @Then("API v1-internal-profiler-accountID should be SHOP")
    public void api_v1_intersnal_profiler_should_be() {
        verifyAPIGet_InternalProfiler_PTYShop();
    }

    @Then("API v1-internal-profiler-accountId should be PRO with Cate {string} AdType {string}")
    public void api_v1_public_new_profiler_accountId_cat_id_categoryID_ad_type_s_should_be(String cateID, String adType) {
        verifyAPIGet_InternalProfiler_PTY("pro", cateID, adType);
    }

    @Then("API v1-internal-profiler-accountId should be PRIVATE with Cate {string} AdType {string}")
    public void api_v1_public_new_profilesr_accountId_cat_id_categoryID_ad_type_s_should_be(String cateID, String adType) {
        verifyAPIGet_InternalProfiler_PTY("private", cateID, adType);
    }

    @Then("API v1-internal-profiler-accountId should be NULL with Cate {string} AdType {string}")
    public void api_v1_public_new_profilesr_accountId_cat_id_categoryID_ad_stype_s_should_be(String cateID, String adType) {
        verifyAPIGet_InternalProfiler_PTY("null", cateID, adType);
    }

    @Then("API v1-internal-profiler-accountId should be SHOP with Cate {string} AdType {string}")
    public void api_v1_public_new_profilesr_accountId_cast_id_categoryID_ad_type_s_should_be(String cateID, String adType) {
        verifyAPIGet_InternalProfiler_PTY("shop", cateID, adType);
    }

    @Then("API POST v1-internal-profiler-accountID should be SHOP")
    public void api_v1_internals_profiler_should_be() {
        verifyAPIGet_InternalProfilerPost_PTYShop();
    }

    @Then("API POST v1-internal-profiler-accountID should be NULL with Cate {string} AdType {string}")
    public void api_v1_internals_profiler_shosuld_bse(String cate, String adType) {
        verifyAPIGet_InternalProfilerPost_PTY("null", cate, adType);
    }

    @Then("API POST v1-internal-profiler-accountID should be PRO with Cate {string} AdType {string}")
    public void api_v1_internals_profiler_should_bse(String cate, String adType) {
        verifyAPIGet_InternalProfilerPost_PTY("pro", cate, adType);
    }

    @Then("API POST v1-internal-profiler-accountID should be PRIVATE with Cate {string} AdType {string}")
    public void api_v1_Sinternals_profiler_should_bse(String cate, String adType) {
        verifyAPIGet_InternalProfilerPost_PTY("private", cate, adType);
    }

    @Then("API POST v1-internal-profiler-accountID should be SHOP with Cate {string} AdType {string}")
    public void api_v1_Sinternals_profilser_should_bse(String cate, String adType) {
        verifyAPIGet_InternalProfilerPost_PTY("shop", cate, adType);
    }


    //v1-public-profiler-accountId
    @Then("API v1-public-profiler-accountId should be SHOP")
    public void api_v1_public_pssrofiler_check_profiler_accountID_should_be() {
        verifyAPIGet_PublicProfiler_PTYShop();
    }

    @Then("API v1-public-profiler-accountId should be PRIVATE with Cate {string} AdType {string}")
    public void api_v1_public_pssrofiler_check_profiler_accountIDs_should_be(String cateID, String adType) {
        verifyAPIGet_PublicProfiler_PTY(cateID, adType, "private");
    }

    @Then("API v1-public-profiler-accountId should be PRO with Cate {string} AdType {string}")
    public void api_v1_public_pssrofiler_check_profiler_accountIDs_shosuld_be(String cateID, String adType) {
        verifyAPIGet_PublicProfiler_PTY(cateID, adType, "pro");
    }

    @Then("API v1-public-profiler-accountId should be SHOP with Cate {string} AdType {string}")
    public void api_v1_public_pssrofiler_check_profiler_accounstIDs_shosuld_be(String cateID, String adType) {
        verifyAPIGet_PublicProfiler_PTY(cateID, adType, "shop");
    }

    @Then("API v1-public-profiler-accountId should be NULL with Cate {string} AdType {string}")
    public void api_v1_publics_pssrofiler_check_profiler_accountIDs_shosuld_be(String cateID, String adType) {
        verifyAPIGet_PublicProfiler_PTY(cateID, adType, "null");
    }



    //v1-public-profiler-check-profiler-accountID
    @Then("API v1-public-profiler-check-profiler-accountID should be SHOP")
    public void api_v1_public_profiler_check_profiler_accountID_should_be() {
        verifyAPIGet_PublicCheckProfiler_PTYShop();
    }

    @Then("API v1-public-profiler-check-profiler-accountID should be PRIVATE with Cate {string} AdType {string}")
    public void api_v1_public_profiler_check_profiler_accountIsD_should_be(String cateID, String adType) {
        verifyAPIGet_PublicCheckProfiler_PTY(cateID, adType, "private");
    }

    @Then("API v1-public-profiler-check-profiler-accountID should be PRO with Cate {string} AdType {string}")
    public void api_v1_public_profiler_check_profiler_accountIsD_should_bse(String cateID, String adType) {
        verifyAPIGet_PublicCheckProfiler_PTY(cateID, adType, "pro");
    }

    @Then("API v1-public-profiler-check-profiler-accountID should be SHOP with Cate {string} AdType {string}")
    public void api_v1_public_profiler_check_profiler_accountIsD_sshould_bse(String cateID, String adType) {
        verifyAPIGet_PublicCheckProfiler_PTY(cateID, adType, "shop");
    }

    @Then("API v1-public-profiler-check-profiler-accountID should be NULL with Cate {string} AdType {string}")
    public void api_v1_public_profiler_check_profiler_accountIssD_should_bse(String cateID, String adType) {
        verifyAPIGet_PublicCheckProfiler_PTY(cateID, adType, "null");
    }

    //v1-private-profiler

    @Then("API v1-private-profiler should be SHOP")
    public void api_v1_private_profiler_should_be() {
        verifyAPIGet_PrivateProfiler_PTYShop(global_accessToken);
    }

    @Then("API v1-private-profiler should be PRIVATE with Cate {string} AdType {string}")
    public void api_v1_private_profiler_sshould_be(String category, String adType) {
        verifyAPIGet_PrivateProfiler_PTY(global_accessToken, category, adType, "private");
    }

    @Then("API v1-private-profiler should be PRO with Cate {string} AdType {string}")
    public void api_v1_private_profiler_sshould_bse(String category, String adType) {
        verifyAPIGet_PrivateProfiler_PTY(global_accessToken, category, adType, "pro");
    }

    @Then("API v1-private-profiler should be SHOP with Cate {string} AdType {string}")
    public void api_v1_private_profiler_ssshould_bse(String category, String adType) {
        verifyAPIGet_PrivateProfiler_PTY(global_accessToken, category, adType, "shop");
    }

    @Then("API v1-private-profiler should be NULL with Cate {string} AdType {string}")
    public void api_v1_privates_profiler_sshould_bse(String category, String adType) {
        verifyAPIGet_PrivateProfiler_PTY(global_accessToken, category, adType, "null");
    }

    //REASONS
    @Given("Create a User Profiler new reason")
    public void create_a_new_reason_of_User_Profiler() {
        setReasonName(userProfiler_Reason_Create());
    }

    @When("Update the User Profiler reason with name, short detail, long detail")
    public void update_the_new_reason_of_User_Profiler() {
        userProfiler_Reason_UpdateAll(true, true, true);
    }

    @When("Remove the User Profiler reason of User Profiler")
    public void remove_the_new_reason_of_User_Profiler() {
        userProfiler_Reason_Remove();
    }

    @Then("The User Profiler reason should be deleted when Get by name")
    public void the_new_reason_should_display_when_Get_by_name() {
        userProfiler_Reason_VerifyDeleted(true);
    }

    @Then("The User Profiler reason should exist when Get by name")
    public void the_new_reason_should_not_display_when_Get_by_name() {
        userProfiler_Reason_VerifyDeleted(false);
    }

    @Then("The User Profiler reason should be deleted when Get all reasons")
    public void the_new_reason_should_display_when_Get_all_reasons() {
        userProfiler_Reason_VerifyDeleted_GetAll(true);
    }

    @Then("The User Profiler reason should exist when Get all reasons")
    public void the_new_reason_should_not_display_when_Get_all_reasons() {
        userProfiler_Reason_VerifyDeleted_GetAll(false);
    }



}
