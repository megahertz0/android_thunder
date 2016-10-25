package com.xunlei.downloadprovider.web.base;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailFragment.a;
import org.android.spdy.SpdyProtocol;

// compiled from: ShortMovieDetailFragment.java
final class bd implements OnClickListener {
    final /* synthetic */ ShortMovieDetailFragment a;

    bd(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onClick(View view) {
        ShortMovieDetailFragment.b(this.a);
        ShortMovieDetailFragment.c(this.a);
        ShortMovieDetailFragment.d(this.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        ((a) ShortMovieDetailFragment.e(this.a)).b(false);
    }
}
