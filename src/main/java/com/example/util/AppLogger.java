package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anhhlhq
 * @email anhlhq@pvcombank.com.vn
 * @since 27/01/2024 21:01
 */
public class AppLogger {
    public static void info(String message, Object... args) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.info(message, args);
    }

    public static void error(String message, Object... args) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.error("ERROR: " + message, args);
    }

    public static void debug(String message, Object... args) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.debug("DEBUG: " + message, args);
    }

    public static void warn(String message, Object... args) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.warn("WARN: " + message, args);
    }

    public static void trace(String message, Object... args) {
        String className = new Throwable().getStackTrace()[1].getClassName();
        Logger logger = LoggerFactory.getLogger(className);
        logger.trace("TRACE: " + message, args);
    }
}
