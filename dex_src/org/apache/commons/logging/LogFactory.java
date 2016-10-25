package org.apache.commons.logging;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

public abstract class LogFactory {
    public static final String FACTORY_DEFAULT = "org.apache.commons.logging.impl.LogFactoryImpl";
    public static final String FACTORY_PROPERTIES = "commons-logging.properties";
    public static final String FACTORY_PROPERTY = "org.apache.commons.logging.LogFactory";
    protected static final String SERVICE_ID = "META-INF/services/org.apache.commons.logging.LogFactory";
    static Class class$java$lang$Thread;
    static Class class$org$apache$commons$logging$LogFactory;
    protected static Hashtable factories;

    class AnonymousClass_2 implements PrivilegedAction {
        private final ClassLoader val$classLoader;
        private final String val$factoryClass;

        AnonymousClass_2(ClassLoader classLoader, String str) {
            this.val$classLoader = classLoader;
            this.val$factoryClass = str;
        }

        public Object run() {
            ClassLoader classLoader;
            Class class$;
            try {
                if (this.val$classLoader != null) {
                    return (LogFactory) this.val$classLoader.loadClass(this.val$factoryClass).newInstance();
                }
            } catch (ClassNotFoundException e) {
                classLoader = this.val$classLoader;
                if (class$org$apache$commons$logging$LogFactory == null) {
                    class$ = LogFactory.class$(FACTORY_PROPERTY);
                    class$org$apache$commons$logging$LogFactory = class$;
                } else {
                    class$ = class$org$apache$commons$logging$LogFactory;
                }
                if (classLoader == class$.getClassLoader()) {
                    throw e;
                }
            } catch (NoClassDefFoundError e2) {
                classLoader = this.val$classLoader;
                if (class$org$apache$commons$logging$LogFactory == null) {
                    class$ = LogFactory.class$(FACTORY_PROPERTY);
                    class$org$apache$commons$logging$LogFactory = class$;
                } else {
                    class$ = class$org$apache$commons$logging$LogFactory;
                }
                if (classLoader == class$.getClassLoader()) {
                    throw e2;
                }
            } catch (ClassCastException e3) {
                classLoader = this.val$classLoader;
                if (class$org$apache$commons$logging$LogFactory == null) {
                    class$ = LogFactory.class$(FACTORY_PROPERTY);
                    class$org$apache$commons$logging$LogFactory = class$;
                } else {
                    class$ = class$org$apache$commons$logging$LogFactory;
                }
                if (classLoader == class$.getClassLoader()) {
                    throw e3;
                }
            } catch (Throwable e4) {
                return new LogConfigurationException(e4);
            }
            return (LogFactory) Class.forName(this.val$factoryClass).newInstance();
        }
    }

    class AnonymousClass_3 implements PrivilegedAction {
        private final ClassLoader val$loader;
        private final String val$name;

        AnonymousClass_3(ClassLoader classLoader, String str) {
            this.val$loader = classLoader;
            this.val$name = str;
        }

        public Object run() {
            return this.val$loader != null ? this.val$loader.getResourceAsStream(this.val$name) : ClassLoader.getSystemResourceAsStream(this.val$name);
        }
    }

    public abstract Object getAttribute(String str);

    public abstract String[] getAttributeNames();

    public abstract Log getInstance(Class cls) throws LogConfigurationException;

    public abstract Log getInstance(String str) throws LogConfigurationException;

    public abstract void release();

    public abstract void removeAttribute(String str);

    public abstract void setAttribute(String str, Object obj);

    static {
        factories = new Hashtable();
    }

    public static LogFactory getFactory() throws LogConfigurationException {
        Properties properties;
        Properties properties2;
        String property;
        LogFactory newFactory;
        InputStream resourceAsStream;
        BufferedReader bufferedReader;
        String readLine;
        String str;
        Enumeration propertyNames;
        ClassLoader classLoader = (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return LogFactory.getContextClassLoader();
            }
        });
        LogFactory cachedFactory = getCachedFactory(classLoader);
        if (cachedFactory == null) {
            InputStream resourceAsStream2;
            Properties properties3;
            Class class$;
            String str2;
            Object obj = null;
            try {
                resourceAsStream2 = getResourceAsStream(classLoader, FACTORY_PROPERTIES);
                properties3 = properties;
            } catch (IOException e) {
                properties3 = properties2;
                property = System.getProperty(FACTORY_PROPERTY);
                if (property != null) {
                    newFactory = newFactory(property, classLoader);
                    if (newFactory == null) {
                        resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                        if (resourceAsStream != null) {
                            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                            readLine = bufferedReader.readLine();
                            bufferedReader.close();
                            newFactory = newFactory(readLine, classLoader);
                        }
                        cachedFactory = newFactory;
                        property = properties3.getProperty(FACTORY_PROPERTY);
                        if (property != null) {
                            cachedFactory = newFactory(property, classLoader);
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            } else {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                        return cachedFactory;
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        } else {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                    return cachedFactory;
                }
                newFactory = cachedFactory;
                if (newFactory == null) {
                    resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                    if (resourceAsStream != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                        readLine = bufferedReader.readLine();
                        bufferedReader.close();
                        newFactory = newFactory(readLine, classLoader);
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        } else {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                    return cachedFactory;
                }
                cachedFactory = newFactory;
                property = properties3.getProperty(FACTORY_PROPERTY);
                if (property != null) {
                    cachedFactory = newFactory(property, classLoader);
                }
                if (cachedFactory == null) {
                    str = FACTORY_DEFAULT;
                    if (class$org$apache$commons$logging$LogFactory != null) {
                        class$ = class$org$apache$commons$logging$LogFactory;
                    } else {
                        class$ = class$(FACTORY_PROPERTY);
                        class$org$apache$commons$logging$LogFactory = class$;
                    }
                    cachedFactory = newFactory(str, class$.getClassLoader());
                }
                if (cachedFactory != null) {
                    cacheFactory(classLoader, cachedFactory);
                    if (properties3 != null) {
                        propertyNames = properties3.propertyNames();
                        while (propertyNames.hasMoreElements()) {
                            str2 = (String) propertyNames.nextElement();
                            cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                        }
                    }
                }
                return cachedFactory;
            } catch (SecurityException e2) {
                properties3 = properties2;
                property = System.getProperty(FACTORY_PROPERTY);
                if (property != null) {
                    newFactory = newFactory(property, classLoader);
                    if (newFactory == null) {
                        resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                        if (resourceAsStream != null) {
                            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                            readLine = bufferedReader.readLine();
                            bufferedReader.close();
                            newFactory = newFactory(readLine, classLoader);
                        }
                        cachedFactory = newFactory;
                        property = properties3.getProperty(FACTORY_PROPERTY);
                        if (property != null) {
                            cachedFactory = newFactory(property, classLoader);
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            } else {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                        return cachedFactory;
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        } else {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                    return cachedFactory;
                }
                newFactory = cachedFactory;
                if (newFactory == null) {
                    resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                    if (resourceAsStream != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                        readLine = bufferedReader.readLine();
                        bufferedReader.close();
                        newFactory = newFactory(readLine, classLoader);
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        } else {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                    return cachedFactory;
                }
                cachedFactory = newFactory;
                property = properties3.getProperty(FACTORY_PROPERTY);
                if (property != null) {
                    cachedFactory = newFactory(property, classLoader);
                }
                if (cachedFactory == null) {
                    str = FACTORY_DEFAULT;
                    if (class$org$apache$commons$logging$LogFactory != null) {
                        class$ = class$org$apache$commons$logging$LogFactory;
                    } else {
                        class$ = class$(FACTORY_PROPERTY);
                        class$org$apache$commons$logging$LogFactory = class$;
                    }
                    cachedFactory = newFactory(str, class$.getClassLoader());
                }
                if (cachedFactory != null) {
                    cacheFactory(classLoader, cachedFactory);
                    if (properties3 != null) {
                        propertyNames = properties3.propertyNames();
                        while (propertyNames.hasMoreElements()) {
                            str2 = (String) propertyNames.nextElement();
                            cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                        }
                    }
                }
                return cachedFactory;
            }
            if (resourceAsStream2 != null) {
                properties = new Properties();
                try {
                    properties.load(resourceAsStream2);
                    resourceAsStream2.close();
                } catch (IOException e3) {
                    properties2 = properties;
                    properties3 = properties2;
                    property = System.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        newFactory = newFactory(property, classLoader);
                        if (newFactory == null) {
                            resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                            if (resourceAsStream != null) {
                                bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                                readLine = bufferedReader.readLine();
                                bufferedReader.close();
                                newFactory = newFactory(readLine, classLoader);
                            }
                            cachedFactory = newFactory;
                            property = properties3.getProperty(FACTORY_PROPERTY);
                            if (property != null) {
                                cachedFactory = newFactory(property, classLoader);
                            }
                            if (cachedFactory == null) {
                                str = FACTORY_DEFAULT;
                                if (class$org$apache$commons$logging$LogFactory != null) {
                                    class$ = class$(FACTORY_PROPERTY);
                                    class$org$apache$commons$logging$LogFactory = class$;
                                } else {
                                    class$ = class$org$apache$commons$logging$LogFactory;
                                }
                                cachedFactory = newFactory(str, class$.getClassLoader());
                            }
                            if (cachedFactory != null) {
                                cacheFactory(classLoader, cachedFactory);
                                if (properties3 != null) {
                                    propertyNames = properties3.propertyNames();
                                    while (propertyNames.hasMoreElements()) {
                                        str2 = (String) propertyNames.nextElement();
                                        cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                    }
                                }
                            }
                            return cachedFactory;
                        }
                        cachedFactory = newFactory;
                        property = properties3.getProperty(FACTORY_PROPERTY);
                        if (property != null) {
                            cachedFactory = newFactory(property, classLoader);
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            } else {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                        return cachedFactory;
                    }
                    newFactory = cachedFactory;
                    if (newFactory == null) {
                        resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                        if (resourceAsStream != null) {
                            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                            readLine = bufferedReader.readLine();
                            bufferedReader.close();
                            newFactory = newFactory(readLine, classLoader);
                        }
                        cachedFactory = newFactory;
                        property = properties3.getProperty(FACTORY_PROPERTY);
                        if (property != null) {
                            cachedFactory = newFactory(property, classLoader);
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            } else {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                        return cachedFactory;
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        } else {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                    return cachedFactory;
                } catch (SecurityException e4) {
                    properties2 = properties;
                    properties3 = properties2;
                    property = System.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        newFactory = newFactory(property, classLoader);
                        if (newFactory == null) {
                            resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                            if (resourceAsStream != null) {
                                bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                                readLine = bufferedReader.readLine();
                                bufferedReader.close();
                                newFactory = newFactory(readLine, classLoader);
                            }
                            cachedFactory = newFactory;
                            property = properties3.getProperty(FACTORY_PROPERTY);
                            if (property != null) {
                                cachedFactory = newFactory(property, classLoader);
                            }
                            if (cachedFactory == null) {
                                str = FACTORY_DEFAULT;
                                if (class$org$apache$commons$logging$LogFactory != null) {
                                    class$ = class$(FACTORY_PROPERTY);
                                    class$org$apache$commons$logging$LogFactory = class$;
                                } else {
                                    class$ = class$org$apache$commons$logging$LogFactory;
                                }
                                cachedFactory = newFactory(str, class$.getClassLoader());
                            }
                            if (cachedFactory != null) {
                                cacheFactory(classLoader, cachedFactory);
                                if (properties3 != null) {
                                    propertyNames = properties3.propertyNames();
                                    while (propertyNames.hasMoreElements()) {
                                        str2 = (String) propertyNames.nextElement();
                                        cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                    }
                                }
                            }
                            return cachedFactory;
                        }
                        cachedFactory = newFactory;
                        property = properties3.getProperty(FACTORY_PROPERTY);
                        if (property != null) {
                            cachedFactory = newFactory(property, classLoader);
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            } else {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                        return cachedFactory;
                    }
                    newFactory = cachedFactory;
                    if (newFactory == null) {
                        resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                        if (resourceAsStream != null) {
                            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                            readLine = bufferedReader.readLine();
                            bufferedReader.close();
                            newFactory = newFactory(readLine, classLoader);
                        }
                        cachedFactory = newFactory;
                        property = properties3.getProperty(FACTORY_PROPERTY);
                        if (property != null) {
                            cachedFactory = newFactory(property, classLoader);
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            } else {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                        return cachedFactory;
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        } else {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                    return cachedFactory;
                }
            }
            properties = null;
            try {
                property = System.getProperty(FACTORY_PROPERTY);
                if (property != null) {
                    newFactory = newFactory(property, classLoader);
                    if (newFactory == null) {
                        try {
                            resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                            if (resourceAsStream != null) {
                                try {
                                    bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                                } catch (UnsupportedEncodingException e5) {
                                    bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
                                }
                                readLine = bufferedReader.readLine();
                                bufferedReader.close();
                                if (!(readLine == null || BuildConfig.VERSION_NAME.equals(readLine))) {
                                    newFactory = newFactory(readLine, classLoader);
                                }
                            }
                            cachedFactory = newFactory;
                        } catch (Exception e6) {
                        }
                        if (cachedFactory == null && properties3 != null) {
                            property = properties3.getProperty(FACTORY_PROPERTY);
                            if (property != null) {
                                cachedFactory = newFactory(property, classLoader);
                            }
                        }
                        if (cachedFactory == null) {
                            str = FACTORY_DEFAULT;
                            if (class$org$apache$commons$logging$LogFactory != null) {
                                class$ = class$(FACTORY_PROPERTY);
                                class$org$apache$commons$logging$LogFactory = class$;
                            } else {
                                class$ = class$org$apache$commons$logging$LogFactory;
                            }
                            cachedFactory = newFactory(str, class$.getClassLoader());
                        }
                        if (cachedFactory != null) {
                            cacheFactory(classLoader, cachedFactory);
                            if (properties3 != null) {
                                propertyNames = properties3.propertyNames();
                                while (propertyNames.hasMoreElements()) {
                                    str2 = (String) propertyNames.nextElement();
                                    cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                                }
                            }
                        }
                    }
                    cachedFactory = newFactory;
                    property = properties3.getProperty(FACTORY_PROPERTY);
                    if (property != null) {
                        cachedFactory = newFactory(property, classLoader);
                    }
                    if (cachedFactory == null) {
                        str = FACTORY_DEFAULT;
                        if (class$org$apache$commons$logging$LogFactory != null) {
                            class$ = class$org$apache$commons$logging$LogFactory;
                        } else {
                            class$ = class$(FACTORY_PROPERTY);
                            class$org$apache$commons$logging$LogFactory = class$;
                        }
                        cachedFactory = newFactory(str, class$.getClassLoader());
                    }
                    if (cachedFactory != null) {
                        cacheFactory(classLoader, cachedFactory);
                        if (properties3 != null) {
                            propertyNames = properties3.propertyNames();
                            while (propertyNames.hasMoreElements()) {
                                str2 = (String) propertyNames.nextElement();
                                cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                            }
                        }
                    }
                }
            } catch (SecurityException e7) {
            }
            newFactory = cachedFactory;
            if (newFactory == null) {
                resourceAsStream = getResourceAsStream(classLoader, SERVICE_ID);
                if (resourceAsStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, CharsetConvert.UTF_8));
                    readLine = bufferedReader.readLine();
                    bufferedReader.close();
                    newFactory = newFactory(readLine, classLoader);
                }
                cachedFactory = newFactory;
                property = properties3.getProperty(FACTORY_PROPERTY);
                if (property != null) {
                    cachedFactory = newFactory(property, classLoader);
                }
                if (cachedFactory == null) {
                    str = FACTORY_DEFAULT;
                    if (class$org$apache$commons$logging$LogFactory != null) {
                        class$ = class$(FACTORY_PROPERTY);
                        class$org$apache$commons$logging$LogFactory = class$;
                    } else {
                        class$ = class$org$apache$commons$logging$LogFactory;
                    }
                    cachedFactory = newFactory(str, class$.getClassLoader());
                }
                if (cachedFactory != null) {
                    cacheFactory(classLoader, cachedFactory);
                    if (properties3 != null) {
                        propertyNames = properties3.propertyNames();
                        while (propertyNames.hasMoreElements()) {
                            str2 = (String) propertyNames.nextElement();
                            cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                        }
                    }
                }
            }
            cachedFactory = newFactory;
            property = properties3.getProperty(FACTORY_PROPERTY);
            if (property != null) {
                cachedFactory = newFactory(property, classLoader);
            }
            if (cachedFactory == null) {
                str = FACTORY_DEFAULT;
                if (class$org$apache$commons$logging$LogFactory != null) {
                    class$ = class$org$apache$commons$logging$LogFactory;
                } else {
                    class$ = class$(FACTORY_PROPERTY);
                    class$org$apache$commons$logging$LogFactory = class$;
                }
                cachedFactory = newFactory(str, class$.getClassLoader());
            }
            if (cachedFactory != null) {
                cacheFactory(classLoader, cachedFactory);
                if (properties3 != null) {
                    propertyNames = properties3.propertyNames();
                    while (propertyNames.hasMoreElements()) {
                        str2 = (String) propertyNames.nextElement();
                        cachedFactory.setAttribute(str2, properties3.getProperty(str2));
                    }
                }
            }
        }
        return cachedFactory;
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static Log getLog(Class cls) throws LogConfigurationException {
        return getFactory().getInstance(cls);
    }

    public static Log getLog(String str) throws LogConfigurationException {
        return getFactory().getInstance(str);
    }

    public static void release(ClassLoader classLoader) {
        synchronized (factories) {
            LogFactory logFactory = (LogFactory) factories.get(classLoader);
            if (logFactory != null) {
                logFactory.release();
                factories.remove(classLoader);
            }
        }
    }

    public static void releaseAll() {
        synchronized (factories) {
            Enumeration elements = factories.elements();
            while (elements.hasMoreElements()) {
                ((LogFactory) elements.nextElement()).release();
            }
            factories.clear();
        }
    }

    public static ClassLoader getContextClassLoader() throws LogConfigurationException {
        try {
            Class class$;
            if (class$java$lang$Thread == null) {
                class$ = class$("java.lang.Thread");
                class$java$lang$Thread = class$;
            } else {
                class$ = class$java$lang$Thread;
            }
            return (ClassLoader) class$.getMethod("getContextClassLoader", null).invoke(Thread.currentThread(), null);
        } catch (Throwable e) {
            throw new LogConfigurationException("Unexpected IllegalAccessException", e);
        } catch (InvocationTargetException e2) {
            if (e2.getTargetException() instanceof SecurityException) {
                return null;
            }
            throw new LogConfigurationException("Unexpected InvocationTargetException", e2.getTargetException());
        } catch (NoSuchMethodException e3) {
            if (class$org$apache$commons$logging$LogFactory == null) {
                class$ = class$(FACTORY_PROPERTY);
                class$org$apache$commons$logging$LogFactory = class$;
            } else {
                class$ = class$org$apache$commons$logging$LogFactory;
            }
            return class$.getClassLoader();
        }
    }

    private static LogFactory getCachedFactory(ClassLoader classLoader) {
        return classLoader != null ? (LogFactory) factories.get(classLoader) : null;
    }

    private static void cacheFactory(ClassLoader classLoader, LogFactory logFactory) {
        if (classLoader != null && logFactory != null) {
            factories.put(classLoader, logFactory);
        }
    }

    protected static LogFactory newFactory(String str, ClassLoader classLoader) throws LogConfigurationException {
        Object doPrivileged = AccessController.doPrivileged(new AnonymousClass_2(classLoader, str));
        if (!(doPrivileged instanceof LogConfigurationException)) {
            return (LogFactory) doPrivileged;
        }
        throw ((LogConfigurationException) doPrivileged);
    }

    private static InputStream getResourceAsStream(ClassLoader classLoader, String str) {
        return (InputStream) AccessController.doPrivileged(new AnonymousClass_3(classLoader, str));
    }
}
