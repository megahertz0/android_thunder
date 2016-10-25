package com.xunlei.downloadprovider.search.ui.search;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.bean.d;

// compiled from: SearchMainFixFragment.java
final class l implements OnItemClickListener {
    final /* synthetic */ SearchMainFixFragment a;

    l(SearchMainFixFragment searchMainFixFragment) {
        this.a = searchMainFixFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        d dVar = (d) SearchMainFixFragment.a(this.a).getItem(i);
        if (TextUtils.isEmpty(dVar.a())) {
            SearchMainFixFragment.b(this.a).b("search_his", dVar.a);
        } else {
            SearchMainFixFragment.b(this.a).a(dVar.a + " " + dVar.a(), SniffStartFrom.search_hot_wait);
        }
        new Handler().postDelayed(new m(this), c.x);
        f.b("histroy", "word", dVar.a);
    }
}
