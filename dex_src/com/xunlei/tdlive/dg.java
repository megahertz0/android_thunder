package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;
import org.android.spdy.SpdyProtocol;

// compiled from: LoginActivity.java
class dg implements OnFocusChangeListener {
    final /* synthetic */ LoginActivity a;

    dg(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onFocusChange(View view, boolean z) {
        this.a.k.setSelected(z);
        ImageView h = this.a.m;
        int i = (!z || this.a.b.getText().length() <= 0) ? SpdyProtocol.PUBKEY_SEQ_ADASH : 0;
        h.setVisibility(i);
    }
}
