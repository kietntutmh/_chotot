package projects.steps.chotot.api.escrow;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.functions.chotot.escrow.Escrow_API_Functions;

import static api.configuration.DataConfig.newUserPhone;
import static api.configuration.DataConfig.tempListID;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;

public class Escrow_API_Steps extends Escrow_API_Functions {
    // LINKING STEPS

    @When("I link my Account to a Payoo account that is valid")
    public void i_link_my_Account_to_a_valid_Payoo_account() {
        requestLinkToPayoo_validAccount(global_accessToken);
    }

    @When("I link my Account as a Seller to a Payoo account that is valid")
    public void i_link_my_Account_As_A_Seller_to_a_valid_Payoo_account() {
        requestLinkToPayoo_validAccount(getSellerToken());
        verifyLinkToPayoo_validOTP(getSellerToken());
    }

    @When("I link my Account as a Buyer to a Payoo account that is valid")
    public void i_link_my_Account_As_A_Buyer_to_a_valid_Payoo_account() {
        requestLinkToPayoo_validAccount(getBuyerToken());
        verifyLinkToPayoo_validOTP(getBuyerToken());
    }

    @When("I cannot link my Account to a Payoo account that is locked")
    public void i_link_my_Account_to_a_lockesd_Payoo_account() {
        requestLinkToPayoo_lockedAccount(global_accessToken);
    }

    @When("I can link my Account to a Payoo account that is linked as a main account")
    public void i_link_my_Account_to_a_lsocked_Payoo_account() {
        requestLinkToPayoo_linkedAccount_main(global_accessToken);
    }

    @When("I cannot link my Account to a Payoo account that is linked as a sub account")
    public void i_link_my_Account_to_a_locked_Pasyoo_account() {
        requestLinkToPayoo_linkedAccount_sub(global_accessToken);
    }

    @When("I cannot link my Account to a Payoo account that has expired OTP")
    public void i_link_my_Acscount_to_a_locked_Payoo_account() {
        requestLinkToPayoo_expiredOTPAccount(global_accessToken);
    }

    @When("I cannot link my Account to a Payoo account that is non-private")
    public void i_link_my_Account_to_a_locked_Payoso_account() {
        requestLinkToPayoo_nonprivateAccount(global_accessToken);
    }

    @When("I cannot link my Account to a Payoo account that overs SMS Limitation per day")
    public void i_link_my_Account_to_a_lockeds_Payoo_account() {
        requestLinkToPayoo_overSMSLimitationAccount(global_accessToken);
    }

    @When("I cannot link my Account to a Payoo account that is not verified Passport")
    public void i_link_my_Account_to_a_lockeds_Payoso_account() {
        requestLinkToPayoo_notverifiedAccount(global_accessToken);
    }

    @Then("My Account should be linked to Payoo account after I verified with valid OTP")
    public void i_verify_Payoo_linking_with_the_valid_OTP() {
        verifyLinkToPayoo_validOTP(global_accessToken);
    }

    @Then("My Account should be linked to Payoo account as a Seller after I verified with valid OTP")
    public void seller_verify_Payoo_linking_with_the_valid_OTP() {
        verifyLinkToPayoo_validOTP(getSellerToken());
    }

    @Then("My Account should be linked to Payoo account as a Buyer after I verified with valid OTP")
    public void bueyr_verify_Payoo_linking_with_the_valid_OTP() {
        verifyLinkToPayoo_validOTP(getBuyerToken());
    }

    @Then("My Account should not be linked to Payoo account after I verified with invalid OTP")
    public void i_verify_Payoo_linking_with_the_vgalid_OTP() {
        verifyLinkToPayoo_invalidOTP(global_accessToken);
    }

    @Then("My Account should not be linked to Payoo account after I verified with expired OTP")
    public void i_verify_Payoo_linkisng_with_the_vgalid_OTP() {
        verifyLinkToPayoo_expiredOTP(global_accessToken);
    }

    // DEPOSIT STEPS
    @Given("Buyer requests deposit for my new Ad via Chat")
    public void seller_requires_deposit_for_my_new_Ads_via_Chat() {
        getAccessTokenOfNewUser();
        // Setup Buyer info
        setBuyerToken(global_accessToken);
        setBuyerAccountID(global_accountID);
        setBuyerPhone(newUserPhone);

        // Link Buyer with a valid payoo account
        requestLinkToPayoo_linkedAccount_main(getBuyerToken());     // Link Buyer to another valid Payoo Account

        // Set Listing ID for Seller's Ad
        setListIDSeller(tempListID);

        // Buyer chats to Seller to request deposit
        createRoomIDByBuyer(getBuyerToken(), getListIDSeller());  // tempListID is Ad List ID of seller
        buyerRequestDeposit(getBuyerToken(), getRoomIdSeller());
    }

    @When("I create a deposit order for the request of Seller with 100k VND")
    public void i_create_a_deposit_order_for_the_request() {
        sellerCreateDepositOrder(getSellerToken(), getBuyerAccountID(), getListIDSeller(), getRoomIdSeller(), 100000);
    }

    @Given("I login my Account as a Seller that is already linked to Payoo")
    public void login_link_verify_seller() {
        getAccessTokenOfNewUser();
        // Setup Seller info
        setSellerToken(global_accessToken);
        setSellerAccountID(global_accountID);
        setSellerPhone(newUserPhone);

        requestLinkToPayoo_validAccount(getSellerToken());
        verifyLinkToPayoo_validOTP(getSellerToken());
    }

    @Given("I login my Account as a Buyer that is already linked to Payoo")
    public void login_link_verify_buyer() {
        getAccessTokenOfNewUser();
        // Setup Seller info
        setBuyerToken(global_accessToken);
        setBuyerAccountID(global_accountID);
        setBuyerPhone(newUserPhone);

        requestLinkToPayoo_validAccount(getBuyerToken());
        verifyLinkToPayoo_validOTP(getBuyerToken());
    }

    @Given("Buyer pays the order by Visa Card on Payoo Payment Page successfully")
    public void buyer_checkouts_my_order() {
        String url = buyerCheckoutOrderOfSeller_byWeb(getBuyerToken(), getOrderNumber());
        payOrderOnPayooPage_visa(url);
    }

    @Then("Buyer confirms his payment on Chotot after paid on Payoo Page")
    public void buyer_checkouts_mys_order() {
        buyerConfirmOrderOfSeller(getBuyerToken(), getOrderNumber());
    }

    @Given("Payoo notifies to me that money is transfered")
    public void payoo_notifies_to_me_that_money() {
        verifyOrderStatus_paymentReceived(getSellerToken(), getBuyerAccountID(), getListIDSeller(), getOrderNumber());
    }

    @When("Seller confirms that I already delivered my product")
    @When("I can confirm that I already delivered my product")
    public void i_confirm_that_I_already_delivered_my_product() {
        sellerConfirmDelivery(getSellerToken(), getOrderNumber());
        verifyOrderStatus_ProductDelivered();
    }

    @Then("Buyer can confirm that he already received my product")
    public void buyer_can_confirm_that_he_already_received_my_product() {
        //  buyerConfirmReceiveProduct(getBuyerToken(), getOrderNumber());
        verifyOrderStatus_PaymentRelease(getBuyerToken(), getOrderNumber());
    }

    @Then("I should see Buyer's request with status ĐÃ GỬI YÊU CẦU ĐẶT CỌC on ĐANG GIAO DỊCH tab of Order Management")
    public void i_should_see_Buyer_s_request_with_status_dang_giao_dich_tab_of_Order_Management() {
//        verifyEscrowOrderManagement_requestWaiting();
    }

    @When("I click on Tab TÔI MUA of Order Management")
    public void i_click_on_Tab_TOI_MUA_of_Order_Management() {
        clickOnTabSelling_orderManagement();
    }

    @When("I click on Tab TÔI BÁN of Order Management")
    public void i_click_on_Tab_TOI_BAN_of_Order_Management() {
        clickOnTabBuying_orderManagement();
    }

    @When("Buyer should see Order Status is PAYMENT_IN_PROGRESS")
    public void buyer_see_status_PAYMENT_IN_PROGRESS() {
        verifyOrderStatus_paymentInProgress(getBuyerToken(), getBuyerAccountID(), getListIDSeller(), getOrderNumber());
    }

//    @When("Seller should see Order Status is PAYMENT_IN_PROGRESS")
//    public void seller_see_status_PAYMENT_IN_PROGRESS() {
//        verifyOrderStatus_paymentInProgress(getSellerToken(), getSellerAccountID(), getListIDSeller(), getOrderNumber());
//    }

    @When("I dispute a deposit before buyers pay")
    public void i_dispute_a_deposit_before_paying() {
        cancelOrderBySeller(getOrderNumber(), getSellerToken());
    }

    @Then("I should be able to dispute the deposit successfully")
    public void i_should_be_able_dispute_the_deposit_successfully() {
        verifyOrderStatus_CancelBySeller(getSellerToken(), getOrderNumber());
    }

    @Then("Buyer dispute order with reason Tôi đổi ý, không muốn mua nữa")
    public void buyer_dispute_order_with_reason_Tôi_đổi_ý_không_muốn_mua_nữa() {
        disputeWithReason_ToiDoiYKhongMuonBanNua(getOrderNumber(), getBuyerToken());
    }

    @Then("Buyer should see Order Status is DISPUTE_RAISED_BY_BUYER")
    public void buyer_should_see_Order_Status_is_DISPUTE_RAISED_BY_BUYER() {
        verifyOrderStatus_DisputeByBuyer(getBuyerToken(), getOrderStatus(), getOrderNumber());
    }

    @Then("I dispute order with reason Tôi không muốn bán nữa")
    public void i_dispute_order_with_reason() {
        disputeWithReason_ToiKhongMuonBanNua(getOrderNumber(), getSellerToken());
    }

    @Then("I should see Order Status is DISPUTE_RAISED_BY_SELLER")
    public void i_should_see_Order_Status_is_DISPUTE_RAISED_BY_SELLER() {
        verifyOrderStatus_DisputeBySeller(getSellerToken(), getOrderStatus(), getOrderNumber());
    }

    @When("Seller unlink {string} account from his Chotot account fail")
    public void seller_unlink_account_from_his_Chotot_account(String platform) {
        sellerUnlinkPayooFail(platform);
    }

    @When("Seller unlink {string} account from his Chotot account")
    public void seller_unlink_account_from_his_Chotot_account_successfully(String platform) {
        sellerUnlinkPayoo(platform);
    }
    @When("Seller cannot unlink account Payoo successfully")
    public void seller_can_not_unlink_account_successfully() {
            verifyUnlinkPayooFail();
    }
    @When("Seller should be able to unlink account successfully")
    public void seller_account_should_be_able_to_unlink_successfully() {
            verifyUnlinkPayooSuccess(getSellerAccountID());
    }
    @When("Buyer unlink {string} account from Chotot account fail")
    public void buyer_unlink_account_from_his_Chotot_account_unsuccessfully(String platform) {
        buyerUnlinkPayooFail(platform);
    }
    @When("Buyer unlink {string} account from Chotot account")
    public void buyer_unlink_account_from_his_Chotot_account_successfully(String platform) {
        buyerUnlinkPayoo(platform);
    }

    @Then("Buyer cannot unlink account successfully")
    public void buyer_account_should_be_able_to_unlink_fail() {
        verifyUnlinkPayooFail();
    }
    @Then("Buyer should be able to unlink successfully")
        public void buyer_should_be_able_to_unlink_successfully() {
        verifyUnlinkPayooSuccess(getBuyerAccountID());
    }
}

