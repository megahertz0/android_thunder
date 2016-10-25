package com.xunlei.downloadprovider.player;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

// compiled from: MediaPlayerControllerView.java
final class k implements OnSeekBarChangeListener {
    final /* synthetic */ MediaPlayerControllerView a;

    k(MediaPlayerControllerView mediaPlayerControllerView) {
        this.a = mediaPlayerControllerView;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (MediaPlayerControllerView.b(this.a) != null) {
            MediaPlayerControllerView.b(this.a).a(z);
        }
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        MediaPlayerControllerView.c(this.a);
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        if (MediaPlayerControllerView.d(this.a)) {
            MediaPlayerControllerView.e(this.a);
        }
        MediaPlayerControllerView.a(this.a).a(seekBar.getProgress());
    }
}
