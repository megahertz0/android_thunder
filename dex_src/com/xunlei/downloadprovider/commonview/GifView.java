package com.xunlei.downloadprovider.commonview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.tdlive.im.ChatMessage;

public class GifView extends View {
    private int a;
    private Movie b;
    private long c;
    private int d;
    private float e;
    private float f;
    private float g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private volatile boolean l;

    public GifView(Context context) {
        this(context, null);
    }

    public GifView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.styleable.CustomTheme_gifViewStyle);
    }

    public GifView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.k = true;
        this.l = false;
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GifView, i, 0);
        this.a = obtainStyledAttributes.getResourceId(R.styleable.GifView_gif, -1);
        this.l = obtainStyledAttributes.getBoolean(R.styleable.GifView_paused, false);
        obtainStyledAttributes.recycle();
        if (this.a != -1) {
            this.b = Movie.decodeStream(getResources().openRawResource(this.a));
        }
    }

    public void setMovieResource(int i) {
        this.a = i;
        this.b = Movie.decodeStream(getResources().openRawResource(this.a));
        requestLayout();
    }

    public void setMovie(Movie movie) {
        this.b = movie;
        requestLayout();
    }

    public Movie getMovie() {
        return this.b;
    }

    public void setMovieTime(int i) {
        this.d = i;
        invalidate();
    }

    public int getDuration() {
        return this.b != null ? this.b.duration() : 0;
    }

    public void setPaused(boolean z) {
        this.l = z;
        if (!z) {
            this.c = SystemClock.uptimeMillis() - ((long) this.d);
        }
        invalidate();
    }

    public void setByUser(boolean z) {
        this.j = z;
    }

    protected void onMeasure(int i, int i2) {
        if (this.b != null) {
            int width = this.b.width();
            int height = this.b.height();
            int size = MeasureSpec.getSize(i);
            this.g = 1.0f / (((float) width) / ((float) size));
            this.h = size;
            this.i = (int) (((float) height) * this.g);
            setMeasuredDimension(this.h, this.i);
            return;
        }
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.e = ((float) (getWidth() - this.h)) / 2.0f;
        this.f = ((float) (getHeight() - this.i)) / 2.0f;
        this.k = getVisibility() == 0;
    }

    protected void onDraw(Canvas canvas) {
        if (this.b == null) {
            return;
        }
        if (this.l) {
            a(canvas);
            return;
        }
        if (!this.j) {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (this.c == 0) {
                this.c = uptimeMillis;
            }
            int duration = this.b.duration();
            if (duration == 0) {
                duration = ChatMessage.FLAG_SYS_NOTIFY;
            }
            this.d = (int) ((uptimeMillis - this.c) % ((long) duration));
        }
        a(canvas);
        a();
    }

    @SuppressLint({"NewApi"})
    private void a() {
        if (this.k) {
            invalidate();
        }
    }

    private void a(Canvas canvas) {
        this.b.setTime(this.d);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        canvas.save(1);
        canvas.scale(this.g, this.g);
        this.b.draw(canvas, this.e / this.g, this.f / this.g);
        canvas.restore();
    }

    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        boolean z = true;
        super.onScreenStateChanged(i);
        if (i != 1) {
            z = false;
        }
        this.k = z;
        a();
    }

    @SuppressLint({"NewApi"})
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.k = i == 0;
        a();
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.k = i == 0;
        a();
    }
}
