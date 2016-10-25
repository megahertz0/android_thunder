package com.xunlei.downloadprovider.download.tasklist.list.g;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$h;

// compiled from: VipRenewRemindViewHolder.java
final class c implements OnClickListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        a.a(this.a);
        ThunderReporter$h.a("dl_center_top_close");
        a.b(this.a);
    }
}
