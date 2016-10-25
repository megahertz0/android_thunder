package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class UnifiedLoadingView extends FrameLayout {
    private int a;
    private SimpleLoadingPageView b;
    private WebpageProgressBar c;
    private e d;

    public UnifiedLoadingView(Context context) {
        this(context, null);
    }

    public UnifiedLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UnifiedLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 2;
        setOnClickListener(new h(this));
        this.b = new SimpleLoadingPageView(context);
        this.c = new WebpageProgressBar(context);
        this.d = this.b;
        addView(this.c, new LayoutParams(-1, (int) (context.getResources().getDisplayMetrics().density * 2.0f), 48));
        addView(this.b, new LayoutParams(-1, -1, 17));
        this.b.a();
        this.c.b();
        this.d = this.b;
        this.a = 2;
    }

    public void setType(int i) {
        if (this.a != i) {
            this.d.b();
            this.a = i;
            if (this.a == 1) {
                this.d = this.c;
            } else {
                this.d = this.b;
            }
            this.d.a();
        }
    }

    public final void a() {
        setVisibility(0);
        this.d.a();
    }

    public final void b() {
        this.d.b();
        setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    public void setPageLoadingViewBgColor(int i) {
        if (this.b != null) {
            this.b.setBackgroundColor(i);
        }
    }

    public final void c() {
        if (this.b != null) {
            this.b.a.setBackgroundResource(0);
        }
    }
}
