package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;

// compiled from: RemoteDownloadTaskViewHolder.java
final class ah implements am$c {
    final /* synthetic */ ag a;

    ah(ag agVar) {
        this.a = agVar;
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        if (i != 0 || list == null || list.isEmpty() || ((DownloadTaskResult) list.get(0)).getResult() != 0) {
            ac.g;
            ac.a(this.a.a, 1, false);
            return;
        }
        ac.g;
        this.a.a.t.setText(R.string.download_item_button_pause);
        this.a.a.t.setEnabled(true);
        ac.a(this.a.a, 1, true);
    }
}
