package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: XZBWebviewActivity.java
final class bk implements OnClickListener {
    final /* synthetic */ XZBWebviewActivity a;

    bk(XZBWebviewActivity xZBWebviewActivity) {
        this.a = xZBWebviewActivity;
    }

    public final void onClick(View view) {
        if (!TextUtils.isEmpty(this.a.i)) {
            this.a.b.setVisibility(XZBDevice.Wait);
            XZBWebviewActivity.g(this.a);
            this.a.a(this.a.i);
        }
    }
}
