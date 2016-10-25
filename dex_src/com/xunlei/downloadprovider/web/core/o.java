package com.xunlei.downloadprovider.web.core;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ThunderWebView.java
final class o implements OnClickListener {
    final /* synthetic */ ThunderWebView a;

    o(ThunderWebView thunderWebView) {
        this.a = thunderWebView;
    }

    public final void onClick(View view) {
        this.a.l.setVisibility(XZBDevice.Wait);
        this.a.a.setVisibility(0);
        this.a.b();
    }
}
