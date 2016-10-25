package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class WebpageProgressBar extends View implements e {
    private Paint a;
    private Handler b;
    private int c;
    private int d;
    private int e;
    private Animation f;
    private Runnable g;

    public WebpageProgressBar(Context context) {
        super(context);
        this.a = new Paint();
        this.b = new Handler(Looper.getMainLooper());
        this.d = 10000;
        this.e = a.a;
        this.g = new j(this);
    }

    public WebpageProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Paint();
        this.b = new Handler(Looper.getMainLooper());
        this.d = 10000;
        this.e = a.a;
        this.g = new j(this);
    }

    public WebpageProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        this.b = new Handler(Looper.getMainLooper());
        this.d = 10000;
        this.e = a.a;
        this.g = new j(this);
    }

    public final void a() {
        this.b.removeCallbacks(this.g);
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
        this.e = a.b;
        setVisibility(0);
        this.b.post(this.g);
    }

    public final void b() {
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
        this.e = a.c;
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400);
        alphaAnimation.setAnimationListener(new i(this));
        this.f = alphaAnimation;
        startAnimation(alphaAnimation);
    }

    public int getProgress() {
        return this.c;
    }

    public int getMaxProgress() {
        return this.d;
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > this.d) {
            i = this.d;
        }
        this.c = i;
        invalidate();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int left = getLeft();
        int top = getTop();
        int bottom = getBottom();
        Canvas canvas2 = canvas;
        canvas2.drawRect((float) left, (float) top, (float) (left + ((this.c * getWidth()) / this.d)), (float) bottom, this.a);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a.setColor(Color.argb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, R.styleable.AppCompatTheme_alertDialogStyle, 152, 254));
    }

    static /* synthetic */ void b(WebpageProgressBar webpageProgressBar) {
        if (webpageProgressBar.f != null) {
            webpageProgressBar.f.cancel();
            webpageProgressBar.f = null;
        }
        webpageProgressBar.b.removeCallbacks(webpageProgressBar.g);
        webpageProgressBar.e = a.a;
        webpageProgressBar.c = 0;
        webpageProgressBar.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }
}
