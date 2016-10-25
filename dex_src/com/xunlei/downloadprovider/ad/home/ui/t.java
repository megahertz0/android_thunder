package com.xunlei.downloadprovider.ad.home.ui;

import android.view.ViewTreeObserver.OnPreDrawListener;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: VideoInfoViewHolder.java
final class t implements OnPreDrawListener {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public final boolean onPreDraw() {
        if (!s.a(this.a)) {
            int lineCount = s.b(this.a).getLineCount();
            s.b(this.a).setMaxLines(SimpleLog.LOG_LEVEL_DEBUG);
            s.c(this.a);
            if (lineCount <= 2) {
                s.d(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                s.d(this.a).setVisibility(0);
            }
        }
        return true;
    }
}
