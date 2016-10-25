package com.xunlei.downloadprovider.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.a.b;
import com.umeng.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.user.ag;
import com.xunlei.downloadprovider.frame.user.bo;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// compiled from: XLUtil.java
public class ai {
    private static final String a;

    static {
        a = ai.class.getSimpleName();
    }

    public static String a(String str) {
        return !str.endsWith("/") ? str + "/" : str;
    }

    public static int b(String str) {
        bo.a();
        bo.a(str);
        List installedPackages = BrothersApplication.a().getApplicationContext().getPackageManager().getInstalledPackages(0);
        List arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(((PackageInfo) installedPackages.get(i)).packageName);
            }
        }
        return arrayList.contains(str) ? 1 : 0;
    }

    public static int c(String str) {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) BrothersApplication.a().getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName != null && !runningAppProcessInfo.processName.equals(a.d) && runningAppProcessInfo.processName.startsWith(str)) {
                return 1;
            }
        }
        return 0;
    }

    public static void d(String str) {
        try {
            BrothersApplication.a().getApplicationContext().startActivity(BrothersApplication.a().getApplicationContext().getPackageManager().getLaunchIntentForPackage(str));
            bo.a();
            ag c = bo.c(str);
            if (c != null) {
                bo.a();
                bo.d(c.b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context) {
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse(new StringBuilder("package:").append(BrothersApplication.a.getPackageName()).toString()));
            if (VERSION.SDK_INT >= 21) {
                intent.setFlags(b.MAX_POOL_SIZE);
            } else {
                intent.setFlags(b.MAX_POOL_SIZE);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean a() {
        return !TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.equalsIgnoreCase("Xiaomi");
    }

    private static String f(String str) throws MalformedURLException {
        Matcher matcher = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.\u516c\u53f8|\\.\u4e2d\u56fd|\\.\u7f51\u7edc)").matcher(new URL(str).getHost().toLowerCase());
        return matcher.find() ? matcher.group() : null;
    }

    public static boolean e(String str) {
        try {
            return (f(str).equals("xunlei.com") || f(str).equals("sjzhushou.com")) ? false : true;
        } catch (Exception e) {
            return false;
        }
    }
}
