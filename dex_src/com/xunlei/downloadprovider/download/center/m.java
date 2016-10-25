package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.a.c;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.xllib.a.b;
import java.util.ArrayList;
import java.util.List;

// compiled from: DownloadCenterActivityFragment.java
final class m implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    m(DownloadCenterActivityFragment downloadCenterActivityFragment) {
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
        if (!b.a(aVar.a)) {
            XLToast.a(aVar.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, aVar.a.getString(R.string.net_disable));
        } else if (b.h(aVar.a)) {
            n.a().a(arrayList, false);
        } else {
            aVar.a(new c(aVar, arrayList));
        }
        this.a.b();
    }
}
