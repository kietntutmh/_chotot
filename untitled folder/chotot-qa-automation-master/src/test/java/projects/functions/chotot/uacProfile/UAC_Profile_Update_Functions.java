package projects.functions.chotot.uacProfile;

import org.junit.Assert;
import projects.functions.APISupportFunctions;

import java.util.ArrayList;
import java.util.List;

import static api.configuration.GatewayConfig.*;

public class UAC_Profile_Update_Functions extends APISupportFunctions {
    private static String fullName = "";
    private static String gender = "";
    private static String birthday = "1990-03-04";
    private static String taxCode = "";
    private static List<Integer> favorites = new ArrayList<>();
    private static String address = "";
    private static String idNumber = "";
    private static String email = "";
    private static String accessToken = null;
    private static final int retryOfUpdate = 5;

    protected static void setFullName(String fullName) {
        UAC_Profile_Update_Functions.fullName = fullName;
    }

    protected static String getName() {
        return fullName;
    }

    protected static void setGenderToMale() {
        gender = "m";
    }

    protected static void setGenderToFemale() {
        gender = "f";
    }

    protected static void setGenderToOther() {
        gender = "u";
    }

    protected static String getGender() {
        return gender;
    }

    protected static void setBirthday(String birthday) {
        UAC_Profile_Update_Functions.birthday = birthday;
    }

    protected static String getBirthday() {
        return birthday;
    }

    protected static void setTaxCode(String taxCode) {
        UAC_Profile_Update_Functions.taxCode = taxCode;
    }

    protected static String getTaxCode() {
        return taxCode;
    }

    protected static void setFavorites(List<Integer> favorites) {
        UAC_Profile_Update_Functions.favorites = favorites;
    }

    protected static List<Integer> getFavorites() {
        return favorites;
    }

    protected static void setAddress(String address) {
        UAC_Profile_Update_Functions.address = address;
    }

    protected static String getAddress() {
        return address;
    }

    protected static void setIdNumber(String idNumber) {
        UAC_Profile_Update_Functions.idNumber = idNumber;
    }

    protected static String getIdNumber() {
        return idNumber;
    }

    protected static void setEmail(String email) {
        UAC_Profile_Update_Functions.email = email;
    }

    protected static String getEmail() {
        return email;
    }

    protected static void setAccessToken(String accessToken) {
        UAC_Profile_Update_Functions.accessToken = accessToken;
    }

    protected static String getAccessToken() {
        return accessToken;
    }

    protected static void setDefaultProfile() {
        setDefaultProfile(accessToken);
    }

    private static void initUpdateInfo() {
        initBody();
        if (!fullName.isEmpty()) {
            bodyJson.addProperty("full_name", fullName);
        }
        if (!address.isEmpty()) {
            bodyJson.addProperty("address", address);
        }
        if (!gender.isEmpty()) {
            bodyJson.addProperty("gender", gender);
        }
        if (!birthday.isEmpty()) {
            bodyJson.addProperty("birthday", birthday);
        }
        if (!taxCode.isEmpty()) {
            bodyJson.addProperty("tax_code", taxCode);
        }
        if (!favorites.isEmpty()) {
            bodyJson.add("favorites", getJsonArrayValueInt(favorites));
        }
        if (!idNumber.isEmpty()) {
            bodyJson.addProperty("id_number", idNumber);
        }
        if (!email.isEmpty()) {
            bodyJson.addProperty("email", email);
        }
    }

    private static String getProfileInfo(String keyName) {
        String data = "";
        switch (keyName.toLowerCase()) {
            case "full_name":
                data = fullName;
                break;
            case "address":
                data = address;
                break;
            case "gender":
                data = gender;
                break;
            case "birthday":
                data = birthday;
                break;
            case "tax_code":
                data = taxCode;
                break;
            case "id_number":
                data = idNumber;
                break;
            case "email":
                data = email;
                break;
            case "favorites":
                data = String.valueOf(favorites)
                        .replace("[", "")
                        .replace("]", "")
                        .replace("\"", "")
                        .replace(" ", "");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + keyName);
        }
        return data;
    }

    private static void setDefaultProfile(String accessToken) {
        //set random email
        String email = "autochotot" + getCurrentTimeStampUnix() + "@gmail.com";

        initBody();
        bodyJson.addProperty("name", "Customer Auto");
        bodyJson.addProperty("email", email);
        bodyJson.addProperty("address", "2 Ngô Đức Kế");
        bodyJson.addProperty("favorites", "[]");

        for (int i = 0; i < retryOfUpdate; i++) {
            try {
                response = put(accessToken, bodyJson, gatewayProfile);
                verifyStatusCode200("SET DEFAULT PROFILE API IS FAILED/DEAD");
                Assert.assertNotNull("NAME UPDATE IS FAILED", getResponseData("$.full_name"));
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }

        verifyStatusCode200("SET DEFAULT PROFILE API IS FAILED/DEAD");
        Assert.assertEquals("NAME UPDATE IS FAILED", "customerAuto", getResponseData("$.full_name"));
//        Assert.assertSame("EMAIL UPDATE IS FAILED", email, getResponseData("$.email"));
        Assert.assertEquals("ADDRESS UPDATE IS FAILED", address, getResponseData("$.address"));
        Assert.assertEquals("FAVORITES UPDATE IS FAILED", "[]", getResponseData("$.favorites"));
    }

    private static String getCurrentInfo(String accessToken, String keyName) {
        for (int i = 0; i < retryOfUpdate; i++) {
            try {
                response = get(accessToken, gatewaySelfProfile);
                verifyStatusCode200("GET PROFILE API FAILED");
                Assert.assertNotNull("GET " + keyName.toUpperCase() + " IS FAILED", getResponseData("$." + keyName));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }

        verifyStatusCode200("GET PROFILE API FAILED");
        return getResponseData("$." + keyName);
    }

    private static void verifyUpdateFailed(String errorMessage) {
        initUpdateInfo();

        for (int i = 0; i < retryOfUpdate; i++) {
            try {
                response = put(accessToken, bodyJson, gatewayProfile);
                verifyStatusCode400("UPDATE PROFILE FAILED");
                Assert.assertNotNull("GET ERROR MESSAGE IS FAILED", getResponseData("$.message"));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }

        verifyStatusCode400("UPDATE PROFILE FAILED");
        Assert.assertEquals("ERROR MESSAGE IS INCORRECT", errorMessage, getResponseData("$.message"));
        Assert.assertEquals("ERROR CODE IS INCORRECT", "ProfileUpdate", getResponseData("$.err_code"));
        Assert.assertEquals("CODE IS INCORRECT", "ProfileUpdate", getResponseData("$.code"));
    }

    private static void verifyUpdateProfileData(String keyName, String expectedValue) {
        initUpdateInfo();

        for (int i = 0; i < retryOfUpdate; i++) {
            try {
                response = put(accessToken, bodyJson, gatewayProfile);
                verifyStatusCode200("UPDATE PROFILE API FAILED");
                Assert.assertNotNull("GET " + keyName.toUpperCase() + " IS FAILED", getResponseData("$." + keyName));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }

        verifyStatusCode200("UPDATE PROFILE API FAILED");
        Assert.assertEquals(keyName.toUpperCase() + " UPDATED", expectedValue, getResponseData("$." + keyName));
    }

    private static void verifyEmailNotChange(String expectedEmail) {
        verifyUpdateProfileData("email", expectedEmail);
    }

    private static void verifyEmailChange(String expectedEmail) {
        verifyUpdateProfileData("email", expectedEmail);
    }

    protected static String getCurrentEmail(String accessToken) {
        return getCurrentInfo(accessToken, "email");
    }

    protected static void verifyUpdateIsSuccessful() {
        initUpdateInfo();

        for (int i = 0; i < retryOfUpdate; i++) {
            try {
                response = put(accessToken, bodyJson, gatewayProfile);
                verifyStatusCode200("UPDATE PROFILE API FAILED");
                Assert.assertEquals(getProfileInfo("full_name"), getResponseData("$.full_name"));
                break;
            } catch (AssertionError assertionError) {
                waitConstant(2);
            }
        }

        debugResponse();
        verifyStatusCode200("UPDATE PROFILE API FAILED");
        Assert.assertEquals("USER NAME UPDATE IS FAILED", getProfileInfo("full_name"), getResponseData("$.full_name"));
        Assert.assertEquals("GENDER UPDATE IS FAILED", getProfileInfo("gender"), getResponseData("$.gender"));
        Assert.assertNotNull("BIRTHDAY UPDATE IS FAILED", getResponseData("$.birthday"));
        Assert.assertEquals("TAX CODE UPDATE IS FAILED", getProfileInfo("tax_code"), getResponseData("$.tax_code"));
        Assert.assertEquals("FAVORITES UPDATE IS FAILED", getProfileInfo("favorites"), getResponseData("$.favorites"));
        Assert.assertEquals("ADDRESS UPDATE IS FAILED", getProfileInfo("address"), getResponseData("$.address"));
        Assert.assertEquals("ID NUMBER UPDATE IS FAILED", getProfileInfo("id_number"), getResponseData("$.id_number"));
    }

    protected static void verifyUpdateProfileFailedWithInvalidName() {
        verifyUpdateFailed("Tên không hợp lệ.");
    }

    protected static void verifyUpdateProfileFailedWithInvalidGender() {
        verifyUpdateFailed("Gender: Giới tính không hợp lệ. ");
    }

    protected static void verifyUpdateProfileFailedWithInvalidAddress() {
        verifyUpdateFailed("Địa chỉ không hợp lệ.");
    }

    protected static void verifyUpdateProfileFailedWithInvalidBirthday() {
        verifyUpdateFailed("Ngày sinh không hợp lệ.");
    }

    protected static void verifyUpdateProfileFailedWithInvalidIDNumber() {
        verifyUpdateFailed("ID number không hợp lệ.");
    }

    protected static void verifyUpdateProfileFailedWithInvalidFavorites() {
        verifyUpdateFailed("Favorites number không hợp lệ.");
    }

    protected static void verifyUpdateProfileFailedWithInvalidTaxCode() {
        verifyUpdateFailed("Mã số thuế không hợp lệ.");
    }

    protected static void verifyUpdateProfileFailedWithInvalidEmail() {
        verifyUpdateFailed("Email: Email không hợp lệ. ");
    }

    protected static void verifyUpdateProfileFailedWithAccountHasEmail() {
        verifyEmailNotChange(getCurrentEmail(accessToken));
    }
}
