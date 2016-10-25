package com.xunlei.downloadprovider.download.create;

import android.os.Message;
import com.xunlei.download.TorrentParser.ParseResult;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: DownloadBtFileExplorerActivity.java
final class k implements a {
    final /* synthetic */ DownloadBtFileExplorerActivity a;

    k(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity) {
        this.a = downloadBtFileExplorerActivity;
    }

    public final void a(Message message) {
        if (message.what == 1) {
            this.a.stopLoading();
            this.a.finish();
            DownloadCenterActivity.a(this.a, 0, com.umeng.a.d);
        } else if (message.what == 2) {
            this.a.mListView.setVisibility(0);
            ParseResult parseResult = (ParseResult) message.obj;
            this.a.mAdapter = new a(parseResult);
            this.a.setDataSelected();
            if (this.a.mSeedInfos.size() == this.a.mSelected.size()) {
                this.a.mSelectFileTitle.a(false);
            }
            this.a.mListView.setAdapter(this.a.mAdapter);
            this.a.mTaskId = parseResult.mTaskId <= 0 ? -1 : parseResult.mTaskId;
            this.a.updateStorageTxt();
            this.a.mProcess.setVisibility(XZBDevice.Wait);
        }
    }
}
