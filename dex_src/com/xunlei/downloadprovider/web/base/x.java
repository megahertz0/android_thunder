package com.xunlei.downloadprovider.web.base;

import android.support.v4.widget.AutoScrollHelper;
import com.xunlei.downloadprovider.web.base.core.ObservableWebView.a;

// compiled from: LongVideoDetailActivity.java
final class x implements a {
    final /* synthetic */ LongVideoDetailActivity a;

    x(LongVideoDetailActivity longVideoDetailActivity) {
        this.a = longVideoDetailActivity;
    }

    public final void a(int i) {
        float f = 1.0f;
        if (this.a.i || this.a.j) {
            LongVideoDetailActivity.a(this.a, 1.0f);
            if (this.a.j) {
                LongVideoDetailActivity.a(this.a, (float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
                this.a.e.setImageResource(2130839458);
                return;
            }
            return;
        }
        int e = this.a.k - this.a.d.getHeight();
        int i2 = e / 2;
        if (i < e) {
            f = i >= i2 ? 1.0f - (((float) (e - i)) / ((float) (e - i2))) : 0.0f;
        }
        LongVideoDetailActivity.a(this.a, f);
    }
}
