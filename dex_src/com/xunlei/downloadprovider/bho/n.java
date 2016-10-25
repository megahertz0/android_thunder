package com.xunlei.downloadprovider.bho;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.bho.ThunderTaskBHOActivity.b;

// compiled from: ThunderTaskBHOActivity.java
final class n implements OnClickListener {
    final /* synthetic */ b a;

    n(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (b.a(this.a) != null) {
            b.a(this.a).onClick(this.a, -1);
        }
    }
}
