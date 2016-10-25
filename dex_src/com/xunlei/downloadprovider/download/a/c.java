package com.xunlei.downloadprovider.download.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.util.List;

// compiled from: DownloadCenterControl.java
public final class c implements OnClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ a b;

    public c(a aVar, List list) {
        this.b = aVar;
        this.a = list;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        BrothersApplication.a(true);
        n.a().a(this.a, true);
    }
}
