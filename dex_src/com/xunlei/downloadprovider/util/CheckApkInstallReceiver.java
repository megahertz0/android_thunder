package com.xunlei.downloadprovider.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.notification.a;

public class CheckApkInstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
            String dataString = intent.getDataString();
            if (dataString != null) {
                dataString = dataString.substring(dataString.indexOf(":") + 1);
            }
            int a = j.a(context, "clearApkNotification", dataString);
            a.a(context).a(a);
            j.b(context, "clearApkNotification", dataString);
            new StringBuilder("checkpackageName=====").append(dataString).append("checktaskid =  ").append(a);
        }
    }
}
