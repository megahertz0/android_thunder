package com.xunlei.tdlive.play.a;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

// compiled from: ReplayDialogPresenter.java
private class aa$d implements OnSeekBarChangeListener {
    final /* synthetic */ aa a;

    private aa$d(aa aaVar) {
        this.a = aaVar;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        aa.e(this.a).a(seekBar.getProgress());
        aa.d(this.a).b(seekBar.getProgress());
        aa.f(this.a).clear();
    }
}
