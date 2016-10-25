package com.xunlei.downloadprovider.search.ui.search;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.bean.b;

// compiled from: SearchMainFixFragment.java
final class n implements OnItemClickListener {
    final /* synthetic */ SearchMainFixFragment a;

    n(SearchMainFixFragment searchMainFixFragment) {
        this.a = searchMainFixFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        b bVar = (b) SearchMainFixFragment.e(this.a).getItem(i - SearchMainFixFragment.d(this.a).getHeaderViewsCount());
        String str = bVar.a;
        if (!TextUtils.isEmpty(bVar.e)) {
            str = bVar.a + " " + bVar.e;
        }
        SearchMainFixFragment.b(this.a).a(str, SniffStartFrom.search_hot_wait);
        new Handler().postDelayed(new o(this), c.x);
        f.b("hotword", "word", bVar.a);
    }
}
