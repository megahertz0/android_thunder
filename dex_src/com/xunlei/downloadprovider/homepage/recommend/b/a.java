package com.xunlei.downloadprovider.homepage.recommend.b;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.f;
import com.android.volley.r.b;
import org.json.JSONObject;

// compiled from: CounterDataHelper.java
public class a {
    private static a c;
    public final Context a;
    public final b b;

    private a(Context context) {
        this.b = new b();
        this.a = context;
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
        return c;
    }

    public final void a(String str, String str2, String str3, b<JSONObject> bVar, com.android.volley.r.a aVar) {
        if (com.xunlei.c.a.b.a(this.a)) {
            b bVar2 = this.b;
            Request aVar2 = new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/counter/praise", b.a(str, str2, str3), new c(bVar2, bVar), new d(bVar2, aVar));
            aVar2.setShouldCache(false);
            aVar2.setRetryPolicy(new f(5000, 1, 1.0f));
            bVar2.a(aVar2);
        }
    }

    public final void a(String str, String str2, String str3, String str4) {
        if (com.xunlei.c.a.b.a(this.a)) {
            b bVar = this.b;
            Request aVar = new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/counter/share", b.a(str, str2, str3, str4), new e(bVar), new f(bVar));
            aVar.setShouldCache(false);
            aVar.setRetryPolicy(new f(5000, 1, 1.0f));
            bVar.a(aVar);
        }
    }

    public final void a(String str, String str2, String str3, int i, String str4, int i2) {
        if (com.xunlei.c.a.b.a(this.a)) {
            b bVar = this.b;
            new StringBuilder("sendPlay length=").append(i).append(",playType=").append(str4).append(",playTime=").append(i2);
            Request aVar = new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/counter/play", b.a(str, str2, str3, i, str4, i2), new g(bVar), new h(bVar));
            aVar.setShouldCache(false);
            aVar.setRetryPolicy(new f(5000, 1, 1.0f));
            bVar.a(aVar);
        }
    }
}
