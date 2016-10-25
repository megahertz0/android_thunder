package com.xunlei.downloadprovider.download.center.widget;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

// compiled from: DownloadMenuPopWindow.java
final class r implements OnKeyListener {
    final /* synthetic */ q a;

    r(q qVar) {
        this.a = qVar;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        if (!this.a.isShowing()) {
            return true;
        }
        this.a.dismiss();
        return true;
    }
}
