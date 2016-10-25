package com.xunlei.downloadprovider.member.payment.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.payment.b;

// compiled from: BasePayPageFragment.java
final class t implements OnClickListener {
    final /* synthetic */ BasePayPageFragment a;

    t(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        ((PayActivity) this.a.getActivity()).finish();
        b.a(true);
    }
}
