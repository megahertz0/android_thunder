package com.umeng.message;

import com.umeng.common.UmLog;
import com.umeng.message.proguard.g;
import org.json.JSONObject;

class UTrack$8 implements Runnable {
    final /* synthetic */ UTrack a;

    UTrack$8(UTrack uTrack) {
        this.a = uTrack;
    }

    public void run() {
        try {
            JSONObject b = UTrack.b(this.a);
            UmLog.i(UTrack.a(), new StringBuilder("trackRegister-->request:").append(b.toString()).toString());
            String c = UTrack.c(this.a);
            if (!g.d(c)) {
                UmLog.d(UTrack.a(), new StringBuilder("TestDevice sign =").append(c).toString());
                b.put("TD", c);
            }
            UTrack.b().trackRegister(b, true);
            UTrack.d(false);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains(MsgConstant.HTTPS_ERROR)) {
                try {
                    UTrack.b().trackRegister(null, false);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            UTrack.d(false);
        }
    }
}
