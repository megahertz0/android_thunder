package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerControllerView.java
final class g implements OnClickListener {
    final /* synthetic */ MediaPlayerControllerView a;

    g(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void onClick(View view) {
        if (MediaPlayerControllerView.a(this.a) != null) {
            MediaPlayerControllerView.a(this.a).n();
        }
    }
}
