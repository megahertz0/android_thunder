package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.a;
import com.xunlei.downloadprovider.h.b;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: DownloadTaskMoreOperationDialog.java
final class y implements OnClickListener {
    final /* synthetic */ v a;

    y(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (!(v.c(this.a) == null || v.a(this.a) == null)) {
            a c = v.c(this.a);
            com.xunlei.downloadprovider.download.tasklist.a.a a = v.a(this.a);
            if (a != null) {
                b.a(c.a, a.mFilePath, BuildConfig.VERSION_NAME);
            }
        }
        this.a.dismiss();
    }
}
