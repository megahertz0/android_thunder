package com.handmark.pulltorefresh.library.a;

import android.annotation.SuppressLint;
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
import org.android.spdy.SpdyAgent;

@SuppressLint({"ViewConstructor"})
// compiled from: FlipLoadingLayout.java
public final class b extends d {
    private final Animation h;
    private final Animation i;

    // compiled from: FlipLoadingLayout.java
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

    public b(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        super(context, mode, orientation, typedArray);
        int i = mode == Mode.PULL_FROM_START ? -180 : 180;
        this.h = new RotateAnimation(0.0f, (float) i, 1, 0.5f, 1, 0.5f);
        this.h.setInterpolator(a);
        this.h.setDuration(150);
        this.h.setFillAfter(true);
        this.i = new RotateAnimation((float) i, 0.0f, 1, 0.5f, 1, 0.5f);
        this.i.setInterpolator(a);
        this.i.setDuration(150);
        this.i.setFillAfter(true);
    }

    protected final void a(Drawable drawable) {
        if (drawable != null) {
            drawable.getIntrinsicHeight();
            drawable.getIntrinsicWidth();
            Matrix matrix = new Matrix();
        }
    }

    protected final void a(float f) {
    }

    protected final void a() {
    }

    protected final void b() {
    }

    protected final int getDefaultDrawableResId() {
        return R.drawable.default_ptr_flip;
    }

    private float getDrawableRotationAngle() {
        switch (AnonymousClass_1.a[this.f.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return this.g == Orientation.HORIZONTAL ? 90.0f : 180.0f;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return this.g == Orientation.HORIZONTAL ? 270.0f : AutoScrollHelper.RELATIVE_UNSPECIFIED;
            default:
                return AutoScrollHelper.RELATIVE_UNSPECIFIED;
        }
    }

    public final void a(int i, State state, Mode mode) {
    }
}
