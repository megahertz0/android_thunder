package com.xunlei.downloadprovider.download.center;

import com.xunlei.downloadprovider.discovery.kuainiao.KuaiNiaoActivity;
import com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.a;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;

// compiled from: DownloadCenterActivityFragment.java
final class ad implements a {
    final /* synthetic */ DownloadCenterActivityFragment a;

    ad(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void a() {
        com.xunlei.downloadprovider.download.report.a.f("download_task");
        com.xunlei.downloadprovider.download.a.a.a(this.a.getActivity(), PayFrom.DOWNLOAD_TASK_NEW, "v_an_shoulei_hytq_kt_ds");
    }

    public final void b() {
        com.xunlei.downloadprovider.download.report.a.f("top_kuainiao");
        this.a.startActivity(KuaiNiaoActivity.a(this.a.getActivity(), false));
    }
}
