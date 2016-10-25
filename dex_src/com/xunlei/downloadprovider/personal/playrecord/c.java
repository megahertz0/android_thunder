package com.xunlei.downloadprovider.personal.playrecord;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.personal.playrecord.widget.PlayRecordListWidget;
import com.xunlei.downloadprovider.personal.playrecord.widget.i;
import java.util.HashSet;

// compiled from: PlayRecordFragment.java
final class c implements OnClickListener {
    final /* synthetic */ PlayRecordFragment a;

    c(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        PlayRecordListWidget d = PlayRecordFragment.d(this.a);
        d.h = true;
        if (d.i == null || d.i.size() <= 0) {
            d.i = new HashSet();
        } else {
            d.i.clear();
        }
        d.i.addAll(d.d);
        if (d.d == null || d.d.size() <= 0) {
            d.d();
        } else {
            new i(d).start();
        }
        PlayRecordFragment.r(this.a).dismiss();
        PlayRecordFragment.s(this.a);
    }
}
