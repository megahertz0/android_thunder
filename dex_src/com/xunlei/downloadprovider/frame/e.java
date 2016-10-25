package com.xunlei.downloadprovider.frame;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: BaseViewPagerFragment.java
final class e implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ BaseViewPagerFragment b;

    e(BaseViewPagerFragment baseViewPagerFragment, int i) {
        this.b = baseViewPagerFragment;
        this.a = i;
    }

    public final void onClick(View view) {
        if (this.a != BaseViewPagerFragment.g(this.b)) {
            this.b.a(this.a);
        } else if (BaseViewPagerFragment.h(this.b) != null) {
            BaseViewPagerFragment.h(this.b).a();
        }
    }
}
