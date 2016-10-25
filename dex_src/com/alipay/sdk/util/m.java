package com.alipay.sdk.util;

import android.app.Activity;

final class m implements Runnable {
    final /* synthetic */ Activity a;

    m(Activity activity) {
        this.a = activity;
    }

    public final void run() {
        this.a.finish();
    }
}
