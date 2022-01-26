package projects.steps.chotot.api.hierarchy;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import projects.functions.chotot.flashad.FlashAd_PTY_Functions;
import projects.functions.chotot.hierarchy.AccountHierarchy_Functions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accessToken;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static api.utils.GetAccessToken.getAccessTokenOfUser;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.*;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getBalanceDT;
import static projects.functions.chotot.payment.TopupDongTot_API_Functions.*;
import static projects.functions.chotot.pos.POS_Functions.*;
import static projects.functions.chotot.shops.PaidShop_API_Functions.*;

public class AccountHierarchy_API_Steps extends AccountHierarchy_Functions {
    private static List<String> childIDList = new ArrayList<>();
    private static List<String> childTokenList = new ArrayList<>();
    private int money = 0;
    private FlashAd_PTY_Functions flashAd_pty = new FlashAd_PTY_Functions();


    @Then("Chotot upgrades my account to Biz Account")
    @When("Chotot links my Biz Account to my Child Account")
    @And("Chotot links my Biz Account to my Child Account again")
    public void chotot_upgrades_my_account_to_Biz_Account() {
        if (childIDList.isEmpty()) {
            upgrade2BizAccount(getParentAccountID());
            linkChildToBiz(getParentAccountID(), getChildID());
        } else {
            upgrade2BizAccount(getParentAccountID());
            for (String childID : childIDList) {
                linkChildToBiz(getParentAccountID(), childID);
            }
        }
    }

    @Given("I register {string} Chotot Users to register Biz, Child accounts")
    public void register_2_account(String num) {
        //register Biz
        setBizToken(getAccessTokenOfNewUser());
        setParentAccountID(global_accountID);

        //register Child
        setChildToken(getAccessTokenOfNewUser());
        setChildID(global_accountID);
    }

    @Then("My account should be Biz Account")
    public void my_account_should_be_Biz_Account() {
        verifyChildLinkedToBiz(getParentAccountID(), getChildID());
    }

    @Given("I register a Normal Account that isn't linked to my Biz Account")
    public void i_register_normal_account() {
        registerNormalAccount();
    }

    @Given("I register a new account that is upgraded to Biz Account")
    public void i_register_a_new_account_that_is_upgraded_to_Biz_Account() {
//        registerBizAccount();  //OLD
        upgrade2BizAccount();   //VU REVIEW
    }

    @Given("I register a new account that is upgraded to Biz Account but not link with any Child Account")
    public void i_register_a_new_account_that_is_upgraded_to_Biz_Account_but_not_link_with_child() {
        registerBizAccount_NoLink();
    }

    @Given("I register a Child Account")
    public void i_register_a_Child_Account() {
        registerChildAccount(getBizAccountID());
    }


    @Then("My Child Account should be linked to my Biz Account")
    public void my_Child_Account_should_be_linked_to_my_Biz_Account() {
        checkBizContainsChilds(getParentAccountID(), childIDList);
    }

    @Then("All of my Child Accounts should be linked to my Biz Account")
    public void all_my_Child_Accounts_should_be_linked_to_my_Biz_Account() {
        for (String childToken : childTokenList) {
            checkChildLinkedToBiz(childToken, getBizAccountID());
        }
    }

    @Then("My Biz Account should be linked to all of my Child Accounts")
    public void my_Biz_Account_should_be_linked_to_all_of_my_Child_Accounts() {
        checkBizContainsChilds(getBizAccountID(), childIDList);
    }

    @Then("My Biz Account should contain my {string} Child Accounts")
    public void my_Biz_Account_checknum_hild_Accounts(String number) {
        checkNumberOfChilds(getBizAccountID(), Integer.parseInt(number));
    }

    @Then("My Biz Account should be linked to my Child Account")
    public void my_Biz_Account_should_be_linked_to_my_Child_Account() {
        verifyChildLinkedToBiz(getParentAccountID(), getChildID());
    }

    @Then("My Biz Account should be unlinked to all of my Child Accounts")
    public void my_Biz_Account_should_be_unlinked_to_my_Child_Account() {
        for (String childID : childIDList) {
            verifyChildUnLinkedToBiz(getParentAccountID(), childID);
        }
    }

    @Then("My Biz Account should have {string} Active Child Accounts")
    public void my_biz_acc_should_have_active_child(String numberOfActiveChild) {
        verifyNumberOfActiveChild(getParentAccountID(), Integer.parseInt(numberOfActiveChild));
    }

    @Then("My Biz Account should have {string} Deleted Child Accounts")
    public void my_biz_acc_should_have_delete_child(String numberOfActiveChild) {
        verifyNumberOfDeletedChild(getParentAccountID(), Integer.parseInt(numberOfActiveChild));
    }

    @When("My Child Accounts should be unlinked")
    public void child_accounts_should_be_deleted() {
        for (String childID : childIDList) {
            verifyChildIsUnlinked(getParentAccountID(), childID);
        }
    }

    @When("My Child Account A should be unlinked")
    public void child_account_a_should_be_deleted() {
        verifyChildIsUnlinked(getParentAccountID(), getChildAID());
    }

    @When("My Child Account B should be linked")
    public void child_account_a_should_be_active() {
        verifyChildIsLinked(getParentAccountID(), getChildBID());
    }


    @When("Chotot links my Biz Account to my inactive Child Account")
    public void chotot_links_my_Biz_Account_to_my_inactive_Child_Account() {
        linkChildToBiz(getBizAccountID(), String.valueOf(10000000));
    }

    @Then("My Biz Account should not be linked to my Child Account")
    public void my_Biz_Account_should_not_be_linked_to_my_Child_Account() {
        checkBizLinkedToChildFailed(getBizAccountID(), String.valueOf(10000000));
    }

    @Then("I register {string} Child Accounts")
    public void i_register_Child_Accounts(String number) {
        for (int i = 0; i < Integer.parseInt(number); i++) {
            childIDList.add(registerChildAccount_NotLink(getBizAccountID()));
            childTokenList.add(getChildToken());
        }
    }

    @Then("I register a new Biz Account that isn't linked to any Child Account")
    public void i_register_a_new_Biz_Account() {
        //registerBizAccount_NoLink();
        registerParentAccount_NoChild();
    }

    @Then("I register {string} Child Accounts which are linked to my Biz Account")
    public void i_register_Child_Accounts_linked(String number) {
        for (int i = 0; i < Integer.parseInt(number); i++) {
            childIDList.add(registerChildAccount(getBizAccountID()));
            childTokenList.add(getChildToken());
        }
    }

    @When("Chotot links my Biz Account to all my Child Accounts")
    public void chotot_links_my_Biz_Account_to_all_my_Child_Accounts() {
        for (String childID : childIDList) {
            linkChildToBiz(getBizAccountID(), childID);
        }
    }

    @Then("Each of My Child Account should be linked to my Biz Account")
    public void each_of_My_Child_Account_should_be_linked_to_my_Biz_Account() {
        for (int i = 0; i < childIDList.size(); i++) {
            checkChildLinkedToBiz(childTokenList.get(i), getBizAccountID());

        }
    }

    @When("Chotot links my Child Account to my Biz Account")
    public void chotot_links_my_Child_Account_to_my_Biz_Account() {
    }

    @Then("My Child Account should not able to be linked to my Biz Account")
    public void my_Child_Account_should_not_be_linked_to_my_Biz_Account() {
        linkMyChildToMyBizFailed(getChildID(), getBizAccountID());     //Swap BizID with childID
    }

    @When("Chotot links my Biz Account to my Child Account on {int} times")
    public void chotot_links_my_Biz_Account_to_my_Child_Account_on_times(Integer time) {
        for (int i = 0; i < time - 1; i++) {
            linkChildToBiz(getBizAccountID(), getChildID());
        }
    }

    @Then("My Child Account should not be linked to my Biz Account on 2nd times and error responses")
    public void my_Child_Account_should_not_be_linked_to_my_Biz_Account_on_2nd_times_and_error_responses() {
        linkChildToBizFailed(getBizAccountID(), getChildID());
    }


    @Given("I register a Biz Account A")
    public void register_Biz_X() {
        registerBizAccountA_NoLink();
    }

    @Given("I register a Child Account A that is linked to my Biz Account A")
    public void register_Child_A() {
        if (getBizAID().isEmpty()) {
            registerBizAccountA_NoLink();
        }
        registerChildAccountA_NoLink();
        registerBizAccountA_NoLink();
        linkChildToBiz(getBizAID(), getChildAID());
    }

    @Given("I register a Child Account A that is linked to my Biz Account")
    public void register_Child_A_link_toBiz() {
        registerChildAccountA_NoLink();
        linkChildToBiz(getParentAccountID(), getChildAID());
    }

    @Given("I register a Child Account B that is linked to my Biz Account")
    public void register_Child_B_link_toBiz() {
        registerChildAccountB_NoLink();
        linkChildToBiz(getParentAccountID(), getChildBID());
    }

    @Given("I register a Child Account B that is linked to my Biz Account B")
    public void register_Child_B() {
        registerBizAccountB_NoLink();
        registerChildAccountB_NoLink();
        linkChildToBiz(getBizBID(), getChildBID());
    }

    @And("Chotot unlinks my Biz Account to my Child Accounts")
    public void chotot_unlinks_all_my_Child_Accounts_from_my_Biz_Account() {
        for (String childAccountID : childIDList) {
            unlinkParentToChild(getParentAccountID(), childAccountID);
        }
    }

    @And("Chotot unlinks my Biz Account to my Child Account A")
    public void chotot_unlinks_all_my_Child_Account_A_from_my_Biz_Account() {
        unlinkParentToChild(getParentAccountID(), getChildAID());
    }

    @Then("My Child Accounts should become Normal Account")
    public void my_Child_Accounts_should_be_unlinked() {
        if (childTokenList.isEmpty()) {
            verifyIsNormalAccount(getChildToken());
        } else {
            for (String childToken : childTokenList) {
                verifyIsNormalAccount(childToken);
            }
        }
    }

    @Given("DT4B balance is {string}")
    @When("I topup {string} DT4B to the account")
    @And("I topup {string} Dong Tot to my Biz Account")
    public void i_topup_DT4B_to_the_account(String amount) {
        topupDongTotWithMomo(getBizPhone(), Integer.parseInt(amount));
    }

    @When("I topup {string} Dong Tot to my Normal Account")
    public void i_topup_DT4B_to_my_normal_account(String amount) {
        topupDongTotWithMomo(getNormalPhone(), Integer.parseInt(amount));
    }

    @When("I topup {string} Dong Tot to my Child Account")
    public void i_topup_DT4B_to_Child_account(String amount) {
        topupDongTotWithMomo(getChildPhone(), Integer.parseInt(amount));
    }

    @When("Normal Account's Dong Tot should be paid {string}")
    public void my_DT4B_balance_should_be(String amount) {
        checkTopupBalanceDT4B(getNormalToken(), Integer.parseInt(amount));
    }

    @When("Child Account's Dong Tot should be paid {string}")
    @When("Child Account's Dong Tot should be {string}")
    public void my_DT4B_balance_child_should_be(String amount) {
        checkTopupBalanceDT4B(getChildToken(), Integer.parseInt(amount));
    }

    @When("The rest of Dong Tot should be {string}")
    @Then("My DT4B balance should be {string}")
    @Then("DT balance of my Biz Account should be {string}")
    @Then("Biz Account's Dong Tot should be {string}")
    public void my_DT4B_balance_biz_should_be(String amount) {
        checkTopupBalanceDT4B(getBizToken(), Integer.parseInt(amount));
    }

    @When("Child Account can't see Total DT4B of Biz Account")
    public void child_cannot_see_dt4b() {
        checkTopupBalanceDT4B_Deny(getChildToken(), getBizAccountID());
    }

    @Then("I can see topup history with {string} in my Biz Account.")
    public void i_can_see_topup_history_in_my_Biz_Account(String amount) {
        checkTopupHistoryDT4B(Integer.parseInt(amount));
    }

    @Given("I register a Biz Account and link a Child Account")
    public void i_register_a_Biz_Account_and_link_a_Child_Account() {
        registerBizAccount_NoLink();
        registerChildAccount(getBizAccountID());
    }

    @When("Chotot set Budget Limitation for my Biz Account with {string}")
    public void chotot_set_Budget_Limitation_for_my_Biz_Account_with(String bizMoney) {
        money = Integer.parseInt(bizMoney);
        setBudget4BizAccount(money);
    }

    @When("Chotot set Budget Limitation for my parent account with {string}")
    public void chotot_set_Budget_Limitation_for_my_parent_account_with(String bizMoney) {
        money = Integer.parseInt(bizMoney);
        setBudget4BizAccount(money);
    }

    @Then("The monthly budget of parent account should be {string}")
    public void the_monthly_budget_of_parent_account_should_be(String bizMoney) {
        money = Integer.parseInt(bizMoney);
        verifySetLimitBudget4BizWhenCreateBizAccount(bizMoney);
    }

    @When("Chotot update Budget Limitation for my Biz Account to {string}")
    public void chotot_update_Budget_Limitation_for_my_Biz_Account_to(String bizMoney) {
        money = Integer.parseInt(bizMoney);
        updateBudget4Biz_PerMonth(getParentAccountID(), money);
    }

    @When("Chotot update Budget Limitation for my parent account to {string}")
    public void chotot_update_Budget_Limitation_for_my_parent_Account_to(String bizMoney) {
        money = Integer.parseInt(bizMoney);
        updateBudget4Biz_PerMonth(getParentAccountID(), money);
    }

    @When("Chotot set Budget Limitation for per transaction of my Child Account with {string}")
    public void chotot_set_Budget_Limitation_for_per_transaction_of_my_Child_Account_with(String childMoney) {
        money = Integer.parseInt(childMoney);
        updateBudget4Biz_PerChildTransaction(getParentAccountID(), money);
    }

    @Then("The Budget Limitation per transaction of my Child Account should be {string}")
    public void the_Budget_Limitation_per_transaction_of_my_Child_Account_should_be(String transMoney) {
        money = Integer.parseInt(transMoney);
        verifySetLimitBudget_WhenCreateBizAccount_BudgetPerTransaction_4Child(transMoney);
    }

    @When("Chotot set Budget Limitation per transaction for my Child Account with {string}")
    public void chotot_set_Budget_Limitation_per_transaction_for_my_Child_Account_with(String transMoney) {
        money = Integer.parseInt(transMoney);
        updateBudget4Biz_PerChildTransaction(getParentAccountID(), money);
    }

    @When("Chotot update limit budget per transaction for my Child Account to {string}")
    public void chotot_update_limit_budget_per_transaction_for_my_Child_Account_to(String limitPerOrder) {
        money = Integer.parseInt(limitPerOrder);
        updateBudget4Biz_PerChildTransaction(getParentAccountID(), money);
    }

    @Given("I login with my Biz Account")
    @And("I login with my Biz Account that has a linked child")
    public void i_login_with_my_Biz_Account() {
        registerBizAccount_SetNewChild();
    }

    @Given("I login with my Biz Account that has DT4B balance {string}")
    @Given("I login with my another Biz Account that has DT4B balance {string}")
    @When("I register my Biz Account that has DT4B balance {string}")
    public void i_register_a_biz_account_with_balance(String amount) {
        registerBizAccount_SetNewChild();       //Co register Child va setChildId, ChildToken
        topupDongTotWithMomo(getBizPhone(), Integer.parseInt(amount));
    }

    @Given("I login with my Biz Account with balance {string} that is expired")
    public void i_register_a_biz_accousnt_with_balance(String amount) {
        registerBizAccount_SetNewChild_Expired();       //Co register Child va setChildId, ChildToken
        topupDongTotWithMomo(getBizPhone(), Integer.parseInt(amount));
        waitConstant(2);
        verifyIsBizAccountExpired(getBizToken());
    }

    @When("I use my Biz Account to pay for premium services with {string}")
    public void i_use_my_Biz_Account_to_pay_for_premium_services_with(String payMoney) {
        global_accessToken = getBizToken();

        //Insert Ad
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getBizToken(), tempAdID);
        paymentOrder();
        setAmountAfterPayBiz(getAmountAfterPayDT());
    }

    @When("I post and pay POS for {string} new Ads that each of them costs {string} by my Biz Account")
    public void post_many_ad(String numberOfAd, String codeofAd) {
        global_accessToken = getBizToken();
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
            setBizToken(getAccessTokenOfUser(getBizPhone(), defaultPassword));
            posBump(getBizToken(), tempAdID);
            paymentOrder();
        }
    }

    @When("I post and pay POS for {string} new Ads that each of them costs {string} by my Child Account")
    @When("I can pay for {string} new Ads that each of them costs {string} by my Child Account")
    @When("I pay for {string} new Ads that costs {string} by my Child Account")
    @When("I can pay for {string} new Ads that costs {string} by my Child Account")
    public void post_many_ad_child(String numberOfAd, String codeofAd) {
        global_accessToken = getChildToken();
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            tempAdID = flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
            posBump(getChildToken(), tempAdID);
            paymentOrderDT4B(getChildToken());
        }
    }

    @When("I pay for a Pro Ad by my Child Account and Chotot refused my Ad")
    public void post_many_adss_child() {
        global_accessToken = getChildToken();
        tempAdID = flashAd_pty.insertHouseSellPro(global_accessToken, "refusepaydt4b", false);
        setAmountAfterPayBiz(getAmountAfterPayDT4B());
    }

    @When("I can pay again by my Child Account")
    public void post_manyss_asd_child() {
        paymentOrderDT4B(getChildToken());
    }

    @When("I can't pay for {string} new Ads that costs {string} by my Child Account")
    public void not_post_many_ad_child(String numberOfAd, String codeofAd) {
        global_accessToken = getChildToken();

        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
            posBump(getChildToken(), tempAdID);
            paymentOrderDT4B(getChildToken(), "400");
        }
    }

    @When("I can't pay for POS with {string} by using my Biz Account")
    public void i_cannot_use_my_Biz_Account_to_pay_for_premium_services_with(String payMoney) {
        global_accessToken = getBizToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getBizToken(), tempAdID);
        paymentOrder(getBizToken(), "400");
    }

    @When("I can't pay for POS with {string} by using my Child Account")
    public void i_cannot_use_my_Biz_Account_to_pay_for_premium_services_with_child(String payMoney) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrder(getChildToken(), "400");
    }

    @When("I use my Child Account to pay for premium services with {string}")
    public void i_use_my_Child_Account_to_pay_for_premium_services_with(String payMoney) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
    }

    @When("I should use my Child Account to pay for premium services unsuccessfully")
    public void i_use_my_Child_Account_to_pay_for_premium_services_unsuccessfully() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken(), "400");
    }

    @Then("I should see the transaction history on my Biz Account")
    public void i_should_see_the_transaction_history_on_my_Biz_Account() {
    }

    @When("Chotot set Budget Limitation per month for my Child Account with {string}")
    public void chotot_set_Budget_Limitation_per_month_for_my_Child_Account_with(String childMoney) {
        money = Integer.parseInt(childMoney);
//        setBudget4ChildAccountPerMonth(money);
        updateBudget4Child_PerMonth(getChildAccountID(), getParentAccountID(), money);
    }

    @Then("The Budget Limitation per month of my Child Account should be {string}")
    public void the_Budget_Limitation_per_month_for_my_Child_Account_should_be(String childBudget) {
        verifySetBudget4Child_PerMonth(getChildAccountID(), childBudget);
    }

    //    @Given("I login with my Child Account that is linked to my Biz Account")
    @Given("I register my Child Account that is linked to my Biz Account")
    public void i_login_with_my_Child_Account_that_is_linked_to_Biz_Account() {
        registerChildAccountLinkedToNewBizAccount();
    }

    @Given("I login with my Child Account that is linked to my Biz Account")
    public void i_logins_with_my_Child_Account_that_is_linked_to_Biz_Account() {
        loginChildAccountLinkedToBizAccount();
    }

    @When("I register a Child Account that is linked to my Biz Account")
    public void i_register_child_for_my_biz() {
        registerChildAccount(getBizAccountID());
    }

    @When("I register {string} Child Account that is linked to my Biz Account")
    public void i_register_child_for_my_biz(String numOfChild) {
        for (int i = 0; i < Integer.parseInt(numOfChild); i++) {
            registerChildAccount(getBizAccountID());
        }
    }

    @When("I use Child Account to pay POS by DT4B")
    public void i_use_Child_Account_to_pay_for_premium_services_with() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
    }

    @Then("I should use my Child Account to pay for premium services successfully")
    @When("I use Child Account to pay for premium services by DT4B")
    public void i_use_Child_Account_to_pay_for_premium_services_() {
        global_accessToken = getChildToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B());
    }

    @Then("I should use my Child Account to pay again for premium services successfully")
    public void i_use_Child_Account_to_pay_again_for_premium_services_() {
        global_accessToken = getChildToken();       //Use for insert Ad
        paymentOrderDT4B(getChildToken());
    }

    @Given("My Biz Budget Limitation is {string}")
    @Given("My Biz Budget Limitation which is set on Biz Account is {string}")
    public void my_Biz_Budget_Limitation_is(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerMonth(getParentAccountID(), money);
    }

    @Given("My Child Budget Limitation is {string}")
    @And("My Child Budget Limitation Per Month is {string}")
    @And("My Child Budget Limitation Per Month which is set on Child Account is {string}")
    public void my_Child_Budget_Limitation_is(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Child_PerMonth(getChildAccountID(), getParentAccountID(), money);
    }

    @And("My Child Budget Limitation Per Month which is set on Biz Account is {string}")
    public void my_biz_Budget_Limitation_is(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerChildMonth(getParentAccountID(), money);
    }

    @When("My Child Budget Limitation Per Month which is set on my Biz Account is {string}")
    public void my_Child_Budget_Limitation_is_setBioz(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerChildMonth(getParentAccountID(), money);
    }

    @And("My Child Budget Limitation Per Month which is set from my Biz Account is {string}")
    public void my_Child_Budget_Limitation_is_set_by_biz(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerChildMonth(getParentAccountID(), money);
    }

    @Given("My Transaction Budget Limitation which is set from my Biz Account is {string}")
    public void my_Transaction_Budget_Limitation_is(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerChildTransaction(getParentAccountID(), money);
    }

    @Given("My Transaction Budget Limitation which is set from my Child Account is {string}")
    public void my_Transaction_Budget_Limitation_Child_is(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Child_PerTransaction(getChildID(), getParentAccountID(), money);
    }

    @Given("My Transaction Budget Limitation is updated to {string}")
    @And("My Transaction Budget Limitation which is set from my Biz Account is updated to {string}")
    public void my_Transaction_Budget_Limitation_is_updated(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerChildTransaction(getParentAccountID(), money);
    }

    @And("My Transaction Budget Limitation which is set from my Child Account is updated to {string}")
    public void my_Transaction_Budget_Limitation_child_is_updated(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Child_PerTransaction(getChildID(), getParentAccountID(), money);
    }


    @Then("I should be able to pay for premium services with {string} successfully")
    @Then("I should be able to pay for premium services with {string} by my Biz Account successfully")
    @When("I should be able to pay for premium services with {string}")
    public void i_should_pay_for_premium_services_with_successfully(String string) {
        global_accessToken = getBizToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getBizToken(), tempAdID);
        paymentOrderWithDongTot();
        setAmountAfterPayChild(getAmountAfterPayDT4B());
    }

    @When("My Biz Budget Limitation is updated to {string}")
    public void my_Biz_Budget_Limitation_is_updated_to(String budget) {
        money = Integer.parseInt(budget);
//        updateBudget4BizAccount(money);
        updateBudget4Biz_PerMonth(getParentAccountID(), money);
    }

    @When("My Child Budget Limitation is updated to {string}")
    @When("My Child Budget Limitation Per Month is updated to {string}")
    @When("My Child Budget Limitation Per Month which is set on Child Account is updated to {string}")
    public void my_Child_Budget_Limitation_is_updated_to(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Child_PerMonth(getChildID(), getParentAccountID(), money);
    }

    @When("My Child Budget Limitation Per Month which is set on Biz Account is updated to {string}")
    public void my_Child_Budget_Limitation_is_updated_on_biz_to(String budget) {
        money = Integer.parseInt(budget);
        updateBudget4Biz_PerChildMonth(getParentAccountID(), money);
    }

    @When("Chotot update Budget Limitation per month for my Child Account to {string}")
    public void chotot_update_Budget_Limitation_per_month_for_my_Child_Account_to(String childMonthlyBudget) {
        money = Integer.parseInt(childMonthlyBudget);
        updateBudget4Child_PerMonth(getChildAccountID(), getParentAccountID(), money);
    }

    @Then("I should see the POS order of my Biz Account in Biz Order History is {string}")
    public void i_should_see_payment_in_History_is(String amount) {
//        checkOrderHistory_POSOrder_AdAccept(getBizToken(), Integer.parseInt(amount));
        checkOrderHistory_POSOrderOfBiz_AdAccept(getBizToken(), getBizAccountID(), Integer.parseInt(amount), "200");
    }

    @Then("I should see the Listing Fee order of my Biz Account in Biz Order History is {string}")
    public void i_should_see_payment_in_History_a_is(String amount) {
        checkOrderHistory_ListingFee_AdAccept(getBizToken(), Integer.parseInt(amount));
    }

    @Then("I should see the Listing Fee order of my Biz Account in Biz Order History")
    public void i_should_see_payment_in_Historys() {
        checkOrderHistory_ListingFee_AdAccept(getBizToken(), getAmountAfterPayDT());
    }

    @Then("I should see the Listing Fee order of my Child Account in Child Order History is {string}")
    public void i_should_see_payment_child_in_History_a_is(String amount) {
        checkOrderHistory_ListingFee_AdAccept(getChildToken(), Integer.parseInt(amount));
    }

    @Then("I should see the Topup order of my Biz Account in Biz Order History is {string}")
    public void i_should_see_payment_in_History_is_topup(String amount) {
        checkOrderHistory_TopupOrder(getBizToken(), Integer.parseInt(amount));
    }

    @Then("I should see the POS order of my Child Account in Child Order History is {string}")
    public void i_should_see_child_payment_in_History_is(String amount) {
        checkOrderHistory_POSOrder_AdAccept(getChildToken(), getChildID(), Integer.parseInt(amount));
    }

    @Then("I should see the POS order of my Child Account in Child Order History")
    public void i_should_see_child_payment_in_Hist() {
        checkOrderHistory_POSOrder_AdAccept(getChildToken(), getChildID(), getAmountAfterPayChild());
    }

    @Then("I should see the POS order of my Child Account in Biz Order History")
    public void i_should_see_child_payment_in_biz_Histo__ary_is() {
        checkBizOrderHistory_POSOrderOfChild(getBizToken(), getChildID(), getAmountAfterPayChild());
    }

    @When("I login with my Child Account A that is linked to my Biz Account")
    public void i_login_with_my_Child_Account_A_that_is_linked_to_my_Biz_Account() {
        registerChildAccountA(getBizAccountID());
    }

    @When("I use Child Account A to pay for premium services with {string}")
    public void i_use_Child_Account_A_to_pay_for_premium_services_with(String money) {
        //refreshToken
        global_accessToken = getChildToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildAToken(), tempAdID);
        paymentOrderDT4B(getChildAToken());
    }

    @When("I login with my Child Account B that is linked to my Biz Account")
    public void i_login_with_my_Child_Account_B_that_is_linked_to_my_Biz_Account() {
        registerChildAccountB(getBizAccountID());
    }

    @Then("I should not be able to see the POS order of my Child Account A in Child B Order History is {string}")
    public void i_should_not_be_able_to_see_the_payment_of_my_Child_Account_A_in_Child_B_History(String amount) {
        checkOrderHistory_POSOrder_NotExistChildOrder(getChildBToken(), getChildAID(), Integer.parseInt(amount));
    }

    @Then("I should not be able to see the POS order of my Biz Account in Child B Order History is {string}")
    public void i_should_not_be_able_to_see_the_payment_of_my_Biz_Account_in_Child_B_History(String amount) {
        checkOrderHistory_POSOrder_NotExistBizOrder(getChildToken(), Integer.parseInt(amount));
    }

    @When("My Biz Account have {string} private PTY House Ads which are published on Listing Fee")
    public void biz_have_2_ad_house(String numberOfAd) {
        global_accessToken = getBizToken();
        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            flashAd_pty.insertHouseSellPrivate(global_accessToken, "accept", false);
        }
    }

    @When("My Child Account have {string} private PTY House Ads which are published on Listing Fee")
    public void child_have_2_ad_house(String numberOfAd) {
        registerChildAccount(getBizAccountID());
        global_accessToken = getChildToken();

        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            flashAd_pty.insertHouseSellPrivate(global_accessToken, "accept", false);
        }
    }

    @When("I use My current Child Account have {string} private PTY House Ads which are published on Listing Fee")
    public void child_have_current_2_ad_house(String numberOfAd) {
        global_accessToken = getChildToken();

        for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
            flashAd_pty.insertHouseSellPrivate(global_accessToken, "accept", false);
        }
    }

    @Then("I should be able to use my Biz Account to post and pay for 2nd Private House Ad")
    public void biz_add_ad_house() {
        global_accessToken = getBizToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "acceptpay", false);
    }

    @Then("I should be able to use my Child Account to post and pay 3rd Private House Ad with {string}")
    public void child_add_ad_house(String money) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "acceptpaydt4b", false);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }


    //----------------- Private Ad ---------------------
    @Then("I should pay for premium service Bump with my Child Account successfully")
    public void biz_pay_singsle_bump() {
        global_accessToken = getChildToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Bump 3 Days with my Child Account successfully")
    public void biz_pay_single_bump() {
        global_accessToken = getChildToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump3Days(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Bump 7 Days with my Child Account successfully")
    public void biz_pay_ssingle_bump() {
        global_accessToken = getChildToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump7Days(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @When("I pay for premium service Feature Ad with my Child Account")
    @Then("I should pay for premium service Ad Feature Ribbon with my Child Account successfully")
    public void biz_pay_Prioritize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posAdFeatureRibbon(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Ad Feature Price with my Child Account successfully")
    public void biz_pay_Priorsitize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posAdFeaturePrice(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Ad Feature Title with my Child Account successfully")
    public void biz_pay_Prsiorsitize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posAdFeatureTitle(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Bump Timer Ad with my Child Account successfully")
    public void biz_pay_blockssss_time() {
        global_accessToken = getChildToken();

        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBumpTimer(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Sticky Ad with my Child Account successfully")
    public void biz_pay_sticky_ad() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posStickyAd(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay for premium service Bundle with my Child Account successfully")
    public void biz_pay_Ultility() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBundle(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }


    //----------------- Pro Ad ---------------------
    @Then("I should pay premium service Bump for my Pro Ad by my Child Account successfully")
    public void biz_pay_ssingsle_bump() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bump 3 Days for my Pro Ad by my Child Account successfully")
    public void biz_pay_sisngle_bump() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posBump3Days(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bump 7 Days for my Pro Ad by my Child Account successfully")
    public void biz_pay_ssinglse_bump() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posBump7Days(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Ad Feature Ribbon for my Pro Ad by my Child Account successfully")
    public void biz_pay_Psrioritize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posAdFeatureRibbon(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Ad Feature Price for my Pro Ad by my Child Account successfully")
    public void biz_pays_Priorsitize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posAdFeaturePrice(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Ad Feature Title for my Pro Ad by my Child Account successfully")
    public void biz_pay_sPrsiorsitize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posAdFeatureTitle(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bump Timer Ad for my Pro Ad by my Child Account successfully")
    public void biz_pay_blocksssss_time() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posBumpTimer(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Sticky Ad for my Pro Ad by my Child Account successfully")
    public void bisz_pay_sticky_ad() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posStickyAd(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bundle for my Pro Ad by my Child Account successfully")
    public void biz_psay_Ultility() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "accept", false);
        posBundle(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }


    //----------------- Shop Ad ---------------------
    @Then("I should pay premium service Bump for my Shop Ad by my Child Account successfully")
    public void biz_pay_ssisngsle_bump() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bump 3 Days for my Shop Ad by my Child Account successfully")
    public void biz_psay_sisnsgle_bump() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posBump3Days(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bump 7 Days for my Shop Ad by my Child Account successfully")
    public void biz_pay_ssinglse_sbump() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posBump7Days(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Ad Feature Ribbon for my Shop Ad by my Child Account successfully")
    public void biz_pasy_Psrioritize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posAdFeatureRibbon(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Ad Feature Price for my Shop Ad by my Child Account successfully")
    public void biz_pays_Priorssitize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posAdFeaturePrice(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Ad Feature Title for my Shop Ad by my Child Account successfully")
    public void biz_pay_sPrssiorsitize() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posAdFeatureTitle(global_accessToken, tempAdID);
        paymentOrderDT4B(global_accessToken);
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bump Timer Ad for my Shop Ad by my Child Account successfully")
    public void biz_pay_blocksssssss_time() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posBumpTimer(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Sticky Ad for my Shop Ad by my Child Account successfully")
    public void bisz_pay_sticsky_ad() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posStickyAd(getChildToken(), tempAdID);
        paymentOrder(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("I should pay premium service Bundle for my Shop Ad by my Child Account successfully")
    public void biz_spsay_Ultility() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellShop(global_accessToken, "accept", true, false);
        posBundle(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }


    //REFUND
    @Then("I post a new Ad House and pay for Listing Fee with my Biz Account But ad is rejected and DT is refunded")
    public void biz_pay_listing_fee_refund() {
        //1. Insert ad and accepted
        global_accessToken = getBizToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "accept", false);
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "rejectpay", false); //Contains step 2,3,4,5
        //2. Bump but not immediately
        //3. Pay for Bump
        //4. Get money for Bump to check order
        //5. Reject Bump
        setAmountAfterPayBiz(getAmountAfterPayDT());
    }

    @Then("I post a new Ad House and pay for Listing Fee with my Child Account But ad is rejected and DT4B is refunded")
    public void child_pay_listing_fee_refund() {
        //1. Insert ad and accepted
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "accept", false);
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "rejectpaydt4b", false);
        setAmountAfterPayChild(getAmountAfterPayDT4B());
    }

    @Given("I post a new Ad House and pay for Listing Fee with my Child Account and ChoTot accept my ad")
    public void i_post_a_new_Ad_House_and_pay_for_Listing_Fee_with_my_Child_Account_and_ChoTot_accept_my_ad() {
        //1. Insert ad and accepted
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "acceptpaydt4b", false);
        setAmountAfterPayChild(getAmountAfterPayDT4B());
    }

    @Then("I post a new Ad and pay for POS Single Bump with my Child Account But ad is rejected and DT4B is refunded")
    public void biz_pay_single_bump_refund_child() {
        //1. Insert ad and accepted
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPro(global_accessToken, "refusepaydt4b_withbump", false);
        setAmountAfterPayChild(getAmountAfterPayDT4B());
    }

    @Then("I should see Dong Tot refunded to my Biz Account in Biz Order History")
    public void i_should_see_payment_in_History_is_amountAfterPayDT() {
        checkOrderHistory_Biz_POSOrder_Refund(getBizToken(), getAmountAfterPayBiz());
    }

    @Then("I should see Dong Tot refunded to my Biz Account in Biz Order History is {string}")
    public void i_should_see_payment_in_Histosry_is_amountAfterPayDT(String amount) {
        checkOrderHistory_Biz_POSOrder_Refund(getBizToken(), Integer.parseInt(amount));
    }

    @Then("I should see payments of Child Account in Biz Order History")
    public void i_should_see_payment_in_Histosry_iss_amountAfterPayDT() {
        checkBizOrderHistory(getBizToken(), getChildAccountID(), getAmountAfterPayDT4B());
    }

    @Then("I should see Dong Tot of my Biz Account is refunded")
    public void i_should_see_payment_in_History_is_amountAftesrPayDT() {
        checkTopupBalanceDT4B(getBizToken(), getAmountAfterPayDT4B());
    }

    @Then("I should see the POS refund of my Child Account in Biz Order History")
    public void i_should_see_child_payment_in_biz_History_is() {
        checkOrderHistory_Biz_ChildPOSOrder_Refund(getBizToken(), getAmountAfterPayChild());
    }

    @Then("I should see the POS refund of my Child Account in Child Order History")
    public void i_should_see_child_payment_in_child_History_is() {
        checkOrderHistory_Biz_POSOrder_Refund(getChildToken(), getAmountAfterPayChild());
    }

    @Then("I should see the POS refund of my Biz Account in Child Order History")
    public void i_should_see_payment_in_History_is_amcildT() {
        checkOrderHistory_Child_BizPOSOrder_Refund(getChildToken(), getBizAccountID(), getAmountAfterPayBiz());
    }

    @Then("I should not see the POS refund of my Child Account in Child Order History")
    public void not_see_child_in_child() {
        checkOrderHistory_Biz_ChildPOSOrder_Refund_NOSEE(getBizToken(), getChildID(), getAmountAfterPayChild());
    }

    @When("Chotot upgrades my account to Biz Account as parent account")
    public void chototUpgradesMyAccountToBizAccountToParentAccount() {
        upgradeToBizAccountAsParent(getParentAccountID());
    }

    @And("My Biz Account should be child account")
    public void myBizAccountShouldBeChildAccount() {
        verifyIsChildAccount(getBizToken());
    }

    @Given("I login with my parent account")
    public void iLoginWithMyParentAccount() {
        upgrade2BizAccount();
    }

    @Given("I login with my Child Account that is linked to Biz Account")
    public void iLoginWithMyChildAccountThatIsLinkedToBizAccount_register() {
        upgrade2BizAccount();
        registerChildAccount(getBizAccountID());
    }

    @Given("I login with my Child Account that is linked to my existed Biz Account")
    public void loginChildWIthBiz() {
        registerChildAccount(getBizAccountID());
    }

    @Then("My child account should be able to pay for premium services with {string} successfully")
    public void myChildAccountShouldBeAbleToPayForPremiumServicesWithSuccessfully(String arg0) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B());
    }

    @Then("My child account should be able to pay for premium services by Dong Tot")
    public void myChildAccountShouldBeAbleToPayForPremiumsServicesWithSuccessfully() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrder(getChildToken());
    }

    @Then("My Child account should not be able to pay for premium services with {string} successfully")
    public void myChildAccountShouldNotBeAbleToPayForPremiumServicesWithSuccessfully(String arg0) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken(), "400");
    }

    @When("My Child account should not be able to pay for premium services by DT4B")
    public void myChildAccountShosuldNotBeAbleToPayForPremiumServicesWithSsssuccessfully() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken(), "400");
    }

    @When("My Child account should be able to pay for premium services by DT4B")
    public void myChildAccountShossuldNotBeAbleToPayForPremiumServicesWithSsssuccessfully() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken(), "200");
    }

    @Then("My child account should not be able to pay and see message {string}")
    public void myChildAccountShouldNotBeAbleToPayAndSeeMessage(String errorMsg) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B_VerifyError(getChildToken(), "400", errorMsg);
    }

    @Then("My child account should pay for premium services {string} unsuccessfully and got message {string}")
    public void myChildAccountShouldPayForPremiumServicesUnsuccessfully(String amount, String msg) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B_VerifyError(getChildToken(), "400", msg);
    }

    @Then("My child account should pay for premium services {string} successfully")
    public void myChildAccountShouldPayForPremiumServicesSuccessfully(String arg0) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
        setAmountAfterPayChild(getAmountAfterPayDT4B()); //Get data for check history
    }

    @Then("My child account should pay for premium services {string} unsuccessfully")
    public void myChildAccountShouldPayForPsremiumServicesSuccessfully(String arg0) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken());
    }

    @Given("I register a new account that is upgraded to Biz Account as parent account")
    public void iRegisterANewAccountThatIsUpgradedToBizAccountAsParentAccount() {
        upgrade2BizAccount();
    }


    @Then("My Biz Account A should not be able to link to my Child Account B")
    public void myBizAccountAShouldNotBeAbleToLinkToMyChildAccountB() {
        linkChildToBizFailed(getBizAID(), getChildBID());

    }

    @And("My Biz Account B should not be able to link to my Child Account A")
    public void myBizAccountBShouldNotBeAbleToLinkToMyChildAccountA() {
        linkChildToBizFailed(getBizBID(), getChildAID());
    }

    @Then("My Child Account A should not be able to link to my Child Account B")
    public void myChildAccountAShouldNotBeAbleToLinkToMyChildAccountB() {
        linkChildToBizFailed(getChildAID(), getChildBID());
    }

    @Then("My Biz Account should display in Biz Parent List with {string} Childs")
    public void my_Biz_Account_should_display_in_Biz_Parent_List_with_correct_info(String numOfChild) {
        verifyBizAccountInBizList(getBizAccountID(), getBizPhone(), getParentAccountID(), Integer.parseInt(numOfChild));  // Lost getParentIDForBizParent(), replaced by getParentAccountID
    }

    //------------ SHOP : MAINTENANCE phan PAYMENT ------------------------
    @Given("I pay for PTY Shop by my Child Account using DT")
    public void total_sorder() {
        topupDongTotWithMomo_500k(getChildPhone());
        createShopPTY(getChildToken(), getChildPhone());
    }

    @Given("I pay for ELT Shop by my Child Account using DT")
    public void total_sorsder() {
        topupDongTotWithMomo_500k(getChildPhone());
        createShopELT(getChildToken(), getChildPhone());
    }

    @Given("I pay for VEH Shop by my Child Account using DT")
    public void total_sorsdser() {
        topupDongTotWithMomo_500k(getChildPhone());
        createShopVEH(getChildToken(), getChildPhone());
    }


    @Given("I should pay for creating new PTY Shop by my Child Account using DT4B successfully")
    public void total_sorderDT4B() {
        createShopPTY_DT4B(getChildToken(), getChildPhone());
    }

    @Given("I should pay for creating new ELT Shop by my Child Account using DT4B successfully")
    public void total_sorsderDT4B() {
        createShopELT_DT4B(getChildToken(), getChildPhone());
    }

    @Given("I should pay for creating new VEH Shop by my Child Account using DT4B successfully")
    public void total_sorsdserDT4B() {
        createShopVEH_DT4B(getChildToken(), getChildPhone());
    }


    @Given("I should pay for extending PTY Shop by my Child Account using DT4B successfully")
    public void total_sorderDT4extendingB() {
        extendShopPTY(getChildToken(), getShopAlias(), 1, true);
    }

    @Given("I should pay for extending ELT Shop by my Child Account using DT4B successfully")
    public void total_sorsderextendingDT4B() {
        extendShopELT(getChildToken(), getShopAlias(), 1, true);
    }

    @Given("I should pay for extending VEH Shop by my Child Account using DT4B successfully")
    public void total_sorsdserDextendingT4B() {
        extendShopVEH(getChildToken(), getShopAlias(), 1, true);
    }

    @When("The parent filter total spending of the child from {string} to {string}")
    public void the_parent_filter_total_spending_of_the_child_from_to(String filterTimeFrom, String filterTimeTo) {
        getBizCostAll(getBizToken(), filterTimeFrom, filterTimeTo);
    }
    @When("The parent filter Biz cost by child from 1st day of the month to Today")
    public void the_parent_filter_Biz_cost_by_child_from_to() {
        getBizCostByChild(getBizToken(), getListChildAID());
    }

    @Then("The parent should be able to view total spending of Biz Account within filter time")
    public void the_parent_should_be_able_to_view_total_spending_of_Biz_Account_within_filter_time() {
        verifyBizCost();
    }
    @Given("I register {string} Child Account with {string} House Ad Listing Fee that is linked to my Biz Account")
    public void i_register_Child_Account_with_House_Ad_Listing_Fee_that_is_linked_to_my_Biz_Account(String numberOfAccount, String numberOfAds) {
        createListChildWithNumberOfAdListingFee(numberOfAccount, numberOfAds);
    }
    //------------ NEW COMMON STEPS ------------------------
    //Author: VUHOANG       Date: Dev 22nd 2020

    @Given("I login my Biz Account with {string} Đồng Tốt")
    @When("I login my other Biz Account with {string} Đồng Tốt")
    public void i_login_my_Biz_Account_with_Đồng_Tốt(String balance, DataTable table) {
        registerBizAccount();
        topupDongTotWithMomo(getBizPhone(), Integer.parseInt(balance));     //100k, 200k, 500k
        int amountTotal = Integer.parseInt(table.row(1).get(0));
        int amountPerChild = Integer.parseInt(table.row(1).get(1));
        int amountPerOrder = Integer.parseInt(table.row(1).get(2));
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);
        verifySetBudget(getBizAccountID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));
    }

    @Given("I login my Biz Account with 30.000.000 Đồng Tốt")
    public void i_login_my_Biz_Account_with_Đồng_Tốts(DataTable table) {
        registerBizAccount();
        for (int i = 0; i < 10; i++) {
            topupDongTotWithMomo_3000k(getBizPhone());
        }
        int amountTotal = Integer.parseInt(table.row(1).get(0));
        int amountPerChild = Integer.parseInt(table.row(1).get(1));
        int amountPerOrder = Integer.parseInt(table.row(1).get(2));
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);
        verifySetBudget(getBizAccountID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));
    }

    @When("I update budget limitation of my Biz Account")
    public void update_limitation(DataTable table) {
        waitConstant(10);
        int amountTotal = Integer.parseInt(table.row(1).get(0));
        int amountPerChild = Integer.parseInt(table.row(1).get(1));
        int amountPerOrder = Integer.parseInt(table.row(1).get(2));
        updateBudgetLimitationBizAccount(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);
        verifySetBudget(getBizAccountID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));
    }

    @Given("I login my Child Account")
    @Given("I login my another Child Account")
    public void i_login_my_Child_Account(DataTable table) {
        registerChildAccount();

        int amountTotal = Integer.parseInt(table.row(1).get(0));
        int amountPerChild = 0;
        int amountPerOrder = Integer.parseInt(table.row(1).get(2));

        upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);
        verifySetBudget(getChildID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));
    }

    @Given("I login {string} Child Account")
    public void i_login_my_Child_Account(String numberOfChild, DataTable table) {
        for (int i = 0; i < Integer.parseInt(numberOfChild); i++) {
            registerChildAccount();

            int amountTotal = Integer.parseInt(table.row(1).get(0));

            String amountPerChildInput = table.row(1).get(1);
            int amountPerChild = 0;
            if (!amountPerChildInput.contains("-")) {
                amountPerChild = Integer.parseInt(amountPerChildInput);
            }

            int amountPerOrder = Integer.parseInt(table.row(1).get(2));     //Doesn't effect

            upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);
//            verifySetBudget(getChildID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));
        }
    }

    @Given("I login {string} Child Account and pay bump")
    public void i_login_my_Child_Acscount(String numberOfChild, DataTable table) {
        for (int i = 0; i < Integer.parseInt(numberOfChild); i++) {
            registerChildAccount();

            int amountTotal = Integer.parseInt(table.row(1).get(0));

            String amountPerChildInput = table.row(1).get(1);
            int amountPerChild = 0;
            if (!amountPerChildInput.contains("-")) {
                amountPerChild = Integer.parseInt(amountPerChildInput);
            }

            int amountPerOrder = Integer.parseInt(table.row(1).get(2));     //Doesn't effect

            upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);

            flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
            posBump(getChildToken(), tempAdID);
            paymentOrderDT4B(getChildToken());
        }
    }

    @Given("I login {string} Child Account to pay for {string} per account")
    public void i_login_my_Child_Assccount(String numberOfChild, String numberOfAd, DataTable table) {
        for (int i = 0; i < Integer.parseInt(numberOfChild); i++) {
            registerChildAccount();
            System.out.println("NEW CHILD USER: " + i);
            System.out.println("NEW CHILD TOKEN: " + global_accessToken);
            int amountTotal = Integer.parseInt(table.row(1).get(0));

            String amountPerChildInput = table.row(1).get(1);
            int amountPerChild = 0;
            if (!amountPerChildInput.contains("-")) {
                amountPerChild = Integer.parseInt(amountPerChildInput);
            }

            int amountPerOrder = Integer.parseInt(table.row(1).get(2));     //Doesn't effect

            upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);
            verifySetBudget(getChildID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));

            //pay
            global_accessToken = getChildToken();
            for (int j = 0; j < Integer.parseInt(numberOfAd); j++) {
                flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
                posBump(getChildToken(), tempAdID);
            }
            paymentOrderDT4B(getChildToken());
            System.out.println("PAY AT " + i + " TIME");
        }

    }

    @Given("I update budget limitation of my Child Account")
    public void update_budget_limitation_child(DataTable table) {
        int amountTotal = Integer.parseInt(table.row(1).get(0));
        int amountPerChild = 0;
        int amountPerOrder = Integer.parseInt(table.row(1).get(2));     //Doesn't effect
        updateBudget4Child(getChildID(), getBizAccountID(), amountTotal, amountPerChild, amountPerOrder, "active");
        verifySetBudget(getChildID(), String.valueOf(amountTotal), String.valueOf(amountPerChild), String.valueOf(amountPerOrder));
    }

    @When("I can't pay more by my Child Account")
    public void i_cannot_ssuse_my_Biz_Account_to_pay_for_premium_services_with_child() {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B(getChildToken(), "400");
    }

    @When("I can't pay more by my Child Account with announce {string}")
    @Then("I can't pay DT4B by my Child Account with announce {string}")
    public void i_cannot_ssuse_my_Biz_Account_to_pay_for_premium_services_with_chsild(String errorMsg) {
        global_accessToken = getChildToken();
        flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
        posBump(getChildToken(), tempAdID);
        paymentOrderDT4B_VerifyError(getChildToken(), "400", errorMsg);
    }

    @When("I can't pay again by my Child Account with announce {string}")
    public void i_cannot_ssuse_my_Biz_Account_to_pay_forss_premium_services_with_chsild(String errorMsg) {
        paymentOrderDT4B_VerifyError(getChildToken(), "400", errorMsg);
    }

    @Then("Budget Limitation of my Biz Account should be correct")
    public void my_account_should_become_Biz_Account_successfully(DataTable table) {
        String bizTotal = table.row(1).get(0).trim();
        String bizChildPerMonth = table.row(1).get(1).trim();
        String bizChildPerOrder = table.row(1).get(2).trim();
        verifySetBudget(getBizAccountID(), bizTotal, bizChildPerMonth, bizChildPerOrder);
    }

    @Then("Total Used of my Biz Account should be {string}")
    public void total_Used_of_my_Biz_Account_is(String totalused) {

    }

    //============================ Register  ============================

    @Given("I register a new Biz Account with valid info and legal document")
    public void i_register_a_new_Biz_Account_with_valid_info_and_legal_document() {
        getAccessTokenOfNewUser();
        setBizRegisterAccountID(registerBizAccountByUser(global_accessToken, global_accountID));
    }

    @Given("I register a new Biz Account and see my request is sent successfully")
    public void i_registser_a_new_Biz_Account_with_valid_info_and_legal_document() {
        getAccessTokenOfNewUser();
        setBizRegisterAccountID(registerBizAccountByUser(global_accessToken, global_accountID));
        Assert.assertNotNull("Biz Register Token is null", getBizRegisterToken());
        Assert.assertNotNull("Biz Register Account ID is null", getBizRegisterAccountID());
        checkRegisterRequestStatusByUser(getBizRegisterToken(), getBizRegisterAccountID(), "REVIEWING");
    }

    @Given("I update my Biz Register request when Chotot rejected my request")
    public void i_registsser_a_new_Biz_Account_with_vssalid_info_and_legal_document() {
        setBizRegisterAccountID(registerBizAccountByUser(global_accessToken, global_accountID));
        Assert.assertNotNull("Biz Register Token is null", getBizRegisterToken());
        Assert.assertNotNull("Biz Register Account ID is null", getBizRegisterAccountID());
        checkRegisterRequestStatusByUser(getBizRegisterToken(), getBizRegisterAccountID(), "REVIEWING");
    }

    @Given("I register a new Biz Account on 2 times")
    public void i_register_a_new_Biz_Accounts_with_valid_info_and_legal_document() {
        getAccessTokenOfNewUser();
        setBizRegisterAccountID(registerBizAccountByUser(global_accessToken, global_accountID));
        waitConstant(2);
        setBizRegisterAccountID(registerBizAccountByUser(global_accessToken, global_accountID));
    }

    @Then("I should see my register request is sent to Chotot with status REVIEWING")
    @Then("I should see only 1 register request is sent to Chotot with status REVIEWING")
    public void my_register_request_should_be_sent_to_Chotot() {
        Assert.assertNotNull("Biz Register Token is null", getBizRegisterToken());
        Assert.assertNotNull("Biz Register Account ID is null", getBizRegisterAccountID());
        checkRegisterRequestStatusByUser(getBizRegisterToken(), getBizRegisterAccountID(), "REVIEWING");
    }

    @Then("I should see my register request is sent to Chotot with status REJECTED")
    public void my_register_request_should_be_sent_to_Chotot_rejected() {
        Assert.assertNotNull("Biz Register Token is null", getBizRegisterToken());
        Assert.assertNotNull("Biz Register Account ID is null", getBizRegisterAccountID());
        checkRegisterRequestStatusByUser(getBizRegisterToken(), getBizRegisterAccountID(), "REJECTED");
    }

    @Then("I should see my register request is sent to Chotot with status APPROVED")
    public void my_register_request_should_be_sent_to_Chotot_approved() {
        Assert.assertNotNull("Biz Register Token is null", getBizRegisterToken());
        Assert.assertNotNull("Biz Register Account ID is null", getBizRegisterAccountID());
        setBizRegisterRequestID(checkRegisterRequestStatusByUser(getBizRegisterToken(), getBizRegisterAccountID(), "APPROVED"));
    }

    @Then("Chotot should receive the register request with status REVIEWING")
    @Then("Chotot should receive only 1 register request with status REVIEWING")
    public void chotot_should_receive_the_register_request_with_status_REVIEWING() {
        checkRegisterRequestStatusByChotot(getBizRegisterAccountID(), "REVIEWING");
    }

    @Then("Chotot should receive the register request with status APPROVED")
    public void chotot_should_receive_the_register_request_with_status_APPROVED() {
        checkRegisterRequestStatusByChotot(getBizRegisterAccountID(), "APPROVED");
    }

    @Then("Chotot should receive the register request with status REJECTED")
    public void chotot_should_receive_the_register_request_wisth_status_REJECTED() {
        checkRegisterRequestStatusByChotot(getBizRegisterAccountID(), "REJECTED");
    }

    @When("Chotot approves my Biz Register Request")
    public void chotot_approves_my_Biz_Register_Request() {
        approveRegisterRequestByChotot(getBizRegisterRequestID());
    }

    @When("Chotot rejects my Biz Register Request")
    public void chotot_rejects_my_Biz_Register_Request() {
        rejectRegisterRequestByChotot(getBizRegisterRequestID());
    }

    @Then("My Registered Account should be Biz Account")
    public void my_account_should_be_biz_account() {
        verifyIsParentAccount(getBizRegisterAccountID());
    }

    @Then("I can add some Child Account")
    public void i_can_add_some_child_account() {
        registerChildAccount();
        upgradeToChildAccount(getBizRegisterAccountID(), getChildID(), 0, 0, 0);
        verifySetBudget(getChildID(), String.valueOf(0), String.valueOf(0), String.valueOf(0));
    }

    @Then("I can't add Child Account")
    public void i_can_t_add_Child_Account() {
        verifyIsNotParentAccount(getBizRegisterAccountID());
    }

    @Then("I can't update my Biz Register request and got Error: Register is exists")
    public void can_not_update() {
        registerBizAccountByUserFailed(global_accessToken, global_accountID, "Register is exists");
    }

    @Then("I can't register a Biz Register request for my Child Account")
    public void i_can_t_register_a_Biz_Register_request_for_my_Child_Account() {
        registerBizAccountByUserFailed(getChildToken(), getChildID(), "Your account is already Biz Account");
    }

    @When("I login {string} Child Account and pay {string} orders cost {string} each order")
    public void i_login_my_Child_Account_and_pay_orders_cost_each_order(String numberOfChild, String numberOfAd, String balance, io.cucumber.datatable.DataTable table) {
        for (int j = 0; j < Integer.parseInt(numberOfChild); j++) {
            try {
                registerChildAccount();

                int amountTotal = Integer.parseInt(table.row(1).get(0));

                String amountPerChildInput = table.row(1).get(1);
                int amountPerChild = 0;
                if (!amountPerChildInput.contains("-")) {
                    amountPerChild = Integer.parseInt(amountPerChildInput);
                }

                int amountPerOrder = Integer.parseInt(table.row(1).get(2));     //Doesn't effect

                upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);


                global_accessToken = getChildToken();
                for (int i = 0; i < Integer.parseInt(numberOfAd); i++) {
                    flashAd_pty.insertHouseSellPrivate(global_accessToken, "", false);
                    posBump(getChildToken(), tempAdID);
                    paymentOrderDT4B(getChildToken());
                }
            } catch (AssertionError error) {

            } catch (Exception e) {

            }
        }
    }


    //============================  Remaining & AmountPerOrder  ============================
    @Then("Remaining Amount \\(Hạn mức còn lại) of my Child Account should be Chưa Cài Đặt")
    public void remaining_Amount_() {
        checkRemainingBudget_Unlimited(getChildToken());
    }

    @Then("Remaining Amount \\(Hạn mức còn lại) of my Child Account should be 0")
    public void remaining_Amount_ld_Account_should_be() {
        checkRemainingBudget(getChildToken(), "0");
    }

    @Then("Remaining Amount \\(Hạn mức còn lại) of my Child Account should be {string}")
    public void remaining_Amount_my_Child_Account_should_be(String value) {
        checkRemainingBudget(getChildToken(), value);
    }

    @Then("Amount Per Order \\(Hạn mức trên mỗi Giao Dịch) of my Child Account should be Chưa Cài Đặt")
    public void amount_Per_Order_rder_of_my_Child_Account_should_be_() {
        checkPerOrderLimitation_Unlimited(getChildToken());
    }

    @Then("Amount Per Order \\(Hạn mức trên mỗi Giao Dịch) of my Child Account should be {string}")
    public void amount_Per_Ordi_Order_of_my_Child_Account_should_be(String value) {
        checkPerOrderLimitation(getChildToken(), value);
    }

    //Quang steps
    @Then("Default value of reset date should be 1")
    @Then("Reset date should be 1")
    public void defaultValueOfResetDateShouldBe() {
        checkResetDateDefault();
    }

    @Then("I can update the reset date successfully")
    public void iCanUpdateTheResetDateSuccessfully() {
        updateResetDateToFuture(0);
    }

    @And("I Deactivate biz account")
    public void iDeactivateBizAccount() {
        updateStatusToInactive();
    }

    @And("I Reactivate biz account")
    public void iReactivateBizAccount() {
        updateStatusToActive();
    }

    @And("I update Reset Date to a date in the past")
    public void iUpdateResetDateToADateInThePast(DataTable table) {
        int amountPerChild = Integer.parseInt(table.row(1).get(0));
        updateResetDateToPast(amountPerChild);
    }

    @And("I update Reset Date to a date in the future")
    public void iUpdateResetDateToADateInTheFuture(DataTable table) {
        int amountPerChild = Integer.parseInt(table.row(1).get(0));
        updateResetDateToFuture(amountPerChild);
    }

    @And("I update Reset Date to current day")
    public void iUpdateResetDateToCurrentDay(DataTable table) {
        int amountPerChild = Integer.parseInt(table.row(1).get(0));
        updateResetDateToToday(amountPerChild);
    }

    @Then("The remaining budget is not reset")
    public void theRemainingBudgetIsNotReset() {
        checkNotResetTotalUsedAmount();
    }

    @Then("The remaining budget is reset")
    public void theRemainingBudgetIsReset() {
        checkResetTotalUsedAmount();
    }

    @Then("Reset date is not changed")
    public void resetDateIsNotChanged() {
        checkResetDateChanged();
    }

    @Then("The start time is updated")
    public void theStartTimeIsUpdated() {
        verifyStartTime();
    }

    @And("The end time is updated to next day in month")
    public void theEndTimeIsUpdatedToNextDayInMonth() {
        verifyEndTimeInMonth();
    }

    @And("The end time is updated to next month")
    public void theEndTimeIsUpdatedToNextMonth() {
        verifyEndTimeInNextMonth();
    }

    @When("I delete child account from parent account")
    public void iDeleteChildAccountFromParentAccount() {
        deleteChildAccount(getChildAccountID());
    }

    @Then("The status of child account should be DELETED")
    public void theStatusOfChildAccountShouldBeDELETED() {
        verifyDeleteChildStatus();
    }

    @When("I add deleted child account to same parent account")
    @When("I add again the Child Account that I deleted")
    public void iAddDeletedChildAccountToSameParentAccount() {
        addExistingChildToBizAccount(getBizAccountID(), getChildAccountID());
    }

    @Then("The status of child account should be ACTIVE")
    public void theStatusOfChildAccountShouldBeACTIVE() {
        verifyActiveChildStatus();
    }

    @When("I add deleted child account to other parent account")
    public void iAddDeletedChildAccountToOtherParentAccount() {
        addExistingChildToBizAccount(getBizAccountID(), getChildAccountID());
    }

    @Then("I can not add child account to old parent account")
    public void iCanNotAddChildAccountToOldParentAccount() {
        linkMyChildToMyBizFailed(getOldParentBizID(), getChildID());
    }

    @When("I login my Child Account with status is Inactive")
    public void iLoginMyChildAccountWithStatusIsInactive() {
        registerChildAccount();
        updateChildToInactive(getBizToken(), getBizAccountID(), getChildID());
    }

    @Then("I add deleted Inactive child account to same parent account")
    public void iAddDeletedInactiveChildAccountToSameParentAccount() {
        addExistingInactiveChildToBizAccount();
    }

    @Then("I add deleted Inactive child account to other parent account")
    public void iAddDeletedInactiveChildAccountToOtherParentAccount() {
        addExistingInactiveChildToBizAccount();
    }

    // =============================== DEBIT APPROACH ===============================
    private String debitErrorMsg = "";
    private String debitErrorMsg_FromChild = "";

    @Given("I login my Biz Account with {string} Đồng Tốt 365")
    public void i_login_my_Biz_Account_witsh_Đồng_Tốt_to_transfer_dt_365(String balance) {
        registerBizAccount();
        if (!balance.isEmpty()) {
            topupDongTotWithMomo(getBizPhone(), Integer.parseInt(balance));     //100k, 200k, 500k
        }
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);
    }

    @Given("I login my another Biz Account to receive DT Transfer")
    public void i_login_my_ssBiz_Account_witsh_Đồng_Tốt_to_transfer_dt_365() {
        getAccessTokenOfNewUser();
        upgradeToBizAccountAsParent(global_accountID, 0, 0, 0);
        addChildReceiverAccountIDs(Integer.parseInt(global_accountID));
        addChildReceiverTokens(global_accessToken);
    }

    @Given("I login my Biz Account with {string} Đồng Tốt Free")
    public void i_login_my_Biz_Account_with_Đồng_Tốt_to_transfer_dt_free(String balance) {
        registerBizAccount();
        topupDongTotFree_ByRedeemPromotionCode(getBizAccountID(), getBizPhone(), Integer.parseInt(balance));
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);

        // Check DT Free balance after topped up
        verifyDTBalance_Free(getBizToken(), balance);
        setParentDTFree_ExpiredClosest(getDTBalance_Free_ExpiredClosest(getBizToken()));
    }

    @Given("I login my Biz Account with {string} Đồng Tốt Free but expired")
    public void i_login_my_Biz_Account_with_Đồng_Tốt_to_transfer_dt_free_expired(String balance) {
        registerBizAccount();
        topupDongTotFree_ByRedeemPromotionCode(getBizAccountID(), getBizPhone(), Integer.parseInt(balance));    // dang sua cai nay
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);

        // Check DT Free balance after topped up
        verifyDTBalance_Free(getBizToken(), balance);
        setParentDTFree_ExpiredClosest(getDTBalance_Free_ExpiredClosest(getBizToken()));
    }

    private int expiredDTExpiryOfParent = 0;

    @Given("I login my Biz Account with 20k Đồng Tốt Expiry")
    public void i_login_my_Biz_Account_with_Đồng_Tốt_to_transfer_dt_expiry() {
        registerBizAccount();
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);

        // Topup
        topupDongTotExpiry_20k(getBizAccountID(), getBizPhone());
        // VUHOANG DEBUG: Bank Transfer has a problem, topup to 365 -> checking
        try {
            verifyDTBalance_Expiry(getBizToken(), "20000");
        } catch (AssertionError e) {
            topupDongTotExpiry_20k(getBizAccountID(), getBizPhone());
            verifyDTBalance_Expiry(getBizToken(), "20000");
        }


        // Set value for next steps
        setParentDTExpiry_ExpiredClosest(getDTBalance_Expiry_ExpiredClosest(getBizToken()));

    }

    @Given("I login my Child Account to receive DT transfer from Parent")
    public void i_login_my_child_account_receive_dt_from_parent() {
        registerChildAccount();
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;

        upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);
        addChildReceiverAccountIDs(Integer.parseInt(getChildAccountID()));
    }

    @Given("I login {string} Child Accounts to receive DT transfer from Parent")
    public void i_login_n_child_account_receive_dt_from_parent(String numberOfAccount) {
        for (int i = 0; i < Integer.parseInt(numberOfAccount); i++) {
            registerChildAccount();
            int amountTotal = 0;
            int amountPerChild = 0;
            int amountPerOrder = 0;

            upgradeToChildAccount(getBizAccountID(), getChildID(), amountTotal, amountPerChild, amountPerOrder);
            addChildReceiverAccountIDs(Integer.parseInt(getChildAccountID()));
            addChildReceiverTokens(getChildToken());
        }
    }

    @Given("I login my another one that is not my Child Accounts")
    public void login_private_() {
        getAccessTokenOfNewUser();
        setChildPhone(tempUserPhone);
        setChildAccountID(global_accountID);
        setChildToken(global_accessToken);
        addChildReceiverAccountIDs(Integer.parseInt(getChildAccountID()));
        addChildReceiverTokens(getChildToken());
    }


    // Transfer DT 365
    @When("I transfer {string} Đồng Tốt 365 to each Child")
    @Then("I can transfer {string} Đồng Tốt 365 to each Child")
    public void i_transfer_DT365_to_my_Childs(String amount) {
        transferDongTot(getBizToken(), DEBIT_DONGTOT_TYPE_365, amount, getChildReceiverAccountIDs());
    }

    @Then("I cannot transfer {string} Đồng Tốt 365 to each Child")
    @Then("I cannot transfer {string} Đồng Tốt 365 to users who are not my Childs")
    public void i_not_transfer_DT365_to_my_Childs(String amount) {
        debitErrorMsg = transferDongTotFailed(getBizToken(), DEBIT_DONGTOT_TYPE_365, amount, getChildReceiverAccountIDs());
    }

    @Then("I cannot transfer {string} Đồng Tốt 365 to my another Biz Account")
    public void i_not_transfer_DT365_to_another_parent(String amount) {
        String currentBizToken = getBizToken();
        // Create new Biz
        registerBizAccount();
        upgradeToBizAccountAsParent(getBizAccountID(), 0, 0, 0);
        int newBizAccountID = Integer.parseInt(getBizAccountID());

        // user current Biz transfer DT to another Biz
        debitErrorMsg = transferDongTotFailed(currentBizToken, DEBIT_DONGTOT_TYPE_365, amount, newBizAccountID);
    }

    @Then("I cannot transfer {string} Đồng Tốt 365 from Child Account to Parent")
    public void i_not_transfer_DT365_by_Childs(String amount) {
        List<Integer> accIDs = new ArrayList<>();
        accIDs.add(Integer.parseInt(getBizAccountID()));
        debitErrorMsg_FromChild = transferDongTotFailed(getChildToken(), DEBIT_DONGTOT_TYPE_365, amount, accIDs);
    }

    @When("I transfer {string} Đồng Tốt Free to each Child")
    @When("I can transfer {string} Đồng Tốt Free to each Child")
    public void i_transfer_DTFree_to_my_Childs(String amount) {
        transferDongTot(getBizToken(), DEBIT_DONGTOT_TYPE_FREE, amount, getChildReceiverAccountIDs());
    }

    @Then("I cannot transfer {string} Đồng Tốt Free to each Child")
    public void i_not_transfer_DTFree_to_my_Childs(String amount) {
        debitErrorMsg = transferDongTotFailed(getBizToken(), DEBIT_DONGTOT_TYPE_FREE, amount, getChildReceiverAccountIDs());
    }

    @When("I transfer {string} Đồng Tốt Expiry to each Child")
    @When("I can transfer {string} Đồng Tốt Expiry to each Child")
    public void i_transfer_DTExpiry_to_my_Childs(String amount) {
        transferDongTot(getBizToken(), DEBIT_DONGTOT_TYPE_EXPIRY, amount, getChildReceiverAccountIDs());
    }

    @Then("I cannot transfer {string} Đồng Tốt Expiry to each Child")
    public void i_not_transfer_DTExpiry_to_my_Childs(String amount) {
        debitErrorMsg = transferDongTotFailed(getBizToken(), DEBIT_DONGTOT_TYPE_EXPIRY, amount, getChildReceiverAccountIDs());
    }


    @Then("I got error message to my Biz Account {string}")
    public void i_got_error_message_to_my_Biz_Account(String errorMsgSce) {
        Assert.assertEquals("DEBIT ERROR MESSAGE IS WRONG", errorMsgSce.trim().toUpperCase(), debitErrorMsg.trim().toUpperCase());
    }

    @Then("I got error message to my Child Account {string}")
    public void i_got_error_message_to_msy_Biz_Account(String errorMsgSce) {
        Assert.assertEquals("DEBIT ERROR MESSAGE IS WRONG", errorMsgSce.trim().toUpperCase(), debitErrorMsg_FromChild.trim().toUpperCase());
    }

    // Verify balance
    @Then("Each Child should receive {string} Đồng Tốt 365")
    public void each_Child_should_receive_Đồng_Tốt(String balance) {
        int expectedBalance = Integer.parseInt(balance);
        int actualBalance = -1;
        for (int i = 0; i < getChildReceiverTokens().size(); i++) {
            actualBalance = getBalanceDT(getChildReceiverTokens().get(i));
            Assert.assertEquals("Each child doesn't receive " + expectedBalance, expectedBalance, actualBalance);
        }
    }

    @Then("Each Child should have {string} Đồng Tốt in Total")
    public void each_Child_should_receive_Đồng_Tốt_Total(String balance) {
        int expectedBalance = Integer.parseInt(balance);
        int actualBalance = -1;
        String childToken = "";
        for (int i = 0; i < getChildReceiverTokens().size(); i++) {
            childToken = getChildReceiverTokens().get(i);
            actualBalance = Integer.parseInt(getDTBalance_Expiry_AmountTotal(childToken));      //VUHOANG DEBUG, call API get all
            Assert.assertEquals("Total Amount of each child is incorrect", expectedBalance, actualBalance);
        }
    }

    @Then("Each Child should have {string} Đồng Tốt Expiry")
    public void each_Child_should_receive_Đồng_Tốt_Expiry(String balance) {
        int expectedBalance = Integer.parseInt(balance);
        int actualBalance = -1;
        String childToken = "";
        for (int i = 0; i < getChildReceiverTokens().size(); i++) {
            childToken = getChildReceiverTokens().get(i);
            actualBalance = Integer.parseInt(getDTBalance_Expiry_AmountTotal(childToken));
            Assert.assertEquals("Total Expiry Amount of each child is incorrect", expectedBalance, actualBalance);
        }
    }

    @Then("Each Child should have {string} Đồng Tốt Free")
    public void each_Child_should_receive_Đồng_Tốt_Free(String balance) {
        int expectedBalance = Integer.parseInt(balance);
        int actualBalance = -1;
        String childToken = "";
        for (int i = 0; i < getChildReceiverTokens().size(); i++) {
            childToken = getChildReceiverTokens().get(i);
            actualBalance = Integer.parseInt(getDTBalance_Free_AmountTotal(childToken));
            Assert.assertEquals("Total Free Amount of each child is incorrect", expectedBalance, actualBalance);
        }
    }

    @Then("Each Child should receive {string} Đồng Tốt Expiry with same Closest expired as Parent")
    public void each_Child_should_receive_Đồng_Tốt_Expiry_closest(String balance) {
        String childToken = "";
        for (int i = 0; i < getChildReceiverTokens().size(); i++) {
            childToken = getChildReceiverTokens().get(i);

            // Verify DT Amount
            Assert.assertEquals("Closest Amount of each child is incorrect"
                    , balance
                    , getDTBalance_Expiry_AmountClosest(childToken));

            // Verify DT Closest Expired
            Assert.assertEquals("Closest Expired of each child is incorrect"
                    , getParentDTExpiry_ExpiredClosest()
                    , getDTBalance_Expiry_ExpiredClosest(childToken));
        }
    }

    @Then("Each Child should receive {string} Đồng Tốt Free with same Closest expired as Parent")
    public void each_Child_should_receive_Đồng_Tốt_Free_closest(String balance) {
        String childToken = "";
        for (int i = 0; i < getChildReceiverTokens().size(); i++) {
            childToken = getChildReceiverTokens().get(i);

            // Verify DT Amount
            Assert.assertEquals("Closest Amount of each child is incorrect"
                    , balance
                    , getDTBalance_Free_AmountClosest(childToken));

            // Verify DT Closest Expired
            Assert.assertEquals("Closest Expired of each child is incorrect"
                    , getParentDTFree_ExpiredClosest()
                    , getDTBalance_Free_ExpiredClosest(childToken));
        }
    }

    @Then("My Biz Account should have {string} Đồng Tốt 365")
    public void my_Biz_Account_shoudl_have_Đồng_Tốt_365(String balance) {
        Assert.assertEquals("Biz Parent DT365 balance is incorrect"
                , balance
                , getDTBalance_365_AmountTotal(getBizToken()));
    }

    @Then("My Biz Account should have {string} Đồng Tốt Expiry")
    public void my_Biz_Account_should_have_Đồng_Tốt_Expiry(String balance) {
        Assert.assertEquals("Total amount of Parent is incorrect!"
                , balance
                , getDTBalance_Expiry_AmountTotal(getBizToken())
        );
    }

    @Then("My Biz Account should have {string} Đồng Tốt Free")
    public void my_Biz_Account_should_have_Đồng_Tốt_Free(String balance) {
        Assert.assertEquals("Total amount of Parent is incorrect!"
                , balance
                , getDTBalance_Free_AmountTotal(getBizToken())
        );
    }

    @Deprecated
    @When("My Child Account has 20k Đồng Tốt Expiry to receive DT Transfer")
    public void my_child_acc_has_20k_dt_expiry_but_expired() {
        topupDongTotExpiry_20k(getChildAccountID(), getChildPhone(), getToday_MMddyyyy());      // Fixed code

        // VUHOANG DEBUG: Bank Transfer has a problem, topup to 365 -> checking
        try {
            verifyDTBalance_Expiry(getChildToken(), "20000");
        } catch (AssertionError e) {
            topupDongTotExpiry_20k(getChildAccountID(), getChildPhone(), getToday_MMddyyyy());      // Fixed code
            verifyDTBalance_Expiry(getBizToken(), "20000");
        }
        setChildDTExpiry_ExpiredClosest(getDTBalance_Expiry_ExpiredClosest(getChildToken()));
    }

    @Then("Expired Date of Đồng Tốt Expiry should not changed on my Child Account")
    public void verify_expiry_not_change_expired_date() {
        String expectedExpired = getChildDTExpiry_ExpiredClosest();
        String actualExpired = getDTBalance_Expiry_ExpiredClosest(getChildToken());
        Assert.assertEquals("Expired Date is changed when Parent transfer 0 DT to trick"
                , expectedExpired
                , actualExpired
        );
    }

    @When("I delete my Child Account to stop receiving DT Transfer")
    public void iDeleteChildAccountFromParentAccousnt() {
        for (int i = 0; i < getChildReceiverAccountIDs().size(); i++) {
            deleteChildAccount(String.valueOf(getChildReceiverAccountIDs().get(i)));
        }
    }

    // DEBIT ORDER HISTORY : DT 365
    @Then("An Order of Transfering {string} Đồng Tốt 365 should be created on my Biz Account with status SUCCESS")
    public void an_Order_of_Transfering_Đồng_Tốt_should_be_created_on_my_Biz_Account_with_status_SUCCESS(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_365
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_SUCCESS));
    }

    @Then("An Order of Transfering {string} Đồng Tốt 365 should be created on my Biz Account with status FAILED")
    public void an_Order_of_Transfering_Đồng_Tốt_should_be_created_on_my_Biz_Account_with_status_FAILED(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_365
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_FAILED));
    }

    @Then("An Order of Transfering {string} Đồng Tốt 365 should be created on my Biz Account with status PENDING")
    public void an_Order_of_Transfering_Đồng_Tốt_should_be_created_on_my_Biz_Account_with_status_PENDING(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_365
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_PENDING));
    }


    @Then("Order Details of Transfering {string} Đồng Tốt 365 to each Child should be correct")
    public void info_correct(String amount) {
        checkOrderHistory_Debit_TransferDongTot_Details(getBizToken()
                , getOrderDebitID()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_365
                , Integer.parseInt(amount)
        );
    }

    // DEBIT ORDER HISTORY : DT Expiry
    @Then("An Order of Transfering {string} Đồng Tốt Expiry should be created on my Biz Account with status SUCCESS")
    public void an_Order_of_Transfering_Đồng_Tốt_Expiry_should_be_created_on_my_Biz_Account_with_status_SUCCESS(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_EXPIRY
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_SUCCESS));
    }

    @Then("An Order of Transfering {string} Đồng Tốt Expiry should be created on my Biz Account with status FAILED")
    public void an_Order_of_Transfering_Đồng_Tốt_Expiry_should_be_created_on_my_Biz_Account_with_status_FAILED(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_EXPIRY
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_FAILED));
    }

    @Then("An Order of Transfering {string} Đồng Tốt Expiry should be created on my Biz Account with status PENDING")
    public void an_Order_of_Transfering_Đồng_Tốt_Expiry_should_be_created_on_my_Biz_Account_with_status_PENDING(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_EXPIRY
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_PENDING));
    }


    @Then("Order Details of Transfering {string} Đồng Tốt Expiry to each Child should be correct")
    public void info_correct_expiry(String amount) {
        checkOrderHistory_Debit_TransferDongTot_Details(getBizToken()
                , getOrderDebitID()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_EXPIRY
                , Integer.parseInt(amount)
        );
    }

    // DEBIT ORDER HISTORY : DT Free
    @Then("An Order of Transfering {string} Đồng Tốt Free should be created on my Biz Account with status SUCCESS")
    public void an_Order_of_Transfering_Đồng_Tốt_Free_should_be_created_on_my_Biz_Account_with_status_SUCCESS(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_FREE
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_SUCCESS));
    }

    @Then("An Order of Transfering {string} Đồng Tốt Free should be created on my Biz Account with status FAILED")
    public void an_Order_of_Transfering_Đồng_Tốt_Free_should_be_created_on_my_Biz_Account_with_status_FAILED(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_FREE
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_FAILED));
    }

    @Then("An Order of Transfering {string} Đồng Tốt Free should be created on my Biz Account with status PENDING")
    public void an_Order_of_Transfering_Đồng_Tốt_Free_should_be_created_on_my_Biz_Account_with_status_PENDING(String amount) {
        setOrderDebitID(checkOrderHistory_Debit_TransferDongTot(getBizToken()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_FREE
                , Integer.parseInt(amount)
                , DEBIT_ORDER_STATUS_PENDING));
    }


    @Then("Order Details of Transfering {string} Đồng Tốt Free to each Child should be correct")
    public void info_correct_Free(String amount) {
        checkOrderHistory_Debit_TransferDongTot_Details(getBizToken()
                , getOrderDebitID()
                , getChildReceiverAccountIDs()
                , DEBIT_DONGTOT_TYPE_FREE
                , Integer.parseInt(amount)
        );
    }


    // Check order history of Child
    @Then("An Order of Receiving {string} Đồng Tốt 365 should be created in Child Order History")
    public void an_Order_of_Receiving_Đồng_Tốt_365_should_be_created_in_Child_Order_History(String amount) {
        for(int i = 0; i < getChildReceiverTokens().size(); i++) {
            checkOrderHistory_Debit_ReceiveDongTot(getChildReceiverTokens().get(i)
                    , getChildReceiverAccountIDs().get(i)
                    , getBizAccountID()
                    , DEBIT_DONGTOT_TYPE_365
                    , Integer.parseInt(amount));
        }
    }

    @Then("An Order of Receiving {string} Đồng Tốt Expiry should be created in Child Order History")
    public void an_Order_of_Receiving_Đồng_Tốt_Expiry_should_be_created_in_Child_Order_History(String amount) {
        for(int i = 0; i < getChildReceiverTokens().size(); i++) {
            checkOrderHistory_Debit_ReceiveDongTot(getChildReceiverTokens().get(i)
                    , getChildReceiverAccountIDs().get(i)
                    , getBizAccountID()
                    , DEBIT_DONGTOT_TYPE_EXPIRY
                    , Integer.parseInt(amount));
        }
    }

    @Then("An Order of Receiving {string} Đồng Tốt Free should be created in Child Order History")
    public void an_Order_of_Receiving_Đồng_Tốt_Free_should_be_created_in_Child_Order_History(String amount) {
        for(int i = 0; i < getChildReceiverTokens().size(); i++) {
            checkOrderHistory_Debit_ReceiveDongTot(getChildReceiverTokens().get(i)
                    , getChildReceiverAccountIDs().get(i)
                    , getBizAccountID()
                    , DEBIT_DONGTOT_TYPE_FREE
                    , Integer.parseInt(amount));
        }
    }

    @Given("I login my Biz Account with 20k Đồng Tốt Expiry which expires this month")
    public void i_login_my_Biz_Account_with_Đồng_Tốt_to_transfer_sdt_expiry_this_month() {
        registerBizAccount();
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);

        String thisMonth = getTomorrow_MMddyyyy();
        timestampDTExpiry_thisMonth = getTimestamp_fromDate_MMddyyyy(thisMonth);
        // Topup & expires today
        topupDongTotExpiry_20k(getBizAccountID(), getBizPhone(), thisMonth);

        try {
            verifyDTBalance_Expiry(getBizToken(), "20000");
        } catch (AssertionError e) {
            topupDongTotExpiry_20k(getBizAccountID(), getBizPhone(), thisMonth);
            verifyDTBalance_Expiry(getBizToken(), "20000");
        }

        // Set value for next steps
        setParentDTExpiry_ExpiredClosest(getDTBalance_Expiry_ExpiredClosest(getBizToken()));
    }

    @Given("I use my Biz Account to topup 20k Đồng Tốt Expiry which expires next month")
    public void i_login_my_Biz_Account_with_Đồng_Tốt_to_transfer_sdt_expiry_next() {
        int amountTotal = 0;
        int amountPerChild = 0;
        int amountPerOrder = 0;
        upgradeToBizAccountAsParent(getBizAccountID(), amountTotal, amountPerChild, amountPerOrder);

        String nextMonth = getToday_nextMonth_MMddyyyy();
        timestampDTExpiry_nextMonth = getTimestamp_fromDate_MMddyyyy(nextMonth);

        // Topup & expires today
        topupDongTotExpiry_20k(getBizAccountID(), getBizPhone(), nextMonth);

        try {
            verifyDTBalance_Expiry(getBizToken(), "20000");
        } catch (AssertionError e) {
            topupDongTotExpiry_20k(getBizAccountID(), getBizPhone(), nextMonth);
            verifyDTBalance_Expiry(getBizToken(), "20000");
        }
    }

//    @Then("I should receive {string} Đồng Tốt Expiry which expires this month")
//    public void i_should_receive_Đồng_Tốt_Expiry_which_expires_this_month(String amount) {
//        Assert.assertFalse(getChildReceiverTokens() == null);
//        int actualBalance = Integer.parseInt(getDTBalance_Expiry_ByExpiredDate(getChildToken(), timestampDTExpiry_thisMonth));
//        Assert.assertEquals("Total Free Amount of each child is incorrect", Integer.parseInt(amount), actualBalance);
//    }
//
//    @Then("I should receive {string} Đồng Tốt Expiry which expires next month")
//    public void i_should_receive_Đồng_Tốt_Expiry_which_expires_next_month(String amount) {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @When("I inactivate my Child Account")
    public void i_inactivate_my_Child_Account() {
        updateChildToInactive(getBizAccountID(), getChildAccountID());
    }

    @When("I expires my Child Account")
    public void i_expires_my_Child_Account() {
        updateChildToExpired(getBizAccountID(), getChildAccountID());
    }
}
