package com.vn.chotot.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static String getHostName() {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            System.out.println("Host Name: " + inetAddress.getHostName());
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> combineTwoListStringIntoMap(
            List<String> list1, List<String> list2) {
        Map<String, Object> mapData = new HashMap<>();
        for (int i = 0; i < list1.size(); i++) {
            mapData.put(list1.get(i), list2.get(i));
        }

        return mapData;
    }

    public static Map<String, Object> mergeTwoMap(
            Map<String, Object> map1, Map<String, Object> map2) {

        // Return new map
        return Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static String convertMapToJsonString(Map<Object, Object> mapObject) {
        Gson gson = new GsonBuilder().create();

        return gson.toJson(mapObject);
    }

    public static String sha1Decode(String text) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(text);
    }

    public static List<String> removeDuplicateListString(List<String> listWithDuplicates) {
        List<String> listWithoutDuplicates = listWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        return listWithoutDuplicates;
    }

    public static List<String> removeElementListString(List<String> listElements, String item) {
        List<String> listWithoutDuplicates = listElements.stream()
                .filter(e -> !Objects.equals(e, item))
                .collect(Collectors.toList());
        return listWithoutDuplicates;
    }

}
