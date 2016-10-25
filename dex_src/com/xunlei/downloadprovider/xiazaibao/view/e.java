package com.xunlei.downloadprovider.xiazaibao.view;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: XZBDetailDeleteDialog.java
final class e implements OnClickListener {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.g != null) {
            this.a.g.onClick(view);
        }
        this.a.dismiss();
    }
}
