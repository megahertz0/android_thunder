package com.xunlei.tdlive.play.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

// compiled from: ChatListView.java
class d implements OnItemClickListener {
    final /* synthetic */ OnItemClickListener a;
    final /* synthetic */ ChatListView b;

    d(ChatListView chatListView, OnItemClickListener onItemClickListener) {
        this.b = chatListView;
        this.a = onItemClickListener;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.onItemClick(adapterView, view, i, j);
        this.b.a.removeCallbacksAndMessages(null);
    }
}
