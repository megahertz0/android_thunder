package com.xunlei.downloadprovider.web.sniff;

import android.text.TextUtils;
import android.view.View;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.web.sniff.widget.e.a;
import com.xunlei.thundersniffer.sniff.SniffingResource;

// compiled from: SnifferResultsResourceAdapter.java
final class q implements a {
    final /* synthetic */ SniffingResource a;
    final /* synthetic */ View b;
    final /* synthetic */ m c;

    q(m mVar, SniffingResource sniffingResource, View view) {
        this.c = mVar;
        this.a = sniffingResource;
        this.b = view;
    }

    public final void a() {
        String str = "sniff_3_download_click_comfirm";
        Sniff.a(g.a("android_sniff", str, str));
        if (this.a == null || TextUtils.isEmpty(this.a.downloadUrl) || f.c(this.a.downloadUrl)) {
            if (m.a(this.c) != null) {
                m.a(this.c).a(1, this.a);
            }
            m.c(this.c).dismiss();
            return;
        }
        XLToast.b(this.c.a, XLToastType.XLTOAST_TYPE_ALARM, "\u521b\u5efa\u4efb\u52a1\u5931\u8d25");
        m.c(this.c).dismiss();
    }
}
