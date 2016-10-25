package com.xunlei.downloadprovider.download.center;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.a.d;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.report.a;
import com.xunlei.xllib.a.b;

// compiled from: DownloadCenterActivityFragment.java
final class q implements OnClickListener {
    final /* synthetic */ DownloadCenterActivityFragment a;

    q(DownloadCenterActivityFragment downloadCenterActivityFragment) {
        this.a = downloadCenterActivityFragment;
    }

    public final void onClick(View view) {
        a.f("top_act_start");
        com.xunlei.downloadprovider.download.a.a aVar = this.a.d;
        if (!b.a(aVar.a)) {
            XLToast.a(aVar.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, aVar.a.getString(R.string.net_disable));
        } else if (b.h(aVar.a)) {
            n.a();
            n.a(false);
        } else {
            aVar.a(new d(aVar));
        }
        DownloadCenterActivityFragment.i(this.a).dismiss();
    }
}
