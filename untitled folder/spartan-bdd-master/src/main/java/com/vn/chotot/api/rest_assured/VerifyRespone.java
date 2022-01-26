package com.vn.chotot.api.rest_assured;

import com.google.gson.JsonParseException;
import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.logger.Log4jFactory;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.vn.chotot.api.rest_assured.RestAssure.getRequestURL;
import static com.vn.chotot.exception.ExceptionHandler.setExceptionDebug;
import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;
import static com.vn.chotot.keywords.selenium.Utils.verifyNotMatch;

public class VerifyRespone {

    static final Logger log = Log4jFactory.instance().createClassLogger(VerifyRespone.class);

    public static String getResponseBodyAsString(Response response) {
        return StringEscapeUtils.unescapeJava(response.getBody().asString());
    }

    public static String getPropertyValue(Response response, String propertyName) {
        String result = getResponseBodyAsString(response);
        JsonPath jsonPath = new JsonPath(result);
        String value = jsonPath.getString(propertyName);
        log.info("\n----- Read Response <" + propertyName + "> " + result);
        log.info("\n----- <" + propertyName + "> : " + value);
        return value;
    }

    public static List<String> getPropertyValueList(Response response, String propertyName) {
        String result = getResponseBodyAsString(response);
        JsonPath jsonPath = new JsonPath(result);
        List<String> value = jsonPath.getList(propertyName);
        log.info("\n----- <" + propertyName + "> : " + value);
        return value;
    }

    public static boolean verifyStatusCode(Response response, String expectedStatusCode) {
        return verifyStatusCode(response, expectedStatusCode, true);
    }

    public static boolean verifyStatusCode(Response response, String expectedStatusCode, boolean isMatched) {
        String result = getResponseBodyAsString(response);
        String statusCode = String.valueOf(response.getStatusCode());
        log.info("\n----- Response code <" + statusCode + "> \n-----Body:\n " + result);

        if(isMatched){
            return verifyMatch(statusCode, expectedStatusCode, false, FailureHandling.CONTINUE_ON_FAILURE);
        } else {
            return verifyNotMatch(statusCode, expectedStatusCode, false, FailureHandling.CONTINUE_ON_FAILURE);
        }
    }

    public static boolean verifyStatusCode(
            Response response, String expectedStatus, FailureHandling failureHandling) {
        String result = getResponseBodyAsString(response);
        String statusCode = String.valueOf(response.getStatusCode());

        log.info("\n----- Response code <" + statusCode + "> \n-----Body:\n " + result);
        if (failureHandling.equals(FailureHandling.STOP_ON_FAILURE) && !statusCode.equals("200")) {
            String req_url = getRequestURL();
            int res_code = response.statusCode();
            setExceptionDebug(String.format("Request URL: %s\n-- Response message: %s-- Response code: %d", req_url, result, res_code));
        }

        return verifyMatch(statusCode, expectedStatus, false, failureHandling);
    }

    @Deprecated
    public static List<String> getResponseDataList(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    @Deprecated
    public static List<String> getResponseKeyList(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    @Deprecated
    public static List<Boolean> getResponseDataListBoolean(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    @Deprecated
    public static List<Integer> getResponseDataListInt(Response response, String jsonPathQuery) {
        return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
    }

    public static String getResponseData(String bodyResponse, String jsonPathQuery) {
        try {
            String responseData = com.jayway.jsonpath.JsonPath.parse(bodyResponse).read(jsonPathQuery) + "";
            responseData = responseData
                    .replace("[", "")
                    .replace("]","")
                    .replace("\"","");
            return responseData;
        } catch (Exception ex) {
            return null;
        }
    }

    @Deprecated
    public static String getResponseData(Response response, String jsonPathQuery) {
        try {
            String responseData = com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery) + "";
            responseData = responseData
                                .replace("[", "")
                                .replace("]","")
                                .replace("\"","");
            return responseData;
        } catch (Exception ex) {
            return null;
        }
    }

    @Deprecated
    public static int getResponseDataInt(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (Exception ex) {
            return -1;
        }
    }

    @Deprecated
    public static double getResponseDataDouble(Response response, String jsonPathQuery) {
        try {
            return com.jayway.jsonpath.JsonPath.parse(response.body().asString()).read(jsonPathQuery);
        } catch (Exception ex) {
            return -1.0;
        }
    }

    public static String getResponseCode(Response response) {
        return String.valueOf(response.getStatusCode());
    }

    public static String getResponseMessage(Response response) {
        try {
            return getPropertyValue(response, "message");
        } catch (JsonParseException jex) {
        } catch (Exception ex) {
        }
        return "";
    }
}
