package api.configuration;

import static com.vn.chotot.configuration.Constant.DOMAIN;

public class GatewayConfig {

    public static final Boolean allowAllCertFlag = true;
    public static final String gatewayHost = "https://gateway.chotot." + DOMAIN;
    public static final String gatewayHostPrivateV1 = "https://gateway.chotot." + DOMAIN + "/v1/private";
    public static final String gatewayHostPrivateV2 = "https://gateway.chotot." + DOMAIN + "/v2/private";
    public static final String gatewayHostInternalV1 = "https://gateway.chotot." + DOMAIN + "/v1/internal";
    public static final String gatewayHostPubliclV5 = "https://gateway.chotot." + DOMAIN + "/v5/public";
    public static final String gatewayHostPubliclV1 = "https://gateway.chotot." + DOMAIN + "/v1/public";
    public static final String gatewayHostPubliclV3 = "https://gateway.chotot." + DOMAIN + "/v3/public";
    public static final String gatewayHostPubliclV2 = "https://gateway.chotot." + DOMAIN + "/v2/public";
    public static final String gatewayAdListing = "/v1/public/ad-listing";
    public static final String gatewayViewAdOnListing = gatewayHostPubliclV1 + "/ad-listing/%s";
    //    public static final String gatewayGetAdFeatureID = gatewayHostPubliclV3 + "/ad-features/categories/%s";
    public static final String gatewayGetAdFeatureID = gatewayHostPubliclV1 + "/ad-features/categories2/%s";
    public static final String gatewayAdListingAlpha = "/v1/public/alpha-ad-listing";
    public static final String gatewayAdListingExporter = "/v1/public/ad-listing-exporter";
    public static final String gatewayCreditPrivate = "/v1/private/credit";
    public static final String gatewayCreditInternal = "/v1/internal/credit";
    public static final String gatewayOrderPrivateV1 = "/v1/private/orders";
    public static final String gatewayOderInternal = "/v1/internal/orders";
    public static final String gatewayOrderPrivateV2 = "/v2/private/orders";
    public static final String gatewayShoot = "/v1/private/shoot";
    public static final String gatewayScheduleBump = "/v1/private/schedule-pos";
    public static final String gatewayStickyAdsInternal = "/v1/internal/sticky-ads";
    public static final String gatewayStickyAdsPublicV1 = "/v1/public/sticky-ads";
    public static final String gatewayStickyAdsPublicV2 = "/v2/public/sticky-ads";
    public static final String gatewayPackagesPrivate = "/v1/private/packages";
    public static final String gatewayPackagesPublic = "/v1/public/packages/pricing";
    public static final String gatewayPackagesInternal = "/v1/internal/packages";
    public static final String gatewayPricerPrivateV1 = "/v1/private/pricer";
    public static final String gatewayPricerPrivateV2 = "/v2/private/pricer";
    public static final String gatewayPricerInternalV1 = "/v1/internal/pricer";
    public static final String gatewayPricerInternalV2 = "/v2/internal/pricer";
    public static final String gatewayCartPrivate = "/v2/private/cart";
    public static final String gatewayCartInternal = "/v2/internal/cart";
    public static final String gatewayAdFeaturesPublic = "/v1/public/ad-features/mappings";
    public static final String gatewayAdFeaturesInternal = "/v1/internal/ad-features/mappings";
    public static final String gatewayChatImagesPrivate = "/v1/private/chat/images";
    public static final String gatewaySearchSuggestionPublic = "/v1/public/search-suggestion";
    public static final String gatewaySearchSuggestionInternal = "/v1/internal/search-suggestion";
    public static final String gatewayReasonPublic = "/v1/public/reason";
    public static final String gatewayReasonInternal = "/v1/internal/reason";

    // API for Control Panel
    public static final String gatewayCPAcceptAd = "/v1/ads/accept";
    public static final String gatewayCPRejectAd = "/v1/ads/refuse";
    public static final String gatewayCPAdInfo = "/v1/ads/get_info";
    public static final String gatewayCPLoadAction = "/v1/ads/load_action";
    public static final String gatewayCPLogin = "/v1/admin/login";
    public static final String gatewayCPWaitEditingAd = "/v1/mama/waitediting_ad";
    //    public static final String gatewayCPAddShopIntoQueue = "/v1/internal/alias/%s/moveQueue";

    // Payment
    public static final String gatewayTopupDongTotWithPayoo =
            "https://payment.chotot." + DOMAIN + "/payooapi/pos/topup";
    public static final String gatewayTopupDongTotWithMomo =
            "https://gateway.chotot." + DOMAIN + "/v1/protected/momoapp/topup";
    public static final String gatewayMomoPackage =
            "https://gateway.chotot." + DOMAIN + "/v1/protected/momoapp/packages";
    public static final String gatewayPayOrderWithDongTot =
            gatewayHost + "/v1/private/payment/pay-order";
    public static final String gatewayPayOrderReturnURL =
            "https://pay.chotot." + DOMAIN + "/checkout/response";
    public static final String gatewayCheckDongTotWithPayoo =
            gatewayHost + "/v1/private/credit/balance?platform=global";
    public static final String gatewayCheckDongTot =
            gatewayHost + "/v1/private/credit/balance";
    public static final String gatewayDongTot365_SetExpire_ByTime =
            gatewayHost + "/v1/internal/credit/expired/set-expired-by-time";
    public static final String gatewayDongTot_BankTransfer_Approve =
            gatewayHost + "/v1/internal/bank_transfer/contract/approve-bank-transfer";

    // Dashboard
    public static final String gatewayAdDashboard = gatewayHost + "/v1/private/theia/list_ads/%s?shop_alias=all&is_latest=true";
    public static final String gatewayAdDashboardStatus = gatewayHost + "/v1/private/theia";
    public static final String gatewayShopDashboard = gatewayHost + "/v1/private/theia?shop_alias=%s&count_by_cate=true";

    public static final String gatewayShopByPhone = gatewayHost + "/v1/internal/shops/search?phone=%s"; // %s: phone
    public static final String gatewayAdsShopByShopAlias = gatewayHost + "/v1/private/theia?shop_alias=%s"; // %s: shop_alias
    public static final String gatewayAdsPrivate = gatewayHost + "/v1/private/theia";
    public static final String gatewayUserAdsInternal = gatewayHost + "/v1/internal/user_ads/ad/%s"; //%s: ad_id
    public static final String gatewayUserAdsPrivate = gatewayHost + "/v1/private/user_ads/ad/%s"; //%s: account_id

    // Deeplink
    public static final String gatewayDeepLink = gatewayHost + "v1/public/deeplink-resolver?url=";

    // Create SHOP
    @Deprecated
    public static final String gatewayCreateShop = gatewayHost + "/v1/private/shops";
    public static final String gatewayShop_GetAlias = gatewayHost + "/v1/public/shops/accounts/%s";
    public static final String gatewayShop_Extend_PTY_BuyPackage = gatewayHostPrivateV2 + "/cart/service";
    public static final String gatewayShop_Extend_ELT_BuyPackage = gatewayHostPrivateV2 + "/cart/service?cart_id=shoppackage";
    public static final String gatewayShop_Extend_VEH_BuyPackage = gatewayHostPrivateV2 + "/cart/service";
    public static final String gatewayShop_AdShopToChotot = gatewayHostPrivateV1 + "/flashad/move_between_shop_chotot";
    public static final String gatewayShop_AdChototToShop = gatewayHostPrivateV1 + "/flashad/move_between_shop_chotot_all";
    public static final String gatewayShop_HideAdOnDashboard = gatewayHostPrivateV1 + "/theia/%s";      //%s : ad_id
    public static final String gatewayShop_UnHideAd = gatewayHostPrivateV1 + "/theia/%s";      //%s : ad_id

    public static final String getGatewayShop_ApproveShop_ByAlias = gatewayHostInternalV1 + "/shops/alias/%s/approve";
    public static final String gatewayCart_AddService = gatewayHostPrivateV2 + "/cart/service";

    public static final String getGatewayShop_RemainingFreeAd = gatewayHostInternalV1 + "/user_ads/%s/count_shop_remain/%s/%s"; //Account ID, shop alias, category ID


    //Cart
    public static final String gatewayCart_GetInfo = gatewayHostPrivateV2 + "/cart/info";

    //    public static final String gatewayCreateShopCover = gatewayHost + "/v1/private/temp/cover";
    public static final String gatewayCreateShopCover =
            gatewayHost + "v1/private/shops/id/6eWeYVI3rW0TiBj/cover";
    public static final String gatewayCreateShopAvatar = gatewayHost + "/v1/private/temp/avatar";
    public static final String gatewayCheckShopURL =
            gatewayHost + "/v1/private/shops/check/url/"; // + phone
    public static final String gatewayCheckShopName =
            gatewayHost
                    + "/v1/private/shops/check/name/Chuy%C3%AAn%20trang%20B%C4%90S%20test%20auto%20"; // +
    public static final String gatewayGetAllShopInfo =
            gatewayHost + "/v1/private/pricer/shops/get-all";
    public static final String gatewayCheckPhone = gatewayHost + "/v1/public/profile/checkphone/";
    public static final String gatewayLogin = gatewayHost + "/v1/public/auth/login";
    public static final String gatewayLogout = gatewayHost + "/v1/private/auth/logout";
    public static final String gatewayProfile = gatewayHost + "/v1/private/profile";
    public static final String gatewaySelfProfile = gatewayHost + "/v1/private/profile/self";
    public static final String gatewayFlashAdNew = gatewayHost + "/v2/private/flashad/new";
    public static final String gatewayFlashAdEdit = gatewayHost + "/v1/private/flashad/edit";
    public static final String gatewayUploadImage = "https://cloudgw.chotot." + DOMAIN + "/v1/private/images/upload";
    public static final String gatewayRegister = gatewayHost + "/v1/public/auth/register";
    public static final String gatewayVerifyOTP = gatewayHost + "/v1/private/auth/verify";
    public static String global_accessToken = "";
    public static String global_accessTokenCP = "";
    public static String global_accountPhoneCP = "";
    public static String global_accountPasswordCP = "";
    public static String global_accountID = "";
    public static String global_accountOID = "";
    public static String global_shopAlias = "";
    public static final String gatewayChatSendMessage = gatewayHost + "/v2/public/chat/message/send";
    public static final String gatewayPublic = gatewayHost + "/v1/public";

    // Video Ads
    //public static final String gatewayProxyVideoUpload = "https://video-uat.cmco.io/proxy-video-upload/api";
    public static final String gatewayVideoConfigPublic = gatewayHost + "/v1/public/chapy-pro/conf";
    public static final String gatewayVideoUploadPrivate = gatewayHost + "/v1/private/videos/upload";
    public static final String gatewayVideoUploadStatusPrivate = gatewayHost + "/v1/private/videos/%s/status"; //%s:video_id


    // IRIS-FAS
    //public static final String gatewayIrisFas = "https://cloudgw.chotot.org/v1/1i";
    public static final String gatewayIrisFas = "https://iris-uat.cmco.io";


    //SCOOBY GATEWAY
    public static final String gatewayChatPublic = gatewayHost + "/v2/public/chat";
    public static final String gatewayChatPrivate = gatewayHost + "/v1/private/chat";

    public static final String gatewayChatPublic_RoomInfo = gatewayChatPublic + "/room";
    public static final String gatewayChatPublic_CreateRoom = gatewayChatPublic + "/room/create";
    public static final String gatewayChatPublic_ListRoom = gatewayChatPublic + "/room/list";
    public static final String gatewayChatPublic_GetMessageTemplateByRoomID = gatewayChatPublic + "/room/template/%s";
    public static final String gatewayChatPublic_GetMessageTemplateByListID = gatewayChatPublic + "/template/%s";

    public static final String gatewayChatPublic_SendMessage = gatewayChatPublic + "/message/send";
    public static final String gatewayChatPublic_GetUnreadCount = gatewayChatPublic + "/message/getallunreadmessagecount";
    public static final String gatewayChatPublic_SetReadMessage = gatewayChatPublic + "/message/setread";
    public static final String gatewayChatPublic_GetMessageHistory = gatewayChatPublic + "/message/history";
    public static final String gatewayChatPublic_GetMessageUnreadHistory = gatewayChatPublic + "/message/unreadhistory";
    public static final String gatewayChatPublic_HideRoom = gatewayChatPublic + "/message/deleteMessageInRoom";

    public static final String gatewayChatPublic_BlockUSer = gatewayChatPublic + "/user/block";
    public static final String gatewayChatPublic_UnblockUSer = gatewayChatPublic + "/user/unblock";
    public static final String gatewayChatPublic_PublicInfo = gatewayChatPublic + "/user/get/%s";

    public static final String gatewayChatPublic_ReportUSer = gatewayChatPublic + "/user/report";
    public static final String gatewayChatPublic_PrivateInfo = gatewayChatPublic + "/user/get";
    public static final String gatewayChatPublic_CheckRegister = gatewayChatPublic + "/user/checkRegister";
    public static final String gatewayChatPublic_RegisterUser = gatewayChatPublic + "/user/register";
    public static final String gatewayChatPrivate_UploadImage = gatewayChatPrivate + "/images/upload";

    // Chat Admin
    public static final String gatewayChatAdminPrivate_Ban = getGatewayCP() + "/internal/user/ban";
    public static final String gatewayChatAdminPrivate_Unban = getGatewayCP() + "/internal/user/unban";

    // New Chat
    public static final String newChatAPIHost = "https://ct-chat-api-dev.cmco.io/api/v1";
    public static final String gatewayInternalNewChat = newChatAPIHost + "/internal";
    public static final String gatewayPrivateNewChat = newChatAPIHost + "/private";
    // Internal
    public static final String gatewayInternalPutUserDataNewChat = gatewayInternalNewChat + "/users/%s"; //%s:userID
    public static final String gatewayInternalGetUserDataNewChat = gatewayInternalNewChat + "/users/%s"; //%s:userID
    public static final String gatewayInternalCreateChannelNewChat = gatewayInternalNewChat + "/channels";
    public static final String gatewayInternalGetUserChannelNewChat = gatewayInternalNewChat + "/user_channel/%s/channels?offset=0&limit=20"; //%s:userID
    public static final String gatewayInternalGetChannelInfoNewChat = gatewayInternalNewChat + "/user_channel/%s/%s"; //%s:userID, channelID
    public static final String gatewayInternalSendMessageNewChat = gatewayInternalNewChat + "/messages";
    public static final String gatewayInternalSetReadMessageNewChat = gatewayInternalNewChat + "/set_read";
    public static final String gatewayInternalGetUnreadCountNewChat = gatewayInternalNewChat + "/users/%s/unread_count";//%s:userID
    public static final String gatewayInternalInsertAdNewChat = gatewayInternalNewChat + "/ads";
    public static final String gatewayInternalGetTokenNewChat = gatewayInternalNewChat + "/tokens/new";
    // Private
    public static final String gatewayPrivateGetUserDataNewChat = gatewayPrivateNewChat + "/me"; //%s:userID
    public static final String gatewayPrivateCreateChannelNewChat = gatewayPrivateNewChat + "/channels";
    public static final String gatewayPrivateGetUserChannelNewChat = gatewayPrivateNewChat + "/channels";
    public static final String gatewayPrivateGetChannelInfoNewChat = gatewayPrivateNewChat + "/channels/%s"; //%s: channelID
    public static final String gatewayPrivateGetChatMessagesNewChat = gatewayPrivateNewChat + "/channels/%s/messages?limit=%s&order=%s"; //%s:channelID, limit, order
    public static final String gatewayPrivateGetChangesMessagesNewChat = gatewayPrivateNewChat + "/channels/%s/changes?limit=%s&order=%s"; //%s:channelID, limit, order
    public static final String gatewayPrivateSendMessageNewChat = gatewayPrivateNewChat + "/messages";
    public static final String gatewayPrivateGetUnreadCountNewChat = gatewayPrivateNewChat + "/unread_count";

    // Sendy
    public static final String gatewaySendy = "https://stg-sendy-api.cmco.io";
    public static final String gatewaySendySendEmailTemplate = gatewaySendy + "/api/sendmail";
    public static final String gatewaySendySendEmailCampaign = gatewaySendy + "/api/sendmail_campaign";
    public static final String gatewaySendyEmailValidation = gatewaySendy + "/api/validate/email/%s"; //%s: email address
    public static final String gatewaySendySendUploadImage = gatewaySendy + "/upload";
    public static final String gatewaySendySendDeleteImage = gatewaySendy + "/image/%s"; //%s: image name

    //PROMOTION
    public static final String gatewayPromotion_CreateCampaign = gatewayHostInternalV1 + "/promotion/campaigns";
    public static final String gatewayPromotion_GenerateCode = gatewayHostInternalV1 + "/promotion/campaign/genarate-code";
    public static final String gatewayPromotion_RedeemCode = gatewayHostInternalV1 + "/promotion/redeem";

    // Ad listing
    public static final String gatewayAdListing_GetListID = gatewayPublic + "/ad-listing";

    //SHOP GATEWAY: PTY, ELT, VEH (has to by pass payment)
    public static final String gatewayShopC2C = gatewayHost + "/v1/private/shops";
    public static final String gatewayShopC2CPublic = gatewayHost + "/v1/public/shops";
    public static final String gatewayShopC2CInternal = gatewayHost + "/v1/internal/shops";
    public static final String gatewayShopC2C_CreateTempShop = gatewayShopC2C + "/preview";
    public static final String gatewayShopC2C_GetTempShop = gatewayShopC2C + "/preview";
    public static final String gatewayShopC2C_UpdateTempShop = gatewayShopC2C + "/preview";
    public static final String gatewayShopC2C_UpdateTempShop_CoverPhoto = gatewayShopC2C + "/temp/cover";
    public static final String gatewayShopC2C_UpdateTempShop_AvatarPhoto = gatewayShopC2C + "/temp/avatar";
    public static final String gatewayShopC2C_TempShop_PayShop = gatewayHost + "/v1/private/payment/pay-order";
    public static final String gatewayShopC2C_UpdateNewShop = gatewayShopC2C + "/id/%s";
    public static final String gatewayShopC2C_CheckShop_ExistURL = gatewayShopC2CPublic + "/check/url/%s";
    public static final String gatewayShopC2C_CheckShop_ByURL = gatewayShopC2CPublic + "/url/%s";

    //PAID SHOP
    public static final String gatewayShop_CreateAvatar = gatewayHostPrivateV1 + "/shops/temp/avatar";
    public static final String gatewayShop_CreateCover = gatewayHostPrivateV1 + "/shops/temp/cover";
    public static final String gatewayShop_CreatePreview = gatewayHostPrivateV1 + "/shops/preview";
    public static final String gatewayShop_Status = gatewayHostPrivateV1 + "/shops";
    public static final String gatewayShop_ShopDashboard = gatewayHostPrivateV1 + "/theia?shop_alias=%s"; //%s: shop_alias
    public static final String gatewayShop_Extend = gatewayHostInternalV1 + "/shops/alias/%s/extend"; //%s: shop_alias
    public static final String gatewayShop_DeleteAd = gatewayHostPrivateV2 + "/cart/target/%s"; //%s: adID
    public static final String gatewayShop_Announce = gatewayHostInternalV1 + "/announcer/message";
    public static final String gatewayShop_AnnounceLink = gatewayHostInternalV1 + "/promotion/encrypt-code?phone=%s&code=%s"; //phone & code generated from promotion


    //C2C SHOP GATEWAY: FOOD
    public static final String gatewayShopC2C_Accept_Shop = gatewayHost + "/v1/internal/queues/accept";
    public static final String gatewayShopC2C_Alias_Shop = gatewayShopC2CInternal + "/alias/%s";                //%s: Shop Alias
    public static final String gatewayShopC2C_Reject_Shop = gatewayShopC2CInternal + "/alias/%s/reject";                //%s: Shop Alias
    public static final String gatewayShopC2C_Approve_Shop_ByAlias = gatewayShopC2CInternal + "/alias/%s/approve";      //%s: Shop Alias
    public static final String gatewayShopC2C_Reject_Shop_ByAlias = gatewayShopC2CInternal + "/alias/%s/reject";      //%s: Shop Alias
    public static final String gatewayShopC2C_Accept_EditName = gatewayShopC2CInternal + "/alias/%s/allowEditName";     //%s: Shop Alias
    public static final String gatewayShopC2C_Accept_EditUrl = gatewayShopC2CInternal + "/alias/%s/allowEditUrl";       //%s: Shop Alias
    public static final String gatewayShopC2C_User_CloseShop = gatewayShopC2C + "/id/%s/toggle";       //%s: Shop Alias
    public static final String gatewayShopC2C_CS_BlockShop = gatewayShopC2C_Alias_Shop + "/block";       //%s: Shop Alias
    public static final String gatewayShopC2C_CS_UnBlockShop = gatewayShopC2C_Alias_Shop + "/unblock";       //%s: Shop Alias
    public static final String gatewayShopC2C_CS_ExpireShop = gatewayShopC2C_Alias_Shop + "/expire";       //%s: Shop Alias
    public static final String gatewayShopC2C_CS_ExtentShop = gatewayShopC2C + "/extend/%s";       //%s: Shop Alias
    public static final String gatewayShopC2C_CS_ExtentShopFreeToken = gatewayShopC2CInternal + "/token-extend-free/%s/%s/%s";       //%s: Shop Alias
    public static final String gatewayShopC2C_CS_ExtentShopFreeTokenByJson = gatewayShopC2CInternal + "/token-extend-free";       //%s: Shop Alias

    public static final String gatewayShopC2C_Queue_GetShop = gatewayShopC2CInternal + "/queues/name/new?reviewer=5";
    public static final String gatewayShopC2C_Queue_SearchShop = gatewayShopC2CInternal + "/search?alias=%s";          //djnCnyY37jjC5m3
    public static final String gatewayShopC2C_Queue_MoveShops = gatewayShopC2CInternal + "/alias/%s/moveQueue";         //%s: Shop Alias
    public static final String gatewayShopC2C_Queue_GetShopCount = gatewayShopC2CInternal + "/queues/count";

    //PRIVATE DASHBOARD
    public static final String gatewayPrivateDashboardInternal = gatewayHost + "/v1/internal/user_ads";
    public static final String gatewayPrivateDashboard_UserAds_GetAdList =
            gatewayPrivateDashboardInternal + "/%s/list_ads/%s?is_latest=true";   //%s: user_account_id, ad_id

    //LISTING FEE
    public static final String gatewayPrivateListingFee = gatewayHost
//            + "/v1/private/theia?page=1&limit=100&count_by_cate=true&categories=5010,5020,5030,5040,5050,5060,5070,2030,2050,2060,2090";
            + "/v1/private/theia?page=1&limit=100";
    public static final String gatewayPrivateUserAds = gatewayHost +
            "/v1/private/user_ads/ad/%s";
    public static final String gatewayPublicUserAds = gatewayHostPubliclV1 +
            "/user_ads/ad/%s";
    public static final String gatewayInternalUserAds_Count = gatewayHostInternalV1 +
            "/user_ads/status/%s/count"; //accountId

    // HIERARCHY
    public static final String gatewayHierarchy_BizChild_Link = gatewayHostInternalV1 + "/maslow/accounts";
    public static final String gatewayHierarchy_Update_Child = gatewayHost + "/v1/internal/maslow/accounts/%s/update"; // account id
    @Deprecated
    public static final String gatewayHierarchy_InactiveChild = gatewayHost + "/v1/private/maslow/childs";
    public static final String gatewayHierarchy_BizAccountList = gatewayHostInternalV1 + "/maslow/accounts?type_list=parent";
    public static final String gatewayHierarchy_BizChild_ChildCheck = gatewayHostPrivateV1 + "/maslow/check";
    public static final String gatewayHierarchy_BizChild_CheckChilds = gatewayHostInternalV1 + "/maslow/accounts/%s/childs"; //parentAccID
    public static final String gatewayHierarchy_BizChild_GetBizID = gatewayHostInternalV1 + "/maslow/accounts/%s";
    public static final String gatewayHierarchy_BizChild_SetBudget = gatewayHostInternalV1 + "/maslow/budget";
    public static final String gatewayHierarchy_Biz_UpdateBudget = gatewayHostInternalV1 + "/maslow/accounts/%s/update";
    public static final String gatewayHierarchy_BizChild_GetBudgetByAccountIDOnly = gatewayHostInternalV1 + "/maslow/budget/%s";
    @Deprecated     // Use delete, stop using unlink
    public static final String gatewayHierarchy_BizChild_Unlink = gatewayHostInternalV1 + "/maslow/accounts/%s/childs/%s";
    public static final String gatewayHierarchy_BizOrderHistory = gatewayHostPrivateV1 + "/papi/list-biz-order";
    public static final String gatewayHierarchy_BizAccount_GetBizCost = gatewayHostPrivateV1 + "/papi/list-biz-cost?limit=20&offset=0&from=%s&to=%s&child_account_ids=%s"; // Filter time from, Filter Time to
    public static final String gatewayHierarchy_DeleteChild = gatewayHostPrivateV1 + "/maslow/childs/%s"; //childAccID
    public static final String gatewayHierarchy_AddChild = gatewayHostPrivateV1 + "/maslow/childs";
    // user register a Biz Account
    public static final String gatewayHierarchy_Register_CreateInfo = gatewayHost + "/v1/private/maslow/registers/infos";
    public static final String gatewayHierarchy_Register_UserGetRequestInfo = gatewayHost + "/v1/private/maslow/registers";
    public static final String gatewayHierarchy_Register_GetRequestInfo = gatewayHost + "/v1/internal/maslow/registers?limit=5&offset=0&page=1";
    public static final String gatewayHierarchy_Register_ApproveRequest = gatewayHost + "/v1/internal/maslow/registers/%s";     //requestId
    public static final String gatewayHierarchy_Register_RejectRequest = gatewayHost + "/v1/internal/maslow/registers/%s";     //requestId
    public static final String gatewayHierarchy_RemainBudget_GetAll = gatewayHost + "/v1/private/maslow/remain-budget";
    public static final String gatewayHierarchy_OrderHistoryOfBiz = gatewayHost + "/v1/private/papi/list-biz-order";
    public static final String getGatewayHierarchy_Debit_OrderHistory = gatewayHost + "/v1/private/credit/transfer-history";
    public static final String getGatewayHierarchy_Debit_OrderHistory_Details = gatewayHost + "/v1/private/credit/transfer-history/%s";  // order ID
    public static final String getGatewayHierarchy_Debit_OrderHistory_ReceiveChild = gatewayHost + "/v1/private/credit/receive-history?limit=20&offset=0";  // &type=dongtot
    // AH - Budget Limitation
    public static final String gatewayHierarchy_CurrentBudget = gatewayHostPrivateV1 + "/maslow/current-budget";
    public static final String gatewayHierarchy_UpdateStatus = gatewayHostInternalV1 + "/maslow/accounts/%s/update"; //accountID
    // AH - Debit
    public static final String gatewayHierarchy_Debit_TransferDT = gatewayHost + "/v1/private/credit/transfer";

    //PRICER:
    public static final String gatewayPricer_InternalV1 = gatewayHostInternalV1 + "/pricer";
    public static final String gatewayPricer_Private = gatewayHostPrivateV1 + "/pricer";
    public static final String gatewayPricer_PrivateV2 = gatewayHostPrivateV2 + "/pricer";
    public static final String gatewayPricer_PrivateV1 = gatewayHostPrivateV1 + "/pricer";
    public static final String gatewayPricer_Public = gatewayHostPubliclV1 + "/pricer";
    public static final String gatewayPricer_Internal_AdFeature_GetPrice = gatewayHostInternalV1
            + "/v1/internal/pricer/ad-features?category_id=%s&feature=%s&duration=%d"; //category_id = subcate,

    public static final String gatewayPricer_Public_GetAllPrice = gatewayPricer_Public + "/get-all";
    public static final String gatewayPricer_Public_Pricing_GetAllPrice = gatewayPricer_Public + "/pricing/get-all";
    public static final String gatewayPricer_StickyAds_Internal = gatewayPricer_InternalV1 + "/sticky-ads?region_id=%s&category_id=%s&duration=%s&stickyads=%s";
    public static final String gatewayPricer_Private_GetAllPrice_Bump = gatewayPricer_PrivateV1 + "/pricing/get-all";
//    public static final String gatewayPricer_Private_SinglePrice_Bump = gatewayPricer_PrivateV1 + "/pricing/single";
    public static final String gatewayPricer_Internal_AdFeature = gatewayPricer_InternalV1 + "/ad-features?category_id=%s&feature=%s&duration=%s";
    public static final String gatewayPricer_Bump_Private = gatewayPricer_Private + "/pricing/single?category_id=%s&bump_type=bump";
    public static final String gatewayPricer_Bump7Days_Private = gatewayPricer_Private + "/pricing/single?category_id=%s&bump_type=7days_bump";
    public static final String gatewayPricer_BumpTimer_Private = gatewayPricer_Private + "/pricing/single?category_id=%s&bump_type=timer_bump";
    public static final String gatewayPricer_Bump3Days_Private = gatewayPricer_Private + "/pricing/single?category_id=%s&bump_type=3days_bump";

    public static final String gatewayPricer_AdFeature_Private = gatewayPricer_PrivateV2 + "/services?region_id=13000&category_id=%s";
    public static final String gatewayPricer_ListingFee = gatewayPricer_PrivateV2 + "/services?category_id=%s&ad_type=s";   //region_id=%s&
    public static final String gatewayPricer_ListingFee_ByRegion = gatewayPricer_PrivateV2 + "/services?region_id=%s&category_id=%s&ad_type=s";
    @Deprecated
    public static final String gatewayPricer_ListingFee_SpecifiedUser = gatewayPricer_PrivateV2 + "/services?account_id=%s&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Service_SpecifiedUser = gatewayPricer_PrivateV2 + "/services?account_id=%s&region_id=%s&category_id=%s&ad_type=%s";

    public static final String gatewayPricer_ListingFee4Rent = gatewayPricer_PrivateV2 + "/services?region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_SpecifiedUser = gatewayPricer_PrivateV2 + "/services?account_id=%s&region_id=%s&category_id=%s&ad_type=u";

    public static final String gatewayPricer_ListingFee_Private_PricerInternal = gatewayHostInternalV1 + "/pricer?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Private_PricerPrivate = gatewayHostPrivateV1 + "/pricer?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Shop_PricerInternal = gatewayHostInternalV1 + "/pricer?account_id=%s&action_type=shop_to_chotot&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Shop_PricerPrivate = gatewayHostPrivateV1 + "/pricer?account_id=%s&action_type=shop_to_chotot&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee4Rent_Private_PricerInternal = gatewayHostInternalV1 + "/pricer?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_Private_PricerPrivate = gatewayHostPrivateV1 + "/pricer?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_Shop_PricerInternal = gatewayHostInternalV1 + "/pricer?account_id=%s&action_type=shop_to_chotot&region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_Shop_PricerPrivate = gatewayHostPrivateV1 + "/pricer?account_id=%s&action_type=shop_to_chotot&region_id=%s&category_id=%s&ad_type=u";

    public static final String gatewayPricer_ListingFee_Private_PricerGoldpotInternal = gatewayHostInternalV1 + "/pricer/goldpot?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Private_PricerGoldpotPrivate = gatewayHostPrivateV1 + "/pricer/goldpot?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Shop_PricerGoldpotInternal = gatewayHostInternalV1 + "/pricer/goldpot?account_id=%s&action_type=shop_to_chotot&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee_Shop_PricerGoldpotPrivate = gatewayHostPrivateV1 + "/pricer/goldpot?account_id=%s&action_type=shop_to_chotot&category_id=%s&ad_type=s";
    public static final String gatewayPricer_ListingFee4Rent_Private_PricerGoldpotInternal = gatewayHostInternalV1 + "/pricer?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_Private_PricerGoldpotPrivate = gatewayHostPrivateV1 + "/pricer?account_id=%s&action_type=insert_ad&region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_Shop_PricerGoldpotInternal = gatewayHostInternalV1 + "/pricer?account_id=%s&action_type=shop_to_chotot&region_id=%s&category_id=%s&ad_type=u";
    public static final String gatewayPricer_ListingFee4Rent_Shop_PricerGoldpotPrivate = gatewayHostPrivateV1 + "/pricer?account_id=%s&action_type=shop_to_chotot&region_id=%s&category_id=%s&ad_type=u";

    public static final String gatewayPricer_ListingFee_PricerGetAllListing_Internal = gatewayHostInternalV1 + "/pricer/get-all/listing-fee";
    public static final String gatewayPricer_ListingFee_PTY_PricerGetAllListing_Internal = gatewayHostInternalV1 + "/pricer/get-all/listing-fee-pty";
    public static final String gatewayPricer_ListingFee_PricerGetAll_Public = gatewayHostPubliclV1 + "/pricer/get-all";


    public static final String gatewayPricer_Shop = gatewayPricer_Public + "/shops/get-all";
    public static final String gatewayPricer_Shop_Private = gatewayPricer_Private + "/shops/get-all";
    public static final String gatewayStickyAd_GetPrice_Internal = gatewayPricer_InternalV1 + "/sticky-ads?category_id=%s&duration=%s&region_id=%s";
    public static final String gatewayPricer_ShopPackage_Create = gatewayPricer_InternalV1 + "/shops/get-package?account_id=%s&category_id=%s&action_type=shop_create&duration=%s";
    public static final String gatewayPricer_ShopPackage_Extend = gatewayPricer_InternalV1 + "/shops/get-package?account_id=%s&category_id=%s&action_type=shop_extend&duration=%s";


    public static final String gatewayPricer_SpecialDisplay_ListPosService = gatewayPricer_PrivateV2 + "/services?region_id=%s&category_id=%s&ad_id=%s"; // region_id, category_id, ad_id
    public static final String gatewayPricer_SpecialDisplay_Price = gatewayPricer_InternalV1 + "/special_display?category_id=%s&duration=%s&region_id=%s"; // region_id, duration, region_id


    //REWARD
    public static final String gatewayReward_InternalV1 = gatewayHostInternalV1 + "/rewards";
    public static final String gatewayReward_PrivateV1 = gatewayHostPrivateV1 + "/rewards";
    public static final String gatewayReward_AddPoint_Internal = gatewayReward_InternalV1 + "/add_point";
    public static final String gatewayReward_GetPoint_Private = gatewayReward_PrivateV1 + "/self";
    public static final String gatewayReward_GenerateCode = gatewayHostInternalV1 + "/promotion/diem-tot/codes";
    public static final String gatewayReward_RedeemCodeToDT = gatewayHostPrivateV1 + "/promotion/redeem";


    //SHOP

    public static final String gatewayShopPublicV1 = gatewayHostPubliclV1 + "/shops";
    public static final String gatewayShop_GetShopCate = gatewayShopPublicV1 + "/shop-info";
    public static final String gatewayShop_Expire = gatewayHostInternalV1 + "/shops/alias/%s/expire";       //%s: Shop Alias

    //Notification - Inbox
    public static final String gatewayInbox = gatewayHostPrivateV1 + "/notification/inbox";
    public static final String gatewayInbox_GetMessage = gatewayInbox + "/user?offset=0&limit=20";


    //POS
    public static final String gatewayPOS_AddToCard = gatewayHost + gatewayCartPrivate + "/service";
    public static final String gatewayPOS_AdFeature_getAds = gatewayHostInternalV1 + "/ad-features/get-ads?offset=0&limit=100&timestamp=";    //+ System.currentTimeMillis()

    //Credit
    public static final String gatewayCredit_CheckBalance = gatewayHostPrivateV1 + "/credit/balance";
    public static final String gatewayCredit_CheckBalanceDetail = gatewayHostPrivateV1 + "/credit/balancedetail";
    public static final String gatewayCredit_CheckHistory = gatewayHostPrivateV1 + "/history?platform=global&type=paid&orderType=topup";
    public static final String gatewayCredit_GetBalanceDetail = gatewayHost + "/v1/private/credit/balancedetail-by-group";

    //ORDER HISTORY
    public static final String gatewayOrderHistory_ListPaid = gatewayHostPrivateV1 + "/papi/get-list-paid-order?offset=0&limit=100";
    public static final String gatewayOrderHistory_ListPaid_DongTot = gatewayHostPrivateV1 + "/papi/get-list-paid-order?offset=2&limit=100&type=dongtot";
    public static final String gatewayOrderHistory_GetDetail = gatewayHostInternalV1 + "/order-history/order/detail/%s/full?show_invoice=true";

    //GENERAL
    public static final String gatewayCategories_GetAllSubCate = gatewayHostPubliclV5 + "/chapy-pro/categories";
    public static final String gatewayRegion_GetAll = gatewayHostPubliclV2 + "/chapy-pro/regions";

    //GRINGOTTS
    public static final String gatewayBankLoan_Calculate = gatewayHostInternalV1 + "/gringotts/ppmt";

    //PROMOTION
    public static final String gatewayPromotion_CreateGroup = gatewayHostInternalV1 + "/sniper/user_groups";
    public static final String gatewayPromotion_AddGroupCampaign = gatewayHostInternalV1 + "/zexal/campaigns";
    public static final String gatewayPromotion_CheckCampaign_FreeListing = gatewayHostInternalV1 + "/zexal/campaign/%s/listing_campaign?category_id=%s";

    //USER PROFILER
    public static final String gatewayUserProfiler_UpdateProfiler = gatewayHostInternalV1 + "/profiler/%s";     //account_id
    public static final String gatewayUserProfiler = gatewayHostInternalV1 + "/profiler/%s";     //account_id
    @Deprecated
    public static final String gatewayUserProfilerPublic = gatewayHostPubliclV1 + "/profiler/%s?cat=%s&ad_type=%s&get_next=true";     //account_id, cateId, adType
    public static final String gatewayUserProfilerPublic_Cate = gatewayHostPubliclV1 + "/profiler/%s?cat=%s";     //account_id, cateId, adType
    public static final String gatewayUserProfiler_Get_InternalProfiler = gatewayHost + "/v1/internal/profiler/%s";     //account_id
    public static final String gatewayUserProfiler_Post_PrivateProfiler = gatewayHost + "/v1/private/profiler";     //account_id
    public static final String gatewayUserProfiler_Get_InternalProfilerPost = gatewayHost + "/v1/internal/profiler";     //account_id
    public static final String gatewayUserProfiler_Get_PublicProfiler = gatewayHost + "/v1/public/profiler/%s?cat_id=%s&ad_type=%s&get_next=true";     //account_id
    public static final String gatewayUserProfiler_Get_PublicCheckProfiler = gatewayHost + "/v1/public/profiler/check-profiler/%s?cat_id=%s&ad_type=%s";     //account_id
    public static final String gatewayUserProfiler_Reason_Create = gatewayHost + "/v1/internal/reason";
    public static final String gatewayUserProfiler_Reason_Update = gatewayHost + "/v1/internal/reason/%s";
    public static final String gatewayUserProfiler_Reason_GetByName = gatewayHost + "/v1/public/reason/%s";
    public static final String gatewayUserProfiler_Reason_GetAll = gatewayHost + "/v1/public/reason";

    //CUSTOMER | USER_GROUP
//    public static final String gatewayUserGroup_Create = gatewayHost + "/v1/internal/user_groups";

    public static final String gatewayUserGroup_Create = gatewayHost + "/v1/internal/user_groups";
    public static final String gatewayUserGroup_Update = gatewayHost + "/v1/internal/user_groups";
    public static final String gatewayUserGroup_Check = gatewayHost + "/v1/internal/user_groups/check?group_id=%s&account_id=%s"; // group_id, account_id

    //CUSTOMER | FREE PREMIUM | FREE BUMP
    public static final String gatewayFreePremium_AddCampaign_FreeBump = gatewayHost + "/v1/internal/zexal/campaigns";
    public static final String gatewayFreePremium_AddRedeem_FreeBump = gatewayHost + "/v1/internal/zexal/redeems/%s"; // campaign_id
    public static final String gatewayFreePremium_UpdateStatusCampaign_FreeBump = gatewayHost + "/v1/internal/zexal/campaigns/%s/status"; // campaign_id
    public static final String gatewayFreePremium_RedeemEndUser_FreeBump = gatewayHost + "/v1/public/zexal/redeems/%s"; // campaign_id
    public static final String gatewayFreePremium_GetCampaignInfo_FreeBump = gatewayHost + "/v1/internal/zexal/redeems?campaign_id=%s"; // campaign_id
    public static final String gatewayFreePremium_UpdateCampaign_FreeBump = gatewayHost + "/v1/internal/zexal/campaigns/%s"; // campaign_id
    public static final String gatewayFreePremium_AnnouncerWeb = gatewayHost + "/v1/private/announcer/messages?page=private_dashboard&platform=oneweb";
    public static final String gatewayFreePremium_CloseAnnouncerWeb = gatewayHost + "/v1/private/announcer/messages?page=private_dashboard&platform=oneweb&previous_message=%s"; // _id: announcement box id
    public static final String gatewayAnnouncer_Internal_GetMessages = gatewayHost + "/v1/internal/message?search=Thông tin";

    //ESCROW
    public static final String gatewayEscrow_Linking = gatewayHost + "/v1/private/profile/payoo";
    public static final String gatewayEscrow_UnLinking = gatewayHost + "/v1/private/profile/unlink";
    public static final String gatewayEscrow_Buyer_RequestMessage = gatewayHost + "/v1/private/escrow/buyer/order/request";
    public static final String gatewayEscrow_Seller_CreateOrder = gatewayHost + "/v1/private/escrow/seller/order";
    public static final String gatewayEscrow_Buyer_ConfirmOrderOfSeller = gatewayHost + "/v1/private/escrow/buyer/order/confirmation";
    public static final String gatewayEscrow_Buyer_CheckoutOrderOfSeller = gatewayHost + "/v1/private/escrow/buyer/order/checkout";
    public static final String gatewayEscrow_Seller_ConfirmDelivery = gatewayHost + "/v1/private/escrow/seller/order/confirmation";
    public static final String gatewayEscrow_Buyer_ConfirmReceiveProduct = gatewayHost + "/v1/private/escrow/buyer/order/confirmation";
    public static final String gatewayEscrow_Order_SellingList = gatewayHost + "/v1/private/escrow/seller/orders";
    public static final String gatewayEscrow_Order_BuyingList = gatewayHost + "/v1/private/escrow/buyer/orders";
    public static final String gatewayEscrow_Order_Status = gatewayHost + "/v1/private/escrow/orders/ongoing?buyer_id=%s&listing_id=%s";
    public static final String gatewayEscrow_Dispute = gatewayHost + "/v1/private/escrow/%s/order/dispute"; // buyer or seller
    public static final String gatewayEscrow_Get_Order = gatewayHost + "v1/private/escrow/orders/%s"; // Order number
    public static final String gatewayEscrow_Cancel_Order = gatewayHost + "/v1/private/escrow/seller/order/cancel";


    //CUSTOMER | PROMOTION NEW FLOW
    public static final String gatewayCrePromotion_Internal_AddCampaign_Promo = gatewayHostInternalV1 + "/promotion-credit/campaigns/add";
    public static final String gatewayCrePromotion_Internal_UpdateStatusCampaign_Promo = gatewayHostInternalV1 + "/promotion-credit/campaigns/%s/status"; //campaign_id
    public static final String gatewayCrePromotion_Internal_GetCode_Promo = gatewayHostInternalV1 + "/promotion-credit/codes?limit=20&latest_id=0";
    public static final String gatewayCrePromotion_Internal_Redeem_Promo = gatewayHostInternalV1 + "/promotion-credit/redeem";
    public static final String gatewayCrePromotion_Internal_GetCampaignByID_Promo = gatewayHostInternalV1 + "/promotion-credit/campaigns/%s"; //campaign_id
    public static final String gatewayCrePromotion_Private_Redeem_Promo = gatewayHostPrivateV1 + "/promotion-credit/redeem";
    public static final String gatewayCrePromotion_Private_CreditBalance_Promo = gatewayHostPrivateV1 + "/credit/balance";
    public static final String gatewayCrePromotion_Internal_EditCampaign_Promo = gatewayHostInternalV1 + "/promotion-credit/campaigns/%s/info";  //campaign_id
    public static final String gatewayCrePromotion_Internal_GetAllHistory_Promo = gatewayHostInternalV1 + "/promotion-credit/history";
    public static final String gatewayCrePromotion_Internal_AutoRedeem_Promo = gatewayHostInternalV1 + "/promotion-credit/campaigns/%s/auto-redeem";  //campaign_id


    //AD-STAT V1
    public static final String gatewayAdStat_V1 = gatewayHostPrivateV1 + "/papi/ad-stat/%s";  //adID
    //AD-STAT V2
    public static final String gatewayAdStat_V2 = gatewayHostPrivateV1 + "/papi/ad-stat-v2/%s";  //adID


    public static String getGatewayPayOrderBodyIP() {
        switch (DOMAIN) {
            case "org":
            default:
                return "192.168.1.156";
        }
    }

    public static String getGatewayCPRemoteAddress() {
        switch (DOMAIN) {
            case "org":
            default:
                return "127.0.0.1";
        }
    }

    public static String getGatewayCPHost() {
        switch (DOMAIN) {
            case "org":
            default:
                return "http://10.60.3.47:5656";
        }
    }

    public static String getGatewayCP() {
        switch (DOMAIN) {
            case "org":
            default:
                return "http://10.60.3.66:30034";
        }
    }

    public static String getGatewaySendy() {
        switch (DOMAIN) {
            case "org":
            default:
                return "http://10.60.3.64:30022";
        }
    }
}

