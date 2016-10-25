package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnLongClickListener;

// compiled from: RemoteDownloadTaskViewHolder.java
final class af implements OnLongClickListener {
    final /* synthetic */ ac a;

    af(ac acVar) {
        this.a = acVar;
    }

    public final boolean onLongClick(View view) {
        if (this.a.c) {
            return false;
        }
        ac.c(this.a);
        return true;
    }
}
