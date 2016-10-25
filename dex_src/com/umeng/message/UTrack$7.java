package com.umeng.message;

import com.umeng.common.UmLog;
import org.json.JSONObject;

class UTrack$7 implements Runnable {
    final /* synthetic */ UTrack a;

    UTrack$7(UTrack uTrack) {
        this.a = uTrack;
    }

    public void run() {
        JSONObject jSONObject = null;
        try {
            jSONObject = UTrack.b(this.a);
            UTrack.b().trackAppLaunch(jSONObject, true);
            UTrack.c(false);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                try {
                    UTrack.b().trackAppLaunch(jSONObject, false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            UmLog.d(UTrack.a(), e.toString());
            UTrack.c(false);
        }
    }
}
