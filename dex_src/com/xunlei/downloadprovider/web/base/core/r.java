package com.xunlei.downloadprovider.web.base.core;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: WebTitleBar.java
final class r implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ WebTitleBar b;

    r(WebTitleBar webTitleBar, Context context) {
        this.b = webTitleBar;
        this.a = context;
    }

    public final void onClick(View view) {
        ((Activity) this.a).finish();
    }
}
