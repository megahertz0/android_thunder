package com.xunlei.downloadprovider.web.sniff.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.web.sniff.widget.SuffixListView.a;
import com.xunlei.downloadprovider.web.x;
import com.xunlei.xllib.b.d;
import java.util.Iterator;

// compiled from: SuffixListView.java
final class h implements OnItemClickListener {
    final /* synthetic */ SuffixListView a;

    h(SuffixListView suffixListView) {
        this.a = suffixListView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        x xVar = (x) adapterView.getItemAtPosition(i);
        if (!d.a(SuffixListView.h)) {
            Iterator it = SuffixListView.h.iterator();
            while (it.hasNext()) {
                ((a) it.next()).a(xVar);
            }
        }
    }
}
