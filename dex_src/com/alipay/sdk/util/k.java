package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.widget.LinearLayout;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.app.h;
import com.alipay.sdk.app.i;
import com.alipay.sdk.app.statistic.c;
import com.qq.e.comm.constants.Constants.KEYS;
import com.taobao.accs.utl.BaseMonitor;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.spdy.SpdyAgent;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
public final class k {
    static final String a = "com.alipay.android.app";
    public static final String b = "com.eg.android.AlipayGphone";
    public static final int c = 99;
    public static final int d = 73;

    public static class a {
        public Signature[] a;
        public int b;

        public final boolean a() {
            if (this.a == null || this.a.length <= 0) {
                return false;
            }
            for (int i = 0; i < this.a.length; i++) {
                String a = k.a(this.a[i].toByteArray());
                if (a != null && !TextUtils.equals(a, com.alipay.sdk.cons.a.g)) {
                    com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.s, a);
                    return true;
                }
            }
            return false;
        }
    }

    public static Map<String, String> a(String str) {
        Map<String, String> hashMap = new HashMap();
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        int length = split.length;
        for (int i = 0; i < length; i++) {
            String str2 = split[i];
            int indexOf = str2.indexOf("=", 1);
            hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
        }
        return hashMap;
    }

    public static String a(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            if (length <= str.length()) {
                return com.umeng.a.d;
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, length);
            }
            return i <= 0 ? str3.substring(length) : str3.substring(length, i);
        } catch (Throwable th) {
            return com.umeng.a.d;
        }
    }

    public static String a(byte[] bArr) {
        try {
            String toString = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (toString.indexOf("modulus") != -1) {
                return toString.substring(toString.indexOf("modulus") + 8, toString.lastIndexOf(MiPushClient.ACCEPT_TIME_SEPARATOR)).trim();
            }
        } catch (Throwable e) {
            com.alipay.sdk.app.statistic.a.a(BaseMonitor.ALARM_POINT_AUTH, c.m, e);
        }
        return null;
    }

    private static a i(Context context) {
        return a(context, b);
    }

    public static a a(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, Impl.STATUS_RUNNING);
            if (!a(packageInfo)) {
                try {
                    packageInfo = c(context, str);
                } catch (Throwable th) {
                    com.alipay.sdk.app.statistic.a.a(BaseMonitor.ALARM_POINT_AUTH, c.l, th);
                }
            }
        } catch (Throwable th2) {
            try {
                com.alipay.sdk.app.statistic.a.a(BaseMonitor.ALARM_POINT_AUTH, c.j, th2);
                if (a(null)) {
                    packageInfo = null;
                } else {
                    try {
                        packageInfo = c(context, str);
                    } catch (Throwable th22) {
                        com.alipay.sdk.app.statistic.a.a(BaseMonitor.ALARM_POINT_AUTH, c.l, th22);
                        packageInfo = null;
                    }
                }
            } catch (Throwable th3) {
                if (!a(null)) {
                    try {
                        c(context, str);
                    } catch (Throwable th4) {
                        com.alipay.sdk.app.statistic.a.a(BaseMonitor.ALARM_POINT_AUTH, c.l, th4);
                    }
                }
            }
        }
        if (!a(packageInfo) || packageInfo == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = packageInfo.signatures;
        aVar.b = packageInfo.versionCode;
        return aVar;
    }

    private static boolean a(PackageInfo packageInfo) {
        String str = com.umeng.a.d;
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.statistic.a.a(BaseMonitor.ALARM_POINT_AUTH, c.k, str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, Impl.STATUS_RUNNING);
    }

    private static PackageInfo c(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(Impl.STATUS_RUNNING)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    private static a b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = packageInfo.signatures;
        aVar.b = packageInfo.versionCode;
        return aVar;
    }

    public static boolean a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            return packageInfo != null && packageInfo.versionCode > 73;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.A, th);
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(b, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            return packageInfo != null && packageInfo.versionCode < 99;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String d(Context context) {
        String a = a();
        String b = b();
        String e = e(context);
        return new StringBuilder(" (").append(a).append(h.b).append(b).append(h.b).append(e).append(";;").append(f(context)).append(")(sdk android)").toString();
    }

    public static String a() {
        return new StringBuilder("Android ").append(VERSION.RELEASE).toString();
    }

    public static WebView a(Activity activity, String str, String str2) {
        Method method;
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(activity.getApplicationContext()).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
        View linearLayout = new LinearLayout(activity);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        activity.setContentView(linearLayout, layoutParams);
        View webView = new WebView(activity);
        layoutParams.weight = 1.0f;
        webView.setVisibility(0);
        linearLayout.addView(webView, layoutParams);
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + d(activity));
        settings.setRenderPriority(RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setDownloadListener(new l(activity));
        if (VERSION.SDK_INT >= 7) {
            try {
                method = webView.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                if (method != null) {
                    method.invoke(webView.getSettings(), new Object[]{Boolean.valueOf(true)});
                }
            } catch (Exception e) {
            }
        }
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
            try {
                method = webView.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method != null) {
                    method.invoke(webView, new Object[]{"searchBoxJavaBridge_"});
                    method.invoke(webView, new Object[]{"accessibility"});
                    method.invoke(webView, new Object[]{"accessibilityTraversal"});
                }
            } catch (Throwable th2) {
            }
        }
        if (VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(1);
        }
        webView.loadUrl(str);
        return webView;
    }

    public static String b() {
        String d = d();
        int indexOf = d.indexOf(SocializeConstants.OP_DIVIDER_MINUS);
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        indexOf = d.indexOf("\n");
        if (indexOf != -1) {
            d = d.substring(0, indexOf);
        }
        return new StringBuilder("Linux ").append(d).toString();
    }

    private static String d() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            CharSequence readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (matcher.matches()) {
                return matcher.groupCount() < 4 ? "Unavailable" : new StringBuilder(matcher.group(1)).append("\n").append(matcher.group(XZBDevice.DOWNLOAD_LIST_RECYCLE)).append(" ").append(matcher.group(XZBDevice.DOWNLOAD_LIST_FAILED)).append("\n").append(matcher.group(XZBDevice.DOWNLOAD_LIST_ALL)).toString();
            } else {
                return "Unavailable";
            }
        } catch (IOException e) {
            return "Unavailable";
        }
    }

    public static String e(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String f(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(displayMetrics.widthPixels);
        stringBuilder.append("*");
        stringBuilder.append(displayMetrics.heightPixels);
        return stringBuilder.toString();
    }

    private static DisplayMetrics j(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static String k(Context context) {
        String a = j.a(context);
        return a.substring(0, a.indexOf(HttpConstant.SCHEME_SPLIT));
    }

    private static String e() {
        return "-1;-1";
    }

    public static String c() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            switch (random.nextInt(XZBDevice.DOWNLOAD_LIST_FAILED)) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 65.0d))));
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    stringBuilder.append(String.valueOf((char) ((int) Math.round((Math.random() * 25.0d) + 97.0d))));
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    stringBuilder.append(String.valueOf(new Random().nextInt(XZBDevice.Stop)));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public static boolean b(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static String g(Context context) {
        String str = com.umeng.a.d;
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(b)) {
                    str = str + "#M";
                } else {
                    String str2;
                    if (runningAppProcessInfo.processName.startsWith("com.eg.android.AlipayGphone:")) {
                        str2 = str + "#" + runningAppProcessInfo.processName.replace("com.eg.android.AlipayGphone:", com.umeng.a.d);
                    } else {
                        str2 = str;
                    }
                    str = str2;
                }
            }
        } catch (Throwable th) {
            str = com.umeng.a.d;
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        return str.length() == 0 ? "N" : str;
    }

    public static String h(Context context) {
        try {
            List installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                Object obj;
                PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
                int i2 = packageInfo.applicationInfo.flags;
                if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    if (packageInfo.packageName.equals(b)) {
                        stringBuilder.append(packageInfo.packageName).append(packageInfo.versionCode).append(SocializeConstants.OP_DIVIDER_MINUS);
                    } else if (!packageInfo.packageName.contains("theme") && !packageInfo.packageName.startsWith("com.google.") && !packageInfo.packageName.startsWith("com.android.")) {
                        stringBuilder.append(packageInfo.packageName).append(SocializeConstants.OP_DIVIDER_MINUS);
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(KEYS.BIZ, c.C, th);
            return com.umeng.a.d;
        }
    }

    @SuppressLint({"InlinedApi"})
    private static boolean c(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return (i & 1) == 0 && (i & 128) == 0;
    }

    public static boolean a(WebView webView, String str, Activity activity) {
        i a;
        if (!TextUtils.isEmpty(str)) {
            if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.h.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.i.toLowerCase())) {
                try {
                    a a2 = a(activity, b);
                    if (!(a2 == null || a2.a())) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.cons.a.h);
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (Throwable th) {
                }
            } else if (TextUtils.equals(str, com.alipay.sdk.cons.a.k) || TextUtils.equals(str, com.alipay.sdk.cons.a.l)) {
                h.a = h.a();
                activity.finish();
            } else if (str.startsWith(com.alipay.sdk.cons.a.j)) {
                try {
                    String substring = str.substring(str.indexOf(com.alipay.sdk.cons.a.j) + 24);
                    int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf(com.alipay.sdk.cons.a.m) + 10));
                    if (parseInt == i.a.h || parseInt == i.g.h) {
                        StringBuilder stringBuilder = new StringBuilder();
                        substring = URLDecoder.decode(str);
                        substring = substring.substring(substring.indexOf(com.alipay.sdk.cons.a.j) + 24, substring.lastIndexOf(com.alipay.sdk.cons.a.m));
                        if (substring.contains(com.alipay.sdk.cons.a.o)) {
                            int indexOf = substring.indexOf(com.alipay.sdk.cons.a.o) + 12;
                            stringBuilder.append(substring.split(com.alipay.sdk.cons.a.o)[0]).append(com.alipay.sdk.cons.a.o).append(substring.substring(indexOf, substring.indexOf(com.alipay.sdk.sys.a.a, indexOf))).append(substring.substring(substring.indexOf(com.alipay.sdk.sys.a.a, indexOf)));
                            substring = stringBuilder.toString();
                        }
                        i a3 = i.a(parseInt);
                        h.a = h.a(a3.h, a3.i, substring);
                        activity.runOnUiThread(new m(activity));
                    } else {
                        a = i.a(i.b.h);
                        h.a = h.a(a.h, a.i, com.umeng.a.d);
                        activity.runOnUiThread(new m(activity));
                    }
                } catch (Exception e) {
                    a = i.a(i.e.h);
                    h.a = h.a(a.h, a.i, com.umeng.a.d);
                }
            } else {
                webView.loadUrl(str);
            }
        }
        return true;
    }
}
