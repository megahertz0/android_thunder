package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import java.util.concurrent.atomic.AtomicBoolean;

public class PullToRefreshWebView2 extends PullToRefreshWebView {
    private final AtomicBoolean a;
    private final AtomicBoolean h;

    public PullToRefreshWebView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new AtomicBoolean(false);
        this.h = new AtomicBoolean(false);
    }

    protected final WebView b(Context context, AttributeSet attributeSet) {
        return super.b(context, attributeSet);
    }

    protected final boolean f() {
        ((WebView) getRefreshableView()).loadUrl("javascript:isReadyForPullDown();");
        return this.a.get();
    }

    protected final boolean g() {
        ((WebView) getRefreshableView()).loadUrl("javascript:isReadyForPullUp();");
        return this.h.get();
    }

    protected final /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        return super.b(context, attributeSet);
    }
}
