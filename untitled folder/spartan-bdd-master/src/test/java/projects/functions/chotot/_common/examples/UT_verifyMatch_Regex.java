package projects.functions.chotot._common.examples;

import com.vn.chotot.exception.FailureHandling;
import org.testng.annotations.Test;

import static com.vn.chotot.keywords.selenium.Utils.verifyMatch;

public class UT_verifyMatch_Regex {
    @Test
    public void UT_Sensitive_Case() {
        verifyMatch(
                "Chuyen tRAng BDS", "(?i)(.*)Chuyen trang(.*)", true, FailureHandling.STOP_ON_FAILURE);
    }

    @Test
    public void UT_Sensitive_Case_Wrong() {
        verifyMatch("Chuyen tRAng BDS", "(.*)Chuyen trang(.*)", true, FailureHandling.STOP_ON_FAILURE);
    }
}
