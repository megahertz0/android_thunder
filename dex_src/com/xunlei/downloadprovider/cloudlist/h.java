package com.xunlei.downloadprovider.cloudlist;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.cloudlist.a.a;

// compiled from: CloudListBTFileActivity.java
final class h implements OnClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    h(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case 2131755565:
            case 2131755570:
                if (this.a.h) {
                    CloudListBTFileActivity.b(this.b.b, this.a);
                } else {
                    CloudListBTFileActivity.c(this.b.b, this.a);
                }
            default:
                break;
        }
    }
}
