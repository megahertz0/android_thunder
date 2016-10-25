package com.handmark.pulltorefresh.library.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.a;
import com.xunlei.downloadprovider.commonview.GifView;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

@SuppressLint({"ViewConstructor"})
// compiled from: LoadingLayout.java
public abstract class d extends FrameLayout implements a {
    static final Interpolator a;
    protected View b;
    protected final GifView c;
    protected final ImageView d;
    protected final GifView e;
    protected final Mode f;
    protected final Orientation g;
    private boolean h;
    private final TextView i;
    private CharSequence j;
    private CharSequence k;
    private CharSequence l;

    // compiled from: LoadingLayout.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            b = new int[Mode.values().length];
            try {
                b[Mode.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[Mode.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            a = new int[Orientation.values().length];
            try {
                a[Orientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Orientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    protected abstract void a();

    protected abstract void a(float f);

    public abstract void a(int i, State state, Mode mode);

    protected abstract void a(Drawable drawable);

    protected abstract void b();

    protected abstract int getDefaultDrawableResId();

    static {
        a = new LinearInterpolator();
    }

    public d(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
        Drawable drawable;
        ColorStateList colorStateList;
        super(context);
        this.f = mode;
        this.g = orientation;
        switch (AnonymousClass_1.a[orientation.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_horizontal, this);
                break;
            default:
                LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical, this);
                break;
        }
        this.b = findViewById(R.id.fl_inner);
        this.i = (TextView) this.b.findViewById(R.id.pull_to_refresh_text);
        this.c = (GifView) this.b.findViewById(R.id.pull_to_refresh_pulling);
        this.c.setByUser(true);
        this.c.setPaused(false);
        this.d = (ImageView) this.b.findViewById(R.id.pull_to_refresh_pulling_static);
        this.e = (GifView) this.b.findViewById(R.id.pull_to_refresh_refreshing);
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        switch (AnonymousClass_1.b[mode.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                layoutParams.gravity = orientation == Orientation.VERTICAL ? com.xunlei.tdlive.R.styleable.AppCompatTheme_homeAsUpIndicator : XZBDevice.DOWNLOAD_LIST_FAILED;
                this.j = "\u4e0a\u62c9\u52a0\u8f7d";
                this.k = com.alipay.sdk.widget.a.a;
                this.l = "\u677e\u5f00\u7acb\u5373\u52a0\u8f7d";
                this.b.setPadding(this.b.getPaddingLeft(), this.b.getPaddingBottom(), this.b.getPaddingRight(), this.b.getPaddingTop());
                break;
            default:
                layoutParams.gravity = orientation == Orientation.VERTICAL ? com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                setClipChildren(false);
                this.j = "\u4e0b\u62c9\u5237\u65b0";
                this.k = "\u6b63\u5728\u5237\u65b0...";
                this.l = "\u677e\u5f00\u7acb\u5373\u5237\u65b0";
                break;
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderBackground)) {
            drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrHeaderBackground);
            if (drawable != null) {
                if (VERSION.SDK_INT >= 16) {
                    setBackground(drawable);
                } else {
                    setBackgroundDrawable(drawable);
                }
            }
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance, typedValue);
            setTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance)) {
            typedValue = new TypedValue();
            typedArray.getValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance, typedValue);
            setSubTextAppearance(typedValue.data);
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderTextColor)) {
            colorStateList = typedArray.getColorStateList(R.styleable.PullToRefresh_ptrHeaderTextColor);
            if (colorStateList != null) {
                setTextColor(colorStateList);
            }
        }
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrHeaderSubTextColor)) {
            colorStateList = typedArray.getColorStateList(R.styleable.PullToRefresh_ptrHeaderSubTextColor);
            if (colorStateList != null) {
                setSubTextColor(colorStateList);
            }
        }
        drawable = null;
        if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawable)) {
            drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawable);
        }
        switch (AnonymousClass_1.b[mode.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableEnd)) {
                    drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableEnd);
                } else if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableBottom)) {
                    drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableBottom);
                }
                break;
            default:
                if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableStart)) {
                    drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableStart);
                } else if (typedArray.hasValue(R.styleable.PullToRefresh_ptrDrawableTop)) {
                    drawable = typedArray.getDrawable(R.styleable.PullToRefresh_ptrDrawableTop);
                }
                break;
        }
        if (drawable == null) {
            drawable = context.getResources().getDrawable(getDefaultDrawableResId());
        }
        setLoadingDrawable(drawable);
        f();
    }

    public final void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }

    public final void setWidth(int i) {
        getLayoutParams().width = i;
        requestLayout();
    }

    public final void setPaddingTop(int i) {
        this.b.setPadding(this.b.getPaddingLeft(), i, this.b.getPaddingRight(), this.b.getPaddingBottom());
    }

    public final int getContentSize() {
        switch (AnonymousClass_1.a[this.g.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return this.b.getWidth();
            default:
                return this.b.getHeight();
        }
    }

    public final int getInnerTop() {
        return this.b.getTop();
    }

    public static void c() {
    }

    public final void b(float f) {
        if (!this.h) {
            a(f);
        }
    }

    public final void d() {
        if (this.i != null) {
            this.i.setText(this.j);
        }
    }

    public final void e() {
        if (this.i != null) {
            this.i.setText(this.k);
        }
        if (!this.h) {
            a();
        }
    }

    public final void f() {
        if (!this.h) {
            b();
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        setSubHeaderText(charSequence);
    }

    public final void setLoadingDrawable(Drawable drawable) {
        this.h = drawable instanceof AnimationDrawable;
        a(drawable);
    }

    public void setPullLabel(CharSequence charSequence) {
        this.j = charSequence;
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        this.k = charSequence;
    }

    public void setReleaseLabel(CharSequence charSequence) {
        this.l = charSequence;
    }

    public void setTextTypeface(Typeface typeface) {
    }

    public static void g() {
    }

    private void setSubHeaderText(CharSequence charSequence) {
    }

    private void setSubTextAppearance(int i) {
    }

    private void setSubTextColor(ColorStateList colorStateList) {
    }

    private void setTextAppearance(int i) {
    }

    private void setTextColor(ColorStateList colorStateList) {
    }
}
