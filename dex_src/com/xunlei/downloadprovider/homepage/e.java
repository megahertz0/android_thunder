package com.xunlei.downloadprovider.homepage;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;

// compiled from: HomeFragment.java
final class e implements OnClickListener {
    final /* synthetic */ HomeFragment a;

    e(HomeFragment homeFragment) {
        this.a = homeFragment;
    }

    public final void onClick(View view) {
        SearchActivity.a(this.a.getActivity());
        this.a.getActivity().overridePendingTransition(R.anim.search_activity_in, R.anim.search_activity_out);
        f.a("home", "search_prepare", "bar");
    }
}
