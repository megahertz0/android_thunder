package com.xunlei.downloadprovider.frame.user;

import android.content.ClipData;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;

// compiled from: HistoryCommentItemFragment.java
final class t implements OnClickListener {
    final /* synthetic */ HistoryCommentItemFragment a;

    t(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void onClick(View view) {
        HistoryCommentItemFragment.o(this.a).dismiss();
        c cVar = HistoryCommentItemFragment.o(this.a).a;
        if (cVar == null) {
            throw new IllegalStateException("comment target is null, call method setTargetComment First");
        }
        HistoryCommentItemFragment.p(this.a).setPrimaryClip(ClipData.newPlainText(WeiXinShareContent.TYPE_TEXT, cVar.b));
        XLToast.b(HistoryCommentItemFragment.c(this.a), XLToastType.XLTOAST_TYPE_SUC, "\u590d\u5236\u6210\u529f");
        k.a("copy", cVar.a);
    }
}
