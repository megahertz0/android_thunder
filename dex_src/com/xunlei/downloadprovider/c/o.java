package com.xunlei.downloadprovider.c;

import com.android.volley.r.b;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CommentManager.java
public final class o implements b<JSONObject> {
    final /* synthetic */ a a;
    final /* synthetic */ long b;
    final /* synthetic */ a c;

    public o(a aVar, a aVar2, long j) {
        this.c = aVar;
        this.a = aVar2;
        this.b = j;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        a.a;
        new StringBuilder("send zan response=>").append(jSONObject);
        int i = -1;
        try {
            i = jSONObject.getInt("result");
        } catch (JSONException e) {
            a.a;
            new StringBuilder("send comment error=>").append(e.getMessage());
            e.printStackTrace();
        }
        if (this.a == null) {
            return;
        }
        if (i == 0) {
            this.a.a(Long.valueOf(this.b));
            return;
        }
        a.b bVar = new a.b();
        bVar.a = i;
        bVar.b = "failed";
        this.a.a(bVar);
    }
}
