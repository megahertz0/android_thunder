package com.xunlei.tdlive.play.view;

import android.widget.PopupWindow;

// compiled from: PlayButtonBar.java
class t implements Runnable {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ PlayButtonBar b;

    t(PlayButtonBar playButtonBar, PopupWindow popupWindow) {
        this.b = playButtonBar;
        this.a = popupWindow;
    }

    public void run() {
        try {
            this.a.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
