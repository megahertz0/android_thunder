package com.xunlei.downloadprovider.search.ui.website;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.web.record.FavorAndHistroyActivity;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: SearchFavorTitleInfo.java
final class d implements OnClickListener {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public final void onClick(View view) {
        this.a.b.startActivity(new Intent(this.a.b, FavorAndHistroyActivity.class));
        f.d("collect", "more", BuildConfig.VERSION_NAME);
    }
}
