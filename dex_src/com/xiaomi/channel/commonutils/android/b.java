package com.xiaomi.channel.commonutils.android;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.socialize.weixin.BuildConfig;
import com.xunlei.xllib.R;
import java.util.List;
import org.android.spdy.SpdyProtocol;

public class b {

    public enum a {
        UNKNOWN(0),
        ALLOWED(1),
        NOT_ALLOWED(2);
        private final int d;

        static {
            a = new com.xiaomi.channel.commonutils.android.b.a("UNKNOWN", 0, 0);
            b = new com.xiaomi.channel.commonutils.android.b.a("ALLOWED", 1, 1);
            c = new com.xiaomi.channel.commonutils.android.b.a("NOT_ALLOWED", 2, 2);
            e = new com.xiaomi.channel.commonutils.android.b.a[]{a, b, c};
        }

        private a(int i) {
            this.d = i;
        }

        public final int a() {
            return this.d;
        }
    }

    public static String a(Context context) {
        try {
            List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            return (com.xiaomi.channel.commonutils.misc.b.a(runningTasks) || runningTasks.get(0) == null || ((RunningTaskInfo) runningTasks.get(0)).topActivity == null) ? null : ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName();
        } catch (Throwable th) {
            return null;
        }
    }

    public static String a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, SpdyProtocol.SLIGHTSSL_L7E);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : BuildConfig.VERSION_NAME;
    }

    public static int b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, SpdyProtocol.SLIGHTSSL_L7E);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionCode : 0;
    }

    public static boolean b(Context context) {
        return TextUtils.equals(context.getPackageName(), a(context));
    }

    public static int c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, 1).versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    @TargetApi(19)
    public static a d(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || VERSION.SDK_INT < 19) {
            return a.a;
        }
        try {
            if (((Integer) com.xiaomi.channel.commonutils.reflect.a.a(AppOpsManager.class, "OP_POST_NOTIFICATION")) == null) {
                return a.a;
            }
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            Integer num = (Integer) com.xiaomi.channel.commonutils.reflect.a.a((AppOpsManager) context.getSystemService("appops"), "checkOpNoThrow", num, Integer.valueOf(applicationInfo.uid), str);
            return (num == null || num.intValue() != 0) ? a.c : a.b;
        } catch (Throwable th) {
            return a.a;
        }
    }

    public static Signature[] e(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, R.styleable.AppCompatTheme_imageButtonStyle).signatures;
        } catch (Exception e) {
            return null;
        }
    }
}
