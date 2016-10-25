package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.util.ac;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: WebBrowserActivity.java
class et implements OnClickListener {
    final /* synthetic */ WebBrowserActivity a;

    et(WebBrowserActivity webBrowserActivity) {
        this.a = webBrowserActivity;
    }

    public void onClick(View view) {
        if (ac.a()) {
            this.a.mErrorView.setVisibility(XZBDevice.Wait);
            this.a.mWebView.reload();
        }
    }
}
