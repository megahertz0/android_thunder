package com.xunlei.downloadprovider.web.base;

import android.support.v4.widget.AutoScrollHelper;
import com.xunlei.downloadprovider.web.base.core.CustomWebView.b;

// compiled from: LongVideoDetailActivity.java
final class v implements b {
    final /* synthetic */ LongVideoDetailActivity a;

    v(LongVideoDetailActivity longVideoDetailActivity) {
        this.a = longVideoDetailActivity;
    }

    public final void a(boolean z) {
        this.a.j = z;
        if (this.a.i) {
            LongVideoDetailActivity.a(this.a, 1.0f);
            return;
        }
        LongVideoDetailActivity.a(this.a, (float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
        if (z) {
            this.a.e.setImageResource(2130839458);
        }
    }
}
