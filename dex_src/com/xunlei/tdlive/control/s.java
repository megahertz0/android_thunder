package com.xunlei.tdlive.control;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.q;
import android.view.View;

// compiled from: VisitorHListView.java
class s extends g {
    final /* synthetic */ float a;
    final /* synthetic */ VisitorHListView b;

    s(VisitorHListView visitorHListView, float f) {
        this.b = visitorHListView;
        this.a = f;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, q qVar) {
        int childPosition = recyclerView.getChildPosition(view);
        float f = this.a;
        if (childPosition == this.b.getAdapter().getItemCount() - 1) {
            f = 0.0f;
        }
        rect.set(0, 0, (int) f, 0);
    }
}
