package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: RemoteDownloadTaskViewHolder.java
final class aj implements am$b {
    final /* synthetic */ ai a;

    aj(ai aiVar) {
        this.a = aiVar;
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        if (i != 0 || list == null || list.isEmpty() || ((DownloadTaskResult) list.get(0)).getResult() != 0) {
            ac.g;
            this.a.a.t.setText(R.string.download_item_button_pause);
            ac.a(this.a.a, SimpleLog.LOG_LEVEL_DEBUG, false);
            return;
        }
        ac.g;
        this.a.a.o.setText(R.string.download_item_task_status_paused);
        this.a.a.t.setText(R.string.download_item_button_start);
        this.a.a.t.setEnabled(true);
        ac.a(this.a.a, SimpleLog.LOG_LEVEL_DEBUG, true);
    }
}
