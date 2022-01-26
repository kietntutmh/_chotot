

package api.utils;

import api.feature.ads.InsertAds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vn.chotot.logger.Log4jFactory;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.logging.log4j.Logger;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.configuration.DataConfig.*;
import static api.configuration.GatewayConfig.global_accountID;
import static api.utils.GetAccessToken.getAccessTokenOfCPUser;
import static api.utils.Images.getImageCellIndex;
import static api.utils.Images.getImageID;
import static com.vn.chotot.helper.DateTime.getDateInStringFormat;
import static com.vn.chotot.helper.Utils.combineTwoListStringIntoMap;
import static com.vn.chotot.helper.Utils.mergeTwoMap;

public class GetJSONString {

    static final Logger log = Log4jFactory.instance().createClassLogger(InsertAds.class);
    static final LinkedMap<String, Integer> mapActionId = new LinkedMap<>();

    private static Map<String, Object> getDefaultJSONString() {
        Map<String, Object> mapDefaultData = new HashMap<>();
        mapDefaultData.put("app_id", defaultAppID);
        mapDefaultData.put("lang", defaultLanguage);

        return mapDefaultData;
    }


    //Insert
    public static String extractAndUpdateSubjectJSONMapping(List<String> listKey, List<String> lisValue) {
        // Get image cell index
        int imageCellIndex = getImageCellIndex(lisValue);

        // Edit Ad common: doesn't edit Image. @author: VUHOANG
        Map<String, Object> mapImageID = new HashMap<>();
            if (imageCellIndex > -1) {
                mapImageID = getImageID(lisValue.get(imageCellIndex));
                lisValue.remove(imageCellIndex);
                listKey.remove(imageCellIndex);
            }


        // Create Map<String, Object>
        Map<String, Object> mapData = combineTwoListStringIntoMap(listKey, lisValue);

        // Merge map values
        Map<String, Object> mapDefault = getDefaultJSONString();
        Map<String, Object> mergedMapData = mergeTwoMap(mapData, mapDefault);
        mergedMapData = mergeTwoMap(mapImageID, mergedMapData);

        // Update Subject by adding timestamp
        String currentValue = mergedMapData.get("subject").toString();
        mergedMapData.put("subject", getPrefixAdSubjectModeration() + "A " + currentValue + " " + getDateInStringFormat(null, "mmss"));

        // Get ad subject if existing
        try {
            tempAdSubject = mergedMapData.get("subject").toString(); // Using for Edit Ad & Insert
        } catch (NullPointerException e) {
            log.debug("No existing ad subject on test data");
        }

        // Convert to String
        Gson gson = new GsonBuilder().create();

        return gson.toJson(mergedMapData);
    }

    //Insert
    public static String extractAndUpdateSubjectJSONMappingWithVideo(List<String> listKey, List<String> lisValue, String video_id) {
        // Get image cell index
        int imageCellIndex = getImageCellIndex(lisValue);

        // Edit Ad common: doesn't edit Image. @author: VUHOANG
        Map<String, Object> mapImageID = new HashMap<>();
        if (imageCellIndex > -1) {
            mapImageID = getImageID(lisValue.get(imageCellIndex));
            lisValue.remove(imageCellIndex);
            listKey.remove(imageCellIndex);
        }


        // Create Map<String, Object>
        Map<String, Object> mapData = combineTwoListStringIntoMap(listKey, lisValue);

        // Merge map values
        Map<String, Object> mapDefault = getDefaultJSONString();
        Map<String, Object> mergedMapData = mergeTwoMap(mapData, mapDefault);
        mergedMapData = mergeTwoMap(mapImageID, mergedMapData);

        // Update Subject by adding timestamp
        String currentValue = mergedMapData.get("subject").toString();
        mergedMapData.put("subject", getPrefixAdSubjectModeration() + "A " + currentValue + " " + getDateInStringFormat(null, "mmss"));
        mergedMapData.put("video_id0", video_id);

        // Get ad subject if existing
        try {
            tempAdSubject = mergedMapData.get("subject").toString(); // Using for Edit Ad & Insert
        } catch (NullPointerException e) {
            log.debug("No existing ad subject on test data");
        }

        // Convert to String
        Gson gson = new GsonBuilder().create();

        return gson.toJson(mergedMapData);
    }

    //VUHOANG : insert ad without uploading image
    public static String extractAndUpdateSubjectJSONMapping_NoUploadNewImage(
            List<String> listKey, List<String> lisValue) {
        // Get image cell index
        int imageCellIndex = getImageCellIndex(lisValue);

        // Get image_id
        Map<String, Object> mapImageID = new HashMap<>();
        if (imageCellIndex > -1) {
            mapImageID = getImageID(lisValue.get(imageCellIndex));
            lisValue.remove(imageCellIndex);
            listKey.remove(imageCellIndex);
        }

        // Create Map<String, Object>
        Map<String, Object> mapData = combineTwoListStringIntoMap(listKey, lisValue);

        // Merge map values
        Map<String, Object> mapDefault = getDefaultJSONString();
        Map<String, Object> mergedMapData = mergeTwoMap(mapData, mapDefault);
        mergedMapData = mergeTwoMap(mapImageID, mergedMapData);

        // Update Subject by adding timestamp
        String currentValue = mergedMapData.get("subject").toString();
        mergedMapData.put("subject", getPrefixAdSubjectModeration() + "A " + currentValue + " " + getDateInStringFormat(null, "mmss"));


        // Get ad subject if existing
        try {
            tempAdSubject = mergedMapData.get("subject").toString(); // Using for Edit Ad & Insert
        } catch (NullPointerException e) {
            log.debug("No existing ad subject on test data");
        }

        // Convert to String
        Gson gson = new GsonBuilder().create();

        return gson.toJson(mergedMapData);
    }


    //VUHHOANG no change image -> support for test data
    public static String extractAndUpdateSubjectJSONMapping_EditAd(String adID,
            List<String> listKey, List<String> lisValue) {
        // Get image cell index
        int imageCellIndex = getImageCellIndex(lisValue);

        // Get image_id
        Map<String, Object> mapImageID = new HashMap<>();
        if (imageCellIndex > -1) {
            mapImageID = getImageID(lisValue.get(imageCellIndex));
            lisValue.remove(imageCellIndex);
            listKey.remove(imageCellIndex);
        }

        // Create Map<String, Object>
        Map<String, Object> mapData = combineTwoListStringIntoMap(listKey, lisValue);

        //If Edit ad Else Insert Ad
        if(!adID.isEmpty())
            mapData.put("ad_id", adID);

        // Merge map values
        Map<String, Object> mapDefault = getDefaultJSONString();
        Map<String, Object> mergedMapData = mergeTwoMap(mapData, mapDefault);
        mergedMapData = mergeTwoMap(mapImageID, mergedMapData);

        // Update Subject by adding timestamp
        String currentValue = mergedMapData.get("subject").toString();
        mergedMapData.put("subject", getPrefixAdSubjectModeration() + "A " + currentValue + " " + getDateInStringFormat(null, "mmss"));


        // Get ad subject if existing
        try {
            tempAdSubject = mergedMapData.get("subject").toString(); // Using for Edit Ad & Insert
        } catch (NullPointerException e) {
            log.debug("No existing ad subject on test data");
        }

        // Convert to String
        Gson gson = new GsonBuilder().create();

        return gson.toJson(mergedMapData);
    }

    public static String extractAndUpdateSubjectJSONMapping_InsertAd_Food(
            List<String> listKey, List<String> lisValue) {
        // Get image cell index
        int imageCellIndex = getImageCellIndex(lisValue);

        // Get image_id
        Map<String, Object> mapImageID = new HashMap<>();
        if (imageCellIndex > -1) {
            mapImageID = getImageID(lisValue.get(imageCellIndex));
            lisValue.remove(imageCellIndex);
            listKey.remove(imageCellIndex);
        }

        // Create Map<String, Object>
        Map<String, Object> mapData = combineTwoListStringIntoMap(listKey, lisValue);

        // Merge map values
        Map<String, Object> mapDefault = getDefaultJSONString();
        Map<String, Object> mergedMapData = mergeTwoMap(mapData, mapDefault);
        mergedMapData = mergeTwoMap(mapImageID, mergedMapData);

        // Update Subject by adding timestamp
        String currentValue = mergedMapData.get("subject").toString();
        mergedMapData.put("subject", getPrefixAdSubjectModeration() + "A " + currentValue + " " + getDateInStringFormat(null, "mmss"));

        // Get ad subject if existing
        try {
            tempAdSubject = mergedMapData.get("subject").toString(); // Using for Edit Ad & Insert
        } catch (NullPointerException e) {
            log.debug("No existing ad subject on test data");
        }

        // Convert to String
        Gson gson = new GsonBuilder().create();

        return gson.toJson(mergedMapData);
    }

    public static String extractJsonMapping(Map<String, Object> mapData) {
        return new GsonBuilder().create().toJson(mapData);
    }

    // Edit Ad JSON
    public static String extractJSONMapping_EditAd(
            String adID, List<String> listKeys, List<String> lisValues) {
        Map<String, Object> mapData = new HashMap<>();
        if (adID.isEmpty()) mapData.put("ad_id", tempAdID);
        else mapData.put("ad_id", adID);
        mapData.put("lang", "vi");
        mapData.put("app_id", "desktop_site_flashad");

        String key, value;
        for (int i = 0; i < listKeys.size(); i++) {
            key = listKeys.get(i);
            value = lisValues.get(i);
            if (key.contains("price"))
                mapData.put(key, Integer.parseInt(value)); // "price" field in EditJSON is int
            mapData.put(key, value);
        }

        tempAdEditSubject =
                mapData.get("subject").toString() + " " + getDateInStringFormat(null, "mmss");
        mapData.put("subject", tempAdEditSubject);

        tempAdSubject = tempAdEditSubject; // Using for Insert Ads

        Gson gson = new GsonBuilder().create();
        return gson.toJson(mapData);
    }

    public static String extractJSONMapping_EditAd_ELT(
            String adID, List<String> listKeys, List<String> lisValues) {
        return extractJSONMapping_EditAd(adID, listKeys, lisValues);
    }

    public static String extractJSONMapping_EditAd_Vehicle(
            String adID, List<String> listKeys, List<String> lisValues) {
        return extractJSONMapping_EditAd(adID, listKeys, lisValues);
    }

    public static String extractJSONMapping_EditAd_PTY(
            String adID, List<String> listKeys, List<String> lisValues) {
        return extractJSONMapping_EditAd(adID, listKeys, lisValues);
    }

    public static String extractJSONMapping_EditAd_Entertainment(
            String adID, List<String> listKeys, List<String> lisValues) {
        return extractJSONMapping_EditAd(adID, listKeys, lisValues);
    }

    public static String extractJSONMapping_EditAd_Fashion(
            String adID, List<String> listKeys, List<String> lisValues) {
        return extractJSONMapping_EditAd(adID, listKeys, lisValues);
    }

    public static String extractJSONMapping_EditAd_Services(
            String adID, List<String> listKeys, List<String> lisValues) {
        return extractJSONMapping_EditAd(adID, listKeys, lisValues);
    }

    private static int getActionID(String adID) {
        int action_id = 1;
        if (mapActionId.containsKey(adID)) {
            action_id = isEditedAd ? action_id + 1 : action_id;
        }
        mapActionId.put(tempAdID, action_id);
        return mapActionId.get(adID);
    }

    // CP: DEFAUL JSON
    private static Map<String, Object> extractJSONMappingCP(
            List<String> listKey, List<String> listValue) {
        Map<String, Object> mapData = new HashMap<>();

        int action_id = getActionID(tempAdID);
        // Get Token of account CP
        mapData.put("token", getAccessTokenOfCPUser());
        // Set up Ad info
        mapData.put("action_id", action_id);
        mapData.put("ad_id", Integer.parseInt(tempAdID));
        mapData.put("subject", tempAdSubject);
        mapData.put("ad_params", "");

        // Set up PC info
        try {
            mapData.put("remote_addr", Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            mapData.put("remote_addr", "192.168.1.197");
        }

        // Transfer from insertAd JSON
        for (int i = 0; i < listKey.size(); i++) {
            String key = listKey.get(i);
            String value = listValue.get(i);
            switch (key.toLowerCase().trim()) {
                case "category":
                    mapData.put("category", Integer.parseInt(value));
                    break;
                case "type":
                    mapData.put("type", value);
                    break;
                case "region_v2":
                    mapData.put("region_v2", Integer.parseInt(value));
                    break;
                case "ward":
                    mapData.put("ward", Integer.parseInt(value));
                    break;
                case "body":
                    mapData.put("body", value);
                    break;
                case "payment_delivery":
                    mapData.put("payment_delivery", value);
                    break;
                case "price":
                    mapData.put("price", Integer.parseInt(value));
                    break;
            }
        }
        return mapData;
    }

    // CP: FEATURES JSON: PTY
    public static String extractJSONCP_PTY_House(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue); // Add ad_id
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
//        mapData_adParams.put("landed_type", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_PTY_Apartment(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("apartment_type", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_PTY_Land(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put(
                "land_type", Integer.parseInt(listValue.get(listKey.indexOf("land_type"))));
        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_PTY_Office(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("commercial_type", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_PTY_Room(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        //        mapData_adParams.put("commercial_type", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: FEATURES JSON: ELT
    public static String extractJSONCP_ELT_Laptop(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_Phone(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("mobile_type", 1);
        mapData_adParams.put("mobile_brand", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_Tablet(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("tablet_model", 6);
        mapData_adParams.put("tablet_brand", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_PC(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_Camera(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_Sound(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_Wearable(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_Accessories(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_ELT_PC_Component(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("elt_condition", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: FEATURES JSON: VEH
    public static String extractJSONCP_VEH_Car(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("carmodel", 21);
        mapData_adParams.put("carbrand", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_VEH_Motorbike(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("motorbikemodel", 21);
        mapData_adParams.put("motorbikebrand", 1);
        mapData_adParams.put("motorbiketype", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_VEH_Truck(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("truckbrand", 1);
        mapData_adParams.put("truckweight", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_VEH_Electric(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("evehicletype", 1);
        mapData_adParams.put("evehiclebrand", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_VEH_Bicycles(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("bicyclebrand", 1);
        mapData_adParams.put("bicyclesport", 1);
        mapData_adParams.put("bicycletype", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_VEH_Vehicles_Parts(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_VEH_Other_Vehicles(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 1);
        mapData_adParams.put("svehicletype", 1);
        mapData_adParams.put("ovehicletype", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    @Deprecated
    private static String extractAndUpdateSubjectJSONMappingCP_OLD(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        // Get image cell index
        int imageCellIndex = getImageCellIndex(listValue);

        // Get image_id
        Map<String, Object> mapImageID = new HashMap<>();
        if (imageCellIndex > -1) {
            //noinspection UnusedAssignment
            mapImageID = getImageID(listValue.get(imageCellIndex));
            listValue.remove(imageCellIndex);
            listKey.remove(imageCellIndex);
        }

        // Split ad_params from lisKey & lisValue
        List<String> listKey_adParams = listKey.subList(0, 6);
        List<String> listValue_adParams = listValue.subList(0, 6);

        List<String> listKeyAd = listKey.subList(6, listKey.size());
        List<String> listValueAd = listValue.subList(6, listValue.size());

        // Create 2 Map<String, Object> from Lists
        Map<String, Object> mapData = combineTwoListStringIntoMap(listKeyAd, listValueAd);
        Map<String, Object> mapData_adParams =
                combineTwoListStringIntoMap(listKey_adParams, listValue_adParams);

        // Change format of some fields
        mapData_adParams.put("area", Integer.parseInt(mapData_adParams.get("area").toString()));
        mapData_adParams.put(
                "condition_ad", Integer.parseInt(mapData_adParams.get("condition_ad").toString()));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(mapData_adParams.get("region_v2").toString()));
        mapData_adParams.put("area_v2", Integer.parseInt(mapData_adParams.get("area_v2").toString()));
        mapData_adParams.put("ward", Integer.parseInt(mapData_adParams.get("ward").toString()));

        // TESTING
        mapData_adParams.put("landed_type", 1);
        mapData_adParams.put("projectid", 4245);
        mapData_adParams.remove("condition_ad");

        // Add mapData_adParams to mapData
        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Static fields
        mapData.put("account_id", global_accountID);
        mapData.put("ad_id", tempAdID);
        mapData.put("subject", tempAdSubject);
        mapData.put("user_id", Integer.parseInt(tempUID));
        int[] hide_image = {0};
        mapData.put("hide_image", hide_image);
        mapData.put("lang", defaultLanguage);
        mapData.put("name", "hue");
        mapData.put("reason", "");
        mapData.put("token", getAccessTokenOfCPUser());
        mapData.put("reviewer_name", "hue");
        mapData.put("phone", "0973598776");
        mapData.put("status", "inactive");
        try {
            mapData.put("remote_addr", Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            mapData.put("remote_addr", "192.168.1.197");
        }

        // Change format of some fields
        mapData.put("account_id", Integer.parseInt(mapData.get("account_id").toString()));
        mapData.put("ad_id", Integer.parseInt(mapData.get("ad_id").toString()));
        mapData.put("category", Integer.parseInt(mapData.get("category").toString()));
        mapData.put("city", Integer.parseInt(mapData.get("city").toString()));
        mapData.put("region", Integer.parseInt(mapData.get("region").toString()));
        mapData.put("price", Integer.parseInt(mapData.get("price").toString()));
        mapData.put("action", Integer.parseInt(mapData.get("action").toString()));
        mapData.put("action_id", Integer.parseInt(mapData.get("action_id").toString()));
        mapData.put("company_ad", Boolean.parseBoolean(mapData.get("company_ad").toString()));
        mapData.put("is_paid_ad", Boolean.parseBoolean(mapData.get("is_paid_ad").toString()));
        mapData.put("is_private_ad", Boolean.parseBoolean(mapData.get("is_private_ad").toString()));
        mapData.put("ad_has_images", Boolean.parseBoolean(mapData.get("is_private_ad").toString()));

        // Get ad subject if existing
        try {
            tempAdSubject = mapData.get("subject").toString();
        } catch (NullPointerException e) {
            log.debug("No existing ad subject on test data");
        }

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: ENTERMAINMENT
    public static String extractJSONCP_Entertainment_Instrument(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        //        mapData_adParams.put("instrument_type", 1);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Entertainment_Sport(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Entertainment_Book(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Entertainment_Collectibles(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        //        mapData_adParams.put("collection_type", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Entertainment_Gaming(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Entertainment_Hobby(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: FASHION
    public static String extractJSONCP_Fashion_Clothes(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        String area_v2 = listValue.get(listKey.indexOf("area_v2"));
        mapData_adParams.put("area_v2", Integer.parseInt(area_v2));
        mapData_adParams.put("area", Integer.parseInt(area_v2.substring(area_v2.length() - 2)));
        mapData_adParams.put("ward", Integer.parseInt(listValue.get(listKey.indexOf("ward"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 2);
        mapData_adParams.put("payment_delivery", listValue.get(listKey.indexOf("payment_delivery")));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Fashion_Watch(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 2);
        mapData_adParams.put("itemconsumer", 3);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Fashion_Shoes(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 2);
        mapData_adParams.put("itemconsumer", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Fashion_Handbag(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 2);
        mapData_adParams.put("itemconsumer", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Fashion_Perfume(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 2);
        mapData_adParams.put("itemconsumer", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Fashion_Accessories(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));
        mapData_adParams.put("condition_ad", 2);
        mapData_adParams.put("itemconsumer", 2);

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: SERVICES
    public static String extractJSONCP_Services_Service(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: Job
    public static String extractJSONCP_Job(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // CP: Mom and baby
    public static String extractJSONCP_MomAndBaby(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Services_Travel(List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    // Chuẩn hóa format of json CP
    public static String extractJSONCP_Format(String jsonData) {
        Gson gson = new Gson();
        Map mapData = gson.fromJson(jsonData, Map.class);

        // Format Ad_id to Integer
        int ad_id = Integer.parseInt(String.valueOf(mapData.get("ad_id")));
        //noinspection unchecked
        mapData.put("ad_id", ad_id);

        gson = new GsonBuilder().create();
        return gson.toJson(mapData);
    }

    // SHOP
    public static String extractJSONShop_PTY_Create(String phone) {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("phoneNumber", phone);
        mapData.put("name", "CHUYÊN TRANG BĐS TEST AUTO " + phone);
        mapData.put("address", "123 Ngô Đức Kế Q1");
        mapData.put("description", "Giới thiệu Shop BĐS auto " + phone);
        mapData.put("categoryId", 1020);
        return new GsonBuilder().create().toJson(mapData);
    }

    public static String extractJSONShop_ELT_Create(String phone) {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("phoneNumber", phone);
        mapData.put("name", "CHUYÊN TRANG BĐS TEST AUTO " + phone);
        mapData.put("address", "123 Ngô Đức Kế Q1");
        mapData.put("description", "Giới thiệu Shop ELT auto " + phone);
        mapData.put("categoryId", 5010);
        return new GsonBuilder().create().toJson(mapData);
    }

    public static String extractJSONShop_VEH_Create(String phone) {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("phoneNumber", phone);
        mapData.put("name", "CHUYÊN TRANG BĐS TEST AUTO " + phone);
        mapData.put("address", "123 Ngô Đức Kế Q1");
        mapData.put("description", "Giới thiệu Shop Xe auto " + phone);
        mapData.put("categoryId", 2080);
        return new GsonBuilder().create().toJson(mapData);
    }

    public static String extractJSONShop_Active(String phone) {
        Map<String, Object> mapDataURL = new HashMap<>();
        mapDataURL.put("url", phone);

        Map<String, Object> mapDataArea = new HashMap<>();
        mapDataArea.put("areaId", 407);
        mapDataArea.put("regionId", 1);
        mapDataArea.put("areaName", "Quận 1");
        JSONArray arrDataArea = new JSONArray();
        arrDataArea.appendElement(mapDataArea);

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("name", "CHUYÊN TRANG BĐS TEST AUTO " + phone);
        mapData.put("contactName", phone);
        mapData.put("address", "123 Ngô Đức Kế");
        mapData.put("description", "CHUYÊN TRANG BĐS AUTO TEST DESCRIPTION");
        mapData.put("showAbsoluteAddress", true);
        mapData.put("latitude", 0);
        mapData.put("longitude", 0);
        mapData.put("url", new JSONObject(mapDataURL));
        mapData.put("workingAreas", arrDataArea);
        mapData.put(
                "profileImageUrl",
                "https://static.chotot.org/imaginary/e3a3ce42010a541457b51eb3d680da9cd83cffea/shop_image/0f4c8670e0fa11e98fa1253f12f27628/resize?width=200&height=200");
        mapData.put(
                "coverImageUrl",
                "https://static.chotot.org/imaginary/cf5bb47b743869ab953c4ea58b97af6c3a04d3d3/shop_image/12062830e0fa11e99b4b49fa715853a2/resize?width=960&height=334");

        return new GsonBuilder().create().toJson(mapData);
    }

    public static String extractJSONChat_FilterWord(String senderID, String roomID, String text) {
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("sender_id", senderID);
        mapData.put("room_id", roomID);
        mapData.put("text", text);

        return new GsonBuilder().create().toJson(mapData);
    }


    public static String extractJSONCP_Food(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Pet_Chicken(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

    public static String extractJSONCP_Pet_Dog(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Pet_Bird(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Pet_Cat(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Pet_Others(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Pet_Accessories(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_ElectricAppliances_Refrigerator(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_ElectricAppliances_Cooler(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_ElectricAppliances_WashingMachine(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Table_Chair(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Drawer_Shelf(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Bed_Bedding(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Kitchen_Utensil_Dinnerware(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_KitchenAppliance(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Fan(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Lighting(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Decoration(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Bathware(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Household_Others(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Requisite_OfficeEquipment(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }
    public static String extractJSONCP_Requisite_SpecializedItem(
            List<String> listKey, List<String> listValue) {
        Gson gson = new GsonBuilder().create();

        Map<String, Object> mapData = extractJSONMappingCP(listKey, listValue);
        Map<String, Object> mapData_adParams = new HashMap<>();

        mapData_adParams.put("area", Integer.parseInt(listValue.get(listKey.indexOf("area_v2"))));
        mapData_adParams.put(
                "region_v2", Integer.parseInt(listValue.get(listKey.indexOf("region_v2"))));

        mapData.put("ad_params", new JSONObject(mapData_adParams));

        // Convert to String
        return gson.toJson(mapData);
    }

}