package com.xunlei.tdlive.base;

import android.os.Handler;
import android.os.Message;

// compiled from: BaseActivity.java
class f extends Handler {
    final /* synthetic */ BaseActivity a;

    f(BaseActivity baseActivity) {
        this.a = baseActivity;
    }

    public void handleMessage(Message message) {
        this.a.onMessage(message);
    }
}
