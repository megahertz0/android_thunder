package com.xunlei.downloadprovider.web.base;

import android.content.Context;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovidercommon.a.a;
import com.xunlei.downloadprovidercommon.a.c;

// compiled from: UploadVideoTestDialog.java
public final class bm {
    public static void a(Context context, String str) {
        d dVar = new d(context);
        dVar.setTitle("\u63d0\u793a");
        dVar.b("\u4e0a\u4f20\u89c6\u9891\u7528\u6237\u5f81\u96c6\u4e2d\uff0c\u62a5\u540d\u540e\u53ef\u4ee5\u4f18\u5148\u4f53\u9a8c\uff01");
        dVar.c("\u53d6\u6d88");
        dVar.d("\u6211\u8981\u62a5\u540d");
        dVar.b(new bn(context));
        dVar.a(new bo());
        dVar.show();
        c a = a.a("android_shortvideo_upload", "shortvideo_upload_notice_show");
        a.a("from", str);
        bl.a(a);
    }
}
