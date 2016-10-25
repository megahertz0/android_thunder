package com.xunlei.downloadprovider.vod;

import android.media.AudioManager.OnAudioFocusChangeListener;

// compiled from: VodPlayerActivity.java
final class af implements OnAudioFocusChangeListener {
    final /* synthetic */ VodPlayerActivity a;

    af(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onAudioFocusChange(int i) {
        VodPlayerActivity.access$500();
        if (i == -2) {
            return;
        }
        if (i == 1) {
            if (VodPlayerActivity.access$1100(this.a)) {
                VodPlayerActivity.access$1200(this.a).Play();
            }
        } else if (i == -1) {
            VodPlayerActivity.access$1400(this.a).abandonAudioFocus(VodPlayerActivity.access$1300(this.a));
            if (VodPlayerActivity.access$800(this.a)) {
                VodPlayerActivity.access$1102(this.a, true);
                VodPlayerActivity.access$1200(this.a).Pause();
            }
        }
    }
}
