package com.aplayer.aplayerandroid;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;

// compiled from: APlayerAndroid.java
final class f implements OnBufferingUpdateListener {
    final /* synthetic */ l a;

    f(l lVar) {
        this.a = lVar;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        APlayerAndroid.access$0();
        this.a.d = i;
        if (APlayerAndroid.access$7(this.a.e) != null) {
            APlayerAndroid.access$7(this.a.e).a(i);
        }
    }
}
