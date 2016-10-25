package com.xunlei.downloadprovider.player.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

// compiled from: PlayerAdapter.java
public final class b extends BaseAdapter {
    List<String> a;
    private Context b;
    private int c;

    public b(Context context) {
        this.a = new ArrayList();
        this.c = -1;
        this.b = context;
    }

    public final int getCount() {
        return this.a.size();
    }

    public final Object getItem(int i) {
        return this.a.get(i);
    }

    public final long getItemId(int i) {
        return 0;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View mediaTestItemView;
        if (view == null) {
            mediaTestItemView = new MediaTestItemView(this.b);
        } else {
            mediaTestItemView = view;
        }
        ((MediaTestItemView) mediaTestItemView).setPlayUrl((String) this.a.get(i));
        mediaTestItemView.setOnClickListener(new c(this, (MediaTestItemView) mediaTestItemView));
        return mediaTestItemView;
    }
}
