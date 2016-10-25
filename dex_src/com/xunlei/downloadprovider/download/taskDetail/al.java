package com.xunlei.downloadprovider.download.taskDetail;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailSniffInfo;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: DownloadTaskDetailNormalInfoFragment.java
final class al implements OnClickListener {
    final /* synthetic */ DownloadTaskDetailNormalInfoFragment a;

    al(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment) {
        this.a = downloadTaskDetailNormalInfoFragment;
    }

    public final void onClick(View view) {
        BrowserUtil.a();
        BrowserUtil.a(this.a.getActivity(), 2074, this.a.g.mSniffKeyword, true, StartFromType.download_detail_search_again, true, SniffStartFrom.download_detail_search_agin);
        TaskDetailSniffInfo.a("sniff_search", this.a.g);
    }
}
