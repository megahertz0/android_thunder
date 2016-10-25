package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.entities.DownloadTaskResult;
import java.util.List;
import org.android.spdy.SpdyProtocol;

// compiled from: XZBTaskInfoDetailFragment.java
final class az implements am$c {
    final /* synthetic */ TextView a;
    final /* synthetic */ ay b;

    az(ay ayVar, TextView textView) {
        this.b = ayVar;
        this.a = textView;
    }

    public final void a(int i, List<DownloadTaskResult> list) {
        if (XZBTaskInfoDetailFragment.d(this.b.a) != null) {
            XZBTaskInfoDetailFragment.d(this.b.a).a(1, i == 0);
        }
        if (i == 0 && list != null && list.get(0) != null && ((DownloadTaskResult) list.get(0)).getResult() == 0) {
            this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            XZBTaskInfoDetailFragment.m(this.b.a).setVisibility(0);
            XZBTaskInfoDetailFragment.n(this.b.a).setVisibility(0);
            XZBTaskInfoDetailFragment.o(this.b.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            XZBTaskInfoDetailFragment.k(this.b.a).setVisibility(0);
            XZBTaskInfoDetailFragment.j(this.b.a).setText("\u4e0b\u8f7d\u901f\u5ea6");
            XZBTaskInfoDetailFragment.k(this.b.a).setText(R.string.download_item_task_status_linking);
            XZBTaskInfoDetailFragment.l(this.b.a).setText("--");
        }
        this.a.setText(R.string.download_item_button_retry);
        this.a.setClickable(true);
    }
}
