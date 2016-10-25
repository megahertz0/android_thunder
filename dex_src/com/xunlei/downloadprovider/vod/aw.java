package com.xunlei.downloadprovider.vod;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

// compiled from: VodPlayerView.java
final class aw implements OnSeekBarChangeListener {
    final /* synthetic */ VodPlayerView a;

    aw(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        String str = VodPlayerView.TAG;
        if (z) {
            this.a.updateVideoSeekBarThumb(seekBar, i);
            if (i == this.a.mUIParams.f) {
                i -= 3000;
            }
        }
        this.a.mUIParams.g = i;
        if (this.a.mEventListener != null) {
            this.a.mEventListener.onPlayPostionChanged(z, i);
        }
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        if (this.a.mEventListener != null) {
            this.a.mEventListener.onPlayPostionChangeStart();
        }
        this.a.autoHideControlBar(false);
        this.a.updateVideoSeekBarThumb(seekBar, seekBar.getProgress());
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        if (this.a.mEventListener != null) {
            this.a.mEventListener.onPlayPostionChangeEnd(this.a.mUIParams.g);
        }
        this.a.autoHideControlBar(true);
        this.a.dimissVideoSeekBarThumb();
    }
}
