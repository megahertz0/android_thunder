package com.xunlei.downloadprovider.download.taskDetail;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;

// compiled from: DownloadCenterDetailFragment.java
final class q implements OnClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ DownloadCenterDetailFragment c;

    q(DownloadCenterDetailFragment downloadCenterDetailFragment, List list, int i) {
        this.c = downloadCenterDetailFragment;
        this.a = list;
        this.b = i;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        DownloadCenterDetailFragment.s(this.c);
        if (DownloadCenterDetailFragment.t(this.c) != null) {
            DownloadCenterDetailFragment.t(this.c).dismiss();
            DownloadCenterDetailFragment.u(this.c);
        }
        DownloadCenterDetailFragment.a(this.c, BuildConfig.VERSION_NAME, new StringBuilder("\u6b63\u5728\u5220\u9664\u4efb\u52a1  0/").append(this.a.size()).toString(), this.a);
        DownloadCenterDetailFragment.a(this.c, this.b, this.a.size(), this.a);
    }
}
