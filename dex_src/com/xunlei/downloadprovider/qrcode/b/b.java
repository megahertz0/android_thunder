package com.xunlei.downloadprovider.qrcode.b;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

// compiled from: BeepPlayer.java
final class b implements OnErrorListener {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        a.a(this.a).release();
        a.b(this.a);
        return true;
    }
}
