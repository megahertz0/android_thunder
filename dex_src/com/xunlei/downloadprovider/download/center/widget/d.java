package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.xunlei.downloadprovider.R;

// compiled from: DownloadCenterBottomView.java
final class d implements AnimationListener {
    final /* synthetic */ DownloadCenterBottomView a;

    d(DownloadCenterBottomView downloadCenterBottomView) {
        this.a = downloadCenterBottomView;
    }

    public final void onAnimationStart(Animation animation) {
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        View findViewById = ((View) this.a.getParent()).findViewById(R.id.expand_view);
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        this.a.setAnimation(null);
    }
}
