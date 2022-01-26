package projects.functions.chotot.hierarchy;

import com.vn.chotot.api.rest_assured.RestAssure;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.java.sl.In;
import io.restassured.response.Response;
import org.testng.Assert;
import projects.functions.APISupportFunctions;
import projects.functions.chotot.flashad.FlashAd_PTY_Functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static api.configuration.GatewayConfig.*;
import static api.utils.CheckAccessToken.setAccessTokenOfCP;
import static api.utils.GetAccessToken.getAccessTokenOfNewUser;
import static desktop.configuration.LoginConfig.tempUserPhone;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getAmountAfterPayDT;
import static projects.functions.chotot.payment.PayOrder_API_Functions.getAmountAfterPayDT4B;

public class AccountHierarchy_Functions extends APISupportFunctions {
    public AccountHierarchy_Functions() {
        setAccessTokenOfCP();
    }

    public static final String phoneChildShop = "0976222219";
    private static final SimpleDateFormat timeStampToRFC3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final String ADMIN_ACCOUNT_EMAIL = "admin-ds@chotot.vn";
    private static final int retryAccountHierarchy = 5;
    private static String bizToken = "", childToken = ""        //Main
            , bizAToken = "", bizBToken = "", childAToken = "", childBToken = "";
    private static String bizAccountID = "";       // Means Account ID will be parentAccountID
    private static String childID = ""      // Main
            , bizAID = "", bizBID = "", childAID = "", childBID = "", listChildAID;
    private static String bizPhone = "", childPhone = ""      // Main
            , bizAPhone = "", bizBPhone = "", childAPhone = "", childBPhone = "";
    private static String normalID = "", normalPhone = "", normalToken = "";
    private static String parentAccountID = "",     // BizAccountID after registered successfully
            childAccountID = "";
    private static String childBizID = "";

    private static String parentBizID = "";      // parent_id of Child  - VUHOANG DEBUG: UNKNOWN
    private static String parentBizID_OfParent = "";      // parent_id of Biz Parent     //VUHOANG DEBUG: must remove
    private static String childBudgetID = "", parentBudgetID = "";
    private static String childBudgetAmount = "", parentBudgetAmount = "";
    private static String childLimitPerTransaction = "";
    private static String childLimitBudgetAmount = "";
    private static String EXPIRED_DATE = getExpiredDate();
    private static int POSFeeOfChildPaid = -1;
    private static int amountAfterPayChild = 0;  //Will be replaced by value of amountAfterPayDT or amountAfterPayDT4B
    private static int amountAfterPayBiz = 0;  //Will be replaced by value of amountAfterPayDT or amountAfterPayDT4B

    // REGISTER VARIABLE INITIALIZATION
    private static String bizRegisterAccountID = "";
    private static String bizRegisterPhone = "";
    private static String bizRegisterToken = "";
    private static String bizRegisterRequestID = "";

    // Thuan Ly: Budget Statistic
    private static int totalChildUsedAmmount = 0;
    private static int sumAmountChild = 0;
    private String RFC3339From = "T00%3A00%3A00.000Z";
    private String RFC3339To = "T23%3A59%3A59.999Z";
    private static int temp_resetDate = 0;
    @Deprecated
    private static String totalBizCost = "", totalParentCost = "", totalChildCost = "", amountChildCost;
    private static String numberOfStaff = "20-50";      //1-10 , 11-20 , 20-50 , 50-
    private static String bizCompanyType = "hkdct";     //dkkd, hkdct, other
    private FlashAd_PTY_Functions flashAd_pty = new FlashAd_PTY_Functions();

    public static int getSumAmountChild() {
        return sumAmountChild;
    }

    public static void setSumAmountChild(int sumAmountChild) {
        AccountHierarchy_Functions.sumAmountChild = sumAmountChild;
    }

    public static String getAmountChildCost() {
        return amountChildCost;
    }

    public static void setAmountChildCost(String amountChildCost) {
        AccountHierarchy_Functions.amountChildCost = amountChildCost;
    }

    public static String getTotalBizCost() {
        return totalBizCost;
    }

    public static void setTotalBizCost(String totalBizCost) {
        AccountHierarchy_Functions.totalBizCost = totalBizCost;
    }

    public static String getTotalParentCost() {
        return totalParentCost;
    }

    public static void setTotalParentCost(String totalParentCost) {
        AccountHierarchy_Functions.totalParentCost = totalParentCost;
    }

    public static String getTotalChildCost() {
        return totalChildCost;
    }

    public static void setTotalChildCost(String totalChildCost) {
        AccountHierarchy_Functions.totalChildCost = totalChildCost;
    }
    // ----- End of Budget Statistic

    //Quang
    private static String oldParentBizID = "";

    public static String getOldParentBizID() {
        return oldParentBizID;
    }

    public static void setOldParentBizID(String oldParentBizID) {
        AccountHierarchy_Functions.oldParentBizID = oldParentBizID;
    }

    public static String getBizAccountID() {
        return bizAccountID;
    }

    public static void setBizAccountID(String bizAccID) {
        bizAccountID = bizAccID;
    }

    public static String getBizAID() {
        return bizAID;
    }

    public static String getBizBID() {
        return bizBID;
    }

    public static String getChildID() {
        return childID;
    }

    public static String getChildAID() {
        return childAID;
    }

    public static String getChildBID() {
        return childBID;
    }

    public static String getListChildAID() {
        return listChildAID;
    }

    public static void setListChildAID(String listChildAID) {
        AccountHierarchy_Functions.listChildAID = listChildAID;
    }

    public static String setBizAID(String bizIDInput) {
        return bizAID = bizIDInput;
    }

    public static String setBizBID(String bizIDInput) {
        return bizBID = bizIDInput;
    }

    public static String setChildID(String childIDInput) {
        return childID = childIDInput;
    }

    public static String setChildAID(String childIDInput) {
        return childAID = childIDInput;
    }

    public static String setChildBID(String childIDInput) {
        return childBID = childIDInput;
    }

    public static String setBizToken(String token) {
        return bizToken = token;
    }

    public static String setBizAToken(String token) {
        return bizAToken = token;
    }

    public static String setBizBToken(String token) {
        return bizBToken = token;
    }

    public static String setChildToken(String token) {
        return childToken = token;
    }

    public static String setChildPhone(String phone) {
        return childPhone = phone;
    }

    public static void setBizPhone(String bizPhone) {
        AccountHierarchy_Functions.bizPhone = bizPhone;
    }

    public static String setChildAToken(String token) {
        return childAToken = token;
    }

    public static String setChildBToken(String token) {
        return childBToken = token;
    }

    public static String setChildBizID(String token) {
        return childBizID = token;
    }

    public static String setChildAccountID(String accID) {
        return childAccountID = accID;
    }

    public static String setParentAccountID(String accID) {
        return parentAccountID = accID;
    }

    public static String getParentAccountID() {
        if (parentAccountID.isEmpty())
            parentAccountID = bizAccountID;
        return parentAccountID;
    }

    public static String getChildAccountID(String token) {
        return childAccountID;
    }

    public static String getChildAccountID() {
        return childAccountID;
    }

    public static String getBizPhone() {
        return bizPhone;
    }

    public static String getChildPhone() {
        return childPhone;
    }

    public static String getNormalID() {
        return normalID;
    }

    public static String getNormalPhone() {
        return normalPhone;
    }

    public static String getBizToken() {
        return bizToken;
    }

    public static String getBizAToken() {
        return bizAToken;
    }

    public static String getBizBToken() {
        return bizBToken;
    }

    public static String getChildToken() {
        return childToken;
    }

    public static String getChildAToken() {
        return childAToken;
    }

    public static String getChildBToken() {
        return childBToken;
    }


    public static String getNormalToken() {
        return normalToken;
    }

    public static int getAmountAfterPayChild() {
        return amountAfterPayChild;
    }

    public static void setAmountAfterPayChild(int amountAfterPayChild) {
        AccountHierarchy_Functions.amountAfterPayChild = amountAfterPayChild;
    }

    public static int getAmountAfterPayBiz() {
        return amountAfterPayBiz;
    }

    public static void setAmountAfterPayBiz(int amountAfterPayBiz) {
        AccountHierarchy_Functions.amountAfterPayBiz = amountAfterPayBiz;
    }

    public static void setAmountAfterPayBiz() {
        amountAfterPayBiz = getAmountAfterPayDT();
    }

    @Deprecated //MASLOW ACCOUNT MANAGEMENT - VUHOANG DEBUG: should be removed
    public static void setParentIDForBizParent(String parentID) {
        parentBizID_OfParent = parentID;
    }

    public static int[] getTotalChildUsedAmmount() {
        return new int[]{totalChildUsedAmmount};
    }

    public static void setTotalChildUsedAmmount(int totalChildUsedAmmount) {
        AccountHierarchy_Functions.totalChildUsedAmmount = totalChildUsedAmmount;
    }

    // REGISTER VARIABLE GETTER AND SETTER
    public static String getBizRegisterAccountID() {
        return bizRegisterAccountID;
    }

    public static void setBizRegisterAccountID(String bizRegisterAccID) {
        bizRegisterAccountID = bizRegisterAccID;
        setBizAccountID(bizRegisterAccountID);      // To combine Steps of Register with Steps of global AH
    }

    public static String getBizRegisterPhone() {
        return bizRegisterPhone;
    }

    public static void setBizRegisterPhone(String bizRegisterPhone) {
        AccountHierarchy_Functions.bizRegisterPhone = bizRegisterPhone;
    }

    public static String getBizRegisterRequestID() {
        return bizRegisterRequestID;
    }

    public static void setBizRegisterRequestID(String bizRegisterRequestID) {
        AccountHierarchy_Functions.bizRegisterRequestID = bizRegisterRequestID;
    }

    public static String getBizRegisterToken() {
        return bizRegisterToken;
    }

    public static void setBizRegisterToken(String bizRegisterToken) {
        AccountHierarchy_Functions.bizRegisterToken = bizRegisterToken;
    }

    //Functions
    public static void registerBizAccountWithBizBudget(int bizBudget) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccount(bizAccountID, bizBudget, 0, 0);
    }

    public static void registerBizAccount_SetNewChild() {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccount(bizAccountID, 0, 0, 0);  //VU REVIEW
    }

    public static void registerBizAccount_SetNewChild_Expired() {
        setExpireDateToBeExpired();
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccount(bizAccountID, 0, 0, 0);  //VU REVIEW
    }

    public static void extendBizAccount(String bizAccountID) {
        EXPIRED_DATE = getExpiredDate();    //Get after 1 month
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("status", "active");
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);
        response = RestAssure.instance().put(bodyJson, String.format(gatewayHierarchy_Biz_UpdateBudget, bizAccountID));
        debugResponse();
        verifyStatusCode200("Extend Biz Account.");
    }

    public static void upgrade2ParentAccount(String accountID) {
        upgradeToBizAccount(accountID, 0, 0, 0);
    }

    @Deprecated
    public static void registerBizAccountWithChildAndSetBudget(int bizBudget, int childBudget, int limitPerOrder) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        registerBizAccount_NoLink();
//        upgradeToBizAccount(bizID);
        upgradeToBizAccount(bizAccountID, bizBudget, childBudget, limitPerOrder); //VU REVIEW
    }

    public static void registerBizAccount_NewChild_SetBizBudget(int bizBudget, int childBudget, int limitPerOrder) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccountAsParent(bizAccountID, bizBudget, childBudget, limitPerOrder);   //Phai upgrade thanh Biz truoc khi link

        getAccessTokenOfNewUser();
        String childIDLocal = global_accountID;
        setChildToken(global_accessToken);

        upgradeToChildAccount(bizAccountID, childIDLocal, 0, 0, 0);
    }

    public static void registerBizAccount_NewChild_SetChildBudget(int bizBudget, int childBudget, int limitPerOrder) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccountAsParent(bizAccountID, 0, 0, 0);   //Phai upgrade thanh Biz truoc khi link

        getAccessTokenOfNewUser();
        String childIDLocal = global_accountID;
        setChildToken(global_accessToken);

        upgradeToChildAccount(bizAccountID, childIDLocal, bizBudget, childBudget, limitPerOrder);
    }

    public static void registerBizAccount_NewChild_SetBizChildBudget(int bizBudget, int childBudget, int limitPerOrder) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccountAsParent(bizAccountID, bizBudget, childBudget, limitPerOrder);   //Phai upgrade thanh Biz truoc khi link

        getAccessTokenOfNewUser();
        String childIDLocal = global_accountID;
        setChildToken(global_accessToken);

        upgradeToChildAccount(bizAccountID, childIDLocal, bizBudget, childBudget, limitPerOrder);
    }

    public static void upgrade2BizAccount() {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        upgradeToBizAccount_NoSetBudget(bizAccountID);
        parentAccountID = bizAccountID;
    }

    public static void upgrade2BizAccount(String accountID) {
        upgradeToBizAccountAsParent(accountID, 0, 0, 0);
    }

    public static void registerBizAccount_NoLink() {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;

        //Add more function upgrade to BizParentAccount without link
        upgradeToBizAccountAsParent(bizAccountID, 0, 0, 0);
    }

    public static void registerParentAccount_NoChild() {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;

        //Add more function upgrade to BizParentAccount without link
        upgradeToBizAccountAsParent(bizAccountID, 0, 0, 0);
    }

    public static void registerBizAccountA_NoLink() {
        bizAToken = getAccessTokenOfNewUser();
        bizAPhone = tempUserPhone;
        bizAID = global_accountID;
        //Add more function upgrade to BizParentAccount without link
        upgradeToBizAccountAsParent(bizAID, 0, 0, 0);
    }

    public static void registerBizAccountB_NoLink() {
        bizBToken = getAccessTokenOfNewUser();
        bizBPhone = tempUserPhone;
        bizBID = global_accountID;
        //Add more function upgrade to BizParentAccount without link
        upgradeToBizAccountAsParent(bizBID, 0, 0, 0);
    }

    public static void registerChildAccount_NoLink() {
        childToken = getAccessTokenOfNewUser();
        childPhone = tempUserPhone;
        childID = global_accountID;
    }

    public static void registerChildAccountA_NoLink() {
        childAToken = getAccessTokenOfNewUser();
        childAPhone = tempUserPhone;
        childAID = global_accountID;
    }

    public static void registerChildAccountB_NoLink() {
        childBToken = getAccessTokenOfNewUser();
        childBPhone = tempUserPhone;
        childBID = global_accountID;
    }

    public static String registerChildAccount(String bizAccountID) {
        childID = registerChildAccount(bizAccountID, 0, 0, 0);
        return childID;
    }

    public static String registerChildAccount(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        childToken = getAccessTokenOfNewUser();
        childPhone = tempUserPhone;
        childID = global_accountID;
        upgradeToChildAccount(bizAccountID, childID, bizBudget, childBudget, limitPerOrder);
        return childID;
    }

    public static String registerChildAccountA(String bizAccountID) {
        childID = registerChildAccountA(bizAccountID, 0, 0, 0);
        return childAID;
    }

    public static String registerChildAccountA(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        childAToken = getAccessTokenOfNewUser();
        childAPhone = tempUserPhone;
        childAID = global_accountID;
        upgradeToChildAccount(bizAccountID, childAID, bizBudget, childBudget, limitPerOrder);
        return childAID;
    }

    public static String registerChildAccountB(String bizAccountID) {
        childBID = registerChildAccountB(bizAccountID, 0, 0, 0);
        return childBID;
    }

    public static String registerChildAccountB(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        childBToken = getAccessTokenOfNewUser();
        childBPhone = tempUserPhone;
        childBID = global_accountID;
        upgradeToChildAccount(bizAccountID, childBID, bizBudget, childBudget, limitPerOrder);
        return childBID;
    }

    public static String registerChildAccount_NotLink(String bizAccountID) {
        childToken = getAccessTokenOfNewUser();
        childPhone = tempUserPhone;
        childID = global_accountID;
        return childID;
    }

    public static String registerChildAccount_NotLink() {
        childToken = getAccessTokenOfNewUser();
        childPhone = tempUserPhone;
        childID = global_accountID;
        return childID;
    }

    public static void registerNormalAccount() {
        normalToken = getAccessTokenOfNewUser();
        normalPhone = tempUserPhone;
        normalID = global_accountID;
    }

    public static void upgradeToBizAccount(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        upgradeToBizAccountAsParent(bizAccountID, bizBudget, childBudget, limitPerOrder);   //Phai upgrade thanh Biz truoc khi link

        //Create a new child
        getAccessTokenOfNewUser();
        setChildToken(global_accessToken);
        setChildPhone(tempUserPhone);
        upgradeToChildAccount(bizAccountID, global_accountID, bizBudget, childBudget, limitPerOrder);
    }


    //Link child to Biz
    public static void linkChildToBiz(String bizAccountID, String childAccountID) {
        upgradeToChildAccount(bizAccountID, childAccountID, 0, 0, 0, "200");
    }

    public static void linkChildToBiz(String bizAccountID, String childAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        upgradeToChildAccount(bizAccountID, childAccountID, bizBudget, childBudget, limitPerOrder, "200");
    }

    public static void linkChildToBizFailed(String bizAccountID, String childAccountID) {
        linkChildToBizFailed(bizAccountID, childAccountID, 0, 0, 0);
    }

    public static void linkChildToBizFailed(String bizAccountID, String childAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        upgradeToChildAccount(bizAccountID, childAccountID, bizBudget, childBudget, limitPerOrder, "500");
    }

    public static void linkMyChildToMyBizFailed(String bizAccountID, String childAccountID) {
        linkMyChildToMyBizFailed(bizAccountID, childAccountID, 0, 0, 0);
    }

    public static void linkMyChildToMyBizFailed(String bizAccountID, String childAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        upgradeToChildAccount(bizAccountID, childAccountID, bizBudget, childBudget, limitPerOrder, "400");
    }

    public static void upgradeToChildAccount(String bizAccountID, String childAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        upgradeToChildAccount(bizAccountID, childAccountID, bizBudget, childBudget, limitPerOrder, "200");
    }

    public static void upgradeToBizAccountAsParent(String bizAccountID) {
        upgradeToBizAccountAsParent(bizAccountID, 0, 0, 0, "200");
    }

    public static void upgradeToBizAccount_NoSetBudget(String bizAccountID) {
        upgradeToBizAccountAsParent(bizAccountID, 0, 0, 0, "200");
    }

    public static void upgradeToBizAccountAsParent(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        upgradeToBizAccountAsParent(bizAccountID, bizBudget, childBudget, limitPerOrder, "200");
    }

    private static void upgradeToBizAccountAsParent(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder, String responseCode) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("parent_id", 0);       //0 is default for Biz Parent without any child
        bodyJson.addProperty("status", "active");
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("total_amount", bizBudget);
        bodyJson.addProperty("child_limit_per_month", childBudget);
        bodyJson.addProperty("amount_per_order", limitPerOrder);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = post(bodyJson, gatewayHierarchy_BizChild_Link);
                verifyStatusCode200("Upgrade To Biz Account.");
                break;
            } catch (AssertionError e) {
                waitConstant(2);
            }
        }

        verifyStatusCode200("MASLOW", "LINK CHILD TO BIZ");

        //Verify bizAccountID is already Business Account
        verifyIsParentAccount(bizAccountID);

        //Set internal variables
        setParentAccountID(bizAccountID);    //After link Child, bizAccountID becomes parentAccountID. For new code
        setParentIDForBizParent("0");       //UNKNOWN
    }

    public static void registerAndLinkNewChildAccount(String responseCode) {
        childAToken = getAccessTokenOfNewUser();
        childAPhone = tempUserPhone;
        childAID = global_accountID;
        upgradeToChildAccount(getParentAccountID(), childAID, 0, 0, 0, responseCode);
    }

    //bizAccountID is parent ID
    public static void upgradeToChildAccount(String bizAccountID, String childAccID, int bizBudget, int childBudget, int limitPerOrder, String responseCode) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(childAccID));
        bodyJson.addProperty("parent_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("status", "active");
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("total_amount", bizBudget);
        bodyJson.addProperty("child_limit_per_month", childBudget);
        bodyJson.addProperty("amount_per_order", limitPerOrder);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        for (int i = 0; i < 5; i++) {
            try {
                response = post(bodyJson, gatewayHierarchy_BizChild_Link);
                verifyStatusCode(response, "MASLOW-UPGRADE_TO_CHILD", gatewayHierarchy_BizChild_Link, responseCode);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        verifyStatusCode(response, "MASLOW-UPGRADE_TO_CHILD", gatewayHierarchy_BizChild_Link, responseCode);

        if (Integer.parseInt(responseCode) < 300)
            verifyChildLinkedToBiz(bizAccountID, childAccID);

        setParentAccountID(bizAccountID);       //After link Child, bizAccountID becomes parentAccountID. For new code
        setChildAccountID(childAccID);
        setChildID(childAccID);
        setBizChildID(childAccID);
        setChildPhone(childPhone);
    }

    public static void checkBizLinkedToChild(String bizAccountID, String childAccountID) {
        verifyChildLinkedToBiz(bizAccountID, childAccountID);
    }

    // ------ Verify information of Parent Account ------
    public static void verifyIsParentAccount(String bizAccountID) {
        verifyIsParentAccount(bizAccountID, "active", "200");
    }

    public static void verifyIsNotParentAccount(String bizAccountID) {
        verifyIsParentAccount(bizAccountID, "active", "404");
    }

    private static void verifyIsParentAccount(String bizAccountID, String status, String responseCode) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(String.format(gatewayHierarchy_BizChild_GetBizID, bizAccountID));
                verifyStatusCode(response, "MASLOW-CHECK-IS-PARENT", gatewayHierarchy_BizChild_GetBizID, responseCode);
                break;
            } catch (AssertionError e) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode(response, "MASLOW-CHECK-IS-PARENT", gatewayHierarchy_BizChild_GetBizID, responseCode);

        // Verify all conditions to become a BIZ ACCOUNT
        if (responseCode.startsWith("2")) {
            // A BizID is created when an account is upgraded to ParentAccount successfully
            Assert.assertNotNull(getResponseData("$.id"));

            // Check same account
            String actualAccountID = getResponseData("$.account_id");
            Assert.assertEquals(actualAccountID, bizAccountID,
                    "API CHECK IS PARENT -> RESPONSES INCORRECT DATA: Check AccID[" + bizAccountID + "] but responses [" + actualAccountID + "].");

            // Parent account shouldn't link to another account
            String actualParentID = getResponseData("$.parent_id");    // Parent ID of this parent account
            Assert.assertEquals(actualParentID, "0"
                    , "ACCOUNT PARENT[" + bizAccountID + "] IS LINKED TO ANOTHER PARENT[" + actualParentID + "]");

            // Check status
            Assert.assertEquals(getResponseData("$.status"), status,
                    "ACCOUNT PARENT[" + bizAccountID + "] IS NOT " + status.toUpperCase());

            // $.total_childs
            // $.total_paid_orders
        }
    }

    public static void verifyIsChildAccount(String ownerToken) {
        verifyIsBizAccount(ownerToken, false);
    }

    @Deprecated
    public static void verifyIsBizAccount(String ownerToken, boolean isParentAccount) {
        response = get(ownerToken, String.format(gatewayHierarchy_BizChild_ChildCheck));
        verifyStatusCode200("Verify Is Biz Account");
        debugResponse(response);
        query = String.format("$.is_biz_account");
        String isBizAccountActual = getResponseData(response, query);
        Assert.assertEquals(isBizAccountActual, "true");

        query = String.format("$.parent_id");
        String Actual_parent_id = getResponseData(response, query);
        if (isParentAccount) {
            Assert.assertNull(Actual_parent_id);
        } else {
            Assert.assertNotNull(Actual_parent_id);
        }
    }

    public static void verifyIsBizAccountExpired(String bizToken) {
        response = get(bizToken, String.format(gatewayHierarchy_BizChild_ChildCheck));
        verifyStatusCode200("Verify Is Biz Account");
        debugResponse(response);
        query = String.format("$.is_biz_account");
        Assert.assertEquals(getResponseData(response, query), "false", "BIZ ACCOUNT " + bizAccountID + "IS NOT EXPIRED YET");
    }

    public static void verifyIsNormalAccount(String ownerToken) {
        response = get(ownerToken, String.format(gatewayHierarchy_BizChild_ChildCheck));
        verifyStatusCode200("Verify Is Normal Account");
        debugResponse(response);
        query = String.format("$.is_biz_account");
        Assert.assertFalse(Boolean.parseBoolean(getResponseData(response, query)),
                "Check Unlink & become normal account FAILED. Child Account is still linked to Biz Account.");

        query = String.format("$.error");
        Assert.assertNotNull(getResponseData(response, query), "Check Unklink & become normal account FAILED. Error doesn't response");
    }

    public static void verifyChildLinkedToBiz(String bizAccountID, String childAccountID) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        debugResponse();
        verifyStatusCode200("Verify Child is Linked to Biz");
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'active')].parent_id", Integer.parseInt(childAccountID));
        String actualBizID = getResponseData(response, query);
        Assert.assertEquals(actualBizID, bizAccountID, String.format("Child[%s] isn't linked to Biz[%s].", childAccountID, bizAccountID));
    }

    public static void verifyChildUnLinkedToBiz(String bizAccountID, String childAccountID) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        debugResponse();
        verifyStatusCode200("Verify Child is Unlinked From Biz");
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'deleted')].parent_id", Integer.parseInt(childAccountID));
        String actualBizID = getResponseData(response, query);
        Assert.assertEquals(actualBizID, bizAccountID, String.format("Child[%s] isn't linked to Biz[%s].", childAccountID, bizAccountID));
    }

    public static void verifyNumberOfActiveChild(String bizAccountID, int numberOfActiveChild) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        verifyStatusCode200("Verify Number of Active Child Account is correct");
        query = "$.childs[?(@.status == 'active')]";
        List<String> actualBizID = getResponseDataList(response, query);
        Assert.assertEquals(actualBizID.size(), numberOfActiveChild,
                "Number of Active Child is " + actualBizID.size() + " BUT expect " + numberOfActiveChild + ".\n");
    }

    public static void verifyNumberOfDeletedChild(String bizAccountID, int numberOfActiveChild) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        verifyStatusCode200("Verify Number of Deleted Child Account is correct.");
        query = "$.childs[?(@.status == 'deleted')]";
        List<String> actualBizID = getResponseDataList(response, query);
        Assert.assertEquals(actualBizID.size(), numberOfActiveChild,
                "Number of Active Child is " + actualBizID.size() + " BUT expect " + numberOfActiveChild + ".\n");
    }

    public static void verifyChildIsUnlinked(String bizAccountID, String childAccID) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        verifyStatusCode200("Verify Child is Unlinked");
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'deleted')].parent_id", Integer.parseInt(childAccID));
        String actualParentID = getResponseData(response, query);
        Assert.assertEquals(actualParentID, bizAccountID, "Child " + childAccID + " isn't deteled & unlinked.");

        //Check duplicate json. If an account is deleted, it shouldn't be active
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'active')].parent_id", Integer.parseInt(childAccID));
        actualParentID = getResponseData(response, query);
        Assert.assertTrue(actualParentID.isEmpty(), "Child " + childAccID + " isn't deteled & unlinked.");
    }

    public static void verifyChildIsLinked(String bizAccountID, String childAccID) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        verifyStatusCode200("Verify Child is Linked");
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'active')].parent_id", Integer.parseInt(childAccID));
        String actualParentID = getResponseData(response, query);
        Assert.assertEquals(actualParentID, bizAccountID, "Child " + childAccID + " isn't linked.");

        //Check duplicate json. If an account is active, it shouldn't be deleted
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'deleted')].parent_id", Integer.parseInt(childAccID));
        actualParentID = getResponseData(response, query);
        Assert.assertTrue(actualParentID.isEmpty(), "Child " + childAccID + " isn't linked.");
    }

    public static void checkBizLinkedToChildFailed(String bizAccountID, String childAccountID) {
        apiURL = String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID);
        response = get(apiURL);
        verifyStatusCode200("Verify Link of Biz & Child is failed: " + apiURL);
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'active')].parent_id", Integer.parseInt(childAccountID));
        String actualBizID = getResponseData(response, query);
        Assert.assertNull(actualBizID, String.format("Fake Child[%s] is linked to Biz[%s].", childAccountID, bizAccountID));
    }

    public static void unlinkParentToChild(String parentID, String childID) {
        apiURL = String.format(gatewayHierarchy_BizChild_Unlink, parentID, childID);
        response = delete(apiURL);
        verifyStatusCode200("Unlink Parent out of Child: " + apiURL);
        query = "$.is_success";
        boolean success = Boolean.parseBoolean(getResponseData(response, query));
        Assert.assertTrue(success, "API unlink doesn't work");

        query = "$.affected";
        int updatedRecordInDB = Integer.parseInt(getResponseData(response, query));
        Assert.assertEquals(updatedRecordInDB, 1, "API unlink updated more than 1 record in DB");
    }

    //This function is to create a child successfully
    public static void registerChildAccountLinkedToNewBizAccount() {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;

        upgradeToBizAccount(bizAccountID, 0, 0, 0);

        if (childID.isEmpty()) {
            childToken = getAccessTokenOfNewUser();
            childID = global_accountID;
        }

        upgradeToChildAccount(bizAccountID, childID, 0, 0, 0);
    }

    public static void loginChildAccountLinkedToBizAccount() {
        childToken = getAccessTokenOfNewUser();
        childID = global_accountID;
        setChildPhone(tempUserPhone);
        Assert.assertNotNull(bizAccountID, "Not yet register a new Biz Account");
        upgradeToChildAccount(bizAccountID, childID, 0, 0, 0);
    }

    //Verify Function
    public static void checkTopupBalanceDT(String ownerToken, int expectedMoney) {
        checkTopupBalanceDT4B(ownerToken, expectedMoney);
    }

    public static void checkTopupBalanceDT4B(String ownerToken, int expectedMoney) {
        int i = 0;
        int actualMoney = -1;
        while (i < retryAccountHierarchy) {
            response = get(ownerToken, gatewayCredit_CheckBalance);
            verifyStatusCode200("Check Topup Balance DT4B, at try " + (i + 1) + " : " + gatewayCredit_CheckBalance);
            debugResponse();
            try {
                //Get the last pay
                List<String> topupMomoList = getResponseDataList(response, "$[*]");
                int lastPayIndex = topupMomoList.size() - 1;

                //Check status is paid
                query = "$[" + lastPayIndex + "].type";
                String type = getResponseData(response, query);
                Assert.assertEquals(type, "paid", "Type of momo topup isn't 'paid'");
                //Check balance

                query = "$[" + lastPayIndex + "].balance";
                actualMoney = getResponseDataInt(response, query);
                Assert.assertEquals(actualMoney, expectedMoney, "Money is deposited wrong. Actual [" + actualMoney + "]");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(4);
            }
            i++;
        }
        Assert.assertEquals(actualMoney, expectedMoney, "Money is deposited wrong. Actual [" + actualMoney + "]");
    }

    public static void checkTopupBalanceDTFree(String ownerToken, int expectedMoney) {
        int i = 0;
        int actualMoney = -1;
        while (i < retryAccountHierarchy) {
            response = get(ownerToken, gatewayCredit_CheckBalance);
            verifyStatusCode200("Check Topup Balance DT FREE, at try " + (i + 1) + " : " + gatewayCredit_CheckBalance);
            debugResponse();
            try {
                //Get the last pay
                List<String> topupMomoList = getResponseDataList(response, "$[*]");
                int lastPayIndex = topupMomoList.size() - 1;

                //Check status is paid
                query = "$[" + lastPayIndex + "].type";
                String type = getResponseData(response, query);
                Assert.assertEquals(type, "free", "Type of DT FREE topup isn't 'free'");
                //Check balance

                query = "$[" + lastPayIndex + "].balance";
                actualMoney = getResponseDataInt(response, query);
                Assert.assertEquals(actualMoney, expectedMoney, "Money is deposited wrong. Actual [" + actualMoney + "]");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(4);
            }
            i++;
        }
        Assert.assertEquals(actualMoney, expectedMoney, "Money is deposited DT FREE wrong. Actual [" + actualMoney + "]");
    }

    public static void checkTopupBalanceDTFreeExpire(String ownerToken, int expireMonth) {
        int i = 0;
        long expireStamp = -1, expireStampExpected = -1, lastPayIndex = -1;
        while (i < retryAccountHierarchy) {
            response = get(ownerToken, gatewayCredit_CheckBalance);
            verifyStatusCode200("Check Topup Balance DT4B, at try " + (i + 1) + " : " + gatewayCredit_CheckBalance);
            debugResponse();
            try {
                //Get the last pay
                List<String> topupMomoList = getResponseDataList(response, "$[*]");
                lastPayIndex = topupMomoList.size() - 1;

                //Check status is paid
                query = "$[" + lastPayIndex + "].type";
                String type = getResponseData(response, query);
                Assert.assertEquals(type, "paid", "Type of momo topup isn't 'paid'");

                //Check balance
                break;
            } catch (AssertionError assertionError) {
                waitConstant(4);
            }
            i++;
        }

        query = "$[" + lastPayIndex + "].expired";
        expireStamp = getResponseDataLong(response, query);

        //Convert expireInBody to Date
        Calendar calExpireActual = Calendar.getInstance();
        calExpireActual.setTime(new Date(expireStamp));
        int dayActual = calExpireActual.get(Calendar.DAY_OF_MONTH);
        int monthActual = calExpireActual.get(Calendar.MONTH);
        int yearActual = calExpireActual.get(Calendar.YEAR);


        Calendar calExpireExpect = Calendar.getInstance();
        int dayExpected = calExpireExpect.get(Calendar.DAY_OF_MONTH);
        int monthExpected = calExpireActual.get(Calendar.MONTH);
        int yearExpected = calExpireActual.get(Calendar.YEAR);


        boolean isPassed = true;
        String failedMsg = "";
        try {
            Assert.assertEquals(dayActual, dayExpected, "Day of Dong Tot Free is different");
        } catch (AssertionError e) {
            failedMsg += "\nDay of Dong Tot Free is different";
            isPassed = false;
        }

        try {
            Assert.assertEquals(monthActual, monthExpected + expireMonth, "Month of Dong Tot Free is different");
        } catch (AssertionError e) {
            failedMsg += "\nMonth of Dong Tot Free is different";
            isPassed = false;
        }

        try {
            Assert.assertEquals(yearActual, yearExpected, "Year of Dong Tot Free is different");
        } catch (AssertionError e) {
            failedMsg += "\nYear of Dong Tot Free is different";
            isPassed = false;
        }

        Assert.assertTrue(isPassed, failedMsg);
    }


    public static void checkTopupBalanceDT4B_Deny(String notOwnerToken, String ownerID) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(notOwnerToken, gatewayCredit_CheckBalance + "/" + ownerID);
                verifyStatusCode404("Verify Topup Balance Dt4B is denied.");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }
        verifyStatusCode404("Verify Topup Balance Dt4B is denied.");
    }

    public static void checkTopupHistoryDT4B(int expectedMoney) {
        checkTopupHistoryDT4B(bizToken, expectedMoney);
    }

    public static void checkTopupHistoryDT4B(String ownerToken, int expectedMoney) {
        response = get(ownerToken, gatewayCredit_CheckHistory);
        verifyStatusCode200("Check Topup History DT4B");

        //Get the last pay
        List<String> topupHistory = getResponseDataList(response, "$[*]");
        int lastHistoryIndex = topupHistory.size() - 1;

        //Check money of history
        query = "$[" + lastHistoryIndex + "].amount";
        int actualMoney = getResponseDataInt(response, query);
        Assert.assertEquals(actualMoney, expectedMoney, "Money of History is different from Money of Topup. History: " + actualMoney);

        //Check Condition
        query = "$[" + lastHistoryIndex + "].order_status";
        Assert.assertEquals(getResponseData(response, query), "success", "Order popup is not successful");

        query = "$[" + lastHistoryIndex + "].order_type";
        Assert.assertEquals(getResponseData(response, query), "topup", "Order type is not topup");
    }

    @Deprecated
    public static void checkOrderHistory_POSOrder_AdAccept(String childToken, int amount) {
        checkOrderHistory_POSOrder_AdAccept(childToken, getChildID(), amount, "200");
    }

    public static void checkOrderHistory_POSOrder_AdAccept(String childToken, String childID, int amount) {
        checkOrderHistory_POSOrder_AdAccept(childToken, childID, amount, "200");
    }

    public static void checkOrderHistory_POSOrder_AdAccept(String childToken, String childID, int amount, String responseCode) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(childToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode(response, "PAPI-CHILD_HISTORY", gatewayOrderHistory_ListPaid, responseCode);
                query = "$[?(@.account_id == %s && @.order_type == 'normal' && @.payment_method == 'credit_biz')].order_value";
                query = String.format(query, childID);
                Assert.assertNotNull(getResponseData(response, query), "Child Order doesn't display in Child History: Can't get response");
                actualOrderValue = Integer.parseInt(getResponseData(response, query));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(7);
                j++;
            }
        }
        verifyStatusCode(response, "PAPI-CHILD_HISTORY", gatewayOrderHistory_ListPaid, responseCode);
        Assert.assertEquals(actualOrderValue, amount, "POS order is paid but doesn't display in Order history");
    }

    public static void checkOrderHistory_POSOrderOfBiz_AdAccept(String bizToken, String bizAccountID, int amount, String responseCode) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(bizToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode(response, "PAPI", gatewayOrderHistory_ListPaid, responseCode);
                query = "$[?(@.account_id == %s && @.order_type == 'normal' && @.payment_method == 'credit')].order_value";
                query = String.format(query, bizAccountID);
                String actualValueStr = getResponseData(response, query);
                Assert.assertNotNull(actualValueStr, "Child Order doesn't display in Child History: Can't get response");
                Assert.assertFalse(actualValueStr.isEmpty(), "Child Order doesn't display in Child History: Can't get response");
                actualOrderValue = Integer.parseInt(actualValueStr);
                break;
            } catch (AssertionError assertionError) {
                waitConstant(7);
                j++;
            }
        }
        Assert.assertEquals(actualOrderValue, amount, "POS order is paid but doesn't display in Order history");
    }

    public static void checkOrderHistory_ListingFee_AdAccept(String ownerToken, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                debugResponse(response);
                verifyStatusCode200("Check OrderHistory: " + gatewayOrderHistory_ListPaid);
                query = "$[?(@.order_type == 'normal' && @.order_status == 'paid')].order_value";

                actualOrderValue = Integer.parseInt(Objects.requireNonNull(getResponseData(response, query)));

                //Check order value is displayed in Order history
                Assert.assertTrue(actualOrderValue > 0);
                Assert.assertEquals(actualOrderValue, amount, "POS order is paid but doesn't display in Order history");

                break;
            } catch (AssertionError | Exception assertionError) {
                waitConstant(5);
                j++;
            }
        }
        Assert.assertEquals(actualOrderValue, amount, "POS order is paid but doesn't display in Order history");
    }

    //Check child can't see other Child's order
    public static void checkOrderHistory_POSOrder_NotExistChildOrder(String ownerToken, String childID, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                debugResponse(response);
                verifyStatusCode200("Check OrderHistory doesn't exit Child order");
                query = "$[?(@.order_type == 'normal' " +
                        "&& @.order_status == 'closed' " +
                        "&& @.payment_method == 'credit' " +
                        "&& @.account_id == " + Integer.parseInt(childID) + ")]" +
                        ".order_value";
                Assert.assertTrue(getResponseData(response, query).isEmpty());
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
                j++;
            }
        }
        Assert.assertNotEquals(actualOrderValue, amount, "Child Accounts can see other's POS Order History");
    }

    //Check biz Order doesn't exist in Child Order History
    public static void checkOrderHistory_POSOrder_NotExistBizOrder(String ownerToken, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode200("Check Order History doesn't exist Biz Order");
                query = "$[?(@.order_type == 'normal' && @.order_status == 'paid' && @.payment_method == 'credit')].order_value";
                Assert.assertTrue(getResponseData(response, query).isEmpty());
                actualOrderValue = Integer.parseInt(getResponseData(response, query));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(7);
                j++;
            }
        }
        Assert.assertNotEquals(actualOrderValue, amount, "Child Accounts can see Biz's POS Order History");
    }

    @Deprecated
    public static void checkOrderHistory_POSOrder_AdInProcess(String ownerToken, int amount) {
        response = get(ownerToken, gatewayOrderHistory_ListPaid);
        verifyStatusCode200("Check Order History of POS order");
        query = "$[?(@.order_type == 'normal' && @.order_status == 'paid' && @.payment_method == 'credit')].order_value";       //VUHOANG DEBUG
        List<String> orderHistoryValues = getResponseDataList(response, query);
        int paidMoney = Integer.parseInt(orderHistoryValues.get(orderHistoryValues.size() - 1));
        Assert.assertEquals(paidMoney, amount, "POS order is paid but doesn't display in Order history");
    }


    //Check topup in Order History
    public static void checkOrderHistory_TopupOrder(String ownerToken, int amount) {
        int actualOrderValue = -1;
        int j = 0;
        while (j < retryAccountHierarchy) {
            try {
                waitConstant(7);
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                debugResponse(response);
                verifyStatusCode200("Check Order history of Topup Order");
                query = "$[?(@.order_type == 'topup' && @.order_status == 'paid' && @.payment_method == 'momo_app')].order_value";
                Assert.assertFalse(getResponseData(response, query).isEmpty());
                actualOrderValue = Integer.parseInt(getResponseData(response, query));
                break;
            } catch (AssertionError assertionError) {
                j++;
            }
        }

        Assert.assertEquals(actualOrderValue, amount, "POS order is paid but doesn't display in Order history");
    }

    public static void checkBizOrderHistory_POSOrderOfChild(String bizToken, String childID, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(bizToken, gatewayHierarchy_BizOrderHistory);
                verifyStatusCode200("Check OrderHistory of Child's POS order");
                query = "$.orders[?(@.account_id == %s " +
                        "&& @.payment_method == 'credit_biz' " +
                        "&& @.order_type == 'normal' " +
                        "&& @.unit == 'credit')]" +
                        ".order_value";
                query = String.format(query, childID);
                debugResponse();
                debugQuery();
                Assert.assertFalse(getResponseData(response, query).isEmpty(), "Pay Order of Child doesn't display in Biz Order History");
                actualOrderValue = Integer.parseInt(getResponseData(response, query));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(7);
                j++;
            }
        }
        Assert.assertEquals(actualOrderValue, amount, "Pay Order of Child doesn't display in Biz Order History");
    }

    public static void checkChildLinkedToBiz(String childToken, String bizAccountID) {
        response = get(childToken, gatewayHierarchy_BizChild_ChildCheck);
        verifyStatusCode200("Check Child is linked to Biz");
        query = "$.is_biz_account";     //VUHOANG DEBUG: is_biz_account  --> linked_to_biz_account
        Assert.assertEquals(getResponseData(response, query), "true", "Child Account isn't linked");

        //query = "$.parent_id";
        //Assert.assertEquals(getResponseDataInt(response, query), Integer.parseInt(bizID), "Biz Account is not [" + bizAccountID + "]");

        query = "$.is_parent";
        Assert.assertEquals(getResponseData(response, query), "false", "This is not Child Account ");

    }

    public static void checkBizContainsChilds(String bizAccountID, List<String> childAccountIDs) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        verifyStatusCode200("Check Biz contains Childs");

        boolean TSPassed = true;
        List<String> errorList = new ArrayList<>();

        for (String childAccountID : childAccountIDs) {
            query = String.format("$.childs[?(@.account_id == %d && @.status == 'active')].parent_id", Integer.parseInt(childAccountID));
            String actualBizID = getResponseData(response, query);
            if (!actualBizID.equalsIgnoreCase(bizAccountID)) {
                if (TSPassed)
                    TSPassed = false;
                errorList.add(String.format("Child[%s] isn't linked to Biz[%s]. \n", childAccountID, bizAccountID));
            }
        }
        Assert.assertTrue(TSPassed, "ERROR: " + errorList);
    }

    public static void checkNumberOfChilds(String bizAccountID, int numberOfChild) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        debugResponse();
        verifyStatusCode200("Check number of Childs is correct");
        query = "$.childs[*].parent_id";
        List<String> listOfChild = getResponseDataList(response, query);
        Assert.assertEquals(listOfChild.size(), numberOfChild, "Number of Childs that are linked to Biz [" + bizAccountID + "] is wrong: " + listOfChild.size());
    }

    @Deprecated
    public static void checkOrderHistory_Biz_POSOrder_Refund(String ownerToken, int amount) {
        int actualOrderValue = -1;
        query = "$[?(@.payment_method == 'compensation')].order_value";
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
                Assert.assertNotNull(getResponseData(response, query));
                Assert.assertFalse(getResponseData(response, query).isEmpty());
                break;
            } catch (AssertionError assertionError) {
                waitConstant(5);
            }
        }
        debugResponse();
        Assert.assertFalse(getResponseData(response, query).isEmpty());
        actualOrderValue = Integer.parseInt(getResponseData(response, query));
        Assert.assertEquals(actualOrderValue, amount, "POS order is paid but doesn't display in Order history");
    }

    public static void checkBizOrderHistory(String bizOwnerToken, String childAccountID, int amount) {
        query = "$.orders[*][?(@.account_id == " + childAccountID + " && @.payment_method == 'credit_biz')].credit_order.amount";
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(bizOwnerToken, gatewayHierarchy_OrderHistoryOfBiz);
                verifyStatusCode200("ORDER HISTORY BIZ", gatewayHierarchy_OrderHistoryOfBiz);
                Assert.assertTrue(getResponseDataList(query).size() > 0);
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("ORDER HISTORY BIZ", gatewayHierarchy_OrderHistoryOfBiz);
        Assert.assertTrue(getResponseDataList("$.orders[*]").size() > 0, "PAY DT4B DOESN'T WRITE INTO ORDER HISTORY");
        Assert.assertTrue(getResponseDataList(query).size() > 0);
        int actualAmount = Objects.requireNonNull(getResponseDataListInt(query)).get(0);
        Assert.assertEquals(amount, actualAmount, "PAYMENT OF CHILD IS INCORRECT IN BIZ ORDER HISTORY");
    }


    //Hien tai order of Biz ko hien ben cha
    public static void checkOrderHistory_Child_BizPOSOrder_Refund(String ownerToken, String bizAccountID, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                debugResponse(response);
                verifyStatusCode200("Check OrderHistory of Biz's POS Order refunds");
                query = "$[?(@.order_type == 'topup' " +
                        "&& @.order_status == 'paid' " +
                        "&& @.payment_method == 'compensation')]" +
                        "&& @.account_id == " + bizAccountID + "" +
                        ".order_value";     //VUHOANG DEBUG: WRONG
                Assert.assertNotNull(getResponseData(response, query));
                int refund = Integer.parseInt(getResponseData(response, query));
                System.out.println("Refund: " + refund);
                actualOrderValue = refund;
                break;
            } catch (AssertionError assertionError) {
                waitConstant(7);
                j++;
            }
        }
        Assert.assertEquals(actualOrderValue, amount, "POS order of Biz doesn't display in Child Order history");
    }

    public static void checkOrderHistory_Biz_ChildPOSOrder_Refund(String ownerToken, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                debugResponse(response);
                verifyStatusCode200("Check OrderHistory of Child's POS Order refund in BIZ");
                query = "$[?(@.order_type == 'topup' " +
//                        "&& @.order_status == 'paid' " +
                        "&& @.payment_method == 'compensation')]" +
                        ".order_value";
                Assert.assertNotNull(getResponseData(response, query));
                int refund = Integer.parseInt(getResponseData(response, query));
                System.out.println("Refund: " + refund);
                actualOrderValue = refund;
                break;
            } catch (AssertionError assertionError) {

            } catch (NumberFormatException a) {

            }
            waitConstant(7);
            j++;
        }
        Assert.assertEquals(actualOrderValue, amount, "Refund of Child doesn't display in Biz Order History.");
    }

    public static void checkOrderHistory_Biz_ChildPOSOrder_Refund_NOSEE(String ownerToken, String childID, int amount) {
        int actualOrderValue = -1;
        int tryGetOrder = 5;
        int j = 0;
        while (j < tryGetOrder) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                debugResponse(response);
                verifyStatusCode200("Check Order History of CHild's POS order doesn't refund in BIZ");
                query = "$[?(@.order_type == 'topup' " +
                        "&& @.order_status == 'paid' " +
                        "&& && @.payment_method == 'compensation' " +
                        "&& @.account_id == '" + childID + "')]" +
                        ".order_value";
                Assert.assertNull(getResponseData(response, query));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(7);
                j++;
            }
        }
        Assert.assertNotEquals(actualOrderValue, amount, "Refund of Child doesn't display in Biz Order History.");
    }

    public static String setBizChildID(String childAccountID) {
        response = get(String.format(gatewayHierarchy_BizChild_GetBizID, childAccountID));
        verifyStatusCode200("Set Biz Child ID");
        query = "$.id";
        childBizID = getResponseData(response, query);
        Assert.assertNotNull(childBizID);

        query = "$.account_id";
        String actualAccountID = getResponseData(response, query);
        Assert.assertEquals(actualAccountID, childAccountID);

        return childBizID;
    }

    public static void setBudget4BizAccount(int amount) {
        setBudget4BizAccount(amount, "active");
    }

    public static void setBudget4BizAccount(int amount, String status) {
        bodyJson = new JsonObject();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime now = LocalDateTime.now();
        String startdate = dtf.format(now);
        LocalDateTime end = now.plusDays(1);
        String enddate = dtf.format(end);

        bodyJson.addProperty("biz_account_id", Integer.parseInt(parentBizID));
        bodyJson.addProperty("total_amount", amount);
        bodyJson.addProperty("status", status);
        //bodyJson.addProperty("start_time", startdate);
        //bodyJson.addProperty("end_time", enddate);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        response = post(bodyJson, gatewayHierarchy_BizChild_SetBudget);
        debugResponse(response);
        verifyStatusCode200("Set Budget of Biz Account");
        query = "$.id";
        parentBudgetID = getResponseData(response, query);
        parentBudgetAmount = String.valueOf(amount);


    }

    @Deprecated
    public static void setBudget4ChildAccountPerMonth(int amount) {
        setBudget4ChildAccountPerMonth(amount, "active");
    }

    //VUHOANG DEBUG
    @Deprecated
    private static void setBudget4ChildAccountPerMonth(int amount, String status) {
        bodyJson = new JsonObject();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime now = LocalDateTime.now();
        String startdate = dtf.format(now);
        LocalDateTime end = now.plusDays(30);
        String enddate = dtf.format(end);

        bodyJson.addProperty("biz_account_id", Integer.parseInt(childBizID));
        bodyJson.addProperty("total_amount", amount);
        bodyJson.addProperty("status", status);
        bodyJson.addProperty("start_time", startdate);
        bodyJson.addProperty("end_time", enddate);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);


        response = post(bodyJson, gatewayHierarchy_BizChild_SetBudget);
        debugResponse(response);
        verifyStatusCode200("Set Budget For Child Account Per month");
        query = "$.id";
        childBudgetID = getResponseData(response, query);
        childBudgetAmount = String.valueOf(amount);
    }

    @Deprecated
    public static void verifySetLimitBudget4BizWhenCreateBizAccount(String bizBudget) {
        verifySetLimitBudget4BizAccountWhenCreateBizAccount(bizAccountID, bizBudget, "0", "0");
    }

    @Deprecated
    public static void verifySetLimitBudget_WhenCreateBizAccount_BudgetPerTransaction_4Child(String budgetPerTransaction) {
        verifySetLimitBudget4BizAccountWhenCreateBizAccount(childAccountID, "0", "0", budgetPerTransaction);
    }

    @Deprecated
    public static void verifySetLimitBudget_WhenCreateBizAccount_BudgetPerTransaction_4Parent(String budgetPerTransaction) {
        verifySetLimitBudget4BizAccountWhenCreateBizAccount(bizAccountID, "0", "0", budgetPerTransaction);
    }

    @Deprecated
    public static void verifySetLimitBudget4BizAccountWhenCreateBizAccount(String accountID, String expectedBizBudget, String expectedChildBudget, String expectedLimitPerOrder) {
        response = get(String.format(gatewayHierarchy_BizChild_GetBudgetByAccountIDOnly, accountID));
        debugResponse(response);
        verifyStatusCode200("Verify set Budget for Biz account");
        query = "$[0].total_amount";
        String actualTotalAmount = getResponseData(response, query);
        Assert.assertEquals(actualTotalAmount, expectedBizBudget);

        query = "$[0].child_limit_per_month";
        String actualChildLimitPerMonth = getResponseData(response, query);
        Assert.assertEquals(actualChildLimitPerMonth, expectedChildBudget);

        query = "$[0].amount_per_order";
        String actualAmountPerOrder = getResponseData(response, query);
        Assert.assertEquals(actualAmountPerOrder, expectedLimitPerOrder);
    }

    public static void verifySetBudget(String accountID, String bizTotalBudget, String bizChildPerMonth, String bizChildPerOrder) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(String.format(gatewayHierarchy_BizChild_GetBudgetByAccountIDOnly, accountID));
                verifyStatusCode200("Verify set Budget for Biz account");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }
        verifyStatusCode200("Verify set Budget for Biz account");

        query = "$[0].total_amount";
        String actualTotalAmount = getResponseData(response, query);
        Assert.assertEquals(actualTotalAmount, bizTotalBudget);

        query = "$[0].child_limit_per_month";
        String actualChildLimitPerMonth = getResponseData(response, query);
        Assert.assertEquals(actualChildLimitPerMonth, bizChildPerMonth);

        query = "$[0].amount_per_order";
        String actualAmountPerOrder = getResponseData(response, query);
        Assert.assertEquals(actualAmountPerOrder, bizChildPerOrder);
    }

    public static void verifySetBudget4Child_PerMonth(String childAccountID, String expectedChildBudget) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(String.format(gatewayHierarchy_BizChild_GetBudgetByAccountIDOnly, childAccountID));
                verifyStatusCode200("Verify set Budget for Biz account");
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }
        verifyStatusCode200("Verify set Budget for Biz account");
//        query = "$[0].child_limit_per_month";     FIELD vô dụng
        query = "$[0].child_limit_per_month";
        String actualTotalAmount = getResponseData(response, query);
        Assert.assertEquals(actualTotalAmount, expectedChildBudget);
    }

    public static void updateBudgetLimitationBizAccount(int bizBudget) {
        updateBudgetLimitationBizAccount(bizAccountID, bizBudget, 0, 0, "active");
    }

    public static void updateBudgetLimitationBizAccount(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        updateBudgetLimitationBizAccount(bizAccountID, bizBudget, childBudget, limitPerOrder, "active");
    }

    public static void updateBudgetLimitationBizAccount(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder, String status) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("parent_id", 0);
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("status", status);
        bodyJson.addProperty("total_amount", bizBudget);
        bodyJson.addProperty("child_limit_per_month", childBudget);
        bodyJson.addProperty("amount_per_order", limitPerOrder);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        response = put(bodyJson, String.format(gatewayHierarchy_Biz_UpdateBudget, bizAccountID));

        debugResponse(response);
        verifyStatusCode200("Verify update budget for Biz account");
        query = "$.id";
        parentBudgetID = getResponseData(response, query);
        parentBudgetAmount = String.valueOf(bizBudget);
    }


    public static void updateBudget4Biz_PerMonth(String bizAccountID, int bizBudget) {
        updateBudget4Biz(bizAccountID, bizBudget, -1, -1, "active");
    }

    public static void updateBudget4Biz_PerChildMonth(String bizAccountID, int bizBudget) {
        updateBudget4Biz(bizAccountID, -1, bizBudget, -1, "active");
    }

    public static void updateBudget4Biz_PerChildTransaction(String bizAccountID, int bizBudget) {
        updateBudget4Biz(bizAccountID, -1, -1, bizBudget, "active");
    }

    private static void updateBudget4Biz(String ownerAccountID, int bizPerMonth, int childPerMonth, int childPerTrans, String status) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(ownerAccountID));
        bodyJson.addProperty("parent_id", 0);
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("status", status);
        if (bizPerMonth >= 0)
            bodyJson.addProperty("total_amount", bizPerMonth);
        if (childPerMonth >= 0)
            bodyJson.addProperty("child_limit_per_month", childPerMonth);
        if (childPerTrans >= 0)
            bodyJson.addProperty("amount_per_order", childPerTrans);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        response = put(bodyJson, String.format(gatewayHierarchy_Biz_UpdateBudget, parentAccountID));
        debugResponse(response);

        verifyStatusCode200("Verify update budget for Biz account");
        parentBudgetID = getResponseData(response, "$.id");
        parentBudgetAmount = String.valueOf(bizPerMonth);
    }


    public static void updateBudget4Child_PerMonth(String childAccountID, String parentAccountID, int childBudgetMonth) {
        updateBudget4Child(childAccountID, parentAccountID, childBudgetMonth, -1, "active");      //no need param child_limit_per_month
    }

    public static void updateBudget4Child_PerTransaction(String childAccountID, String parentAccountID, int childBudgetTrans) {
        updateBudget4Child(childAccountID, parentAccountID, -1, childBudgetTrans, "active");      //no need param child_limit_per_month
    }

    //For old TS
    @Deprecated
    public static void updateBudget4Child(String childAccountID, String parentAccountID, int childTotal, int childPerTrans, String status) {
        updateBudget4Child(childAccountID, parentAccountID, childTotal, 0, childPerTrans, status);
    }

    public static void updateBudget4Child(String childAccountID, String parentAccountID, int childTotal, int childPerMonth, int childPerTrans, String status) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(childAccountID));
        bodyJson.addProperty("parent_id", Integer.parseInt(parentAccountID));
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("status", status);
        if (childTotal >= 0) {
            bodyJson.addProperty("total_amount", childTotal);        //childPerMonth is childTotal
        }
        if (childPerTrans >= 0) {
            bodyJson.addProperty("amount_per_order", childPerTrans);
        }
        bodyJson.addProperty("child_limit_per_month", childPerMonth);       //Not available for childAccount
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);
        response = put(bodyJson, String.format(gatewayHierarchy_Biz_UpdateBudget, parentAccountID));
        debugResponse(response);

        verifyStatusCode200("Verify update budget for Biz account");
        childBudgetID = getResponseData(response, "$.id");
    }

    @Deprecated // Must remove
    public static void registerBizAccountWithBizBudgetLimit(int budget) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        parentAccountID = bizAccountID;
        upgradeToBizAccount_SetBudget_BizMonth(parentAccountID, budget);
    }

    @Deprecated // Must remove
    public static void registerBizAccountWithChildBudgetLimit(int budget) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        parentAccountID = bizAccountID;
        upgradeToBizAccount_SetBudget_ChildMonth(parentAccountID, budget);
    }

    @Deprecated // Must remove
    public static void registerBizAccountWithChildTransactionBudgetLimit(int budget) {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;
        parentAccountID = bizAccountID;
        upgradeToBizAccount_SetBudget_ChildTransaction(parentAccountID, budget);    //set only for parent
    }

    @Deprecated
    public static void upgradeToBizAccount_SetBudget_ChildMonth(String bizAccountID, int childBudget) {
        upgradeToBizAccountAsParentWithSetBudget(bizAccountID, 0, childBudget, 0, "200");
    }

    @Deprecated
    public static void upgradeToBizAccount_SetBudget_ChildTransaction(String bizAccountID, int childBudget) {
        upgradeToBizAccountAsParentWithSetBudget(bizAccountID, 0, 0, childBudget, "200");
    }

    @Deprecated
    public static void upgradeToBizAccount_SetBudget_BizMonth(String bizAccountID, int bizBudget) {
        upgradeToBizAccountAsParentWithSetBudget(bizAccountID, bizBudget, 0, 0, "200");
    }

    private static void upgradeToBizAccountAsParentWithSetBudget(String bizAccountID, int bizBudget, int childBudget, int limitPerOrder, String responseCode) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("parent_id", 0);       //0 is default for Biz Parent without any child
        bodyJson.addProperty("status", "active");
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("total_amount", bizBudget);
        bodyJson.addProperty("child_limit_per_month", childBudget);
        bodyJson.addProperty("amount_per_order", limitPerOrder);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        response = post(bodyJson, gatewayHierarchy_BizChild_Link);
        debugResponse(response);
        verifyStatusCode200("UPGRADE TO BIZ ACCOUNT API FAILED");
        query = "$.id";
        parentBudgetID = getResponseData(response, query);
        parentBudgetAmount = String.valueOf(bizBudget);

        if (Integer.parseInt(responseCode) < 300)
            verifyIsParentAccount(bizAccountID);

        setParentAccountID(bizAccountID);       //After link Child, bizAccountID becomes parentAccountID. For new code
    }

    public static void verifyBizAccountInBizList(String bizAccountID, String bizPhone, String bizParentID, int numOfChild) {
        response = get(gatewayHierarchy_BizAccountList);
        verifyStatusCode200("Check Biz Account List (Admin Tool)");
        boolean testPassed = true;
        String failMsg = "";

        String bizIDActual = getResponseData(response, "$.accounts[0].account_id");
        if (!bizAccountID.equalsIgnoreCase(bizIDActual)) {
            if (testPassed)
                testPassed = false;
            failMsg += "\nBiz Account ID expect [" + bizAccountID + "] but actual [" + bizIDActual + "].";
        }

        String bizPhoneActual = getResponseData(response, "$.accounts[0].phone");
        if (!bizPhone.equalsIgnoreCase(bizPhoneActual)) {
            if (testPassed)
                testPassed = false;
            failMsg += "\nBiz Account Phone expect [" + bizPhone + "] but actual [" + bizPhoneActual + "].";
        }

        String bizParentIDActual = getResponseData(response, "$.accounts[0].parent_id");
        if (!bizParentID.equalsIgnoreCase(bizParentIDActual)) {
            if (testPassed)
                testPassed = false;
            failMsg += "\nBiz Parent ID expect [" + bizParentID + "] but actual [" + bizParentIDActual + "].";
        }


        int numOfChildActual = getResponseDataInt(response, "$.accounts[0].total_childs");
        if (numOfChildActual != numOfChild) {
            if (testPassed)
                testPassed = false;
            failMsg += "\nNumber of Child expect [" + numOfChild + "] but actual [" + numOfChildActual + "].";
        }

        String expire = getResponseData(response, "$.accounts[0].expired_at");
        if (!expire.equalsIgnoreCase(EXPIRED_DATE)) {
            if (testPassed)
                testPassed = false;
            failMsg += "\nExpire Date expect [" + EXPIRED_DATE + "] but actual [" + expire + "].";
        }

        Assert.assertTrue(testPassed, failMsg);
    }

    private static String getExpiredDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String oldDate = sdf.format(now);

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, 1);
        String newDate = sdf.format(c.getTime()) + "T07:50:22.5Z";
        return newDate;
    }

    private static String getExpiredDate_Expired() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String oldDate = sdf.format(now);

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DAY_OF_MONTH, -1);
        String newDate = sdf.format(c.getTime()) + "T07:50:22.5Z";
        return newDate;
    }

    private static void setExpireDateToBeExpired() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date now = new Date();
        EXPIRED_DATE = sdf.format(now) + ".1Z";
        System.out.println(EXPIRED_DATE);
    }

    public static void checkBizNotification(String token) {
        response = get(token, gatewayInbox_GetMessage);
        verifyStatusCode200("API GET MESSAGE INBOX DIE");
        List<String> messages = getResponseDataList(response, "$.messages");
        Assert.assertTrue(messages.size() > 0, "Not any notification is sent to Biz User");
        String message = getResponseData(response, "$.messages[0].data.title");

        Assert.assertTrue(message.startsWith("Nhân viên của bạn vừa sử dụng "),
                "No message is sent after Child pay by DT4B");

        Assert.assertTrue(message.equalsIgnoreCase("Nhân viên của bạn vừa sử dụng " + getAmountAfterPayChild() / 1000 + ".000ĐT"),
                "A Message after Child pays is sent to Biz but WRONG amount of DT");
    }

    public static void adminCheckTotalBizOrder(int totalExpect) {
//        response = get();
        verifyStatusCode200("API GET TOTAL OF ORDER HISTORY IS DEAD");
        query = "$.";
        Assert.assertEquals(totalExpect, getResponseDataInt(response, query), "TOTAL OF ORDER HISTORY IS WRONG");
    }

    //For debug
    public static void setBizBudget(String parentAccountID, int bizBudget, int childBudget, int limitPerOrder) {
        bodyJson = new JsonObject();
        bodyJson.addProperty("account_id", Integer.parseInt(parentAccountID));
        bodyJson.addProperty("parent_id", 0);
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("status", "active");
        bodyJson.addProperty("total_amount", bizBudget);
        bodyJson.addProperty("child_limit_per_month", childBudget);
        bodyJson.addProperty("amount_per_order", limitPerOrder);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        response = put(bodyJson, String.format(gatewayHierarchy_Biz_UpdateBudget, parentAccountID));

        debugResponse(response);
        verifyStatusCode200("Verify update budget for Biz account");
        query = "$.id";
//        parentBudgetID = getResponseData(response, query);
        parentBudgetAmount = String.valueOf(bizBudget);
    }

    //----------------- Deprecated ACTIONS ---------------------

    @Deprecated     // Register of Child should be combined upgrading to Child by using new API
    public static void registerChildAccount() {
        childToken = getAccessTokenOfNewUser();
        childPhone = tempUserPhone;
        childID = global_accountID;
//        childAccountID       : defined after register Child successfully
    }

    public static void registerBizAccount() {
        bizToken = getAccessTokenOfNewUser();
        bizPhone = tempUserPhone;
        bizAccountID = global_accountID;     //bizAccountID is created after register Biz Account and this value is not available
    }


    //----------------- USER REGISTER BIZ ---------------------
    public static String registerBizAccountByUser(String registerToken, String registerAccountID) {
        // STEP 1: UPLOAD LEGAL DOCUMENT


        // STEP 2: CREATE REGISTER REQUEST
        initBodyJson();

        String bizCompanyName = "Customer Automation Test Company " + getTimeStamp();
        int category = 1000;
        bodyJson.addProperty("account_id", Integer.parseInt(registerAccountID));
        bodyJson.addProperty("num_staff", numberOfStaff);
        bodyJson.addProperty("company_name", bizCompanyName);
        bodyJson.addProperty("company_type", bizCompanyType);
        bodyJson.addProperty("category", category);

        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = post(registerToken, bodyJson, gatewayHierarchy_Register_CreateInfo);
                verifyStatusCode200("MASLOW REGISTER - CREATE REGISTER REQUEST", gatewayHierarchy_Register_CreateInfo);
                Assert.assertEquals(getResponseData("$.message").toUpperCase(), "OK"
                        , "REGISTER BIZ: FAILED"
                );
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }

        debugResponse();
        verifyStatusCode200("MASLOW REGISTER", "API CREATE INFO");
        Assert.assertEquals(getResponseData("$.message").toUpperCase(), "OK"
                , "REGISTER BIZ: FAILED"
        );

//        Assert.assertEquals(getResponseDataInt("$.account_id")
//                , Integer.parseInt(registerAccountID)
//                , "REGISTER BIZ: Create info with incorrect Account ID."
//        );
//        Assert.assertEquals(getResponseData("$.num_staff")
//                , numberOfStaff
//                , "REGISTER BIZ: Create info with incorrect Number of Staff."
//        );
//        Assert.assertEquals(getResponseData("$.company_name")
//                , bizCompanyName
//                , "REGISTER BIZ: Create info with incorrect Biz Company Name."
//        );
//        Assert.assertEquals(getResponseData("$.company_type")
//                , bizCompanyType
//                , "REGISTER BIZ: Create info with incorrect Biz Company Type."
//        );
//        Assert.assertEquals(getResponseDataInt("$.category")
//                , category
//                , "REGISTER BIZ: Create info with incorrect Category."
//        );

        setBizRegisterAccountID(registerAccountID);
        setBizRegisterToken(registerToken);

        return registerAccountID;
    }

    public static void registerBizAccountByUserFailed(String registerToken, String registerAccountID, String errorMsg) {
        // STEP 1: UPLOAD LEGAL DOCUMENT


        // STEP 2: CREATE REGISTER REQUEST
        initBodyJson();
        String bizCompanyName = "Customer Automation Test Company " + getTimeStamp();
        int category = 1000;
        bodyJson.addProperty("account_id", Integer.parseInt(registerAccountID));
        bodyJson.addProperty("num_staff", numberOfStaff);
        bodyJson.addProperty("company_name", bizCompanyName);
        bodyJson.addProperty("company_type", bizCompanyType);
        bodyJson.addProperty("category", category);

        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = post(registerToken, bodyJson, gatewayHierarchy_Register_CreateInfo);
                verifyStatusCode400("MASLOW REGISTER - STILL CAN CREATE REGISTER REQUEST");
                Assert.assertEquals(getResponseData("$.error")
                        , "Register is exists"
                        , "REGISTER BIZ: Still create request after approved."
                );
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }

        debugResponse();
        verifyStatusCode400("MASLOW REGISTER - STILL CAN CREATE REGISTER REQUEST");
        Assert.assertEquals(getResponseData("$.error")
                , errorMsg
                , "REGISTER BIZ: Still create request after approved."
        );
    }

    public static String checkRegisterRequestStatusByUser(String registerToken, String registerBizAccountID, String status) {
        status = status.trim().toUpperCase();
        //Get Token by Phone
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(registerToken, gatewayHierarchy_Register_UserGetRequestInfo);
                verifyStatusCode200("MASLOW REGISTER -  USERGET REGISTER REQUEST", gatewayHierarchy_Register_UserGetRequestInfo);
                Assert.assertEquals(getResponseDataInt("$.data.account_id")
                        , Integer.parseInt(registerBizAccountID)
                        , "MASLOW REGISTER CHECK REQUEST - INCORRECT ACCOUNT ID");
                Assert.assertEquals(getResponseData("$.data.status").toUpperCase(), status
                        , "MASLOW REGISTER USER CHECK REQUEST - INCORRECT STATUS");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW REGISTER - USER GET REGISTER REQUEST", gatewayHierarchy_Register_UserGetRequestInfo);
        Assert.assertEquals(getResponseDataInt("$.data.account_id")
                , Integer.parseInt(registerBizAccountID)
                , "MASLOW REGISTER USER CHECK REQUEST - INCORRECT ACCOUNT ID");
        Assert.assertEquals(getResponseData("$.data.status").toUpperCase(), status
                , "MASLOW REGISTER USER CHECK REQUEST - INCORRECT STATUS");

        setBizRegisterRequestID(String.valueOf(getResponseDataInt("$.data.id")));
        return getBizRegisterRequestID();
    }

    public static String checkRegisterRequestStatusByChotot(String registerBizAccountID, String status) {
        status = status.trim().toUpperCase();
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(gatewayHierarchy_Register_GetRequestInfo);
                verifyStatusCode200("MASLOW REGISTER - GET REGISTER REQUEST", gatewayHierarchy_Register_GetRequestInfo);
                Assert.assertEquals(getResponseDataInt("$.data[?(@.account_id == '" + registerBizAccountID + "')].account_id")
                        , Integer.parseInt(registerBizAccountID)
                        , "MASLOW REGISTER CHECK REQUEST - INCORRECT ACCOUNT ID");
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW REGISTER - GET REGISTER REQUEST", gatewayHierarchy_Register_GetRequestInfo);
        Assert.assertEquals(getResponseDataInt("$.data[?(@.account_id == '" + registerBizAccountID + "')].account_id")
                , Integer.parseInt(registerBizAccountID)
                , "MASLOW REGISTER CHECK REQUEST - INCORRECT ACCOUNT ID");
        Assert.assertEquals(getResponseData("$.data[?(@.account_id == '" + registerBizAccountID + "')].status").toUpperCase(), status
                , "MASLOW REGISTER CHECK REQUEST - INCORRECT STATUS");

        setBizRegisterRequestID(getResponseData("$.data[?(@.account_id == '" + registerBizAccountID + "')].id"));
        return getBizRegisterRequestID();
    }

    public static void approveRegisterRequestByChotot(String bizRegisterRequestID) {
        initBodyJson();
        bodyJson.addProperty("status", "approved");
        bodyJson.addProperty("refuse_reason", "empty");
        url = String.format(gatewayHierarchy_Register_ApproveRequest, bizRegisterRequestID);
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = put(bodyJson, url);
                verifyStatusCode200("MASLOW REGISTER - APPROVED", url);
                Assert.assertEquals(getResponseData("$.message"), "Ok");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW REGISTER - APPROVED", gatewayHierarchy_Register_ApproveRequest);
        Assert.assertEquals(getResponseData("$.message"), "Ok");
    }

    public static void rejectRegisterRequestByChotot(String bizRegisterRequestID) {
        initBodyJson();
        bodyJson.addProperty("status", "rejected");
        bodyJson.addProperty("refuse_reason", "rejected");
        url = String.format(gatewayHierarchy_Register_RejectRequest, bizRegisterRequestID);
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = put(bodyJson, url);
                verifyStatusCode200("MASLOW REGISTER - REJECTED", url);
                Assert.assertEquals(getResponseData("$.message"), "Ok");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW REGISTER - REJECTED", gatewayHierarchy_Register_ApproveRequest);
        Assert.assertEquals(getResponseData("$.message"), "Ok");
    }

    //----------------- REMAINING & AMOUNT PER ORDER ---------------------
    public static void checkRemainingBudget_Unlimited(String childToken) {
        checkRemainingBudget(childToken, "-1", null);
    }

    public static void checkRemainingBudget(String childToken, String expectedRemainAmount) {
        checkRemainingBudget(childToken, expectedRemainAmount, null);
    }

    public static void checkPerOrderLimitation_Unlimited(String childToken) {
        checkRemainingBudget(childToken, null, "-1");   // Actually, it is 0
    }

    public static void checkPerOrderLimitation(String childToken, String expectedPerOrderLimitation) {
        checkRemainingBudget(childToken, null, expectedPerOrderLimitation);
    }

    private static void checkRemainingBudget(String childToken, String expectedRemainAmount, String expectedPerOrderLimitation) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(childToken, gatewayHierarchy_RemainBudget_GetAll);
                verifyStatusCode200("MASLOW", "GET REMAIN-BUDGET & AMOUNT-PER-ORDER");
                if (expectedRemainAmount != null)
                    Assert.assertEquals(getResponseData("$.data.remain_total_amount"), expectedRemainAmount
                            , "FAILED TO VERIFY: Hạn mức còn lại");
                // Limitation isn't set = 0, but API returns -1 for FE display
                if (expectedPerOrderLimitation != null)
                    Assert.assertEquals(getResponseData("$.data.amount_per_order"), expectedPerOrderLimitation
                            , "FAILED TO VERIFY: Hạn mức tối đa trên mỗi giao dịch");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW", "GET REMAIN-BUDGET & AMOUNT-PER-ORDER");
        // Always >= 0. < 0 : unlimited
        if (expectedRemainAmount != null)
            Assert.assertEquals(getResponseData("$.data.remain_total_amount"), expectedRemainAmount
                    , "FAILED TO VERIFY: Hạn mức còn lại");
        // Limitation isn't set = 0, but API returns -1 for FE display
        if (expectedPerOrderLimitation != null)
            Assert.assertEquals(getResponseData("$.data.amount_per_order"), expectedPerOrderLimitation
                    , "FAILED TO VERIFY: Hạn mức tối đa trên mỗi giao dịch");
    }
    //----------------- PERFORMANCE MANAGEMENT ---------------------

    protected void createListChildWithNumberOfAdListingFee(String numberOfAccount, String numberOfAds) {
        List<String> listChildAID = new ArrayList<String>();
        for (int i = 0; i < Integer.parseInt(numberOfAccount); i++) {
            listChildAID.add(registerChildAccount(getBizAccountID()));
            global_accessToken = getChildToken();
            for (int j = 0; j < Integer.parseInt(numberOfAds); j++) {
                flashAd_pty.insertHouseSellPro(global_accessToken, "acceptpaydt4b", false);
            }
            setAmountAfterPayChild(getAmountAfterPayDT4B());
        }
        String ListChildAID = listChildAID.toString();
        String replaceStart = ListChildAID.replace("[", "");
        String replaceEnd = replaceStart.replaceAll("]", "");
        String listChildAIDFinal = replaceEnd.replaceAll(" ", "");

        setListChildAID(listChildAIDFinal);
        System.out.println(listChildAIDFinal);
    }

    private void getBizCost(String token, String listChildAID) {

        LocalDate firstDayOfMonth = LocalDate.now();
        String TimeFromYYYYMMDD = firstDayOfMonth.withDayOfMonth(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String TimeToYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String urlGetBizCost = String.format(gatewayHierarchy_BizAccount_GetBizCost, (TimeFromYYYYMMDD + RFC3339From), (TimeToYYYYMMDD + RFC3339To), listChildAID);

        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(token, urlGetBizCost);
                verifyStatusCode200("ACCOUNT HIERARCHY: API GET BIZ COST IS DEAD", urlGetBizCost);

            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("API DEAD: GET BIZ COST", urlGetBizCost);

        Assert.assertNotNull(getResponseData("$.staffs.[*].amount"), "FAIL TEST: AMOUNT STAFF IS EMPTY");
        // Get data for next steps
        setTotalBizCost(getResponseData("$.cost.total"));
        setTotalParentCost(getResponseData("$.cost.parent"));
        setTotalChildCost(getResponseData("$.cost.staff"));

        int sumAmountChild = 0;
        int countChild = getResponseDataInt("staffs.size()");
        for (int i = 0; i < countChild; i++) {
            int amountCostChild = getResponseDataInt("staffs[" + i + "].amount");
            sumAmountChild = sumAmountChild + amountCostChild;
        }
        setSumAmountChild(sumAmountChild);
    }

    protected void getBizCostAll(String token, String TimeFromYYYYMMDD, String TimeToYYYYMMDD) {
        getBizCost(token, "");
    }

    protected void getBizCostByChild(String token, String listChildAID) {
        getBizCost(token, listChildAID);
    }

    protected void verifyBizCost() {
        Assert.assertEquals(
                String.valueOf((Integer.parseInt(getTotalChildCost()) + Integer.parseInt(getTotalParentCost())))
                , (getTotalBizCost())
                , "TOTAL BIZ COST INCORRECT");
        Assert.assertEquals(String.valueOf(getSumAmountChild()), getTotalChildCost(), "AMOUNT COST OF THE CHILD ACCOUNTS DOES NOT MATCH");
    }

    private static Response getCurrentBudget(String bizToken) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(bizToken, gatewayHierarchy_CurrentBudget);
                verifyStatusCode200("MASLOW", "GET CURRENT BUDGET");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW", "GET CURRENT BUDGET");
        return response;
    }

    protected static void checkResetDateDefault() {
        response = getCurrentBudget(bizToken);
        Assert.assertEquals(String.valueOf(getResponseData("$.budget.reset_date")), "1", "FAILED TO VERIFY: Default Reset Date should be 1");
    }

    protected static void checkResetDateChanged() {
        response = getCurrentBudget(bizToken);
        Assert.assertEquals(String.valueOf(getResponseData("$.budget.reset_date")), String.valueOf(temp_resetDate), "FAILED TO VERIFY: Default Reset Date should be 1");
    }

    protected static void checkResetTotalUsedAmount() {
        response = getCurrentBudget(bizToken);
        Assert.assertEquals(String.valueOf(getResponseData("$.budget.total_used_amount")), "0", "FAILED TO VERIFY: TOTAL USED AMOUNT should be 0");
    }

    protected static void checkNotResetTotalUsedAmount() {
        response = getCurrentBudget(bizToken);
        Assert.assertEquals(String.valueOf(getResponseData("$.budget.total_used_amount")), "15000", "FAILED TO VERIFY: TOTAL USED AMOUNT should be 15000");
    }

    private static void updateResetDate(String date, int amountPerMonth) {
        initBodyJson();
        bodyJson.addProperty("reset_date", Integer.parseInt(date));
        bodyJson.addProperty("child_limit_per_month", amountPerMonth);
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = put(bizToken, bodyJson, gatewayHierarchy_CurrentBudget);
                verifyStatusCode200("MASLOW", "UPDATE CURRENT BUDGET");
                Assert.assertEquals(String.valueOf(getResponseData("$.reset_date")), date, "FAILED TO VERIFY: Update Reset Date Failed");
                Assert.assertEquals(String.valueOf(getResponseData("$.child_limit_per_month")), String.valueOf(amountPerMonth), "FAILED TO VERIFY: Child Limit Per Month is wrong");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW", "UPDATE CURRENT BUDGET");
        Assert.assertEquals(String.valueOf(getResponseData("$.reset_date")), date, "FAILED TO VERIFY: Update Reset Date Failed");
        Assert.assertEquals(String.valueOf(getResponseData("$.child_limit_per_month")), String.valueOf(amountPerMonth), "FAILED TO VERIFY: Child Limit Per Month is wrong");
    }

    protected static void updateResetDateToFuture(int amountPerMonth) {
        LocalDate now = LocalDate.now();
        temp_resetDate = now.getDayOfMonth() + 1;
        if (temp_resetDate > 31) {
            temp_resetDate = 1;
        }
        updateResetDate(String.valueOf(temp_resetDate), amountPerMonth);
    }

    protected static void updateResetDateToPast(int amountPerMonth) {
        LocalDate now = LocalDate.now();
        temp_resetDate = now.getDayOfMonth() - 1;
        if (temp_resetDate < 1) {
            temp_resetDate = 2;
        }
        updateResetDate(String.valueOf(temp_resetDate), amountPerMonth);
    }

    protected static void updateResetDateToToday(int amountPerMonth) {
        LocalDate now = LocalDate.now();
        updateResetDate(String.valueOf(now.getDayOfMonth()), amountPerMonth);
    }

    private static void updateStatusParent(String status) {
        initBodyJson();
        bodyJson.addProperty("account_id", Integer.parseInt(getBizAccountID()));
        bodyJson.addProperty("status", status);
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = put(bodyJson, gatewayHierarchy_UpdateStatus);
                verifyStatusCode200("MASLOW", "UPDATE STATUS ACCOUNT");
                Assert.assertEquals(getResponseData("$.status"), status, "FAILED TO VERIFY: Update Status Failed");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW", "UPDATE STATUS ACCOUNT");
        Assert.assertEquals(getResponseData("$.status"), status, "FAILED TO VERIFY: Update Status Failed");
    }

    protected static void updateStatusToInactive() {
        updateStatusParent("inactive");
    }

    protected static void updateStatusToActive() {
        updateStatusParent("active");
    }

    protected static void verifyStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        String start_time = sdf.format(c.getTime()) + "T00:00:00Z";

        response = getCurrentBudget(bizToken);
        Assert.assertEquals(getResponseData("$..start_time"), start_time, "START TIME IS WRONG");
    }

    protected static void verifyEndTimeInMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, temp_resetDate);
        String end_time = sdf.format(c.getTime()) + "T00:00:00Z";

        response = getCurrentBudget(bizToken);
        Assert.assertEquals(getResponseData("$..end_time"), end_time, "END TIME IS WRONG");
    }

    protected static void verifyEndTimeInNextMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.DATE, temp_resetDate);
        String end_time = sdf.format(c.getTime()) + "T00:00:00Z";

        response = getCurrentBudget(bizToken);
        Assert.assertEquals(getResponseData("$..end_time"), end_time, "END TIME SHOULD BE NEXT MONTH");
    }

    protected static void deleteChildAccount(String childAccID) {
        url = String.format(gatewayHierarchy_DeleteChild, childAccID);
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = deleteNoBody(bizToken, url);
                verifyStatusCode200("MASLOW", "DELETE CHILD ACCOUNT");
                Assert.assertEquals(getResponseData("$.is_success"), "true", "DELETE ACC NOT SUCCESS");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW", "DELETE CHILD ACCOUNT");
        Assert.assertEquals(getResponseData("$.is_success"), "true", "DELETE ACC NOT SUCCESS");
        setOldParentBizID(getBizAccountID());
    }

    private static void verifyAccountStatus(String parentAccountID, String childAccountID, String accountStatus) {
        url = String.format(gatewayHierarchy_BizChild_CheckChilds, parentAccountID);
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(url);
                verifyStatusCode200("MASLOW", "GET LIST OF CHILD ACCOUNT");
                Assert.assertEquals(getResponseData("$.childs[?(@.account_id=='" + childAccountID + "')].status"), accountStatus, "STATUS IS WRONG");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("MASLOW", "GET LIST OF CHILD ACCOUNT");
        Assert.assertEquals(getResponseData("$.childs[?(@.account_id=='" + childAccountID + "')].status"), accountStatus, "STATUS IS WRONG");
    }

    protected static void verifyDeleteChildStatus() {
        verifyAccountStatus(getBizAccountID(), getChildAccountID(), "deleted");
    }

    protected static void addExistingChildToBizAccount(String bizAccountId, String childAccountID) {
        upgradeToChildAccount(bizAccountId, childAccountID, 0, 0, 0);
    }

    protected static void addExistingInactiveChildToBizAccount() {
        updateChildToInactive(bizAccountID, childAccountID, "200");
    }

    protected static void verifyActiveChildStatus() {
        verifyAccountStatus(getParentAccountID(), getChildAccountID(), "active");
    }

    public static void updateChildToInactive(String bizAccountID, String childAccountID) {
        updateChildToInactive(bizAccountID, childAccountID, "200");
    }

    public static void verifyInactiveChildLinkedToBiz(String bizAccountID, String childAccountID) {
        response = get(String.format(gatewayHierarchy_BizChild_CheckChilds, bizAccountID));
        debugResponse();
        verifyStatusCode200("Verify Child is Linked to Biz");
        query = String.format("$.childs[?(@.account_id == %d && @.status == 'inactive')].parent_id", Integer.parseInt(childAccountID));
        String actualBizID = getResponseData(response, query);
        Assert.assertEquals(actualBizID, bizAccountID, String.format("Child[%s] isn't linked to Biz[%s].", childAccountID, bizAccountID));
    }

    protected static void updateChildToInactive(String bizAccountID, String childAccID, String responseCode) {
        initBodyJson();
        bodyJson.addProperty("account_id", Integer.parseInt(childAccID));
        bodyJson.addProperty("parent_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("status", "inactive");
        bodyJson.addProperty("expired_at", EXPIRED_DATE);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        url = String.format(gatewayHierarchy_Update_Child, childAccID);

        for (int i = 0; i < 5; i++) {
            try {
                response = put(bodyJson, url);
                verifyStatusCode(response, "MASLOW-UPGRADE_TO_CHILD", url, responseCode);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode(response, "MASLOW-UPGRADE_TO_CHILD", url, responseCode);

        if (Integer.parseInt(responseCode) < 300) {
            verifyInactiveChildLinkedToBiz(bizAccountID, childAccID);
        }

        setParentAccountID(bizAccountID);       //After link Child, bizAccountID becomes parentAccountID. For new code
        setChildAccountID(childAccID);
        setChildID(childAccID);
        setChildPhone(childPhone);
    }

    protected static void updateChildToExpired(String bizAccountID, String childAccID) {
        String expiredDate = getCurrentTime_PlusSecond_GMT7(10);
        initBodyJson();
        bodyJson.addProperty("account_id", Integer.parseInt(childAccID));
        bodyJson.addProperty("parent_id", Integer.parseInt(bizAccountID));
        bodyJson.addProperty("expired_at", expiredDate);
        bodyJson.addProperty("action_by", ADMIN_ACCOUNT_EMAIL);

        url = String.format(gatewayHierarchy_Update_Child, childAccID);

//        for (int i = 0; i < 5; i++) {
//            try {
                response = put(bodyJson, url);
                verifyStatusCode200("MASLOW-UPGRADE_TO_CHILD", url);
                waitConstant(10);   // Wait to be expired
                waitConstant(10);   // Wait to response
                Assert.assertEquals(getResponseData("$[?(@.account_id == '" + childAccID + "')].expired_at")
                        , expiredDate
                        , "Expired Date is updated failed");


//            } catch (AssertionError error) {
//                waitConstant(2);
//                expiredDate = getCurrentTime_PlusSecond_GMT7(6);
//                bodyJson.addProperty("expired_at", expiredDate);
//            }
//        }
//        debugResponse();
//        verifyStatusCode200("MASLOW-UPGRADE_TO_CHILD", url);
//        Assert.assertEquals(getResponseData("$[?(@.account_id == '" + childAccID + "')].expired_at")
//                , expiredDate
//                , "Expired Date is updated failed");

        setParentAccountID(bizAccountID);       //After link Child, bizAccountID becomes parentAccountID. For new code
        setChildAccountID(childAccID);
        setChildID(childAccID);
        setChildPhone(childPhone);
    }

    // =============================== DEBIT APPROACH ===============================
    // Variables
    protected static final String DEBIT_DONGTOT_TYPE_365 = "dt_365";
    protected static final String DEBIT_DONGTOT_TYPE_FREE = "dt_free";
    protected static final String DEBIT_DONGTOT_TYPE_EXPIRY = "dt_expiry";
    protected static final String DEBIT_ORDER_STATUS_SUCCESS = "success";
    protected static final String DEBIT_ORDER_STATUS_FAILED = "failed";
    protected static final String DEBIT_ORDER_STATUS_PENDING = "pending";

    private List<Integer> childReceiverAccountIDs = null;

    protected List<Integer> getChildReceiverAccountIDs() {
        return childReceiverAccountIDs;
    }

    protected void addChildReceiverAccountIDs(int childAccountID) {
        if (childReceiverAccountIDs == null)
            childReceiverAccountIDs = new ArrayList<>();
        this.childReceiverAccountIDs.add(childAccountID);
    }

    private List<String> childReceiverTokens = null;

    protected List<String> getChildReceiverTokens() {
        return childReceiverTokens;
    }

    protected void addChildReceiverTokens(String childToken) {
        if (childReceiverTokens == null)
            childReceiverTokens = new ArrayList<>();
        this.childReceiverTokens.add(childToken);
    }

    private static String parentDTExpiry_ExpiredClosest = null;

    protected static String getParentDTExpiry_ExpiredClosest() {
        return parentDTExpiry_ExpiredClosest;
    }

    protected static void setParentDTExpiry_ExpiredClosest(String parentExpiryDT_ExpiredClosest) {
        AccountHierarchy_Functions.parentDTExpiry_ExpiredClosest = parentExpiryDT_ExpiredClosest;
    }

    // Closest Expired Date of Parent when topup
    private static String parentDTFree_ExpiredClosest = null;

    protected static String getParentDTFree_ExpiredClosest() {
        return parentDTFree_ExpiredClosest;
    }

    protected static void setParentDTFree_ExpiredClosest(String parentDTFree_ExpiredClosest) {
        AccountHierarchy_Functions.parentDTFree_ExpiredClosest = parentDTFree_ExpiredClosest;
    }

    // Closest Expired Date of Child when topup
    private static String childDTExpiry_ExpiredClosest = null;

    protected static String getChildDTExpiry_ExpiredClosest() {
        return childDTExpiry_ExpiredClosest;
    }

    protected static void setChildDTExpiry_ExpiredClosest(String childDTExpiry_ExpiredClosest) {
        AccountHierarchy_Functions.childDTExpiry_ExpiredClosest = childDTExpiry_ExpiredClosest;
    }

    private int orderDebitID = -1;

    protected int getOrderDebitID() {
        return orderDebitID;
    }

    protected void setOrderDebitID(int orderDebitID) {
        this.orderDebitID = orderDebitID;
    }

    protected static String timestampDTExpiry_thisMonth = "";
    protected static String timestampDTExpiry_nextMonth = "";


    // Functionalities
    protected static void transferDongTot(String parentBizToken, String balanceType, String amountPerChild, List<Integer> childReceiverAccountIDs) {
        Assert.assertNotNull(childReceiverAccountIDs, "AH DEBIT - RECEIVER LIST IS NULL");
        initBodyJson();
        bodyJson.addProperty("balance_type", balanceType.toLowerCase().trim());
        bodyJson.addProperty("amount_per_account", Integer.parseInt(amountPerChild));
        bodyJson.addProperty("platform", "global");
        bodyJson.add("receivers", getJsonArrayValueInt(childReceiverAccountIDs));

        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = post(parentBizToken, bodyJson, gatewayHierarchy_Debit_TransferDT);
                verifyStatusCode200("CREDIT - AH DEBIT", gatewayHierarchy_Debit_TransferDT);
                Assert.assertTrue(getResponseDataBoolean("$.ok"));
                break;
            } catch (AssertionError e) {
                waitConstant(5);
            }
        }
        debugResponse();
        verifyStatusCode200("CREDIT - AH DEBIT", gatewayHierarchy_Debit_TransferDT);
        Assert.assertTrue(getResponseDataBoolean("$.ok")
                , "BIZ PARENT TRANSFER DT " + balanceType + " IS FAILED");

        Assert.assertEquals(getResponseDataInt("$.data.total_account"), childReceiverAccountIDs.size()
                , "BIZ PARENT TRANSFER DT " + balanceType + " IS FAILED");

        Assert.assertEquals(getResponseDataInt("$.data.total_amount"), childReceiverAccountIDs.size() * Integer.parseInt(amountPerChild)
                , "BIZ PARENT TRANSFER DT " + balanceType + " IS FAILED");

        Assert.assertEquals(getResponseDataInt("$.data.amount_per_account"), Integer.parseInt(amountPerChild)
                , "BIZ PARENT TRANSFER DT " + balanceType + " IS FAILED");
    }

    protected static String transferDongTotFailed(String parentBizToken, String balanceType, String amount, List<Integer> childReceiverAccountIDs) {
        Assert.assertNotNull(childReceiverAccountIDs, "AH DEBIT - RECEIVER LIST IS NULL");
        initBodyJson();
        bodyJson.addProperty("balance_type", balanceType.toLowerCase().trim());
        bodyJson.addProperty("amount_per_account", Integer.parseInt(amount));
        bodyJson.addProperty("platform", "global");
        bodyJson.add("receivers", getJsonArrayValueInt(childReceiverAccountIDs));

        // If transfer is successful, there'll be not enough DT to try again
        response = post(parentBizToken, bodyJson, gatewayHierarchy_Debit_TransferDT);
        debugResponse();
        verifyStatusCode400("CREDIT - AH DEBIT Verify Transfer DT Failed", gatewayHierarchy_Debit_TransferDT);
        Assert.assertFalse(getResponseDataBoolean("$.ok"));
        return getResponseData("$.error.message");
    }

    protected static String transferDongTotFailed(String parentBizToken, String balanceType, String amount, int childReceiverAccountID) {
        List<Integer> childIDs = new ArrayList<>();
        childIDs.add(childReceiverAccountID);
        return transferDongTotFailed(parentBizToken, balanceType, amount, childIDs);
    }

    protected static int checkOrderHistory_Debit_TransferDongTot(String ownerToken, List<Integer> receiverAccIDs, String dtType, int amount, String status) {
        // Check Order Sumup
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(ownerToken, getGatewayHierarchy_Debit_OrderHistory);
                verifyStatusCode200("AH - DEBIT - ORDER HISTORY", getGatewayHierarchy_Debit_OrderHistory);
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertTrue(getResponseData("$.data[0].type_id").equalsIgnoreCase(dtType.trim())
                , "DT Type of Debit Order is incorrect");
        Assert.assertTrue(getResponseData("$.data[0].status").equalsIgnoreCase(status.trim())
                , "Status of Debit Order is incorrect");
        Assert.assertEquals(getResponseDataInt("$.data[0].number_of_receiver")
                , receiverAccIDs.size()
                , "Status of Debit Order is incorrect");
        Assert.assertEquals(getResponseDataInt("$.data[0].total_amount")
                , amount
                , "Total of DT Transfer is incorrect"
        );
        return getResponseDataInt("$.data[0].id");

    }

    protected static void checkOrderHistory_Debit_TransferDongTot_Details(String ownerToken, int orderID, List<Integer> receiverAccIDs, String dtType, int amountPerChild) {
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(ownerToken, String.format(getGatewayHierarchy_Debit_OrderHistory_Details, orderID));
                verifyStatusCode200("AH DEBIT OPAPI", getGatewayHierarchy_Debit_OrderHistory_Details);
                Assert.assertNotNull(getResponseData("$[0].id"));
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }

        debugResponse();
        verifyStatusCode200("AH DEBIT OPAPI", getGatewayHierarchy_Debit_OrderHistory_Details);
        for (int accID : receiverAccIDs) {
            Assert.assertEquals(getResponseData("$[*][?(@.receiver == '" + accID + "')].type_id")
                    , dtType
                    , "DT Type of Order History Detail is incorrect"
            );
            Assert.assertEquals(getResponseDataInt("$[*][?(@.receiver == '" + accID + "')].amount")
                    , amountPerChild
                    , "DT Amount of Order History Detail is incorrect"
            );
        }
    }

    protected static void checkOrderHistory_Debit_ReceiveDongTot(String receiverToken, int receiverID, String senderID, String dtType, int amount) {
        query = "$.data[*][?(@.type_id == '" + dtType + "' && @.amount == " + amount + " && @.status == 'success')].";
        // Check Order Sumup
        for (int i = 0; i < retryAccountHierarchy; i++) {
            try {
                response = get(receiverToken, getGatewayHierarchy_Debit_OrderHistory_ReceiveChild);
                verifyStatusCode200("AH - RECEIVE DT - ORDER HISTORY", getGatewayHierarchy_Debit_OrderHistory_ReceiveChild);
                Assert.assertNotNull(getResponseDataList(query + "receiver"));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(3);
            }
        }
        debugResponse();
        List<Integer> receiverIDs = getResponseDataListInt(query + "receiver");
        for (int actualReceiverID : receiverIDs) {
            Assert.assertEquals(actualReceiverID, receiverID, "Child has transfer order of other Childs");
        }
    }
}


