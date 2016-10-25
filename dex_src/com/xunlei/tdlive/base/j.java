package com.xunlei.tdlive.base;

import android.os.Handler;
import android.os.Message;

// compiled from: BaseFragment.java
class j extends Handler {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public void handleMessage(Message message) {
        if (message.what == 6535) {
            this.a.a_(message.arg1);
        }
    }
}
