package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$h;

// compiled from: ChoicenessVipRenewalRemindItemView.java
final class j implements OnClickListener {
    final /* synthetic */ ChoicenessVipRenewalRemindItemView a;

    j(ChoicenessVipRenewalRemindItemView choicenessVipRenewalRemindItemView) {
        this.a = choicenessVipRenewalRemindItemView;
    }

    public final void onClick(View view) {
        ThunderReporter$h.a("home_collect", "bar");
        a.a(this.a.c, null, this.a.g);
    }
}
