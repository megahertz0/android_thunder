package com.xunlei.downloadprovider.web.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: ShortMovieDetailFragment.java
final class an extends BroadcastReceiver {
    final /* synthetic */ ShortMovieDetailFragment a;

    an(ShortMovieDetailFragment shortMovieDetailFragment) {
        this.a = shortMovieDetailFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("action_comment_zan_state_changed".contentEquals(intent.getAction())) {
            ShortMovieDetailFragment.a(this.a, intent);
        }
    }
}
