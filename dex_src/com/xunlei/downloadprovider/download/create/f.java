package com.xunlei.downloadprovider.download.create;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

// compiled from: CreateUrlTask.java
final class f implements OnKeyListener {
    final /* synthetic */ CreateUrlTask a;

    f(CreateUrlTask createUrlTask) {
        this.a = createUrlTask;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == 66 && keyEvent.getAction() == 1 && this.a.e.isEnabled()) {
            CreateUrlTask.b(this.a);
        }
        return false;
    }
}
