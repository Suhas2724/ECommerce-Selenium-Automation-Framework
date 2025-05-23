package org.seleniumFramework.listeners;

import org.seleniumFramework.constants.Environment;
import org.seleniumFramework.utils.PropertiesUtility;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.ConcurrentHashMap;

public class RetryAnalyzer implements IRetryAnalyzer {

    // Track retry count per test method
    private static final ConcurrentHashMap<String, Integer> retryCounts = new ConcurrentHashMap<>();
    private static final int MAX_RETRY_COUNT = Integer.parseInt(PropertiesUtility.readProperty(Environment.QA,"MAX_RETRY_COUNT"));

    @Override
    public boolean retry(ITestResult result) {
        String key = result.getMethod().getQualifiedName();
        int currentCount = retryCounts.getOrDefault(key, 0);
        if (currentCount < MAX_RETRY_COUNT) {
            retryCounts.put(key, currentCount + 1);
            return true;
        }
        return false;
    }

}
