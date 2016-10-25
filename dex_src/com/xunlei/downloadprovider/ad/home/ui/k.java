package com.xunlei.downloadprovider.ad.home.ui;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

// compiled from: ADPlayVodItem.java
final class k implements OnCompletionListener {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        i.a(this.a);
    }
}
