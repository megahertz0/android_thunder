package com.xunlei.tdlive.aniengine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import org.android.spdy.TnetStatusCode;

public class AniSurfaceView extends SurfaceView {
    private a a;

    public AniSurfaceView(Context context) {
        super(context);
        this.a = new a();
        a();
    }

    public AniSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new a();
        a();
    }

    public AniSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new a();
        a();
    }

    public AniSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new a();
        a();
    }

    public a getAniEngine() {
        return this.a;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.a((SurfaceView) this);
        this.a.a(new x(getContext()));
        this.a.c();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        post(new b(this));
    }

    private void a() {
        if (!isInEditMode()) {
            setZOrderOnTop(true);
        }
        getHolder().setFormat(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
    }
}
