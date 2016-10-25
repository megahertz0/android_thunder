package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerControllerView.java
final class l implements OnClickListener {
    final /* synthetic */ MediaPlayerControllerView a;

    l(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void onClick(View view) {
        if (MediaPlayerControllerView.a(this.a) != null) {
            ab a = MediaPlayerControllerView.a(this.a);
            if (a.l) {
                a.n();
                return;
            }
            boolean z;
            if (a.p()) {
                z = false;
            } else {
                z = true;
            }
            a.y = z;
            a.x = false;
            a.w = true;
            a.k();
        }
    }
}
