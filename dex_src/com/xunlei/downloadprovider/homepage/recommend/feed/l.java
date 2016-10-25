package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.recommend.a;
import org.android.spdy.SpdyProtocol;

// compiled from: ChannelFeedVideoItemView.java
final class l implements OnClickListener {
    final /* synthetic */ a a;

    l(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        a.c(this.a).p = false;
        a.g(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a.b(this.a);
        if (a.c(this.a) != null) {
            a.a("replay", a.c(this.a).a, a.c(this.a).o);
        }
    }
}
