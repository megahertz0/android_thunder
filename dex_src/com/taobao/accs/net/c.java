package com.taobao.accs.net;

import com.taobao.accs.data.Message;
import com.taobao.accs.utl.ALog;
import com.tencent.connect.dataprovider.ErrorCode;

// compiled from: Taobao
class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    c(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public void run() {
        Message b = this.b.c.b(this.a);
        if (b != null) {
            this.b.c.a(b, (int) ErrorCode.FileIsEmpty);
            this.b.a(this.a, "receive data time out");
            ALog.e(this.b.e(), this.b.a + "receive data time out! ", new Object[0]);
        }
    }
}
