package com.xunlei.downloadprovider.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

public class MediaPlayerLoadingView extends ImageView {
    public boolean a;
    private Animation b;

    public MediaPlayerLoadingView(Context context) {
        super(context);
        setImageResource(R.drawable.ic_loading);
    }

    public MediaPlayerLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setImageResource(R.drawable.ic_loading);
    }

    public MediaPlayerLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setImageResource(R.drawable.ic_loading);
    }

    public final void a() {
        if (this.b == null) {
            Animation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(1000);
            rotateAnimation.setRepeatCount(-1);
            this.b = rotateAnimation;
        }
        if (!this.a) {
            this.a = true;
            startAnimation(this.b);
            setVisibility(0);
        }
    }

    public final void b() {
        if (this.a) {
            this.a = false;
            clearAnimation();
            setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }
}
