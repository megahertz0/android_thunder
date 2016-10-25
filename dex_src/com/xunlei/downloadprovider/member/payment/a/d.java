package com.xunlei.downloadprovider.member.payment.a;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.member.a.a;

// compiled from: ActivationBox.java
public final class d extends Thread {
    final /* synthetic */ Handler a;
    final /* synthetic */ a b;

    public d(a aVar, Handler handler) {
        this.b = aVar;
        this.a = handler;
    }

    public final void run() {
        this.b.b = true;
        a a = new com.xunlei.downloadprovider.member.a().a();
        this.b.b = false;
        Message obtainMessage = this.a.obtainMessage(904);
        obtainMessage.obj = a;
        obtainMessage.sendToTarget();
    }
}
