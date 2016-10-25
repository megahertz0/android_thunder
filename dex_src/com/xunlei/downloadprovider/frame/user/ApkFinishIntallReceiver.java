package com.xunlei.downloadprovider.frame.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.downloadprovider.web.w;

public class ApkFinishIntallReceiver extends BroadcastReceiver {
    public static final String a;

    static {
        a = ApkFinishIntallReceiver.class.getSimpleName();
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED") || intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            String dataString = intent.getDataString();
            Object trim = dataString.substring(dataString.indexOf(":") + 1).trim();
            new StringBuilder("\u5b89\u88c5\u4e86:").append(trim).append("\u5305\u540d\u7684\u7a0b\u5e8f");
            bo.a();
            ag c = bo.c(trim);
            if (!(c == null || c.a == null || c.a.equals(a.d))) {
                bo.a().b(c);
            }
            if (!TextUtils.isEmpty(trim)) {
                w.a();
                dataString = w.a(trim);
                w.a();
                w.b(dataString);
            }
        }
    }
}
