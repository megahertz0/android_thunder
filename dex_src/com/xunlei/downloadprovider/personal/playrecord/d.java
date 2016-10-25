package com.xunlei.downloadprovider.personal.playrecord;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: PlayRecordFragment.java
final class d implements OnClickListener {
    final /* synthetic */ PlayRecordFragment a;

    d(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        PlayRecordFragment.r(this.a).dismiss();
        PlayRecordFragment.s(this.a);
    }
}
