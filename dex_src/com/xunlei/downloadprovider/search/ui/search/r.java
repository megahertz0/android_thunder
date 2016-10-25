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

// compiled from: SearchMainFragment.java
final class r implements OnItemClickListener {
    final /* synthetic */ SearchMainFragment a;

    r(SearchMainFragment searchMainFragment) {
        this.a = searchMainFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        b bVar = (b) this.a.g.getItem(i - this.a.f.getHeaderViewsCount());
        String str = bVar.a;
        if (!TextUtils.isEmpty(bVar.e)) {
            str = bVar.a + " " + bVar.e;
        }
        ((SearchActivity) this.a.getActivity()).a(str, SniffStartFrom.search_hot_wait);
        new Handler().postDelayed(new s(this), c.x);
        f.b("hotword", "word", bVar.a);
    }
}
