package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.xllib.a.b;

// compiled from: HistoryCommentItemFragment.java
final class i implements OnClickListener {
    final /* synthetic */ HistoryCommentItemFragment a;

    i(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onClick(View view) {
        HistoryCommentItemFragment.o(this.a).dismiss();
        if (b.a(HistoryCommentItemFragment.c(this.a))) {
            c cVar = HistoryCommentItemFragment.o(this.a).a;
            if (cVar == null) {
                throw new IllegalStateException("comment target is null, call method setTargetComment First");
            }
            HistoryCommentItemFragment.a(this.a, cVar);
            HistoryCommentItemFragment.q(this.a).a(cVar.a);
            k.a("delete", cVar.a);
            return;
        }
        XLToast.b(HistoryCommentItemFragment.c(this.a), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }
}
