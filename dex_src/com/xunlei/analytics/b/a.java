package com.xunlei.analytics.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.xunlei.analytics.c.d;
import com.xunlei.analytics.c.f;
import com.xunlei.analytics.c.g;
import com.xunlei.analytics.dbstore.b;
import java.util.HashMap;

public class a {
    private static a a = null;
    private static final int b = 10000;
    private static final String c = "MSG_EVENT_INTERID";
    private static final String d = "MSG_EVENT_EVENTID";
    private HandlerThread e;
    private Callback f;
    private Handler g;

    private a() {
        this.f = new b(this);
        this.e = new HandlerThread("InsertThread");
        this.e.start();
        this.g = new Handler(this.e.getLooper(), this.f);
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    private void a(String str) {
        if (f.a()) {
            f.a(new StringBuilder("generate data =").append(str).toString());
        }
    }

    private void b(String str, String str2, HashMap<String, String> hashMap) {
        String b = d.b(com.xunlei.analytics.config.a.d(), str, str2, hashMap);
        a(b);
        b.a().a(com.xunlei.analytics.config.a.a(), str, b);
    }

    private void c() {
        if (d()) {
            b(c.f, c.f, null);
            g.e(com.xunlei.analytics.config.a.d());
        }
    }

    private boolean d() {
        return !d.a(System.currentTimeMillis(), g.f(com.xunlei.analytics.config.a.d()));
    }

    public void a(String str, String str2, HashMap<String, String> hashMap) {
        Message message = new Message();
        message.what = 10000;
        if (hashMap != null && hashMap.size() > 0) {
            message.obj = hashMap;
        }
        Bundle bundle = new Bundle();
        bundle.putString(c, str);
        bundle.putString(d, str2);
        message.setData(bundle);
        this.g.sendMessage(message);
    }

    public void b() {
        a(c.f, c.f, null);
    }
}
