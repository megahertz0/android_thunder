package com.xunlei.downloadprovider.member.register.view;

import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;

// compiled from: VerifyCodeDialog.java
final class f extends Handler {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 100000) {
            e.a(this.a).requestFocus();
            ((InputMethodManager) e.b(this.a).getSystemService("input_method")).showSoftInput(e.a(this.a), 0);
        }
    }
}
