package com.xunlei.downloadprovidershare;

import android.text.TextUtils;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;

// compiled from: ShareHelper.java
final class ae implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ad b;

    ae(ad adVar, String str) {
        this.b = adVar;
        this.a = str;
    }

    public final void run() {
        this.b.e.c();
        if (TextUtils.isEmpty(this.a)) {
            XLToast.a(this.b.a, XLToastType.XLTOAST_TYPE_ALARM, "\u7f51\u7edc\u4e0d\u7ed9\u529b");
            d.a(this.b.e);
            return;
        }
        this.b.b.h = this.a;
        this.b.b.a += "?storid=" + this.a;
        new StringBuilder("directUrl: ").append(this.b.b.a);
        d.a(this.b.e, this.b.a, this.b.b, this.b.c, this.b.d);
    }
}
