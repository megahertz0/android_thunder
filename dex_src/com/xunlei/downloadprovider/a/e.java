package com.xunlei.downloadprovider.a;

import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Context;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;

// compiled from: ClipBoardUti.java
public final class e implements OnPrimaryClipChangedListener {
    final /* synthetic */ ClipboardManager a;
    final /* synthetic */ Context b;

    public e(ClipboardManager clipboardManager, Context context) {
        this.a = clipboardManager;
        this.b = context;
    }

    public final void onPrimaryClipChanged() {
        try {
            this.a.removePrimaryClipChangedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        XLToast.a(this.b, XLToastType.XLTOAST_TYPE_SUC, "\u590d\u5236\u6210\u529f");
    }
}
