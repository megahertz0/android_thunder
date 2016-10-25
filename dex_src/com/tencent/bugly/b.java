package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.proguard.m;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// compiled from: BUGLY
public final class b {
    public static boolean a;
    public static boolean b;
    private static List<a> c;
    private static o d;
    private static m e;
    private static boolean f;

    static {
        a = true;
        c = new ArrayList();
    }

    private static boolean a(a aVar) {
        List list = aVar.m;
        aVar.getClass();
        return list != null && list.contains("bugly");
    }

    public static synchronized void a(Context context) {
        synchronized (b.class) {
            a(context, null);
        }
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (f) {
                w.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                r0 = w.a;
            } else {
                a a = a.a(context);
                if (a(a)) {
                    a = false;
                } else {
                    String e = a.e();
                    if (e == null) {
                        r0 = w.a;
                    } else {
                        a(context, e, a.t, buglyStrategy);
                    }
                }
            }
        }
    }

    public static synchronized void a(Context context, String str, boolean z, BuglyStrategy buglyStrategy) {
        synchronized (b.class) {
            if (f) {
                w.d("[init] initial Multi-times, ignore this.", new Object[0]);
            } else if (context == null) {
                r0 = w.a;
            } else if (str == null) {
                r0 = w.a;
            } else {
                f = true;
                if (z) {
                    b = true;
                    w.b = true;
                    w.d("Bugly debug\u6a21\u5f0f\u5f00\u542f\uff0c\u8bf7\u5728\u53d1\u5e03\u65f6\u628aisDebug\u5173\u95ed\u3002 -- Running in debug model for 'isDebug' is enabled. Please disable it when you release.", new Object[0]);
                    w.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    w.d("Bugly debug\u6a21\u5f0f\u5c06\u6709\u4ee5\u4e0b\u884c\u4e3a\u7279\u6027 -- The following list shows the behaviour of debug model: ", new Object[0]);
                    w.d("[1] \u8f93\u51fa\u8be6\u7ec6\u7684Bugly SDK\u7684Log -- More detailed log of Bugly SDK will be output to logcat;", new Object[0]);
                    w.d("[2] \u6bcf\u4e00\u6761Crash\u90fd\u4f1a\u88ab\u7acb\u5373\u4e0a\u62a5 -- Every crash caught by Bugly will be uploaded immediately.", new Object[0]);
                    w.d("[3] \u81ea\u5b9a\u4e49\u65e5\u5fd7\u5c06\u4f1a\u5728Logcat\u4e2d\u8f93\u51fa -- Custom log will be output to logcat.", new Object[0]);
                    w.e("--------------------------------------------------------------------------------------------", new Object[0]);
                    w.b("[init] bugly in debug mode.", new Object[0]);
                }
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                x.a(context);
                a a = a.a(context);
                d = o.a(context, c);
                t.a(context);
                com.tencent.bugly.crashreport.common.strategy.a.a(context, c);
                e = m.a(context);
                if (a(a)) {
                    a = false;
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    a.getClass();
                    w.a(stringBuilder.append(" crash report start init!").toString(), new Object[0]);
                    w.b("[init] bugly start init...", new Object[0]);
                    a.a(str);
                    w.a("[param] setted APPID:%s", str);
                    if (buglyStrategy != null) {
                        String appVersion = buglyStrategy.getAppVersion();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                r0 = appVersion.substring(0, R.styleable.AppCompatTheme_buttonStyle);
                                w.d("appVersion %s length is over limit %d substring to %s", appVersion, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle), r0);
                            } else {
                                r0 = appVersion;
                            }
                            a.i = r0;
                            w.a("setted APPVERSION:%s", buglyStrategy.getAppVersion());
                        }
                        try {
                            if (buglyStrategy.isReplaceOldChannel()) {
                                appVersion = buglyStrategy.getAppChannel();
                                if (!TextUtils.isEmpty(appVersion)) {
                                    String str2;
                                    if (appVersion.length() > 100) {
                                        w.d("appChannel %s length is over limit %d substring to %s", appVersion, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle), appVersion.substring(0, R.styleable.AppCompatTheme_buttonStyle));
                                        str2 = r0;
                                    } else {
                                        str2 = appVersion;
                                    }
                                    d.a(556, "app_channel", str2.getBytes(), null, false);
                                    a.j = str2;
                                }
                            } else {
                                Map a2 = d.a(556, null, true);
                                if (a2 != null) {
                                    byte[] bArr = (byte[]) a2.get("app_channel");
                                    if (bArr != null) {
                                        a.j = new String(bArr);
                                    }
                                }
                            }
                            w.a("setted APPCHANNEL:%s", a.j);
                        } catch (Exception e) {
                            if (b) {
                                e.printStackTrace();
                            }
                        }
                        appVersion = buglyStrategy.getAppPackageName();
                        if (!TextUtils.isEmpty(appVersion)) {
                            if (appVersion.length() > 100) {
                                r0 = appVersion.substring(0, R.styleable.AppCompatTheme_buttonStyle);
                                w.d("appPackageName %s length is over limit %d substring to %s", appVersion, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle), r0);
                            } else {
                                r0 = appVersion;
                            }
                            a.c = r0;
                            w.a("setted PACKAGENAME:%s", buglyStrategy.getAppPackageName());
                        }
                        appVersion = buglyStrategy.getDeviceID();
                        if (appVersion != null) {
                            if (appVersion.length() > 100) {
                                r0 = appVersion.substring(0, R.styleable.AppCompatTheme_buttonStyle);
                                w.d("deviceId %s length is over limit %d substring to %s", appVersion, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle), r0);
                            } else {
                                r0 = appVersion;
                            }
                            a.c(r0);
                            w.a("setted deviceId :%s", r0);
                        }
                        x.a = buglyStrategy.isBuglyLogUpload();
                    }
                    com.tencent.bugly.crashreport.biz.b.a(context, buglyStrategy);
                    e.b();
                    for (int i = 0; i < c.size(); i++) {
                        try {
                            if (e.a(((a) c.get(i)).id)) {
                                ((a) c.get(i)).init(context, z, buglyStrategy);
                            }
                        } catch (Throwable th) {
                            if (!w.a(th)) {
                                th.printStackTrace();
                            }
                        }
                    }
                    w.a("crash report inited!", new Object[0]);
                    w.b("[init] bugly init finished.", new Object[0]);
                }
            }
        }
    }

    public static void a(a aVar) {
        if (!c.contains(aVar)) {
            c.add(aVar);
        }
    }
}
