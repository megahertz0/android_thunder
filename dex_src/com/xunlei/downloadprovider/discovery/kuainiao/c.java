package com.xunlei.downloadprovider.discovery.kuainiao;

import android.os.Handler;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;

// compiled from: KuaiNiaoFragment.java
final class c implements com.xunlei.downloadprovider.service.DownloadService.c {
    final /* synthetic */ DownData a;
    final /* synthetic */ Handler b;
    final /* synthetic */ g c;
    final /* synthetic */ boolean d;
    final /* synthetic */ KuaiNiaoFragment$a e;

    c(KuaiNiaoFragment$a kuaiNiaoFragment$a, DownData downData, g gVar) {
        this.e = kuaiNiaoFragment$a;
        this.a = downData;
        this.b = null;
        this.c = gVar;
        this.d = false;
    }

    public final void a(DownloadService downloadService) {
        if (downloadService != null) {
            ((ThunderTask) this.e.a.getActivity()).createTask(this.a, this.b, this.c, this.d);
        }
    }
}
