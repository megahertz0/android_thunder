package com.xunlei.downloadprovider.search.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.xunlei.downloadprovider.R;

// compiled from: SearchBannerLayout.java
final class d implements OnClickListener {
    final /* synthetic */ ImageView a;
    final /* synthetic */ SearchBannerLayout b;

    d(SearchBannerLayout searchBannerLayout, ImageView imageView) {
        this.b = searchBannerLayout;
        this.a = imageView;
    }

    public final void onClick(View view) {
        if (this.b.a != null) {
            this.a.startAnimation(AnimationUtils.loadAnimation(this.b.getContext(), R.anim.search_switch_rotate_anim));
            this.b.a.b();
        }
    }
}
