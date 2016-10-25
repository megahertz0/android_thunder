package com.taobao.accs.internal;

import com.taobao.accs.utl.ALog;
import com.tencent.connect.dataprovider.ErrorCode;

// compiled from: Taobao
class f implements Runnable {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public void run() {
        ALog.e("ElectionServiceImpl", "serverElection time out", new Object[0]);
        this.a.a(null, (int) ErrorCode.FileIsEmpty);
    }
}
