package com.aplayer.aplayerandroid;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

// compiled from: APlayerAndroid.java
final class e implements OnErrorListener {
    final /* synthetic */ l a;

    e(l lVar) {
        this.a = lVar;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        APlayerAndroid.access$0();
        new StringBuilder("mediaplayer onError what ").append(i).append("extra = ").append(i2);
        this.a.b();
        l lVar = this.a;
        APlayerAndroid.access$15(lVar.e, lVar.c, APlayerAndroid.access$2(lVar.e));
        if (APlayerAndroid.access$14(lVar.e) != null) {
            APlayerAndroid.access$14(lVar.e);
        }
        return false;
    }
}
