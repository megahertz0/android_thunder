package com.xunlei.downloadprovider.download.tasklist.list.g;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$h;

// compiled from: VipRenewRemindViewHolder.java
final class d implements OnClickListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        ThunderReporter$h.a("dl_center_top");
        a.a(this.a);
        a.b(this.a);
        a.a(a.c(this.a), PayFrom.DOWNLOAD_TASK_RENEWTIP, a.d(this.a));
    }
}
