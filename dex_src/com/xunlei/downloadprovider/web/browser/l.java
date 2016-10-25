package com.xunlei.downloadprovider.web.browser;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

// compiled from: BrowserActivity.java
final class l implements OnClickListener {
    final /* synthetic */ BrowserActivity a;

    l(BrowserActivity browserActivity) {
        this.a = browserActivity;
    }

    public final void onClick(View view) {
        this.a.a();
        if (this.a.h != null) {
            String str = BrowserActivity.a;
            if (TextUtils.isEmpty(this.a.h.g())) {
                this.a.f();
                return;
            }
            if (this.a.t != null) {
                this.a.t.a(this.a.h.g());
            }
            this.a.l.d(true);
            this.a.v.c();
            this.a.h.b();
            this.a.h.j();
        }
    }
}
