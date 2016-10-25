package com.inmobi.commons.core.utilities.info;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.Map;

// compiled from: AppInfo.java
public class a {
    private static final String a;
    private static a b;
    private static Object c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Map<String, String> h;

    static {
        a = a.class.getSimpleName();
        c = new Object();
    }

    private a() {
        this.h = new HashMap();
        a(com.inmobi.commons.a.a.b());
        d();
    }

    private void a(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo != null) {
                this.d = applicationInfo.packageName;
                this.e = applicationInfo.loadLabel(packageManager).toString();
                this.g = packageManager.getInstallerPackageName(this.d);
            }
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            String str = null;
            if (packageInfo != null) {
                str = packageInfo.versionName;
                if (str == null || str.equals(com.umeng.a.d)) {
                    str = packageInfo.versionCode;
                }
            }
            if (str != null && !str.equals(com.umeng.a.d)) {
                this.f = str;
            }
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Failed to fetch app info completely", e);
        }
    }

    private void d() {
        this.h.put("u-appbid", this.d);
        this.h.put("u-appdnm", this.e);
        this.h.put("u-appver", this.f);
    }

    public static a a() {
        a aVar = b;
        if (aVar == null) {
            synchronized (c) {
                aVar = b;
                if (aVar == null) {
                    aVar = new a();
                    b = aVar;
                }
            }
        }
        return aVar;
    }

    public String b() {
        return this.g;
    }

    public Map<String, String> c() {
        return this.h;
    }
}
