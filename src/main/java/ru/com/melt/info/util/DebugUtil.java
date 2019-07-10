package ru.com.melt.info.util;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class DebugUtil {
    public static void turnOnShowMongoQuery() {
        Logger sqlLogger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.DEBUG);
        Logger descLogger = (Logger) LoggerFactory.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.TRACE);
    }

    public static void turnOffShowMongoQuery() {
        Logger sqlLogger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.INFO);
        Logger descLogger = (Logger) LoggerFactory.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        descLogger.setLevel(Level.INFO);
    }
}