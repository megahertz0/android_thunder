package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: DownloadBriefInfoHeaderView.java
final class b implements OnClickListener {
    final /* synthetic */ DownloadBriefInfoHeaderView a;

    b(DownloadBriefInfoHeaderView downloadBriefInfoHeaderView) {
        this.a = downloadBriefInfoHeaderView;
    }

    public final void onClick(View view) {
        if (this.a.y != null) {
            this.a.y.b();
        }
    }
}
