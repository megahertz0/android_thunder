package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$h;

// compiled from: ChoicenessVipRenewalRemindItemView.java
final class m implements OnClickListener {
    final /* synthetic */ ChoicenessVipRenewalRemindItemView a;

    m(ChoicenessVipRenewalRemindItemView choicenessVipRenewalRemindItemView) {
        this.a = choicenessVipRenewalRemindItemView;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_btn_renew:
                ThunderReporter$h.a("home_collect", "bar");
                a.a(this.a.c, null, this.a.g);
            case R.id.rl_btn_delete:
                ThunderReporter$h.a("home_collect", "close");
                ChoicenessVipRenewalRemindItemView.d(this.a);
                this.a.a();
            default:
                break;
        }
    }
}
