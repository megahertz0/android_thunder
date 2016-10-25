package com.xiaomi.mipush.sdk;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.ab;

class k extends ContentObserver {
    final /* synthetic */ j a;

    k(j jVar, Handler handler) {
        this.a = jVar;
        super(handler);
    }

    public void onChange(boolean z) {
        j.a(this.a, Integer.valueOf(ab.a(j.a(this.a)).b()));
        if (j.b(this.a).intValue() != 0) {
            j.a(this.a).getContentResolver().unregisterContentObserver(this);
            if (d.d(j.a(this.a))) {
                this.a.c();
            }
        }
    }
}
