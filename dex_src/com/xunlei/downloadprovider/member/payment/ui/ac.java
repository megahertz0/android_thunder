package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.member.payment.bean.PayMealItem;

// compiled from: PayOpenFragment.java
final class ac implements OnItemClickListener {
    final /* synthetic */ PayOpenFragment a;

    ac(PayOpenFragment payOpenFragment) {
        this.a = payOpenFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a((PayMealItem) adapterView.getItemAtPosition(i));
    }
}
