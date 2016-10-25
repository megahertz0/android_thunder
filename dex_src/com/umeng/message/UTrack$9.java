package com.umeng.message;

import com.umeng.common.UmLog;
import com.umeng.message.util.HttpRequest.a;
import org.json.JSONObject;

class UTrack$9 implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ UTrack b;

    UTrack$9(UTrack uTrack, byte[] bArr) {
        this.b = uTrack;
        this.a = bArr;
    }

    public void run() {
        JSONObject jSONObject = null;
        try {
            jSONObject = UTrack.d(this.b);
            jSONObject.put("location", a.a(this.a));
            jSONObject.put("interval", PushAgent.getInstance(UTrack.a(this.b)).getLocationInterval());
            UmLog.d(UTrack.a(), jSONObject.toString());
            UTrack.b().trackLocation(jSONObject, true);
        } catch (Exception e) {
            if (e.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                UTrack.b().trackLocation(jSONObject, false);
            }
        }
    }
}
