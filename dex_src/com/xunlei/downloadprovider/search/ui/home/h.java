package com.xunlei.downloadprovider.search.ui.home;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;

// compiled from: SearchHomeFragment.java
final class h implements OnClickListener {
    final /* synthetic */ SearchHomeFragment a;

    h(SearchHomeFragment searchHomeFragment) {
        this.a = searchHomeFragment;
    }

    public final void onClick(View view) {
        SearchActivity.a(this.a.getActivity());
        this.a.getActivity().overridePendingTransition(R.anim.search_activity_in, R.anim.search_activity_out);
        f.a("search", "search_prepare", "bar");
    }
}
