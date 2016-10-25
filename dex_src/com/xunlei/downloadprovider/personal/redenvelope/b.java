package com.xunlei.downloadprovider.personal.redenvelope;

import android.os.CountDownTimer;
import android.widget.TextView;

// compiled from: RedEnvelopeHelper.java
final class b extends CountDownTimer {
    final /* synthetic */ TextView a;

    b(long j, TextView textView) {
        this.a = textView;
        super(j, 1000);
    }

    public final void onTick(long j) {
        this.a.setText(a.a.replace("%s", (j / 1000)));
    }

    public final void onFinish() {
    }
}
