package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;

// compiled from: FeedVideoItemView.java
final class aw implements OnClickListener {
    final /* synthetic */ ap a;

    aw(ap apVar) {
        this.a = apVar;
    }

    public final void onClick(View view) {
        ap.a(this.a, From.VIDEO_HOT_DISCUSS);
        VideoFeedReporter.a(this.a.a.a, this.a.a.r);
    }
}
