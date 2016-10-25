package com.xunlei.downloadprovider.ad.home.ui;

import com.qq.e.ads.nativ.MediaListener;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: ADGDTVideoItem.java
final class d implements MediaListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void onVideoReady(long j) {
        this.a.c;
    }

    public final void onVideoStart() {
        this.a.c;
    }

    public final void onVideoPause() {
        this.a.c;
    }

    public final void onVideoComplete() {
        this.a.c;
    }

    public final void onVideoError(int i) {
        this.a.c;
    }

    public final void onReplayButtonClicked() {
        this.a.c;
    }

    public final void onADButtonClicked() {
        this.a.c;
        if (this.a.i != null) {
            this.a.b("gdt_button");
            this.a.i.a(SimpleLog.LOG_LEVEL_DEBUG);
        }
    }
}
