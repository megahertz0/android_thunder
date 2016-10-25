package com.umeng.message;

import com.umeng.common.UmLog;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.k$a;
import java.util.ArrayList;

class UTrack$5 implements Runnable {
    final /* synthetic */ UTrack a;

    UTrack$5(UTrack uTrack) {
        this.a = uTrack;
    }

    public void run() {
        try {
            ArrayList a = k.a(UTrack.a(this.a)).a();
            for (int i = 0; i < a.size(); i++) {
                k$a com_umeng_message_proguard_k_a = (k$a) a.get(i);
                UTrack.a(this.a, com_umeng_message_proguard_k_a.a, com_umeng_message_proguard_k_a.c, com_umeng_message_proguard_k_a.b);
            }
            UmLog.d(UTrack.a(), "sendCachedMsgLog finished, clear cacheLogSending flag");
            UTrack.a(false);
        } catch (Throwable th) {
            th.printStackTrace();
            UmLog.d(UTrack.a(), th.toString());
            UmLog.d(UTrack.a(), "sendCachedMsgLog finished, clear cacheLogSending flag");
            UTrack.a(false);
        }
    }
}
