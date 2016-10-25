package com.xunlei.downloadprovidershare;

import android.text.TextUtils;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovidershare.b.a;

// compiled from: ShareHelper.java
final class w implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ v b;

    w(v vVar, String str) {
        this.b = vVar;
        this.a = str;
    }

    public final void run() {
        this.b.a.d.c();
        if (TextUtils.isEmpty(this.a)) {
            XLToast.a(this.b.a.a, XLToastType.XLTOAST_TYPE_ALARM, "\u7f51\u7edc\u4e0d\u7ed9\u529b");
            d.a(this.b.a.d);
            return;
        }
        this.b.a.c.h = this.a;
        this.b.a.c.a += "?storid=" + this.a;
        a.a().a(this.b.a.c.a, new x(this));
    }
}
