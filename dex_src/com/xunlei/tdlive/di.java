package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;
import org.android.spdy.SpdyProtocol;

// compiled from: LoginActivity.java
class di implements OnFocusChangeListener {
    final /* synthetic */ LoginActivity a;

    di(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public void onFocusChange(View view, boolean z) {
        this.a.l.setSelected(z);
        ImageView k = this.a.n;
        int i = (!z || this.a.c.getText().length() <= 0) ? SpdyProtocol.PUBKEY_SEQ_ADASH : 0;
        k.setVisibility(i);
    }
}
