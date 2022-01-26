package projects.functions.chotot.escrow;

import com.vn.chotot.driver.selenium.DriverFactory;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projects.functions.APISupportFunctions;

import static api.configuration.GatewayConfig.*;
import static com.vn.chotot.keywords.selenium.Action.clickJS;
import static com.vn.chotot.keywords.selenium.Action.setText;
import static com.vn.chotot.keywords.selenium.Page.openURL;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementClickable;
import static com.vn.chotot.keywords.selenium.Wait.waitForElementVisible;

public class Escrow_API_Functions extends APISupportFunctions {
    // Constant
    private static final int reTryToConnectToPayoo = 3;
    private static final int reTryToCallEscrow = 5;

    private static final String PAYOO_OTP_VALID = "123456";
    private static final String PAYOO_OTP_INVALID = "000000";
    private static final String PAYOO_OTP_EXPIRED = "111111";
    private static final String PAYOO_PHONE_VALID = "0908686960";
    private static final String PAYOO_PHONE_LINKED_MAIN = "0909789776";
    private static final String PAYOO_PHONE_LINKED_SUB = "0937123448";
    private static final String PAYOO_PHONE_LOCKED = "0905429704";
    private static final String PAYOO_PHONE_NON_PRIVATE = "0935123446";
    private static final String PAYOO_PHONE_NOT_VERIFIED_PASSPORT = "0909091234";   // LOCKED
    private static final String PAYOO_PHONE_OTP_EXPIRED = "0971122523";
    private static final String PAYOO_PHONE_OVER_SMS_LIMITATION = "0836951004";

    private static final String PAYOO_ERROR_CODE_LOCKED = "91";
    private static final String PAYOO_ERROR_CODE_LINKED_PHONE = "92";
    private static final String PAYOO_ERROR_CODE_NON_PRIVATE = "28";
    private static final String PAYOO_ERROR_CODE_OTP_EXPIRED = "38";
    private static final String PAYOO_ERROR_CODE_OVER_SMS_LIMITATION = "39";

    private static final String PAYOO_ERROR_MSG_LOCKED = "Ví có trạng thái khóa hoặc chưa kích hoạt";
    private static final String PAYOO_ERROR_MSG_LINKED_PHONE = "aaa";
    private static final String PAYOO_ERROR_MSG_NON_PRIVATE = "Loại tài khoản ví không hợp lệ";
    private static final String PAYOO_ERROR_MSG_LINKED_SUB = "Số điện thoại không phải là số điện thoại chính";
    private static final String PAYOO_ERROR_MSG_OTP_EXPIRED = "OTP hết hiệu lực";
    private static final String PAYOO_ERROR_MSG_OTP_INCORRECT = "Sai OTP";
    private static final String PAYOO_ERROR_MSG_OVER_SMS_LIMITATION = "Tài khoản/số điện thoại vượt hạn mức nhận OTP trong ngày";
    private static final String DISPUTE_REASON = "Tôi đổi ý, không muốn mua nữa";

    private static final String msgAfterUnlinkPayooSuccess = "Bạn đã gỡ bỏ kết nối đến tài khoản Payoo thành công";
    private static final String msgAfterUnlinkPayooFail = "Bạn đang có đơn hàng trong quá trình xử lý. Vui lòng hoàn tất đơn hàng trước khi huỷ kết nối";

    // Variables of Linking
    protected static String payooToken = "";
    protected static int payooOTPTimeout;
    protected static String userTokenAfterLinkedPayoo = "";

    private static String
            sellerToken = "", sellerAccountID = "", sellerPhone = "", buyerToken = "",
            buyerAccountID = "", buyerPhone = "", roomIdSeller = "",
            listIDSeller = "", orderNumber = "", orderStatus = "",
            AccountIDUnlink = "", userTokenUnlink = "", userPhoneUnlink = "", userAccountIDUnlink = "",
            msgUnlinkResponse = "";

    private static boolean isOnTabSelling = false, isOnTabBuying = false;

    // Getter & Setter
    // ========== ACTIONS ON UI OF PAYOO ===========
    // ATM Bank Tab
    @FindBy(xpath = "//a[@data-method = '#bank-account']")
    private static WebElement payment_atm;
    // Visa Tab
    @FindBy(xpath = "//a[@data-method = '#cc']")
    private static WebElement payment_visa;
    @FindBy(xpath = "//input[@id = 'card-no']")
    private static WebElement visa_card_number;
    @FindBy(xpath = "//input[@id = 'card-holder-name']")
    private static WebElement visa_card_name;
    @FindBy(xpath = "//input[@id = 'card-date']")
    private static WebElement visa_card_expire_date;
    @FindBy(xpath = "//input[@id = 'card-cvv']")
    private static WebElement visa_card_cvv;
    @FindBy(xpath = "//button[text()='Tiếp tục']")
    private static WebElement btnContinue;
    @FindBy(xpath = "//button[text()='Thanh Toán']")
    private static WebElement btnPay;
    @FindBy(xpath = "//input[@id='Button1']")
    private static WebElement checkCVV;
    @FindBy(xpath = "//input[@id='Button3']")
    private static WebElement verifyOTP;
    @FindBy(xpath = "//a[contains(text(),'Quay về trang mua hàng')]")
    private static WebElement backToChototFromPayoo;

    // DEPOSIT VAR INITIALIZATION
    @FindBy(xpath = "//td[contains(., 'TỔNG TIỀN')]")
    private static WebElement title_success;
    @FindBy(xpath = "//h2[contains(text(),'Nội dung không tồn tại')]")
    private static WebElement title_success_visa;
    // Payoo Tab
    @FindBy(xpath = "//a[@data-method = '#payoo-account']")
    private static WebElement payment_payoo;


    public static String getUserPhoneUnlink() {
        return userPhoneUnlink;
    }

    public static void setUserPhoneUnlink(String userPhoneUnlink) {
        Escrow_API_Functions.userPhoneUnlink = userPhoneUnlink;
    }

    public static String getUserAccountIDUnlink() {
        return userAccountIDUnlink;
    }

    public static void setUserAccountIDUnlink(String userAccountIDUnlink) {
        Escrow_API_Functions.userAccountIDUnlink = userAccountIDUnlink;
    }

    public static String getAccountIDUnlink() {
        return AccountIDUnlink;
    }

    public static void setAccountIDUnlink(String accountIDUnlink) {
        AccountIDUnlink = accountIDUnlink;
    }

    public static String getUserTokenUnlink() {
        return userTokenUnlink;
    }

    public static void setUserTokenUnlink(String userTokenUnlink) {
        Escrow_API_Functions.userTokenUnlink = userTokenUnlink;
    }

    protected static String getOrderStatus() {
        return orderStatus;
    }

    protected static void setOrderStatus(String orderStatus) {
        Escrow_API_Functions.orderStatus = orderStatus;
    }

    protected static String getPayooToken() {
        return payooToken;
    }

    protected static void setPayooToken(String payooToken) {
        Escrow_API_Functions.payooToken = payooToken;
    }

    protected static int getPayooOTPTimeout() {
        return payooOTPTimeout;
    }

    protected static void setPayooTimeout(int payooTimeout) {
        Escrow_API_Functions.payooOTPTimeout = payooTimeout;
    }

    protected static String getUserTokenAfterLinkedPayoo() {
        return userTokenAfterLinkedPayoo;
    }

    protected static void setUserTokenAfterLinkedPayoo(String userTokenAfterLinkedPayoo) {
        Escrow_API_Functions.userTokenAfterLinkedPayoo = userTokenAfterLinkedPayoo;
    }

    protected static String getSellerToken() {
        return sellerToken;
    }

    protected static void setSellerToken(String sellerToken) {
        Escrow_API_Functions.sellerToken = sellerToken;
    }

    protected static String getSellerAccountID() {
        return sellerAccountID;
    }

    protected static void setSellerAccountID(String sellerAccountID) {
        Escrow_API_Functions.sellerAccountID = sellerAccountID;
    }

    protected static String getSellerPhone() {
        return sellerPhone;
    }

    protected static void setSellerPhone(String sellerPhone) {
        Escrow_API_Functions.sellerPhone = sellerPhone;
    }

    protected static String getBuyerToken() {
        return buyerToken;
    }

    protected static void setBuyerToken(String buyerToken) {
        Escrow_API_Functions.buyerToken = buyerToken;
    }

    protected static String getBuyerAccountID() {
        return buyerAccountID;
    }

    protected static void setBuyerAccountID(String buyerAccountID) {
        Escrow_API_Functions.buyerAccountID = buyerAccountID;
    }

    public static String getMsgUnlinkResponse() {
        return msgUnlinkResponse;
    }

    public static void setMsgUnlinkResponse(String msgUnlinkResponse) {
        Escrow_API_Functions.msgUnlinkResponse = msgUnlinkResponse;
    }

    protected static String getBuyerPhone() {
        return buyerPhone;
    }

    protected static void setBuyerPhone(String buyerPhone) {
        Escrow_API_Functions.buyerPhone = buyerPhone;
    }

    protected static String getRoomIdSeller() {
        return roomIdSeller;
    }

    private static void setRoomIdSeller(String roomIdSeller) {
        Escrow_API_Functions.roomIdSeller = roomIdSeller;
    }

    protected static String getListIDSeller() {
        return listIDSeller;
    }

    protected static void setListIDSeller(String listIDSeller) {
        Escrow_API_Functions.listIDSeller = listIDSeller;
    }

    protected static String createRoomIDByBuyer(String buyerToken, String listIDOfSeller) {
        JsonObject listId = new JsonObject();
        listId.addProperty("item_id", listIDOfSeller);

        initBodyJson();
        bodyJson = new JsonObject();
        bodyJson.add("product", listId);

        for (int i = 0; i < reTryToConnectToPayoo; i++) {
            try {
                response = post(buyerToken, bodyJson, gatewayChatPublic_CreateRoom);
                verifyStatusCode200("SCOOBY", "CREATE ROOM FOR ESCROW");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        verifyStatusCode200("SCOOBY", "CREATE ROOM FOR ESCROW");
        String roomID = getResponseData("$.result._id");
        setRoomIdSeller(roomID);
        return roomID;
    }

    protected static String getOrderNumber() {
        return orderNumber;
    }

    protected static void setOrderNumber(String orderNumber) {
        Escrow_API_Functions.orderNumber = orderNumber;
    }

    // Function
    private static void requestLinkToPayoo(String ownerToken, String payooPhoneNumber) {
        initBodyJson();
        bodyJson.addProperty("phone", payooPhoneNumber.trim());

        for (int i = 0; i < reTryToConnectToPayoo; i++) {
            try {
                response = post(ownerToken, bodyJson, gatewayEscrow_Linking);
                verifyStatusCode200("ESCROW", "REQUEST LINK TO PAYOO");
                Assert.assertNotNull(getResponseData("$.code"));
                break;
            } catch (AssertionError e) {
                waitConstant(5);
            }
        }

        debugResponse();
        verifyStatusCode200("ESCROW", "LINK TO PAYOO");
        Assert.assertEquals("Request link to Payoo failed.", "SHOW_OTP", getResponseData("$.code"));
        Assert.assertNotNull(getResponseData("$.otp_timeout"));

        // Set value
        setPayooTimeout(getResponseDataInt("$.otp_timeout"));
    }

    protected static void requestLinkToPayoo_validAccount(String ownerToken) {
        requestLinkToPayoo(ownerToken, PAYOO_PHONE_VALID);
    }

    private static void requestLinkToPayooFailed(String ownerToken, String payooPhoneNumber, String errorMessage) {
        initBodyJson();
        bodyJson.addProperty("phone", payooPhoneNumber.trim());

        for (int i = 0; i < reTryToConnectToPayoo; i++) {
            try {
                response = post(ownerToken, bodyJson, gatewayEscrow_Linking);
                verifyStatusCode400("ESCROW - REQUEST LINK TO PAYOO FAILED");
                Assert.assertNotNull(getResponseData("$.code"));
                break;
            } catch (AssertionError e) {
                waitConstant(5);
            }
        }

        debugResponse();
        verifyStatusCode400("ESCROW - REQUEST LINK TO PAYOO FAILED");
        Assert.assertEquals("Request link to Payoo Failed is failed."
                , "PAYOO_ERROR"
                , getResponseData("$.code"));

        Assert.assertEquals("Request link to Payoo Failed is failed."
                , "PAYOO_ERROR"
                , getResponseData("$.err_code"));

        Assert.assertEquals("Failed Message is incorrect."
                , errorMessage
                , getResponseData("$.message").replace("\\" + '\"', ""));

    }

    protected static void requestLinkToPayoo_lockedAccount(String ownerToken) {
        requestLinkToPayooFailed(ownerToken, PAYOO_PHONE_LOCKED, PAYOO_ERROR_MSG_LOCKED);
    }

    protected static void requestLinkToPayoo_linkedAccount_main(String ownerToken) {
        requestLinkToPayoo(ownerToken, PAYOO_PHONE_LINKED_MAIN);
    }

    protected static void requestLinkToPayoo_linkedAccount_sub(String ownerToken) {
        requestLinkToPayooFailed(ownerToken, PAYOO_PHONE_LINKED_SUB, PAYOO_ERROR_MSG_LINKED_SUB);
    }

    protected static void requestLinkToPayoo_nonprivateAccount(String ownerToken) {
        requestLinkToPayooFailed(ownerToken, PAYOO_PHONE_NON_PRIVATE, PAYOO_ERROR_MSG_NON_PRIVATE);
    }

    protected static void requestLinkToPayoo_notverifiedAccount(String ownerToken) {
        requestLinkToPayooFailed(ownerToken, PAYOO_PHONE_NOT_VERIFIED_PASSPORT, PAYOO_ERROR_MSG_LOCKED);
    }

    protected static void requestLinkToPayoo_expiredOTPAccount(String ownerToken) {
        requestLinkToPayoo(ownerToken, PAYOO_PHONE_OTP_EXPIRED);
    }

    protected static void requestLinkToPayoo_overSMSLimitationAccount(String ownerToken) {
        requestLinkToPayooFailed(ownerToken, PAYOO_PHONE_OVER_SMS_LIMITATION, PAYOO_ERROR_MSG_OVER_SMS_LIMITATION);
    }

    private static void verifyLinkToPayooByOTP(String ownerToken, String payooPhoneNumber, String OTP, String payooErrMsg) {
        initBodyJson();
        bodyJson.addProperty("otp", OTP);
        bodyJson.addProperty("phone", payooPhoneNumber.trim());

        for (int i = 0; i < reTryToConnectToPayoo; i++) {
            try {
                response = post(ownerToken, bodyJson, gatewayEscrow_Linking);
                if (OTP.equalsIgnoreCase(PAYOO_OTP_VALID) && payooErrMsg == null) {
                    verifyStatusCode200("ESCROW", "VERIFY OTP TO LINK TO PAYOO");
                    Assert.assertEquals(getResponseData("$.code"), "LINK_SUCCESS");
                } else if (OTP.equalsIgnoreCase(PAYOO_OTP_EXPIRED)) {
                    verifyStatusCode400("ESCROW - VERIFY OTP TO LINK TO PAYOO FAILED");
                    Assert.assertEquals(getResponseData("$.error"), "RESEND_OTP");
                } else {
                    verifyStatusCode400("ESCROW - VERIFY OTP TO LINK TO PAYOO FAILED");
                    Assert.assertEquals(getResponseData("$.error"), "PAYOO_ERROR");
                }
                break;
            } catch (AssertionError e) {
                waitConstant(5);
            }
        }

        debugResponse();
        if (OTP.equalsIgnoreCase(PAYOO_OTP_VALID) && payooErrMsg == null) {
            verifyStatusCode200("ESCROW", "VERIFY OTP TO LINK TO PAYOO");
            Assert.assertEquals("Link to Payoo failed"
                    , "LINK_SUCCESS"
                    , getResponseData("$.code"));

            // Verification
            Assert.assertEquals("Chotot Account is NOT active"
                    , true
                    , getResponseDataBoolean("$.profile.is_active"));

            Assert.assertEquals("Linking to Payoo is failed"
                    , true
                    , getResponseDataBoolean("$.profile.is_payoo_linked"));

            setPayooToken(getResponseData("$.token"));
            setUserTokenAfterLinkedPayoo("Bearer " + getResponseData("$.access_token"));
        } else if (OTP.equalsIgnoreCase(PAYOO_OTP_EXPIRED)) {
            verifyStatusCode400("ESCROW - VERIFY OTP TO LINK TO PAYOO");
            Assert.assertEquals(getResponseData("$.err_code"), "RESEND_OTP");
            Assert.assertEquals(getResponseData("$.code"), "RESEND_OTP");
            Assert.assertTrue(getResponseData("$.message").equalsIgnoreCase(payooErrMsg));
        } else {
            verifyStatusCode400("ESCROW - VERIFY OTP TO LINK TO PAYOO");
            Assert.assertEquals(getResponseData("$.err_code"), "PAYOO_ERROR");
            Assert.assertEquals(getResponseData("$.code"), "PAYOO_ERROR");
            Assert.assertTrue(getResponseData("$.message").equalsIgnoreCase(payooErrMsg));
        }

    }

    protected static void verifyLinkToPayoo_validOTP(String ownerToken) {
        verifyLinkToPayooByOTP(ownerToken, PAYOO_PHONE_VALID, PAYOO_OTP_VALID, null);
    }

    protected static void verifyLinkToPayoo_invalidOTP(String ownerToken) {
        verifyLinkToPayooByOTP(ownerToken, PAYOO_PHONE_VALID, PAYOO_OTP_INVALID, PAYOO_ERROR_MSG_OTP_INCORRECT);
    }


//    protected static void verifyEscrowOrderManagement_seller_(String buyerToken, String orderNumber) {
//        initBodyJson();
//        bodyJson.addProperty("order_number", orderNumber);
//
//        for (int i = 0; i < reTryToCallEscrow; i++) {
//            try {
//                response = post(buyerToken, bodyJson, gatewayEscrow_Buyer_ConfirmReceiveProduct);
//                verifyStatusCode200("ESCROW", "BUYER CONFIRMS THAT RECEIVED PRODUCT");
//                break;
//            } catch (AssertionError e) {
//                waitConstant(3);
//            }
//        }
//        debugResponse();
//        verifyStatusCode200("ESCROW", "BUYER CONFIRMS THAT RECEIVED PRODUCT");
//    }

    protected static void verifyLinkToPayoo_expiredOTP(String ownerToken) {
        verifyLinkToPayooByOTP(ownerToken, PAYOO_PHONE_OTP_EXPIRED, PAYOO_OTP_EXPIRED, PAYOO_ERROR_MSG_OTP_EXPIRED);
    }

    protected static void verifyLinkToPayoo_overSMSLimitation(String ownerToken) {
        verifyLinkToPayooByOTP(ownerToken, PAYOO_PHONE_OVER_SMS_LIMITATION, PAYOO_OTP_EXPIRED, PAYOO_ERROR_MSG_OVER_SMS_LIMITATION);
    }

    // FUNCTION DEPOSIT
    protected static void buyerRequestDeposit(String buyerToken, String roomIdOfSellerAd) {
        Assert.assertFalse("ROOM ID IS N/A. NO ROOM IS CREATED", roomIdOfSellerAd.isEmpty());

        initBodyJson();
        bodyJson.addProperty("room_id", roomIdOfSellerAd);

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(buyerToken, bodyJson, gatewayEscrow_Buyer_RequestMessage);
                verifyStatusCode200("ESCROW", "BUYER REQUESTS DEPOSIT");
                break;
            } catch (AssertionError e) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "REQUEST MESSAGE: CREATE");
        Assert.assertTrue("BUY SEND DEPOSIT REQUEST FAILED", getResponseDataBoolean("$.ok"));
    }

    protected static String sellerCreateDepositOrder(String sellerToken, String buyerAccountID, String adListIDOfSeller, String roomIdOfSellerAd, int depositAmount) {
        // Check input param
        Assert.assertFalse("CREATE DEPOSIT ORDER: ROOM ID IS NULL", roomIdOfSellerAd.isEmpty());
        Assert.assertFalse("CREATE DEPOSIT ORDER: BUY ACCOUNT ID IS NULL", buyerAccountID.isEmpty());
        Assert.assertFalse("CREATE DEPOSIT ORDER: LIST ID IS NULL", adListIDOfSeller.isEmpty());

        // Init Request body
        initBodyJson();
        bodyJson.addProperty("deposit_amount", depositAmount);
        bodyJson.addProperty("description", "CUSTOMER AUTO TEST | DEPOSIT ORDER | " + getTimeStamp());
        bodyJson.addProperty("buyer_id", Integer.parseInt(buyerAccountID));
        bodyJson.addProperty("listing_id", Integer.parseInt(adListIDOfSeller));
        bodyJson.addProperty("room_id", roomIdOfSellerAd);

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(sellerToken, bodyJson, gatewayEscrow_Seller_CreateOrder);
                verifyStatusCode200("ESCROW", "SELLER CREATE ORDER");
                break;
            } catch (AssertionError e) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "SELLER CREATE ORDER");

        Assert.assertEquals("SELLER CREATE ORDER: STATUS IS INCORRECT"
                , "WAITING_FOR_PAYMENT", getResponseData("$.status").toUpperCase());

        Assert.assertEquals("SELLER CREATE ORDER: INCORRECT DEPOSIT AMOUNT"
                , depositAmount, getResponseDataInt("$.amount_deposit"));

        String orderNumber = getResponseData("$.order_number");
        setOrderNumber(orderNumber);
        return orderNumber;
    }

    protected static void buyerConfirmOrderOfSeller(String buyerToken, String orderNumber) {
        initBodyJson();
        bodyJson.addProperty("order_number", orderNumber);

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(buyerToken, bodyJson, gatewayEscrow_Buyer_ConfirmOrderOfSeller);
                verifyStatusCode200("ESCROW", "BUYER CONFIRMS ORDER");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "BUYER CONFIRMS ORDER");
    }

    protected static String buyerCheckoutOrderOfSeller_byWeb(String buyerToken, String orderNumber) {
        initBodyJson();
        bodyJson.addProperty("order_number", orderNumber);
        bodyJson.addProperty("redirect_url", "localhost");
        bodyJson.addProperty("description", "CUSTOMER AUTO TEST - BUYER Checkouts Seller's order");

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(buyerToken, bodyJson, gatewayEscrow_Buyer_CheckoutOrderOfSeller);
                verifyStatusCode200("ESCROW", "BUYER CONFIRMS ORDER");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "BUYER CONFIRMS ORDER");
        return getResponseData("$.url");
    }

    protected static void sellerConfirmDelivery(String sellerToken, String orderNumber) {
        initBodyJson();
        bodyJson.addProperty("order_number", orderNumber);

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(sellerToken, bodyJson, gatewayEscrow_Seller_ConfirmDelivery);
                verifyStatusCode200("ESCROW", "SELLER CONFIRMS DELIVERY");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "SELLER CONFIRMS DELIVERY");
    }

    protected static void buyerConfirmReceiveProduct(String buyerToken, String orderNumber) {
        initBodyJson();
        bodyJson.addProperty("order_number", orderNumber);

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(buyerToken, bodyJson, gatewayEscrow_Buyer_ConfirmReceiveProduct);
                verifyStatusCode200("ESCROW", "BUYER CONFIRMS THAT RECEIVED PRODUCT");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "BUYER CONFIRMS THAT RECEIVED PRODUCT");
    }

    protected static void cancelOrderBySeller(String orderNumber, String userToken) {
        initBodyJson();
        bodyJson.addProperty("order_number", orderNumber);

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(userToken, bodyJson, gatewayEscrow_Cancel_Order);
                verifyStatusCode200("ESCROW", "ORDER CANCEL BY SELLER");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "ORDER CANCEL BY SELLER");

        // GET DATA FOR NEXT STEP
        setOrderNumber(getResponseData("$.order_number"));
        setOrderStatus(getResponseData("$.status"));
    }

    private static void disputeDepositOrder(String orderNumber, String userToken, String disputeReason, String userType) {
        initBodyJson();
        bodyJson.addProperty("order_number", orderNumber);
        bodyJson.addProperty("reason", disputeReason);
        bodyJson.addProperty("description", "");
        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = post(userToken, bodyJson, String.format(gatewayEscrow_Dispute, userType));
                verifyStatusCode200("ESCROW", "BUYER DISPUTE DEPOSIT ORDER");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "BUYER DISPUTE DEPOSIT ORDER");

        // GET DATA FOR NEXT STEP
        setOrderNumber(getResponseData("$.order_number"));
        setOrderStatus(getResponseData("$.status"));
    }

    protected static void disputeWithReason_ToiKhongMuonBanNua(String orderNumber, String sellerToken) {
        disputeDepositOrder(getOrderNumber(), getSellerToken(), "Tôi không muốn bán nữa", "seller");
    }

    protected static void disputeWithReason_ToiDoiYKhongMuonBanNua(String orderNumber, String buyerToken) {
        disputeDepositOrder(orderNumber, buyerToken, "Tôi đổi ý, không muốn mua nữa", "buyer");
    }

    protected static void getOrderInfo(String orderNumber, String userToken) {
        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = get(userToken, String.format(gatewayEscrow_Get_Order, orderNumber));
                verifyStatusCode200("ESCROW", "GET ORDER INFO FROM USER");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "GET ORDER INFO FROM USER");
        Assert.assertNotNull(getResponseData("$.status"));

        // GET DATA FOR NEXT STEP
        setOrderStatus(getResponseData("$.status"));
        setOrderNumber(getResponseData("$.order_number"));
        setSellerAccountID(getResponseData("$.user_info.seller_id"));
        setBuyerAccountID(getResponseData("$.user_info.buyer_id"));
    }

    private static void verifyDepositOrderStatus(String userToken, String orderStatus, String orderNumber) {

        Assert.assertEquals(getOrderNumber(), getResponseData("$.order_number"));
//        Assert.assertEquals("", getSellerAccountID(), getResponseData("$.user_info.seller_id"));
//        Assert.assertEquals("", getBuyerAccountID(), getResponseData("$.user_info.buyer_id"));

        switch (orderStatus) {
            case "CANCELLED_BY_SELLER":
                Assert.assertEquals("CANCELLED_BY_SELLER", getResponseData("$.status"));
                System.out.println("CANCELLED_BY_SELLER: PASSED");

                break;
            case "PAYMENT_REQUEST_EXPIRED":
                break;
            case "WAITING_FOR_PAYMENT":
                Assert.assertEquals("WAITING_FOR_PAYMENT", getResponseData("$.status"));
                System.out.println("WAITING_FOR_PAYMENT: PASSED");
                break;
            case "PAYING_IN_PROCESS":
                Assert.assertEquals("PAYING_IN_PROCESS", getResponseData("$.status"));
                System.out.println("PAYMENT_IN_PROGRESS: PASSED");
                break;
            case "PAYMENT_RECEIVED":
                Assert.assertEquals("PAYMENT_RECEIVED", getResponseData("$.status"));
                System.out.println("PAYMENT_RECEIVED: PASSED");
                break;
            case "PRODUCT_DELIVERED_BY_SELLER":
                Assert.assertEquals("PRODUCT_DELIVERED_BY_SELLER", getResponseData("$.status"));
                System.out.println("PRODUCT_DELIVERED_BY_SELLER: PASSED");
                break;
            case "DISPUTE_RAISED_BY_BUYER":
                Assert.assertEquals("DISPUTE_RAISED_BY_BUYER", getResponseData("$.status"));
                System.out.println("DISPUTE_RAISED_BY_BUYER: PASSED");
                break;
            case "DISPUTE_RAISED_BY_SELLER":
                Assert.assertEquals("DISPUTE_RAISED_BY_SELLER", getResponseData("$.status"));
                System.out.println("DISPUTE_RAISED_BY_SELLER: PASSED");
                break;
            case "PAYMENT_REFUND_TO_BUYER":
                Assert.assertEquals("PAYMENT_REFUND_TO_BUYER", getResponseData("$.status"));
                System.out.println("PAYMENT_REFUND_TO_BUYER: PASSED");
                break;
            case "PAYMENT_RELEASED":
                Assert.assertEquals("PAYMENT_RELEASED", getResponseData("$.status"));
                System.out.println("PAYMENT_RELEASED: PASSED");
                break;
        }
    }

    protected static void verifyOrderStatus_CancelBySeller(String sellerToken, String orderNumber) {
        verifyDepositOrderStatus(sellerToken, "CANCELLED_BY_SELLER", orderNumber);
    }

    protected static void verifyOrderStatus_DisputeByBuyer(String buyerToken, String orderStatus, String orderNumber) {
        verifyDepositOrderStatus(buyerToken, orderStatus, orderNumber);
    }

    protected static void verifyOrderStatus_DisputeBySeller(String sellerToken, String orderStatus, String orderNumber) {
        verifyDepositOrderStatus(sellerToken, orderStatus, orderNumber);
    }

    protected static void verifyOrderStatus_PaymentRelease(String buyerToken, String orderNumber) {
        verifyDepositOrderStatus(buyerToken, "PAYMENT_RELEASED", orderNumber);
    }

    protected static void verifyOrderStatus_ProductDelivered() {
        verifyDepositOrderStatus(getSellerToken(), "PRODUCT_DELIVERED_BY_SELLER", getOrderNumber());
    }

    protected static void clickOnTabSelling_orderManagement() {
        isOnTabSelling = true;
        isOnTabBuying = false;
    }

    protected static void clickOnTabBuying_orderManagement() {
        isOnTabSelling = false;
        isOnTabBuying = true;
    }

    protected static void getListOnSellingTab(String ownerToken) {
//        response = get(ownerToken, gatewayEscrow_OrderManagement_Selling);
//        debugResponse();
//
//        response = get(ownerToken, gatewayEscrow_OrderManagement_Buying);
//        debugResponse();
    }

    protected static void getListOnBuyingTab() {
    }

    private static void verifyOrderStatus(String ownerToken, String buyerAccountID, String listID, String orderId, String status) {
        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = get(ownerToken, String.format(gatewayEscrow_Order_Status, buyerAccountID, listID));
                verifyStatusCode200("ESCROW", "CHECK ORDER STATUS");
                break;
            } catch (AssertionError e) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ESCROW", "CHECK ORDER STATUS");
        Assert.assertEquals("CHEK ORDER STATUS : incorrect listid", getResponseData("$.ad_info.listing_id"), listID);
        Assert.assertEquals("CHEK ORDER STATUS : incorrect orderid", getResponseData("$.order_number"), orderId);
        // Assert.assertEquals("CHEK ORDER STATUS : incorrect status", getResponseData("$.status"), status);
        Assert.assertEquals("CHEK ORDER STATUS : incorrect status", status, getResponseData("$.status"));
    }

    protected static void verifyOrderStatus_paymentInProgress(String ownerToken, String buyerAccountID, String listID, String orderId) {
        verifyOrderStatus(ownerToken, buyerAccountID, listID, orderId, "PAYMENT_IN_PROGRESS");
    }

    protected static void verifyOrderStatus_paymentReceived(String ownerToken, String buyerAccountID, String listID, String orderId) {
        verifyOrderStatus(ownerToken, buyerAccountID, listID, orderId, "PAYMENT_RECEIVED");
    }

    private static void payOrderOnPayooPage(String url, String paymentMehod) {
        DriverFactory.instance().createWebDriver("chrome");
        PageFactory.initElements(DriverFactory.instance().getWebDriver(), Escrow_API_Functions.class);

        try {
            openURL(url);

            switch (paymentMehod) {
                case "ATM":
                    clickJS(payment_atm);
                    break;
                case "VISA":
                case "MASTER_CARD":
                    waitForElementVisible(payment_visa, reTryToCallEscrow);
                    clickJS(payment_visa);
                    setText(visa_card_number, "5555555555554444");
                    setText(visa_card_name, "USER DEMO");
                    visa_card_expire_date.sendKeys("06");
                    waitConstant(1 / 2);
                    visa_card_expire_date.sendKeys("22");
                    setText(visa_card_cvv, "123");
                    clickJS(btnContinue);
                    clickJS(btnPay);
                    waitForElementVisible(checkCVV, 30);
                    clickJS(checkCVV);
                    clickJS(verifyOTP);
                    waitForElementClickable(backToChototFromPayoo, 20);
                    clickJS(backToChototFromPayoo);
                    waitForElementVisible(title_success_visa, 30);
                    waitConstant(20); // Wait for Payoo update order
                    break;
                case "PAYOO":
                default:
                    clickJS(payment_payoo);
                    break;
            }
        } catch (Exception e) {
            DriverFactory.instance().disposeWebDriver();
            Assert.assertTrue("PAYMENT ON PAYOO PAGE IS FAILED: " + e, false);
        }
        DriverFactory.instance().disposeWebDriver();

    }

    protected static void payOrderOnPayooPage_visa(String url) {
        payOrderOnPayooPage(url, "VISA");
    }

    private static void payOrderOnPayooPage_atm(String url) {
        payOrderOnPayooPage(url, "ATM");
    }

    private static void payOrderOnPayooPage_payoo(String url) {
        payOrderOnPayooPage(url, "PAYOO");
    }

    // Unlink account Payoo

    private static void requestUnlinkPayoo(String platform, String userTokenUnlink, Boolean isSuccess) {
        initBodyJson();
        bodyJson.addProperty("platform", platform.toLowerCase().trim());

        for (int i = 0; i < reTryToCallEscrow; i++) {
            try {
                response = put(userTokenUnlink, bodyJson, gatewayEscrow_UnLinking);
                if(isSuccess) {
                    verifyStatusCode200("ESCROW", gatewayEscrow_UnLinking);
                } else {
                    verifyStatusCode400("ESCROW", gatewayEscrow_UnLinking);
                }
                Assert.assertNotNull(getResponseData("$.message"));
                break;

            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        if(isSuccess) {
            verifyStatusCode200("ESCROW", gatewayEscrow_UnLinking);
        } else {
            verifyStatusCode400("ESCROW", gatewayEscrow_UnLinking);
        }
        setMsgUnlinkResponse(getResponseData("$.message"));
        setAccountIDUnlink(getResponseData("$.data.profile.account_id"));
    }

    protected static void verifyUnlinkPayooSuccess(String accountIDOfUserUnlink) {
        Assert.assertEquals(msgAfterUnlinkPayooSuccess, getResponseData("$.message"));
        Assert.assertEquals(getAccountIDUnlink(), accountIDOfUserUnlink);
    }
    protected static void verifyUnlinkPayooFail() {
        Assert.assertEquals(msgAfterUnlinkPayooFail, getMsgUnlinkResponse());
    }

    protected static void sellerUnlinkPayoo(String platform) {
        requestUnlinkPayoo(platform, getSellerToken(), true);
    }

    protected static void buyerUnlinkPayoo(String platform) {
        requestUnlinkPayoo(platform, getBuyerToken(), true);
    }
    protected static void sellerUnlinkPayooFail(String platform) {
        requestUnlinkPayoo(platform, getSellerToken(), false);
    }

    protected static void buyerUnlinkPayooFail(String platform) {
        requestUnlinkPayoo(platform, getBuyerToken(), false);
    }
}

