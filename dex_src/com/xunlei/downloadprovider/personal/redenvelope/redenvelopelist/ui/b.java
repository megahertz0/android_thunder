package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.ui;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

// compiled from: RecyclerItemClickListener.java
final class b extends SimpleOnGestureListener {
    final /* synthetic */ RecyclerView a;
    final /* synthetic */ a b;

    b(a aVar, RecyclerView recyclerView) {
        this.b = aVar;
        this.a = recyclerView;
    }

    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    public final void onLongPress(MotionEvent motionEvent) {
        View findChildViewUnder = this.a.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder != null && this.b.a != null) {
            this.a.getChildAdapterPosition(findChildViewUnder);
        }
    }
}
