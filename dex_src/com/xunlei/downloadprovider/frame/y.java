package com.xunlei.downloadprovider.frame;

import android.os.Build.VERSION;
import android.view.View;
import com.xunlei.downloadprovider.web.core.JsInterface;

// compiled from: MainTabActivity.java
final class y implements Runnable {
    final /* synthetic */ MainTabActivity a;

    y(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    public final void run() {
        if (VERSION.SDK_INT < 16) {
            this.a.getWindow().clearFlags(JsInterface.MSG_JS_COLLECT_WEBSITE);
            return;
        }
        View decorView = this.a.getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & -1285);
    }
}
