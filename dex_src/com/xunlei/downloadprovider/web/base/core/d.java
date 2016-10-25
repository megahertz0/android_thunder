package com.xunlei.downloadprovider.web.base.core;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: CustomWebView.java
final class d implements OnClickListener {
    final /* synthetic */ CustomWebView a;

    d(CustomWebView customWebView) {
        this.a = customWebView;
    }

    public final void onClick(View view) {
        if (TextUtils.isEmpty(CustomWebView.c(this.a).getUrl())) {
            this.a.a(CustomWebView.d(this.a));
        } else {
            CustomWebView.c(this.a).reload();
        }
        this.a.a();
        this.a.c();
        if (CustomWebView.e(this.a) != null) {
            CustomWebView.e(this.a);
        }
    }
}
