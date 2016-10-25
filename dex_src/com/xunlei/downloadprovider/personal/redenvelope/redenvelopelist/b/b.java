package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b;

import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b.a.a;
import java.util.List;
import org.json.JSONObject;

// compiled from: RedEnvelopesManager.java
public final class b implements com.android.volley.r.b<JSONObject> {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    public b(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject != null) {
            List a;
            a aVar = this.b;
            if (jSONObject != null) {
                aVar.c = jSONObject.optString("result");
                a = a.a(jSONObject.optJSONArray("list"));
            } else {
                a = null;
            }
            this.a.a(a);
        }
    }
}
