package com.taobao.accs.client;

import com.taobao.accs.antibrush.b;
import com.taobao.accs.utl.UtilityImpl;

// compiled from: Taobao
class c implements Runnable {
    final /* synthetic */ GlobalClientInfo a;

    c(GlobalClientInfo globalClientInfo) {
        this.a = globalClientInfo;
    }

    public void run() {
        b.a(GlobalClientInfo.e);
        GlobalClientInfo.a = UtilityImpl.restoreCookie(GlobalClientInfo.e);
    }
}
