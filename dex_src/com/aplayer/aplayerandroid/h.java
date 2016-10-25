package com.aplayer.aplayerandroid;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

// compiled from: APlayerAndroid.java
final class h implements OnPreparedListener {
    final /* synthetic */ l a;

    h(l lVar) {
        this.a = lVar;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        APlayerAndroid.access$0();
        if (this.a.b == 7) {
            this.a.a.start();
            this.a.b = 4;
            return;
        }
        this.a.b = 3;
        APlayerAndroid.access$13(this.a.e, true);
        APlayerAndroid.access$4(this.a.e);
    }
}
