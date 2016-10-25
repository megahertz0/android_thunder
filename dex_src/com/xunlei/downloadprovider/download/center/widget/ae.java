package com.xunlei.downloadprovider.download.center.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: DownloadTitleBarView.java
final class ae implements AnimationListener {
    final /* synthetic */ int a;
    final /* synthetic */ DownloadTitleBarView b;

    ae(DownloadTitleBarView downloadTitleBarView, int i) {
        this.b = downloadTitleBarView;
        this.a = i;
    }

    public final void onAnimationStart(Animation animation) {
        DownloadTitleBarView.a(this.b, SimpleLog.LOG_LEVEL_DEBUG);
    }

    public final void onAnimationEnd(Animation animation) {
        DownloadTitleBarView.a(this.b, 0);
        DownloadTitleBarView.a(this.b).setVisibility(this.a);
    }

    public final void onAnimationRepeat(Animation animation) {
    }
}
