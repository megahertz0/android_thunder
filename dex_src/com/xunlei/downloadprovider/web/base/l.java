package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: KandanListActivity.java
final class l implements OnClickListener {
    final /* synthetic */ KandanListActivity a;

    l(KandanListActivity kandanListActivity) {
        this.a = kandanListActivity;
    }

    public final void onClick(View view) {
        if (this.a.y) {
            this.a.r = !this.a.r;
            KandanListActivity.i(this.a);
        }
    }
}
