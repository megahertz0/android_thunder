package com.xunlei.android.xlstat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import java.io.File;

public final class XLStatManager {
    private static XLStatManager e;
    public XLStatLoader a;
    public Context b;
    public NetworkChangeReceiver c;
    private final String d;

    private class NetworkChangeReceiver extends BroadcastReceiver implements Runnable {
        int a;
        Thread b;

        private NetworkChangeReceiver() {
        }

        public final void onReceive(Context context, Intent intent) {
            XLLog.b("TAG_VipNetReceiver", "call onReceive");
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                int b = XLUtil.b(context);
                XLLog.b("TAG_VipNetReceiver", new StringBuilder("onReceive nettype=").append(b).toString());
                synchronized (this) {
                    this.a = b;
                    if (this.b != null) {
                        return;
                    }
                    this.b = new Thread(this);
                    this.b.start();
                }
            }
        }

        public final void run() {
            while (true) {
                int i = this.a;
                synchronized (this) {
                    if (i == this.a) {
                        this.b = null;
                        return;
                    }
                }
            }
        }
    }

    public static synchronized XLStatManager a(Context context) {
        XLStatManager xLStatManager;
        synchronized (XLStatManager.class) {
            if (e == null) {
                e = new XLStatManager(context);
            }
            xLStatManager = e;
        }
        return xLStatManager;
    }

    private XLStatManager(Context context) {
        this.d = "XLStatManager";
        this.c = null;
        if (context != null) {
            this.a = new XLStatLoader(context);
            this.b = context.getApplicationContext();
            XLLog.a(new File(Environment.getExternalStorageDirectory().getPath(), "xunlei_ds_log.ini").getPath());
        }
    }
}
