package com.xunlei.downloadprovider.player;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: MediaPlayerCompletionView.java
final class e implements OnClickListener {
    final /* synthetic */ MediaPlayerCompletionView a;

    e(MediaPlayerCompletionView mediaPlayerCompletionView) {
        this.a = mediaPlayerCompletionView;
    }

    public final void onClick(View view) {
        if (MediaPlayerCompletionView.c(this.a) != null) {
            MediaPlayerCompletionView.c(this.a).b();
        }
    }
}
