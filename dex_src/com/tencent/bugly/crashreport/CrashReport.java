package com.tencent.bugly.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.alipay.sdk.util.h;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.BuglyStrategy.a;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.b;
import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.w;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// compiled from: BUGLY
public class CrashReport {
    private static Context a;

    // compiled from: BUGLY
    public static class CrashHandleCallback extends a {
    }

    // compiled from: BUGLY
    public static class UserStrategy extends BuglyStrategy {
        private CrashHandleCallback a;

        public UserStrategy(Context context) {
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.a = crashHandleCallback;
        }
    }

    public static void enableBugly(boolean z) {
        b.a = z;
    }

    public static synchronized void initCrashReport(Context context) {
        synchronized (CrashReport.class) {
            a = context;
            b.a(CrashModule.getInstance());
            b.a(context);
        }
    }

    public static synchronized void initCrashReport(Context context, UserStrategy userStrategy) {
        synchronized (CrashReport.class) {
            a = context;
            b.a(CrashModule.getInstance());
            b.a(context, userStrategy);
        }
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z) {
        synchronized (CrashReport.class) {
            initCrashReport(context, str, z, null);
        }
    }

    public static synchronized void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        synchronized (CrashReport.class) {
            if (context != null) {
                a = context;
                b.a(CrashModule.getInstance());
                b.a(context, str, z, userStrategy);
            }
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            w.d("Please call with context.", new Object[0]);
            return UtilityImpl.NET_TYPE_UNKNOWN;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context);
        return com.tencent.bugly.crashreport.common.info.a.b();
    }

    public static synchronized void testJavaCrash() {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
            } else {
                str = w.a;
            }
        }
    }

    public static synchronized void testNativeCrash() {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                w.a("start to create a native crash for test!", new Object[0]);
                c.a().k();
            } else {
                str = w.a;
            }
        }
    }

    public static synchronized void testANRCrash() {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                w.a("start to create a anr crash for test!", new Object[0]);
                c.a().l();
            } else {
                str = w.a;
            }
        }
    }

    public static synchronized void testBlockCrash() {
        synchronized (CrashReport.class) {
            if (!b.a) {
                String str = w.a;
            } else if (CrashModule.hasInitialized()) {
                w.a("start to create a anr crash for test!", new Object[0]);
                c.a().m();
            } else {
                throw new BuglyHintException("Report has not been initialed! pls to call method 'initCrashReport' first!");
            }
        }
    }

    public static synchronized void postCatchedException(Throwable th) {
        synchronized (CrashReport.class) {
            postCatchedException(th, false);
        }
    }

    public static synchronized void postCatchedException(Throwable th, boolean z) {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (!CrashModule.hasInitialized()) {
                str = w.a;
            } else if (th == null) {
                w.d("throwable is null, just return", new Object[0]);
            } else {
                c.a().a(Thread.currentThread(), th, false, null, null);
                if (z) {
                    w.a("clear user datas", new Object[0]);
                    com.tencent.bugly.crashreport.common.info.a.a(a).z();
                }
            }
        }
    }

    public static synchronized void closeNativeReport() {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                c.a().f();
            } else {
                str = w.a;
            }
        }
    }

    public static synchronized void startCrashReport() {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                c.a().c();
            } else {
                str = w.a;
            }
        }
    }

    public static synchronized void closeCrashReport() {
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                c.a().d();
            } else {
                str = w.a;
            }
        }
    }

    public static void closeBugly() {
        String str;
        if (!b.a) {
            str = w.a;
        } else if (!CrashModule.hasInitialized()) {
            str = w.a;
        } else if (a != null) {
            BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
            if (instance != null) {
                instance.unregist(a);
            }
            closeCrashReport();
            com.tencent.bugly.crashreport.biz.b.a(a);
            v a = v.a();
            if (a != null) {
                a.b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        String str;
        if (!b.a) {
            str = w.a;
        } else if (context == null) {
            str = w.a;
        } else {
            if (i <= 0) {
                w.d("setTag args tagId should > 0", new Object[0]);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).a(i);
            w.b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        String str;
        if (!b.a) {
            str = w.a;
            return -1;
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).D();
        } else {
            str = w.a;
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        String str2;
        if (!b.a) {
            str2 = w.a;
            return UtilityImpl.NET_TYPE_UNKNOWN;
        } else if (context == null) {
            str2 = w.a;
            return UtilityImpl.NET_TYPE_UNKNOWN;
        } else {
            Object obj = (str == null || str.trim().length() <= 0) ? 1 : null;
            return obj != null ? null : com.tencent.bugly.crashreport.common.info.a.a(context).g(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        String str3;
        if (!b.a) {
            str3 = w.a;
        } else if (context == null) {
            str3 = w.a;
        } else if (str == null) {
            w.d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            w.d("putUserData args value should not be null", new Object[0]);
        } else if (str.matches("[a-zA-Z[0-9]]+")) {
            if (str2.length() > 200) {
                w.d("user data value length over limit %d, it will be cutted!", Integer.valueOf(Impl.STATUS_SUCCESS));
                str2 = str2.substring(0, Impl.STATUS_SUCCESS);
            }
            com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(context);
            NativeCrashHandler instance;
            if (a.B().contains(str)) {
                instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                com.tencent.bugly.crashreport.common.info.a.a(context).b(str, str2);
                w.c("replace KV %s %s", str, str2);
            } else if (a.A() >= 10) {
                w.d("user data size is over limit %d, it will be cutted!", Integer.valueOf(XZBDevice.Stop));
            } else {
                if (str.length() > 50) {
                    w.d("user data key length over limit %d , will drop this new key %s", Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle), str);
                    str = str.substring(0, R.styleable.AppCompatTheme_buttonBarStyle);
                }
                instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                com.tencent.bugly.crashreport.common.info.a.a(context).b(str, str2);
                w.b("[param] set user data: %s - %s", str, str2);
            }
        } else {
            w.d(new StringBuilder("putUserData args key should match [a-zA-Z[0-9]]+  {").append(str).append(h.d).toString(), new Object[0]);
        }
    }

    public static String removeUserData(Context context, String str) {
        String str2;
        if (!b.a) {
            str2 = w.a;
            return UtilityImpl.NET_TYPE_UNKNOWN;
        } else if (context == null) {
            str2 = w.a;
            return UtilityImpl.NET_TYPE_UNKNOWN;
        } else {
            int i;
            if (str == null || str.trim().length() <= 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                return null;
            }
            w.b("[param] remove user data: %s", str);
            return com.tencent.bugly.crashreport.common.info.a.a(context).f(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        String str;
        if (!b.a) {
            str = w.a;
            return new HashSet();
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).B();
        } else {
            str = w.a;
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        String str;
        if (!b.a) {
            str = w.a;
            return -1;
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).A();
        } else {
            str = w.a;
            return -1;
        }
    }

    public static synchronized String getAppID() {
        String str;
        synchronized (CrashReport.class) {
            if (!b.a) {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            } else if (CrashModule.hasInitialized()) {
                str = com.tencent.bugly.crashreport.common.info.a.a(a).e();
            } else {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            }
        }
        return str;
    }

    public static void setUserId(String str) {
        String str2;
        if (!b.a) {
            str2 = w.a;
        } else if (CrashModule.hasInitialized()) {
            setUserId(a, str);
        } else {
            str2 = w.a;
        }
    }

    public static void setUserId(Context context, String str) {
        String str2;
        if (!b.a) {
            str2 = w.a;
        } else if (context == null) {
            str2 = w.a;
        } else if (str == null) {
            w.d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                w.d("userId %s length is over limit %d substring to %s", str, Integer.valueOf(R.styleable.AppCompatTheme_buttonStyle), str.substring(0, R.styleable.AppCompatTheme_buttonStyle));
                str = str2;
            }
            if (!str.equals(com.tencent.bugly.crashreport.common.info.a.a(context).f())) {
                com.tencent.bugly.crashreport.common.info.a.a(context).b(str);
                w.b("[user] set userId : %s", str);
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.setNativeUserId(str);
                }
                if (CrashModule.hasInitialized()) {
                    com.tencent.bugly.crashreport.biz.b.a();
                }
            }
        }
    }

    public static synchronized String getUserId() {
        String str;
        synchronized (CrashReport.class) {
            if (!b.a) {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            } else if (CrashModule.hasInitialized()) {
                str = com.tencent.bugly.crashreport.common.info.a.a(a).f();
            } else {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            }
        }
        return str;
    }

    public static synchronized String getAppVer() {
        String str;
        synchronized (CrashReport.class) {
            if (!b.a) {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            } else if (CrashModule.hasInitialized()) {
                str = com.tencent.bugly.crashreport.common.info.a.a(a).i;
            } else {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            }
        }
        return str;
    }

    public static synchronized String getAppChannel() {
        String str;
        synchronized (CrashReport.class) {
            if (!b.a) {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            } else if (CrashModule.hasInitialized()) {
                str = com.tencent.bugly.crashreport.common.info.a.a(a).j;
            } else {
                str = w.a;
                str = UtilityImpl.NET_TYPE_UNKNOWN;
            }
        }
        return str;
    }

    public static synchronized void setContext(Context context) {
        synchronized (CrashReport.class) {
            a = context;
        }
    }

    public static synchronized boolean isLastSessionCrash() {
        boolean z = false;
        synchronized (CrashReport.class) {
            String str;
            if (!b.a) {
                str = w.a;
            } else if (CrashModule.hasInitialized()) {
                z = c.a().b();
            } else {
                str = w.a;
            }
        }
        return z;
    }

    public static synchronized void setSdkExtraData(Context context, String str, String str2) {
        Object obj = null;
        synchronized (CrashReport.class) {
            if (!b.a) {
                String str3 = w.a;
            } else if (context != null) {
                Object obj2;
                int i;
                if (str != null) {
                    if (str.trim().length() > 0) {
                        obj2 = null;
                        if (obj2 == null) {
                            if (str2 == null || str2.trim().length() <= 0) {
                                i = 1;
                            }
                            if (obj == null) {
                                com.tencent.bugly.crashreport.common.info.a.a(context).a(str, str2);
                            }
                        }
                    }
                }
                int i2 = 1;
                if (obj2 == null) {
                    i = 1;
                    if (obj == null) {
                        com.tencent.bugly.crashreport.common.info.a.a(context).a(str, str2);
                    }
                }
            }
        }
    }

    public static Map<String, String> getSdkExtraData() {
        String str;
        if (!b.a) {
            str = w.a;
            return new HashMap();
        } else if (CrashModule.hasInitialized()) {
            return com.tencent.bugly.crashreport.common.info.a.a(a).z;
        } else {
            str = w.a;
            return null;
        }
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!b.a) {
            String str = w.a;
            return new HashMap();
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).z;
        } else {
            w.d("Context should not be null.", new Object[0]);
            return null;
        }
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null) {
            int i;
            if (str == null || str.trim().length() <= 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                if (str2 == null || str2.trim().length() <= 0) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i == 0) {
                    String str3;
                    String replace = str.replace("[a-zA-Z[0-9]]+", com.umeng.a.d);
                    if (replace.length() > 100) {
                        str3 = w.a;
                        String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{Integer.valueOf(R.styleable.AppCompatTheme_buttonBarStyle)});
                        replace = replace.substring(0, R.styleable.AppCompatTheme_buttonBarStyle);
                    }
                    if (str2.length() > 500) {
                        str3 = w.a;
                        String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{Integer.valueOf(Impl.STATUS_SUCCESS)});
                        str2 = str2.substring(0, Impl.STATUS_SUCCESS);
                    }
                    com.tencent.bugly.crashreport.common.info.a.a(context).c(replace, str2);
                    w.b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
                }
            }
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!b.a) {
            String str = w.a;
        } else if (context == null) {
            w.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                w.c("App is in foreground.", new Object[0]);
            } else {
                w.c("App is in background.", new Object[0]);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).n = z;
        }
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!b.a) {
            String str = w.a;
        } else if (context == null) {
            w.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                w.c("This is a development device.", new Object[0]);
            } else {
                w.c("This is not a development device.", new Object[0]);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).x = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (b.a) {
            com.tencent.bugly.crashreport.biz.b.a(j);
        } else {
            String str = w.a;
        }
    }

    public static void setAppVersion(Context context, String str) {
        String str2;
        if (!b.a) {
            str2 = w.a;
        } else if (context == null) {
            str2 = w.a;
        } else if (str == null) {
            str2 = w.a;
        } else {
            com.tencent.bugly.crashreport.common.info.a.a(context).i = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppVersion(str);
            }
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebView webView, boolean z, boolean z2) {
        String str;
        if (webView == null) {
            str = w.a;
            return false;
        } else if (CrashModule.hasInitialized()) {
            w.a("Set Javascript exception monitor of webview.", new Object[0]);
            if (b.a) {
                w.c("URL of webview is %s", webView.getUrl());
                if (webView.getUrl() == null) {
                    return false;
                }
                if (z2 || VERSION.SDK_INT >= 19) {
                    WebSettings settings = webView.getSettings();
                    if (!settings.getJavaScriptEnabled()) {
                        w.a("Enable the javascript needed by webview monitor.", new Object[0]);
                        settings.setJavaScriptEnabled(true);
                    }
                    H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webView);
                    if (instance != null) {
                        w.a("Add a secure javascript interface to the webview.", new Object[0]);
                        webView.addJavascriptInterface(instance, "exceptionUploader");
                    }
                    if (z) {
                        w.a("Inject bugly.js(v%s) to the webview.", com.tencent.bugly.crashreport.crash.h5.c.b());
                        String a = com.tencent.bugly.crashreport.crash.h5.c.a();
                        if (a == null) {
                            w.e("Failed to inject Bugly.js.", com.tencent.bugly.crashreport.crash.h5.c.b());
                            return false;
                        }
                        webView.loadUrl(new StringBuilder(BaseJsInterface.JS_PREFIX).append(a).toString());
                    }
                    return true;
                }
                w.e("This interface is only available for Android 4.4 or later.", new Object[0]);
                return false;
            }
            str = w.a;
            return false;
        } else {
            w.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
    }
}
