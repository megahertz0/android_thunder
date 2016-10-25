package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.a;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.xllib.b.d;
import java.util.List;

// compiled from: DownloadCenterActivityFragment.java
final class h implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    h(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        List b = DownloadCenterActivityFragment.c(this.a).b();
        if (!d.a(b)) {
            int size = b.size();
            a[] aVarArr = new a[size];
            for (int i = 0; i < size; i++) {
                com.xunlei.downloadprovider.download.tasklist.a.a b2 = ((e) b.get(i)).b();
                if (b2 != null) {
                    a aVar = new a();
                    aVar.a = b2.getTaskDownloadUrl();
                    aVar.b = b2.mFileName;
                    aVarArr[i] = aVar;
                }
            }
            com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a().a(this.a.getActivity(), SaveToXZBEntry.top, aVarArr);
            DownloadCenterActivityFragment.h(this.a);
            this.a.b();
        }
    }
}
