package com.xunlei.downloadprovider.search.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: SearchTitleBar.java
final class f implements OnClickListener {
    final /* synthetic */ SearchTitleBar a;

    f(SearchTitleBar searchTitleBar) {
        this.a = searchTitleBar;
    }

    public final void onClick(View view) {
        if (SearchTitleBar.a(this.a) != null) {
            SearchTitleBar.a(this.a).onClick(view);
        }
        SearchTitleBar.b(this.a).setText(BuildConfig.VERSION_NAME);
    }
}
