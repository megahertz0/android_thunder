package com.xiaomi.push.service.timers;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.util.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class a {
    private static a a;

    public static synchronized void a() {
        synchronized (a.class) {
            if (a != null) {
                a.a();
            }
        }
    }

    public static void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        if ("com.xiaomi.xmsf".equals(applicationContext.getPackageName())) {
            a = new b(applicationContext);
            return;
        }
        Object obj;
        try {
            int i;
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), XZBDevice.DOWNLOAD_LIST_ALL);
            if (packageInfo.services != null) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                int length = serviceInfoArr.length;
                for (i = 0; i < length; i++) {
                    ServiceInfo serviceInfo = serviceInfoArr[i];
                    if ("com.xiaomi.push.service.XMJobService".equals(serviceInfo.name) && "android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                        obj = 1;
                        break;
                    }
                }
            }
            obj = null;
        } catch (Exception e) {
            b.a(new StringBuilder("check service err : ").append(e.getMessage()).toString());
            obj = null;
        }
        if (obj == null && h.b(applicationContext)) {
            throw new RuntimeException("Should export service: com.xiaomi.push.service.XMJobService with permission android.permission.BIND_JOB_SERVICE in AndroidManifest.xml file");
        }
        i = VERSION.SDK_INT;
        a = new b(applicationContext);
    }

    public static synchronized void a(boolean z) {
        synchronized (a.class) {
            if (a == null) {
                b.a("timer is not initialized");
            } else {
                a.a(z);
            }
        }
    }

    public static synchronized boolean b() {
        boolean b;
        synchronized (a.class) {
            b = a == null ? false : a.b();
        }
        return b;
    }
}
