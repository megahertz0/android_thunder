package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerControllerView.java
final class j implements OnClickListener {
    final /* synthetic */ MediaPlayerControllerView a;

    j(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void onClick(View view) {
        if (MediaPlayerControllerView.b(this.a) != null) {
            MediaPlayerControllerView.b(this.a).b();
        }
    }
}
