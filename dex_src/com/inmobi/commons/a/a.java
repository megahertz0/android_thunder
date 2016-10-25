package com.inmobi.commons.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.b;
import com.taobao.accs.data.Message;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

// compiled from: SdkContext.java
public final class a {
    private static final String a;
    private static Context b;
    private static String c;
    private static String d;
    private static AtomicBoolean e;

    // compiled from: SdkContext.java
    final class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass_1(Context context) {
            this.a = context;
        }

        public final void run() {
            c = new WebView(this.a).getSettings().getUserAgentString();
        }
    }

    static {
        a = a.class.getSimpleName();
        c = com.umeng.a.d;
        d = com.umeng.a.d;
        e = new AtomicBoolean();
    }

    public static void a(Context context, String str) {
        if (!a()) {
            b = context.getApplicationContext();
            d = str;
            e.set(true);
            b(context);
            c(context);
        }
    }

    public static boolean a() {
        return b != null;
    }

    public static Context b() {
        return b;
    }

    public static String c() {
        return d;
    }

    public static String d() {
        return c;
    }

    public static boolean e() {
        return e.get();
    }

    public static void a(boolean z) {
        e.set(z);
    }

    public static File a(Context context) {
        return new File(context.getCacheDir(), "im_cached_content");
    }

    public static void a(File file, String str) {
        if (str == null || str.trim().length() == 0) {
            b.a(file);
        } else {
            b.a(new File(file, str));
        }
    }

    public static void b(Context context) {
        if (VERSION.SDK_INT >= 17) {
            c = d(context);
        } else {
            new Handler(context.getMainLooper()).post(new AnonymousClass_1(context));
        }
    }

    private static void c(Context context) {
        File a = a(context);
        a(a, null);
        if (!a.mkdir() && !a.isDirectory()) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot create media cache directory");
        }
    }

    @TargetApi(17)
    private static String d(Context context) {
        return WebSettings.getDefaultUserAgent(context.getApplicationContext());
    }

    public static boolean a(String str) {
        Context b = b();
        if (b == null) {
            return false;
        }
        PackageManager packageManager = b.getPackageManager();
        if (VERSION.SDK_INT < 23) {
            return packageManager.checkPermission(str, packageManager.getNameForUid(Binder.getCallingUid())) == 0;
        } else {
            return b(str);
        }
    }

    public static boolean b(String str) {
        Context b = b();
        if (b == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = b.getPackageManager().getPackageInfo(b.getPackageName(), Message.FLAG_ERR);
            if (packageInfo.requestedPermissions == null) {
                return false;
            }
            String[] strArr = packageInfo.requestedPermissions;
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                if (strArr[i].equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Could not check manifest for permission:").append(str).append(" Error:").append(e.getLocalizedMessage()).toString());
            return false;
        }
    }

    public static void a(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        context.startActivity(intent);
    }
}
