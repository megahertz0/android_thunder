package com.xunlei.downloadprovider.personal.lixianspace;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: LixianSpaceFragment.java
final class f implements OnClickListener {
    final /* synthetic */ LixianSpaceFragment a;

    f(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        LixianSpaceFragment.z(this.a).dismiss();
        LixianSpaceFragment.A(this.a);
    }
}
