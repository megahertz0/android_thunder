package com.umeng.socialize.view;

import android.os.Handler;
import android.os.Message;
import org.android.spdy.SpdyProtocol;

// compiled from: OauthDialog.java
class a extends Handler {
    final /* synthetic */ OauthDialog a;

    a(OauthDialog oauthDialog) {
        this.a = oauthDialog;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1 && this.a.e != null) {
            this.a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        int i = message.what;
    }
}
