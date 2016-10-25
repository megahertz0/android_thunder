package com.xunlei.tdlive.base;

import android.os.Handler;
import android.os.Message;

// compiled from: BaseActivity.java
class e extends Handler {
    final /* synthetic */ BaseActivity a;

    e(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void handleMessage(Message message) {
        if (message.what == 6535) {
            this.a.onTimer(message.arg1);
        }
    }
}
