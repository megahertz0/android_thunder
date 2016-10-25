package com.xunlei.downloadprovider.xiazaibao.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

// compiled from: XZBDownloadBottomView.java
final class i implements AnimationListener {
    final /* synthetic */ XZBDownloadBottomView a;

    i(XZBDownloadBottomView xZBDownloadBottomView) {
        this.a = xZBDownloadBottomView;
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
    }
}
