package com.xunlei.downloadprovider.web.browser;

import android.text.Editable;
import android.text.TextWatcher;
import org.android.spdy.SpdyProtocol;

// compiled from: BrowserTitleBarFragment.java
final class z implements TextWatcher {
    final /* synthetic */ BrowserTitleBarFragment a;

    z(BrowserTitleBarFragment browserTitleBarFragment) {
        this.a = browserTitleBarFragment;
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String trim = charSequence.toString().trim();
        BrowserTitleBarFragment.a(this.a, trim);
        if (trim.length() <= 0) {
            BrowserTitleBarFragment.c(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else if (BrowserTitleBarFragment.b(this.a).hasFocus()) {
            BrowserTitleBarFragment.c(this.a).setVisibility(0);
        }
    }

    public final void afterTextChanged(Editable editable) {
    }
}
