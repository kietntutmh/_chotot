package AutomationLibrary.Execution;

import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class data {
    private static JSONObject data = getJsonObj(System.getProperty("user.dir")+ "//data//data.json");

    public static String validPhone(){
        return data.getJSONObject("valid_account").getString("phone");
    }

    public static String validPw(){
        return data.getJSONObject("valid_account").getString("pw");
    }

    public static String profileName(){
        return data.getJSONObject("valid_account").getString("profile_name");
    }

    private static JSONObject getJsonObj(String dataFilePath) {

        StringBuilder jsonData = new StringBuilder();
        BufferedReader br = null;
        try {

            String line;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFilePath), StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                jsonData.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (jsonData.length() == 0)
            jsonData = new StringBuilder("{}");
        return new JSONObject(jsonData.toString());
    }

}
