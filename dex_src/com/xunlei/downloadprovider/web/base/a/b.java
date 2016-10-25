package com.xunlei.downloadprovider.web.base.a;

import android.view.ViewTreeObserver.OnPreDrawListener;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: BaseInfoViewHolder.java
final class b implements OnPreDrawListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final boolean onPreDraw() {
        if (!this.a.u) {
            int lineCount = this.a.a.getLineCount();
            this.a.a.setMaxLines(SimpleLog.LOG_LEVEL_DEBUG);
            this.a.u = true;
            if (lineCount <= 2) {
                this.a.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.a.b.setVisibility(0);
            }
        }
        return true;
    }
}
