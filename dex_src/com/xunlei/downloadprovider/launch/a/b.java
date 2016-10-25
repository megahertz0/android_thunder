package com.xunlei.downloadprovider.launch.a;

import android.view.View;
import android.view.View.OnClickListener;
import com.uc.addon.sdk.remote.Tabs;

// compiled from: RequiredPermissionDialog.java
final class b implements OnClickListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.g != null) {
            this.a.g.onClick(this.a, Tabs.TAB_CREATE_REACH_MAX_COUNT);
        } else {
            this.a.dismiss();
        }
    }
}
