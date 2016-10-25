package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadExceptionBanner.java
final class c implements OnClickListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        this.a.b.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (this.a.c != null) {
            a.a(this.a, this.a.c);
        }
    }
}
