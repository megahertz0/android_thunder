package com.xunlei.downloadprovider.frame.advertisement.b;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.frame.advertisement.b.d.a;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ThunderADManager.java
final class f implements b<String> {
    final /* synthetic */ String a;
    final /* synthetic */ a b;
    final /* synthetic */ String c;
    final /* synthetic */ d d;

    f(d dVar, String str, a aVar, String str2) {
        this.d = dVar;
        this.a = str;
        this.b = aVar;
        this.c = str2;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        String str = (String) obj;
        String str2 = d.a;
        new StringBuilder("load success pageFrom: ").append(this.a).append(str);
        if (this.b == null || !this.b.c) {
            try {
                b.a(this.a).a(new JSONObject(str));
                List a = d.a(this.a, this.c);
                if (this.b != null) {
                    this.b.a(a);
                    return;
                }
                return;
            } catch (JSONException e) {
                str2 = d.a;
                if (this.b != null) {
                    this.b.a(-100, BuildConfig.VERSION_NAME);
                }
            }
        }
        str2 = d.a;
        new StringBuilder("load success pageFrom: ").append(this.a).append(str).append(" time out ");
    }
}
