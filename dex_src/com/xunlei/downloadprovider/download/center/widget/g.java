package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadCenterSelectFileTitleView.java
final class g implements OnClickListener {
    final /* synthetic */ DownloadCenterSelectFileTitleView a;

    g(DownloadCenterSelectFileTitleView downloadCenterSelectFileTitleView) {
        this.a = downloadCenterSelectFileTitleView;
    }

    public final void onClick(View view) {
        if (DownloadCenterSelectFileTitleView.a(this.a) != null) {
            DownloadCenterSelectFileTitleView.b(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            DownloadCenterSelectFileTitleView.c(this.a).setVisibility(0);
            DownloadCenterSelectFileTitleView.a(this.a).a(true);
        }
    }
}
