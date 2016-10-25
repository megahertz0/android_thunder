package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.h;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadCenterActivityFragment.java
final class i implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    i(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        List<e> b = DownloadCenterActivityFragment.c(this.a).b();
        List arrayList = new ArrayList();
        if (b.size() != 0) {
            long j = 0;
            Object obj = null;
            for (e eVar : b) {
                if (eVar.b() != null) {
                    if (!eVar.b().c && eVar.b().mDownloadedSize != 0) {
                        j += eVar.b().mDownloadedSize;
                    } else if (eVar.b().c || j == 0) {
                        j = 1;
                    }
                    if (eVar.b().mTaskStatus == 8) {
                        arrayList.add(eVar);
                        if (obj == null) {
                            int i = 1;
                            obj = r0;
                        }
                    }
                }
                Object obj2 = obj;
                obj = obj2;
            }
            h tVar = new t(this.a.getActivity(), arrayList.size(), b.size() - arrayList.size(), j, "top");
            if (obj == null) {
                tVar.b(null);
            }
            tVar.d = new j(this, tVar);
            tVar.e = new k(this, arrayList, tVar);
            tVar.show();
        }
    }
}
