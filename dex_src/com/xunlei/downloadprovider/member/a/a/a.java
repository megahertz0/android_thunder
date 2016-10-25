package com.xunlei.downloadprovider.member.a.a;

import com.android.volley.r.b;
import com.android.volley.w;
import com.xunlei.xiazaibao.BuildConfig;
import org.json.JSONException;

// compiled from: HighSpeedClient.java
public final class a implements com.android.volley.r.a, b<String> {
    b a;

    public final /* synthetic */ void onResponse(Object obj) {
        try {
            c.a(BuildConfig.VERSION_NAME, (String) obj);
            c.a();
            if (this.a != null) {
                this.a.b();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            a();
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    public final void onErrorResponse(w wVar) {
        wVar.getMessage();
        a();
    }

    private void a() {
        if (this.a != null) {
            b bVar = this.a;
            if (bVar.a != null) {
                bVar.a.b();
            }
        }
    }
}
