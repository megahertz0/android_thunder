package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XZBTaskInfoDetailFragment.java
final class av implements am$b {
    final /* synthetic */ TextView a;
    final /* synthetic */ au b;

    av(au auVar, TextView textView) {
        this.b = auVar;
        this.a = textView;
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        if (XZBTaskInfoDetailFragment.d(this.b.a) != null) {
            XZBTaskInfoDetailFragment.d(this.b.a).a(SimpleLog.LOG_LEVEL_DEBUG, i == 0);
        }
        if (i == 0 && list != null && list.get(0) != null && ((DownloadTaskResult) list.get(0)).getResult() == 0) {
            this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            XZBTaskInfoDetailFragment.i(this.b.a).setVisibility(0);
            XZBTaskInfoDetailFragment.j(this.b.a).setText("\u4e0b\u8f7d\u72b6\u6001");
            XZBTaskInfoDetailFragment.k(this.b.a).setText(R.string.download_item_task_status_paused);
            XZBTaskInfoDetailFragment.l(this.b.a).setText("--");
        }
        this.a.setText(R.string.download_item_button_pause);
        this.a.setClickable(true);
    }
}
