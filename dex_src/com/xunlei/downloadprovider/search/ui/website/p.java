package com.xunlei.downloadprovider.search.ui.website;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: SearchHotWebsiteTitleInfo.java
final class p implements OnClickListener {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        this.a.b.startActivity(new Intent(this.a.b, HotWebsiteActivity.class));
        f.d("hotlink", "more", BuildConfig.VERSION_NAME);
    }
}
