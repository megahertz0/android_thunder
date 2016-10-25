package com.xunlei.downloadprovider.player;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.player.u.a;

// compiled from: MediaPlayerUtils.java
final class v implements OnClickListener {
    final /* synthetic */ a a;

    v(a aVar) {
        this.a = aVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a();
    }
}
