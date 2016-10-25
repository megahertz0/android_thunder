package com.handmark.pulltorefresh.library.a;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.AutoScrollHelper;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RotateLoadingLayout.java
public final class e extends d {
    private final Animation h;
    private final Matrix i;
    private float j;
    private float k;
    private final boolean l;
    private boolean m;

    public e(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        this.m = true;
        this.l = typedArray.getBoolean(R.styleable.PullToRefresh_ptrRotateDrawableWhilePulling, true);
        this.i = new Matrix();
        this.e.setVisibility(0);
        this.h = new RotateAnimation(0.0f, 720.0f, 1, 0.5f, 1, 0.5f);
        this.h.setInterpolator(a);
        this.h.setDuration(1200);
        this.h.setRepeatCount(-1);
        this.h.setRepeatMode(1);
    }

    public final void a(Drawable drawable) {
        if (drawable != null) {
            this.j = (float) Math.round(((float) drawable.getIntrinsicWidth()) / 2.0f);
            this.k = (float) Math.round(((float) drawable.getIntrinsicHeight()) / 2.0f);
        }
    }

    protected final void a(float f) {
        float f2;
        if (this.l) {
            f2 = 90.0f * f;
        } else {
            f2 = Math.max(AutoScrollHelper.RELATIVE_UNSPECIFIED, Math.min(180.0f, (360.0f * f) - 180.0f));
        }
        this.i.setRotate(f2, this.j, this.k);
    }

    protected final void a() {
        this.e.setVisibility(0);
        this.c.setVisibility(XZBDevice.Wait);
        this.d.setVisibility(XZBDevice.Wait);
        this.e.setPaused(false);
    }

    protected final void b() {
        this.e.setVisibility(XZBDevice.Wait);
        this.e.setPaused(true);
    }

    protected final int getDefaultDrawableResId() {
        return R.drawable.default_ptr_rotate;
    }

    public final void a(int i, State state, Mode mode) {
        int abs = Math.abs(i);
        int contentSize = getContentSize();
        if (this.f == Mode.PULL_FROM_START) {
            if (abs >= contentSize) {
                scrollTo(0, (abs - contentSize) / 2);
                if (this.d.getVisibility() == 0) {
                    this.e.setVisibility(0);
                    this.e.setPaused(false);
                    this.d.setVisibility(XZBDevice.Wait);
                }
            } else {
                this.e.setVisibility(XZBDevice.Wait);
                if (this.d.getVisibility() == 8 && state == State.PULL_TO_REFRESH) {
                    this.e.setVisibility(XZBDevice.Wait);
                    this.d.setVisibility(0);
                    this.e.setPaused(true);
                }
                if (getScrollY() != 0) {
                    scrollTo(0, 0);
                }
            }
            if (state == State.REFRESHING || state == State.MANUAL_REFRESHING) {
                this.e.setVisibility(0);
                this.c.setVisibility(XZBDevice.Wait);
                this.d.setVisibility(XZBDevice.Wait);
            }
        }
        if (mode == Mode.PULL_FROM_END && state == State.PULL_TO_REFRESH) {
            this.e.setVisibility(XZBDevice.Wait);
            this.d.setVisibility(0);
        }
    }
}
