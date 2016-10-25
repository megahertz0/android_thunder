package com.xunlei.downloadprovider.web.base.a;

import android.view.ViewTreeObserver.OnPreDrawListener;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CommentItemViewHolder.java
final class k implements OnPreDrawListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final boolean onPreDraw() {
        if (!j.a(this.a)) {
            int lineCount = j.b(this.a).getLineCount();
            j.b(this.a).setMaxLines(SimpleLog.LOG_LEVEL_DEBUG);
            j.c(this.a);
            if (lineCount <= 2) {
                j.d(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                j.d(this.a).setVisibility(0);
            }
        }
        return true;
    }
}
