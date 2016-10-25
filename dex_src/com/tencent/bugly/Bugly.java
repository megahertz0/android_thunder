package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.w;
import java.util.Map;

// compiled from: BUGLY
public class Bugly {
    public static final String SDK_IS_DEV = "false";
    private static boolean a;
    public static Context applicationContext;
    private static String[] b;
    private static String[] c;
    public static boolean enable;
    public static Boolean isDev;

    static {
        enable = true;
        applicationContext = null;
        b = new String[]{"BuglyCrashModule", "BuglyRqdModule", "BuglyBetaModule"};
        c = new String[]{"BuglyRqdModule", "BuglyCrashModule", "BuglyBetaModule"};
    }

    public static void init(Context context, String str, boolean z) {
        init(context, str, z, null);
    }

    public static synchronized void init(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (Bugly.class) {
            if (!a) {
                a = true;
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                applicationContext = context;
                String str2;
                if (context == null) {
                    str2 = w.a;
                } else {
                    if (isDev()) {
                        b = c;
                    }
                    String[] strArr = b;
                    int length = strArr.length;
                    for (int i = 0; i < length; i++) {
                        str2 = strArr[i];
                        try {
                            if (str2.equals("BuglyCrashModule")) {
                                b.a(CrashModule.getInstance());
                            } else if (!str2.equals("BuglyBetaModule") && !str2.equals("BuglyRqdModule")) {
                                str2.equals("BuglyFeedbackModule");
                            }
                        } catch (Throwable th) {
                            w.b(th);
                        }
                    }
                    b.a = enable;
                    b.a(applicationContext, str, z, buglyStrategy);
                }
            }
        }
    }

    public static synchronized String getAppChannel() {
        String str = null;
        synchronized (Bugly.class) {
            a a = a.a();
            if (a != null) {
                if (TextUtils.isEmpty(a.j)) {
                    o a2 = o.a();
                    if (a2 == null) {
                        str = a.j;
                    } else {
                        Map a3 = a2.a(556, null, true);
                        if (a3 != null) {
                            byte[] bArr = (byte[]) a3.get("app_channel");
                            if (bArr != null) {
                                str = new String(bArr);
                            }
                        }
                    }
                }
                str = a.j;
            }
        }
        return str;
    }

    public static boolean isDev() {
        if (isDev == null) {
            isDev = Boolean.valueOf(Boolean.parseBoolean(SDK_IS_DEV.replace("@", com.umeng.a.d)));
        }
        return isDev.booleanValue();
    }
}
