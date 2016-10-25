package com.xunlei.downloadprovider.web.record;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

// compiled from: FavorAndHistroyActivity.java
final class s implements OnDismissListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    s(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.q = null;
    }
}
