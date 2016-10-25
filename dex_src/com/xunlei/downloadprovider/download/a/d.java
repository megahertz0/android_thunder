package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;

// compiled from: DownloadCenterControl.java
public final class d implements OnClickListener {
    final /* synthetic */ a a;

    public d(a aVar) {
        this.a = aVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        BrothersApplication.a(true);
        n.a();
        n.a(true);
    }
}
