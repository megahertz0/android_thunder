package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: DownloadCenterDetailFragment.java
final class i implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    i(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        int i = 1;
        DownloadCenterDetailFragment.a(this.a, true);
        String str = "dl_more_bt_delete";
        String e = n.e(DownloadCenterDetailFragment.f(this.a));
        if (!n.c(DownloadCenterDetailFragment.f(this.a))) {
            i = 0;
        }
        a.a(str, e, i);
    }
}
