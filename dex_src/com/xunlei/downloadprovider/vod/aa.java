package com.xunlei.downloadprovider.vod;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: VodPlayerActivity.java
final class aa implements OnClickListener {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        VodPlayerActivity.access$7300(this.a.a);
    }
}
