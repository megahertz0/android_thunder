package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailSniffInfo;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: DownloadTaskDetailNormalInfoFragment.java
final class ak implements OnClickListener {
    final /* synthetic */ DownloadTaskDetailNormalInfoFragment a;

    ak(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment) {
        this.a = downloadTaskDetailNormalInfoFragment;
    }

    public final void onClick(View view) {
        BrowserUtil.a();
        BrowserUtil.a(this.a.getActivity(), r1.g.mRefUrl, this.a.g.mSniffKeyword, this.a.g.mWebsiteName, StartFromType.download_detail_web, SniffStartFrom.download_detail_web);
        TaskDetailSniffInfo.a("sniff_view_web", this.a.g);
    }
}
