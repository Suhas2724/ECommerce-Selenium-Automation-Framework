package org.seleniumFramework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    private LoggerUtility() {
        // Private constructor to prevent instantiation
    }


    public static Logger getLogger(Class<?> clazz) {
        Logger logger = null;
        if (logger == null) {
            logger = LogManager.getLogger(clazz);
        }
        return logger;
    }
}
