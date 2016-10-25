package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.bean.UpgradePriceParam;
import com.xunlei.downloadprovider.member.payment.external.d;
import com.xunlei.downloadprovider.search.b.b;
import java.util.ArrayList;

// compiled from: PayActivity.java
final class z implements b<ArrayList<PayConfigurationParam>> {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ PayActivity c;

    z(PayActivity payActivity, int i, String str) {
        this.c = payActivity;
        this.a = i;
        this.b = str;
    }

    public final /* synthetic */ void a(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        if (this.a == 1) {
            UpgradePriceParam parseFrom = UpgradePriceParam.parseFrom(this.b);
            if (parseFrom != null) {
                int tdays = parseFrom.getTdays();
                if (tdays > 0) {
                    this.c.q = d.a(arrayList, tdays);
                } else {
                    this.c.q = d.a(arrayList, -1);
                }
            } else {
                this.c.q = d.a(arrayList, -1);
            }
            this.c.k();
        }
    }
}
