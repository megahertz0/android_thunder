package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerErrorView.java
final class m implements OnClickListener {
    final /* synthetic */ MediaPlayerErrorView a;

    m(MediaPlayerErrorView mediaPlayerErrorView) {
        this.a = mediaPlayerErrorView;
    }

    public final void onClick(View view) {
        if (this.a.a != null) {
            this.a.a.d();
        }
    }
}
