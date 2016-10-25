package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;

// compiled from: FeedVideoItemView.java
final class av implements OnClickListener {
    final /* synthetic */ ap a;

    av(ap apVar) {
        this.a = apVar;
    }

    public final void onClick(View view) {
        if (!this.a.w.a) {
            ap.b(this.a);
            if (this.a.a != null) {
                VideoFeedReporter.a("play", this.a.a.a, this.a.a.o, this.a.a.b());
            }
        }
    }
}
