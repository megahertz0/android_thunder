package com.xunlei.downloadprovider.web.core;

import android.os.Bundle;
import android.os.Handler;
import com.xunlei.downloadprovider.util.sniff.f;

// compiled from: DefaultJsCallbackListener.java
class c$a implements Runnable {
    Object a;
    Bundle b;
    int c;
    Handler d;
    final /* synthetic */ c e;

    public c$a(c cVar, Object obj, Bundle bundle, int i, Handler handler) {
        this.e = cVar;
        this.a = obj;
        this.b = bundle;
        this.c = i;
        this.d = handler;
    }

    public final void run() {
        String string = this.b.getString("pageUrl");
        if (this.a != null) {
            String str = new String((byte[]) this.a);
            f.a();
            String a = f.a(str);
            f.a();
            f.a(string, new e(this, a, string));
            return;
        }
        f.a();
        f.a(string, new f(this, string));
    }
}
