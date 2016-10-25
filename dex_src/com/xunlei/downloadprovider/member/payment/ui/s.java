package com.xunlei.downloadprovider.member.payment.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.member.payment.b;

// compiled from: BasePayPageFragment.java
final class s implements OnClickListener {
    final /* synthetic */ BasePayPageFragment a;

    s(BasePayPageFragment basePayPageFragment) {
        this.a = basePayPageFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        b.a(false);
    }
}
