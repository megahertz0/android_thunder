package com.xunlei.downloadprovider.search.ui.widget;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.android.spdy.SpdyProtocol;

class SearchTitleBar$a implements TextWatcher {
    final /* synthetic */ SearchTitleBar a;

    SearchTitleBar$a(SearchTitleBar searchTitleBar) {
        this.a = searchTitleBar;
    }

    public final void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(this.a.getEditTextContent())) {
            SearchTitleBar.c(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            SearchTitleBar.c(this.a).setVisibility(0);
        }
        if (SearchTitleBar.d(this.a) != null) {
            SearchTitleBar.d(this.a).afterTextChanged(editable);
        }
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (SearchTitleBar.d(this.a) != null) {
            SearchTitleBar.d(this.a).beforeTextChanged(charSequence, i, i2, i3);
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (SearchTitleBar.d(this.a) != null) {
            SearchTitleBar.d(this.a).onTextChanged(charSequence, i, i2, i3);
        }
    }
}
