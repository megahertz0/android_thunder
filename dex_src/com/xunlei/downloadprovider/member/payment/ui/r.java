package com.xunlei.downloadprovider.member.payment.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: BasePayPageFragment.java
final class r implements OnClickListener {
    final /* synthetic */ BasePayPageFragment a;

    r(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void onClick(View view) {
        if (this.a.b()) {
            BasePayPageFragment.d(this.a);
        }
    }
}
