package com.alipay.sdk.authjs;

import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

final class e extends TimerTask {
    final /* synthetic */ a a;
    final /* synthetic */ c b;

    e(c cVar, a aVar) {
        this.b = cVar;
        this.a = aVar;
    }

    public final void run() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("toastCallBack", "true");
        } catch (JSONException e) {
        }
        a aVar = new a(a.c);
        aVar.i = this.a.i;
        aVar.m = jSONObject;
        this.b.a.a(aVar);
    }
}
