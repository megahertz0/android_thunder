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

// compiled from: SearchMainFragment.java
final class p implements OnItemClickListener {
    final /* synthetic */ SearchMainFragment a;

    p(SearchMainFragment searchMainFragment) {
        this.a = searchMainFragment;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        d dVar = (d) this.a.c.getItem(i);
        if (TextUtils.isEmpty(dVar.a())) {
            ((SearchActivity) this.a.getActivity()).b("search_his", dVar.a);
        } else {
            ((SearchActivity) this.a.getActivity()).a(dVar.a + " " + dVar.a(), SniffStartFrom.search_hot_wait);
        }
        new Handler().postDelayed(new q(this), c.x);
        f.b("histroy", "word", dVar.a);
    }
}
