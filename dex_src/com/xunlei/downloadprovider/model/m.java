package com.xunlei.downloadprovider.model;

import android.os.Handler;
import android.os.Message;
import com.android.volley.r.b;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.model.j.a;
import org.json.JSONObject;

// compiled from: SignInUtil.java
public final class m implements b<JSONObject> {
    final /* synthetic */ Handler a;

    public m(Handler handler) {
        this.a = handler;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (this.a != null) {
            Message message = new Message();
            message.setTarget(this.a);
            a aVar = new a();
            message.obj = aVar;
            message.what = 10003;
            try {
                if (jSONObject.has(SocializeProtocolConstants.PROTOCOL_KEY_DATA)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA);
                    if (jSONObject2.has(c.f)) {
                        aVar.b = jSONObject2.getString(c.f);
                    }
                    if (jSONObject2.has("score")) {
                        aVar.c = Integer.valueOf(jSONObject2.getString("score")).intValue();
                    }
                    if (jSONObject.has("errinfo")) {
                        aVar.e = jSONObject.getString("errinfo");
                    }
                    if (jSONObject.has("errcode")) {
                        aVar.a = Integer.valueOf(jSONObject.getInt("errcode")).intValue();
                    }
                    if (jSONObject.has("datatype")) {
                        aVar.d = jSONObject.getString("datatype");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            message.sendToTarget();
        }
    }
}
