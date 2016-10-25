package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.h;
import java.util.List;

// compiled from: DownloadCenterDetailFragment.java
final class b implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    b(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        int i;
        DownloadCenterDetailFragment downloadCenterDetailFragment = this.a;
        downloadCenterDetailFragment.e();
        downloadCenterDetailFragment.f = new h(downloadCenterDetailFragment.getActivity());
        List d = downloadCenterDetailFragment.d();
        if (d.size() == downloadCenterDetailFragment.p.c().size()) {
            i = 1;
            downloadCenterDetailFragment.f.a("\u5220\u9664\u6240\u6709\u5b50\u4efb\u52a1\u5c06\u5220\u9664BT\u4efb\u52a1\u672c\u8eab\uff0c\u662f\u5426\u5220\u9664\uff1f");
        } else {
            i = 0;
            downloadCenterDetailFragment.f.a(new StringBuilder("\u786e\u5b9a\u5220\u9664").append(d.size()).append("\u4e2aBT\u5b50\u4efb\u52a1?").toString());
        }
        downloadCenterDetailFragment.f.b(null);
        downloadCenterDetailFragment.f.e = new q(downloadCenterDetailFragment, d, i);
        downloadCenterDetailFragment.f.show();
    }
}
