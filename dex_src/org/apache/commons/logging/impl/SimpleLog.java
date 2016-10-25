package org.apache.commons.logging.impl;

import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.download.proguard.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.android.agoo.common.AgooConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SimpleLog implements Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_DEBUG = 2;
    public static final int LOG_LEVEL_ERROR = 5;
    public static final int LOG_LEVEL_FATAL = 6;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_OFF = 7;
    public static final int LOG_LEVEL_TRACE = 1;
    public static final int LOG_LEVEL_WARN = 4;
    static Class class$java$lang$Thread = null;
    static Class class$org$apache$commons$logging$impl$SimpleLog = null;
    protected static DateFormat dateFormatter = null;
    protected static boolean showDateTime = false;
    protected static boolean showLogName = false;
    protected static boolean showShortName = false;
    protected static final Properties simpleLogProps;
    protected static final String systemPrefix = "org.apache.commons.logging.simplelog.";
    protected int currentLogLevel;
    protected String logName;
    private String prefix;

    class AnonymousClass_1 implements PrivilegedAction {
        private final String val$name;

        AnonymousClass_1(String str) {
            this.val$name = str;
        }

        public Object run() {
            ClassLoader access$000 = SimpleLog.access$000();
            return access$000 != null ? access$000.getResourceAsStream(this.val$name) : ClassLoader.getSystemResourceAsStream(this.val$name);
        }
    }

    static ClassLoader access$000() {
        return getContextClassLoader();
    }

    static {
        simpleLogProps = new Properties();
        showLogName = false;
        showShortName = true;
        showDateTime = false;
        dateFormatter = null;
        InputStream resourceAsStream = getResourceAsStream("simplelog.properties");
        if (resourceAsStream != null) {
            try {
                simpleLogProps.load(resourceAsStream);
                resourceAsStream.close();
            } catch (IOException e) {
            }
        }
        showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
        showShortName = getBooleanProperty("org.apache.commons.logging.simplelog.showShortLogname", showShortName);
        showDateTime = getBooleanProperty("org.apache.commons.logging.simplelog.showdatetime", showDateTime);
        showLogName = getBooleanProperty("org.apache.commons.logging.simplelog.showlogname", showLogName);
        if (showDateTime) {
            dateFormatter = new SimpleDateFormat(getStringProperty("org.apache.commons.logging.simplelog.dateformat", "yyyy/MM/dd HH:mm:ss:SSS zzz"));
        }
    }

    private static String getStringProperty(String str) {
        String property = System.getProperty(str);
        return property == null ? simpleLogProps.getProperty(str) : property;
    }

    private static String getStringProperty(String str, String str2) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? str2 : stringProperty;
    }

    private static boolean getBooleanProperty(String str, boolean z) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? z : "true".equalsIgnoreCase(stringProperty);
    }

    public SimpleLog(String str) {
        this.logName = null;
        this.prefix = null;
        this.logName = str;
        setLevel(LOG_LEVEL_INFO);
        String stringProperty = getStringProperty(new StringBuffer("org.apache.commons.logging.simplelog.log.").append(this.logName).toString());
        int lastIndexOf = String.valueOf(str).lastIndexOf(".");
        while (stringProperty == null && lastIndexOf >= 0) {
            str = str.substring(LOG_LEVEL_ALL, lastIndexOf);
            stringProperty = getStringProperty(new StringBuffer("org.apache.commons.logging.simplelog.log.").append(str).toString());
            lastIndexOf = String.valueOf(str).lastIndexOf(".");
        }
        if (stringProperty == null) {
            stringProperty = getStringProperty("org.apache.commons.logging.simplelog.defaultlog");
        }
        if ("all".equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_ALL);
        } else if (AgooConstants.MESSAGE_TRACE.equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_TRACE);
        } else if ("debug".equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_DEBUG);
        } else if ("info".equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_INFO);
        } else if ("warn".equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_WARN);
        } else if (XiaomiOAuthConstants.EXTRA_ERROR_CODE_2.equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_ERROR);
        } else if ("fatal".equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_FATAL);
        } else if ("off".equalsIgnoreCase(stringProperty)) {
            setLevel(LOG_LEVEL_OFF);
        }
    }

    public void setLevel(int i) {
        this.currentLogLevel = i;
    }

    public int getLevel() {
        return this.currentLogLevel;
    }

    protected void log(int i, Object obj, Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        if (showDateTime) {
            stringBuffer.append(dateFormatter.format(new Date()));
            stringBuffer.append(" ");
        }
        switch (i) {
            case LOG_LEVEL_TRACE:
                stringBuffer.append("[TRACE] ");
                break;
            case LOG_LEVEL_DEBUG:
                stringBuffer.append("[DEBUG] ");
                break;
            case LOG_LEVEL_INFO:
                stringBuffer.append("[INFO] ");
                break;
            case LOG_LEVEL_WARN:
                stringBuffer.append("[WARN] ");
                break;
            case LOG_LEVEL_ERROR:
                stringBuffer.append("[ERROR] ");
                break;
            case LOG_LEVEL_FATAL:
                stringBuffer.append("[FATAL] ");
                break;
        }
        if (showShortName) {
            if (this.prefix == null) {
                this.prefix = new StringBuffer().append(this.logName.substring(this.logName.lastIndexOf(".") + 1)).append(" - ").toString();
                this.prefix = new StringBuffer().append(this.prefix.substring(this.prefix.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1)).append(c.q).toString();
            }
            stringBuffer.append(this.prefix);
        } else if (showLogName) {
            stringBuffer.append(String.valueOf(this.logName)).append(" - ");
        }
        stringBuffer.append(String.valueOf(obj));
        if (th != null) {
            stringBuffer.append(" <");
            stringBuffer.append(th.toString());
            stringBuffer.append(">");
            Writer stringWriter = new StringWriter(1024);
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.close();
            stringBuffer.append(stringWriter.toString());
        }
        System.err.println(stringBuffer.toString());
    }

    protected boolean isLevelEnabled(int i) {
        return i >= this.currentLogLevel;
    }

    public final void debug(Object obj) {
        if (isLevelEnabled(LOG_LEVEL_DEBUG)) {
            log(LOG_LEVEL_DEBUG, obj, null);
        }
    }

    public final void debug(Object obj, Throwable th) {
        if (isLevelEnabled(LOG_LEVEL_DEBUG)) {
            log(LOG_LEVEL_DEBUG, obj, th);
        }
    }

    public final void trace(Object obj) {
        if (isLevelEnabled(LOG_LEVEL_TRACE)) {
            log(LOG_LEVEL_TRACE, obj, null);
        }
    }

    public final void trace(Object obj, Throwable th) {
        if (isLevelEnabled(LOG_LEVEL_TRACE)) {
            log(LOG_LEVEL_TRACE, obj, th);
        }
    }

    public final void info(Object obj) {
        if (isLevelEnabled(LOG_LEVEL_INFO)) {
            log(LOG_LEVEL_INFO, obj, null);
        }
    }

    public final void info(Object obj, Throwable th) {
        if (isLevelEnabled(LOG_LEVEL_INFO)) {
            log(LOG_LEVEL_INFO, obj, th);
        }
    }

    public final void warn(Object obj) {
        if (isLevelEnabled(LOG_LEVEL_WARN)) {
            log(LOG_LEVEL_WARN, obj, null);
        }
    }

    public final void warn(Object obj, Throwable th) {
        if (isLevelEnabled(LOG_LEVEL_WARN)) {
            log(LOG_LEVEL_WARN, obj, th);
        }
    }

    public final void error(Object obj) {
        if (isLevelEnabled(LOG_LEVEL_ERROR)) {
            log(LOG_LEVEL_ERROR, obj, null);
        }
    }

    public final void error(Object obj, Throwable th) {
        if (isLevelEnabled(LOG_LEVEL_ERROR)) {
            log(LOG_LEVEL_ERROR, obj, th);
        }
    }

    public final void fatal(Object obj) {
        if (isLevelEnabled(LOG_LEVEL_FATAL)) {
            log(LOG_LEVEL_FATAL, obj, null);
        }
    }

    public final void fatal(Object obj, Throwable th) {
        if (isLevelEnabled(LOG_LEVEL_FATAL)) {
            log(LOG_LEVEL_FATAL, obj, th);
        }
    }

    public final boolean isDebugEnabled() {
        return isLevelEnabled(LOG_LEVEL_DEBUG);
    }

    public final boolean isErrorEnabled() {
        return isLevelEnabled(LOG_LEVEL_ERROR);
    }

    public final boolean isFatalEnabled() {
        return isLevelEnabled(LOG_LEVEL_FATAL);
    }

    public final boolean isInfoEnabled() {
        return isLevelEnabled(LOG_LEVEL_INFO);
    }

    public final boolean isTraceEnabled() {
        return isLevelEnabled(LOG_LEVEL_TRACE);
    }

    public final boolean isWarnEnabled() {
        return isLevelEnabled(LOG_LEVEL_WARN);
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static ClassLoader getContextClassLoader() {
        try {
            Class class$;
            if (class$java$lang$Thread == null) {
                class$ = class$("java.lang.Thread");
                class$java$lang$Thread = class$;
            } else {
                class$ = class$java$lang$Thread;
            }
            ClassLoader classLoader = (ClassLoader) class$.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
        } catch (IllegalAccessException e) {
            classLoader = null;
        } catch (InvocationTargetException e2) {
            if (e2.getTargetException() instanceof SecurityException) {
                classLoader = null;
            } else {
                throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
            }
        } catch (NoSuchMethodException e3) {
            classLoader = null;
        }
        if (classLoader != null) {
            return classLoader;
        }
        if (class$org$apache$commons$logging$impl$SimpleLog == null) {
            class$ = class$("org.apache.commons.logging.impl.SimpleLog");
            class$org$apache$commons$logging$impl$SimpleLog = class$;
        } else {
            class$ = class$org$apache$commons$logging$impl$SimpleLog;
        }
        return class$.getClassLoader();
    }

    private static InputStream getResourceAsStream(String str) {
        return (InputStream) AccessController.doPrivileged(new AnonymousClass_1(str));
    }
}
