package com.xunlei.downloadprovider.download.taskDetail;

import com.xunlei.downloadprovider.ad.recommend.a.b.a;
import com.xunlei.downloadprovider.ad.recommend.a.b.b;
import com.xunlei.downloadprovider.download.taskDetail.a.h;
import java.util.List;

// compiled from: DownloadTaskDetailNormalInfoFragment.java
final class am implements b<h> {
    final /* synthetic */ long a;
    final /* synthetic */ DownloadTaskDetailNormalInfoFragment b;

    am(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, long j) {
        this.b = downloadTaskDetailNormalInfoFragment;
        this.a = j;
    }

    public final void a(List<h> list) {
        if (DownloadTaskDetailNormalInfoFragment.a(this.b, this.a)) {
            DownloadTaskDetailNormalInfoFragment.k;
            this.b.l.addAll(list);
            if (this.b.h != null) {
                this.b.h.notifyDataSetChanged();
            }
        }
    }

    public final void a(a aVar) {
        if (DownloadTaskDetailNormalInfoFragment.a(this.b, this.a)) {
            DownloadTaskDetailNormalInfoFragment.k;
        }
    }
}
