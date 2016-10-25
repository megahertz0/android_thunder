package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.download.util.n;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadFreeTrialBanner.java
final class f implements OnClickListener {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public final void onClick(View view) {
        if (!g.a().a(this.a.c.getTaskId())) {
            g.a().a(this.a.c.getTaskId(), g.a, true);
        } else if (this.a.c.mVipChannelStatus == 16) {
            g.a().a(this.a.c.getTaskId(), g.c, true);
        } else {
            if (n.a(this.a.c, n.g(this.a.c))) {
                g.a().a(this.a.c.getTaskId(), g.c, true);
            } else {
                g.a().a(this.a.c.getTaskId(), g.b, true);
            }
        }
        this.a.a(SpdyProtocol.PUBKEY_SEQ_ADASH);
        d.g(this.a);
        if (this.a.d != null) {
            this.a.d.onClick(view);
        }
    }
}
