package com.xunlei.tdlive.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.PagerIndicator;
import com.xunlei.tdlive.control.RecycleableViewPager;
import com.xunlei.tdlive.util.r;

public class LiveListBannerView extends RelativeLayout implements OnPageChangeListener, Runnable {
    private r a;
    private AdapterView<?> b;
    private PagerIndicator c;
    private RecycleableViewPager d;
    private OnItemClickListener e;
    private GestureDetector f;
    private DataSetObserver g;

    private class a extends SimpleOnGestureListener {
        private a() {
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (LiveListBannerView.this.e != null) {
                if (LiveListBannerView.this.b == null) {
                    LiveListBannerView.this.b = new m(this, LiveListBannerView.this.getContext());
                    LiveListBannerView.this.b.setId(LiveListBannerView.this.getId());
                }
                LiveListBannerView.this.e.onItemClick(LiveListBannerView.this.b, LiveListBannerView.this, LiveListBannerView.this.d.getCurrentItem(), (long) LiveListBannerView.this.getId());
            }
            return true;
        }
    }

    public LiveListBannerView(Context context) {
        super(context);
        this.g = new l(this);
    }

    public LiveListBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new l(this);
    }

    public LiveListBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new l(this);
    }

    @TargetApi(21)
    public LiveListBannerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = new l(this);
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        try {
            this.d.getAdapter().unregisterDataSetObserver(this.g);
        } catch (Throwable th) {
        }
        this.d.setAdapter(pagerAdapter);
        this.d.getAdapter().registerDataSetObserver(this.g);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (this.f == null) {
            this.f = new GestureDetector(getContext(), new a());
        }
        this.e = onItemClickListener;
    }

    public void autoStep(int i) {
        if (this.a == null) {
            this.a = new r(i, this);
        }
        this.a.a(i);
        this.a.d();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = (RecycleableViewPager) findViewById(R.id.banner);
        this.d.setOnPageChangeListener(this);
        this.c = (PagerIndicator) findViewById(R.id.banner_indicator);
        this.c.setSingleVisible(false);
        this.c.setAdapter(new com.xunlei.tdlive.a.r());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        try {
            this.d.getAdapter().unregisterDataSetObserver(this.g);
        } catch (Throwable th) {
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (f == 0.0f && this.a != null) {
            this.a.d();
        }
    }

    public void onPageSelected(int i) {
        this.c.select(i % this.c.getAdapter().getCount());
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void run() {
        if (this.d != null) {
            this.d.step(true);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f != null) {
            this.f.onTouchEvent(motionEvent);
        }
        return false;
    }
}
