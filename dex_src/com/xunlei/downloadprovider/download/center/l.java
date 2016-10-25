package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadCenterActivityFragment.java
final class l implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    l(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        List<e> b = DownloadCenterActivityFragment.c(this.a).b();
        List arrayList = new ArrayList();
        for (e eVar : b) {
            a b2 = eVar.b();
            if (b2 != null) {
                arrayList.add(b2);
            }
        }
        com.xunlei.downloadprovider.download.a.a aVar = this.a.d;
        n.a().a(arrayList);
        this.a.b();
    }
}
