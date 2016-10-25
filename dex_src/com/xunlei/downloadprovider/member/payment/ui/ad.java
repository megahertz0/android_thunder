package com.xunlei.downloadprovider.member.payment.ui;

import com.xunlei.downloadprovider.member.payment.bean.PayMealItem;
import java.util.ArrayList;

// compiled from: PayOpenFragment.java
final class ad implements Runnable {
    final /* synthetic */ ArrayList a;
    final /* synthetic */ PayMealItem b;
    final /* synthetic */ PayOpenFragment c;

    ad(PayOpenFragment payOpenFragment, ArrayList arrayList, PayMealItem payMealItem) {
        this.c = payOpenFragment;
        this.a = arrayList;
        this.b = payMealItem;
    }

    public final void run() {
        this.c.r.setItemChecked(this.a.indexOf(this.b), true);
        this.c.a(this.b);
    }
}
