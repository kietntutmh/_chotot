package projects.functions.chotot.ads.listing;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.testng.Assert;
import projects.functions.APISupportFunctions;
import projects.functions.PSQLSupportFunctions;

import java.sql.SQLException;
import java.util.List;

import static api.configuration.GatewayConfig.gatewayHost;
import static api.configuration.GatewayConfig.gatewayStickyAdsPublicV2;

public class AdListing_API_Functions extends APISupportFunctions {
    private static PSQLSupportFunctions stickAdDb = null;
    private static String query = "";
    private static int slotExpected = 0;
    private static String regionID = "", categoryId = "", adType = "";
    private static int limitAdPerPage;

    public static void setRegionID(String regionID) {
        AdListing_API_Functions.regionID = regionID;
    }

    public static void setCategoryId(String categoryId) {
        AdListing_API_Functions.categoryId = categoryId;
    }

    public static void setAdTypeSell() {
        adType = "s";
    }

    public static void setAdTypeBuy() {
        adType = "k";
    }

    public static void setAdTypeSellAndBuy() {
        adType = "s,k";
    }

    public static int getLimitAdPerPage() {
        return limitAdPerPage;
    }

    public static void setLimitAdPerPage(int limitAdPerPage) {
        AdListing_API_Functions.limitAdPerPage = limitAdPerPage;
    }

    //Function
    public static void connectToStickyAdDB() {
        try {
            stickAdDb = new PSQLSupportFunctions("10.60.7.35", "sticky_ads", "postgres", "");
        } catch (SQLException throwables) {
            System.out.println("CAN'T CONNECT TO DATABASE");
            throwables.printStackTrace();
        }
    }

    public static int getStickSlotConf() {
        return getStickSlotConf(regionID, categoryId, adType);
    }

    private static int getStickSlotConf(String regionId, String categoryId, String adType) {
        query = "SELECT t.pages\n" +
                "      FROM placement_settings ps\n" +
                "      LEFT JOIN (\n" +
                "            SELECT placement_setting_id, json_object_agg (name, value) AS pages\n" +
                "            FROM placement_setting_values\n" +
                "            GROUP BY placement_setting_id\n" +
                "     ) t ON ps.id = t.placement_setting_id\n" +
                "WHERE \n" +
                "    ps.placement_id = 1\n";
        if (!regionId.isEmpty())
            query += "   AND  ps.region_id = " + regionId + "\n";

        if (!categoryId.isEmpty())
            query += "    AND ps.category_id = " + categoryId + "\n";

        if (!adType.isEmpty())
            query += "    AND ps.category_id = " + adType + "\n";

        System.out.println("DEBUG: \n" + query);

        //Query DB
        String pageSlot = stickAdDb.queryGetTopValue(query, 1).toString();
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(pageSlot);
            slotExpected = Integer.parseInt(String.valueOf(json.get("page1")));
        } catch (ParseException e) {
            slotExpected = -1;
        }

        Assert.assertTrue(slotExpected > -1,
                String.format("Doen't exist Sticky Slot Region[%s] Cate[%s] adType[%s] in Database", regionId, categoryId, adType));

        return slotExpected;
    }


    @Deprecated
    public static void verifyAdInStickySlotEqualToAvailableSlot(List<String> adIdList) {
        url = gatewayHost + gatewayStickyAdsPublicV2 + "?";


        if (!regionID.isEmpty()) {
            if (url.contains("&")) {  //Not First param
                url += "&";
            }
            url += "region_v2=" + regionID;
        }
        if (!categoryId.isEmpty()) {
            if (url.contains("&")) {  //Not First param
                url += "&";
            }
            url += "category_id=" + categoryId;
        }
        if (!adType.isEmpty()) {
            if (url.contains("&")) {  //Not First param
                url += "&";
            }
            url += "st=" + adType;
        }

        response = get(url);
        verifyStatusCode200("API Sticky Ad on Listing dies");
        debugResponse();

        List<String> adIdInStickyList = getResponseDataList(response, "$.sticky_ads[*].ad_id");
        boolean isPassed = true;

        //Number of New Ad equals to Available Sticky slots
        Assert.assertEquals(slotExpected, adIdInStickyList.size(), "FAILED Compare Number of inserted Ad with Sticky Slots");

        //All inserted Ads are displayed in Sticky Ad List
        for (String adID : adIdList) {
            if (!adIdInStickyList.contains(adID)) {
                isPassed = false;
                break;
            }
        }
        Assert.assertTrue(isPassed, String.format("Inserted Ads [%s] don't contain in Available Sticky Slots [%s]", adIdList, adIdInStickyList));
    }

    public static void verifyStickyAdSlot(int pageNumber) {
        verifyStickyAdSlot("", pageNumber, -1);
    }

    public static void verifyStickyAdSlot(int pageNumber, int numberOfAdPerPage) {
        verifyStickyAdSlot("", pageNumber, numberOfAdPerPage);
    }

    public static void verifyStickyAdSlot(String url, int pageNumber, int numberOfAdPerPage) {
        if (url.isEmpty()) {
            url = gatewayHost + gatewayStickyAdsPublicV2 + "?";

            if (pageNumber == 1) {
                url += String.format("limit=%d&offset=%d&o=%d", getLimitAdPerPage(), 0, 0);
            } else {
                url += String.format("limit=%d&offset=%d&o=%d", getLimitAdPerPage(), (pageNumber - 1) * getLimitAdPerPage(), (pageNumber - 1) * getLimitAdPerPage());
            }

            if (!regionID.isEmpty()) {
                if (url.contains("&")) {  //Not First param
                    url += "&";
                }
                url += "region_v2=" + regionID;
            }
            if (!categoryId.isEmpty()) {
                if (url.contains("&")) {  //Not First param
                    url += "&";
                }
                url += "category_id=" + categoryId;
            }
            if (!adType.isEmpty()) {
                if (url.contains("&")) {  //Not First param
                    url += "&";
                }
                url += "st=" + adType;
            }
        }

        response = get(url);
        verifyStatusCode200("API Sticky Ad on Listing dies");
        debugResponse();

        List<String> adIdInStickyList = getResponseDataList(response, "$.sticky_ads[*].ad_id");

        //Number of New Ad equals to Available Sticky slots
        if (numberOfAdPerPage < 0) {
            Assert.assertEquals(slotExpected, adIdInStickyList.size(), "FAILED Compare Number of Sticky Ad Slots with number of the available slot in configuation");
        } else {
            Assert.assertEquals(numberOfAdPerPage, adIdInStickyList.size(), "FAILED Compare Number of Sticky Ad Slots with number of the available slot in configuation");
        }
    }

    public static void verifyStickyAdSlot_ByUrl(String url) {
        response = get(url);
        verifyStatusCode200("API Sticky Ad on Listing dies");

        List<String> adIdInStickyList = getResponseDataList(response, "$.sticky_ads[*].ad_id");

        //Number of New Ad equals to Available Sticky slots
        Assert.assertEquals(slotExpected, adIdInStickyList.size(), "FAILED Compare Number of Sticky Ad Slots with number of the available slot in configuation");
    }
}
