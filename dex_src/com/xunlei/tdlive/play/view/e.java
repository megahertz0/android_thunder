package com.xunlei.tdlive.play.view;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

// compiled from: ChatListView.java
class e implements OnScrollListener {
    final /* synthetic */ ChatListView a;
    private int b;
    private int c;

    e(ChatListView chatListView) {
        this.a = chatListView;
        this.b = -1;
        this.c = -1;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        Object obj = 1;
        Object obj2 = null;
        if (this.b != i) {
            this.b = i;
            int i4 = 1;
        }
        if (this.c != i2) {
            this.c = i2;
        } else {
            obj = obj2;
        }
        if (obj != null) {
            this.a.a();
        }
    }
}
