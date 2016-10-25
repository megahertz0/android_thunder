package com.xunlei.downloadprovider.web.base.core;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: WebTitleBar.java
final class q implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ WebTitleBar b;

    q(WebTitleBar webTitleBar, Context context) {
        this.b = webTitleBar;
        this.a = context;
    }

    public final void onClick(View view) {
        WebTitleBar.a(this.b);
        if (WebTitleBar.b(this.b).f()) {
            WebTitleBar.b(this.b).g();
            WebTitleBar.c(this.b);
            return;
        }
        ((Activity) this.a).finish();
        WebTitleBar.b(this.b).e();
    }
}
