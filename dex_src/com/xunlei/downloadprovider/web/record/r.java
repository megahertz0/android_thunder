package com.xunlei.downloadprovider.web.record;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: FavorAndHistroyActivity.java
final class r implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    r(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.q.dismiss();
        this.a.q = null;
    }
}
