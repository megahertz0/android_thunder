package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerControllerView.java
final class i implements OnClickListener {
    final /* synthetic */ MediaPlayerControllerView a;

    i(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void onClick(View view) {
        if (MediaPlayerControllerView.b(this.a) != null) {
            MediaPlayerControllerView.b(this.a).a();
        }
    }
}
