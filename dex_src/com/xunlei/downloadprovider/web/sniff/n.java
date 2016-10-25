package com.xunlei.downloadprovider.web.sniff;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.thundersniffer.sniff.SniffingResource;

// compiled from: SnifferResultsResourceAdapter.java
final class n implements OnClickListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void onClick(View view) {
        SniffingResource sniffingResource = (SniffingResource) view.getTag();
        DownloadService a = DownloadService.a();
        if (a != null) {
            long a2 = a.a(sniffingResource.downloadUrl);
            if (a2 != -1) {
                DownloadCenterActivity.a(this.a.a, a2, "sniff");
                return;
            }
        }
        this.a.a(sniffingResource, this.a.a, view);
    }
}
