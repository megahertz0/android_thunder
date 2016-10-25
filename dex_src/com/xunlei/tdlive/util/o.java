package com.xunlei.tdlive.util;

import android.text.Spannable;
import android.widget.TextView;

// compiled from: SpannedStringUtil.java
final class o implements Runnable {
    final /* synthetic */ TextView a;
    final /* synthetic */ Spannable b;

    o(TextView textView, Spannable spannable) {
        this.a = textView;
        this.b = spannable;
    }

    public final void run() {
        n.a(this.a, this.b, "[GIFTX_", n.a(), null);
    }
}
