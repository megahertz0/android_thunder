package com.xunlei.downloadprovider.search.ui.website;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: SearchFavorWebsite.java
final class f implements OnClickListener {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public final void onClick(View view) {
        StartFromType startFromType = StartFromType.favorite;
        BrowserUtil.a();
        BrowserUtil.a(this.a.b, this.a.a.c, false, startFromType);
        com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f.d("collect", "website", this.a.a.c);
    }
}
