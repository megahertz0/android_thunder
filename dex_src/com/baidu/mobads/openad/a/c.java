package com.baidu.mobads.openad.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.baidu.mobads.openad.d.b;

public class c extends d {
    private int a;
    private b d;
    private IntentFilter e;

    public c(Context context) {
        super(context);
    }

    public void a(BroadcastReceiver broadcastReceiver) {
        this.d = (b) broadcastReceiver;
    }

    public void a() {
        if (this.a == 0) {
            if (this.d == null) {
                a(new b(this));
            }
            this.e = new IntentFilter();
            this.e.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        this.a++;
        this.b.registerReceiver(this.d, this.e);
    }

    public void b() {
        dispatchEvent(new b("network_changed"));
    }
}
