package com.xunlei.downloadprovider.frame.user;

import com.android.volley.r.b;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.x;
import org.json.JSONObject;

// compiled from: ReportActivity.java
final class ax implements b<JSONObject> {
    final /* synthetic */ x a;
    final /* synthetic */ ReportActivity b;

    ax(ReportActivity reportActivity, x xVar) {
        this.b = reportActivity;
        this.a = xVar;
    }

    public final /* synthetic */ void onResponse(Object obj) {
        new StringBuilder("onResponse").append(((JSONObject) obj).toString());
        this.a.dismiss();
        XLToast.b(this.b, XLToastType.XLTOAST_TYPE_SUC, "\u4e3e\u62a5\u6210\u529f");
        this.b.onBackPressed();
    }
}
