package com.xunlei.downloadprovider.service.downloads.kernel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        new StringBuilder("Get Network Action:").append(intent.getAction());
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            new StringBuilder("Target Action:").append(intent.getAction());
            c a = c.a();
            if (a.c != null && !a.d()) {
                new Thread(new d(a)).start();
            }
        }
    }
}
