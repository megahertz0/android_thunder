package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import org.android.spdy.SpdyAgent;

// compiled from: RenderTimeoutHandler.java
final class an extends Handler {
    private AdUnit a;

    public an(AdUnit adUnit) {
        this.a = adUnit;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.a.n().stopLoading();
                this.a.v();
            default:
                break;
        }
    }
}
