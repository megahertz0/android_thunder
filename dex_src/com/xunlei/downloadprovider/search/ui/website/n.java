package com.xunlei.downloadprovider.search.ui.website;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: SearchHotWebsiteItemInfo.java
final class n implements OnClickListener {
    final /* synthetic */ m a;

    n(m mVar) {
        this.a = mVar;
    }

    public final void onClick(View view) {
        String str = this.a.a.c;
        BrowserUtil.a();
        BrowserUtil.b(this.a.b, str, false, StartFromType.hot_website);
        f.d("hotlink", "website", this.a.a.c);
    }
}
