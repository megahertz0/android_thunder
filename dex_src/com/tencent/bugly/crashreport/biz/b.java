package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

// compiled from: BUGLY
public final class b {
    public static a a;
    private static boolean b;
    private static int c;
    private static long d;
    private static long e;
    private static long f;
    private static int g;
    private static long h;
    private static long i;
    private static long j;
    private static ActivityLifecycleCallbacks k;
    private static Class<?> l;

    // compiled from: BUGLY
    final class AnonymousClass_1 implements Runnable {
        private /* synthetic */ Context a;
        private /* synthetic */ BuglyStrategy b;

        AnonymousClass_1(Context context, BuglyStrategy buglyStrategy) {
            this.a = context;
            this.b = buglyStrategy;
        }

        public final void run() {
            b.c(this.a, this.b);
        }
    }

    static /* synthetic */ int g() {
        int i = g;
        g = i + 1;
        return i;
    }

    static {
        c = 10;
        d = 300000;
        e = 30000;
        f = 0;
        j = 0;
        k = null;
        l = null;
    }

    private static void c(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy != null) {
            boolean recordUserInfoOnceADay = buglyStrategy.recordUserInfoOnceADay();
            boolean isEnableUserInfo = buglyStrategy.isEnableUserInfo();
            boolean z = recordUserInfoOnceADay;
        } else {
            int i = 1;
            Object obj = null;
        }
        if (z) {
            Object obj2;
            a a = a.a(context);
            List a2 = a.a(a.d);
            if (a2 != null) {
                for (int i2 = 0; i2 < a2.size(); i2++) {
                    UserInfoBean userInfoBean = (UserInfoBean) a2.get(i2);
                    if (userInfoBean.n.equals(a.i) && userInfoBean.b == 1) {
                        long c = com.tencent.bugly.proguard.a.c();
                        if (c <= 0) {
                            break;
                        } else if (userInfoBean.e >= c) {
                            if (userInfoBean.f <= 0) {
                                a.b();
                            }
                            obj2 = null;
                            if (obj2 == null) {
                                obj2 = null;
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
            obj2 = 1;
            if (obj2 == null) {
                obj2 = null;
            } else {
                return;
            }
        }
        a a3 = a.a();
        if (a3 != null) {
            Object obj3 = null;
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int length = stackTrace.length;
            String str = null;
            for (int i3 = 0; i3 < length; i3++) {
                StackTraceElement stackTraceElement = stackTrace[i3];
                if (stackTraceElement.getMethodName().equals("onCreate")) {
                    str = stackTraceElement.getClassName();
                }
                if (stackTraceElement.getClassName().equals("android.app.Activity")) {
                    obj3 = 1;
                }
            }
            if (str == null) {
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            } else if (obj3 != null) {
                a3.n = true;
            } else {
                str = "background";
            }
            a3.o = str;
        }
        if (isEnableUserInfo) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k == null) {
                            k = new ActivityLifecycleCallbacks() {
                                public final void onActivityStopped(Activity activity) {
                                }

                                public final void onActivityStarted(Activity activity) {
                                }

                                public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                                }

                                public final void onActivityResumed(Activity activity) {
                                    String str = UtilityImpl.NET_TYPE_UNKNOWN;
                                    if (activity != null) {
                                        str = activity.getClass().getName();
                                    }
                                    if (l == null || l.getName().equals(str)) {
                                        w.c(">>> %s onResumed <<<", str);
                                        a a = a.a();
                                        if (a != null) {
                                            a.n = true;
                                            a.o = str;
                                            a.p = System.currentTimeMillis();
                                            a.s = a.p - i;
                                            if (a.p - h > (f > 0 ? f : e)) {
                                                a.c();
                                                b.g();
                                                w.a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(r4 / 1000), Long.valueOf(e / 1000));
                                                if (g % c == 0) {
                                                    a.a(XZBDevice.DOWNLOAD_LIST_ALL, true, 0);
                                                    return;
                                                }
                                                a.a(XZBDevice.DOWNLOAD_LIST_ALL, false, 0);
                                                long currentTimeMillis = System.currentTimeMillis();
                                                if (currentTimeMillis - j > d) {
                                                    j = currentTimeMillis;
                                                    w.a("add a timer to upload hot start user info", new Object[0]);
                                                    v.a().a(new a(a, null, true), d);
                                                }
                                            }
                                        }
                                    }
                                }

                                public final void onActivityPaused(Activity activity) {
                                    Object obj = UtilityImpl.NET_TYPE_UNKNOWN;
                                    if (activity != null) {
                                        obj = activity.getClass().getName();
                                    }
                                    if (l == null || l.getName().equals(obj)) {
                                        w.c(">>> %s onPaused <<<", obj);
                                        a a = a.a();
                                        if (a != null) {
                                            a.n = false;
                                            a.q = System.currentTimeMillis();
                                            a.r = a.q - a.p;
                                            h = a.q;
                                            if (a.r < 0) {
                                                a.r = 0;
                                            }
                                            if (activity != null) {
                                                a.o = "background";
                                            } else {
                                                a.o = UtilityImpl.NET_TYPE_UNKNOWN;
                                            }
                                        }
                                    }
                                }

                                public final void onActivityDestroyed(Activity activity) {
                                }

                                public final void onActivityCreated(Activity activity, Bundle bundle) {
                                }
                            };
                        }
                        application.registerActivityLifecycleCallbacks(k);
                    } catch (Exception e) {
                    }
                }
            }
        }
        i = System.currentTimeMillis();
        a.a(1, true, 0);
        t.a().a((int) IHost.HOST_NOFITY_PAGE_SELECTED, System.currentTimeMillis());
        w.a("[session] launch app, new start", new Object[0]);
        a.a();
        v.a().a(new c(a, 21600000), 21600000);
    }

    public static void a(Context context, BuglyStrategy buglyStrategy) {
        if (!b) {
            long appReportDelay;
            e = com.tencent.bugly.crashreport.common.strategy.a.a().c().n;
            c = com.tencent.bugly.crashreport.common.strategy.a.a().c().t;
            a = new a(context);
            b = true;
            if (buglyStrategy != null) {
                l = buglyStrategy.getUserInfoActivity();
                appReportDelay = buglyStrategy.getAppReportDelay();
            } else {
                appReportDelay = 0;
            }
            if (appReportDelay <= 0) {
                c(context, buglyStrategy);
            } else {
                v.a().a(new AnonymousClass_1(context, buglyStrategy), appReportDelay);
            }
        }
    }

    public static void a(long j) {
        if (j < 0) {
            j = com.tencent.bugly.crashreport.common.strategy.a.a().c().n;
        }
        f = j;
    }

    public static void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.n > 0) {
                e = strategyBean.n;
            }
            if (strategyBean.t > 0) {
                c = strategyBean.t;
            }
            if (strategyBean.u > 0) {
                d = strategyBean.u;
            }
        }
    }

    public static void a() {
        if (a != null) {
            a.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, false, 0);
        }
    }

    public static void a(Context context) {
        if (b && context != null) {
            Application application = null;
            if (VERSION.SDK_INT >= 14) {
                if (context.getApplicationContext() instanceof Application) {
                    application = (Application) context.getApplicationContext();
                }
                if (application != null) {
                    try {
                        if (k != null) {
                            application.unregisterActivityLifecycleCallbacks(k);
                        }
                    } catch (Exception e) {
                    }
                }
            }
            b = false;
        }
    }
}
