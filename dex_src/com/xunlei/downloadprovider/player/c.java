package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerCompletionView.java
final class c implements OnClickListener {
    final /* synthetic */ MediaPlayerCompletionView a;

    c(MediaPlayerCompletionView mediaPlayerCompletionView) {
        this.a = mediaPlayerCompletionView;
    }

    public final void onClick(View view) {
        if (MediaPlayerCompletionView.a(this.a) != null) {
            MediaPlayerCompletionView.a(this.a).onClick(view);
        } else if (MediaPlayerCompletionView.b(this.a) != null) {
            MediaPlayerCompletionView.b(this.a).d();
            if (MediaPlayerCompletionView.c(this.a) != null) {
                MediaPlayerCompletionView.c(this.a).c();
            }
        }
    }
}
