package com.xunlei.downloadprovider.homepage.recommend;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

// compiled from: XLOfficialAccountRecruitHeaderView.java
final class t implements OnClickListener {
    final /* synthetic */ ListView a;
    final /* synthetic */ View b;

    t(ListView listView, View view) {
        this.a = listView;
        this.b = view;
    }

    public final void onClick(View view) {
        this.a.removeHeaderView(this.b);
        q.b();
        VideoFeedReporter.f();
    }
}
