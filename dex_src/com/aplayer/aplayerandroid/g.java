package com.aplayer.aplayerandroid;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

// compiled from: APlayerAndroid.java
final class g implements OnCompletionListener {
    final /* synthetic */ l a;

    g(l lVar) {
        this.a = lVar;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        APlayerAndroid.access$0();
        this.a.b = 0;
        if (APlayerAndroid.access$12(this.a.e) != null) {
            APlayerAndroid.access$12(this.a.e).a("0x0");
        }
    }
}
