package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.member.b.b;
import com.xunlei.downloadprovider.member.payment.external.i;
import java.util.Observable;

// compiled from: ChoicenessVipRenewalRemindItemView.java
final class l extends i {
    final /* synthetic */ ChoicenessVipRenewalRemindItemView a;

    l(ChoicenessVipRenewalRemindItemView choicenessVipRenewalRemindItemView) {
        this.a = choicenessVipRenewalRemindItemView;
    }

    public final void update(Observable observable, Object obj) {
        if (((Boolean) obj).booleanValue()) {
            this.a.a();
            if (this.a.i == null) {
                this.a.i = b.a(null);
            }
            this.a.i.a();
        }
    }
}
