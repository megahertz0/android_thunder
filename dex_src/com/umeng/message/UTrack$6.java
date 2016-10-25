package com.umeng.message;

import com.umeng.common.UmLog;
import com.umeng.message.proguard.k;
import com.umeng.message.proguard.k$b;
import java.util.ArrayList;

class UTrack$6 implements Runnable {
    final /* synthetic */ UTrack a;

    UTrack$6(UTrack uTrack) {
        this.a = uTrack;
    }

    public void run() {
        try {
            ArrayList c = k.a(UTrack.a(this.a)).c();
            for (int i = 0; i < c.size(); i++) {
                k$b com_umeng_message_proguard_k_b = (k$b) c.get(i);
                this.a.sendMsgLogForAgoo(com_umeng_message_proguard_k_b.a, com_umeng_message_proguard_k_b.b, com_umeng_message_proguard_k_b.c, com_umeng_message_proguard_k_b.d);
            }
            UTrack.b(false);
        } catch (Throwable th) {
            th.printStackTrace();
            UmLog.d(UTrack.a(), th.toString());
            UTrack.b(false);
        }
    }
}
