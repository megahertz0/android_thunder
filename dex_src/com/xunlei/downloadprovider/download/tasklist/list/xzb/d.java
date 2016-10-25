package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter$XZBCardClickArea;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.XZBWebviewActivity;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

// compiled from: TaskXZBCardViewHolder.java
final class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onClick(View view) {
        XZBReporter.a(XZBReporter$XZBCardClickArea.bar);
        if (c.a(this.a)) {
            e.a();
            Context c = this.a.c();
            XZBShouleiUtil.getInstance().getDefaultDevice();
            e.a(c);
            return;
        }
        XZBWebviewActivity.a(this.a.c(), "v_an_shoulei_downloadcenter");
    }
}
