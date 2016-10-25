package com.xunlei.downloadprovider.member.payment.a;

import android.os.Handler;
import android.os.Message;
import com.umeng.message.proguard.j;
import com.xunlei.common.encrypt.CharsetConvert;
import org.json.JSONObject;

// compiled from: ActivationBox.java
final class b implements com.android.volley.r.b<String> {
    final /* synthetic */ Handler a;
    final /* synthetic */ a b;

    b(a aVar, Handler handler) {
        this.b = aVar;
        this.a = handler;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        try {
            JSONObject jSONObject = new JSONObject(new String(((String) obj).getBytes(CharsetConvert.ISO_8859_1), CharsetConvert.UTF_8));
            int i = jSONObject.getInt("result");
            this.b.a = jSONObject.getString(j.C);
            if (i == 0) {
                Message obtainMessage = this.a.obtainMessage(905);
                obtainMessage.obj = this.b.a;
                obtainMessage.sendToTarget();
                return;
            }
            obtainMessage = this.a.obtainMessage(906);
            obtainMessage.obj = this.b.a;
            obtainMessage.sendToTarget();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
