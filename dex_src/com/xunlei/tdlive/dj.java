package com.xunlei.tdlive;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import org.android.spdy.SpdyProtocol;

// compiled from: LoginActivity.java
class dj implements TextWatcher {
    final /* synthetic */ LoginActivity a;

    dj(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        CharSequence toString = editable.toString();
        ImageView k = this.a.n;
        int i = (!this.a.c.hasFocus() || TextUtils.isEmpty(toString)) ? SpdyProtocol.PUBKEY_SEQ_ADASH : 0;
        k.setVisibility(i);
    }
}
