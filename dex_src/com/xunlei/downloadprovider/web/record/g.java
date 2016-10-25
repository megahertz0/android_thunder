package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FavorAndHistroyActivity.java
final class g implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    g(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        if (this.a.y != null) {
            this.a.y.dismiss();
        }
        this.a.w.clearAnimation();
        this.a.w.startAnimation(FavorAndHistroyActivity.t(this.a));
        if (this.a.f != null) {
            RecordPageView k = this.a.f;
            k.f = new aa();
            k.f.a(k.g, (int) XZBDevice.DOWNLOAD_LIST_ALL);
        }
        StatReporter.reportFavorClickSyn(16005, "favortolocal");
    }
}
