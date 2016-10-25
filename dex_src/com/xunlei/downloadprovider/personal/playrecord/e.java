package com.xunlei.downloadprovider.personal.playrecord;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: PlayRecordFragment.java
final class e implements OnDismissListener {
    final /* synthetic */ PlayRecordFragment a;

    e(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        if (PlayRecordFragment.t(this.a).getVisibility() == 0 || PlayRecordFragment.u(this.a).getVisibility() == 0) {
            PlayRecordFragment.v(this.a);
            PlayRecordFragment.w(this.a);
        }
    }
}
