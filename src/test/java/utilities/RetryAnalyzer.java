package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (!result.isSuccess()) {

            if (retryCount < MAX_RETRY_COUNT) {

                retryCount++;
                result.setAttribute(
                        "RetryCount",
                        retryCount);

                System.out.println(
                        "Retrying Test Case: "
                        		 + result.getMethod().getMethodName()
                        		+ result.getName()
                        + " | Attempt "
                        + retryCount);

                return true;
            }
        }

        return false;
    }
    
}