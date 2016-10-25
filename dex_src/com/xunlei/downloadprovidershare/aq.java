package com.xunlei.downloadprovidershare;

import android.text.TextUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovidershare.c.d;

// compiled from: ShareHelper.java
final class aq implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ap b;

    aq(ap apVar, String str) {
        this.b = apVar;
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
        d.b(SHARE_MEDIA.WEIXIN_CIRCLE, this.b.a.a, this.b.a.c, d.b(this.b.a.d));
        if (d.c(this.b.a.d) != null) {
            d.c(this.b.a.d).onShareTargetClicked(SHARE_MEDIA.WEIXIN_CIRCLE, this.b.a.c);
        }
    }
}
