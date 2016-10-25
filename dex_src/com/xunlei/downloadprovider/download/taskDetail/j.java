package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadCenterDetailFragment.java
final class j implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    j(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        String str = DownloadCenterDetailFragment.f(this.a).mUrl;
        a.a("dl_more_bt_add", n.e(DownloadCenterDetailFragment.f(this.a)), n.c(DownloadCenterDetailFragment.f(this.a)) ? 1 : 0);
        DownloadBtFileExplorerActivity.startSelf(this.a.getActivity(), str, SpdyProtocol.PUBKEY_PSEQ_ADASH, null);
    }
}
