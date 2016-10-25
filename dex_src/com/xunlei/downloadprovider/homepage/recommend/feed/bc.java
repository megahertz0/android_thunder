package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;

// compiled from: FeedVideoItemView.java
final class bc implements OnCompletionListener {
    final /* synthetic */ ap a;

    bc(ap apVar) {
        this.a = apVar;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (this.a.a != null) {
            this.a.a.p = true;
            VideoFeedReporter.c(this.a.a.a);
            this.a.c();
        }
    }
}
