package com.xunlei.tdlive.play.a;

import com.xunlei.tdlive.play.view.ChatListView;

// compiled from: ReplayDialogPresenter.java
class af implements Runnable {
    final /* synthetic */ ChatListView a;
    final /* synthetic */ aa b;

    af(aa aaVar, ChatListView chatListView) {
        this.b = aaVar;
        this.a = chatListView;
    }

    public void run() {
        this.b.g().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ag(this));
    }
}
