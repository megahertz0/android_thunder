package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: KandanListActivity.java
final class q implements OnClickListener {
    final /* synthetic */ KandanListActivity a;

    q(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void onClick(View view) {
        this.a.c();
        this.a.l.setVisibility(XZBDevice.Wait);
    }
}
