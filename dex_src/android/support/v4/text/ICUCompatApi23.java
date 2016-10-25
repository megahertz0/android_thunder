package android.support.v4.text;

import java.lang.reflect.Method;
import java.util.Locale;

class ICUCompatApi23 {
    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;

    ICUCompatApi23() {
    }

    static {
        try {
            sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[]{Locale.class});
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String maximizeAndGetScript(java.util.Locale r3) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.text.ICUCompatApi23.maximizeAndGetScript(java.util.Locale):java.lang.String");
        /*
        r0 = 1;
        r0 = new java.lang.Object[r0];	 Catch:{ InvocationTargetException -> 0x001a, IllegalAccessException -> 0x0014 }
        r1 = 0;
        r0[r1] = r3;	 Catch:{ InvocationTargetException -> 0x001a, IllegalAccessException -> 0x0014 }
        r1 = sAddLikelySubtagsMethod;	 Catch:{ InvocationTargetException -> 0x001a, IllegalAccessException -> 0x0014 }
        r2 = 0;
        r0 = r1.invoke(r2, r0);	 Catch:{ InvocationTargetException -> 0x001a, IllegalAccessException -> 0x0014 }
        r0 = (java.util.Locale) r0;	 Catch:{ InvocationTargetException -> 0x001a, IllegalAccessException -> 0x0014 }
        r0 = r0.getScript();	 Catch:{ InvocationTargetException -> 0x001a, IllegalAccessException -> 0x0014 }
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = move-exception;
    L_0x0015:
        r0 = r3.getScript();
        goto L_0x0013;
    L_0x001a:
        r0 = move-exception;
        goto L_0x0015;
        */
    }
}
