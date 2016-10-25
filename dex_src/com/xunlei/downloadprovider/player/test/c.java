package com.xunlei.downloadprovider.player.test;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.q;

// compiled from: PlayerAdapter.java
final class c implements OnClickListener {
    final /* synthetic */ MediaTestItemView a;
    final /* synthetic */ b b;

    c(b bVar, MediaTestItemView mediaTestItemView) {
        this.b = bVar;
        this.a = mediaTestItemView;
    }

    public final void onClick(View view) {
        ab a = q.a().a(this.b.b, this.a, this.b.c);
        if (a != null) {
            this.b.c = a.a;
        }
    }
}
