package com.xunlei.downloadprovider.search.ui.hotsite;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;

// compiled from: SearchTabHotSiteView.java
final class f implements OnItemClickListener {
    final /* synthetic */ SearchTabHotSiteView a;

    f(SearchTabHotSiteView searchTabHotSiteView) {
        this.a = searchTabHotSiteView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = SearchTabHotSiteView.h.a(i).d;
        BrowserUtil.a();
        BrowserUtil.b(this.a.a, str, true, StartFromType.hot_website);
        StatReporter.reportHotsiteItem("item");
    }
}
