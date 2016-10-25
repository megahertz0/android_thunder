package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.j;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

// compiled from: RecyclerItemClickListener.java
public final class a implements j {
    a a;
    private GestureDetector b;

    // compiled from: RecyclerItemClickListener.java
    public static interface a {
        void a(View view, int i);
    }

    public a(RecyclerView recyclerView, a aVar) {
        this.a = aVar;
        this.b = new GestureDetector(recyclerView.getContext(), new b(this, recyclerView));
    }

    public final boolean a(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.a == null || !this.b.onTouchEvent(motionEvent)) {
            return false;
        }
        this.a.a(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder));
        return true;
    }
}
