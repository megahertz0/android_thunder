package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: RemoteDownloadTaskViewHolder.java
final class ak implements OnClickListener {
    final /* synthetic */ ac a;

    ak(ac acVar) {
        this.a = acVar;
    }

    public final void onClick(View view) {
        if (this.a.c) {
            boolean z;
            ac acVar = this.a;
            if (this.a.a.a) {
                z = false;
            } else {
                z = true;
            }
            if (acVar.a.a != z) {
                acVar.a.a = z;
                acVar.d.c();
            }
            this.a.b(this.a.a.a);
            return;
        }
        this.a.d.g.a(this.a.a);
    }
}
