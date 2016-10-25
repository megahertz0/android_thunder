package org.apache.commons.logging.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

public class LogFactoryImpl extends LogFactory {
    public static final String LOG_PROPERTY = "org.apache.commons.logging.Log";
    protected static final String LOG_PROPERTY_OLD = "org.apache.commons.logging.log";
    static Class class$java$lang$String;
    static Class class$org$apache$commons$logging$Log;
    static Class class$org$apache$commons$logging$LogFactory;
    protected Hashtable attributes;
    protected Hashtable instances;
    private String logClassName;
    protected Constructor logConstructor;
    protected Class[] logConstructorSignature;
    protected Method logMethod;
    protected Class[] logMethodSignature;

    class AnonymousClass_1 implements PrivilegedAction {
        private final String val$name;

        AnonymousClass_1(String str) {
            this.val$name = str;
        }

        public Object run() {
            ClassLoader access$000 = LogFactoryImpl.access$000();
            if (access$000 != null) {
                try {
                    return access$000.loadClass(this.val$name);
                } catch (ClassNotFoundException e) {
                }
            }
            try {
                return Class.forName(this.val$name);
            } catch (Object e2) {
                return e2;
            }
        }
    }

    static ClassLoader access$000() throws LogConfigurationException {
        return LogFactory.getContextClassLoader();
    }

    public LogFactoryImpl() {
        Class class$;
        this.attributes = new Hashtable();
        this.instances = new Hashtable();
        this.logConstructor = null;
        Class[] clsArr = new Class[1];
        if (class$java$lang$String == null) {
            class$ = class$("java.lang.String");
            class$java$lang$String = class$;
        } else {
            class$ = class$java$lang$String;
        }
        clsArr[0] = class$;
        this.logConstructorSignature = clsArr;
        this.logMethod = null;
        clsArr = new Class[1];
        if (class$org$apache$commons$logging$LogFactory == null) {
            class$ = class$(LogFactory.FACTORY_PROPERTY);
            class$org$apache$commons$logging$LogFactory = class$;
        } else {
            class$ = class$org$apache$commons$logging$LogFactory;
        }
        clsArr[0] = class$;
        this.logMethodSignature = clsArr;
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    public String[] getAttributeNames() {
        Vector vector = new Vector();
        Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            vector.addElement((String) keys.nextElement());
        }
        String[] strArr = new String[vector.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public Log getInstance(Class cls) throws LogConfigurationException {
        return getInstance(cls.getName());
    }

    public Log getInstance(String str) throws LogConfigurationException {
        Log log = (Log) this.instances.get(str);
        if (log != null) {
            return log;
        }
        log = newInstance(str);
        this.instances.put(str, log);
        return log;
    }

    public void release() {
        this.instances.clear();
    }

    public void removeAttribute(String str) {
        this.attributes.remove(str);
    }

    public void setAttribute(String str, Object obj) {
        if (obj == null) {
            this.attributes.remove(str);
        } else {
            this.attributes.put(str, obj);
        }
    }

    protected String getLogClassName() {
        if (this.logClassName != null) {
            return this.logClassName;
        }
        this.logClassName = (String) getAttribute(LOG_PROPERTY);
        if (this.logClassName == null) {
            this.logClassName = (String) getAttribute(LOG_PROPERTY_OLD);
        }
        if (this.logClassName == null) {
            try {
                this.logClassName = System.getProperty(LOG_PROPERTY);
            } catch (SecurityException e) {
            }
        }
        if (this.logClassName == null) {
            try {
                this.logClassName = System.getProperty(LOG_PROPERTY_OLD);
            } catch (SecurityException e2) {
            }
        }
        if (this.logClassName == null && isLog4JAvailable()) {
            this.logClassName = "org.apache.commons.logging.impl.Log4JLogger";
        }
        if (this.logClassName == null && isJdk14Available()) {
            this.logClassName = "org.apache.commons.logging.impl.Jdk14Logger";
        }
        if (this.logClassName == null) {
            this.logClassName = "org.apache.commons.logging.impl.SimpleLog";
        }
        return this.logClassName;
    }

    protected Constructor getLogConstructor() throws LogConfigurationException {
        if (this.logConstructor != null) {
            return this.logConstructor;
        }
        String logClassName = getLogClassName();
        Class loadClass = loadClass(logClassName);
        if (loadClass == null) {
            throw new LogConfigurationException(new StringBuffer("No suitable Log implementation for ").append(logClassName).toString());
        }
        Class class$;
        if (class$org$apache$commons$logging$Log == null) {
            class$ = class$(LOG_PROPERTY);
            class$org$apache$commons$logging$Log = class$;
        } else {
            class$ = class$org$apache$commons$logging$Log;
        }
        if (class$.isAssignableFrom(loadClass)) {
            try {
                this.logMethod = loadClass.getMethod("setLogFactory", this.logMethodSignature);
            } catch (Throwable th) {
                this.logMethod = null;
            }
            this.logConstructor = loadClass.getConstructor(this.logConstructorSignature);
            return this.logConstructor;
        }
        throw new LogConfigurationException(new StringBuffer("Class ").append(logClassName).append(" does not implement Log").toString());
    }

    private static Class loadClass(String str) throws ClassNotFoundException {
        Object doPrivileged = AccessController.doPrivileged(new AnonymousClass_1(str));
        if (doPrivileged instanceof Class) {
            return (Class) doPrivileged;
        }
        throw ((ClassNotFoundException) doPrivileged);
    }

    protected boolean isJdk14Available() {
        try {
            loadClass("java.util.logging.Logger");
            loadClass("org.apache.commons.logging.impl.Jdk14Logger");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    protected boolean isLog4JAvailable() {
        try {
            loadClass("org.apache.log4j.Logger");
            loadClass("org.apache.commons.logging.impl.Log4JLogger");
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    protected Log newInstance(String str) throws LogConfigurationException {
        Object[] objArr = new Object[]{str};
        Log log = (Log) getLogConstructor().newInstance(objArr);
        if (this.logMethod != null) {
            objArr[0] = this;
            this.logMethod.invoke(log, objArr);
        }
        return log;
    }
}
