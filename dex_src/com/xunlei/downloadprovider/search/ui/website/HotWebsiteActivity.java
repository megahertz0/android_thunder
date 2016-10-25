package com.xunlei.downloadprovider.search.ui.website;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView;
import com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.c;
import com.xunlei.downloadprovider.search.ui.hotsite.e;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.tdlive.R;

public class HotWebsiteActivity extends ThunderTask {
    private SearchTabHotSiteView a;
    private f b;
    private c c;

    public HotWebsiteActivity() {
        this.c = new a(this);
    }

    public void onResume() {
        super.onResume();
        new e(this.a).start();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968794);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        View view = (ViewGroup) findViewById(2131755844);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), g.a(this, 7.0f), view.getPaddingBottom());
        this.b = new f(view);
        this.b.f = view;
        this.b.g.setOnClickListener(new b(this));
        this.b.i.setText(2131232419);
        this.a = new SearchTabHotSiteView(this);
        this.a.setFinishInitTabViewListener(this.c);
        viewGroup.addView(this.a);
    }
}
