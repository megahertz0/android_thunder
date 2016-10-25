package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.a;

// compiled from: ChannelFeedVideoItemView.java
final class g implements OnClickListener {
    final /* synthetic */ a a;

    g(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (!a.a(this.a).a) {
            a.b(this.a);
            if (a.c(this.a) != null) {
                a.a("play", a.c(this.a).a, a.c(this.a).o);
            }
        }
    }
}
