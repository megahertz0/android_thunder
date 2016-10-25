package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.h;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: DownloadCenterDetailFragment.java
final class f implements OnClickListener {
    final /* synthetic */ DownloadCenterDetailFragment a;

    f(DownloadCenterDetailFragment downloadCenterDetailFragment) {
        this.a = downloadCenterDetailFragment;
    }

    public final void onClick(View view) {
        int i = 1;
        DownloadCenterDetailFragment.n(this.a).dismiss();
        switch (view.getId()) {
            case R.id.delete_Button:
                long j;
                h tVar;
                if (DownloadCenterDetailFragment.f(this.a).c || DownloadCenterDetailFragment.f(this.a).mDownloadedSize == 0) {
                    j = 1;
                } else {
                    j = DownloadCenterDetailFragment.f(this.a).mDownloadedSize;
                }
                if (DownloadCenterDetailFragment.f(this.a).mTaskStatus == 8) {
                    tVar = new t(this.a.getContext(), 1, 0, j, "dl_detail");
                } else {
                    tVar = new t(this.a.getContext(), 0, 1, j, "dl_detail");
                }
                if (!(DownloadCenterDetailFragment.f(this.a) == null || DownloadCenterDetailFragment.f(this.a).mTaskStatus == 8)) {
                    tVar.b(null);
                }
                tVar.d = new g(this, tVar);
                tVar.e = new h(this, tVar);
                tVar.show();
                String str = "dl_more_delete";
                String e = n.e(DownloadCenterDetailFragment.f(this.a));
                if (!n.c(DownloadCenterDetailFragment.f(this.a))) {
                    i = 0;
                }
                a.a(str, e, i);
            default:
                break;
        }
    }
}
