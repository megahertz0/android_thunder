package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.R;

// compiled from: ReplayDialogPresenter.java
private class aa$f implements ar$b {
    final /* synthetic */ aa a;

    private aa$f(aa aaVar) {
        this.a = aaVar;
    }

    public void a(int i) {
        aa.b(this.a).mRePlayButtonLayout.seekBar.setMax(i);
    }

    public void a(int i, String str) {
        aa.b(this.a).mRePlayButtonLayout.seekBar.setProgress(i);
        aa.b(this.a).mRePlayButtonLayout.process_txt.setText(str);
    }

    private void b(int i) {
        aa.b(this.a).mRePlayButtonLayout.start_stop_btn.setImageResource(i);
    }

    public void a(boolean z) {
        if (aa.c(this.a) != z) {
            if (aa.a(this.a, z)) {
                b(R.drawable.replay_start_stop_start_btn);
            } else {
                b(R.drawable.replay_start_stop_pause_btn);
            }
        }
        if (z) {
            aa.d(this.a).a();
        } else {
            aa.d(this.a).a(aa.e(this.a).i());
        }
    }
}
