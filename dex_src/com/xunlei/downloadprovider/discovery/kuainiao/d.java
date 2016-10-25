package com.xunlei.downloadprovider.discovery.kuainiao;

import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.DownloadService.c;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.xllib.R;
import java.util.List;

// compiled from: KuaiNiaoFragment.java
final class d implements c {
    final /* synthetic */ List a;
    final /* synthetic */ KuaiNiaoFragment$a b;

    d(KuaiNiaoFragment$a kuaiNiaoFragment$a, List list) {
        this.b = kuaiNiaoFragment$a;
        this.a = list;
    }

    public final void a(DownloadService downloadService) {
        if (downloadService != null) {
            ((ThunderTask) this.b.a.getActivity()).createTasks(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu, this.a, KuaiNiaoFragment.b(this.b.a), 41, null);
        }
    }
}
