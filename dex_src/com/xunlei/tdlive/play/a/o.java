package com.xunlei.tdlive.play.a;

import android.widget.PopupWindow;

// compiled from: BaseNormalScreenLayoutPresenter.java
class o implements Runnable {
    final /* synthetic */ PopupWindow a;
    final /* synthetic */ c b;

    o(c cVar, PopupWindow popupWindow) {
        this.b = cVar;
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
