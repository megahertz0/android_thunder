package com.xunlei.downloadprovider.frame.user;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.user.ah.a;

// compiled from: HistoryCommentItemViewHolder.java
final class ab implements OnClickListener {
    final /* synthetic */ v a;

    ab(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (v.b(this.a).m) {
            XLToast.b(view.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u60a8\u5df2\u70b9\u8d5e\u8fc7");
            return;
        }
        v.e(this.a);
        if (v.a(this.a) != null) {
            a a = v.a(this.a);
            v.f(this.a);
            a.a(1, v.b(this.a));
        }
    }
}
