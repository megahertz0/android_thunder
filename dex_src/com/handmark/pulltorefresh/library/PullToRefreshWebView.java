package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.d;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class PullToRefreshWebView extends PullToRefreshBase<WebView> {
    private static final d<WebView> a;
    private final WebChromeClient h;

    @TargetApi(9)
    final class a extends WebView {
        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected final boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            c.a(PullToRefreshWebView.this, i, i3, i2, i4, (int) Math.max(0.0d, Math.floor((double) (((WebView) PullToRefreshWebView.this.d).getScale() * ((float) ((WebView) PullToRefreshWebView.this.d).getContentHeight()))) - ((double) ((getHeight() - getPaddingBottom()) - getPaddingTop()))), XZBDevice.DOWNLOAD_LIST_RECYCLE, 1.5f, z);
            return overScrollBy;
        }
    }

    public /* synthetic */ View a(Context context, AttributeSet attributeSet) {
        return b(context, attributeSet);
    }

    static {
        a = new h();
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new i(this);
        setOnRefreshListener(a);
        ((WebView) this.d).setWebChromeClient(this.h);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    public WebView b(Context context, AttributeSet attributeSet) {
        WebView aVar;
        if (VERSION.SDK_INT >= 9) {
            aVar = new a(context, attributeSet);
        } else {
            aVar = new WebView(context, attributeSet);
        }
        aVar.setId(R.id.webview);
        return aVar;
    }

    public boolean f() {
        return ((WebView) this.d).getScrollY() == 0;
    }

    public boolean g() {
        return ((float) ((WebView) this.d).getScrollY()) >= ((float) Math.floor((double) (((WebView) this.d).getScale() * ((float) ((WebView) this.d).getContentHeight())))) - ((float) ((WebView) this.d).getHeight());
    }

    protected final void a(Bundle bundle) {
        super.a(bundle);
        ((WebView) this.d).restoreState(bundle);
    }

    protected final void b(Bundle bundle) {
        super.b(bundle);
        ((WebView) this.d).saveState(bundle);
    }
}
