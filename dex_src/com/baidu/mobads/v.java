package com.baidu.mobads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mobads.CpuInfoManager.UrlListener;
import com.baidu.mobads.production.c.b;

final class v implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ UrlListener d;

    v(Context context, int i, String str, UrlListener urlListener) {
        this.a = context;
        this.b = i;
        this.c = str;
        this.d = urlListener;
    }

    public final void run() {
        String a = new b(this.a.getApplicationContext(), this.b, this.c).a();
        if (this.d != null) {
            new Handler(Looper.getMainLooper()).post(new w(this, a));
        }
    }
}
