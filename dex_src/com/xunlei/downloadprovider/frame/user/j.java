package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.frame.user.account.k;

// compiled from: HistoryCommentItemFragment.java
final class j implements OnClickListener {
    final /* synthetic */ HistoryCommentItemFragment a;

    j(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onClick(View view) {
        HistoryCommentItemFragment.o(this.a).dismiss();
        c cVar = HistoryCommentItemFragment.o(this.a).a;
        if (cVar == null) {
            throw new IllegalStateException("comment target is null, call method setTargetComment First");
        }
        k.a("cancel", cVar.a);
    }
}
