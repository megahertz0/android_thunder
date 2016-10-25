package com.taobao.accs.internal;

import android.content.Intent;
import android.os.Process;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.a;

// compiled from: Taobao
class h implements Runnable {
    final /* synthetic */ AnonymousClass_2 a;

    h(AnonymousClass_2 anonymousClass_2) {
        this.a = anonymousClass_2;
    }

    public void run() {
        try {
            if (this.a.this$0.b == null || !UtilityImpl.getServiceEnabled(this.a.this$0.b)) {
                Process.killProcess(Process.myPid());
            } else {
                Intent intent = new Intent();
                intent.setAction(Constants.ACTION_PING);
                intent.setClassName(this.a.this$0.b.getPackageName(), a.channelService);
                this.a.this$0.b.startService(intent);
                UTMini.getInstance().commitEvent(UT.EVENT_ID, "probeServiceEnabled", UtilityImpl.getDeviceId(this.a.this$0.b));
                ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao........mContext.startService(intent) [probe][successfully]", new Object[0]);
            }
            ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao........messageServiceBinder [probe][end]", new Object[0]);
        } catch (Throwable th) {
            ALog.d("ServiceImpl", new StringBuilder("ReceiverImpl probeTaoBao error........e=").append(th).toString(), new Object[0]);
        }
    }
}
