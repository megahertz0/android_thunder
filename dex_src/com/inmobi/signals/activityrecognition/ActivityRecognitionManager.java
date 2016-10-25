package com.inmobi.signals.activityrecognition;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.h;
import com.xunlei.tdlive.sdk.IHost;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActivityRecognitionManager extends IntentService {
    private static final String a;
    private static Object b;
    private static Object c;

    private static class a implements InvocationHandler {
        private a() {
        }

        public void a(Bundle bundle) {
            PendingIntent service = PendingIntent.getService(com.inmobi.commons.a.a.b(), 0, new Intent(com.inmobi.commons.a.a.b(), ActivityRecognitionManager.class), 134217728);
            try {
                Field declaredField = Class.forName("com.google.android.gms.location.ActivityRecognition").getDeclaredField("ActivityRecognitionApi");
                Class forName = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
                Class.forName("com.google.android.gms.location.ActivityRecognitionApi").getMethod("requestActivityUpdates", new Class[]{forName, Long.TYPE, PendingIntent.class}).invoke(declaredField.get(null), new Object[]{c, Integer.valueOf(IHost.HOST_NOFITY_REFRESH_LIST), service});
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e2);
            } catch (Throwable e22) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e22);
            } catch (Throwable e222) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e222);
            } catch (Throwable e2222) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Unable to request activity updates from ActivityRecognition client", e2222);
            }
        }

        public void a(int i) {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (objArr != null) {
                if (method.getName().equals("onConnected")) {
                    a((Bundle) objArr[0]);
                    return null;
                } else if (method.getName().equals("onConnectionSuspended")) {
                    a(((Integer) objArr[0]).intValue());
                    return null;
                }
            }
            return method.invoke(this, objArr);
        }
    }

    static {
        a = ActivityRecognitionManager.class.getSimpleName();
        b = null;
        c = null;
    }

    public ActivityRecognitionManager() {
        super("Activity service");
    }

    static void a() {
        if (h.a() && c == null) {
            a(com.inmobi.commons.a.a.b());
        }
    }

    static void b() {
        if (h.a() && c != null) {
            f();
        }
    }

    private static void a(Context context) {
        Logger.a(InternalLogLevel.INTERNAL, a, "Connecting activity recognition manager.");
        Object a = h.a(context, new a(), new a(), "com.google.android.gms.location.ActivityRecognition");
        c = a;
        h.a(a);
    }

    private static void f() {
        Logger.a(InternalLogLevel.INTERNAL, a, "Disconnecting activity recognition manager.");
        h.b(c);
        b = null;
        c = null;
    }

    protected void onHandleIntent(Intent intent) {
        if (c != null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Got activity recognition intent.");
            a(intent);
        }
    }

    private static void a(Intent intent) {
        try {
            Class forName = Class.forName("com.google.android.gms.location.ActivityRecognitionResult");
            if (((Boolean) forName.getMethod("hasResult", new Class[]{Intent.class}).invoke(null, new Object[]{intent})).booleanValue()) {
                b = forName.getMethod("getMostProbableActivity", null).invoke(forName.getMethod("extractResult", new Class[]{Intent.class}).invoke(null, new Object[]{intent}), null);
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "HandleIntent: Google play services not included. Cannot get current activity.", e);
        } catch (Throwable e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "HandleIntent: Google play services not included. Cannot get current activity.", e2);
        } catch (Throwable e22) {
            Logger.a(InternalLogLevel.INTERNAL, a, "HandleIntent: Google play services not included. Cannot get current activity.", e22);
        } catch (Throwable e222) {
            Logger.a(InternalLogLevel.INTERNAL, a, "HandleIntent: Google play services not included. Cannot get current activity.", e222);
        }
    }

    public static int c() {
        int i = -1;
        if (b == null) {
            return -1;
        }
        try {
            int intValue = ((Integer) Class.forName("com.google.android.gms.location.DetectedActivity").getMethod("getType", null).invoke(b, null)).intValue();
            Object obj = null;
            try {
                b = null;
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Getting detected activity:").append(intValue).toString());
                return intValue;
            } catch (ClassNotFoundException e) {
                Throwable e2 = e;
            } catch (NoSuchMethodException e3) {
                e2 = e3;
                Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
                return intValue;
            } catch (InvocationTargetException e4) {
                e2 = e4;
                Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
                return intValue;
            } catch (IllegalAccessException e5) {
                e2 = e5;
                Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
                return intValue;
            }
        } catch (Throwable e6) {
            Throwable th = e6;
            intValue = i;
            e2 = th;
            Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
            return intValue;
        } catch (Throwable e62) {
            th = e62;
            intValue = i;
            e2 = th;
            Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
            return intValue;
        } catch (Throwable e622) {
            th = e622;
            intValue = i;
            e2 = th;
            Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
            return intValue;
        } catch (Throwable e6222) {
            th = e6222;
            intValue = obj;
            e2 = th;
            Logger.a(InternalLogLevel.INTERNAL, a, "getDetectedActivity: Google play services not included. Returning null.", e2);
            return intValue;
        }
    }
}
