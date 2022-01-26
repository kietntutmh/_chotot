package projects.functions.chotot.userGroup;

import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.util.List;

import static api.configuration.GatewayConfig.gatewayUserGroup_Create;
import static api.configuration.GatewayConfig.gatewayUserGroup_Update;
import static api.configuration.GatewayConfig.gatewayUserGroup_Check;

public class UserGroup_API_Functions extends APISupportFunctions {
    private static String userGroupName = "";
    private static final int retryUserGroup = 5;

    public static List<String> userGroupPhones;
    public static String userGroupID = "";
    public static List<Integer> userGroupAccIDs;

    public static String getUserGroupName() {
        return userGroupName;
    }

    public static void setUserGroupName(String userGroupName) {
        UserGroup_API_Functions.userGroupName = userGroupName;
    }

    public static String getUserGroupID() {
        return userGroupID;
    }

    public static void setUserGroupID(String userGroupID) {
        UserGroup_API_Functions.userGroupID = userGroupID;
    }

    public static List<String> getUserGroupPhones() {
        return userGroupPhones;
    }

    public static void setUserGroupPhones(List<String> userGroupPhones) {
        UserGroup_API_Functions.userGroupPhones = userGroupPhones;
    }

    public static List<Integer> getUserGroupAccIDs() {
        return userGroupAccIDs;
    }

    public static void setUserGroupAccIDs(List<Integer> userGroupAccIDs) {
        UserGroup_API_Functions.userGroupAccIDs = userGroupAccIDs;
    }

    public static String createUserGroup() {
        setUserGroupName("Automation User Group " + getTimeStamp());
        initBodyJson();
        bodyJson.addProperty("name", getUserGroupName());

        for(int i = 0 ; i< retryUserGroup; i++){
            try{
                response = post(bodyJson,gatewayUserGroup_Create);
                verifyStatusCode200("User Group", gatewayUserGroup_Create);
                Assert.assertNotNull(getResponseData("$._id"));
                break;
            }catch (AssertionError error){
                waitConstant(1);
            }
        }
        debugResponse();
        // Verify response
        Assert.assertEquals(getUserGroupName(), getResponseData("$.name"));
        Assert.assertEquals(getResponseDataList("$.account_ids").size(), 0);
        Assert.assertEquals(getResponseDataList("$.category_ids").size(), 0);
        Assert.assertEquals(getResponseDataList("$.ad_types").size(), 0);
        Assert.assertEquals(getResponseDataList("$.user_types").size(), 0);

        // Get User Group ID
        userGroupID = getResponseData("$._id");
        Assert.assertNotNull(userGroupID);
        return userGroupID;
    }

    public static void addUserToUserGroup(String userGroupID, List<Integer> accountIDs){
        initBodyJson();
        bodyJson.addProperty("_id", userGroupID);
        bodyJson.add("account_ids", getJsonArrayValueInt(accountIDs));
        for(int i = 0 ; i< retryUserGroup; i++){
            try{
                response = put(bodyJson,gatewayUserGroup_Update);
                verifyStatusCode200("User Group", gatewayUserGroup_Update);
//                Assert.assertNotNull(getResponseData("$._id"));
                Assert.assertEquals("STATUS IS WRONG", "ok", getResponseData("$.status"));
                break;
            }catch (AssertionError error){
                waitConstant(1);
            }
        }
        debugResponse();
//        List<Integer> actualAccountIDs = getResponseDataListInt("$.account_ids");
//        for (int i = 0; i < accountIDs.size(); i++) {
//            Assert.assertEquals("ACCOUNT LIST ISN'T ADDED ENOUGH TO USER GROUP " + userGroupID
//                    , accountIDs.get(i), actualAccountIDs.get(i));
//        }
//        Assert.assertEquals("WRONG USER GROUP ID",userGroupID ,getResponseData("$._id"));
        Assert.assertEquals("STATUS IS WRONG", "ok", getResponseData("$.status"));
    }

    public static void verifyUserInUserGroup(String userGroupID, List<Integer> accountIDs){
        for (Integer accountID : accountIDs) {
            String uri = String.format(gatewayUserGroup_Check, userGroupID, accountID);
            for(int i = 0 ; i< retryUserGroup; i++){
                try{
                    response = get(uri);
                    verifyStatusCode200("Check User Group", uri);
                    break;
                }catch (AssertionError error){
                    waitConstant(1);
                }
            }
            debugResponse();
            Assert.assertEquals("WRONG MESSAGE", "true", String.valueOf(getResponseData("@")));
        }
    }
}
