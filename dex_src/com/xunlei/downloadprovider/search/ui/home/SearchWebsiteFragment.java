package com.xunlei.downloadprovider.search.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.ui.website.q;
import com.xunlei.downloadprovider.search.ui.website.r;
import com.xunlei.downloadprovider.search.ui.website.s;

public class SearchWebsiteFragment extends BasePageFragment {
    private ErrorView a;
    private ListView b;
    private q c;
    private Handler d;

    public SearchWebsiteFragment() {
        this.d = new i(this);
    }

    public void onMainTabClick(boolean z) {
        if (!z && this.b != null) {
            this.b.smoothScrollToPosition(0);
        }
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.website_search_fragment, viewGroup, false);
        this.b = (ListView) inflate.findViewById(R.id.website_list);
        this.a = (ErrorView) inflate.findViewById(R.id.ev_error);
        this.a.setActionButtonListener(new j(this));
        return inflate;
    }

    public void onStart() {
        super.onStart();
        this.b.setEmptyView(null);
        if (!(this.c == null || this.c.isEmpty() || this.b == null || this.b.getAdapter() != null)) {
            this.b.setAdapter(this.c);
        }
        a();
    }

    final void a() {
        if (this.c == null) {
            this.c = new q(getActivity().getApplicationContext());
        }
        FragmentActivity activity = getActivity();
        Handler handler = this.d;
        if (r.b == null) {
            r.b = new r(activity, handler);
        }
        new s(r.b).start();
    }

    public void onPageSelected() {
        super.onPageSelected();
        f.a("website");
    }
}
