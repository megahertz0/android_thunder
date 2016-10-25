package com.xunlei.downloadprovider.vod;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

// compiled from: VodPlayerView.java
final class bc implements OnSeekBarChangeListener {
    final /* synthetic */ VodPlayerView a;

    bc(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.a.autoHideControlBar(true);
    }

    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.a.autoHideControlBar(false);
        String str = VodPlayerView.TAG;
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.a.mUIParams.h = (float) i;
            if (this.a.mEventListener != null) {
                this.a.mEventListener.onVolumnChanged(this.a.mUIParams.h);
            }
        }
    }
}
