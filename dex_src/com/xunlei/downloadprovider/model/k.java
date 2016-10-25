package com.xunlei.downloadprovider.model;

import android.os.Handler;
import android.os.Message;
import com.android.volley.r.b;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import org.json.JSONObject;

// compiled from: SignInUtil.java
public final class k implements b<JSONObject> {
    final /* synthetic */ Handler a;

    public k(Handler handler) {
        this.a = handler;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (this.a != null) {
            Message message = new Message();
            message.setTarget(this.a);
            j.b bVar = new j.b();
            message.obj = bVar;
            message.what = 10000;
            try {
                if (jSONObject.has(XiaomiOAuthConstants.EXTRA_CODE_2)) {
                    bVar.b = Integer.valueOf((String) jSONObject.get(XiaomiOAuthConstants.EXTRA_CODE_2)).intValue();
                }
                if (jSONObject.has("allScores")) {
                    bVar.b = Integer.valueOf((String) jSONObject.get("allScores")).intValue();
                }
                if (jSONObject.has("todaySign")) {
                    bVar.a = jSONObject.optString("todaySign");
                }
                if (jSONObject.has("userId")) {
                    bVar.c = jSONObject.getString("userId");
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("info");
                if (jSONObject2 != null) {
                    jSONObject2 = jSONObject2.getJSONObject("sign");
                    if (jSONObject2 != null) {
                        bVar.d = jSONObject2.getString("statusCode");
                        if (jSONObject2.has(PayBaseConstants.PAY_DAY)) {
                            bVar.e = Integer.valueOf(jSONObject2.getString(PayBaseConstants.PAY_DAY)).intValue();
                        }
                        if (jSONObject2.has("counts")) {
                            bVar.f = Integer.valueOf(jSONObject2.getString("counts")).intValue();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            message.sendToTarget();
        }
    }
}
