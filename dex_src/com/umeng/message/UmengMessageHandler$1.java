package com.umeng.message;

import android.os.PowerManager;

class UmengMessageHandler$1 {
    final /* synthetic */ PowerManager a;
    final /* synthetic */ UmengMessageHandler b;

    UmengMessageHandler$1(UmengMessageHandler umengMessageHandler, PowerManager powerManager) {
        this.b = umengMessageHandler;
        this.a = powerManager;
    }

    boolean a() {
        return this.a.isScreenOn();
    }
}
