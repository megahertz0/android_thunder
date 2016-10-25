package com.xunlei.downloadprovider.download.center.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadCenterBottomView.java
final class e implements AnimationListener {
    final /* synthetic */ DownloadCenterBottomView a;

    e(DownloadCenterBottomView downloadCenterBottomView) {
        this.a = downloadCenterBottomView;
    }

    public final void onAnimationStart(Animation animation) {
        View findViewById = ((View) this.a.getParent()).findViewById(R.id.expand_view);
        if (findViewById != null) {
            findViewById.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationEnd(Animation animation) {
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.setAnimation(null);
        View findViewById = ((View) this.a.getParent()).findViewById(R.id.expand_view);
        if (findViewById != null) {
            findViewById.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }
}
