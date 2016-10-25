package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerControllerView.java
final class h implements OnClickListener {
    final /* synthetic */ MediaPlayerControllerView a;

    h(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void onClick(View view) {
        if (MediaPlayerControllerView.a(this.a) != null) {
            MediaPlayerState a = MediaPlayerControllerView.a(this.a).e.b.a.a();
            if (a == MediaPlayerState.STARTED) {
                MediaPlayerControllerView.a(this.a).c(true);
                a.b("videodetail", "pause");
            } else if (a == MediaPlayerState.PAUSED) {
                MediaPlayerControllerView.a(this.a).c();
                a.b("videodetail", "play");
            } else {
                MediaPlayerControllerView.a(this.a).d();
            }
            MediaPlayerControllerView.e();
        }
    }
}
