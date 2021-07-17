package AutomationLibrary.Execution;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    int counter = 0;
    int retryLimit = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            if (counter < retryLimit) {
                counter++;
                System.out.println("Retry number: " + counter + "/" + retryLimit);

                return true;
            }
        }
        return false;
    }
}
