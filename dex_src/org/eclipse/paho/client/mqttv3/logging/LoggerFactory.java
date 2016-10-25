package org.eclipse.paho.client.mqttv3.logging;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoggerFactory {
    private static final String CLASS_NAME;
    public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";
    static Class class$0;
    static Class class$1;
    private static String jsr47LoggerClassName;
    private static String overrideloggerClassName;

    static {
        Class cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.logging.LoggerFactory");
                class$0 = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        CLASS_NAME = cls.getName();
        overrideloggerClassName = null;
        jsr47LoggerClassName = "org.eclipse.paho.client.mqttv3.logging.JSR47Logger";
    }

    public static Logger getLogger(String str, String str2) {
        String str3 = overrideloggerClassName;
        if (str3 == null) {
            str3 = jsr47LoggerClassName;
        }
        Logger logger = getLogger(str3, ResourceBundle.getBundle(str), str2, null);
        if (logger != null) {
            return logger;
        }
        throw new MissingResourceException("Error locating the logging class", CLASS_NAME, str2);
    }

    private static Logger getLogger(String str, ResourceBundle resourceBundle, String str2, String str3) {
        try {
            Logger logger;
            Class forName = Class.forName(str);
            if (forName != null) {
                try {
                    logger = (Logger) forName.newInstance();
                    logger.initialise(resourceBundle, str2, str3);
                } catch (IllegalAccessException e) {
                    return null;
                } catch (InstantiationException e2) {
                    return null;
                } catch (ExceptionInInitializerError e3) {
                    return null;
                } catch (SecurityException e4) {
                    return null;
                }
            }
            logger = null;
            return logger;
        } catch (NoClassDefFoundError e5) {
            return null;
        } catch (ClassNotFoundException e6) {
            return null;
        }
    }

    public static String getLoggingProperty(String str) {
        try {
            Class forName = Class.forName("java.util.logging.LogManager");
            Object invoke = forName.getMethod("getLogManager", new Class[0]).invoke(null, null);
            String str2 = "getProperty";
            Class[] clsArr = new Class[1];
            Class cls = class$1;
            if (cls == null) {
                cls = Class.forName("java.lang.String");
                class$1 = cls;
            }
            clsArr[0] = cls;
            return (String) forName.getMethod(str2, clsArr).invoke(invoke, new Object[]{str});
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        } catch (Exception e2) {
            try {
                return null;
            }
        }
    }

    public static void setLogger(String str) {
        overrideloggerClassName = str;
    }
}
