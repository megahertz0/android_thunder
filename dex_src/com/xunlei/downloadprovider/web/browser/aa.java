package com.xunlei.downloadprovider.web.browser;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

// compiled from: BrowserTitleBarFragment.java
final class aa implements OnFocusChangeListener {
    final /* synthetic */ BrowserTitleBarFragment a;

    aa(BrowserTitleBarFragment browserTitleBarFragment) {
        this.a = browserTitleBarFragment;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            if (BrowserTitleBarFragment.b(this.a).getText() == null || !(BrowserTitleBarFragment.d(this.a) == null || BrowserTitleBarFragment.d(this.a).equals(BrowserTitleBarFragment.b(this.a).getText().toString()))) {
                BrowserTitleBarFragment.b(this.a).setText(BrowserTitleBarFragment.d(this.a) == null ? BuildConfig.VERSION_NAME : BrowserTitleBarFragment.d(this.a));
            }
            if (BrowserTitleBarFragment.b(this.a).getText() != null && !TextUtils.isEmpty(BrowserTitleBarFragment.b(this.a).getText().toString())) {
                BrowserTitleBarFragment.b(this.a).selectAll();
                BrowserTitleBarFragment.b(this.a).scrollTo(0, 0);
                BrowserTitleBarFragment.c(this.a).setVisibility(0);
                return;
            }
            return;
        }
        BrowserTitleBarFragment.c(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.a();
        this.a.d();
    }
}
