package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.payment.b;

// compiled from: BasePayPageFragment.java
final class n implements OnClickListener {
    final /* synthetic */ BasePayPageFragment a;

    n(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void onClick(View view) {
        PayProblemActivity.a(this.a.getActivity(), this.a.f);
        b.a(this.a.m.g(), this.a.m.d());
    }
}
