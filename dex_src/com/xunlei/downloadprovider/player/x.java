package com.xunlei.downloadprovider.player;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.xunlei.downloadprovider.player.u.a;

// compiled from: MediaPlayerUtils.java
final class x implements OnCancelListener {
    final /* synthetic */ a a;

    x(a aVar) {
        this.a = aVar;
    }

    public final void onCancel(DialogInterface dialogInterface) {
        dialogInterface.dismiss();
    }
}
