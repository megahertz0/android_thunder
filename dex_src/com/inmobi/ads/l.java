package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import org.android.spdy.SpdyAgent;

// compiled from: BannerRefreshHandler.java
final class l extends Handler {
    private InMobiBanner a;

    public l(InMobiBanner inMobiBanner) {
        this.a = inMobiBanner;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.a.load(true);
            default:
                break;
        }
    }
}
