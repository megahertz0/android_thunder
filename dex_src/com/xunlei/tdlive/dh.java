package com.xunlei.tdlive;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import org.android.spdy.SpdyProtocol;

// compiled from: LoginActivity.java
class dh implements TextWatcher {
    final /* synthetic */ LoginActivity a;

    dh(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        CharSequence toString = editable.toString();
        ImageView h = this.a.m;
        int i = (!this.a.b.hasFocus() || TextUtils.isEmpty(toString)) ? SpdyProtocol.PUBKEY_SEQ_ADASH : 0;
        h.setVisibility(i);
    }
}
