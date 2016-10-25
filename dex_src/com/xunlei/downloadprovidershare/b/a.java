package com.xunlei.downloadprovidershare.b;

import com.android.volley.r.b;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ShortUrlHelp.java
public class a {
    private static a a;
    private a b;
    private String c;
    private com.android.volley.r.a d;
    private b<JSONObject> e;

    // compiled from: ShortUrlHelp.java
    public static interface a {
        void a(String str);
    }

    private a() {
        this.d = new b(this);
        this.e = new c(this);
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

    public final void a(String str, a aVar) {
        this.b = aVar;
        this.c = str;
        com.xunlei.downloadprovider.j.a.a().d().a(new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/dlj_create", b(), this.e, this.d));
    }

    private JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SHubBatchQueryKeys.url, this.c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
