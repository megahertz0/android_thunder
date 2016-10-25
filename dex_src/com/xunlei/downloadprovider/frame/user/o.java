package com.xunlei.downloadprovider.frame.user;

import android.text.TextUtils;
import com.umeng.common.inter.ITagManager;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.n;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.account.k;
import com.xunlei.downloadprovider.web.base.model.d$b;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: HistoryCommentItemFragment.java
final class o extends d$b {
    final /* synthetic */ HistoryCommentItemFragment a;

    o(HistoryCommentItemFragment historyCommentItemFragment) {
        this.a = historyCommentItemFragment;
    }

    public final void a(String str, u uVar) {
        super.a(str, uVar);
    }

    public final void a(c cVar) {
        long j;
        XLToast.b(HistoryCommentItemFragment.c(this.a), XLToastType.XLTOAST_TYPE_SUC, "\u53d1\u9001\u8bc4\u8bba\u6210\u529f");
        HistoryCommentItemFragment.d(this.a).a(false);
        HistoryCommentItemFragment.d(this.a).dismiss();
        HistoryCommentItemFragment.d(this.a).a(BuildConfig.VERSION_NAME);
        n nVar = cVar.q != null ? (n) cVar.q.get(0) : null;
        if (nVar == null) {
            j = -1;
        } else {
            j = nVar.a;
        }
        HistoryCommentItemFragment.d(this.a).a(j);
        if (HistoryCommentItemFragment.e(this.a) == cVar.i) {
            ai aiVar = new ai(cVar, 0);
            HistoryCommentItemFragment.f(this.a).add(0, cVar);
            ah g = HistoryCommentItemFragment.g(this.a);
            g.a.add(0, aiVar);
            g.notifyItemInserted(0);
        }
        k.a(cVar.e, true, ITagManager.SUCCESS, j, cVar.a);
    }

    public final void a(int i, String str) {
        if (i == 4) {
            if (TextUtils.isEmpty(HistoryCommentItemFragment.d(this.a).a())) {
                HistoryCommentItemFragment.d(this.a).a(false);
            } else {
                HistoryCommentItemFragment.d(this.a).a(true);
            }
            XLToast.b(HistoryCommentItemFragment.c(this.a), XLToastType.XLTOAST_TYPE_ALARM, "\u53d1\u9001\u8bc4\u8bba\u5931\u8d25");
            c cVar = HistoryCommentItemFragment.d(this.a).b;
            k.a(cVar.s.a, false, str, cVar.a, -1);
        } else if (i == 9) {
            XLToast.b(HistoryCommentItemFragment.c(this.a), XLToastType.XLTOAST_TYPE_ALARM, "\u5220\u9664\u8bc4\u8bba\u5931\u8d25");
        }
    }

    public final void a(long j) {
        HistoryCommentItemFragment.a(this.a, j);
        XLToast.b(HistoryCommentItemFragment.c(this.a), XLToastType.XLTOAST_TYPE_SUC, "\u5220\u9664\u8bc4\u8bba\u6210\u529f");
    }

    public final void b(c cVar) {
        cVar.m = true;
    }
}
