package com.taobao.accs.net;

import com.taobao.accs.utl.ALog;
import com.tencent.connect.common.Constants;
import com.umeng.a;

// compiled from: Taobao
class d implements Runnable {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (this.a.c.d()) {
            ALog.e(this.a.e(), this.a.a + "receive ping time out! ", new Object[0]);
            e.a(this.a.b).c();
            this.a.a(a.d, "receive ping timeout");
            this.a.c.a((int) Constants.ERROR_NO_SDCARD);
        }
    }
}
