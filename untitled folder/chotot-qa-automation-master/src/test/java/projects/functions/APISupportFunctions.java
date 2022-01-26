package projects.functions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.PathNotFoundException;
import com.mongodb.*;
import com.vn.chotot.logger.Log4jFactory;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonArray;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonObject;
import io.cucumber.core.internal.gherkin.deps.com.google.gson.JsonPrimitive;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class APISupportFunctions {
    static final Logger log = Log4jFactory.instance().createClassLogger(APISupportFunctions.class);
    public static String bodyString = null;
    public static JsonObject bodyJson = null;
    public static JsonObject bodyResponseA = null;
    public static JsonObject bodyResponseB = null;
    public static JsonObject bodyChildJson = null;
    public static JsonArray bodyJsonArray = null;
    public static JsonArray bodyJsonArrayChild = null;
    public static JsonObject bodyJsonForArray = null;
    public static Response response = null;
    public static Response responseTemp1 = null;
    public static Response responseTemp2 = null;
    public static String query = "";
    public static String apiURL = "";       //IMPACT
    public static String url = "";
    public static String token = "";
    public static String method = "";
    public static String body = "";
    public static String checksum = "";
    private static boolean scenarioPassedByAssert = true;
    public static String assertFailedMsg = "";

    private static Map<String, String> headerMap = null;

    private static String curl;

    public APISupportFunctions() {
        bodyJson = new JsonObject();
        bodyChildJson = new JsonObject();
    }

    public static void initBody() {
        bodyJson = new JsonObject();
        bodyChildJson = new JsonObject();
    }

    public static void initBodyChild() {
        bodyChildJson = new JsonObject();
    }


    public static void initBodyArray() {
        bodyJsonArray = new JsonArray();
    }

    public static void addPropertyBodyArray(JsonObject obj) {
        if (bodyJsonArray == null)
            bodyJsonArray = new JsonArray();
        bodyJsonArray.add(obj);
    }

    public static void setURL(String url) {
        APISupportFunctions.url = url;
    }

    public static String getURL() {
        return url;
    }

    private static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        APISupportFunctions.token = token;
    }

    public static String getMethod() {
        return method;
    }

    public static void setMethod(String method) {
        APISupportFunctions.method = method;
    }

    private static String getBody() {
        return body;
    }

    public static void setBody(String body) {
        APISupportFunctions.body = body;
    }

    public static void setChecksum(String checksum) {
        APISupportFunctions.checksum = checksum;
    }

    private static String getChecksum() {
        return APISupportFunctions.checksum;
    }

    public static void setScenarioPassedByAssertToDefault() {
        scenarioPassedByAssert = true;
    }

    public static boolean getScenarioPassedByAssert() {
        return scenarioPassedByAssert;
    }

    public static String getAssertFailedMsg() {
        return assertFailedMsg;
    }

    public static void initBodyJson() {
        bodyJson = new JsonObject();
    }

    public static void initBodyJsonForArray() {
        bodyJsonForArray = new JsonObject();
    }

    public static void setHeaderMap(String key, String value) {
        if (headerMap == null)
            headerMap = new HashMap<>();
        headerMap.put(key, value);
    }

    public static Map<String, String> getHeaderMap() {
        return headerMap;
    }

    @Deprecated
    public static boolean compareStatusCode(Response response, Object statusCode) {
        return String.valueOf(response.getStatusCode()).equalsIgnoreCase(String.valueOf(statusCode));
    }

    // ------------- Verify Status Code Functions -------------

    private static String standardizeDebugMessage(String serviceName, String apiURL, String expectedCode) {
        // This condition is used for the old ways that use message instead of serviceName & apiURL. Here, apiURL is errorMsg
        // VUHOANG_DEBUG: This condition will be removed after all usages of verifyStatusCode(message) are maintained to the new way
        if (serviceName == null) {
            return String.format("VERIFY RESPONSE CODE FAILED. Expected[%s] /n%s", expectedCode, apiURL);
        }

        //Remove gateway URI out of apiURL and keep the library independent
        String gateway = "//gateway.chotot.";
        if (apiURL.contains(gateway)) {
            apiURL = apiURL.trim().substring(apiURL.indexOf(":") + gateway.length() + 4);
        }
        return String.format("SERVICE[%s] - API[%s] responses INCORRECT CODE. Expected[%s]",
                serviceName.toUpperCase(), apiURL.trim(), expectedCode);
    }

    /**
     * This is the CORE Function of verifying status code. It calls standardizeDebugMessage to standard the debug output message to print
     *
     * @param response
     * @param serviceName        It's controlled by User. (Eg. "FLASH AD", "PRICER", "OPAPI")
     * @param apiURL             It's URL of API
     * @param expectedStatusCode It's Expected Status Code
     * @author VUHOANG
     */
    public static void verifyStatusCode(Response response, String serviceName, String apiURL, String expectedStatusCode) {
        // Standardize a message to debug
        if (serviceName == null)
            serviceName = "UNKNOWN";    // Legacy of old functions that use "message" instead of serviceName & apiURL
        String msgToDebug = standardizeDebugMessage(serviceName, apiURL, expectedStatusCode);

        // Get Response Code from API Response
        String actualStatusCode = String.valueOf(response.getStatusCode());

        // Verify status code
        Assert.assertEquals(actualStatusCode
                , String.valueOf(expectedStatusCode)
                , "VERIFY STATUS CODE FAILED: " + msgToDebug + "/n");
    }

    public static void verifyStatusCode(String serviceName, String apiURL, String expectedStatusCode) {
        verifyStatusCode(response, serviceName, apiURL, expectedStatusCode);
    }

    //HUGE MAINTENANCE COST -> Remove all verifyStatusCode(response, expectedCode)
    @Deprecated
    public static void verifyStatusCode(Response response, String expectedStatusCode) {
        verifyStatusCode(response, null, getURL(), expectedStatusCode);
    }

    // Verify Status Code: 200, 400, 404, 500

    public static void verifyStatusCode200(String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "200");
    }

    public static void verifyStatusCode200(Response response, String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "200");
    }

    @Deprecated
    public static void verifyStatusCode200(String message) {
        verifyStatusCode(response, null, message, "200");
    }


    public static void verifyStatusCode400(String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "400");
    }

    public static void verifyStatusCode400(Response response, String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "400");
    }

    public static void verifyStatusCode400(String message) {
        verifyStatusCode(response, null, message, "400");
    }


    public static void verifyStatusCode404(String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "404");
    }

    public static void verifyStatusCode404(Response response, String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "404");
    }

    public static void verifyStatusCode404(String message) {
        verifyStatusCode(response, null, message, "404");
    }


    public static void verifyStatusCode500(String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "500");
    }

    public static void verifyStatusCode500(Response response, String serviceName, String apiURL) {
        verifyStatusCode(response, serviceName, apiURL, "500");
    }

    public static void verifyStatusCode500(String message) {
        verifyStatusCode(response, null, message, "500");
    }

    // ------------- DEBUG FUNTIONCS -------------
    public static void debugQuery() {
        debugQuery(query);
    }

    public static void debugQuery(String query) {
        String data = getResponseData(response, query);
        System.out.println("--------------------- QUERY: " + query + " ---------------------");
        if (data == null)
            System.out.println("Data is NULL");
        else
            System.out.println("Data:" + getResponseData(response, query));
        System.out.println("--------------------------------------------------");
    }

    public static void debugQueryList(String query) {
        System.out.println("--------------------- QUERY: " + query + " ---------------------");
        System.out.println("Data:" + getResponseDataList(response, query));
        System.out.println("--------------------------------------------------");
    }

    public static void debugResponse() {
        debugResponse(response);
    }

    @Deprecated
    public static void debugResponse(Response response) {
        debugResponse(response, "");
    }

    public static void debugResponse(Response response, String message) {
        System.out.println("--------------------- DEBUG: " + message + " ---------------------");
        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Message: " + getResponseData("$..message"));
        System.out.println("Response Error: " + getResponseData("$..error"));
        System.out.println("Response Query: " + query);
        System.out.println("Response Body: " + getBodyString(response));
        String seperateStr = " \\\n";

        curl = "curl --location";
        if (!getMethod().isEmpty() && !getURL().isEmpty()) {
            curl += String.format(seperateStr + "--request %s '%s'", getMethod(), getURL());
        }
        if (!getToken().isEmpty()) {
            curl += String.format(seperateStr + "--header 'Authorization: %s' ", getToken());
        }
        if (!getChecksum().isEmpty()) {
            curl += String.format(seperateStr + "--header 'checksum: %s' ", getChecksum());
        }
        if (!getBody().isEmpty()) {
            curl += seperateStr + "--header 'Content-Type: application/json'" +
                    seperateStr + "--data-raw '" + getBody() + "'";
        }

        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                curl += seperateStr + "--header '" + entry.getKey() + ":" + entry.getValue() + "'";
            }
        }
        System.out.println(curl + "\n\n" + "--------------------------------------------------");
    }


    //Used for Shop C2C
    public static void assertAndCollectError(Object actual, Object expect, String title) {
        assertAndCollectError(actual, expect, title, true);
    }

    public static void assertAndCollectError(Object actual, Object expect, String title, boolean isEqual) {
        String failedMsg = "";
        try {
            if (isEqual) {
                Assert.assertEquals(String.valueOf(actual), String.valueOf(expect));
            } else {
                Assert.assertNotEquals(String.valueOf(actual), String.valueOf(expect));
            }
            if (scenarioPassedByAssert)      //if scenarioPassedByAssert == false -> never change to true except setScenarioPassedByAssertToDefault
                scenarioPassedByAssert = true;
        } catch (AssertionError aser) {
            scenarioPassedByAssert = false;
            if (isEqual)
                failedMsg = String.format(title.toUpperCase() + ": Expect[" + expect + "] but Actual[" + actual + "]");
            else
                failedMsg = String.format(title.toUpperCase() + ": Expect[" + expect + "] but Actual[" + actual + "]");
            assertFailedMsg += failedMsg + "\n";
        }
    }

    public static void waitConstant(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getResponseCode() {
        return String.valueOf(response.getStatusCode());
    }

    public static String getResponseCode(Response response) {
        return String.valueOf(response.getStatusCode());
    }


    public static String getBodyString(Response response) {
        return response.getBody().asString();
    }

    public static String getResponseData(String jsonPathQuery) {
        return getResponseData(response, jsonPathQuery);
    }

    public static Object getResponseDataObj(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object getResponseDataObj(String jsonPathQuery) {
        return getResponseDataObj(response, jsonPathQuery);
    }


    public static String getResponseData(Response response, String jsonPathQuery) {
        String responseData;
        try {
            responseData = com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery).toString();
            //output is jsonArray with []
            responseData = responseData
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "");

            Assert.assertFalse(responseData.isEmpty(), "NOT FOUND PATH WITH QUERY: " + jsonPathQuery);
            responseData += "";       //FOUND PATH BUT DATA IS EMPTY
        } catch (Exception | AssertionError ex) {
//            System.out.println("getResponseData PATH doesn't exist");
            responseData = null;
        }
        return responseData;
    }

    public static boolean getResponseDataBoolean(String jsonPathQuery) {
        return getResponseDataBoolean(response, jsonPathQuery);
    }

    public static boolean getResponseDataBoolean(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (Exception ex) {
            return false;
        }
    }

    public static int getResponseDataInt(String jsonPathQuery) {
        return getResponseDataInt(response, jsonPathQuery);
    }

    public static int getResponseDataInt(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (ClassCastException clsError) {
            List<Integer> intList = com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
            if (intList.size() > 0)
                return intList.get(0);
            else
                return -1;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public static long getResponseDataLong(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (Exception ex) {
            return -1;
        }
    }

    public static double getResponseDataDouble(String jsonPathQuery) {
        return getResponseDataDouble(response, jsonPathQuery);
    }

    public static double getResponseDataDouble(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (ClassCastException clsE) {
            BigDecimal responseValue = com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
            return responseValue.doubleValue();
        } catch (Exception ex) {
            System.out.println("getResponseDataDouble Error: " + ex.toString());
            return -1.0;
        }
    }

    public static List<String> getResponseDataList(String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static List<String> getResponseDataList(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static List<Object> getResponseDataListObj(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static List<String> getResponseKeyList(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static List<Boolean> getResponseDataListBoolean(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static List<Integer> getResponseDataListInt(String jsonPathQuery) {
        try {
            return getResponseDataListInt(response, jsonPathQuery);
        } catch (PathNotFoundException e) {
            return null;
        }
    }

    public static List<Integer> getResponseDataListInt(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static String getResponseBodyAsString(Response response) {
        return StringEscapeUtils.unescapeJava(response.getBody().asString());
    }

    public static String getResponseMessage() {
        return getResponseMessage(response);
    }

    public static String getResponseMessage(Response response) {
        try {
            return getResponseData(response, "message");
        } catch (JsonParseException jex) {
        } catch (Exception ex) {
        }
        return "";
    }

    public static boolean compareResponseBody(Response responseA, Response responseB) {
        String bodyA = responseA.body().asString();
        String bodyB = responseB.body().asString();

        ObjectMapper mapper = new ObjectMapper();
        try {
            Assert.assertEquals(mapper.readTree(bodyA), mapper.readTree(bodyB));
            return true;
        } catch (JsonProcessingException | AssertionError e) {
            System.out.println(e);
            return false;
        }
    }

    public static List<String> getResBodyDiffKey(String jsonStrA, String jsonStrB) {
        JsonElement element = new JsonParser().parse(jsonStrA);
        com.google.gson.JsonObject jsonObjA = element.getAsJsonObject();

        element = new JsonParser().parse(jsonStrB);
        com.google.gson.JsonObject jsonObjB = element.getAsJsonObject();

        //Get all Keys of Json body
        List<String> jsonAKeys = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entriesA = jsonObjA.entrySet();//will return members of your object
        for (Map.Entry<String, JsonElement> entry : entriesA) {
            jsonAKeys.add(entry.getKey());
        }
        //Remove duplicates
        jsonAKeys = jsonAKeys.stream().distinct().collect(Collectors.toList());
        Collections.sort(jsonAKeys);

        //Get all Keys of Json body
        List<String> jsonBKeys = new ArrayList<>();
        Set<Map.Entry<String, JsonElement>> entriesB = jsonObjB.entrySet();//will return members of your object
        for (Map.Entry<String, JsonElement> entry : entriesB) {
            jsonBKeys.add(entry.getKey());
        }
        jsonBKeys = jsonBKeys.stream().distinct().collect(Collectors.toList());
        Collections.sort(jsonBKeys);

        System.out.println("jsonBKeys: " + jsonBKeys);
        System.out.println("jsonAKeys: " + jsonAKeys);

        List<String> diffEle = new ArrayList<>();
        if (jsonBKeys.size() > jsonAKeys.size()) {
            jsonBKeys.removeAll(jsonAKeys);
            diffEle.addAll(jsonBKeys);
        } else {
            jsonAKeys.removeAll(jsonBKeys);
            diffEle.addAll(jsonAKeys);
        }

        return diffEle;
//        Assert.assertTrue(diffEle.size() == 0, "Compare Res Body keys, different keys are: " + diffEle + "\n");
    }

    public static void STOP() {
        Assert.assertTrue(false, "STOP TO DEBUG");
    }

    //Response
    public static Response get(String url) {
        setParamForCurl(url, "", "get", "");
        log.info("\n----- GET URL: " + url);
        return given().contentType("application/json").get(url);
    }

    public static Response get(String authorization, String url) {
        setParamForCurl(url, authorization, "GET", "");
        log.info("\n----- GET URL: " + url
                + "\n----- TOKEN: " + authorization);
        return given().contentType("application/json").header("Authorization", authorization).get(url);
    }

    public static Response get(String authorization, String url, Map<String, String> headerMap) {
        setParamForCurl(url, authorization, "GET", "");
        log.info("\n----- GET URL: " + url
                + "\n----- TOKEN: " + authorization);

        if (headerMap != null) {
            return given()
                    .contentType("application/json")
                    .header("Authorization", authorization)
                    .headers(headerMap)
                    .get(url);
        } else {
            return null;
        }

    }


    public static Response put(String url) {
        setParamForCurl(url, "", "PUT", "");
        log.info("\n----- PUT URL: " + url);
        return given().put(url);
    }

    public static Response put(String authorization, JsonObject body, String url) {
        setParamForCurl(url, authorization, "put", body.toString());
        return put(authorization, body.toString(), url);
    }

    public static Response put(String authorization, String body, String url) {
        setParamForCurl(url, authorization, "PUT", body);

        log.info("\n----- PUT url: " + url
                + "\n----- BODY: " + body
                + "\n----- TOKEN: " + authorization
        );

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .put(url);
    }

    public static Response put(String authorization, String url) {
        setParamForCurl(url, authorization, "PUT", "");
        log.info("\n----- PUT URL: " + url
                + "\n----- TOKEN: " + authorization
        );

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .put(url);
    }

    public static Response put(JsonObject body, String url) {
        setParamForCurl(url, "", "PUT", body);
        String bodyString = String.valueOf(body);
        log.info("\n----- PUT URL: " + url
                + "\n----- Body: " + bodyString
        );

        return given().contentType("application/json").body(bodyString).put(url);
    }


    //POST
    public static Response post(String url) {
        setParamForCurl(url, "", "POST", "");
        log.info("\n----- POST url: " + url);
        return given().contentType("application/json").post(url);
    }

    public static Response post(String body, String url) {
        setParamForCurl(url, "", "POST", body);
        log.info("\n----- POST url: " + url + "\n----- Body: " + body);
        return given().contentType("application/json").body(body).post(url);
    }


    public static Response post(Object body, String url) {
        setParamForCurl(url, "", "POST", body.toString());
        return post(body.toString(), url);
    }


    private static Response post(JsonObject body, String url) {
        setParamForCurl(url, "", "POST", body);
        return post(body.toString(), url);
    }

    public static Response post(String authorization, String body, String url) {
        setParamForCurl(url, authorization, "post", body);
        log.info("\n----- POST URL: " + url + "\n----- Body: " + body
                + "\n----- TOKEN: " + authorization
        );

        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .post(url);
    }

    public static Response post(String authorization, JsonObject body, String url) {
        setParamForCurl(url, "", "POST", body);
        return post(authorization, body.toString(), url);
    }


    public static Response postNoBody(String authorization, String url) {
        setParamForCurl(url, authorization, "POST", "");
        log.info("\n----- POST url: " + url);
        return given().contentType("application/json").header("Authorization", authorization).post(url);
    }

    public static Response postImageFile(String authorization, String url, String filePath) {
        setParamForCurl(url, authorization, "POST", filePath);
        log.info("\n----- POST url: " + url + "\n----- File path: " + filePath);
        return given()
                .multiPart("image", new File(filePath))
                .header("Authorization", authorization)
                .when()
                .post(url);
    }

    public static Response postImage(String authorization, String url, String filePath) {
        setParamForCurl(url, authorization, "POST", filePath);
        log.info("\n----- POST url: " + url
                + "\n----- File path: " + filePath
                + "\n----- Token: " + authorization
        );

        String fileExtension = filePath.split("\\.")[1].toLowerCase();

        return given()
                .multiPart("media", new File(filePath), "image/" + fileExtension)
                .header("Authorization", authorization)
                .when()
                .post(url);
    }

    public static Response postCheckSum(String body, String checksumSHA1, String url) {
        setParamForCurl(url, "", "post", body);
        setParamForCurlChecksum(checksumSHA1);
        return given()
                .contentType("application/json")
                .header("checksum", checksumSHA1)
                .body(body)
                .post(url);
    }


    public static Response options(String authorization, JsonObject body, String url) {
        setParamForCurl(url, "", "OPTIONS", body);
        return given()
                .header("Authorization", authorization)
                .contentType("application/json")
                .body(body)
                .options(url);
    }

    //HEAD
    public static Response head(String url) {
        setParamForCurl(url, "", "", "");
        log.info("\n----- HEAD url: " + url
        );
        return given()
                .contentType("application/json")
                .head(url);
    }

    //DELETE
    public static Response delete(String authorization, String body, String url) {
        setParamForCurl(url, authorization, "delete", body);
        log.info("\n----- DELETE url: " + url
                + "\n----- Body: " + body
                + "\n----- Token: " + authorization
        );
        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .body(body)
                .delete(url);
    }

    public static Response delete(String body, String url) {
        setParamForCurl(url, "", "", body);

        log.info("\n----- DELETE url: " + url);
        return given().contentType("application/json").body(body).delete(url);
    }

    public static Response deleteNoBody(String authorization, String url) {
        setParamForCurl(url, authorization, "delete", "");

        log.info("\n----- DELETE url: " + url);
        return given()
                .contentType("application/json")
                .header("Authorization", authorization)
                .delete(url);
    }

    public static Response delete(String url) {
        setParamForCurl(url, "", "", "");
        log.info("\n----- DELETE url: " + url);
        return given().contentType("application/json").delete(url);
    }

    public static Response postImageShop(String authorization, String url, String filePath) {
        setParamForCurl(url, authorization, "post", filePath);

        String fileExtension = filePath.split("\\.")[1].toLowerCase();

        return given()
                .multiPart("media", new File(filePath), "image/" + fileExtension)
                .header("Authorization", authorization)
                .when()
                .post(url);
    }

    //Monggo Support
    private static MongoClient dbClient = new MongoClient(new MongoClientURI("mongodb://10.60.3.2:37017"));
    private static DB db = null;
    private static DBCollection dbCollection = null;
    private static DBObject dbObject = null;
    private static DBCursor cursor = null;
    //Query
    public static BasicDBObjectBuilder dbQuery = BasicDBObjectBuilder.start();

    public static void setDatabase(String dbName) {
        db = dbClient.getDB(dbName);
    }

    public static void addQuery(String key, Object value) {
        dbQuery.append(key, value);
    }

    public static void initQuery() {
        dbQuery = null;
        dbQuery = BasicDBObjectBuilder.start();
    }

    public static void setQuery(String key, Object value) {
        dbQuery = null;
        dbQuery = BasicDBObjectBuilder.start();
        dbQuery.append(key, value);
    }

    public static void setCollection(String collectionName) {
        dbCollection = db.getCollection(collectionName);
        System.out.println("COLLECTION IS SET TO: " + collectionName);
    }

    public static DBObject getDbObject() {
        dbObject = dbQuery.get();
        return dbObject;
    }

    public static void getDataByQuery(String query) {
        dbObject = dbQuery.get();
        String[] queryList = query.split("\\.");
        DBCursor cursor = dbCollection.find(dbObject);

        while (cursor.hasNext()) {

        }

        for (int i = 0; i < queryList.length; i++) {
//            DBObject
        }
    }

    private static String getMongoData() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        System.out.println(((DBObject) price.get("price")).get("vnd"));
//        while (cursor.hasNext()) {
//            DBObject price = cursor.next();
//            ((DBObject) price.get("price")).get("vnd");
//        }
        return null;
    }


    //REFACTOR
    public static String getPriceVND() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        return String.valueOf(((DBObject) price.get("price")).get("vnd"));
    }

    public static String getPriceCredit() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        return String.valueOf(((DBObject) price.get("price")).get("credit"));
    }

    public static String getPricePromotion() {
        dbObject = dbQuery.get();
        DBCursor cursor = dbCollection.find(dbObject);
        DBObject price = cursor.next();
        return String.valueOf(((DBObject) price.get("price")).get("promotion"));
    }

    public static String decodeSHA1(String text) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(text);
    }

    private static void setParamForCurl(String url, String authorization, String method, Object body) {
        setURL(url);     //For info
        setToken(authorization);
        setMethod(method.toUpperCase());
        setBody(String.valueOf(body));
        setChecksum("");
    }

    private static void setParamForCurlChecksum(String checksum) {
        setChecksum(checksum);
    }

    ///PROCESS STRING AS JSON
    private static JsonParser parser = new JsonParser();
    private static com.google.gson.JsonObject jsonObjFromString;

    public static String getPropertyFromJsonStr(String bodyJSONData, String paramName) {
        jsonObjFromString = parser.parse(bodyJSONData).getAsJsonObject();
        return jsonObjFromString.get(paramName).getAsString();
    }

    public static com.google.gson.JsonObject convertStrToJson(String jsonStr) {
        return new JsonParser().parse(jsonStr).getAsJsonObject();
    }

    public static String getCurrentDateByFormat(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeStampUnix_yyyyMMdd(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateStr);
            long unixTime = date.getTime() / 1000;
            return String.valueOf(unixTime);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getTimeStamp() {
        return "" + System.currentTimeMillis() / 1000;
    }

    public static String getCurrentTimeStampUnix() {
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }

    //VUHOANG DEBUG
    public static String getTimeStampFromCurrentTime(int days) {
        int endTime;
        endTime = Integer.parseInt(getTimeStamp()) + (86400 * days); // 1 day equals 86400
        return String.valueOf(endTime);
    }

    public static JsonArray getJsonArrayValue(List<String> values) {
        JsonArray jArray = new JsonArray();
        JsonPrimitive element;
        for (String value : values) {
            element = new JsonPrimitive(value);
            jArray.add(element);
        }
        return jArray;
    }

    public static JsonArray getJsonArrayValueInt(List<Integer> values) {
        JsonArray jArray = new JsonArray();
        JsonPrimitive element;
        for (int value : values) {
            element = new JsonPrimitive(value);
            jArray.add(element);
        }
        return jArray;
    }

    // LocalDateTime
    public static String getMinusDateTimeFromCurrentTime(int day) {
        return String.valueOf(LocalDateTime.now(ZoneId.of("GMT+00:00")).minusDays(day)).substring(0, 23) + "Z";
    }

    public static String getPlusDateTimeFromCurrentTime(int day) {
        return String.valueOf(LocalDateTime.now(ZoneId.of("GMT+00:00")).plusDays(day)).substring(0, 23) + "Z";
    }

    public static String getCurrentTime_PlusMinute_GMT7(int minute) {
        return String.valueOf(LocalDateTime.now(ZoneId.of("GMT+07:00")).plusMinutes(minute)).substring(0, 21) + "Z";
    }

    public static String getCurrentTime_PlusSecond_GMT7(int second) {
        return String.valueOf(LocalDateTime.now(ZoneId.of("GMT+07:00")).plusSeconds(second)).substring(0, 21) + "Z";
    }

    public static String getYesterday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        // Get Yesterday
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        return dateFormat.format(yesterday) + ".1Z";
    }

    public static String getToday(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        // Get Today
        final Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        return dateFormat.format(today);
    }

    public static String getToday_ddMMyyyy() {
        return getToday("dd-MM-yyyy");
    }

    public static String getToday_MMddyyyy() {
        return getToday("MM-dd-yyyy");
    }

    public static String getToday_yyyyMMdd() {
        return getToday("yyyy-MM-dd");
    }

    private static String getToday_nextMonth(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        // Get Today of next Month
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        Date todayNextMonth = cal.getTime();
        return dateFormat.format(todayNextMonth);
    }

    public static String getToday_nextMonth_MMddyyyy() {
        return getToday_nextMonth("MM-dd-yyyy");
    }

    public static String getTomorrow_MMddyyyy() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        // Get Today
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date today = cal.getTime();
        return dateFormat.format(today);
    }

    private static String getTimestamp_fromInputDate(String dateStr, String format) {
        // Convert String to Date
        Date dateFromStr = null;
        try {
            dateFromStr =new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Convert Date to Timestamp
        Timestamp ts = new Timestamp(dateFromStr.getTime());
        return String.valueOf(ts);
    }

    public static String getTimestamp_fromDate_MMddyyyy(String date) {
        return getTimestamp_fromInputDate(date, "MM-dd-yyyy");
    }

    public static String getYearCurrent(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return String.valueOf(year);
    }
}
