package com.inmobi.signals;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.BaseMonitor;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

// compiled from: GoogleApiClientWrapper.java
public class h {
    private static final String a;

    static {
        a = h.class.getSimpleName();
    }

    public static boolean a() {
        try {
            return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(a.b()) == 0;
        } catch (NoClassDefFoundError e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Google Play Services is not installed!");
            return false;
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "RuntimeException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e2.getMessage());
            com.inmobi.commons.core.c.a.a().a("signals", "ExceptionCaught", hashMap);
            return false;
        }
    }

    public static Object a(Context context, InvocationHandler invocationHandler, InvocationHandler invocationHandler2, String str) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Build object for GoogleApiClient");
        Object obj = null;
        Object obj2 = null;
        try {
            Class[] declaredClasses = Class.forName("com.google.android.gms.common.api.GoogleApiClient").getDeclaredClasses();
            int length = declaredClasses.length;
            int i = 0;
            Class cls = null;
            while (i < length) {
                Object obj3;
                Class cls2 = declaredClasses[i];
                if (cls2.getSimpleName().equalsIgnoreCase("ConnectionCallbacks")) {
                    Object obj4 = obj2;
                    obj2 = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, invocationHandler);
                    obj3 = obj4;
                } else if (cls2.getSimpleName().equalsIgnoreCase("OnConnectionFailedListener")) {
                    obj3 = Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, invocationHandler2);
                    obj2 = obj;
                } else if (cls2.getSimpleName().equalsIgnoreCase("Builder")) {
                    cls = cls2;
                    obj3 = obj2;
                    obj2 = obj;
                } else {
                    obj3 = obj2;
                    obj2 = obj;
                }
                i++;
                obj = obj2;
                obj2 = obj3;
            }
            if (cls != null) {
                obj3 = cls.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                Class forName = Class.forName("com.google.android.gms.common.api.Api");
                Class forName2 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks");
                Class forName3 = Class.forName("com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener");
                Method method = cls.getMethod("addApi", new Class[]{forName});
                Method method2 = cls.getMethod("addConnectionCallbacks", new Class[]{forName2});
                Method method3 = cls.getMethod("addOnConnectionFailedListener", new Class[]{forName3});
                Method method4 = cls.getMethod("build", null);
                Field declaredField = Class.forName(str).getDeclaredField("API");
                method.invoke(obj3, new Object[]{declaredField.get(null)});
                method2.invoke(obj3, new Object[]{obj});
                method3.invoke(obj3, new Object[]{obj2});
                return method4.invoke(obj3, null);
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e);
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e2);
        } catch (Throwable e22) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e22);
        } catch (Throwable e222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e222);
        } catch (Throwable e2222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e2222);
        } catch (Throwable e22222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e22222);
        } catch (Throwable e222222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot build Google API client object", e222222);
        }
        return null;
    }

    public static void a(Object obj) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Connecting Google API client.");
        if (obj != null) {
            try {
                Class.forName("com.google.android.gms.common.api.GoogleApiClient").getMethod(BaseMonitor.ALARM_POINT_CONNECT, null).invoke(obj, null);
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call connect on GoogleApiClient", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call connect on GoogleApiClient", e2);
            } catch (Throwable e22) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call connect on GoogleApiClient", e22);
            } catch (Throwable e222) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call connect on GoogleApiClient", e222);
            }
        }
    }

    public static void b(Object obj) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Disconnecting Google API client.");
        if (obj != null) {
            try {
                Class.forName("com.google.android.gms.common.api.GoogleApiClient").getMethod("disconnect", null).invoke(obj, null);
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call disconnect on GoogleApiClient", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call disconnect on GoogleApiClient", e2);
            } catch (Throwable e22) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call disconnect on GoogleApiClient", e22);
            } catch (Throwable e222) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Failed to call disconnect on GoogleApiClient", e222);
            }
        }
    }
}
