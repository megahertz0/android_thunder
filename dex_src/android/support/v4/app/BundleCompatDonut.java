package android.support.v4.app;

import java.lang.reflect.Method;

class BundleCompatDonut {
    private static final String TAG = "BundleCompatDonut";
    private static Method sGetIBinderMethod;
    private static boolean sGetIBinderMethodFetched;
    private static Method sPutIBinderMethod;
    private static boolean sPutIBinderMethodFetched;

    BundleCompatDonut() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.IBinder getBinder(android.os.Bundle r7, java.lang.String r8) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BundleCompatDonut.getBinder(android.os.Bundle, java.lang.String):android.os.IBinder");
        /*
        r1 = 0;
        r6 = 1;
        r0 = sGetIBinderMethodFetched;
        if (r0 != 0) goto L_0x001f;
    L_0x0006:
        r0 = android.os.Bundle.class;
        r2 = "getIBinder";
        r3 = 1;
        r3 = new java.lang.Class[r3];	 Catch:{ NoSuchMethodException -> 0x003b }
        r4 = 0;
        r5 = java.lang.String.class;
        r3[r4] = r5;	 Catch:{ NoSuchMethodException -> 0x003b }
        r0 = r0.getMethod(r2, r3);	 Catch:{ NoSuchMethodException -> 0x003b }
        sGetIBinderMethod = r0;	 Catch:{ NoSuchMethodException -> 0x003b }
        r2 = 1;
        r0.setAccessible(r2);	 Catch:{ NoSuchMethodException -> 0x003b }
    L_0x001d:
        sGetIBinderMethodFetched = r6;
    L_0x001f:
        r0 = sGetIBinderMethod;
        if (r0 == 0) goto L_0x0035;
    L_0x0023:
        r0 = sGetIBinderMethod;	 Catch:{ InvocationTargetException -> 0x0037, IllegalAccessException -> 0x0039, IllegalArgumentException -> 0x0032 }
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x0037, IllegalAccessException -> 0x0039, IllegalArgumentException -> 0x0032 }
        r3 = 0;
        r2[r3] = r8;	 Catch:{ InvocationTargetException -> 0x0037, IllegalAccessException -> 0x0039, IllegalArgumentException -> 0x0032 }
        r0 = r0.invoke(r7, r2);	 Catch:{ InvocationTargetException -> 0x0037, IllegalAccessException -> 0x0039, IllegalArgumentException -> 0x0032 }
        r0 = (android.os.IBinder) r0;	 Catch:{ InvocationTargetException -> 0x0037, IllegalAccessException -> 0x0039, IllegalArgumentException -> 0x0032 }
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = move-exception;
    L_0x0033:
        sGetIBinderMethod = r1;
    L_0x0035:
        r0 = r1;
        goto L_0x0031;
    L_0x0037:
        r0 = move-exception;
        goto L_0x0033;
    L_0x0039:
        r0 = move-exception;
        goto L_0x0033;
    L_0x003b:
        r0 = move-exception;
        goto L_0x001d;
        */
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void putBinder(android.os.Bundle r6, java.lang.String r7, android.os.IBinder r8) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.BundleCompatDonut.putBinder(android.os.Bundle, java.lang.String, android.os.IBinder):void");
        /*
        r5 = 1;
        r0 = sPutIBinderMethodFetched;
        if (r0 != 0) goto L_0x0023;
    L_0x0005:
        r0 = android.os.Bundle.class;
        r1 = "putIBinder";
        r2 = 2;
        r2 = new java.lang.Class[r2];	 Catch:{ NoSuchMethodException -> 0x003f }
        r3 = 0;
        r4 = java.lang.String.class;
        r2[r3] = r4;	 Catch:{ NoSuchMethodException -> 0x003f }
        r3 = 1;
        r4 = android.os.IBinder.class;
        r2[r3] = r4;	 Catch:{ NoSuchMethodException -> 0x003f }
        r0 = r0.getMethod(r1, r2);	 Catch:{ NoSuchMethodException -> 0x003f }
        sPutIBinderMethod = r0;	 Catch:{ NoSuchMethodException -> 0x003f }
        r1 = 1;
        r0.setAccessible(r1);	 Catch:{ NoSuchMethodException -> 0x003f }
    L_0x0021:
        sPutIBinderMethodFetched = r5;
    L_0x0023:
        r0 = sPutIBinderMethod;
        if (r0 == 0) goto L_0x0035;
    L_0x0027:
        r0 = sPutIBinderMethod;	 Catch:{ InvocationTargetException -> 0x003b, IllegalAccessException -> 0x003d, IllegalArgumentException -> 0x0036 }
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x003b, IllegalAccessException -> 0x003d, IllegalArgumentException -> 0x0036 }
        r2 = 0;
        r1[r2] = r7;	 Catch:{ InvocationTargetException -> 0x003b, IllegalAccessException -> 0x003d, IllegalArgumentException -> 0x0036 }
        r2 = 1;
        r1[r2] = r8;	 Catch:{ InvocationTargetException -> 0x003b, IllegalAccessException -> 0x003d, IllegalArgumentException -> 0x0036 }
        r0.invoke(r6, r1);	 Catch:{ InvocationTargetException -> 0x003b, IllegalAccessException -> 0x003d, IllegalArgumentException -> 0x0036 }
    L_0x0035:
        return;
    L_0x0036:
        r0 = move-exception;
    L_0x0037:
        r0 = 0;
        sPutIBinderMethod = r0;
        goto L_0x0035;
    L_0x003b:
        r0 = move-exception;
        goto L_0x0037;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0037;
    L_0x003f:
        r0 = move-exception;
        goto L_0x0021;
        */
    }
}
