package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: ScrollableDeckPagesContainer.java
class ao extends FrameLayout implements OnPageChangeListener {
    private static final String a;
    private ViewPager b;
    private Point c;
    private Point d;
    private boolean e;
    private a f;

    // compiled from: ScrollableDeckPagesContainer.java
    static interface a {
        int a(int i);
    }

    static {
        a = ao.class.getSimpleName();
    }

    public ao(Context context) {
        super(context);
        this.c = new Point();
        this.d = new Point();
        setClipChildren(false);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
        this.b = new ViewPager(getContext());
        this.b.addOnPageChangeListener(this);
        addView(this.b);
    }

    void a() {
        this.f = null;
    }

    @TargetApi(14)
    public void a(v vVar, y yVar, int i, int i2, a aVar) {
        LayoutParams layoutParams = (LayoutParams) aj.a(vVar.a(0), (ViewGroup) this);
        if (VERSION.SDK_INT >= 17) {
            layoutParams.setMarginStart(R.styleable.Toolbar_navigationIcon);
            layoutParams.setMarginEnd(R.styleable.Toolbar_navigationIcon);
        } else {
            layoutParams.setMargins(R.styleable.Toolbar_navigationIcon, 0, R.styleable.Toolbar_navigationIcon, 0);
        }
        layoutParams.gravity = i2;
        this.b.setLayoutParams(layoutParams);
        this.b.setAdapter(yVar);
        this.b.setOffscreenPageLimit(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.b.setPageMargin(R.styleable.Toolbar_titleMarginBottom);
        this.b.setCurrentItem(i);
        this.f = aVar;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.c.x = i / 2;
        this.c.y = i2 / 2;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.d.x = (int) motionEvent.getX();
                this.d.y = (int) motionEvent.getY();
                motionEvent.offsetLocation((float) (this.c.x - this.d.x), (float) (this.c.y - this.d.y));
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                int a = a((float) this.d.x, motionEvent.getX());
                if (a != 0) {
                    motionEvent.setAction(XZBDevice.DOWNLOAD_LIST_FAILED);
                    this.b.setCurrentItem(a + this.b.getCurrentItem());
                }
                motionEvent.offsetLocation((float) (this.c.x - this.d.x), (float) (this.c.y - this.d.y));
                break;
            default:
                motionEvent.offsetLocation((float) (this.c.x - this.d.x), (float) (this.c.y - this.d.y));
                break;
        }
        return this.b.dispatchTouchEvent(motionEvent);
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.e) {
            invalidate();
        }
    }

    public void onPageSelected(int i) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Page Selected:").append(i).toString());
        LayoutParams layoutParams = (LayoutParams) this.b.getLayoutParams();
        if (this.f != null) {
            layoutParams.gravity = this.f.a(i);
            this.b.requestLayout();
        }
    }

    public void onPageScrollStateChanged(int i) {
        this.e = i != 0;
    }

    private int a(float f, float f2) {
        int currentItem = this.b.getCurrentItem();
        int count = this.b.getAdapter().getCount();
        int width = this.b.getWidth();
        int width2 = getWidth();
        if (currentItem == 0 || count - 1 == currentItem) {
            count = width2 - width;
            return currentItem == 0 ? (f <= ((float) count) || f2 <= ((float) count)) ? 0 : (int) Math.ceil((double) ((f2 - ((float) count)) / ((float) width))) : (f >= ((float) count) || f2 >= ((float) count)) ? 0 : -((int) Math.ceil((double) ((((float) count) - f2) / ((float) width))));
        } else {
            currentItem = (width2 - width) / 2;
            if (f < ((float) currentItem) && f2 < ((float) currentItem)) {
                return -((int) Math.ceil((double) ((((float) currentItem) - f2) / ((float) width))));
            }
            currentItem = (width2 + width) / 2;
            return (f <= ((float) currentItem) || f2 <= ((float) currentItem)) ? 0 : (int) Math.ceil((double) ((f2 - ((float) currentItem)) / ((float) width)));
        }
    }
}
