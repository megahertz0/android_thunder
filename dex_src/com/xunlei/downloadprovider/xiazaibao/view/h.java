package com.xunlei.downloadprovider.xiazaibao.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.xunlei.downloadprovider.R;

// compiled from: XZBDownloadBottomView.java
final class h implements AnimationListener {
    final /* synthetic */ XZBDownloadBottomView a;

    h(XZBDownloadBottomView xZBDownloadBottomView) {
        this.a = xZBDownloadBottomView;
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
