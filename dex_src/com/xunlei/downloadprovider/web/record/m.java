package com.xunlei.downloadprovider.web.record;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.a.a;

// compiled from: FavorAndHistroyActivity.java
final class m implements OnClickListener {
    final /* synthetic */ FavorAndHistroyActivity a;

    m(FavorAndHistroyActivity favorAndHistroyActivity) {
        this.a = favorAndHistroyActivity;
    }

    public final void onClick(View view) {
        this.a.finish();
        a.b(this.a);
    }
}
