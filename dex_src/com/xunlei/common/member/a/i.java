package com.xunlei.common.member.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.xunlei.common.base.XLLog;

// compiled from: XLNetWorkMonitor.java
public class i {
    private static String f = "1.1";
    private static String g = "ali_task";
    private static String h = "ali_auth_param";
    private static String i = "http://login.i.xunlei.com/thirdlogin4/entrance.php?module=alipay_app";
    private h a;
    private Context b;
    private boolean c;
    private boolean d;
    private BroadcastReceiver e;

    public i(h hVar, Context context) {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = true;
        this.e = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) i.this.b.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                        XLLog.v("XLNetWorkMonitor", "net work disconnected!");
                        i.a(i.this, false, null);
                        return;
                    }
                    activeNetworkInfo.getTypeName();
                    XLLog.v("XLNetWorkMonitor", "net work connected!");
                    i.a(i.this, true, activeNetworkInfo);
                }
            }
        };
        this.a = hVar;
        this.b = context;
    }

    public final void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.b.registerReceiver(this.e, intentFilter);
    }

    public final void b() {
        if (this.e != null) {
            this.b.unregisterReceiver(this.e);
        }
    }

    private void a(boolean z, NetworkInfo networkInfo) {
        if (this.d) {
            this.a.a(z);
            this.d = false;
        } else if (this.c != z) {
            this.a.a(z);
        }
        this.c = z;
    }

    static /* synthetic */ void a(i iVar, boolean z, NetworkInfo networkInfo) {
        if (iVar.d) {
            iVar.a.a(z);
            iVar.d = false;
        } else if (iVar.c != z) {
            iVar.a.a(z);
        }
        iVar.c = z;
    }
}
