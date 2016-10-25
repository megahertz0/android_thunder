package com.xunlei.tdlive.play.view;

import com.xunlei.tdlive.a.x;

// compiled from: ChatListView.java
class g implements Runnable {
    final /* synthetic */ x a;
    final /* synthetic */ boolean b;
    final /* synthetic */ ChatListView c;

    g(ChatListView chatListView, x xVar, boolean z) {
        this.c = chatListView;
        this.a = xVar;
        this.b = z;
    }

    public void run() {
        this.a.a(this.b);
    }
}
