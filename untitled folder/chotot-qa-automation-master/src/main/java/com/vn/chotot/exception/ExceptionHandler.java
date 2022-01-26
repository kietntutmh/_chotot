package com.vn.chotot.exception;

import com.vn.chotot.driver.selenium.DriverFactory;
import com.vn.chotot.logger.Log4jFactory;

import java.util.Date;

import static com.vn.chotot.helper.DateTime.getDateInStringFormat;

public class ExceptionHandler {
    private static String exceptionDebug = "";

    public static String getExceptionDebug() {
        return exceptionDebug;
    }

    public static void setExceptionDebug(String exception) {
        try {
            if (!exceptionDebug.contains(exception)) // Distinct on duplicate messages
                exceptionDebug += "\n-- " + exception;
                return;
        } catch (NullPointerException ex) {
            // Process for case: get Throwable but null value
        }
    }

    public static void resetExceptionDebug() {
        exceptionDebug = "";
    }

    public static void exceptionHandler(Throwable throwable, FailureHandling failureHandling) {
        switch (failureHandling) {
            case CONTINUE_ON_FAILURE:
                Log4jFactory.instance().logFailedAndContinue(throwable);
                //takeFailureScreenshotBySteps("CONTINUE_ON_FAILURE");
                break;
            case WARNING:
                Log4jFactory.instance().logWarning(throwable);
                break;
            default:
                Log4jFactory.instance().logFailedAndStop(throwable);
        }
    }

    private static void takeFailureScreenshotBySteps(String failure) {
        String failedScreenshot =
                getDateInStringFormat(new Date(), "yyyyMMdd.HHmmss") + "__" + failure + ".jpg";
        String imagePath = "/debug/" + failedScreenshot;
        DriverFactory.instance().takeScreenshot(imagePath);
    }
}
