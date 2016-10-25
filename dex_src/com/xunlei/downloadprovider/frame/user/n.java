package com.xunlei.downloadprovider.frame.user;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.g;
import android.support.v7.widget.RecyclerView.q;
import android.view.View;

// compiled from: HistoryCommentItemFragment.java
final class n extends g {
    final /* synthetic */ HistoryCommentItemFragment a;

    n(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onDraw(Canvas canvas, RecyclerView recyclerView, q qVar) {
        int childCount = recyclerView.getChildCount();
        int itemCount = recyclerView.getAdapter().getItemCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (recyclerView.getChildAdapterPosition(childAt) < itemCount - 1) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int left = childAt.getLeft() - layoutParams.leftMargin;
                int right = childAt.getRight() + layoutParams.rightMargin;
                int bottom = layoutParams.bottomMargin + childAt.getBottom();
                HistoryCommentItemFragment.b(this.a).setBounds(left, bottom, right, HistoryCommentItemFragment.a(this.a) + bottom);
                HistoryCommentItemFragment.b(this.a).draw(canvas);
            }
        }
    }

    public final void onDrawOver(Canvas canvas, RecyclerView recyclerView, q qVar) {
        super.onDrawOver(canvas, recyclerView, qVar);
    }

    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, q qVar) {
        int a;
        if (recyclerView.getChildAdapterPosition(view) < recyclerView.getAdapter().getItemCount() - 1) {
            a = HistoryCommentItemFragment.a(this.a);
        } else {
            a = 0;
        }
        rect.set(0, 0, 0, a);
    }
}
