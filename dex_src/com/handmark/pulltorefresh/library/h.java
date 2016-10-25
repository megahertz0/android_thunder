package com.handmark.pulltorefresh.library;

import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.d;

// compiled from: PullToRefreshWebView.java
final class h implements d<WebView> {
    h() {
    }

    public final void onRefresh(PullToRefreshBase<WebView> pullToRefreshBase) {
        ((WebView) pullToRefreshBase.getRefreshableView()).reload();
    }
}
