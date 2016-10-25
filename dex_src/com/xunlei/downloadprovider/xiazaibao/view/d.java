package com.xunlei.downloadprovider.xiazaibao.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XZBDetailDeleteDialog.java
final class d implements OnClickListener {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.f != null) {
            this.a.f.onClick(view);
        }
        this.a.dismiss();
    }
}
