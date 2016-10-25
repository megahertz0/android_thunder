package com.aplayer.aplayerandroid;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;

// compiled from: APlayerAndroid.java
final class d implements OnSeekCompleteListener {
    final /* synthetic */ l a;

    d(l lVar) {
        this.a = lVar;
    }

    public final void onSeekComplete(MediaPlayer mediaPlayer) {
        APlayerAndroid.access$0();
        if (APlayerAndroid.access$8(this.a.e) != null) {
            APlayerAndroid.access$8(this.a.e);
        }
    }
}
