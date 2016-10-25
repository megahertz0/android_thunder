package com.xunlei.downloadprovider.search.ui.website;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.web.record.FavorAndHistroyActivity;

// compiled from: SearchHistoryTitleInfo.java
final class k implements OnClickListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        Intent intent = new Intent(this.a.b, FavorAndHistroyActivity.class);
        intent.putExtra("locate_to_history_key", true);
        this.a.b.startActivity(intent);
    }
}
