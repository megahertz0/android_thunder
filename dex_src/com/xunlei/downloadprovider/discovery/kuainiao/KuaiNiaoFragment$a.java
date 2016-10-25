package com.xunlei.downloadprovider.discovery.kuainiao;

import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.core.p;
import com.xunlei.xllib.R;
import java.util.List;

class KuaiNiaoFragment$a extends p {
    final /* synthetic */ KuaiNiaoFragment a;

    public KuaiNiaoFragment$a(KuaiNiaoFragment kuaiNiaoFragment) {
        this.a = kuaiNiaoFragment;
    }

    public final void a() {
        KuaiNiaoFragment.a(this.a);
    }

    public final void a(DownData downData) {
        g gVar = new g(41, downData.e, downData.s);
        if (DownloadService.a() == null) {
            DownloadService.a(new c(this, downData, gVar));
        } else {
            ((ThunderTask) this.a.getActivity()).createTask(downData, null, gVar, false);
        }
    }

    public final void a(List<DownData> list) {
        if (DownloadService.a() == null) {
            DownloadService.a(new d(this, list));
            return;
        }
        ((ThunderTask) this.a.getActivity()).createTasks(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu, list, KuaiNiaoFragment.b(this.a), 41, null);
    }
}
