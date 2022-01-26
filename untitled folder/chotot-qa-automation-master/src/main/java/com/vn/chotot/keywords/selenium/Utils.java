package com.vn.chotot.keywords.selenium;

import com.vn.chotot.exception.FailureHandling;
import com.vn.chotot.exception.StepFailedException;
import com.vn.chotot.logger.Log4jFactory;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.vn.chotot.configuration.WaitTime.minWaitTime;
import static com.vn.chotot.exception.ExceptionHandler.exceptionHandler;
import static com.vn.chotot.keywords.selenium.Wait.waitForAllElementVisible;

public class Utils {

    static final Logger log = Log4jFactory.instance().createClassLogger(Utils.class);

    public static void delayStep(int timeSeconds) {
        try {
            Thread.sleep((long) timeSeconds * 1000);
        } catch (InterruptedException ex) {
            log.error("Interrupted!");
            Thread.currentThread().interrupt();
        }
    }

    public static boolean verifyMatch(
            String actualString,
            String expectedString,
            Boolean isUsingRegex,
            FailureHandling failureHandling) {
        boolean isMatch; // Default is NOT MATCH
        if (isUsingRegex) isMatch = actualString.matches(expectedString);
        else isMatch = actualString.equals(expectedString);

        if (!isMatch)
            exceptionHandler(
                    new StepFailedException(
                            "Actual string \""
                                    + actualString
                                    + "\" does NOT match with expected string \""
                                    + expectedString
                                    + "\""),
                    failureHandling);

        return isMatch;
    }

    /**
     * This function is to check 2 Strings matches/equals
     *
     * @author Quoc Tran Last Updated: 2019/06/20 Change Log: 2019/06/20 Vu Hoang Change: result =
     * actualString... to isNotMatch = !actualString... Change: if(result) exceptionHanler... to
     * if(!isNotMatch) exceptionHanler...
     */
    public static boolean verifyNotMatch(
            String actualString,
            String expectedString,
            Boolean isUsingRegex,
            FailureHandling failureHandling) {
        boolean isNotMatch; // Default is MATCH
        if (isUsingRegex) isNotMatch = !actualString.matches(expectedString); // Check not match
        else isNotMatch = !actualString.equals(expectedString);

        if (!isNotMatch) // If NOT isNotMatch (Match)
            exceptionHandler(
                    new StepFailedException(
                            "Actual string \""
                                    + actualString
                                    + "\" MATCHES with expected string \""
                                    + expectedString
                                    + "\""),
                    failureHandling);

        return isNotMatch;
    }

    public static boolean verifyEquals(
            Object actualObject, Object expectedObject, FailureHandling failureHandling) {
        boolean result = actualObject.equals(expectedObject);
        if (!result)
            exceptionHandler(
                    new StepFailedException(
                            "The objects \"" + actualObject + "\" and \"" + expectedObject + "\" are NOT equal"),
                    failureHandling);

        return result;
    }

    public static boolean verifyGreater(
            double actual, double expected, FailureHandling failureHandling) {
        boolean result = (actual - expected) > 0;
        if (!result)
            exceptionHandler(
                    new StepFailedException(
                            "The actual number " + actual + " are NOT greater than expected number " + expected),
                    failureHandling);

        return result;
    }

    public static boolean verifyNotEquals(
            Object actualObject, Object expectedObject, FailureHandling failureHandling) {
        boolean result = actualObject.equals(expectedObject);
        if (result)
            exceptionHandler(
                    new StepFailedException(
                            "The objects \"" + actualObject + "\" and \"" + expectedObject + "\" are equal"),
                    failureHandling);

        return result;
    }

    public static String getProjectDir() {
        return System.getProperty("user.dir");
    }

    public static void verifyOrderListDateTime(
            List<WebElement> lisElement, boolean isAscending, FailureHandling failureHandling) {
        // Get list date time string
        List<String> listDateTimeString = new ArrayList<>();

        // Return if non existing items
        waitForAllElementVisible(lisElement, minWaitTime);
        if (lisElement.isEmpty()) {
            log.debug("No existing list date time");
            return;
        }

        for (WebElement webElement : lisElement) {
            listDateTimeString.add(webElement.getText());
        }

        // Extract list date time value to map
        List<Integer> listDateTime = new ArrayList<>();
        List<String> listPostString = new ArrayList<>();
        String currentValue;
        for (String s : listDateTimeString) {
            currentValue = s;
            try {
                listDateTime.add(Integer.valueOf(currentValue.substring(0, currentValue.indexOf(' '))));
            } catch (NumberFormatException ex) {
                listDateTime.add(0); // To handle exception case "vài giây trước"
            }
            listPostString.add(currentValue.substring(currentValue.indexOf(' ')));
        }

        // Verify order of list value
        if (isAscending) {
            for (int x = 0; x < listDateTime.size() - 1; x++) {
                if (listPostString.get(x).equals(listPostString.get(x + 1))) {
                    if (!((listDateTime.get(x) - listDateTime.get(x + 1)) <= 0)) {
                        exceptionHandler(
                                new StepFailedException(
                                        "Current time \""
                                                + listDateTime.get(x)
                                                + "\" is GREATER than previous time \""
                                                + listDateTime.get(x + 1)),
                                failureHandling);
                    }
                }
            }
        } else {
            for (int x = 0; x < listDateTime.size() - 1; x++) {
                if (listPostString.get(x).equals(listPostString.get(x + 1))) {
                    if (!((listDateTime.get(x) - listDateTime.get(x + 1)) >= 0)) {
                        exceptionHandler(
                                new StepFailedException(
                                        "Current time \""
                                                + listDateTime.get(x)
                                                + "\" is LESS than previous time \""
                                                + listDateTime.get(x + 1)),
                                failureHandling);
                    }
                }
            }
        }
    }
}
