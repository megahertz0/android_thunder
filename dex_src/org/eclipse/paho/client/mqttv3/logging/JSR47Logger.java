package org.eclipse.paho.client.mqttv3.logging;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.MemoryHandler;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class JSR47Logger implements Logger {
    private String catalogID;
    private Logger julLogger;
    private ResourceBundle logMessageCatalog;
    private String loggerName;
    private String resourceName;
    private ResourceBundle traceMessageCatalog;

    public JSR47Logger() {
        this.julLogger = null;
        this.logMessageCatalog = null;
        this.traceMessageCatalog = null;
        this.catalogID = null;
        this.resourceName = null;
        this.loggerName = null;
    }

    public void initialise(ResourceBundle resourceBundle, String str, String str2) {
        this.traceMessageCatalog = this.logMessageCatalog;
        this.resourceName = str2;
        this.loggerName = str;
        this.julLogger = Logger.getLogger(this.loggerName);
        this.logMessageCatalog = resourceBundle;
        this.traceMessageCatalog = resourceBundle;
        this.catalogID = this.logMessageCatalog.getString("0");
    }

    public void setResourceName(String str) {
        this.resourceName = str;
    }

    public boolean isLoggable(int i) {
        return this.julLogger.isLoggable(mapJULLevel(i));
    }

    public void severe(String str, String str2, String str3) {
        log(1, str, str2, str3, null, null);
    }

    public void severe(String str, String str2, String str3, Object[] objArr) {
        log(1, str, str2, str3, objArr, null);
    }

    public void severe(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(1, str, str2, str3, objArr, th);
    }

    public void warning(String str, String str2, String str3) {
        log(SimpleLog.LOG_LEVEL_DEBUG, str, str2, str3, null, null);
    }

    public void warning(String str, String str2, String str3, Object[] objArr) {
        log(SimpleLog.LOG_LEVEL_DEBUG, str, str2, str3, objArr, null);
    }

    public void warning(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(SimpleLog.LOG_LEVEL_DEBUG, str, str2, str3, objArr, th);
    }

    public void info(String str, String str2, String str3) {
        log(MqttConnectOptions.MQTT_VERSION_3_1, str, str2, str3, null, null);
    }

    public void info(String str, String str2, String str3, Object[] objArr) {
        log(MqttConnectOptions.MQTT_VERSION_3_1, str, str2, str3, objArr, null);
    }

    public void info(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(MqttConnectOptions.MQTT_VERSION_3_1, str, str2, str3, objArr, th);
    }

    public void config(String str, String str2, String str3) {
        log(MqttConnectOptions.MQTT_VERSION_3_1_1, str, str2, str3, null, null);
    }

    public void config(String str, String str2, String str3, Object[] objArr) {
        log(MqttConnectOptions.MQTT_VERSION_3_1_1, str, str2, str3, objArr, null);
    }

    public void config(String str, String str2, String str3, Object[] objArr, Throwable th) {
        log(MqttConnectOptions.MQTT_VERSION_3_1_1, str, str2, str3, objArr, th);
    }

    public void log(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
        Level mapJULLevel = mapJULLevel(i);
        if (this.julLogger.isLoggable(mapJULLevel)) {
            logToJsr47(mapJULLevel, str, str2, this.catalogID, this.logMessageCatalog, str3, objArr, th);
        }
    }

    public void fine(String str, String str2, String str3) {
        trace(SimpleLog.LOG_LEVEL_ERROR, str, str2, str3, null, null);
    }

    public void fine(String str, String str2, String str3, Object[] objArr) {
        trace(SimpleLog.LOG_LEVEL_ERROR, str, str2, str3, objArr, null);
    }

    public void fine(String str, String str2, String str3, Object[] objArr, Throwable th) {
        trace(SimpleLog.LOG_LEVEL_ERROR, str, str2, str3, objArr, th);
    }

    public void finer(String str, String str2, String str3) {
        trace(SimpleLog.LOG_LEVEL_FATAL, str, str2, str3, null, null);
    }

    public void finer(String str, String str2, String str3, Object[] objArr) {
        trace(SimpleLog.LOG_LEVEL_FATAL, str, str2, str3, objArr, null);
    }

    public void finer(String str, String str2, String str3, Object[] objArr, Throwable th) {
        trace(SimpleLog.LOG_LEVEL_FATAL, str, str2, str3, objArr, th);
    }

    public void finest(String str, String str2, String str3) {
        trace(SimpleLog.LOG_LEVEL_OFF, str, str2, str3, null, null);
    }

    public void finest(String str, String str2, String str3, Object[] objArr) {
        trace(SimpleLog.LOG_LEVEL_OFF, str, str2, str3, objArr, null);
    }

    public void finest(String str, String str2, String str3, Object[] objArr, Throwable th) {
        trace(SimpleLog.LOG_LEVEL_OFF, str, str2, str3, objArr, th);
    }

    public void trace(int i, String str, String str2, String str3, Object[] objArr, Throwable th) {
        Level mapJULLevel = mapJULLevel(i);
        if (this.julLogger.isLoggable(mapJULLevel)) {
            logToJsr47(mapJULLevel, str, str2, this.catalogID, this.traceMessageCatalog, str3, objArr, th);
        }
    }

    private String getResourceMessage(ResourceBundle resourceBundle, String str) {
        try {
            return resourceBundle.getString(str);
        } catch (MissingResourceException e) {
            return str;
        }
    }

    private void logToJsr47(Level level, String str, String str2, String str3, ResourceBundle resourceBundle, String str4, Object[] objArr, Throwable th) {
        if (str4.indexOf("=====") == -1) {
            str4 = MessageFormat.format(getResourceMessage(resourceBundle, str4), objArr);
        }
        LogRecord logRecord = new LogRecord(level, new StringBuffer(String.valueOf(this.resourceName)).append(": ").append(str4).toString());
        logRecord.setSourceClassName(str);
        logRecord.setSourceMethodName(str2);
        logRecord.setLoggerName(this.loggerName);
        if (th != null) {
            logRecord.setThrown(th);
        }
        this.julLogger.log(logRecord);
    }

    private Level mapJULLevel(int i) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return Level.SEVERE;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return Level.WARNING;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                return Level.INFO;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                return Level.CONFIG;
            case SimpleLog.LOG_LEVEL_ERROR:
                return Level.FINE;
            case SimpleLog.LOG_LEVEL_FATAL:
                return Level.FINER;
            case SimpleLog.LOG_LEVEL_OFF:
                return Level.FINEST;
            default:
                return null;
        }
    }

    public String formatMessage(String str, Object[] objArr) {
        try {
            return this.logMessageCatalog.getString(str);
        } catch (MissingResourceException e) {
            return str;
        }
    }

    public void dumpTrace() {
        dumpMemoryTrace47(this.julLogger);
    }

    protected static void dumpMemoryTrace47(Logger logger) {
        while (logger != null) {
            Handler[] handlers = logger.getHandlers();
            int i = 0;
            while (i < handlers.length) {
                if (handlers[i] instanceof MemoryHandler) {
                    synchronized (handlers[i]) {
                        ((MemoryHandler) handlers[i]).push();
                    }
                    return;
                }
                i++;
            }
            logger = logger.getParent();
        }
    }
}
