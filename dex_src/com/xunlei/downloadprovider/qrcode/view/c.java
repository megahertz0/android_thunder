package com.xunlei.downloadprovider.qrcode.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

// compiled from: ScancodeResultTextView.java
public final class c {
    public View a;
    public TextView b;
    public TextView c;
    private Activity d;

    public c(Activity activity, View view) {
        this.a = view;
        this.d = activity;
    }

    public final void a(int i) {
        this.a.setVisibility(i);
    }
}
