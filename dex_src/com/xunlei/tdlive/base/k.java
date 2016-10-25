package com.xunlei.tdlive.base;

import android.os.Handler;
import android.os.Message;

// compiled from: BaseFragment.java
class k extends Handler {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public void handleMessage(Message message) {
        this.a.onMessage(message);
    }
}
