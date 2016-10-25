package com.handmark.pulltorefresh.library.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

@SuppressLint({"ViewConstructor"})
// compiled from: IndicatorLayout.java
public final class c extends FrameLayout implements AnimationListener {
    private Animation a;
    private Animation b;
    private ImageView c;
    private final Animation d;
    private final Animation e;

    // compiled from: IndicatorLayout.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Mode.values().length];
            try {
                a[Mode.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Mode.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public c(Context context, Mode mode) {
        int i;
        super(context);
        this.c = new ImageView(context);
        Drawable drawable = getResources().getDrawable(R.drawable.indicator_arrow);
        this.c.setImageDrawable(drawable);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.indicator_internal_padding);
        this.c.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        addView(this.c);
        switch (AnonymousClass_1.a[mode.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                i = R.anim.slide_in_from_bottom;
                dimensionPixelSize = R.anim.slide_out_to_bottom;
                setBackgroundResource(R.drawable.indicator_bg_bottom);
                this.c.setScaleType(ScaleType.MATRIX);
                Matrix matrix = new Matrix();
                matrix.setRotate(180.0f, ((float) drawable.getIntrinsicWidth()) / 2.0f, ((float) drawable.getIntrinsicHeight()) / 2.0f);
                this.c.setImageMatrix(matrix);
                break;
            default:
                i = R.anim.slide_in_from_top;
                dimensionPixelSize = R.anim.slide_out_to_top;
                setBackgroundResource(R.drawable.indicator_bg_top);
                break;
        }
        this.a = AnimationUtils.loadAnimation(context, i);
        this.a.setAnimationListener(this);
        this.b = AnimationUtils.loadAnimation(context, dimensionPixelSize);
        this.b.setAnimationListener(this);
        Interpolator linearInterpolator = new LinearInterpolator();
        this.d = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.d.setInterpolator(linearInterpolator);
        this.d.setDuration(150);
        this.d.setFillAfter(true);
        this.e = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.e.setInterpolator(linearInterpolator);
        this.e.setDuration(150);
        this.e.setFillAfter(true);
    }

    public final boolean a() {
        Animation animation = getAnimation();
        return animation != null ? this.a == animation : getVisibility() == 0;
    }

    public final void b() {
        startAnimation(this.b);
    }

    public final void c() {
        this.c.clearAnimation();
        startAnimation(this.a);
    }

    public final void onAnimationEnd(Animation animation) {
        if (animation == this.b) {
            this.c.clearAnimation();
            setVisibility(XZBDevice.Wait);
        } else if (animation == this.a) {
            setVisibility(0);
        }
        clearAnimation();
    }

    public final void onAnimationRepeat(Animation animation) {
    }

    public final void onAnimationStart(Animation animation) {
        setVisibility(0);
    }

    public final void d() {
        this.c.startAnimation(this.d);
    }

    public final void e() {
        this.c.startAnimation(this.e);
    }
}
