package projects.functions.chotot.pos;

import com.jayway.jsonpath.PathNotFoundException;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import org.testng.Assert;
import projects.functions.APISupportFunctions;

import static api.configuration.GatewayConfig.*;

public class POS_Functions extends APISupportFunctions {
    public static final int tryToSubmitPOS = 5;
    public static int posPricePaid = -1;

    private static int priceSDA = 0;
    private static int durationSDA = 0;

    public static int getPriceSDA() {
        return priceSDA;
    }

    public static void setPriceSDA(int priceSDA) {
        POS_Functions.priceSDA = priceSDA;
    }

    public static int getDurationSDA() {
        return durationSDA;
    }

    public static void setDurationSDA(int durationSDA) {
        POS_Functions.durationSDA = durationSDA;
    }

    public static void posBump(String adID) {
        posBump(global_accessToken, adID);
    }

    public static void posBump(String ownerToken, String adID) {
        posBumpCore(ownerToken, adID, 1, "bump", true);
    }

    public static void posBump3Days(String ownerToken, String adID) {
        posBumpCore(ownerToken, adID, 1, "3days_bump", true);
    }

    public static void posBump7Days(String ownerToken, String adID) {
        posBumpCore(ownerToken, adID, 1, "7days_bump", true);
    }

    public static void posBumpCore(String ownerToken, String adID, int duration, String bumpType, boolean isNewAd) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"" + bumpType + "\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": \"" + duration + "\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUMP TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                //Verify Ad is pushed into Cart successfully
                Assert.assertNotNull(getResponseData(response, "$.success[0].target"), "ORDER ISN'T IN CART YET");

                String error = getResponseData(response, "$.fail[0].error");        //VUHOANG REVIEW
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            } catch (PathNotFoundException notExistError){
                break;
            }
        }
        debugResponse();
        responseTemp1 = response; //response of POS service cart
        verifyServiceInCart(ownerToken, adID, bumpType);  //This changes response object
        Assert.assertTrue(getResponseData(responseTemp1, "$.success[0].target").equalsIgnoreCase(adID), "Bumped wrong ad");
        Assert.assertTrue(getResponseData(responseTemp1, "$.success[0].type").equalsIgnoreCase(bumpType), "POS Type isn't Bump");
    }


    public static void posBumpTimer(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"timer_bump\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": 8,\n" +
                "        \"block_times\": [\n" +
                "          {\n" +
                "            \"start\": 6,\n" +
                "            \"end\": 7\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUMP TIMER TO CART");
                Assert.assertTrue(getResponseDataList(response, "$.success").size() > 0, "BUYING BUMP TIMER ISN'T SUCCESSFUL");
                break;
            } catch (AssertionError assertionError) {
                waitForServerAddingOrderToCart();
                i++;
            }
        }
    }

    public static String posBump_NotDevivery(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"bump\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": 1,\n" +
                "        \"start_time\": \"1999935913495\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUMP TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
        Assert.assertTrue(getResponseData(response, "$.success[0].target").equalsIgnoreCase(adID), "Bumped wrong ad");
        Assert.assertTrue(getResponseData(response, "$.success[0].type").equalsIgnoreCase("bump"), "POS Type isn't Bump");

        String order_id = getResponseData(response, "$.order_info.service.order_id");
        System.out.println("Order ID: " + order_id);
        return order_id;
    }

    public static String posBump3Days_NotDevivery(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"3days_bump\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": 1,\n" +
                "        \"start_time\": \"1999935913495\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUMP 3 DAYS TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
        Assert.assertTrue(getResponseData(response, "$.success[0].target").equalsIgnoreCase(adID), "Bumped wrong ad");
        Assert.assertTrue(getResponseData(response, "$.success[0].type").equalsIgnoreCase("bump"), "POS Type isn't Bump");

        String order_id = getResponseData(response, "$.order_info.service.order_id");
        System.out.println("Order ID: " + order_id);
        return order_id;
    }

    public static String posBump7Days_NotDevivery(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"7days_bump\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": 1,\n" +
                "        \"start_time\": \"1999935913495\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUMP 7 DAYS TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
        Assert.assertTrue(getResponseData(response, "$.success[0].target").equalsIgnoreCase(adID), "Bumped wrong ad");
        Assert.assertTrue(getResponseData(response, "$.success[0].type").equalsIgnoreCase("bump"), "POS Type isn't Bump");

        String order_id = getResponseData(response, "$.order_info.service.order_id");
        System.out.println("Order ID: " + order_id);
        return order_id;
    }

    public static void posBumpTimer_NotDevivery(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"timer_bump\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": 8,\n" +
                "        \"start_time\": \"1999935913495\",\n" +
                "        \"block_times\": [\n" +
                "          {\n" +
                "            \"start\": 6,\n" +
                "            \"end\": 7\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUMP TIMER TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
    }


    public static void posStickyAd(String ownerToken, String adID) {
        posStickyAd(ownerToken, adID, 1);       //1 3 5 7
    }

    public static void posStickyAd(String ownerToken, String adID, int duration) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"sticky_ad\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": \"" + duration + "\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD STICKY AD TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
        waitForServerAddingOrderToCart();
        debugResponse();
    }

    public static void posStickyAd_NotDelivery(String adID) {
        posStickyAd_NotDelivery(global_accessToken, adID, 1);
    }

    public static void posStickyAd_NotDelivery(String adID, int duration) {
        posStickyAd_NotDelivery(global_accessToken, adID, duration);
    }

    public static void posStickyAd_NotDelivery(String token, String adID) {
        posStickyAd_NotDelivery(token, adID, 1);
    }

    public static void posStickyAd_NotDelivery(String ownerToken, String adID, int duration) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"sticky_ad\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": \"" + duration + "\",\n" +
                "        \"start_time\": \"1999935913495\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD STICKY AD TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
    }


    // VUHOANG: (price), ribbon, title
    public static void posAdFeatureRibbon(String ownerToken, String adID) {
        posAdFeature(ownerToken, adID, "ribbon");
    }

    public static void posAdFeatureRibbon(String ownerToken, String adID, String adFeatureID) {
        posAdFeature(ownerToken, adID, "ribbon", adFeatureID);
    }

    public static void posAdFeaturePrice(String ownerToken, String adID) {
        posAdFeature(ownerToken, adID, "price");
    }

    public static void posAdFeaturePrice(String ownerToken, String adID, String adFeatureID) {
        posAdFeature(ownerToken, adID, "price", adFeatureID);
    }

    public static void posAdFeatureTitle(String ownerToken, String adID) {
        posAdFeature(ownerToken, adID, "title");
    }

    public static void posAdFeatureTitle(String ownerToken, String adID, String adFeatureID) {
        posAdFeature(ownerToken, adID, "title", adFeatureID);
    }

    public static void posAdFeature(String ownerToken, String adID, String adFeatureType) {
        posAdFeature(ownerToken, adID, adFeatureType, "9");
    }

    public static void posAdFeature(String ownerToken, String adID, String adFeatureType, String adFeatureID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"ad_feature\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": \"7\",\n" +
                "        \"feature\": \"" + adFeatureType + "\",\n" +
                "        \"feature_id\": " + adFeatureID + "\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                debugResponse();
                verifyStatusCode200("API FAILED: CAN'T ADD AD FEATURES TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
        Assert.assertNotNull(getResponseDataList(response, "$.success"));
    }

    public static void posAdFeatureRibbon_NotDelivery(String ownerToken, String adID) {
        posAdFeature_NotDelivery(ownerToken, adID, "ribbon");
    }

    public static void posAdFeaturePrice_NotDelivery(String ownerToken, String adID) {
        posAdFeature_NotDelivery(ownerToken, adID, "price");
    }

    public static void posAdFeatureTitle_NotDelivery(String ownerToken, String adID) {
        posAdFeature_NotDelivery(ownerToken, adID, "title");
    }

    private static void posAdFeature_NotDelivery(String ownerToken, String adID, String adFeatureType) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"ad_feature\",\n" +
                "      \"params\": {\n" +
                "        \"duration\": \"7\",\n" +
                "        \"feature\": \"" + adFeatureType + "\",\n" +
                "        \"start_time\": \"1999935913495\",\n" +
                "        \"feature_id\": 15\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD AD FEATURES TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
    }


    public static void posBundle(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"bundle\",\n" +
                "      \"params\": {\n" +
                "        \"ad_id\": " + adID + ",\n" +
                "        \"area\": 13096,\n" +
                "        \"bundle_id\": 1,\n" +
                "        \"category_id\": 1020,\n" +
                "        \"region_id\": 13000,\n" +
                "        \"segment_id\": \"all\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUNDLE TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
    }

    public static void posBundle_NotDelivery(String ownerToken, String adID) {
        bodyString = "{\n" +
                "  \"services\": [\n" +
                "    {\n" +
                "      \"target\": " + adID + ",\n" +
                "      \"type\": \"bundle\",\n" +
                "      \"params\": {\n" +
                "        \"start_time\": \"1999935913495\",\n" +
                "        \"ad_id\": " + adID + ",\n" +
                "        \"area\": 13096,\n" +
                "        \"bundle_id\": 1,\n" +
                "        \"category_id\": 1020,\n" +
                "        \"region_id\": 13000,\n" +
                "        \"segment_id\": \"all\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        int i = 0;
        while (i < tryToSubmitPOS) {
            try {
                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
                verifyStatusCode200("API FAILED: CAN'T ADD BUNDLE TO CART");
                Assert.assertNotNull(getResponseDataList(response, "$.success"));
                String error = getResponseData(response, "$.fail[0].error");
                Assert.assertNull(error, "POS Feature Ad failed: " + error);
                break;
            } catch (AssertionError assertionError) {
                i++;
                waitConstant(3);
            }
        }
    }

    private static void waitForServerAddingOrderToCart() {
        waitConstant(5);
    }


    private static void verifyServiceInCart(String ownerToken, String adID, String posServiceName) {
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, gatewayCart_GetInfo);
                verifyStatusCode200("API CHECK CART INFO IS DEAD");
                query = "$.services[?(@.target == " + adID + " && @.type == '" + posServiceName + "')].price";
                posPricePaid = getResponseDataListInt(query).get(0);
                Assert.assertTrue(posPricePaid > 0);
                break;
            } catch (AssertionError | IndexOutOfBoundsException assertionError) {
                System.out.println("ERROR: " + assertionError);
                waitConstant(3);
            }
        }
        debugResponse();
        Assert.assertTrue(posPricePaid > 0, "BUMP ORDER DOESN'T CONTAIN IN CART");
    }

    //OLD

//    public static void posBump3Days(String ownerToken, String adID) {
//        bodyString = "{\n" +
//                "  \"services\": [\n" +
//                "    {\n" +
//                "      \"target\": " + adID + ",\n" +
//                "      \"type\": \"3days_bump\",\n" +
//                "      \"params\": {\n" +
//                "        \"duration\": \"1\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        int i = 0;
//        while (i < tryToSubmitPOS) {
//            try {
//                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
//                verifyStatusCode200();
//                Assert.assertNotNull(getResponseDataList(response, "$.success"));
//                String error = getResponseData(response, "$.fail[0].error");
//                Assert.assertNull(error, "POS Feature Ad failed: " + error);
//
//                Assert.assertNotNull(getResponseData(response, "$.success[0].target"), "ORDER ISN'T IN CART YET");
//                verifyServiceInCart(ownerToken, adID, "3days_bump");
//                break;
//            } catch (AssertionError assertionError) {
//                i++;
//                waitConstant(3);
//            }
//        }
//        debugResponse();
//        Assert.assertNotNull(getResponseData(response, "$.success[0].target"), "ORDER ISN'T IN CART YET");
//        Assert.assertTrue(getResponseData(response, "$.success[0].target").equalsIgnoreCase(adID), "Bumped wrong ad");
//        Assert.assertTrue(getResponseData(response, "$.success[0].type").equalsIgnoreCase("3days_bump"), "POS Type isn't Bump");
//        verifyServiceInCart(ownerToken, adID, "bump");
//    }

//    public static void posBump7Days(String ownerToken, String adID) {
//        bodyString = "{\n" +
//                "  \"services\": [\n" +
//                "    {\n" +
//                "      \"target\": " + adID + ",\n" +
//                "      \"type\": \"7days_bump\",\n" +
//                "      \"params\": {\n" +
//                "        \"duration\": \"1\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";
//        int i = 0;
//        while (i < tryToSubmitPOS) {
//            try {
//                response = post(ownerToken, bodyString, gatewayPOS_AddToCard);
//                verifyStatusCode200();
//                Assert.assertNotNull(getResponseDataList(response, "$.success"));
//                String error = getResponseData(response, "$.fail[0].error");
//                Assert.assertNull(error, "POS Feature Ad failed: " + error);
//                break;
//            } catch (AssertionError assertionError) {
//                i++;
//                waitConstant(3);
//            }
//        }
//        debugResponse();
//        Assert.assertTrue(getResponseData(response, "$.success[0].target").equalsIgnoreCase(adID), "Bumped wrong ad");
//        Assert.assertTrue(getResponseData(response, "$.success[0].type").equalsIgnoreCase("7days_bump"), "POS Type isn't Bump");
//    }

    //QUANGTRAN - SPECIAL DISPLAY ADS

    protected static void posSpecialDisplay(String ownerToken, String region_id, String category_id, String ad_id, int priceSDA, int durationSDA) {
        url = String.format(gatewayPricer_SpecialDisplay_ListPosService, region_id, category_id, ad_id);
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, url);
                verifyStatusCode200("PRICER", url);
                Assert.assertNotNull(getResponseData("$.special_display"), "SPECIAL DISPLAY IS NOT DISPLAYED FOR THIS AD_ID: " + ad_id);
                Assert.assertEquals(String.valueOf(getResponseDataInt("$.special_display[*].price.vnd")), String.valueOf(priceSDA), "PRICE VND IS WRONG");
                Assert.assertEquals(String.valueOf(getResponseDataInt("$.special_display[*].price.credit")), String.valueOf(priceSDA), "PRICE CREDIT IS WRONG");
                Assert.assertEquals(String.valueOf(getResponseDataInt("$.special_display[*].duration")), String.valueOf(durationSDA), "DURATION IS WRONG");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PRICER", url);
        Assert.assertNotNull(getResponseData("$.special_display"), "SPECIAL DISPLAY IS NOT DISPLAYED FOR THIS AD_ID: " + ad_id);
        Assert.assertEquals(String.valueOf(getResponseDataInt("$.special_display[*].price.vnd")), String.valueOf(priceSDA), "PRICE VND IS WRONG");
        Assert.assertEquals(String.valueOf(getResponseDataInt("$.special_display[*].price.credit")), String.valueOf(priceSDA), "PRICE CREDIT IS WRONG");
        Assert.assertEquals(String.valueOf(getResponseDataInt("$.special_display[*].duration")), String.valueOf(durationSDA), "DURATION IS WRONG");
    }

    protected static void posSpecialDisplay_Price(String cateID, String duration, String region_id, String priceSDA) {
        url = String.format(gatewayPricer_SpecialDisplay_Price, cateID, duration, region_id);
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(url);
                verifyStatusCode200("PRICER", url);
                Assert.assertNotNull(getResponseData("$.price"), "CAN NOT GET PRICE OF SPECIAL DISPLAY");
                Assert.assertEquals(String.valueOf(getResponseDataInt("$.price.vnd")), priceSDA, "PRICE VND IS WRONG");
                Assert.assertEquals(String.valueOf(getResponseData("$.price.credit")), priceSDA, "PRICE CREDIT IS WRONG");
                Assert.assertEquals(String.valueOf(getResponseData("$.duration")), duration, "DURATION IS WRONG");
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PRICER", url);
        Assert.assertNotNull(getResponseData("$.price"), "CAN NOT GET PRICE OF SPECIAL DISPLAY");
        Assert.assertEquals(String.valueOf(getResponseDataInt("$.price.vnd")), priceSDA, "PRICE VND IS WRONG");
        Assert.assertEquals(String.valueOf(getResponseData("$.price.credit")), priceSDA, "PRICE CREDIT IS WRONG");
        Assert.assertEquals(String.valueOf(getResponseData("$.duration")), duration, "DURATION IS WRONG");
        setPriceSDA(getResponseDataInt("$.price.credit"));
        setDurationSDA(getResponseDataInt("$.duration"));
    }

    protected static void posSpecialDisplay_NotDisplay(String ownerToken, String region_id, String category_id, String ad_id) {
        url = String.format(gatewayPricer_SpecialDisplay_ListPosService, region_id, category_id, ad_id);
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, url);
                verifyStatusCode200("PRICER", url);
                Assert.assertNull(getResponseData("$.special_display"), "SPECIAL DISPLAY IS DISPLAYED FOR THIS AD_ID: " + ad_id);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("PRICER", url);
        Assert.assertNull(getResponseData("$.special_display"), "SPECIAL DISPLAY IS DISPLAYED FOR THIS AD_ID: " + ad_id);
    }

    public static void posSpecialDisplay_AddCart(String ownerToken, String adID, int duration) {
        initBodyJson();

        bodyJson.addProperty("target", adID);
        bodyJson.addProperty("type", "special_display");

        bodyChildJson = new JsonObject();
        bodyChildJson.addProperty("duration", duration);
        bodyJson.add("params", bodyChildJson);

        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = post(ownerToken, bodyJson, gatewayPOS_AddToCard);
                verifyStatusCode200(response, "ADD TO CART", gatewayPOS_AddToCard);
                Assert.assertEquals(getResponseData("$.message"), "Cập nhật giỏ hàng thành công");
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200(response, "ADD TO CART", gatewayPOS_AddToCard);
        Assert.assertEquals(getResponseData("$.message"), "Cập nhật giỏ hàng thành công");
        responseTemp1 = response;
        verifyServiceInCart(ownerToken, adID, "special_display");
        verifyStatusCode200(response, "CART INFO", gatewayCart_GetInfo);
        Assert.assertEquals(getResponseData("$..target"), adID);
        Assert.assertEquals(getResponseData("$..type"), "special_display");
    }

    protected static void posSpecialDisplay_CheckOrderHistory(String ownerToken, String adID, int expectedPrice) {
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
                Assert.assertEquals(getResponseData("$.[0].service_details[0].type"),"special_display");
                Assert.assertEquals(getResponseData("$.[0].service_details[0].ad_id"),adID);
                Assert.assertEquals(getResponseDataInt("$.[0].service_details[0].price"), expectedPrice);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
        //Verify Service Detail
        Assert.assertEquals(getResponseData("$.[0].service_details[0].type"),"special_display");
        Assert.assertEquals(getResponseData("$.[0].service_details[0].ad_id"),adID);
        Assert.assertEquals(getResponseDataInt("$.[0].service_details[0].price"), expectedPrice);
    }

    protected static void posSpecialDisplay_CheckStatistics(String ownerToken, String adID) {
        url = String.format(gatewayAdStat_V1, adID);
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, url);
                verifyStatusCode200("AD-STAT", url);
                Assert.assertNotNull(getResponseData("$.special_displays"), "STATISTICS OF SPECIAL DISPLAY SERVICE IS NULL");
                Assert.assertEquals(getResponseData("$.ad_info.ad_id"),adID);
                break;
            } catch (AssertionError error) {
                waitConstant(2);
            }
        }
        debugResponse();
        verifyStatusCode200("AD-STAT", url);
        Assert.assertNotNull(getResponseData("$.special_displays"), "STATISTICS OF SPECIAL DISPLAY SERVICE IS NULL");
        Assert.assertEquals(getResponseData("$.ad_info.ad_id"),adID);
    }

    protected static void posSpecialDisplay_CheckBalanceAfterRefund(String ownerToken, String credit) {
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, gatewayCrePromotion_Private_CreditBalance_Promo);
                verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
                Assert.assertEquals(getResponseData("$..type"), "paid");
                Assert.assertEquals(getResponseData("$..balance"), credit);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("GET CREDIT AFTER REDEEM", gatewayCrePromotion_Private_CreditBalance_Promo);
        Assert.assertEquals(getResponseData("$..type"), "paid");
        Assert.assertEquals(getResponseData("$..balance"), credit);
    }

    protected static void posSpecialDisplay_CheckRefundOrderHistory(String ownerToken, int expectedPrice) {
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
                Assert.assertEquals(getResponseData("$.[?(@.payment_method == 'compensation')].payment_method"),"compensation");
                Assert.assertEquals(getResponseDataInt("$.[?(@.payment_method == 'compensation')].price"), expectedPrice);
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
        Assert.assertEquals(getResponseData("$.[?(@.payment_method == 'compensation')].payment_method"),"compensation");
        Assert.assertEquals(getResponseDataInt("$.[?(@.payment_method == 'compensation')].price"), expectedPrice);
    }

    protected static void posSpecialDisplay_CheckOrderBumpWithDSA(String ownerToken) {
        for (int i = 0; i < tryToSubmitPOS; i++) {
            try {
                response = get(ownerToken, gatewayOrderHistory_ListPaid);
                verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
                Assert.assertNotNull(getResponseData("$.[0].service_details"), "SERVICE DETAILS IS NULL");
                Assert.assertTrue(getResponseDataList(response, "$.[0].service_details[*].type").contains("bump"));
                Assert.assertTrue(getResponseDataList(response, "$.[0].service_details[*].type").contains("special_display"));
                break;
            } catch (AssertionError error) {
                waitConstant(3);
            }
        }
        debugResponse();
        verifyStatusCode200("ORDER HISTORY", gatewayOrderHistory_ListPaid);
        Assert.assertNotNull(getResponseData("$.[0].service_details"), "SERVICE DETAILS IS NULL");
        Assert.assertTrue(getResponseDataList(response, "$.[0].service_details[*].type").contains("bump"));
        Assert.assertTrue(getResponseDataList(response, "$.[0].service_details[*].type").contains("special_display"));
    }
}
