package com.xunlei.downloadprovider.frame.user;

import com.android.volley.r.a;
import com.android.volley.w;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.x;

// compiled from: ReportActivity.java
final class ay implements a {
    final /* synthetic */ x a;
    final /* synthetic */ ReportActivity b;

    ay(ReportActivity reportActivity, x xVar) {
        this.b = reportActivity;
        this.a = xVar;
    }

    public final void onErrorResponse(w wVar) {
        new StringBuilder("onErrorResponse").append(wVar.toString());
        this.a.dismiss();
        XLToast.b(this.b, XLToastType.XLTOAST_TYPE_ALARM, "\u4e3e\u62a5\u5931\u8d25");
    }
}
