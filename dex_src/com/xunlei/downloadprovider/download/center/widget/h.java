package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadCenterSelectFileTitleView.java
final class h implements OnClickListener {
    final /* synthetic */ DownloadCenterSelectFileTitleView a;

    h(DownloadCenterSelectFileTitleView downloadCenterSelectFileTitleView) {
        this.a = downloadCenterSelectFileTitleView;
    }

    public final void onClick(View view) {
        if (DownloadCenterSelectFileTitleView.a(this.a) != null) {
            DownloadCenterSelectFileTitleView.a(this.a).a(false);
            DownloadCenterSelectFileTitleView.b(this.a).setVisibility(0);
            DownloadCenterSelectFileTitleView.c(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }
}
