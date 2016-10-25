package com.xunlei.downloadprovider.commonview.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import com.uc.addon.sdk.remote.Tabs;

// compiled from: XLAlertDialog.java
final class e implements OnClickListener {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public final void onClick(View view) {
        if (this.a.g != null) {
            this.a.g.onClick(this.a, Tabs.TAB_CREATE_REACH_MAX_COUNT);
        } else {
            this.a.dismiss();
        }
    }
}
