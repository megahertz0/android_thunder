package com.xunlei.tdlive.view;

import android.database.DataSetObserver;
import com.xunlei.tdlive.a.r;

// compiled from: LiveListBannerView.java
class l extends DataSetObserver {
    final /* synthetic */ LiveListBannerView a;

    l(LiveListBannerView liveListBannerView) {
        this.a = liveListBannerView;
    }

    public void onChanged() {
        try {
            ((r) this.a.c.getAdapter()).a(this.a.d.getAdapter().getCount());
        } catch (Throwable th) {
        }
    }

    public void onInvalidated() {
        onChanged();
    }
}
