package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;

// compiled from: ChannelFeedVideoItemView.java
final class n implements OnCompletionListener {
    final /* synthetic */ a a;

    n(a aVar) {
        this.a = aVar;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (a.c(this.a) != null) {
            a.c(this.a).p = true;
            VideoFeedReporter.c(a.c(this.a).a);
            this.a.c();
        }
    }
}
