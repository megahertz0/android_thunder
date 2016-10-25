package com.xunlei.downloadprovider.i.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$UpgradeAlert;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From;

// compiled from: Update.java
final class r implements OnClickListener {
    final /* synthetic */ c a;

    r(c cVar) {
        this.a = cVar;
    }

    public final void onClick(View view) {
        c.a(this.a, c.a(this.a, c.a(this.a).a));
        if (c.g(this.a)) {
            c.t(this.a);
        } else {
            c.q(this.a);
            this.a.a(c.a(this.a).f);
        }
        ((MainTabActivity) c.e(this.a)).b();
        ThunderReporter$UpgradeAlert.b(From.REC_BAR);
    }
}
