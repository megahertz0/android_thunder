package com.xunlei.downloadprovider.frame.user;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.k;

// compiled from: HistoryCommentItemFragment.java
final class s extends k {
    final /* synthetic */ HistoryCommentItemFragment a;

    s(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void a(RecyclerView recyclerView, int i) {
        super.a(recyclerView, i);
        if (HistoryCommentItemFragment.i(this.a) != 0 && HistoryCommentItemFragment.j(this.a) != 0 && HistoryCommentItemFragment.k(this.a) && i == 0 && ((LinearLayoutManager) HistoryCommentItemFragment.l(this.a).getLayoutManager()).k() >= HistoryCommentItemFragment.g(this.a).getItemCount() - 2) {
            ah g = HistoryCommentItemFragment.g(this.a);
            ai m = HistoryCommentItemFragment.m(this.a);
            if (m != null) {
                g.a.add(m);
                g.notifyItemInserted(g.a.size() - 1);
            }
            HistoryCommentItemFragment.n(this.a);
        }
    }
}
