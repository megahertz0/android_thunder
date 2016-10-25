package com.xunlei.downloadprovider.frame.user;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.frame.user.account.k;

// compiled from: HistoryCommentItemFragment.java
final class u implements OnClickListener {
    final /* synthetic */ HistoryCommentItemFragment a;

    u(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onClick(View view) {
        c cVar = HistoryCommentItemFragment.o(this.a).a;
        if (cVar == null) {
            throw new IllegalStateException("comment target is null, call method setTargetComment First");
        }
        Intent intent = new Intent(HistoryCommentItemFragment.c(this.a), ReportActivity.class);
        intent.putExtra("report_target", 1);
        intent.putExtra("comment_id", cVar.a);
        intent.putExtra("comment_res_id", cVar.f);
        intent.putExtra("comment_source_id", cVar.e);
        this.a.startActivity(intent);
        HistoryCommentItemFragment.o(this.a).dismiss();
        k.a("jubao", cVar.a);
    }
}
