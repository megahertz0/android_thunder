package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;

// compiled from: ChannelFeedVideoItemView.java
final class h implements OnClickListener {
    final /* synthetic */ a a;

    h(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        a.a(this.a, From.VIDEO_HOT_DISCUSS);
        VideoFeedReporter.a(a.c(this.a).a, a.c(this.a).r);
    }
}
