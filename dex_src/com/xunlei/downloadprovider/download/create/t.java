package com.xunlei.downloadprovider.download.create;

import android.os.Message;
import com.xunlei.download.TorrentParser.ParseResult;
import com.xunlei.downloadlib.parameter.TorrentFileInfo;

// compiled from: DownloadBtFileExplorerActivity.java
final class t extends Thread {
    final /* synthetic */ TorrentFileInfo[] a;
    final /* synthetic */ ParseResult b;
    final /* synthetic */ DownloadBtFileExplorerActivity c;

    t(DownloadBtFileExplorerActivity downloadBtFileExplorerActivity, TorrentFileInfo[] torrentFileInfoArr, ParseResult parseResult) {
        this.c = downloadBtFileExplorerActivity;
        this.a = torrentFileInfoArr;
        this.b = parseResult;
    }

    public final void run() {
        super.run();
        if (this.a != null && this.a.length > 0) {
            for (int i = 0; i < this.a.length; i++) {
                c access$1200 = this.c.copyFrom(this.a[i]);
                this.c.extractEpisodeInfo(access$1200);
                this.c.mSeedInfos.add(access$1200);
            }
        }
        Message obtainMessage = this.c.handler.obtainMessage();
        obtainMessage.what = 2;
        obtainMessage.obj = this.b;
        this.c.handler.sendMessage(obtainMessage);
    }
}
