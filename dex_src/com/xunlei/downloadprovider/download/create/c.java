package com.xunlei.downloadprovider.download.create;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: CreateUrlTask.java
final class c implements OnClickListener {
    final /* synthetic */ CreateUrlTask a;

    c(CreateUrlTask createUrlTask) {
        this.a = createUrlTask;
    }

    public final void onClick(View view) {
        if (!this.a.i) {
            CreateUrlTask.b(this.a);
        }
    }
}
