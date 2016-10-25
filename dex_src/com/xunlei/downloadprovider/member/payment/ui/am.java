package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.payment.bean.UpgradePriceParam;
import com.xunlei.downloadprovider.member.payment.ui.widget.RangSeekBar.a;

// compiled from: PayUpgradeFragment.java
final class am implements a {
    final /* synthetic */ UpgradePriceParam a;
    final /* synthetic */ PayUpgradeFragment b;

    am(PayUpgradeFragment payUpgradeFragment, UpgradePriceParam upgradePriceParam) {
        this.b = payUpgradeFragment;
        this.a = upgradePriceParam;
    }

    public final void a() {
        PayUpgradeFragment.a(this.b, this.a, PayUpgradeFragment.a(this.b).getCurrentCoordValue());
    }
}
