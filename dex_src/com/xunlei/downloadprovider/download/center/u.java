package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.download.a.o;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import java.util.List;

// compiled from: DownloadCenterActivityFragment.java
final class u implements o {
    final /* synthetic */ DownloadCenterActivityFragment a;

    u(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void a(List<TaskRunningInfo> list) {
        if (DownloadCenterActivityFragment.k(this.a) != null) {
            DownloadCenterActivityFragment.k(this.a).b();
        }
        this.a.a(new v(this), 1000);
    }

    public final void b(List<TaskRunningInfo> list) {
        if (DownloadCenterActivityFragment.k(this.a) != null) {
            DownloadCenterActivityFragment.k(this.a).b();
        }
        this.a.a(new w(this), 2000);
    }

    public final void c(List<TaskRunningInfo> list) {
        if (DownloadCenterActivityFragment.k(this.a) != null) {
            DownloadCenterActivityFragment.k(this.a).b();
        }
        this.a.a(new x(this), 1000);
    }

    public final void a() {
        DownloadCenterActivityFragment.h(this.a);
    }
}
