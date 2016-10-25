package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import org.android.spdy.SpdyProtocol;

// compiled from: FeedVideoItemView.java
final class ba implements OnClickListener {
    final /* synthetic */ ap a;

    ba(ap apVar) {
        this.a = apVar;
    }

    public final void onClick(View view) {
        this.a.a.p = false;
        this.a.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        ap.b(this.a);
        if (this.a.a != null) {
            VideoFeedReporter.a("replay", this.a.a.a, this.a.a.o, this.a.a.b());
        }
    }
}
