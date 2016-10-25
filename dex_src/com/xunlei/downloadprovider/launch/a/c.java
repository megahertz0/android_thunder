package com.xunlei.downloadprovider.launch.a;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: RequiredPermissionDialog.java
final class c implements OnClickListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.h != null) {
            this.a.h.onClick(this.a, -1);
        } else {
            this.a.dismiss();
        }
    }
}
