package com.xunlei.downloadprovider.cloudlist;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.xunlei.downloadprovider.cloudlist.a.a;

// compiled from: CloudListBTFileActivity.java
final class g implements OnLongClickListener {
    final /* synthetic */ a a;
    final /* synthetic */ a b;

    g(a aVar, a aVar2) {
        this.b = aVar;
        this.a = aVar2;
    }

    public final boolean onLongClick(View view) {
        switch (view.getId()) {
            case 2131755565:
                CloudListBTFileActivity.a(this.b.b, this.a);
                break;
        }
        return true;
    }
}
