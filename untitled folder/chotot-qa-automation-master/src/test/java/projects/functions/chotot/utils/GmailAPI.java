package projects.functions.chotot.utils;

import com.vn.chotot.api.rest_assured.RestAssure;
import projects.functions.APISupportFunctions;

public class GmailAPI extends APISupportFunctions {
    private static final String googleMailApi_gateway = "https://www.googleapis.com/gmail";
    private static final String goolgeMailApi_UserProfile = googleMailApi_gateway + "/v1/users/%s/profile";     //userId
    private static final String goolgeMailApi_GetMessageList = googleMailApi_gateway + "/v1/users/%s/messages";     //userId
    private static final String goolgeMailApi_GetMessage = googleMailApi_gateway + "v1/users/%s/messages/%s";     //userId, messageId

    private static String gmail = "";       //testchotot001@gmail.com  (001 -> 005)
    private static String password = "123Abc!@#";
    private static String token;
    private static String clientID;
    private static String clientSecret;
    //testchotot001@gmail.com - 123Abc!@# - AIzaSyD0Vu0HWRCZt5KaKgosBJ-ycO1CY9Mx9g4

    public static void getGmailMessageList(String email){
        initEmailToken(email);
        response = RestAssure.instance().get(token, String.format(goolgeMailApi_GetMessageList, clientID));
        debugResponse();
    }

    private static void initEmailToken(String email){
        gmail = email;
        switch (gmail.toLowerCase()){
            case "testchotot001@gmail.com":
            default:
                token = "AIzaSyD0Vu0HWRCZt5KaKgosBJ-ycO1CY9Mx9g4";
                clientID = "497543193179-t6r0hvm0tkbupo51jhbcuodd5hjhg91j.apps.googleusercontent.com";
                clientSecret = "sTm3IAKRkMWlJqVUCJ_RFzP-";
                break;
        }
    }


}
