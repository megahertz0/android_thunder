package com.xunlei.downloadprovider.web.browser;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: BrowserActivity.java
final class h implements OnClickListener {
    final /* synthetic */ BrowserActivity a;

    h(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    public final void onClick(View view) {
        this.a.b();
        this.a.b.post(new i(this));
    }
}
