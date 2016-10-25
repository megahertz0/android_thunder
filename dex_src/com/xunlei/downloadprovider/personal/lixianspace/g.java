package com.xunlei.downloadprovider.personal.lixianspace;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: LixianSpaceFragment.java
final class g implements OnDismissListener {
    final /* synthetic */ LixianSpaceFragment a;

    g(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (LixianSpaceFragment.B(this.a).getVisibility() == 0 || LixianSpaceFragment.C(this.a).getVisibility() == 0) {
            LixianSpaceFragment.D(this.a);
            LixianSpaceFragment.f(this.a);
        }
    }
}
