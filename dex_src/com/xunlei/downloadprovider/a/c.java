package com.xunlei.downloadprovider.a;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.agoo.message.MessageService;

// compiled from: ApkHelper.java
public final class c {
    private static Context a;
    private static Map<String, SoftReference<a>> b;
    private static Pattern c;

    static {
        a = null;
        b = new HashMap();
        c = Pattern.compile("\\d+(\\.\\d+)?");
    }

    public static void a(Context context) {
        a = context;
    }

    private static a e(Context context, String str) {
        Resources resources;
        Exception e;
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        if (!(packageArchiveInfo == null || packageArchiveInfo.applicationInfo == null)) {
            try {
                Class forName = Class.forName("android.content.res.AssetManager");
                Object newInstance = forName.getConstructor(null).newInstance(null);
                forName.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(newInstance, new Object[]{str});
                resources = context.getResources();
                try {
                    resources = (Resources) Resources.class.getConstructor(new Class[]{forName, DisplayMetrics.class, Configuration.class}).newInstance(new Object[]{newInstance, resources.getDisplayMetrics(), resources.getConfiguration()});
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (resources != null) {
                        return new a(packageArchiveInfo, resources);
                    }
                    return null;
                }
            } catch (Exception e3) {
                e = e3;
                resources = null;
                e.printStackTrace();
                if (resources != null) {
                    return new a(packageArchiveInfo, resources);
                }
                return null;
            }
            if (resources != null) {
                return new a(packageArchiveInfo, resources);
            }
        }
        return null;
    }

    private static a f(Context context, String str) {
        try {
            Object e = e(context, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            e = null;
        }
        if (r1 == null) {
            return null;
        }
        b.put(str, new SoftReference(r1));
        return r1;
    }

    public static a a(Context context, String str) {
        if (b.get(str) == null) {
            return f(context, str);
        }
        SoftReference softReference = (SoftReference) b.get(str);
        return softReference.get() == null ? f(context, str) : (a) softReference.get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.content.Context r11, com.xunlei.downloadprovider.a.c.a r12) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.a.c.a(android.content.Context, com.xunlei.downloadprovider.a.c$a):int");
        /*
        r3 = 5;
        r4 = 3;
        r5 = 0;
        r0 = 1;
        if (r12 == 0) goto L_0x0022;
    L_0x0006:
        r1 = r12.a;
        if (r1 == 0) goto L_0x0022;
    L_0x000a:
        r1 = r11.getPackageManager();	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r12.c();	 Catch:{ NameNotFoundException -> 0x007a }
        r6 = 0;
        r1 = r1.getPackageInfo(r2, r6);	 Catch:{ NameNotFoundException -> 0x007a }
        if (r1 == 0) goto L_0x0022;
    L_0x0019:
        r0 = r1.versionCode;	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r12.a;	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r2.versionCode;	 Catch:{ NameNotFoundException -> 0x007a }
        if (r0 <= r2) goto L_0x0023;
    L_0x0021:
        r0 = r3;
    L_0x0022:
        return r0;
    L_0x0023:
        r0 = r1.versionCode;	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r12.a;	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r2.versionCode;	 Catch:{ NameNotFoundException -> 0x007a }
        if (r0 >= r2) goto L_0x002d;
    L_0x002b:
        r0 = r4;
        goto L_0x0022;
    L_0x002d:
        r0 = r1.versionName;	 Catch:{ NameNotFoundException -> 0x007a }
        r1 = r12.a;	 Catch:{ NameNotFoundException -> 0x007a }
        r1 = r1.versionName;	 Catch:{ NameNotFoundException -> 0x007a }
        if (r0 == 0) goto L_0x0037;
    L_0x0035:
        if (r1 != 0) goto L_0x003c;
    L_0x0037:
        r0 = r5;
    L_0x0038:
        if (r0 <= 0) goto L_0x0074;
    L_0x003a:
        r0 = r3;
        goto L_0x0022;
    L_0x003c:
        r2 = "\\.";
        r7 = r0.split(r2);	 Catch:{ NameNotFoundException -> 0x007a }
        r0 = "\\.";
        r8 = r1.split(r0);	 Catch:{ NameNotFoundException -> 0x007a }
        r1 = r7.length;	 Catch:{ NameNotFoundException -> 0x007a }
        r2 = r8.length;	 Catch:{ NameNotFoundException -> 0x007a }
        if (r1 >= r2) goto L_0x006f;
    L_0x004e:
        r0 = r1;
    L_0x004f:
        r6 = r5;
    L_0x0050:
        if (r6 >= r0) goto L_0x007d;
    L_0x0052:
        r9 = r7[r6];	 Catch:{ NameNotFoundException -> 0x007a }
        r9 = b(r9);	 Catch:{ NameNotFoundException -> 0x007a }
        r9 = java.lang.Integer.parseInt(r9);	 Catch:{ NameNotFoundException -> 0x007a }
        r10 = r8[r6];	 Catch:{ NameNotFoundException -> 0x007a }
        r10 = b(r10);	 Catch:{ NameNotFoundException -> 0x007a }
        r10 = java.lang.Integer.parseInt(r10);	 Catch:{ NameNotFoundException -> 0x007a }
        if (r9 == r10) goto L_0x0071;
    L_0x0068:
        r0 = r9 - r10;
    L_0x006a:
        if (r0 != 0) goto L_0x0038;
    L_0x006c:
        r0 = r1 - r2;
        goto L_0x0038;
    L_0x006f:
        r0 = r2;
        goto L_0x004f;
    L_0x0071:
        r6 = r6 + 1;
        goto L_0x0050;
    L_0x0074:
        if (r0 >= 0) goto L_0x0078;
    L_0x0076:
        r0 = r4;
        goto L_0x0022;
    L_0x0078:
        r0 = 4;
        goto L_0x0022;
    L_0x007a:
        r0 = move-exception;
        r0 = 2;
        goto L_0x0022;
    L_0x007d:
        r0 = r5;
        goto L_0x006a;
        */
    }

    private static String b(String str) {
        String str2 = MessageService.MSG_DB_READY_REPORT;
        if (str == null) {
            return str2;
        }
        Matcher matcher = c.matcher(str);
        return matcher.find() ? matcher.group() : str2;
    }

    public static boolean b(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo == null) {
                return false;
            }
            String str2 = packageArchiveInfo.applicationInfo.packageName;
            new StringBuilder(" packageName = ").append(str2).append(", version = ").append(packageArchiveInfo.versionName);
            PackageInfo packageInfo = packageManager.getPackageInfo(str2, 1);
            if (packageInfo == null) {
                return false;
            }
            if (packageInfo.versionCode == packageArchiveInfo.versionCode) {
                return true;
            }
            new StringBuilder("version not match, installed version = ").append(packageInfo.versionCode).append(", apk version = ").append(packageArchiveInfo.versionCode);
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(String str) {
        PackageManager packageManager = a.getPackageManager();
        if (str == null) {
            return false;
        }
        try {
            return packageManager.getPackageInfo(str, 0) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean c(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return false;
        }
        context.startActivity(launchIntentForPackage);
        return true;
    }

    public static void d(Context context, String str) {
        String c = a(context, str).c();
        Editor edit = context.getSharedPreferences("apk_path", 0).edit();
        edit.putString(c, str);
        edit.commit();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }
}
