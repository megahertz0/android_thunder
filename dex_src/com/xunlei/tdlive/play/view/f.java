package com.xunlei.tdlive.play.view;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

// compiled from: ChatListView.java
class f implements OnScrollListener {
    final /* synthetic */ OnScrollListener a;
    final /* synthetic */ ChatListView b;

    f(ChatListView chatListView, OnScrollListener onScrollListener) {
        this.b = chatListView;
        this.a = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.b.c.onScrollStateChanged(absListView, i);
        this.a.onScrollStateChanged(absListView, i);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.b.c.onScroll(absListView, i, i2, i3);
        this.a.onScroll(absListView, i, i2, i3);
    }
}
